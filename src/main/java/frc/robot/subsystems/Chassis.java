// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel;

import com.revrobotics.RelativeEncoder;
import com.revrobotics.SparkMaxPIDController;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class Chassis extends SubsystemBase {
  /** Creates a new ExampleSubsystem. */

  CANSparkMax left_1;
  CANSparkMax left_2;

  CANSparkMax right_1;
  CANSparkMax right_2;

  DifferentialDrive drive;

  public RelativeEncoder right_encoder;

  public SparkMaxPIDController r_pidController;

  public double kP, kI, kD, kIz, kFF, kMaxOutput, kMinOutput;

  public Chassis() {
    left_1 = new CANSparkMax(1, CANSparkMaxLowLevel.MotorType.kBrushless);
    left_2 = new CANSparkMax(2, CANSparkMaxLowLevel.MotorType.kBrushless);

    right_1 = new CANSparkMax(3, CANSparkMaxLowLevel.MotorType.kBrushless);
    right_2 = new CANSparkMax(4, CANSparkMaxLowLevel.MotorType.kBrushless);

    left_2.follow(left_1);
    right_2.follow(right_1);

    drive = new DifferentialDrive(left_1, right_1);

    right_encoder = right_1.getEncoder();
    r_pidController = right_1.getPIDController();

    // PID coefficients
    kP = 0.5;
    kI = 0;
    kD = 0;
    kIz = 0;
    kFF = 0;
    kMaxOutput = 0.5;
    kMinOutput = -0.5;

    // set PID coefficientss
    r_pidController.setP(kP);
    r_pidController.setI(kI);
    r_pidController.setD(kD);
    r_pidController.setIZone(kIz);
    r_pidController.setFF(kFF);
    r_pidController.setOutputRange(kMinOutput, kMaxOutput);


  }

  public void motor_test(double pct) {
    drive.arcadeDrive(pct, 0);
  }

  public void pid_test(double pos) {
    r_pidController.setReference(pos, CANSparkMax.ControlType.kPosition);
  }

  public void arcade_drive(double fwd, double rot) {
    drive.arcadeDrive(fwd, rot);
  }

  @Override
  public void periodic() {
    drive.feed();
  }


}
