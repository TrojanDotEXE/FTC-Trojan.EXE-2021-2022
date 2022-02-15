package org.firstinspires.ftc.teamcode.Autonome.Teste;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;

@Autonomous(name = "Test #2", group = "Teste")
public class Test_2 extends LinearOpMode
{
    DcMotor leftWheel  = null,
            rightWheel = null;

    @Override
    public void runOpMode() throws InterruptedException {
        leftWheel = hardwareMap.get(DcMotor.class, "motorDreapta");
        rightWheel = hardwareMap.get(DcMotor.class, "motorStanga");
        leftWheel.setDirection(DcMotorSimple.Direction.REVERSE);

        waitForStart();

        rightWheel.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        leftWheel.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

        leftWheel.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        rightWheel.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

        leftWheel.setTargetPosition(1440);
        rightWheel.setTargetPosition(1440);

        rightWheel.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        leftWheel.setMode(DcMotor.RunMode.RUN_TO_POSITION);

        leftWheel.setPower(.3);
        rightWheel.setPower(.3);

        while(opModeIsActive() && (rightWheel.isBusy() || leftWheel.isBusy())) {
            telemetry.addData("Left Position / Target :", "%7d / %7d",
                    leftWheel.getCurrentPosition(), leftWheel.getTargetPosition());
            telemetry.addData("Right Position / Target :", "%7d / %7d",
                    rightWheel.getCurrentPosition(), rightWheel.getTargetPosition());
            telemetry.update();
        }

        leftWheel.setPower(0);
        rightWheel.setPower(0);

        leftWheel.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        rightWheel.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

    }
}
