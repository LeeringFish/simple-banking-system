package banking;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        if (args.length == 2 && "-fileName".equals(args[0])) {
            String pathToDatabase = args[1];
            BankingSystem system = new BankingSystem(pathToDatabase);
            UserInterface ui = new UserInterface(system, new Scanner(System.in));
            ui.run();
        } else {
            System.out.println("incorrect arguments or number of arguments");
        }


    }
}