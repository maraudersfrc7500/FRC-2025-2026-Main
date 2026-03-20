  // Copyright (c) FIRST and other WPILib contributors.
  // Open Source Software; you can modify and/or share it under the terms of
  // the WPILib BSD license file in the root directory of this project.

  package frc.robot;

  import java.io.IOException;
  import java.util.function.BooleanSupplier;

  import org.json.simple.parser.ParseException;

  import com.pathplanner.lib.auto.AutoBuilder;
import com.pathplanner.lib.auto.NamedCommands;
import com.pathplanner.lib.config.RobotConfig;
  import com.pathplanner.lib.controllers.PPLTVController;

import edu.wpi.first.cscore.CvSink;
import edu.wpi.first.cscore.CvSource;
import edu.wpi.first.cscore.MjpegServer;
import edu.wpi.first.cscore.UsbCamera;
import edu.wpi.first.util.PixelFormat;
import edu.wpi.first.wpilibj.Alert;
  import edu.wpi.first.wpilibj.DriverStation;
  import edu.wpi.first.wpilibj.DriverStation.Alliance;
  import edu.wpi.first.wpilibj.PowerDistribution;
  import edu.wpi.first.wpilibj.PowerDistribution.ModuleType;
  import edu.wpi.first.wpilibj.XboxController;
  import edu.wpi.first.wpilibj.Alert.AlertType;
  import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
  import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
  import edu.wpi.first.wpilibj2.command.Command;
  import frc.robot.subsystems.Climb;
  import frc.robot.subsystems.Drive;
  import frc.robot.subsystems.Intake;
  import frc.robot.subsystems.Launcher;
  import frc.robot.subsystems.Omnispike;
  
  public class RobotContainer {

    private PowerDistribution PD;

    private final Drive driveS;
    private final Intake intakeS;
    private final Launcher launcherS;
    private final Omnispike omnispikeS;
    // private final Climb climbS;

    private XboxController driver, operator;

    private final SendableChooser<Command> autoChooser;
    private int driveType;

    private double triggerSpeed, leftY, leftX, rightY, rightX;

    public RobotContainer() {
      PD = new PowerDistribution(63,ModuleType.kRev);

      driveS = new Drive();
      intakeS = new Intake();
      launcherS = new Launcher();
      omnispikeS = new Omnispike();
      // climbS = new Climb();

      //Initialize Cameras
      
      // Creates UsbCamera and MjpegServer [1] and connects them
      UsbCamera usbCamera = new UsbCamera("USB Camera 0", 0);
      MjpegServer mjpegServer1 = new MjpegServer("serve_USB Camera 0", 1181);
      mjpegServer1.setSource(usbCamera);
      // Creates the CvSink and connects it to the UsbCamera
      CvSink cvSink = new CvSink("opencv_USB Camera 0");
      cvSink.setSource(usbCamera);
      // Creates the CvSource and MjpegServer [2] and connects them
      CvSource outputStream = new CvSource("Blur", PixelFormat.kMJPEG, 640, 480, 60);
      MjpegServer mjpegServer2 = new MjpegServer("serve_Blur", 1182);
      mjpegServer2.setSource(outputStream);

      driver = new XboxController(0);
      operator = new XboxController(1);

      configureAutoBuilder();
      configureBindings();

      registerIntakeCmds();

      if (AutoBuilder.isConfigured()) {
        autoChooser = AutoBuilder.buildAutoChooser();
        SmartDashboard.putData("Auto Chooser: ", autoChooser);
      } else {
        autoChooser = new SendableChooser<>();
        DriverStation.reportError("AutoBuilder not configured!", false);
      }
    }

    private void configureAutoBuilder() {
      RobotConfig robotConfig;
      try {
        robotConfig = RobotConfig.fromGUISettings();
      } catch (IOException | ParseException e) {
        e.printStackTrace();
        DriverStation.reportError("Failed to load robot config: " + e.getMessage(), false);
        return;
      }
      PPLTVController ltvController = new PPLTVController(0.02);
      
      BooleanSupplier shouldFlipPath = () -> {
        var alliance = DriverStation.getAlliance().orElse(Alliance.Blue);
        return alliance == Alliance.Red;
      };

      AutoBuilder.configure(
        driveS::getPose,
        driveS::resetOdometry,
        driveS::getChassisSpeeds,
        driveS::driveD,
        ltvController,
        robotConfig,
        shouldFlipPath,
        driveS
      );
    }

    private void configureBindings() {}

    private void registerIntakeCmds() {
      NamedCommands.registerCommand("Intake Forward", intakeS.forwardCmd());
      NamedCommands.registerCommand("Intake Disable", intakeS.stopCmd());
      NamedCommands.registerCommand("Launcher Enable", launcherS.enableCmd());
      NamedCommands.registerCommand("Launcher Disable", launcherS.disableCmd());
      NamedCommands.registerCommand("OmniSpike Enable", omnispikeS.enableCmd());
      NamedCommands.registerCommand("OmniSpike Disable", omnispikeS.stopCmd());
    }

    public void autoPeriodics() {
      telemetry();
    }

    public void teleopPeriodics() {
      telemetry();

      triggerSpeed = driver.getRightTriggerAxis() - driver.getLeftTriggerAxis();
      leftY = -driver.getLeftY();
      leftX = -driver.getLeftX();
      rightY = -driver.getRightY();
      rightX = -driver.getRightX();
      
      driveS.robotCentricDrive(leftY, rightX);

      if (operator.getPOV() == 0) {
        intakeS.forward();
      }
      if (operator.getPOV() == 180) {
        intakeS.disable();
      }
      // if (operator.getLeftBumperButtonPressed()) {
      //   intakeS.changeIntake();
      // }

      if (operator.getAButtonPressed()) {
        omnispikeS.enable();
      }
      if (operator.getBButtonPressed()) {
        omnispikeS.disable();
      }

      if (operator.getYButtonPressed()) {
        launcherS.enable();
      }
      if (operator.getXButtonPressed()) {
        launcherS.disable();
      }
      if (operator.getLeftBumperPressed()) {
        launcherS.changePower(operator.getPOV());
      }
      SmartDashboard.putNumber("POV: ", operator.getPOV());
    }
    
    public double calculateDistance() {
      double a1 = 8.77782;
      double a2 = LimelightHelpers.getTX("limelight-front");
      double h1 = 23.25;
      double h2 = 44.25;
      double height_diff=(h2-h1);
      double angle = Math.toRadians(Math.abs(a2)+Math.abs(a1));
      double distance = (height_diff/Math.tan(angle));
      return distance;
    }
      
    public void telemetry() {
      SmartDashboard.putNumber("XPos: ", driveS.getX());
      SmartDashboard.putNumber("YPos: ",driveS.getY());
      SmartDashboard.putNumber("Heading: ",driveS.getH().getDegrees());
      SmartDashboard.putNumber("Voltage: ",PD.getVoltage());
      SmartDashboard.putNumber("Encoder Left: ", driveS.getEncoderLeft());
      SmartDashboard.putNumber("Encoder Right: ", driveS.getEncoderRight());
      SmartDashboard.putBoolean("Replace Battery", PD.getVoltage() < 12.2);
      SmartDashboard.putBoolean("Double Intake: ", intakeS.doubleIntake);
      launcherS.launchPeriodic();
    }

    public Command getAutonomousCommand() {
      return autoChooser.getSelected();
    }

    private double deadband(double d) {
      if (Math.abs(d) < 0.18) {
        return 0;
      } else {
        return d;
      }
    }
  }
