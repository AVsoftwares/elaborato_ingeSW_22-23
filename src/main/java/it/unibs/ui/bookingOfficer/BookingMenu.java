package it.unibs.ui.bookingOfficer;

import it.unibs.core.Restaurant;
import it.unibs.ui.Menu;
/*Addetto alle prenotazioni
L’applicazione deve supportare l’attività di raccolta delle prenotazioni, con rimozione
automatica delle prenotazioni scadute. Si noti che l’accettazione delle prenotazioni è
sottoposta a dei vincoli. In particolare, il numero totale di coperti prenotati per una data
(cioè la somma del valore numero coperti estesa a tutte le prenotazioni raccolte per tale
data) non deve superare il numero complessivo di posti a sedere del ristorante. Inoltre, il
carico di lavoro della totalità delle prenotazioni relative a una data (cioè la somma del
valore del carico di lavoro di tutti i menu tematici, estesa a tutte le persone che hanno
ordinato ciascun menu, e di tutti i piatti prenotati, estesa a tutte le persone che hanno
ordinato ciascun piatto) non deve eccedere il carico di lavoro sostenibile dal ristorante.*/
public class BookingMenu extends Menu{
    private final Restaurant restaurant;

    private static final String MENU_NAME = "Menu gestore delle prenotazioni";
    private static final String MSG_ADD_BOOKING = "Aggiungi una prenotazione";
    private static final String MSG_DELETE_BOOKING= "Cancella una prenotazione";
    private static final String MSG_CHECK_TODAY_AVAILABILITY = "Controlla disponibilità ODIERNA";
    private static final String MSG_CHECK_AVAILABILITY = "Vedi disponibilità per una data";
    private static final String MSG_VIEW_BOOKING= "Vedi prenotazioni per una data";
    private static final String MSG_VIEW_TODAY_BOOKING = "Vedi prenotazione per data odierna";
    public BookingMenu(Restaurant restaurant) {
        super(MENU_NAME, false);
        this.restaurant = restaurant;
        initMenuEntries();
    }



    private void initMenuEntries() {

        addEntry(MSG_ADD_BOOKING, new HandleAddBooking(restaurant));

        addEntry(MSG_DELETE_BOOKING, new HandleAddBooking(restaurant));

        addEntry(MSG_CHECK_TODAY_AVAILABILITY, new HandleCheckTodayAvailability(restaurant) );

        addEntry(MSG_CHECK_AVAILABILITY, new HandleCheckAvailability(restaurant));

        addEntry(MSG_VIEW_BOOKING, new HandleViewBooking(restaurant));

        addEntry(MSG_VIEW_TODAY_BOOKING, new HandleViewTodayBooking(restaurant) );
    }
}
