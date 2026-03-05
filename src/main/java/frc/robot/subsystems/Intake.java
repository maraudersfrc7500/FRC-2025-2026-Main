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
    private final TalonFX motorIL5, motorIF6;
    // private final SparkMax motorI5, motorV6;

    public Intake() {
        motorIL5 = new TalonFX(5);
        motorIF6 = new TalonFX(6);

        motorIL5.setNeutralMode(NeutralModeValue.Brake);
        motorIF6.setNeutralMode(NeutralModeValue.Brake);

        motorIF6.setControl(new Follower(motorIL5.getDeviceID(), MotorAlignmentValue.Aligned));
    }
    public void forward() {
        motorIL5.set(0.2);
    }
    public void reverse() {
        motorIL5.set(-0.2);
    }
    public void disable() {
        motorIL5.stopMotor();
    }
    public void run(double s) {
        motorIL5.set(s);
    }
}
