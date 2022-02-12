package org.firstinspires.ftc.teamcode.Autonome;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.teamcode.Hardware.HardwareM;

@Autonomous(name = "Test Encodere")
public class Test_Encodere extends LinearOpMode
{
    DcMotor roataStanga = null,
            roataDreapta = null;

    @Override
    public void runOpMode() throws InterruptedException {
        roataStanga = hardwareMap.get(DcMotor.class, "motorStanga");
        roataStanga.setDirection(DcMotorSimple.Direction.FORWARD);
        roataDreapta = hardwareMap.get(DcMotor.class, "motorDreapta");
        roataDreapta.setDirection(DcMotorSimple.Direction.REVERSE);

        // Wait for start while displaying encoder values  (Note: these may not start at zero)
        // This i   s just for testing
        waitForStart();

        // Reset encoders
        roataDreapta.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        roataStanga.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

        // Set target position and speedloin
        roataStanga.setTargetPosition(1440);    //10 * NEVEREST40_TICKS_PER_INCH
        roataDreapta.setTargetPosition(1440);

        // Prepare to drive to target position
        roataDreapta.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        roataStanga.setMode(DcMotor.RunMode.RUN_TO_POSITION);

        roataStanga.setPower(.3);
        roataDreapta.setPower(.3);

        // Loop while we approach the target.  Display position as we go
        while(opModeIsActive() && (roataDreapta.isBusy() || roataStanga.isBusy())) {
            telemetry.addData("Left Position / Target :", "%7d / %7d",
                              roataStanga.getCurrentPosition(), roataStanga.getTargetPosition());
            telemetry.addData("Right Position / Target :", "%7d / %7d",
                              roataDreapta.getCurrentPosition(), roataDreapta.getTargetPosition());
            telemetry.update();
        }

        // We are done, turn motors off and switch back to normal driving mode.
        roataStanga.setPower(0);
        roataDreapta.setPower(0);

        roataStanga.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        roataDreapta.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        }
    }

