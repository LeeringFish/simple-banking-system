package banking;

import org.sqlite.SQLiteDataSource;

public class Database {
    private String url;
    private SQLiteDataSource dataSource;

    public Database(String pathToDatabase) {
        url = "jdbc:sqlite:" + pathToDatabase;
        dataSource = new SQLiteDataSource();
        dataSource.setUrl(url);
    }

}
