package org.firstinspires.ftc.teamcode.Autonome;

import org.firstinspires.ftc.teamcode.Hardware.HardwareM;

public class Autonoma_Albastru_Jos extends TemplateAutonoma {
    HardwareM fer = new HardwareM();

    @Override
    public void runOpMode() throws InterruptedException {
        fer.init(hardwareMap);
        fer.init(hardwareMap);
        telemetry.addData("Robot", "Iitializat");
        telemetry.update();
        double p = 0;
        int rs = 0, rd = 0,r = 0;

        waitForStart();
        while (opModeIsActive()) {
            //segment 1: Analizeaza pozitie ratusca/element echipa
            //1.1

            ///la seg 1 nu putem face inca ca nu avem camera

            //segment 2: pozitioneaza te la carusel
            //2.1: intoarce te drreapta 90 grade
            //2.2: mergi fata pana la carusel
            //2.1': intrarcere 90 grade stanga
            //2.2': mergi pana la carusel cu spatele

          turn(p ,rs, rd);
          drive(p,r);
        //Segment 3 : Da o ratusca jos


            //Segment 4 : Dute la depozit si ia freight
            //4.1: intoarce te 180 grade
            //4.2: mergi fata pana la depozit
            //4.3: lasa bratul jos
            //4.4: activeaza peria si mergi putin in fata pentru a lua freight

            turn (p,rs,rd);
            drive(p,r);
           brat(p,r);
           peria(p,r);
           drive(p,r);

            //Segment 5 : Du Freightul in team s.h.
            //5.1: ridica bratul la nivelul de jos al s.h.
            //5.2: ioarce te 180 grade dreapta
            //5.3:mergi fata pana la D1
            //5.4: intoarce te stanga 90 grade
            //5.5: extinde brat si pune freightul pe stratul de jos

            brat (p,r);
            turn ( p,rs,rd);
            drive ( p,r);
            turn ( p,rs,rd);
            scripete(p,r);
            scripete(-p,r);
            //segment 6: loop s.h.-depozit
            //6.1: mergi spate pana la D1
            //6.2: intoarce te 90 grade stanga
            //6.3: mergi fata pana la depozit
            //6.4: lasa bratul jos
            //6.5: activeaza peria si mergi putin in fata pentru a lua freight
            //6.6: se repeta  segment 5

            drive ( p,r);
            turn ( p,rs, rd);
            drive ( p,r);
            brat (-p,r);
            peria(p,r);
            drive ( p,r);
            ///seg 5
            brat (p,r);
            turn ( p,rs,rd);
            drive ( p,r);
            turn ( p,rs,rd);
            scripete(p,r);
            scripete(-p,r);

            //segment 7: parcheaza te
            //7.1: intoarce te 90 grade dreapta
            //7.2: mergi in fata pana in warehouse
            turn ( p,rs, rd);
            drive ( p,r);
        }
    }
}
