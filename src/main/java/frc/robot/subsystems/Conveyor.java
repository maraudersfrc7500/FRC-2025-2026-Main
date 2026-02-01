package frc.robot.subsystems;

import com.revrobotics.spark.SparkMax;
import com.revrobotics.spark.config.SparkBaseConfig.IdleMode;
import com.revrobotics.spark.config.SparkMaxConfig;
import com.revrobotics.spark.SparkBase.PersistMode;
import com.revrobotics.spark.SparkBase.ResetMode;
import com.revrobotics.spark.SparkLowLevel.MotorType;

public class Conveyor {
    private final SparkMax motorC5, motorC6;

    public Conveyor() {
        motorC5 = new SparkMax(7,MotorType.kBrushless);
        motorC6 = new SparkMax(8,MotorType.kBrushless);

        SparkMaxConfig smConfig = new SparkMaxConfig();
        smConfig.idleMode(IdleMode.kBrake);

        motorC5.configure(smConfig, ResetMode.kResetSafeParameters, PersistMode.kPersistParameters);
        motorC6.configure(smConfig, ResetMode.kResetSafeParameters, PersistMode.kPersistParameters);
    }

    public void enable() {
        motorC5.set(0.8);
        motorC6.set(0.8);
    }
    public void disable() {
        motorC5.stopMotor();
        motorC6.stopMotor();
    }
}
