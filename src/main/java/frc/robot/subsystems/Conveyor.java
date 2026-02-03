package frc.robot.subsystems;

import com.revrobotics.spark.SparkMax;
import com.revrobotics.spark.config.SparkBaseConfig.IdleMode;
import com.revrobotics.spark.config.SparkMaxConfig;
import com.revrobotics.spark.SparkBase.PersistMode;
import com.revrobotics.spark.SparkBase.ResetMode;
import com.revrobotics.spark.SparkLowLevel.MotorType;

public class Conveyor {
    private final SparkMax motorC7, motorC8;

    public Conveyor() {
        motorC7 = new SparkMax(7,MotorType.kBrushless);
        motorC8 = new SparkMax(8,MotorType.kBrushless);

        SparkMaxConfig smConfig = new SparkMaxConfig();
        smConfig.idleMode(IdleMode.kBrake);

        motorC7.configure(smConfig, ResetMode.kResetSafeParameters, PersistMode.kPersistParameters);
        motorC8.configure(smConfig, ResetMode.kResetSafeParameters, PersistMode.kPersistParameters);
    }

    public void enable() {
        motorC7.set(0.8);
        motorC8.set(0.8);
    }
    public void disable() {
        motorC7.stopMotor();
        motorC8.stopMotor();
    }
}
