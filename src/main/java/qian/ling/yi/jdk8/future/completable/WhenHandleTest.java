package qian.ling.yi.jdk8.future.completable;

import org.junit.Test;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

public class WhenHandleTest {



    @Test
    public void testCompletableFutureSuc() throws ExecutionException, InterruptedException {
        CompletableFuture<Integer> supplyAsyncFuture = CompletableFuture.supplyAsync(() -> {
            System.out.println("supplyAsync");
            return 1;
        });

        CompletableFuture<Integer> whenCompleteAsync = supplyAsyncFuture.whenCompleteAsync((n, e) -> {
            System.out.println("whenCompleteAsync");
            System.out.println(++n);
        });
        System.out.println(whenCompleteAsync.get());

        CompletableFuture<Object> thenApplyAsync = supplyAsyncFuture.thenApplyAsync((n) -> {
            System.out.println("thenApplyAsync");
            System.out.println(++n);
            return new Object();
        });
        System.out.println(thenApplyAsync.get());


        CompletableFuture<Object> handle = supplyAsyncFuture.handleAsync((n,e) -> {
            System.out.println("handleAsync");
            if (null != e) {
                e.getMessage();
            } else {
                System.out.println(++n);

            }
            return new Object();
        });
        System.out.println(handle.get());

        CompletableFuture<Void> thenAccept = supplyAsyncFuture.thenAccept((n) -> {
            System.out.println("thenAccept");
            System.out.println(++n);
        });
        System.out.println(thenAccept.get());


        CompletableFuture<Integer> exceptionally = supplyAsyncFuture.exceptionally((e) -> {
            System.out.println("不会执行的 exceptionally");
            if (null != e) {
                System.out.println(e.getMessage());
            }
            return 99;
        });
        System.out.println(exceptionally.get());

    }


    @Test
    public void testCompletableFutureException() throws ExecutionException, InterruptedException {
        CompletableFuture<Integer> supplyAsyncFuture = CompletableFuture.supplyAsync(() -> {
            System.out.println("supplyAsync");
            return 1/0;
        });



        CompletableFuture<Object> handle = supplyAsyncFuture.handleAsync((n,e) -> {
            System.out.println("handleAsync");
            if (null != e) {
                e.getMessage();
            } else {
                System.out.println(++n);

            }
            return new Object();
        });
        System.out.println(handle.get());

        CompletableFuture<Integer> whenCompleteAsync = supplyAsyncFuture.whenCompleteAsync((n, e) -> {
            System.out.println("whenCompleteAsync");
            if (null != e) {
                System.out.println(e.getMessage());
            }else {
                System.out.println(++n);
            }
        });
//        System.out.println(whenCompleteAsync.get());

        CompletableFuture<Integer> exceptionally = supplyAsyncFuture.exceptionally((e) -> {
            System.out.println("exceptionally");
            if (null != e) {
                System.out.println(e.getMessage());
            }
            return 1;
        });
        System.out.println(exceptionally.get());

//        CompletableFuture<Object> thenApplyAsync = supplyAsyncFuture.thenApplyAsync((n) -> {
//            System.out.println("thenApplyAsync");
//            System.out.println(++n);
//            return new Object();
//        });
//        System.out.println(thenApplyAsync.get());

    }


    @Test
    public void testThenCombine() throws ExecutionException, InterruptedException {
        CompletableFuture<Integer> supplyAsync = CompletableFuture.supplyAsync(() -> {
            System.out.println("supplyAsync");
            return 1;
        });
        CompletableFuture<Integer> supplyAsync2 = CompletableFuture.supplyAsync(() -> {
            System.out.println("supplyAsync2");
            return 2;
        });

        CompletableFuture<Integer> thenCombine = supplyAsync.thenCombine(supplyAsync2, (n1, n2) -> {
            System.out.println("thenCombine");
            return n1 + n2;
        });
        System.out.println(thenCombine.get());
    }

    @Test
    public void testThenCompose() throws ExecutionException, InterruptedException {
        CompletableFuture<Integer> supplyAsync = CompletableFuture.supplyAsync(() -> {
            System.out.println("supplyAsync");
            return 1;
        });

        CompletableFuture<Object> compose = supplyAsync.thenCompose((n1) -> {
            System.out.println(n1);
            return CompletableFuture.supplyAsync(() -> {
                System.out.println("supplyAsync2");
                return n1 + 2;
            });
        });
        System.out.println(compose.get());
    }
}
