package andreademasi.catalogo;

import java.util.Random;

public enum Periodicita {
    SETTIMANALE,
    MENSILE,
    SEMESTRALE;
    private static final Random rndm = new Random();

    public static Periodicita randomPeriodicita() {

        Periodicita[] periodicita = values();
        return periodicita[rndm.nextInt(periodicita.length)];

    }
}
