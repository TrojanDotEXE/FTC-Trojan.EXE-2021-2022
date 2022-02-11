package org.firstinspires.ftc.teamcode.Hardware;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.ElapsedTime;

public class HardwareM extends LinearOpMode
{
    public DcMotor roataStanga    = null, roataDreapta  = null; //Motoare fata
    public DcMotor brat_S         = null, brat_D        = null, //Motoare brat
                   brat_Scripete  = null;
    public DcMotor caruselDreapta = null, caruselStanga = null;


    public CRServo leftClaw = null, rightClaw = null;

    public static final double MID_SERVO       =  0.5 ;

    public static final int HDHEX40_TICK_COUNTS     = 1120;         //TODO: fa un Enum pt tick counts, roti, etc.
    public static final int TETRIX_TICK_COUNTS      = 1440;
    public static final int REV_COREHEX_TICK_COUNTS = 288;

    public static final double circumferintaRoata    = 11.131308365;
    public static final double circumferintaScripete = 4.328829925;
    public static final double driveGearRatio        = 1.6;

    public static final int NEVEREST40_TICKS_PER_INCH  = (int)((HDHEX40_TICK_COUNTS * driveGearRatio)/circumferintaRoata); //TODO: coment cu motor
    public static final int TETRIX_TICKS_PER_INCH      = (int)((TETRIX_TICK_COUNTS)/circumferintaRoata);
    public static final int REV_COREHEX_TICKS_PER_INCH = (int)((REV_COREHEX_TICK_COUNTS)/circumferintaRoata);
    public static final int SCRIPETE_TICKS_PER_INCH    = (int)(TETRIX_TICK_COUNTS/circumferintaScripete);

    //TODO: fa autonoma sa se opreasca dupa perioada de timp permisa

    public void init (HardwareMap hardwaremap){
        roataStanga    = hardwaremap.get(DcMotor.class, "motorStanga");   //Motoare
        roataDreapta   = hardwaremap.get(DcMotor.class, "motorDreapta");
        brat_S         = hardwaremap.get(DcMotor.class, "motorS");
        brat_D         = hardwaremap.get(DcMotor.class, "motorD");
        brat_Scripete  = hardwaremap.get(DcMotor.class, "motorScripete");
        caruselDreapta = hardwaremap.get(DcMotor.class, "motorCaruselD");
        caruselStanga  = hardwaremap.get(DcMotor.class, "motorCaruselS");

        leftClaw  = hardwaremap.get(CRServo.class, "leftClaw");
        rightClaw = hardwaremap.get(CRServo.class, "rightClaw");

        set0Behaviour(DcMotor.ZeroPowerBehavior.BRAKE, roataStanga, roataDreapta, brat_S, brat_D, brat_Scripete, caruselDreapta, caruselStanga);               //set 0 Behaivior
        setDirections(DcMotor.Direction.FORWARD,  roataDreapta, brat_S, brat_D, brat_Scripete, caruselDreapta);                            //set Directions Forward
        setDirections(DcMotor.Direction.REVERSE, roataStanga, brat_S, caruselStanga);                                                             //set Directions Reverse
        setEncoderMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER, roataStanga, roataDreapta, brat_S, brat_D, brat_Scripete, caruselDreapta, caruselStanga);    //set encoders
        stopMotors();   //setPower 0
        leftClaw.setDirection(DcMotorSimple.Direction.REVERSE);
        //leftClaw.setDirection();
        stopServos();
    }

    public void stopMotors() {
        roataStanga.setPower(0);
        roataDreapta.setPower(0);
        brat_S.setPower(0);
        brat_D.setPower(0);
        brat_Scripete.setPower(0);
        caruselDreapta.setPower(0);
        caruselStanga.setPower(0);
    }

    public void stopMotors(DcMotor ... motors) {
        for (DcMotor m:motors)
            m.setPower(0);
    }

    private void stopServos() {
        leftClaw.setPower(0);
        rightClaw.setPower(0);
    }

    private void setDirections(DcMotor.Direction d,DcMotor ... motors) {
        for (DcMotor m:motors)
            m.setDirection(d);
    }

    private void set0Behaviour(DcMotor.ZeroPowerBehavior mode, DcMotor ... motors) {
        for (DcMotor m:motors)
            m.setZeroPowerBehavior(mode);
    }

    private void setEncoderMode(DcMotor.RunMode mode, DcMotor ... motors) {
        for (DcMotor m:motors)
            m.setMode(mode);
    }

    public void resetEncoders() {
        roataStanga.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        roataDreapta.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        brat_S.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        brat_D.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        brat_Scripete.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        caruselDreapta.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        caruselStanga.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
    }

    public void goToPosition(double p, int r, DcMotor ... motors) {  //TODO: Drive by encoder pushbot
        for (DcMotor m:motors){
            m.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
            m.setTargetPosition(r);
            m.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            m.setPower(p);
        }
    }

    public void goToPosition(double p, int rs, int rd, DcMotor motor1, DcMotor motor2) {
        goToPosition(p, rs, motor1);
        goToPosition(p/2, rd, motor2);
    }

    @Override
    public void runOpMode(){}
    public HardwareM(){}    //Constructor
}
