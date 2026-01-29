package banking;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        if (args.length == 3) {
            String pathToDatabase = args[2];
            BankingSystem system = new BankingSystem();
            UserInterface ui = new UserInterface(system, new Scanner(System.in));
            ui.run();
        } else {
            System.out.println("incorrect number of arguments");
        }


    }
}