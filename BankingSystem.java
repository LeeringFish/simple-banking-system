package banking;

public class BankingSystem {
    private final Database db;

    public BankingSystem(String pathToDB) {
        db = new Database(pathToDB);
        db.createTableIfAbsent();
    }

    public String createNewAccount() {
        Account newAccount = new Account();
        String accountNum = String.valueOf(newAccount.getCardNumber());
        db.addAccount(accountNum, newAccount.getPIN());
        return accountNum;
    }

    public int getBalance(String accountNum) {
        return db.getBalance(accountNum);
    }

    public long retrievePIN(String accountNum) {
        return Long.parseLong(db.getPIN(accountNum));
    }

    public boolean logIn(String accountNum, String pin) {
        return pin.equals(db.getPIN(accountNum));
    }
}
