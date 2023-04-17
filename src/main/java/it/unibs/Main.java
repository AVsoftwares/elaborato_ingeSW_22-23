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

        managerMenu.create(restaurant);
        // Delegare il menù del manager a ManagerLogin?



        managerMenu.run();
    }
}
