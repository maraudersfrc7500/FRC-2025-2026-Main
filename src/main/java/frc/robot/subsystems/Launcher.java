package frc.robot.subsystems;

import com.ctre.phoenix6.configs.MotorOutputConfigs;
import com.ctre.phoenix6.configs.TalonFXConfiguration;
import com.ctre.phoenix6.configs.TalonFXConfigurator;
import com.ctre.phoenix6.controls.DutyCycleOut;
import com.ctre.phoenix6.controls.Follower;
import com.ctre.phoenix6.controls.VelocityVoltage;
import com.ctre.phoenix6.hardware.TalonFX;
import com.ctre.phoenix6.signals.MotorAlignmentValue;
import com.ctre.phoenix6.signals.NeutralModeValue;
import com.revrobotics.PersistMode;
import com.revrobotics.ResetMode;
import com.revrobotics.spark.SparkMax;
import com.revrobotics.spark.config.SparkBaseConfig.IdleMode;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

import com.revrobotics.spark.config.SparkMaxConfig;
import com.revrobotics.spark.SparkLowLevel.MotorType;

public class Launcher extends SubsystemBase {
    private final TalonFX motorLL9;
    private TalonFXConfiguration config = new TalonFXConfiguration();
    private final VelocityVoltage veloControl = new VelocityVoltage(0);
    private final double targetRPS = 6400.0 / 60.0;

    public Launcher() {
        motorLL9 = new TalonFX(9);

        motorLL9.setNeutralMode(NeutralModeValue.Brake);

        configurePID();
    }

    public void enable() {
        motorLL9.setControl(veloControl.withVelocity(targetRPS));
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
        config.Slot0.kV = 1.0/targetRPS;
        config.Slot0.kS = 0.25;
        config.Slot0.kP = 0.20;

        motorLL9.getConfigurator().apply(config);
    }
    public boolean isReadyToShoot() {
        double currentRPS = motorLL9.getVelocity().getValueAsDouble();
        return Math.abs(currentRPS - targetRPS) < 2.0;
    }
    public void launchPeriodic() {
        SmartDashboard.putNumber("Launcher RPS: ", motorLL9.getVelocity().getValueAsDouble());
    }
}
