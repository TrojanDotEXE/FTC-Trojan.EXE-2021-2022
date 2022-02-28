package org.firstinspires.ftc.teamcode.Hardware;

import com.qualcomm.hardware.bosch.BNO055IMU;
import com.qualcomm.hardware.bosch.JustLoggingAccelerationIntegrator;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.OrientationSensor;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.robotcore.external.navigation.Orientation;

public class HardwareM extends LinearOpMode
{
    public DcMotor roataStanga    = null, roataDreapta  = null; //Motoare fata
    public DcMotor brat_S         = null, brat_D        = null, //Motoare brat
                   brat_Scripete  = null;
    public DcMotor caruselDreapta = null, caruselStanga = null;

    public CRServo leftClaw = null, rightClaw = null;   //schimbati in Servo daca folositi celelalte variante

    public BNO055IMU imu;

    public static final int MAX_SCRIPETE            =  -821 ;
    public static final int MIN_SCRIPETE            =  -8 ;

    public static final int ROTIRE90    = 1980;

    public static final int T1 = 400;
    public static final int T2 = 600;
    public static final int T3 = 900;

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

        BNO055IMU.Parameters parameters = new BNO055IMU.Parameters();
        setIMUParams(parameters);

        imu = hardwaremap.get(BNO055IMU.class, "IMU");
        imu.initialize(parameters);

        set0Behaviour(DcMotor.ZeroPowerBehavior.BRAKE, roataStanga, roataDreapta, brat_S, brat_D, brat_Scripete, caruselDreapta, caruselStanga);               //set 0 Behaivior
        setDirections(DcMotor.Direction.FORWARD,  roataDreapta, brat_S, brat_Scripete, caruselDreapta);                            //set Directions Forward
        setDirections(DcMotor.Direction.REVERSE, roataStanga, brat_D, caruselStanga);                                                             //set Directions Reverse
        resetEncoders(roataDreapta, roataStanga, brat_S, brat_Scripete);
        stopMotors();   //setPower 0
        leftClaw.setDirection(DcMotorSimple.Direction.REVERSE);
        //leftClaw.setDirection();
        //stopServos();
    }

    private void setDirections(DcMotor.Direction d,DcMotor ... motors) {
        for (DcMotor m:motors)
            m.setDirection(d);
    }

    private void set0Behaviour(DcMotor.ZeroPowerBehavior mode, DcMotor ... motors) {
        for (DcMotor m:motors)
            m.setZeroPowerBehavior(mode);
    }

    private void setIMUParams(BNO055IMU.Parameters param) {
        param.angleUnit = BNO055IMU.AngleUnit.DEGREES;
        param.accelUnit = BNO055IMU.AccelUnit.METERS_PERSEC_PERSEC;
        param.calibrationDataFile = "BNO055IMUCalibration.json";
        param.loggingEnabled = true;
        param.loggingTag = "IMU";
        param.accelerationIntegrationAlgorithm = new JustLoggingAccelerationIntegrator();
    }

    public void setEncoderMode(DcMotor.RunMode mode, DcMotor ... motors) {
        for (DcMotor m:motors)
            m.setMode(mode);
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

    public void setWheelPowers(double p1, double p2) {
        roataStanga.setPower(p1);
        roataDreapta.setPower(p2);
    }

    public void resetEncoders(DcMotor ... motors) {
        for(DcMotor motor:motors)
        {
            setEncoderMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER, motor);
            setEncoderMode(DcMotor.RunMode.RUN_USING_ENCODER, motor);
        }
    }

    public void restartServos() {
        leftClaw.setPower(1);
        rightClaw.setPower(.8);
    }

    @Override
    public void runOpMode(){}
    public HardwareM(){}    //Constructor
}
