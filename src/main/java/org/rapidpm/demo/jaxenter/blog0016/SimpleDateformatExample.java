package org.rapidpm.demo.jaxenter.blog0016;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.*;
import java.util.function.Supplier;

/**
 * Created by Sven Ruppert on 24.03.2014.
 */
public class SimpleDateformatExample {

    public Boolean initCompleteA = false;
    public Boolean initCompleteB = false;

    public final ExecutorService cachedThreadPool = Executors.newCachedThreadPool();
    public CompletableFuture<String> supplyAsync;

    public String pattern;
    public SimpleDateFormat sdf;

    public Supplier<String> task = ()-> {
//        Warten bis alle true
        while(! (initCompleteA && initCompleteB) ){
            try {
                System.out.println("initCompleteA = " + initCompleteA);
                System.out.println("initCompleteB = " + initCompleteB);
                System.out.println("pattern = " + pattern);
                System.out.println("sdf = " + sdf);
                TimeUnit.MILLISECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        return sdf.format(this.date);
    };

    public Supplier<Void> taskCreateSDF = ()-> {
        while(! initCompleteA ){
            try {
                System.out.println("createSDF is waiting" );
                TimeUnit.MILLISECONDS.sleep(4);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        sdf = new SimpleDateFormat(pattern);
        initCompleteB = true;

        return null;
    };


    public void createSDF(){
        CompletableFuture<Void> supplyAsync
                = CompletableFuture
                .supplyAsync(taskCreateSDF, cachedThreadPool);
        supplyAsync.thenAccept(System.out::println);
    }

    public void newPattern(final String pattern) {
        this.pattern = pattern;
        initCompleteA=true;
        System.out.println("newPattern = " + pattern);
    }

    private Date date;

    public String format(final Date date){
        this.date = date;
        supplyAsync = CompletableFuture.supplyAsync(task, cachedThreadPool);
        try {
            return supplyAsync.get();
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
        return "";
    }


}
