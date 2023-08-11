package it.unibs.ui.reservationOfficer;

import it.unibs.core.ReservationService;
import it.unibs.core.Restaurant;
import it.unibs.ui.Menu;
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
public class ReservationOfficerMenu extends Menu {

    private static final String MENU_NAME = "Menu gestore delle prenotazioni";
    private static final String MSG_BOOKING = "Raccogli una prenotazione";

    public ReservationOfficerMenu(Restaurant restaurant, ReservationService reservationService) {
        super(MENU_NAME);
        addEntry(MSG_BOOKING, new ReservationCommand(reservationService));
    }
}
