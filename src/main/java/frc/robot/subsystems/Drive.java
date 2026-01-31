package frc.robot.subsystems;

import com.revrobotics.PersistMode;
import com.revrobotics.RelativeEncoder;
import com.revrobotics.ResetMode;
import com.revrobotics.spark.SparkMax;
import com.revrobotics.spark.SparkLowLevel.MotorType;
import com.revrobotics.spark.config.SparkMaxConfig;
import com.revrobotics.spark.config.SparkBaseConfig.IdleMode;

import edu.wpi.first.math.geometry.Translation2d;
import edu.wpi.first.math.kinematics.MecanumDriveKinematics;
import edu.wpi.first.math.kinematics.MecanumDriveOdometry;
import edu.wpi.first.math.kinematics.MecanumDriveWheelPositions;
import edu.wpi.first.wpilibj.drive.MecanumDrive;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class Drive extends SubsystemBase{
    private final SparkMax motorFL1,motorFR2,motorBL3,motorBR4;
    private final SparkMaxConfig smConfig;
    private final RelativeEncoder FLEncoder, FREncoder, BLEncoder, BREncoder;
    private final Translation2d FLwheelLoc, FRwheelLoc, BLwheelLoc, BRwheelLoc;
    private final MecanumDrive mec;
    private final MecanumDriveKinematics mecDK;
    private MecanumDriveOdometry mecOdom;

    public Drive() {
        motorFL1 = new SparkMax(1, MotorType.kBrushless);
        motorFR2 = new SparkMax(2, MotorType.kBrushless);
        motorBL3 = new SparkMax(3, MotorType.kBrushless);
        motorBR4 = new SparkMax(4,MotorType.kBrushless);
        
        motorFR2.setInverted(true);
        motorBR4.setInverted(true);
        
        smConfig = new SparkMaxConfig();
        smConfig.idleMode(IdleMode.kBrake);

        motorFL1.configure(smConfig,ResetMode.kResetSafeParameters,PersistMode.kPersistParameters);
        motorFR2.configure(smConfig,ResetMode.kResetSafeParameters,PersistMode.kPersistParameters);
        motorBL3.configure(smConfig,ResetMode.kResetSafeParameters,PersistMode.kPersistParameters);
        motorBR4.configure(smConfig,ResetMode.kResetSafeParameters,PersistMode.kPersistParameters);
        
        FLEncoder = motorFL1.getEncoder();
        FREncoder = motorFR2.getEncoder();
        BLEncoder = motorBL3.getEncoder();
        BREncoder = motorBR4.getEncoder();

        FLEncoder.setPosition(0);
        FREncoder.setPosition(0);
        BLEncoder.setPosition(0);
        BREncoder.setPosition(0);

        mec = new MecanumDrive(motorFL1, motorBL3, motorFR2, motorBR4);
        mec.setSafetyEnabled(false);

        FLwheelLoc = new Translation2d(0,0);
        FRwheelLoc = new Translation2d(0,0);
        BLwheelLoc = new Translation2d(0,0);
        BRwheelLoc = new Translation2d(0,0);

        mecDK = new MecanumDriveKinematics(FLwheelLoc, FRwheelLoc, BLwheelLoc, BRwheelLoc);
    }
    public void robotCentricDrive(double x, double y, double xr) {
        mec.driveCartesian(x,y,xr);
    }

    @Override
    public void periodic() {
        MecanumDriveWheelPositions MDWP = new MecanumDriveWheelPositions(FLEncoder.getPosition(),FREncoder.getPosition(),BLEncoder.getPosition(),BREncoder.getPosition());
        mecOdom = new MecanumDriveOdometry(mecDK, null, MDWP);
    }
}
