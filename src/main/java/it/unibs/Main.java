package it.unibs;

import it.unibs.core.Restaurant;
import it.unibs.core.businessLogic.Users.Manager;
import it.unibs.ui.Menu;
import it.unibs.ui.commands.login.ManagerLoginCommand;
import it.unibs.ui.commands.manager.CreateRecipeCommand;
import it.unibs.ui.commands.manager.HandleDrinkAmountCommand;
import it.unibs.ui.commands.manager.HandleDrinksCommand;
import it.unibs.ui.commands.manager.HandleExtraAmountCommand;
import it.unibs.ui.commands.manager.HandleExtraCommand;
import it.unibs.ui.commands.manager.HandleIndividualWorkloadCommand;
import it.unibs.ui.commands.manager.HandleSeatsCommand;

public class Main {
    public static void main(String[] args) {
        Restaurant restaurant = new Restaurant("Un bel ristorante");
        Manager manager = new Manager("Mario", "123");
        Menu homeMenu = new Menu("Login menu", true);
        Menu managerMenu = new Menu("Manager Menu", true);

        homeMenu.addEntry("Manager login", new ManagerLoginCommand());

        // Delegare il menù del manager a ManagerLogin?

        managerMenu.addEntry("Gestisci carico di lavoro per persona.", new HandleIndividualWorkloadCommand(restaurant));
        managerMenu.addEntry("Gestisci numero dei posti a sedere.", new HandleSeatsCommand(restaurant));
        managerMenu.addEntry("Gestisci insieme delle bevande.", new HandleDrinksCommand(restaurant));
        managerMenu.addEntry("Gestisci insieme di generi alimentari extra.", new HandleExtraCommand(restaurant));
        managerMenu.addEntry("Gestisci consumo pro-capite di bevande.", new HandleDrinkAmountCommand(restaurant));
        managerMenu.addEntry("Gestisci consumo pro-capite di generi alimentari extra.", new HandleExtraAmountCommand(restaurant));
        managerMenu.addEntry("Gestisci corrispondenze piatto-ricetta.", new HandleExtraCommand(restaurant));
        managerMenu.addEntry("Gestisci denominazione e periodo di validità di ciascun piatto.", new HandleExtraCommand(restaurant));
        managerMenu.addEntry("Crea una ricetta.", new CreateRecipeCommand(restaurant));
        managerMenu.addEntry("Visualizza le ricette disponibili.", null);
        managerMenu.addEntry("Crea un menu tematico.", null);
        managerMenu.addEntry("Visualizza i menu tematici disponibili.", null);

        managerMenu.run();
    }
}
