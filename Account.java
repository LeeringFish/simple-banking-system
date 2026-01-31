package banking;

import java.util.Random;

public class Account {
    private final long cardNumber;
    private final int pin;
    private final Random random;

    public Account() {
        random = new Random();
        cardNumber = generateCardNumber();
        pin = generatePIN();
    }

    public long getCardNumber() {
        return cardNumber;
    }

    public String getPIN() {
        return String.format("%04d", pin);
    }

    public static boolean checkCardNumber(String cardNum) {
        if (cardNum == null || cardNum.isEmpty()) {
            return false;
        }

        if (cardNum.matches("\\d*")) {
            long num = Long.parseLong(cardNum);
            long[] digits = longToArray(num);

            long sum = 0;

            for (int i = 0; i < digits.length - 1; i++) {
                if (i % 2 == 1) {
                    sum += digits[i];
                } else if (digits[i] > 4) {
                    sum += 2 * digits[i] - 9;
                } else {
                    sum += 2 * digits[i];
                }
            }

            return digits[digits.length - 1] == ((10 - (sum % 10)) % 10);
        }

        return false;
    }

    private long generateCardNumber() {
        long accountNumber = generateAccountIdentifier();
        long cardNumWithoutChecksum = 400000000000000L + accountNumber;
        long checksum = findChecksum(cardNumWithoutChecksum);
        return cardNumWithoutChecksum * 10 + checksum;
    }

    private long generateAccountIdentifier() {
        return random.nextLong(1000000000);
    }

    private long findChecksum(long cardNum) {
        long[] digits = longToArray(cardNum);
        long sum = 0;

        for (int i = 0; i < digits.length; i++) {
            if (i % 2 == 1) {
                sum += digits[i];
            } else if (digits[i] > 4) {
                sum += 2 * digits[i] - 9;
            } else {
                sum += 2 * digits[i];
            }
        }

        return ((10 - (sum % 10)) % 10);
    }

    private int generatePIN() {
        return random.nextInt(10000);
    }

    private static long[] longToArray(long num) {
        long temp = num;
        int length = Long.toString(num).length();
        long[] numArray = new long[length];

        for (int i = length - 1; i >= 0; i--) {
            numArray[i] = temp % 10;
            temp /= 10;
        }

        return numArray;
    }

}
