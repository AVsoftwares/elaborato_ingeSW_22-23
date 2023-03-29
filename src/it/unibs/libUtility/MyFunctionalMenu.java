package it.unibs.libUtility;

/**
 * Come il Mymenu ma contine anche i comandi associati alle voci.
 * Vengono eseguiti direttamente qui, senza necessità di usare uno switch.
 *
 */
public class MyFunctionalMenu {

    final private static String CORNICE = "---------------------------------";
    final private static String VOCE_USCITA = "0\t";
    final private static String RICHIESTA_INSERIMENTO = "Digita il numero dell'opzione desiderata -> ";

    final private String titolo;
    final private VoceEComando[] voci;

    public MyFunctionalMenu(String titolo, VoceEComando[] voci) {
        this.titolo = titolo;
        this.voci = voci;
    }

    public boolean scegliEdEseguiVoce() {
        stampaMenu();
        int scelta = InputDati.leggiIntero(RICHIESTA_INSERIMENTO, 0, voci.length);
        voci[scelta].getComando().apply();
        return scelta != 0;
    }

    public void eseguiMenu() {
        boolean continuare;
        do {
            continuare = scegliEdEseguiVoce();
        } while (continuare);
    }

    public void stampaMenu() {
        System.out.println(CORNICE);
        System.out.println(titolo);
        System.out.println(CORNICE);

        for (int i = 1; i < voci.length; i++) {
            System.out.println((i) + "\t" + voci[i].getVoce());
        }

        System.out.println();
        System.out.println(VOCE_USCITA + voci[0].getVoce());
        System.out.println();
    }

}
