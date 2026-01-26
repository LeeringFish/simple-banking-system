package banking;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        BankingSystem system = new BankingSystem();
        UserInterface ui = new UserInterface(system, new Scanner(System.in));
        ui.run();
    }
}