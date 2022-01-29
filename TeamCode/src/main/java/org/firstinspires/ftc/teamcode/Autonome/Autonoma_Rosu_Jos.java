package org.firstinspires.ftc.teamcode.Autonome;

import org.firstinspires.ftc.teamcode.Hardware.HardwareM;
public class Autonoma_Rosu_Jos extends TemplateAutonoma
{
    HardwareM fer = new HardwareM();

    @Override
    public void runOpMode() throws InterruptedException {
        fer.init(hardwareMap);
        telemetry.addData("Robot", "Iitializat");
        telemetry.update();
        int p=0, r=0;

        waitForStart();
        while(opModeIsActive())
        {
            //Segment 1 : Analizeaza pozitie ratusca/element echipa     //TODO: camera recunoastere ratuste, element echipa
            //1.1
            //Segment 2 : Intoarcete si pozitioneazate la carusel
            //2.1 intoarcere 90 grade stanga
            rotationS(p,r);
            //2.2 mergi fata pana la carusel
            drive(p,r);

            //2.1' intrarcere 90 grade dreapta
            //rotationD(p,r);
            //2.2' mergi spate pana la carusel
            //drive(-p,-r);

            //Segment 3 : Da o ratusca jos
            carusel(p,r);

            //Segment 4 : Dute la depozit si ia freight
            //4.1 intoarcete 180 dreapta
            rotationD(p,r);
            //4.2 mergi fata pana la depozit
            drive(p,r);
            //4.3 lasa bratul jos
            brat(p,r);
            //4.4 activeaza peria
            peria(p,r);
            //4.5 mergi putin in fata pentru a lua freight
            drive(p,r);

            //4.1' dute pana la depozit
            //drive(p,r);

            //Segment 5 : Du Freightul in team s.h.
            //5.1 ridica bratul la nivelul de jos al s.h.
            brat(-p,-r);
            //5.2 intoarcete 180 grade stanga
            rotationS(p,r);
            //5.3 mergi pana in drept la s.h.
            drive(p,r);
            //5.4 intoarcete 90 grade dreapta
            rotationD(p,r);
            //5.5: extinde brat
            scripete(p,r);
            //5.6 pune freightul pe stratul de jos
            peria(-p,-r);
            //5.7 retrage brat
            scripete(-p,-r);

        }
    }

}
