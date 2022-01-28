package org.firstinspires.ftc.teamcode.Autonome;

import org.firstinspires.ftc.teamcode.Hardware.HardwareM;

public class Autonoma_Albastru_Sus extends TemplateAutonoma
{
    HardwareM fer = new HardwareM();

    @Override
    public void runOpMode() throws InterruptedException {
        fer.init(hardwareMap);
        //segment 1:Dute la depozit si ia freight
        //1.1: intoarce te 90 de grade la stanga
        //1.2: mergi fata pana la depozit cu fata
        //1.3: lasa bratul jos
        //1.4: activeaza peria si mergi putin in fata pentru a lua freight
        //segment 2: Du Freightul in team s.h.
        //2.1 ridica bratul la nivelul de jos al s.h.
        //2.2: intoarce te 90 grade dreapta
        //2.3: mergi in fata pana in warehouse
        //2.4: intoarce te 90 de grade stanga
        //2.5: mergi in fata pana la A2
        //2.6: intoarce te 90 grade dreapta
        //2.7: mergi in fata pana la A3
        //2.8: te intorci pana la s.s.h
        //2.9: extinde brat si pune freightul pe stratul de jos
        //segment 3: loop depozit- s.h
        //3.1:  intoarce te 120 grade dreapta
        //3.2: mergi in fata pana la depozit
        //3.3: lasa bratul jos
        //3.4: activeaza peria si mergi putin in fata pentru a lua freight
        //3.5: merg cu spatele pana la A3
        //3.6: ma intorc stanga 120 grade
        //3.7: extinde brat si pune freightul pe stratul de jos
        //segment 4: parcheaza te
        //4.1: intoarce te 120 grade dreapta
        //4.2: mergi in fata pana la A2
        //4.3: intoarce te 90 grade stanga
        //4.4: mergi in fata pana in warehouse
    }
}
