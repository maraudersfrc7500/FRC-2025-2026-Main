package frc.robot.subsystems;

import com.ctre.phoenix6.hardware.TalonFX;
import com.ctre.phoenix6.signals.MotorAlignmentValue;
import com.ctre.phoenix6.signals.NeutralModeValue;
import com.ctre.phoenix6.controls.Follower;
import com.revrobotics.PersistMode;
import com.revrobotics.ResetMode;
import com.revrobotics.spark.SparkMax;
import com.revrobotics.spark.SparkLowLevel.MotorType;
import com.revrobotics.spark.config.SparkBaseConfig.IdleMode;

import edu.wpi.first.wpilibj2.command.SubsystemBase;

import com.revrobotics.spark.config.SparkMaxConfig;

public class Intake extends SubsystemBase {
    private final TalonFX motorI5, motorV6;
    // private final SparkMax motorI5, motorV6;

    public Intake() {
        motorI5 = new TalonFX(5);
        motorV6 = new TalonFX(6);

        motorI5.setNeutralMode(NeutralModeValue.Brake);
        motorV6.setNeutralMode(NeutralModeValue.Brake);

        motorI5.setControl(new Follower(motorI5.getDeviceID(), MotorAlignmentValue.Opposed));
    }
    public void forward() {
        motorI5.set(0.8);
    }
    public void reverse() {
        motorI5.set(-0.8);
    }
    public void disable() {
        motorI5.stopMotor();
    }
}
