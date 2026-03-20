package frc.robot.subsystems;

import com.revrobotics.PersistMode;
import com.revrobotics.ResetMode;
import com.revrobotics.spark.SparkMax;
import com.revrobotics.spark.config.SparkMaxConfig;
import com.revrobotics.spark.config.SparkBaseConfig.IdleMode;

import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

import com.revrobotics.spark.SparkLowLevel.MotorType;

public class Omnispike extends SubsystemBase {
    private final SparkMax motorSP7;

    public Omnispike() {
        motorSP7 = new SparkMax(7, MotorType.kBrushless);

        SparkMaxConfig motorSP7Config = new SparkMaxConfig();
        motorSP7Config.idleMode(IdleMode.kBrake);
        
        motorSP7.configure(motorSP7Config, ResetMode.kResetSafeParameters, PersistMode.kPersistParameters);
    }
    public void enable() {
        motorSP7.set(0.4);
    }
    public void disable() {
        motorSP7.stopMotor();
    }
    public Command enableCmd() {
        return this.runOnce(() -> enable());
    }
    public Command stopCmd() {
        return this.runOnce(() -> disable());
    }
    public void spin(double s) {
        motorSP7.set(s);
    }
}
