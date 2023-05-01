package it.unibs.ui.bookingOfficer.commands;
import it.unibs.core.Restaurant;
import it.unibs.ui.Command;
import it.unibs.ui.InputManager;
import lombok.RequiredArgsConstructor;
import java.util.Calendar;
import java.util.Date;

@RequiredArgsConstructor
// Una prenotazione è pertanto un elenco di coppie (menu tematico/piatto, numero persone)
public class HandleAddBooking implements Command{

    private final Restaurant restaurant;
    private int day, month, year;

    Calendar cal = Calendar.getInstance();
    Date actualDate = cal.getTime();
    public void onSelection(){
        Booking booking = new Booking();
    }
    //chiedo una data
    private void askDate(){
        boolean valid= false;
        boolean validDate = false;

        while(!valid){
            int inputDay = InputManager.readInt("Inserisci giorno:", 1, 31);
            int inputMonth = InputManager.readInt("Inserisci mese:", 1, 12);
            int inputYear = InputManager.readInt("Inserisci giorno:", 2023, 2041);

            validDate = isValidDate(inputDay, inputMonth, inputYear);

            if(validDate){
                this.day= inputDay;
                this.month = inputMonth;
                this.year = inputYear;


            }

        if(validDate){

            // creazione di un oggetto Calendar per la data input
            Calendar dataInput = Calendar.getInstance();
            dataInput.set(Calendar.DAY_OF_MONTH, day);
            dataInput.set(Calendar.MONTH, month - 1);
            dataInput.set(Calendar.YEAR, year);

            if (dataInput.after(cal)) {

                // controllo se la data input è un giorno feriale
                if (isWeekDay(dataInput)) {
                    System.out.println("La data input " + inputDay + "/" + inputMonth + "/" + inputYear + " è valida e rappresenta un giorno feriale successivo alla data attuale.");
                    valid = true;
                    break;
                } else {
                    System.out.println("La data input " + inputDay + "/" + inputMonth + "/" + inputYear + " è valida ma non rappresenta un giorno feriale successivo alla data attuale.");
                }

            } else {
                System.out.println("La data input " + inputDay + "/" + inputMonth + "/" + inputYear + " non è successiva alla data attuale.");
            }

        } else {
            System.out.println("La data input " + inputDay + "/" + inputMonth + "/" + inputYear + " non è valida.");
            }
        }
    }


    //controllo che sia una data valida
    //e che sia un giorno feriale antecedente a quello della prenotazione

    //chiedo al cliente numero coperti per prenotazione

    //faccio scegliere piatti/menu tematico chiedendo quante persone
    //elecando fra quelli disponibili in tale data

    //controllo La
    //somma dei valori numero persone relativi a tutte le coppie di una prenotazione è maggiore
    //o uguale al valore numero coperti dell’ordinazione stessa.


    //se esiti tutti ok inserisco la prenotazione


    public static boolean isValidDate(int day, int month, int year) {
        if (month < 1 || month > 12) {
            return false;
        }
        if (day < 1 || day > 31) {
            return false;
        }
        if (month == 2 && day > 29) {
            return false;
        }
        if ((month == 4 || month == 6 || month == 9 || month == 11) && day > 30) {
            return false;
        }
        if (year < 0) {
            return false;
        }
        return true;
    }

    public static boolean isWeekDay(Calendar data) {
        int dayOfTheWeek = data.get(Calendar.DAY_OF_WEEK);
        return (dayOfTheWeek >= Calendar.MONDAY && dayOfTheWeek <= Calendar.FRIDAY);
    }
}
