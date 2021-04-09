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

public class SlalomPath extends SequentialCommandGroup {

    public SlalomPath(Drivetrain drivetrain){
        CustomRamseteCommand slalomPath =
                RamseteGenerator.getRamseteCommand(
                        drivetrain,
                        List.of(
                                new Pose2d(Units.feetToMeters(2.43), Units.feetToMeters(2.53), new Rotation2d().fromDegrees(0.00)),
                                new Pose2d(Units.feetToMeters(12.52), Units.feetToMeters(7.53), new Rotation2d().fromDegrees(0.00)),
                                new Pose2d(Units.feetToMeters(19.26), Units.feetToMeters(7.49), new Rotation2d().fromDegrees(0.00)),
                                new Pose2d(Units.feetToMeters(25.54), Units.feetToMeters(2.35), new Rotation2d().fromDegrees(0.00)),
                                new Pose2d(Units.feetToMeters(25.09), Units.feetToMeters(7.49), new Rotation2d().fromDegrees(180.00)),
                                new Pose2d(Units.feetToMeters(19.31), Units.feetToMeters(2.56), new Rotation2d().fromDegrees(180.00)),
                                new Pose2d(Units.feetToMeters(11.97), Units.feetToMeters(2.53), new Rotation2d().fromDegrees(180.00)),
                                new Pose2d(Units.feetToMeters(2.81), Units.feetToMeters(7.83), new Rotation2d().fromDegrees(180.00))
                        ),
                        Units.feetToMeters(10), Units.feetToMeters(6), false
                );

        addCommands(
                sequence(
                        new InstantCommand(() -> drivetrain.resetOdometry(slalomPath.getInitialPose())),
                        slalomPath.andThen()
                )
        );

    }

}
