package it.unibs.ui.reservationOfficer;

import it.unibs.controller.reservationOfficer.ReservationCommand;
import it.unibs.core.Consumable;
import it.unibs.core.Dish;
import it.unibs.core.ThematicMenu;
import it.unibs.core.reservation.ReservationService;
import it.unibs.ui.BaseMenu;

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
    private static final String NO_AVAILABLE_SEATS = "Non ci sono sufficienti posti disponibili per la data selezionata.";
    private static final String NOTHING_AVAILABLE = "Non sono disponibili né piatti né menu per il giorno selezionato, impossibile continuare.";
    private static final String OVER_WORKLOAD = "Il ristorante non è in grado di gestire il carico di lavoro.";
    private static final String MSG_NO_BOOKING = "Il ristorante lavora nei feriali e la prenotazione deve avvenire con un giorno di anticipo";

    public ReservationOfficerView(ReservationService reservationService) {
        super(MENU_NAME);
        addEntry(MSG_BOOKING, new ReservationCommand(this, reservationService));
    }

    public void printInvalidQuantity() {
        System.out.println(ERR_INVALID_QUANTITY);
    }

    public void printNoAvailableSeats() {
        System.out.println(NO_AVAILABLE_SEATS);
    }

    public void printNothingAvailable() {
        System.out.println(NOTHING_AVAILABLE);
    }

    public void printOverWorkload() {
        System.out.println(OVER_WORKLOAD);
    }

    public void printNoPossibleBooking() {
        System.out.println(MSG_NO_BOOKING);
    }

    public <T extends Consumable> void printConsumables(int i, T consumable) {
        if (consumable instanceof Dish d) {
            System.out.println("\t- " + i + " " + d.getName());
        }

        if (consumable instanceof ThematicMenu t) {
            System.out.println("\t- " + i + " " + t.getName());
        }
    }
}
