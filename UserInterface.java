package banking;

import java.util.Scanner;

public class UserInterface {
    private final BankingSystem system;
    private final Scanner scan;
    private boolean running;

    public UserInterface(BankingSystem system, Scanner scan) {
        this.system = system;
        this.scan = scan;
    }

    public void run() {
        running = true;
        int userChoice;

        while (running) {
            printMainMenu();
            userChoice = Integer.parseInt(scan.nextLine());
            System.out.println();

            switch (userChoice) {
                case 1 -> createAccount();
                case 2 -> attemptLogIn();
                case 0 -> running = false;
                default -> System.out.println("Invalid selection\n");
            }
        }

        System.out.println("Bye!");
    }

    private void printMainMenu() {
        System.out.println("1. Create an account");
        System.out.println("2. Log into account");
        System.out.println("0. Exit");
    }

    private void printLoggedInMenu() {
        System.out.println("1. Balance");
        System.out.println("2. Log out");
        System.out.println("0. Exit");
    }

    private void createAccount() {
        long accountNum = system.createNewAccount();
        System.out.println("Your card has been created");
        System.out.println("Your card number:");
        System.out.println(accountNum);
        System.out.println("Your card PIN");
        System.out.printf("%04d\n", system.retrievePIN(accountNum));
        System.out.println();
    }

    private void attemptLogIn() {
        System.out.println("Enter your card number:");
        long cardNumEntered = Long.parseLong(scan.nextLine());
        System.out.println("Enter your PIN:");
        int pinEntered = Integer.parseInt(scan.nextLine());
        System.out.println();

        if (system.logIn(cardNumEntered, pinEntered)) {
            boolean loggedIn = true;
            System.out.println("You have successfully logged in!\n");
            int userChoice;

            while (loggedIn) {
                printLoggedInMenu();
                userChoice = Integer.parseInt(scan.nextLine());
                System.out.println();

                switch (userChoice) {
                    case 1 -> System.out.printf("Balance: %d\n", system.getBalance(cardNumEntered));
                    case 2 -> {
                        System.out.println("You have successfully logged out!");
                        loggedIn = false;
                    }
                    case 0 -> {
                        loggedIn = false;
                        running = false;
                    }
                    default -> System.out.println("Invalid selection");
                }

                System.out.println();
            }

        } else {
            System.out.println("Wrong card number or PIN!");
        }

        System.out.println();
    }

}
