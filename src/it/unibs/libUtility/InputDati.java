package it.unibs.libUtility;

import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * Classe di utilita che si occupa di chiedere e prendere in
 * input da tastiera diversi valori inseriti dall'utente, con eventuali controlli
 * e relativi messaggi di errore.
 */
public class InputDati {
    private final static String ERRORE_FORMATO = "Attenzione: il dato inserito non e' nel formato corretto.";
    private final static String ERRORE_MINIMO = "Attenzione: e' richiesto un valore maggiore o uguale a ";
    private final static String ERRORE_STRINGA_VUOTA = "Attenzione: non hai inserito alcun carattere.";
    private final static String ERRORE_MASSIMO = "Attenzione: e' richiesto un valore minore o uguale a ";
    private final static String MSG_CARATTERI_AMMISSIBILI = "Attenzione: i caratteri ammissibili sono: ";
    private final static char RISPOSTA_SI = 'S';
    private final static char RISPOSTA_NO = 'N';

    private static final Scanner lettore = creaScanner();

    private static Scanner creaScanner() {
        return new Scanner(System.in);
    }

    public static String leggiStringa(String messaggio) {
        System.out.print(messaggio);
        return lettore.nextLine();
    }

    public static String leggiStringaNonVuota(String messaggio) {
        boolean finito = false;
        String lettura;

        do {
            lettura = leggiStringa(messaggio);
            lettura = lettura.trim();

            if (lettura.length() > 0) {
                finito = true;
            } else {
                System.out.println(ERRORE_STRINGA_VUOTA);
            }

        } while (!finito);

        return lettura;
    }

    public static char leggiChar(String messaggio) {
        boolean finito = false;
        char valoreLetto = '\0';

        do {
            System.out.print(messaggio);
            String lettura = lettore.nextLine();

            if (lettura.length() > 0) {
                valoreLetto = lettura.charAt(0);
                finito = true;
            } else {
                System.out.println(ERRORE_STRINGA_VUOTA);
            }

        } while (!finito);

        return valoreLetto;
    }

    public static char leggiUpperChar(String messaggio, String ammissibili) {
        boolean finito = false;
        char valoreLetto;

        do {
            valoreLetto = leggiChar(messaggio);
            valoreLetto = Character.toUpperCase(valoreLetto); // conversione

            if (ammissibili.indexOf(valoreLetto) != -1) {
                finito = true;
            } else {
                System.out.println(MSG_CARATTERI_AMMISSIBILI + ammissibili);
            }

        } while (!finito);

        return valoreLetto;
    }

    public static int leggiIntero(String messaggio) {
        boolean finito = false;
        int valoreLetto = 0;

        do {
            System.out.print(messaggio);

            try {
                valoreLetto = lettore.nextInt();
                finito = true;
                lettore.nextLine(); // va a capo
            } catch (InputMismatchException e) {
                System.out.println(ERRORE_FORMATO);
                lettore.nextLine(); // pulizia cursore
            }

        } while (!finito);

        return valoreLetto;
    }

    public static int leggiInteroPositivo(String messaggio) {
        return leggiInteroConMinimo(messaggio, 1);
    }

    public static int leggiInteroNonNegativo(String messaggio) {
        return leggiInteroConMinimo(messaggio, 0);
    }

    public static int leggiInteroConMinimo(String messaggio, int minimoIncluso) {
        boolean finito = false;
        int valoreLetto;

        do {
            valoreLetto = leggiIntero(messaggio);

            if (valoreLetto >= minimoIncluso) {
                finito = true;
            } else {
                System.out.println(ERRORE_MINIMO + minimoIncluso);
            }

        } while (!finito);

        return valoreLetto;
    }

    public static int leggiIntero(String messaggio, int minimoIncluso, int massimoIncluso) {
        boolean finito = false;
        int valoreLetto;

        do {
            valoreLetto = leggiIntero(messaggio);

            if (valoreLetto >= minimoIncluso && valoreLetto <= massimoIncluso) {
                finito = true;
            } else if (valoreLetto < minimoIncluso) {
                System.out.println(ERRORE_MINIMO + minimoIncluso);
            } else {
                System.out.println(ERRORE_MASSIMO + massimoIncluso);
            }

        } while (!finito);

        return valoreLetto;
    }

    public static double leggiDouble(String messaggio) {
        boolean finito = false;
        double valoreLetto = 0;

        do {
            System.out.print(messaggio);

            try {
                valoreLetto = lettore.nextDouble();
                finito = true;
                lettore.nextLine(); // va a capo
            } catch (InputMismatchException e) {
                System.out.println(ERRORE_FORMATO);
                lettore.next(); // pulizia cursore
            }

        } while (!finito);

        return valoreLetto;
    }

    public static double leggiDouble(String messaggio, double minimoIncluso, double massimoIncluso) {
        boolean finito = false;
        double valoreLetto;

        do {
            valoreLetto = leggiDouble(messaggio);

            if (valoreLetto >= minimoIncluso && valoreLetto <= massimoIncluso) {
                finito = true;
            } else if (valoreLetto < minimoIncluso) {
                System.out.println(ERRORE_MINIMO + minimoIncluso);
            } else {
                System.out.println(ERRORE_MASSIMO + massimoIncluso);
            }

        } while (!finito);

        return valoreLetto;
    }

    public static double leggiDoubleConMinimo(String messaggio, double minimoIncluso) {
        boolean finito = false;
        double valoreLetto;

        do {
            valoreLetto = leggiDouble(messaggio);

            if (valoreLetto >= minimoIncluso) {
                finito = true;
            } else {
                System.out.println(ERRORE_MINIMO + minimoIncluso);
            }

        } while (!finito);

        return valoreLetto;
    }

    public static boolean yesOrNo(String domanda) {
        String mioMessaggio = domanda + " (" + RISPOSTA_SI + "/" + RISPOSTA_NO + "): ";
        char valoreLetto = leggiUpperChar(mioMessaggio,
                String.valueOf(RISPOSTA_SI) + RISPOSTA_NO);

        return valoreLetto == RISPOSTA_SI;
    }

}
