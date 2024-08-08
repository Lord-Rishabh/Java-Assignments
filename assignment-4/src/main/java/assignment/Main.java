package assignment;

import assignment.config.Secrets;
import assignment.controller.ItemIoHandler;
import assignment.database.ItemDb;
import assignment.models.Item;

import java.sql.Connection;
import java.sql.DriverManager;
import java.util.concurrent.BlockingQueue;

public class Main {

  /**
   * The main method of the application.
   * @param args the command-line arguments
   */
  public static void main(String[] args) {

    String url = Secrets.URL;
    String user = Secrets.USER;
    String password = Secrets.PASSWORD;
    ItemIoHandler itemIoHandler = new ItemIoHandler();

    try {
      Connection con = DriverManager.getConnection(url, user, password);

      ItemDb.createTable(con);
      ItemDb.getItemFromUserAndAddToDb(con, itemIoHandler, args);

      BlockingQueue<Item> items = ItemThread.getProcessedItemQueue(itemIoHandler, con);
      itemIoHandler.printAllItems(items);

      con.close();
    } catch (Exception e) {
      System.err.println(e.getMessage());
    }
  }
}
