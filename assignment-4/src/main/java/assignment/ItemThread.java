package assignment;

import assignment.controller.ItemIoHandler;
import assignment.models.Item;

import java.sql.Connection;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class ItemThread {

  private static final BlockingQueue<Item> itemQueue = new LinkedBlockingQueue<>();
  private static final BlockingQueue<Item> processedItemQueue = new LinkedBlockingQueue<>();
  private static final Object lock = new Object();

  /**
   * It creates producer and consumer thread, and uses multi-threading to run them consecutively.
   */
  public static BlockingQueue<Item> getProcessedItemQueue(
      ItemIoHandler itemIoHandler,
      Connection con) throws Exception {

    final Thread producerThread = createProducerThread(itemIoHandler, con);
    final Thread consumerThread = createConsumerThread(itemIoHandler);

    startAndJoinThread(producerThread, consumerThread);
    return processedItemQueue;
  }

  private static Thread createProducerThread(ItemIoHandler itemIoHandler, Connection con) {
    return new Thread(() -> {
      try {
        itemIoHandler.getItems(con, itemQueue, lock);
      } catch (Exception e) {
        System.err.println("Error occurred while reading data from database: " + e.getMessage());
      } finally {
        itemQueue.add(new Item("end", 0, 0, null));
      }
    });
  }

  private static Thread createConsumerThread(ItemIoHandler itemIoHandler) {
    return new Thread(() -> {
      try {
        while (true) {
          synchronized (lock) {
            if (itemQueue.isEmpty()) {
              lock.wait();
            }
          }
          Item item = itemQueue.take();
          if (item.getName().equalsIgnoreCase("end")) {
            break;
          }
          itemIoHandler.calculateTaxAndFinalPrice(item);
          processedItemQueue.add(item);
          System.out.println("Item consumed");
        }
      } catch (InterruptedException e) {
        Thread.currentThread().interrupt();
        System.err.println("Consumer thread interrupted: " + e.getMessage());
      } catch (Exception e) {
        System.err.println("Error occurred while consuming item: " + e.getMessage());
      }
    });
  }

  private static void startAndJoinThread(Thread producerThread, Thread consumerThread)
      throws InterruptedException {
    producerThread.start();
    consumerThread.start();

    producerThread.join();
    consumerThread.join();
  }
}
