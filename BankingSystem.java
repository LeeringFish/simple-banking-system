package banking;

import java.util.HashMap;
import java.util.Map;

public class BankingSystem {
    private final Map<Long, Account> accounts;

    public BankingSystem() {
        accounts = new HashMap<>();
    }

    public long createNewAccount() {
        Account newAccount = new Account();
        long accountNum = newAccount.getCardNumber();
        accounts.put(accountNum, newAccount);
        return accountNum;
    }

    public int getBalance(long accountNum) {
        return accounts.get(accountNum).getBalance();
    }

    public int retrievePIN(long accountNum) {
        return accounts.get(accountNum).getPIN();
    }

    public boolean logIn(long accountNum, int pin) {
        return accounts.containsKey(accountNum) &&  accounts.get(accountNum).getPIN() == pin;
    }
}
