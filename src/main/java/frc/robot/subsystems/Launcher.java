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

import edu.wpi.first.wpilibj2.command.SubsystemBase;

import com.revrobotics.spark.config.SparkMaxConfig;
import com.revrobotics.spark.SparkLowLevel.MotorType;

public class Launcher extends SubsystemBase {
    private final SparkMax motorLL9, motorLF10;

    public Launcher() {
        motorLL9 = new SparkMax(9, MotorType.kBrushless);
        motorLF10 = new SparkMax(10, MotorType.kBrushless);
        
        SparkMaxConfig configLL9 = new SparkMaxConfig();
        configLL9.idleMode(IdleMode.kBrake);
        SparkMaxConfig configLF10 = new SparkMaxConfig();
        configLF10.idleMode(IdleMode.kBrake);
        configLF10.follow(motorLL9);
        
        motorLL9.configure(configLL9, ResetMode.kResetSafeParameters, PersistMode.kPersistParameters);
        motorLF10.configure(configLF10, ResetMode.kResetSafeParameters, PersistMode.kPersistParameters);
    }

    public void enable() {
        motorLL9.set(0.8);
    }
    public void disable() {
        motorLL9.stopMotor();
    }
}
