package it.unibs.ui.reservationOfficer;

import it.unibs.core.reservation.ReservationService;
import it.unibs.ui.BaseMenu;
import it.unibs.ui.reservationOfficer.commands.ReservationCommand;

/**
 * Addetto alle prenotazioni
 * L’applicazione deve supportare l’attività di raccolta delle prenotazioni, con
 * rimozione
 * automatica delle prenotazioni scadute. Si noti che l’accettazione delle
 * prenotazioni è
 * sottoposta a dei vincoli. In particolare, il numero totale di coperti
 * prenotati per una data
 * (cioè la somma del valore numero coperti estesa a tutte le prenotazioni
 * raccolte per tale
 * data) non deve superare il numero complessivo di posti a sedere del
 * ristorante. Inoltre, il
 * carico di lavoro della totalità delle prenotazioni relative a una data (cioè
 * la somma del
 * valore del carico di lavoro di tutti i menu tematici, estesa a tutte le
 * persone che hanno
 * ordinato ciascun menu, e di tutti i piatti prenotati, estesa a tutte le
 * persone che hanno
 * ordinato ciascun piatto) non deve eccedere il carico di lavoro sostenibile
 * dal ristorante.
 **/
public class ReservationOfficerView extends BaseMenu {
    private static final String ERR_INVALID_QUANTITY = """
            La quantità inserita non è valida.
            Deve essere nel formato: quantity [prefix unit]
            Le unità di misura accettate sono (l)itri e (g)rammi, se omessa si considerano le unità""";
    private static final String MENU_NAME = "Menu gestore delle prenotazioni";
    private static final String MSG_BOOKING = "Raccogli una prenotazione";
    private static final String NO_AVAILABLE_SEATS= "Non ci sono sufficienti posti disponibili per la data selezionata.";
    private static final String NOTHING_AVAILABLE = "Non sono disponibili né piatti né menu per il giorno selezionato, impossibile continuare.";
   private static final String OVERWORKLOAD = "Il ristorante non è in grado di gestire il carico di lavoro.";
    public ReservationOfficerView(ReservationService reservationService) {
        super(MENU_NAME);
        addEntry(MSG_BOOKING, new ReservationCommand(this, reservationService));
    }

    public void printInvalidQuantity() {
        System.out.println(ERR_INVALID_QUANTITY);
    }
    public void printNoAvailableSeats() { System.out.println(NO_AVAILABLE_SEATS); }
    public void printNothingAvailable() { System.out.println(NOTHING_AVAILABLE); }
    public void printOverWorkload() { System.out.println(OVERWORKLOAD); }
}
