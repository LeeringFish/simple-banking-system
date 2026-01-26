package banking;

import java.util.Random;

public class Account {
    private final long cardNumber;
    private final int pin;
    private int balance;
    private final Random random;

    public Account() {
        random = new Random();
        cardNumber = generateCardNumber();
        pin = generatePIN();
        balance = 0;
    }

    public long getCardNumber() {
        return cardNumber;
    }

    public int getPin() {
        return pin;
    }

    public int getBalance() {
        return balance;
    }

    private long generateCardNumber() {
        String BIN = "400000";
        String CHECKSUM = "5";
        long accountNumber = random.nextLong(1000000000);
        String cardNumberString = String.format("%s%09d%s", BIN, accountNumber, CHECKSUM);
        return Long.parseLong(cardNumberString);
    }

    private int generatePIN() {
        return random.nextInt(10000);
    }
}
