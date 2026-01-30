package banking;

import org.sqlite.SQLiteDataSource;

import java.sql.*;

public class Database {
    private final String url;

    public Database(String pathToDatabase) {
        url = "jdbc:sqlite:" + pathToDatabase;
    }

    public void createTableIfAbsent() {
        SQLiteDataSource dataSource = new SQLiteDataSource();
        dataSource.setUrl(url);

        try (Connection con = dataSource.getConnection()) {
            try (Statement statement = con.createStatement()) {

                statement.executeUpdate("CREATE TABLE IF NOT EXISTS card(" +
                        "id INTEGER," +
                        "number TEXT," +
                        "pin TEXT," +
                        "balance INTEGER DEFAULT 0)");

            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public void addAccount(String cardNum, String pin) {
        SQLiteDataSource dataSource = new SQLiteDataSource();
        dataSource.setUrl(url);

        try (Connection con = dataSource.getConnection()) {
            try (Statement statement = con.createStatement()) {

                statement.executeUpdate("INSERT INTO card (number, pin) VALUES ('" +
                        cardNum + "', '" + pin + "')");


            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public int getBalance(String cardNum) {
        SQLiteDataSource dataSource = new SQLiteDataSource();
        dataSource.setUrl(url);
        int balance = 0;

        try (Connection con = dataSource.getConnection()) {
            try (Statement statement = con.createStatement()) {

                try (ResultSet result = statement.executeQuery("SELECT * FROM card WHERE number='" + cardNum + "'")) {
                    balance = result.getInt("balance");
                    return balance;
                }



            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return balance;
    }

    public String getPIN(String cardNum) {
        SQLiteDataSource dataSource = new SQLiteDataSource();
        dataSource.setUrl(url);
        String pin = "";

        try (Connection con = dataSource.getConnection()) {
            try (Statement statement = con.createStatement()) {

                try (ResultSet result = statement.executeQuery("SELECT * FROM card WHERE number='" + cardNum + "'")) {
                    pin = result.getString("pin");
                    return pin;
                }



            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return pin;
    }

    public void addIncome(String cardNum, int amount) {
        String addToBalance = "UPDATE card SET balance = balance + ? WHERE number = ?";
        SQLiteDataSource dataSource = new SQLiteDataSource();
        dataSource.setUrl(url);

        try (Connection con = dataSource.getConnection()) {
            try (PreparedStatement preparedStatement = con.prepareStatement(addToBalance)) {
                preparedStatement.setInt(1, amount);
                preparedStatement.setString(2, cardNum);

                preparedStatement.executeUpdate();
            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
}
