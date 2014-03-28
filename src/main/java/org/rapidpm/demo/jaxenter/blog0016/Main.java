package org.rapidpm.demo.jaxenter.blog0016;

import java.util.Date;
import java.util.concurrent.ExecutionException;

/**
 * Created by Sven Ruppert on 24.03.2014.
 */
public class Main {


    public static void main(String[] args) throws InterruptedException, ExecutionException {

        final SimpleDateformatExample versionAB = new SimpleDateformatExample();

        versionAB.createSDF();
//        TimeUnit.SECONDS.sleep(4);
        versionAB.newPattern("yyyy.MM.dd");
//        TimeUnit.SECONDS.sleep(4);

        final String s = versionAB.format(new Date());
        System.out.println("s = " + s);

//        final SimpleDateformatExample versionAB = new SimpleDateformatExample();
//        versionAB.newPattern("yyyy.MM.dd");
//        versionAB.createSDF();
//        final String s = versionAB.format(new Date());
//        System.out.println("s = " + s);
    }

}
