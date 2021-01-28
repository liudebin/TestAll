package qian.ling.yi.jdk8.future.completable;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

public class AsyncTest {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        CompletableFuture<Void> runAsync = CompletableFuture.runAsync(() -> System.out.println("runAsync"));

        CompletableFuture<Integer> supplyAsyncFuture = CompletableFuture.supplyAsync(() -> {
            System.out.println("supplyAsync");
            return 1;
        });


        Void aVoid = runAsync.get();
        System.out.println(aVoid);
        Integer supplyAsyncRes = supplyAsyncFuture.get();
        System.out.println(supplyAsyncRes);
    }
}
