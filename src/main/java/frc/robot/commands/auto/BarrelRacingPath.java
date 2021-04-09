package frc.robot.commands.auto;

import edu.wpi.first.wpilibj.geometry.Pose2d;
import edu.wpi.first.wpilibj.geometry.Rotation2d;
import edu.wpi.first.wpilibj.util.Units;
import edu.wpi.first.wpilibj2.command.InstantCommand;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.commands.drivetrain.AlignToTarget;
import frc.robot.utils.CustomRamseteCommand;
import frc.robot.subsystems.Drivetrain;
import frc.robot.subsystems.Limelight;
import frc.robot.utils.RamseteGenerator;

import java.util.List;

public class BarrelRacingPath extends SequentialCommandGroup {

    public BarrelRacingPath(Drivetrain drivetrain){
        CustomRamseteCommand barrelRacingPath =
                RamseteGenerator.getRamseteCommand(
                        drivetrain,
                        List.of(
                                new Pose2d(Units.feetToMeters(2.03), Units.feetToMeters(7.55), new Rotation2d().fromDegrees(0.00)),
                                new Pose2d(Units.feetToMeters(15.27), Units.feetToMeters(6.48), new Rotation2d().fromDegrees(-60.00)),
                                new Pose2d(Units.feetToMeters(9.96), Units.feetToMeters(3.19), new Rotation2d().fromDegrees(-245.00)),
                                new Pose2d(Units.feetToMeters(21.40), Units.feetToMeters(7.57), new Rotation2d().fromDegrees(0.00)),
                                new Pose2d(Units.feetToMeters(20.82), Units.feetToMeters(13.03), new Rotation2d().fromDegrees(180.00)),
                                new Pose2d(Units.feetToMeters(20.21), Units.feetToMeters(4.20), new Rotation2d().fromDegrees(-35.00)),
                                new Pose2d(Units.feetToMeters(27.02), Units.feetToMeters(3.46), new Rotation2d().fromDegrees(39.00)),
                                new Pose2d(Units.feetToMeters(25.30), Units.feetToMeters(8.29), new Rotation2d().fromDegrees(180.00)),
                                new Pose2d(Units.feetToMeters(2.01), Units.feetToMeters(7.97), new Rotation2d().fromDegrees(179.52))
                        ),
                        Units.feetToMeters(10), Units.feetToMeters(6), false
                );

        addCommands(
                sequence(
                        new InstantCommand(() -> drivetrain.resetOdometry(barrelRacingPath.getInitialPose())),
                        barrelRacingPath.andThen()
                )
        );

    }

}
