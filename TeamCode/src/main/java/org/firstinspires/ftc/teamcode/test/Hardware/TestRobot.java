package org.firstinspires.ftc.teamcode.test.Hardware;

import com.qualcomm.hardware.lynx.LynxModule;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.HardwareMap;
import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.hardware.drives.Drivetrain;

public abstract class TestRobot {

    HardwareMap hardwareMap;
    OpMode opMode;
    Telemetry telemetry;

    public Drivetrain driveTrain;

    /**
     * Creates a Robot
     *
     * @param op robot's opMode
     */
    public TestRobot( OpMode op ) {
        opMode = op;
        hardwareMap = opMode.hardwareMap;
        telemetry = opMode.telemetry;

        //vuforiaKey = hardwareMap.appContext.getResources().getString(R.string.vuforiakey);

        //Bulk Caching to decrease cycle times
        for( LynxModule module : hardwareMap.getAll( LynxModule.class ) )
            module.setBulkCachingMode( LynxModule.BulkCachingMode.AUTO );
    }

    /**
     * @param millis the amount of milliseconds to wait in a while loop
     */
    public void sleep( long millis ) {
        long startTime = System.currentTimeMillis( );
        while( System.currentTimeMillis( ) < startTime + millis && opModeIsActive( ) ) ;
    }

    public boolean opModeIsActive( ) {
        try {
            return ((LinearOpMode) opMode).opModeIsActive( );
        } catch( ClassCastException e ) {
            return true;
        }
    }
}
