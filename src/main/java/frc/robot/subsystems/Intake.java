package frc.robot.subsystems;

import com.revrobotics.spark.SparkMax;
import com.revrobotics.spark.SparkBase.PersistMode;
import com.revrobotics.spark.SparkBase.ResetMode;
import com.revrobotics.spark.SparkLowLevel.MotorType;
import com.revrobotics.spark.config.SparkBaseConfig.IdleMode;
import com.revrobotics.spark.config.SparkMaxConfig;

public class Intake {
    private final SparkMax motorI5, motorV6;

    public Intake() {
        motorI5 = new SparkMax(5, MotorType.kBrushless);
        motorV6 = new SparkMax(6,MotorType.kBrushless);

        SparkMaxConfig smConfig = new SparkMaxConfig();
        smConfig.idleMode(IdleMode.kBrake);

        motorI5.configure(smConfig, ResetMode.kResetSafeParameters, PersistMode.kPersistParameters);
        motorV6.configure(smConfig, ResetMode.kResetSafeParameters, PersistMode.kPersistParameters);
    }
    public void forward() {
        motorI5.set(0.8);
        motorV6.set(0.8);
    }
    public void reverse() {
        motorI5.set(-0.8);
        motorV6.set(-0.8);
    }
    public void disable() {
        motorI5.stopMotor();
        motorV6.stopMotor();
    }
}
