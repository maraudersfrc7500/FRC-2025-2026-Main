package frc.robot.subsystems;

import com.revrobotics.PersistMode;
import com.revrobotics.RelativeEncoder;
import com.revrobotics.ResetMode;
import com.revrobotics.spark.SparkClosedLoopController;
import com.revrobotics.spark.SparkMax;
import com.revrobotics.spark.SparkBase.ControlType;
import com.revrobotics.spark.SparkLowLevel.MotorType;
import com.revrobotics.spark.config.SparkMaxConfig;
import com.revrobotics.spark.config.SparkBaseConfig.IdleMode;

import edu.wpi.first.math.controller.PIDController;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class Climb extends SubsystemBase {
    private final SparkMax motorC11, motorC12;
    private SparkClosedLoopController pidC11;
    private RelativeEncoder encoderC11;

    public Climb() {
        motorC11 = new SparkMax(11, MotorType.kBrushless);
        motorC12 = new SparkMax(12, MotorType.kBrushless);

        SparkMaxConfig motorC11Config = new SparkMaxConfig();
        motorC11Config.idleMode(IdleMode.kBrake);
        motorC11Config.closedLoop.pid(0.1,0.0,0.0);
        SparkMaxConfig motorC12Config = new SparkMaxConfig();
        motorC12Config.idleMode(IdleMode.kBrake);
        motorC12Config.follow(motorC11);

        motorC11.configure(motorC11Config, ResetMode.kResetSafeParameters, PersistMode.kPersistParameters);
        motorC12.configure(motorC12Config, ResetMode.kResetSafeParameters, PersistMode.kPersistParameters);

        pidC11 = motorC11.getClosedLoopController();

        encoderC11 = motorC11.getEncoder();
    }
    public void resetEncoders() {
        encoderC11.setPosition(0);
    }
    public void setPosition(double targetRotations) {
        pidC11.setReference(targetRotations, ControlType.kPosition);
    }
}
