package frc.robot.subsystems;

import com.revrobotics.spark.SparkMax;
import com.revrobotics.spark.SparkBase.PersistMode;
import com.revrobotics.spark.SparkBase.ResetMode;
import com.revrobotics.spark.SparkLowLevel.MotorType;
import com.revrobotics.spark.config.SparkBaseConfig.IdleMode;
import com.revrobotics.spark.config.SparkMaxConfig;

public class Intake {
    private final SparkMax motorI3, motorV4;

    public Intake() {
        motorI3 = new SparkMax(5, MotorType.kBrushless);
        motorV4 = new SparkMax(6,MotorType.kBrushless);

        SparkMaxConfig smConfig = new SparkMaxConfig();
        smConfig.idleMode(IdleMode.kBrake);

        motorI3.configure(smConfig, ResetMode.kResetSafeParameters, PersistMode.kPersistParameters);
        motorV4.configure(smConfig, ResetMode.kResetSafeParameters, PersistMode.kPersistParameters);
    }
    public void forward() {
        motorI3.set(0.8);
        motorV4.set(0.8);
    }
    public void reverse() {
        motorI3.set(-0.8);
        motorV4.set(-0.8);
    }
    public void disable() {
        motorI3.stopMotor();
        motorV4.stopMotor();
    }
}
