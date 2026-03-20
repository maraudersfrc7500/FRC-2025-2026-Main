package frc.robot.subsystems;

import com.ctre.phoenix6.hardware.TalonFX;
import com.ctre.phoenix6.signals.MotorAlignmentValue;
import com.ctre.phoenix6.signals.NeutralModeValue;
import com.ctre.phoenix6.controls.DutyCycleOut;
import com.ctre.phoenix6.controls.Follower;
import com.revrobotics.PersistMode;
import com.revrobotics.ResetMode;
import com.revrobotics.spark.SparkMax;
import com.revrobotics.spark.SparkLowLevel.MotorType;
import com.revrobotics.spark.config.SparkBaseConfig.IdleMode;

import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

import com.revrobotics.spark.config.SparkMaxConfig;

public class Intake extends SubsystemBase {
    private final TalonFX motorIL5;
    private final TalonFX motorIF6;
    public boolean doubleIntake;
    private NeutralModeValue motorIF6NMV;

    public Intake() {
        motorIL5 = new TalonFX(5);
        motorIF6 = new TalonFX(6);

        motorIF6NMV = NeutralModeValue.Coast;

        motorIL5.setNeutralMode(NeutralModeValue.Brake);
        motorIF6.setNeutralMode(NeutralModeValue.Brake);

        motorIF6.setControl(new Follower(motorIL5.getDeviceID(), MotorAlignmentValue.Aligned));

        doubleIntake = false;
    }
    public void forward() {
        motorIF6.set(0.6);
    }
    public void reverse() {
        motorIF6.set(-0.5);
    }
    public void disable() {
        motorIF6.stopMotor();
    }
    public void spin(double s) {
        motorIF6.set(s);
    }
    public double getPos() {
        return motorIF6.getPosition().getValueAsDouble();
    }
    public Command forwardCmd() {
        return this.runOnce(() -> forward());
    }
    public Command stopCmd() {
        return this.runOnce(() -> disable());
    }
    public double getVolts() {
        return motorIF6.getMotorVoltage().getValueAsDouble();
    }
    public double getCurrent() {
        return motorIF6.getSupplyCurrent().getValueAsDouble();
    }
    public void changeIntake() {
        if (doubleIntake) {
            motorIF6NMV = NeutralModeValue.Coast;
            motorIF6.setControl(new DutyCycleOut(0));
        } else {
            motorIF6NMV = NeutralModeValue.Brake;
            motorIF6.setControl(new Follower(motorIL5.getDeviceID(), MotorAlignmentValue.Aligned));
        }
        doubleIntake = !doubleIntake;
        motorIF6.setNeutralMode(motorIF6NMV);
    }
}
