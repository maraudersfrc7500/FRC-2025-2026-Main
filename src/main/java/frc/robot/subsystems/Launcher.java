package frc.robot.subsystems;

import com.ctre.phoenix6.configs.MotorOutputConfigs;
import com.ctre.phoenix6.configs.TalonFXConfiguration;
import com.ctre.phoenix6.configs.TalonFXConfigurator;
import com.ctre.phoenix6.controls.Follower;
import com.ctre.phoenix6.hardware.TalonFX;
import com.ctre.phoenix6.signals.MotorAlignmentValue;
import com.ctre.phoenix6.signals.NeutralModeValue;
import com.revrobotics.PersistMode;
import com.revrobotics.ResetMode;
import com.revrobotics.spark.SparkMax;
import com.revrobotics.spark.config.SparkBaseConfig.IdleMode;

import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

import com.revrobotics.spark.config.SparkMaxConfig;
import com.revrobotics.spark.SparkLowLevel.MotorType;

public class Launcher extends SubsystemBase {
    private final TalonFX motorLL9;
    // private final SparkMax motorLL9, motorLF10;

    public Launcher() {
        motorLL9 = new TalonFX(9);

        motorLL9.setNeutralMode(NeutralModeValue.Brake);
    }

    public void enable() {
        motorLL9.set(0.8);
    }
    public void disable() {
        motorLL9.stopMotor();
    }
    public Command enableCmd() {
        return this.runOnce(() -> enable());
    }
    public Command disableCmd() {
        return this.runOnce(() -> disable());
    }
    public void spin(double s) {
        motorLL9.set(s);
    }
}
