package org.firstinspires.ftc.teamcode.Autonome;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

import org.firstinspires.ftc.teamcode.Hardware.HardwareM;

@Autonomous(name = "Albastru_Jos", group = "Autonome")
public class Autonoma_Albastru_Jos extends TemplateAutonoma
{
    HardwareM fer = new HardwareM();

    @Override
    public void runOpMode() throws InterruptedException {
        fer.init(hardwareMap);
        fer.init(hardwareMap);
        telemetry.addData("Robot", "Iitializat");
        telemetry.update();
        double p = 0;
        int r = 0;

        waitForStart();
        while (opModeIsActive()) {
            //segment 1: Analizeaza pozitie ratusca/element echipa
            //1.1

            ///la seg 1 nu putem face inca ca nu avem camera

            //segment 2: pozitioneaza te la carusel
            //2.1: intoarce te drreapta 90 grade
            rotationD(p, r);
            //2.2: mergi fata pana la carusel
            drive(p, r);

            //2.1': intrarcere 90 grade stanga
            //2.2': mergi pana la carusel cu spatele

            //Segment 3 : Da o ratusca jos
            caruselDreapta(p, r);

            //Segment 4 : Dute la depozit si ia freight
            //4.1: intoarce te 180 grade
            rotationS(p, r);
            //4.2: mergi fata pana la depozit
            drive(p,r);
            //4.3: lasa bratul jos
            brat(p,r);
            //4.4: activeaza peria

            //mergi putin in fata pentru a lua freight
            drive(p,r);

            //Segment 5 *se repeta: Du Freightul in team s.h.
            //5.1: ridica bratul la nivelul de jos al s.h.
            brat (p,r);
            //5.2: ioarce te 180 grade dreapta
            rotationD(p, r);
            //5.3:mergi fata pana la D1
            drive ( p,r);
            //5.4: intoarce te stanga 90 grade
            rotationS(p, r);
            //5.5: extinde brat
            scripete(p,r);
            //pune freightul pe stratul de jos
            scripete(-p,r);

            //segment 6: loop s.h.-depozit
            //6.1: mergi spate pana la D1
            drive ( p,r);
            //6.2: intoarce te 90 grade stanga
            rotationS(p, r);
            //6.3: mergi fata pana la depozit
            drive ( p,r);
            //6.4: lasa bratul jos
            brat (-p,r);
            //6.5: activeaza peria si mergi putin in fata pentru a lua freight

            //6.6: se repeta  segment 5
            drive (p, r);

            ///seg 5 * se repeta

            //segment 7: parcheaza te
            //7.1: intoarce te 90 grade dreapta
            rotationD(p, r);
            //7.2: mergi in fata pana in warehouse
            drive (p, r);

        }
    }
}
