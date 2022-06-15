package org.firstinspires.ftc.teamcode.TeleOpuri;

import androidx.annotation.NonNull;

import com.qualcomm.hardware.bosch.BNO055IMU;
import com.qualcomm.hardware.bosch.JustLoggingAccelerationIntegrator;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.util.ElapsedTime;
import com.qualcomm.robotcore.util.Range;

import org.firstinspires.ftc.robotcore.external.ClassFactory;
import org.firstinspires.ftc.robotcore.external.hardware.camera.WebcamName;
import org.firstinspires.ftc.robotcore.external.navigation.VuforiaLocalizer;
import org.firstinspires.ftc.robotcore.external.tfod.TFObjectDetector;

@TeleOp(name = "TeleOp-ul")
@Disabled
public class mainOpMode extends OpMode {

    private ElapsedTime runtime = new ElapsedTime();

    public DcMotor wheelLeftFront;
    public DcMotor wheelRightFront;
    public DcMotor wheelLeftBack;
    public DcMotor wheelRightBack;
    public DcMotor brat1;
    public DcMotor brat2;
    public DcMotor carusel;

    public CRServo clesteStanga ;
    public CRServo clesteDreapta;
    public BNO055IMU imu;

    private VuforiaLocalizer vuforia;
    private TFObjectDetector tfod;
    private static final String VUFORIA_KEY = "AbcufIb/////AAABmZs6KcRHr0m2uxP9FdDNZHiJeC/zzCekip17+7u/oYc5AH4DhJyHL++2SqmTLlvYT+UismLHFociytOA1QJoXS00gFgdc0899BBB9MWVz6Jve58FlGOVtMU9PqrGP5XJDi9qz2b5HrBu3wSzRiYm/Mwkp2NEq8G1xszfl1be1WtsTGH6RC/tkTq/mxVTiHyzIu3ogXhRzRhnes9DKJlVb6kD4Tn0xxvQTF/14ipbBa0Z4BKrjEYld4U/wVT5dW95RJtMz+dExw68UNdPRLNgk5UtLSDvtgh0i2F4hyO5eyaiTZ+9ZeUUgChId0ow2Sfqox23GPIECsMf5d9oyD3fdCaDrXV/LqBFoI50j8H5svRP";
    private static final String TFOD_MODEL_ASSET = "FreightFrenzy_BCDM.tflite";
    private static final String[] LABELS = {
            "Ball",
            "Cube",
            "Duck",
            "Marker"
    };


    @Override
    public void init() {
        wheelLeftFront  = hardwareMap.get(DcMotor.class, "motorStangaFata");
        wheelRightFront = hardwareMap.get(DcMotor.class, "motorDreaptaFata");
        wheelLeftBack   = hardwareMap.get(DcMotor.class, "motorStangaSpate");
        wheelRightBack  = hardwareMap.get(DcMotor.class, "motorDreaptaSpate");
        brat1            = hardwareMap.get(DcMotor.class, "motorBrat1");
        brat2            = hardwareMap.get(DcMotor.class, "motorBrat2");
        carusel         = hardwareMap.get(DcMotor.class, "motorCarusel");
        clesteStanga    = hardwareMap.get(CRServo.class, "leftClaw");
        clesteDreapta   = hardwareMap.get(CRServo.class, "rightClaw");

        BNO055IMU.Parameters parameters = new BNO055IMU.Parameters();
        setIMUParams(parameters);

        imu = hardwareMap.get(BNO055IMU.class, "imu");
        imu.initialize(parameters);

        set0Behaviour(DcMotor.ZeroPowerBehavior.BRAKE,
                wheelLeftBack, wheelLeftFront,
                wheelRightBack, wheelRightFront,
                brat1, brat2, carusel
        );
        setDirections(DcMotor.Direction.FORWARD,
                wheelLeftBack, wheelLeftFront,
                brat2
        );
        setDirections(DcMotor.Direction.REVERSE,
                wheelRightBack, wheelRightFront,
                carusel, brat1
        );
        resetEncoders(wheelRightBack, wheelLeftBack,
                wheelRightFront, wheelLeftFront
        );
        clesteStanga.setDirection(DcMotorSimple.Direction.REVERSE);
//        initVuforia();
//        initTfod();
    }

    @Override
    public void loop() {
        double fata_spate     = gamepad1.left_stick_y;
        double stanga_dreapta = gamepad1.right_stick_x;
        double left, right;

        left  = Range.clip(fata_spate - stanga_dreapta,-1,1);
        right = Range.clip(fata_spate + stanga_dreapta,-1 ,1);

        wheelLeftBack.setPower(left);
        wheelLeftFront.setPower(left);
        wheelRightBack.setPower(right);
        wheelRightFront.setPower(right);

        while(gamepad1.dpad_left) {
            wheelLeftBack.setPower(-.7);
            wheelLeftFront.setPower(-.7);
            wheelRightBack.setPower(.7);
            wheelRightFront.setPower(.7);
        }
        while(gamepad1.dpad_right) {
            wheelLeftBack.setPower(.7);
            wheelLeftFront.setPower(.7);
            wheelRightBack.setPower(-.7);
            wheelRightBack.setPower(-.7);
        }

        //Carusel
        while(gamepad1.right_bumper)
            carusel.setPower(-.85);
        while (gamepad1.left_bumper)
            carusel.setPower(.85);
        carusel.setPower(0);

///Gamepad 2

        //Brat
        brat1.setPower(Range.clip(gamepad2.left_stick_y, -.3, .3));
        brat2.setPower(Range.clip(gamepad2.left_stick_y, -.3, .3));

        //Cleste
        if(gamepad2.right_bumper) {
            clesteStanga.setPower(-1);
            clesteDreapta.setPower(-1 );
        }
        else {
            clesteStanga.setPower(1);
            clesteDreapta.setPower(.8);
        }

        telemetry.addData("Run Time: ", "%7d", (int)runtime.seconds());
    }


    private void setDirections(DcMotor.Direction d, @NonNull DcMotor ... motors) {
        for (DcMotor m:motors)
            m.setDirection(d);
    }

    private void set0Behaviour(DcMotor.ZeroPowerBehavior mode, @NonNull DcMotor ... motors) {
        for (DcMotor m:motors)
            m.setZeroPowerBehavior(mode);
    }

    private void setIMUParams(@NonNull BNO055IMU.Parameters param) {
        param.angleUnit = BNO055IMU.AngleUnit.DEGREES;
        param.accelUnit = BNO055IMU.AccelUnit.METERS_PERSEC_PERSEC;
        param.calibrationDataFile = "BNO055IMUCalibration.json";
        param.loggingEnabled = true;
        param.loggingTag = "IMU";
        param.accelerationIntegrationAlgorithm = new JustLoggingAccelerationIntegrator();
    }

    public void setEncoderMode(DcMotor.RunMode mode, @NonNull DcMotor ... motors) {
        for (DcMotor m:motors)
            m.setMode(mode);
    }

    public void stopMotors() {
        wheelLeftBack.setPower(0);
        wheelRightBack.setPower(0);
        brat1.setPower(0);
        brat2.setPower(0);
        carusel.setPower(0);
    }

    public void stopMotors(@NonNull DcMotor ... motors) {
        for (DcMotor m:motors)
            m.setPower(0);
    }

    public void setWheelPowers(double p1, double p2) {
        wheelLeftBack.setPower(p1);
        wheelRightBack.setPower(p2);
    }

    public void resetEncoders(@NonNull DcMotor ... motors) {
        for(DcMotor motor:motors)
        {
            setEncoderMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER, motor);
            setEncoderMode(DcMotor.RunMode.RUN_USING_ENCODER, motor);
        }
    }

    public void restartServos() {
        clesteStanga.setPower(1);
        clesteDreapta.setPower(.8);
    }

    private void initVuforia() {

        VuforiaLocalizer.Parameters parameters = new VuforiaLocalizer.Parameters();

        parameters.vuforiaLicenseKey = VUFORIA_KEY;
        parameters.cameraName = hardwareMap.get(WebcamName.class, "Webcam");

        vuforia = ClassFactory.getInstance().createVuforia(parameters);
    }

    private void initTfod() {

        int tfodMonitorViewId = hardwareMap.appContext.getResources().getIdentifier(
                "tfodMonitorViewId", "id", hardwareMap.appContext.getPackageName());

        TFObjectDetector.Parameters tfodParameters = new TFObjectDetector.Parameters(tfodMonitorViewId);
        tfodParameters.minResultConfidence = 0.8f;
        tfodParameters.isModelTensorFlow2 = true;
        tfodParameters.inputSize = 320;
        tfod = ClassFactory.getInstance().createTFObjectDetector(tfodParameters, vuforia);
        tfod.loadModelFromAsset(TFOD_MODEL_ASSET, LABELS);
    }
}