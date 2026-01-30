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
        System.out.println("2. Add Income");
        System.out.println("3. Do transfer");
        System.out.println("4. Close account");
        System.out.println("5. Log out");
        System.out.println("0. Exit");
    }

    private void createAccount() {
        String accountNum = system.createNewAccount();
        System.out.println("Your card has been created");
        System.out.println("Your card number:");
        System.out.println(accountNum);
        System.out.println("Your card PIN");
        System.out.printf("%04d\n", system.retrievePIN(accountNum));
        System.out.println();
    }

    private void attemptLogIn() {
        System.out.println("Enter your card number:");
        String cardNum = scan.nextLine();
        System.out.println("Enter your PIN:");
        String pinEntered = scan.nextLine();
        System.out.println();

        if (system.logIn(cardNum, pinEntered)) {
            boolean loggedIn = true;
            System.out.println("You have successfully logged in!\n");
            int userChoice;

            while (loggedIn) {
                printLoggedInMenu();
                userChoice = Integer.parseInt(scan.nextLine());
                System.out.println();

                switch (userChoice) {
                    case 1 -> System.out.printf("Balance: %d\n", system.getBalance(cardNum));
                    case 2 -> addIncome(cardNum);
                    case 3 -> doTransfer(cardNum);
                    case 5 -> {
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

    public void addIncome(String cardNum) {
        System.out.println("Enter income:");
        try {
            int income = Integer.parseInt(scan.nextLine());
            if (income <= 0) {
                System.out.println("Must be a positive number!");
                return;
            }
            system.addIncome(cardNum, income);
            System.out.println("Income was added!");
        } catch (NumberFormatException e) {
            System.out.println("Not a valid number!");
        }

    }

    public void doTransfer(String cardNum) {
        System.out.println("Transfer");
        System.out.println("Enter card number:");
        String otherCardNum = scan.nextLine();

        if (!system.checkCardNumber(otherCardNum)) {
            System.out.println("Probably you made a mistake in the card number. Please try again!");
            return;
        }

        if (cardNum.equals(otherCardNum)) {
            System.out.println("You can't transfer money to the same account!");
            return;
        }

        if (!system.cardExists(otherCardNum)) {
            System.out.println("Such a card does not exist.");
            return;
        }

        System.out.println("Enter how much money you want to transfer:");
        int amount = Integer.parseInt(scan.nextLine());

        if (amount > system.getBalance(cardNum)) {
            System.out.println("Not enough money!");
            return;
        }

        system.transfer(cardNum, otherCardNum, amount);

    }

}
