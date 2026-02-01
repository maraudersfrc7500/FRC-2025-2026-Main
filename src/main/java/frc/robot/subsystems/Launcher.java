package frc.robot.subsystems;

import com.revrobotics.spark.SparkMax;
import com.revrobotics.spark.config.SparkBaseConfig.IdleMode;
import com.revrobotics.spark.config.SparkMaxConfig;
import com.revrobotics.spark.SparkBase.PersistMode;
import com.revrobotics.spark.SparkBase.ResetMode;
import com.revrobotics.spark.SparkLowLevel.MotorType;

public class Launcher {
    private final SparkMax motorL7, motorL8;

    public Launcher() {
        motorL7 = new SparkMax(10, MotorType.kBrushless);
        motorL8 = new SparkMax(11, MotorType.kBrushless);

        SparkMaxConfig smConfig = new SparkMaxConfig();
        smConfig.idleMode(IdleMode.kBrake);

        motorL7.configure(smConfig, ResetMode.kResetSafeParameters, PersistMode.kPersistParameters);
        motorL8.configure(smConfig, ResetMode.kResetSafeParameters, PersistMode.kPersistParameters);
    }

    public void enable() {
        motorL7.set(0.8);
        motorL8.set(0.8);
    }
    public void disable() {
        motorL7.stopMotor();
        motorL8.stopMotor();
    }
}
