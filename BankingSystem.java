package banking;

public class BankingSystem {
    private final Database db;

    public BankingSystem(String pathToDB) {
        db = new Database(pathToDB);
        db.createTableIfAbsent();
    }

    public String createNewAccount() {
        Account newAccount = new Account();
        String cardNum = String.valueOf(newAccount.getCardNumber());
        db.addAccount(cardNum, newAccount.getPIN());
        return cardNum;
    }

    public int getBalance(String cardNum) {
        return db.getBalance(cardNum);
    }

    public long retrievePIN(String cardNum) {
        return Long.parseLong(db.getPIN(cardNum));
    }

    public boolean logIn(String cardNum, String pin) {
        return pin.equals(db.getPIN(cardNum));
    }

    public void addIncome(String cardNum, int amount) {
        db.addIncome(cardNum, amount);
    }

    public boolean checkCardNumber(String cardNum) {
        return Account.checkCardNumber(cardNum);
    }

    public boolean cardExists(String cardNum) {
        return db.cardExists(cardNum);
    }

    public void transfer(String payer, String payee, int amount) {
        db.decreaseBalance(payer, amount);
        db.addIncome(payee, amount);
    }

    public void closeAccount(String cardNum) {
        db.deleteAccount(cardNum);
    }
}
