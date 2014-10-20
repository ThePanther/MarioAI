package persistence;

import context.ManagerFactory;
import persistence.database.Database;

public class CreateDB {
    public static void main(String[] args) {
        Database db = ManagerFactory.getManager(Database.class);
        db.createDatabase();
    }
}
