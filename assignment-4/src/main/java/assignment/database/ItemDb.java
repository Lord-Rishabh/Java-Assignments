package assignment.database;

import static assignment.config.Constants.ARGS_LENGTH;

import assignment.controller.ItemIoHandler;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.Statement;

public class ItemDb {

  /**
   * Adds items to the database from user input or provided arguments.
   * @param con the database connection
   * @param itemIoHandler the item I/O handler
   * @param args the arguments passed to the method
   * @throws Exception if an error occurs during database operations
   */
  public static void getItemFromUserAndAddToDb(
      Connection con,
      ItemIoHandler itemIoHandler,
      String[] args) throws Exception {
    String insertSql = "INSERT INTO items (name, price, quantity, type) VALUES (?, ?, ?, ?)";
    PreparedStatement pst = con.prepareStatement(insertSql);

    if (args.length == ARGS_LENGTH) {
      itemIoHandler.addItemToDb(args, con, pst);
      pst.executeUpdate();
    }
    while (itemIoHandler.userWantsToAddItem()) {
      itemIoHandler.getInputFromUserAndAddItem(con, pst);
      pst.executeUpdate();
    }
    pst.close();
  }

  /**
   * Creates the items table in the database if it does not already exist.
   * @param con the database connection
   * @throws Exception if an error occurs during the table creation
   */
  public static void createTable(Connection con) throws Exception {
    Statement st = con.createStatement();

    String createTableSql = "CREATE TABLE IF NOT EXISTS items ("
        + "id INT AUTO_INCREMENT PRIMARY KEY, "
        + "name VARCHAR(255) NOT NULL, "
        + "price DOUBLE NOT NULL, "
        + "quantity INT NOT NULL, "
        + "type VARCHAR(50) NOT NULL)";
    st.executeUpdate(createTableSql);
    System.out.println("Table created or already exists.");
    st.close();
  }
}
