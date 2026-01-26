package banking;

public class Main {
    public static void main(String[] args) {
        Account newAccount = new Account();
        System.out.println(newAccount.getCardNumber());
        System.out.println(newAccount.getPIN());
        System.out.printf("%04d\n", newAccount.getPIN());
    }
}