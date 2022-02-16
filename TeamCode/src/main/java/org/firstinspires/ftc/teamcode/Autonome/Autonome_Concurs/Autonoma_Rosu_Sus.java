package org.firstinspires.ftc.teamcode.Autonome.Autonome_Concurs;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;

import org.firstinspires.ftc.teamcode.Autonome.MetodeAutonoma;
import org.firstinspires.ftc.teamcode.Hardware.HardwareM;

@Autonomous (name="Rosu_Sus", group="Autonome")
@Disabled
public class Autonoma_Rosu_Sus extends MetodeAutonoma
{
    HardwareM fer = new HardwareM();

    @Override
    public void runOpMode() throws InterruptedException {
        fer.init(hardwareMap);
        telemetry.addData("Robot", "Iitializat");
        telemetry.update();
        double p = 0;
        int R=0, r=0;

        waitForStart();
        while(opModeIsActive())
        {
            //segment 1:Dute la depozit si ia freight
            //1.1: intoarce te 90 de grade la stanga
//            rotationS(p,r);
//            //1.2: mergi in fata pana la depozit
//            drive(p,r);
//            //1.3: lasa bratul jos
//            brat(p,r);
//            //1.4: activeaza peria
//
//            //1.5: mergi putin in fata pentru a lua freight
//            drive(p,r);
//
//            //segment 2: Du Freightul in team s.h.
//            //2.1 ridica bratul la nivelul de jos al s.h.
//            brat(-p,-r);
//            //2.2: intoarce te 90 grade stanga
//            rotationS(p,r);
//            //2.3: mergi in fata pana in warehouse
//            drive(p,r);
//            //2.4: intoarce te 90 de grade dreapta
//            rotationS(p,r);
//            //2.5: mergi in fata pana la A5
//            drive(p,r);
//            //2.6: intoarce te 90 grade stanga
//            rotationS(p,r);
//            //2.7: mergi in fata pana la A4
//            drive(p,r);
//            //2.8: te intorci 60 grade stanga
//            rotationS(p,r);
//            //2.9: extinde brat
//            scripete(p,r);
//            //2.10 pune freightul pe stratul de jos
//
//            //2.11 tragere brat
//            scripete(-p,-r);
//
//            //segment 3: loop depozit- s.h
//            //3.1:  intoarce te 120 grade stanga
//            rotationS(p,r);
//            //3.2: mergi in fata pana la depozit
//            drive(p,r);
//            //3.3: lasa bratul jos
//            brat(p,r);
//            //3.4: activeaza peria
//
//            //3.5 mergi putin in fata pentru a lua freight
//            drive(p,r);
//            //3.6: merg cu spatele pana la A4
//            drive(-p,-r);
//            //3.7: ma intorc dreapta 120 grade
//            rotationD(p,r);
//            //3.8: extinde brat
//            scripete(p,r);
//            //3.9 pune freightul pe stratul de jos
//
//            //3.10 retrage brat
//            scripete(-p,-r);
//
//            //segment 4: parcheaza te
//            //4.1: intoarce te 120 grade stanga
//            rotationS(p,r);
//            //4.2: mergi in fata pana la A5
//            drive(p,r);
//            //4.3: intoarce te 90 grade dreapta
//            rotationD(p,r);
            //4.4: mergi in fata pana in warehouse
            goTo(p,r);
        }
    }
}
