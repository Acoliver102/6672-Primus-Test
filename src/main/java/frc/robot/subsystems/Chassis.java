// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel;

import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class Chassis extends SubsystemBase {
  /** Creates a new ExampleSubsystem. */

  CANSparkMax left_1;
  CANSparkMax left_2;

  CANSparkMax right_1;
  CANSparkMax right_2;

  DifferentialDrive drive;

  public Chassis() {
    left_1 = new CANSparkMax(1, CANSparkMaxLowLevel.MotorType.kBrushless);
    left_2 = new CANSparkMax(2, CANSparkMaxLowLevel.MotorType.kBrushless);

    right_1 = new CANSparkMax(3, CANSparkMaxLowLevel.MotorType.kBrushless);
    right_2 = new CANSparkMax(4, CANSparkMaxLowLevel.MotorType.kBrushless);

    left_2.follow(left_1);
    right_2.follow(right_1);

    drive = new DifferentialDrive(left_1, right_1);
  }

  public void motor_test(double pct) {
    drive.arcadeDrive(pct, 0);
  }

  public void arcade_drive(double fwd, double rot) {
    drive.arcadeDrive(fwd, rot);
  }

  @Override
  public void periodic() {
    drive.feed();
  }


}
