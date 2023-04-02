package it.unibs.libUtility;

/**
 * Questa classe rappresenta un menu testuale generico a piu' voci
 * Si suppone che la voce per uscire, se ammessa, sia sempre associata alla scelta 0
 * e sia presentata in fondo al menu.
 */
public class MyMenu {
    final private static String CORNICE = "---------------------------------";
    final private static String VOCE_USCITA = "0\tEsci";
    final private static String RICHIESTA_INSERIMENTO = "Digita il numero dell'opzione desiderata -> ";
    private static final boolean SI_USCITA = true;

    private final String titolo;
    private final String[] voci;
    private final boolean opzioneUscita;

    /**
     * Costruttore per menu che hanno l'opzione uscita.
     *
     * @param titolo
     * @param voci
     */
    public MyMenu(String titolo, String[] voci) {
        this(titolo, voci, SI_USCITA);
    }

    /**
     * Costruttore per menu che permette di deselezionare l'opzione uscita.
     *
     * @param titolo
     * @param voci
     * @param opzioneUscita se true è presente l'uscita, altrimenti no.
     */
    public MyMenu(String titolo, String[] voci, boolean opzioneUscita) {
        this.titolo = titolo;
        this.voci = voci;
        this.opzioneUscita = opzioneUscita;
    }

    public int scegli() {
        stampaMenu();

        if (opzioneUscita) {
            // con uscita
            return InputDati.leggiIntero(RICHIESTA_INSERIMENTO, 0, voci.length);
        } else {
            // senza uscita
            return InputDati.leggiIntero(RICHIESTA_INSERIMENTO, 1, voci.length);
        }

    }

    public void stampaMenu() {
        System.out.println(CORNICE);
        System.out.println(titolo);
        System.out.println(CORNICE);

        for (int i = 1; i <= voci.length; i++) {
            System.out.println(i + "\t" + voci[i-1]);
        }

        if (opzioneUscita) {
            System.out.println();
            System.out.println(VOCE_USCITA);
            System.out.println();
        }

    }

}

