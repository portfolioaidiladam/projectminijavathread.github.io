package programmerzamannow.thread;

import org.junit.jupiter.api.Test;

import java.util.Random;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class ThreadLocalTest {
// ThreadLocal memberi kita kemampuan untuk menyimpan data yang hanya bisa digunakan di thread tersebut
// Tiap thread akan memiliki data yang berbeda dan tidak saling terhubung antar thread
  // intinya valuenya bakal nempel didalam threadnya
  @Test
  void test() throws InterruptedException {

    final var random = new Random();
    final var userService = new UserService();
    final var executor = Executors.newFixedThreadPool(10);

    for (int i = 0; i < 50; i++) {
      final var index = i;
      executor.execute(() -> {
        try {
          userService.setUser("User-" + index);
          Thread.sleep(1000 + random.nextInt(3000));
          userService.doAction();
        } catch (InterruptedException e) {
          e.printStackTrace();
        }
      });
    }

    executor.awaitTermination(1, TimeUnit.DAYS);

  }
}
