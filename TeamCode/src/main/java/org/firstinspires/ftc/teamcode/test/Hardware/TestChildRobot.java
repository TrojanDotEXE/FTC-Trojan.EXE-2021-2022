package org.firstinspires.ftc.teamcode.test.Hardware;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.teamcode.hardware.drives.MecanumDrive;
import org.firstinspires.ftc.teamcode.test.Hardware.TestRobot;

public class TestChildRobot extends TestRobot {

    public OpMode opMode;
    public HardwareMap hardwareMap;

    public MecanumDrive mecanumDrive;

    public TestChildRobot( OpMode op ) {

        super( op );
        opMode = op;
        hardwareMap = op.hardwareMap;

        super.driveTrain = new MecanumDrive( hardwareMap );
        mecanumDrive = (MecanumDrive) driveTrain;
    }

    /**
     * @param time wait time in seconds
     */
    public void sleepRobot( double time ) {
        double startTime = opMode.getRuntime( );
        while( opModeIsActive( ) && startTime + time > opMode.getRuntime( ) );
    }
}
