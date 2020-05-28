
package uc.seng301.asg3.order;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import uc.seng301.asg3.egg.ChocolateEgg;
import uc.seng301.asg3.egg.ChocolateEggFactory;
import uc.seng301.asg3.egg.ChocolateType;
import uc.seng301.asg3.packaging.PackagingType;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ThreadLocalRandom;

public class RegularBoxStrategy implements PrepareStrategy {

    protected PreparingOrder preparingOrder;
    protected Integer crunchiesUsed;
    protected Integer factoryIndex;
    protected Integer numberOfFillings;

    protected final Logger logger = LogManager.getLogger(PreparingOrder.class.getName());

    public RegularBoxStrategy(PreparingOrder po) {
        preparingOrder = po;
        factoryIndex = ThreadLocalRandom.current().nextInt(numberOfFillings);
        crunchiesUsed = 0;
    }

    /**
     * Produce an order by creating all expected eggs using the attributes defined in
     * {@link Order#createOrder} and corresponding factories.<br>
     * Eggs are asynchronously created in separate threads (except the containing hollow egg if
     * the type of packaging is an hollow egg) and placed into the packaging or the containing
     * hollow egg when produced.
     *
     * @see PackagingType
     */
    @Override
    public void prepare() {
        for (int i = 0; i < preparingOrder.quantity; i++) {
            // CompletableFutures are sorts of threads that can be easily created on the fly to process
            // long running tasks, as the produceEgg method. We also pass the executor where we created
            // a pool of threads with a given size of 3
            CompletableFuture.supplyAsync(() ->
                    produceEgg(getNextFactory(preparingOrder.stuffed), preparingOrder.chocolateType, preparingOrder.containsAlcohol), preparingOrder.getExecutor())
                    // and we can be called-back when the process ran inside a CompletableFuture finishes and
                    // return some result when want to process, like here the produced eggs
                    .thenAcceptAsync(egg -> {
                        logger.debug("add egg to package");
                        boolean eggAdded = preparingOrder.packaging.addChocolateEgg(egg);
                        logger.info("{} egg has{}been produced.",
                                egg.getChocolateType(), eggAdded ? " " : " not ");
                    }, preparingOrder.getExecutor())
                    .exceptionally(e -> {
                        System.out.println("Something happens here: "+e);
                        // we need to check here if no exception has been raised from the execution the
                        // the future. Exception raised inside the body of supplyAsync and thenAcceptAsync
                        // won't show up anywhere unless we catch them with this "exceptionally" method
                        logger.error("Something bad happen", e);
                        return null;
                    });
        }
    }

    /**
     * Helper method to produce an egg.
     *
     * @param factory the factory needed to create an egg
     * @param type the chocolate type
     * @param containsAlcohol if this chocolate may contain alcohol or not
     * @return the produced egg by given factory
     */
    private ChocolateEgg produceEgg(ChocolateEggFactory factory, ChocolateType type,
                                    boolean containsAlcohol) {
        logger.debug("produce egg with factory {} of type {} with{} alcohol",
                factory.getClass().getSimpleName(), type.name(), containsAlcohol ? "" : "out");
        try {
            // add a sleep to simulate some preparation time and therefore longer tasks
            Thread.sleep(ThreadLocalRandom.current().nextInt(2000));
        } catch (InterruptedException e) {
            logger.error("Interrupted while producing an egg", e);
        }
        return factory.createChocolateEgg(type, containsAlcohol);
    }

    /**
     * Gets the next factory
     *
     * @return an egg factory
     */
    private ChocolateEggFactory getNextFactory(boolean stuffed) {
        if (factoryIndex % numberOfFillings != 0 && stuffed) {
            factoryIndex ++;
            return preparingOrder.getStuffedEggFactory();
        }
        else {
            return preparingOrder.getHollowEggFactory();
        }
    }
}

