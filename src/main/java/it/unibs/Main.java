package it.unibs;

import it.unibs.core.Restaurant;
import it.unibs.ui.Menu;
import it.unibs.ui.commands.manager.*;

public class Main {
    public static void main(String[] args) {
        Restaurant restaurant = new Restaurant("Un bel ristorante");

        Menu managerMenu = new Menu("Manager Menu", true);

        managerMenu.addEntry("Gestisci carico di lavoro per persona.", new HandleIndividualWorkloadCommand(restaurant));
        managerMenu.addEntry("Gestisci numero dei posti a sedere.", new HandleSeatsCommand(restaurant));
        managerMenu.addEntry("Gestisci insieme delle bevande.", new HandleDrinksCommand(restaurant));
        managerMenu.addEntry("Gestisci insieme di generi alimentari extra.", new HandleExtraCommand(restaurant));
        managerMenu.addEntry("Gestisci consumo pro-capite di bevande.", new HandleDrinkAmountCommand(restaurant));
        managerMenu.addEntry("Gestisci consumo pro-capite di generi alimentari extra.", new HandleExtraAmountCommand(restaurant));
        managerMenu.addEntry("Gestisci corrispondenze piatto-ricetta.", new HandleExtraCommand(restaurant));
        managerMenu.addEntry("Gestisci denominazione e periodo di validità di ciascun piatto.", new HandleExtraCommand(restaurant));

        managerMenu.run();
    }
}
