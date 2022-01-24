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

        waitForStart();
        while(opModeIsActive())
        {
            //Segment 1 : Analizeaza pozitie ratusca/element echipa     //TODO: camera recunoastere ratuste, element echipa
                //1.1
            //Segment 2 : Intoarcete si pozitioneazate la carusel
                //2.1 intoarcere 90 grade stanga
                //2.2 mergi fata pana la carusel
                //2.1' intrarcere 90 grade dreapta
                //2.2' mergi spate pana la carusel
            //Segment 3 : Da o ratusca jos
            //Segment 4 : Dute la depozit si ia freight
                //4.1 intoarcete 180 dreapta
                //4.2 mergi fata pana la depozit
                //4.3 lasa bratul jos
                //4.4 activeaza peria si mergi putin in fata pentru a lua freight
                //4.1' dute pana la depozit
            //Segment 5 : Du Freightul in team s.h.
                //5.1 ridica bratul la nivelul de jos al s.h.
                //5.2 intoarcete 180 grade stanga
                //5.3 mergi pana in drept la s.h.
                //5.4 intoarcete 90 grade dreapta
                //5.5 extinde brat si pune freightul pe stratul de jos
        }
    }

}
