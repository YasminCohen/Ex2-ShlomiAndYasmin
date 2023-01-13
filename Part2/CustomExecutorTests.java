package Part2;

import org.junit.jupiter.api.Test;
import org.junit.platform.commons.logging.Logger;
import org.junit.platform.commons.logging.LoggerFactory;

import java.util.concurrent.*;

public class CustomExecutorTests {
    private static final Logger logger = LoggerFactory.getLogger(CustomExecutorTests.class);

    @Test
    public void testCustomExecutor() throws ExecutionException, InterruptedException {
        CustomExecutor customExecutor = new CustomExecutor();
        System.out.println( customExecutor.getCurrentMax()); ///0
        var task = Task.createTask(()->{
            int sum = 0;
            for (int i = 1; i <= 10; i++) {
                sum += i;
            }
            return sum;
        }, TaskType.COMPUTATIONAL);
        var sumTask = customExecutor.submit(task);
        System.out.println( customExecutor.getCurrentMax()); ///1
        final int sum;
        try {
            sum = (int) sumTask.get(1, TimeUnit.MILLISECONDS);
        } catch (InterruptedException | ExecutionException | TimeoutException e) {
            throw new RuntimeException(e);
        }
        System.out.println( customExecutor.getCurrentMax()); ///0
        logger.info(()-> "Sum of 1 through 10 = " + sum);
        Callable<Double> callable1 = ()-> {
            return 1000 * Math.pow(1.02, 5);
        };
        Callable<String> callable2 = ()-> {
            StringBuilder sb = new StringBuilder("!!!!!!!deeccuS");
            return sb.reverse().toString();
        };
        var priceTask = customExecutor.submit(callable1, TaskType.COMPUTATIONAL);
        System.out.println( customExecutor.getCurrentMax()); ///1
        var reverseTask = customExecutor.submit(callable2, TaskType.IO);
        System.out.println( customExecutor.getCurrentMax()); ///2
        final Double totalPrice;
        final String reversed;
        try {
            totalPrice = priceTask.get();
            System.out.println( customExecutor.getCurrentMax()); ///2
            reversed = reverseTask.get();
            System.out.println( customExecutor.getCurrentMax());; ///0
        } catch (InterruptedException | ExecutionException e) {
            throw new RuntimeException(e);
        }

        Callable<Double> callable111 = () -> {
            double x = 5*3;
            return x;
        };

        Callable<String> callable222 = () -> {
            StringBuilder sb = new StringBuilder("yasminandshlomi");
            return sb.reverse().toString();
        };
         double ya;
        priceTask = customExecutor.submit(() -> {
            double y = 9/8;
            return y;
        }, TaskType.COMPUTATIONAL);
        System.out.println( customExecutor.getCurrentMax()); //1
        ya = priceTask.get();

        priceTask = customExecutor.submit(callable111, TaskType.OTHER);
        System.out.println( customExecutor.getCurrentMax()); ///3
        ya = priceTask.get();

        customExecutor.submit(callable222, TaskType.IO);
        System.out.println( customExecutor.getCurrentMax()); ///2
        ya = priceTask.get();


        logger.info(()-> "Reversed String = " + reversed);
        logger.info(()->String.valueOf("Total Price = " + totalPrice));
        logger.info(()-> "Current maximum priority = " +
                customExecutor.getCurrentMax());
        customExecutor.gracefullyTerminate();
    }

}