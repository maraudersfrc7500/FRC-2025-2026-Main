package frc.robot.subsystems;

import com.ctre.phoenix6.configs.MotorOutputConfigs;
import com.ctre.phoenix6.configs.TalonFXConfiguration;
import com.ctre.phoenix6.configs.TalonFXConfigurator;
import com.ctre.phoenix6.controls.DutyCycleOut;
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
    private TalonFXConfiguration config = new TalonFXConfiguration();

    public Launcher() {
        motorLL9 = new TalonFX(9);

        motorLL9.setNeutralMode(NeutralModeValue.Brake);

        configurePID();
    }

    public void enable() {
        motorLL9.setControl(new DutyCycleOut(1));
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
    public void configurePID() {
        config.Slot0.kV = 60/6400;
        config.Slot0.kS = 0.25;
        config.Slot0.kP = 0.20;

        motorLL9.getConfigurator().apply(config);
    }
}
