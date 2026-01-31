package frc.robot.subsystems;

import com.revrobotics.spark.SparkMax;
import com.revrobotics.spark.SparkLowLevel.MotorType;
import com.revrobotics.spark.config.SparkBaseConfig.IdleMode;
import com.revrobotics.spark.config.SparkMaxConfig;

public class Intake {
    private final SparkMax motorI5, motorV6;
    private final SparkMaxConfig smConfig;

    public Intake() {
        motorI5 = new SparkMax(5, MotorType.kBrushless);
        motorV6 = new SparkMax(6,MotorType.kBrushless);

        smConfig = new SparkMaxConfig();
        smConfig.idleMode(IdleMode.kBrake);
    }
    public void enableIntake() {
        motorI5.set(0.8);
        motorV6.set(0.8);
    }
    public void disable() {
        motorI5.stopMotor();
        motorV6.stopMotor();
    }
}
