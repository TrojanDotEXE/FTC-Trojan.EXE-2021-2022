package org.firstinspires.ftc.teamcode.Autonome;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;

import org.firstinspires.ftc.teamcode.Hardware.HardwareM;

public class MetodeAutonoma extends LinearOpMode {
    HardwareM fer = new HardwareM();

    @Override
    public void runOpMode() throws InterruptedException {}

    //ambele motoare aceeasi distanta
    public void goTo(double power, int rotatii, DcMotor... motors){
        for(DcMotor motor:motors)
        {
            motor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
            motor.setTargetPosition(rotatii);
            motor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            motor.setPower(power);
        }
    }

    //motoarele au directii diferite
    public void goTo(double power, int rotatii1, int rotatii2, DcMotor motor1, DcMotor motor2){
        goTo(power, rotatii1, motor1);
        goTo(power, rotatii2, motor2);
    }

    //rotatii+ => rotire dreapta
    //rotatii- => rotire stanga
    public void turn (double power, int rotatii, DcMotor roataStanga, DcMotor roataDreapta) {
        roataStanga.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        roataStanga.setTargetPosition(rotatii);
        roataStanga.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        roataStanga.setPower(power);
        roataDreapta.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        roataDreapta.setTargetPosition(-rotatii);
        roataDreapta.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        roataDreapta.setPower(power);
    }

    //nr rotatii encoder
    public void displayEncoder(DcMotor motor){
        while(motor.isBusy())
        {
            telemetry.addData("Left Position / Target :", "%7d / %7d",
                    motor.getCurrentPosition(), motor.getTargetPosition());
            telemetry.update();
        }
    }

    //nr rotatii 2 encodere
    public void displayEncoders(DcMotor motorStanga, DcMotor motorDreapta) {
        while (motorStanga.isBusy() || motorDreapta.isBusy()) {
            telemetry.addData("Left Position / Target :", "%7d / %7d",
                    motorStanga.getCurrentPosition(), motorStanga.getTargetPosition());
            telemetry.addData("Right Position / Target :", "%7d / %7d",
                    motorDreapta.getCurrentPosition(), motorDreapta.getTargetPosition());
            telemetry.update();
        }
    }
}
