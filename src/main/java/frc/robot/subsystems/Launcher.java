package frc.robot.subsystems;

import com.ctre.phoenix6.configs.TalonFXConfiguration;
import com.ctre.phoenix6.controls.VelocityVoltage;
import com.ctre.phoenix6.hardware.TalonFX;
import com.ctre.phoenix6.signals.NeutralModeValue;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class Launcher extends SubsystemBase {
    private final TalonFX motorLL9;
    private TalonFXConfiguration config = new TalonFXConfiguration();
    private final VelocityVoltage veloControl = new VelocityVoltage(0);
    private double targetRPS;
    private double launchPower;

    public Launcher() {
        motorLL9 = new TalonFX(9);

        motorLL9.setControl(veloControl.withVelocity(0));
        
        configurePID();
        motorLL9.setNeutralMode(NeutralModeValue.Brake);

        launchPower = 0.85;
        targetRPS = (6400.0*launchPower) / 60.0;
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
        config.Slot0.kP = 0.35;

        motorLL9.getConfigurator().apply(config);
    }
    public boolean isReadyToShoot() {
        double currentRPS = motorLL9.getVelocity().getValueAsDouble();
        return Math.abs(currentRPS - targetRPS) < 2.0;
    }
    public void launchPeriodic() {
        SmartDashboard.putNumber("Launcher RPS: ", motorLL9.getVelocity().getValueAsDouble());
        SmartDashboard.putBoolean("Ready To Shoot: ",isReadyToShoot());
        SmartDashboard.putNumber("Power: ",launchPower);
        SmartDashboard.putNumber("Target RPS: ",targetRPS);
    }
    public void changePower(int POV) {
        if (POV == 270) {
            if (launchPower > 0.5) {
                launchPower -= 0.05;
            }
        }
        if (POV == 90) {
            if (launchPower < 1.0) {
                launchPower += 0.05;
            }
        }
        targetRPS = 6400 * launchPower;
    }
}
