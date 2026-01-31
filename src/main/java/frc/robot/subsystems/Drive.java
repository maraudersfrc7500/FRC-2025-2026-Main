package frc.robot.subsystems;

import com.revrobotics.RelativeEncoder;
import com.revrobotics.spark.SparkMax;
import com.revrobotics.spark.SparkLowLevel.MotorType;
import com.revrobotics.spark.config.SparkMaxConfig;
import com.revrobotics.spark.config.SparkBaseConfig.IdleMode;

import edu.wpi.first.wpilibj.drive.MecanumDrive;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class Drive extends SubsystemBase{
    private final SparkMax motorFL1,motorFR2,motorBL3,motorBR4;
    private final SparkMaxConfig smConfig;
    private final RelativeEncoder fLEncoder;
    private final MecanumDrive mec;

    public Drive() {
        motorFL1 = new SparkMax(1, MotorType.kBrushless);
        motorFR2 = new SparkMax(2, MotorType.kBrushless);
        motorBL3 = new SparkMax(3, MotorType.kBrushless);
        motorBR4 = new SparkMax(4,MotorType.kBrushless);
        
        motorFR2.setInverted(true);
        motorBR4.setInverted(true);
        
        smConfig = new SparkMaxConfig();
        smConfig.idleMode(IdleMode.kBrake);
        
        fLEncoder = motorFL1.getEncoder();

        mec = new MecanumDrive(motorFL1, motorBL3, motorFR2, motorBR4);
        mec.setSafetyEnabled(false);
    }
    public void robotCentricDrive(double x, double y, double xr) {
        mec.driveCartesian(x,y,xr);
    }
}
