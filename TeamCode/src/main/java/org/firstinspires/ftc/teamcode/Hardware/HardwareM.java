package org.firstinspires.ftc.teamcode.Hardware;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.ElapsedTime;

public class HardwareM extends LinearOpMode
{
    public DcMotor roataStanga   = null  , roataDreapta = null; //Motoare fata
    public DcMotor brat_S        = null  , brat_D       = null, //Motoare brat
                   brat_Scripete = null;
    public DcMotor carusel       = null  , peria        = null; //Altele

    public Servo servoBrat = null;//Servouri

    public static final int HDHEX40_TICK_COUNTS     = 1120;         //TODO: fa un Enum pt tick counts, roti, etc.
    public static final int TETRIX_TICK_COUNTS      = 1440;
    public static final int REV_COREHEX_TICK_COUNTS = 288;

    public static final double circumferintaRoata    = 11.131308365;
    public static final double circumferintaScripete = 4.328829925;
    public static final double driveGearRatio        = 1.6;

    public static final int NEVEREST40_TICKS_PER_INCH  = (int)((HDHEX40_TICK_COUNTS * driveGearRatio)/circumferintaRoata);
    public static final int TETRIX_TICKS_PER_INCH      = (int)((TETRIX_TICK_COUNTS)/circumferintaRoata);
    public static final int REV_COREHEX_TICKS_PER_INCH = (int)((REV_COREHEX_TICK_COUNTS)/circumferintaRoata);
    public static final int SCRIPETE_TICKS_PER_INCH    = (int)(TETRIX_TICK_COUNTS/circumferintaScripete);

    private ElapsedTime runtime = new ElapsedTime();     //TODO: fa autonoma sa se opreasca dupa perioada de timp permisa

    public void init (HardwareMap hardwaremap){
        roataStanga  = hardwaremap.get(DcMotor.class, "motorStanga");       //Motoare
        roataDreapta = hardwaremap.get(DcMotor.class, "motorDreapta");
        brat_S       = hardwaremap.get(DcMotor.class, "motorS");
        brat_D       = hardwaremap.get(DcMotor.class, "motorD");
        brat_Scripete = hardwaremap.get(DcMotor.class, "motor3");
        peria        = hardwaremap.get(DcMotor.class, "motorPeria");
        carusel      = hardwaremap.get(DcMotor.class, "motorCarusel");

        servoBrat = hardwaremap.get(Servo.class, "servoBrat");

        set0Behaviour(DcMotor.ZeroPowerBehavior.BRAKE, roataStanga, roataDreapta, brat_S, brat_D, brat_Scripete, peria, carusel);               //set 0 Behaivior
        setDirections(DcMotor.Direction.FORWARD, roataStanga, brat_S, brat_D, brat_Scripete, peria, carusel);                            //set Directions Forward
        setDirections(DcMotor.Direction.REVERSE, roataDreapta, brat_S);                                                             //set Directions Reverse
        setEncoderMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER, roataStanga, roataDreapta, brat_S, brat_D, brat_Scripete, peria, carusel);    //set encoders
        stopMotors();   //setPower 0

        servoReset();
        setDirections(Servo.Direction.FORWARD, servoBrat);

    }

    private void servoReset() {
        servoBrat.setPosition(0);
    }

    public void stopMotors() {
        roataStanga.setPower(0);
        roataDreapta.setPower(0);
        brat_S.setPower(0);
        brat_D.setPower(0);
        brat_Scripete.setPower(0);
        peria.setPower(0);
        carusel.setPower(0);
    }

    public void stopMotors(DcMotor ... motors) {
        for (DcMotor m:motors)
            m.setPower(0);
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
        goToPosition(1, rs, motor1);
        goToPosition(.5, rd, motor2);
    }

    private void setEncoderMode(DcMotor.RunMode mode, DcMotor ... motors) {
        for (DcMotor m:motors)
            m.setMode(mode);
    }

    private void set0Behaviour(DcMotor.ZeroPowerBehavior mode, DcMotor ... motors) {
        for (DcMotor m:motors)
            m.setZeroPowerBehavior(mode);
    }

    private void setDirections(Servo.Direction d, Servo ... servos) {
        for (Servo s:servos)
            s.setDirection(d);
    }

    private void setDirections(DcMotor.Direction d,DcMotor ... motors) {
        for (DcMotor m:motors)
            m.setDirection(d);
    }

    @Override
    public void runOpMode(){}
    public HardwareM(){}    //Constructor
}
