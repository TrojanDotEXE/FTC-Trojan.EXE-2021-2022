package org.firstinspires.ftc.teamcode.test.TeleOps;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.robotcore.external.ClassFactory;
import org.firstinspires.ftc.robotcore.external.hardware.camera.WebcamName;
import org.firstinspires.ftc.robotcore.external.navigation.VuforiaLocalizer;
import org.firstinspires.ftc.robotcore.external.tfod.Recognition;
import org.firstinspires.ftc.robotcore.external.tfod.TFObjectDetector;

import java.util.List;

@Autonomous(name = "Test_Camera", group = "Test_Auto")
@Disabled
public class Test_Camera extends LinearOpMode {

    private static final String TFOD_MODEL_ASSET = "FreightFrenzy_BCDM.tflite";
    private static final String[] LABELS = {
            "Ball",
            "Cube",
            "Duck",
            "Marker"
    };
    private VuforiaLocalizer vuforia;
    private TFObjectDetector tfod;
    private static final String VUFORIA_KEY = "AbcufIb/////AAABmZs6KcRHr0m2uxP9FdDNZHiJeC/zzCekip17+7u/oYc5AH4DhJyHL++2SqmTLlvYT+UismLHFociytOA1QJoXS00gFgdc0899BBB9MWVz6Jve58FlGOVtMU9PqrGP5XJDi9qz2b5HrBu3wSzRiYm/Mwkp2NEq8G1xszfl1be1WtsTGH6RC/tkTq/mxVTiHyzIu3ogXhRzRhnes9DKJlVb6kD4Tn0xxvQTF/14ipbBa0Z4BKrjEYld4U/wVT5dW95RJtMz+dExw68UNdPRLNgk5UtLSDvtgh0i2F4hyO5eyaiTZ+9ZeUUgChId0ow2Sfqox23GPIECsMf5d9oyD3fdCaDrXV/LqBFoI50j8H5svRP";

    @Override
    public void runOpMode() throws InterruptedException {
        initVuforia();
        initTfod();

        /**
         * Activate TensorFlow Object Detection before we wait for the start command.
         * Do it here so that the Camera Stream window will have the TensorFlow annotations visible.
         **/
        if (tfod != null)
        {
            tfod.activate();

            // The TensorFlow software will scale the input images from the camera to a lower resolution.
            // This can result in lower detection accuracy at longer distances (> 55cm or 22").
            // If your target is at distance greater than 50 cm (20") you can adjust the magnification value
            // to artificially zoom in to the center of image.  For best results, the "aspectRatio" argument
            // should be set to the value of the images used to create the TensorFlow Object Detection model
            // (typically 16/9).
            // feature disabled
            // tfod.setZoom(1.77, 16.0/9.0);
        }

        telemetry.addData(">", "Press Play to start op mode");
        telemetry.update();
        waitForStart();

        if (opModeIsActive()) {
            while (opModeIsActive()) {
                if (tfod != null) {
                    List<Recognition> updatedRecognitions = tfod.getUpdatedRecognitions();
                    if (updatedRecognitions != null)
                    {
                        telemetry.addData("# Object Detected", updatedRecognitions.size());

                        int i = 0;
                        for (Recognition recognition : updatedRecognitions)
                        {
                            telemetry.addData(String.format("label (%d)", i), recognition.getLabel());
                            telemetry.addData(String.format("  left,top (%d)", i), "%.03f , %.03f",
                                    recognition.getLeft(), recognition.getTop());
                            telemetry.addData(String.format("  right,bottom (%d)", i), "%.03f , %.03f",
                                    recognition.getRight(), recognition.getBottom());
                            i++;
                        }
                        telemetry.update();
                    }
                }
            }
            //if(tfod != null)
            //    tfod.shutdown();
        }
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
