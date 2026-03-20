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
    public boolean doubleIntake;
    private NeutralModeValue motorIF6NMV;

    public Intake() {
        motorIL5 = new TalonFX(5);

        motorIL5.setNeutralMode(NeutralModeValue.Brake);

        doubleIntake = false;
    }
    public void forward() {
        motorIL5.set(0.7);
    }
    public void reverse() {
        motorIL5.set(-0.5);
    }
    public void disable() {
        motorIL5.stopMotor();
    }
    public void spin(double s) {
        motorIL5.set(s);
    }
    public double getPos() {
        return motorIL5.getPosition().getValueAsDouble();
    }
    public Command forwardCmd() {
        return this.runOnce(() -> forward());
    }
    public Command stopCmd() {
        return this.runOnce(() -> disable());
    }
    public double getVolts() {
        return motorIL5.getMotorVoltage().getValueAsDouble();
    }
    public double getCurrent() {
        return motorIL5.getSupplyCurrent().getValueAsDouble();
    }
}
