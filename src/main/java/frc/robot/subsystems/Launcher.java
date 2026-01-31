package frc.robot.subsystems;

import com.revrobotics.spark.SparkMax;
import com.revrobotics.spark.SparkLowLevel.MotorType;

public class Launcher {
    private final SparkMax motorL10, motorL11;

    public Launcher() {
        motorL10 = new SparkMax(10, MotorType.kBrushless);
        motorL11 = new SparkMax(11, MotorType.kBrushless);
    }
}
