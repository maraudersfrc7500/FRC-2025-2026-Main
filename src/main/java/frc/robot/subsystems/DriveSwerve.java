package frc.robot.subsystems;

import java.io.File;
import java.io.IOException;

import edu.wpi.first.math.geometry.Pose2d;
import edu.wpi.first.math.geometry.Rotation2d;
import edu.wpi.first.math.geometry.Translation2d;
import edu.wpi.first.math.kinematics.ChassisSpeeds;
import edu.wpi.first.math.kinematics.SwerveModuleState;
import edu.wpi.first.wpilibj.Filesystem;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import swervelib.parser.SwerveParser;
import swervelib.telemetry.SwerveDriveTelemetry;
import swervelib.telemetry.SwerveDriveTelemetry.TelemetryVerbosity;
import swervelib.SwerveDrive;

public class DriveSwerve extends SubsystemBase {
    private SwerveDrive swerve;

    private double maxSpeed = 1.4;

    private File swerveJsonDirectory;

    public DriveSwerve() {
        swerveJsonDirectory = new File(Filesystem.getDeployDirectory(),"swerve");

        try {
            swerve = new SwerveParser(swerveJsonDirectory).createSwerveDrive(maxSpeed);
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        SwerveDriveTelemetry.verbosity = TelemetryVerbosity.HIGH;
    }
    public void fieldCentricDrive(Translation2d xy, double xr) {
        swerve.drive(xy, xr, true, true);
    }
    public void robotCentricDrive(Translation2d xy, double xr) {
        swerve.drive(xy,xr,false,true);
    }
    public void driveAuto(ChassisSpeeds speeds) {
        swerve.drive(speeds);
    }
    
    public Pose2d getPosition() {
        return swerve.getPose();
    }
    public void resetOdom(Pose2d pose) {
        swerve.resetOdometry(pose);
    }
    public Rotation2d getHeading() {
        return swerve.getOdometryHeading();
    }
    public void resetGyro() {
        swerve.zeroGyro();
    }

    public SwerveModuleState[] getModuleStates() {
        return swerve.getStates();
    }
    public ChassisSpeeds getRobotRelativeSpeeds() {
        return swerve.getRobotVelocity();
        
    }
}
