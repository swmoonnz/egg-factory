package uc.seng301.asg3.order;

import uc.seng301.asg3.egg.*;
import uc.seng301.asg3.packaging.Packaging;
import uc.seng301.asg3.packaging.PackagingType;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.TimeUnit;


public class RegularBoxPrepare extends PrepareStrategy {
//    public RegularBoxPrepare(Packaging packaging, HollowEggFactory hollowEggFactory, StuffedEggFactory stuffedEggFactory, boolean stuffed, ChocolateType chocolateType, boolean containsAlcohol) {
//        super(packaging, hollowEggFactory, stuffedEggFactory, stuffed, chocolateType, containsAlcohol);
//        this.stuffed = stuffed;
//        this.chocolateType = chocolateType;
//        this.containsAlcohol = containsAlcohol;
//        this.packagingType = packaging.getPackagingType();
////        int quantity = packaging.getEggs().getSize();
//
//    }

    @Override
    public void prepareBox(Packaging packaging, HollowEggFactory hollowEggFactory, StuffedEggFactory stuffedEggFactory, boolean stuffed, ChocolateType chocolateType, boolean containsAlcohol) {
        if (PackagingType.isHollowEggPackaging(packagingType)) {
            packaging.addChocolateEgg(produceEgg(hollowEggFactory, randomChocolateType(), false));
        }


    }

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

//    @Override
//    public void prepareBox() {
//        int quantity = 1;
//        int num = quantity / 5;

//        if (quantity > 5) {
//
//        }
//        for (int i = 0; i < quantity; i++) {
//            // CompletableFutures are sorts of threads that can be easily created on the fly to process
//            // long running tasks, as the produceEgg method. We also pass the executor where we created
//            // a pool of threads with a given size of 3
//            CompletableFuture.supplyAsync(() ->
//                    produceEgg(randomFactory(stuffed), randomChocolateType(), withAlcohol(containsAlcohol)), executor)
//                    // and we can be called-back when the process ran inside a CompletableFuture finishes and
//                    // return some result when want to process, like here the produced eggs
//                    .thenAcceptAsync(egg -> {
//                        logger.debug("add egg to package");
//                        boolean eggAdded;
//                        if (PackagingType.isHollowEggPackaging(packagingType)) {
//                            eggAdded = packaging.getEggs().get(0).addChocolateEgg(egg);
//                        } else {
//                            eggAdded = packaging.addChocolateEgg(egg);
//                        }
//                        logger.info("{} egg has{}been produced.",
//                                egg.getChocolateType(), eggAdded ? " " : " not ");
//                    }, executor)
//                    .exceptionally(e -> {
//                        // we need to check here if no exception has been raised from the execution the
//                        // the future. Exception raised inside the body of supplyAsync and thenAcceptAsync
//                        // won't show up anywhere unless we catch them with this "exceptionally" method
//                        logger.error("Something bad happen", e);
//                        return null;
//                    });
//    }
//        // we need to wait to ensure all tasks submitted to the executor (pool of threads)
//        // have been completed in any way (either with success or with an exception)
//        // if we do not wait, then tasks may take longer to complete and the method may return
//        // before they finished, so before the eggs are produced and packed.
//        try {
//            executor.awaitTermination(30, TimeUnit.SECONDS);
//        } catch (InterruptedException e) {
//            logger.error("Interrupted while waiting completion of all submitted tasks to executor", e);
//        }
//        executor.shutdown();
//        logger.debug("exiting");
//    }
//
//    /**
//     * Stop the production of all eggs in this order. Some eggs may have been produced and added into
//     * the packaging linked to this order.
//     */
//    @Override
//    public void cancel() {
//        executor.shutdownNow();
//    }
//
//    /**
//     * Check whether this order is fully done by checking if all expected eggs have been produced
//     * (i.e. this order quantity, potentially + 1 if it is a hollow_egg box).
//     *
//     * @return all expected eggs have been produced and added into the packaging
//     */
//    public boolean isFinished() {
//        logger.debug("packaging contains {} eggs", packaging.getEggs().size());
//        return PackagingType.isHollowEggPackaging(packagingType) ?
//                !packaging.getEggs().isEmpty() && packaging.getEggs().get(0).getContent().size() == quantity
//                : packaging.getEggs().size() == quantity;
//    }
//
//    /**
//     * Return the packaging when all eggs of this order have been produced.
//     *
//     * @return the packaging, or null if this order is not yet finished
//     */
//    public Packaging getPackaging() {
//        return isFinished() ? packaging : null;
//    }
//
//    /**
//     * Helper method to produce an egg.
//     *
//     * @param factory the factory needed to create an egg
//     * @param type the chocolate type
//     * @param containsAlcohol if this chocolate may contain alcohol or not
//     * @return the produced egg by given factory
//     */
//    private ChocolateEgg produceEgg(ChocolateEggFactory factory, ChocolateType type,
//                                    boolean containsAlcohol) {
//        logger.debug("produce egg with factory {} of type {} with{} alcohol",
//                factory.getClass().getSimpleName(), type.name(), containsAlcohol ? "" : "out");
//        try {
//            // add a sleep to simulate some preparation time and therefore longer tasks
//            Thread.sleep(ThreadLocalRandom.current().nextInt(2000));
//        } catch (InterruptedException e) {
//            logger.error("Interrupted while producing an egg", e);
//        }
//        return factory.createChocolateEgg(type, containsAlcohol);
//    }
//
//    /**
//     * Get a random egg factory
//     *
//     * @return an egg factory
//     */
//    private ChocolateEggFactory randomFactory(boolean stuffed) {
//        return ThreadLocalRandom.current().nextInt() % 2 == 0 && stuffed ?
//                stuffedEggFactory : hollowEggFactory;
//    }
//
//    /**
//     * Get a random chocolate type if packaging allows it
//     *
//     * @return a random chocolate type, or this order chocolate type if packaging type requires it
//     * @see PackagingType
//     */
//    private ChocolateType randomChocolateType() {
//        return packagingType.equals(PackagingType.MIXED_BOX)
//                || packagingType.equals(PackagingType.MIXED_HOLLOW_EGG) ?
//                ChocolateType.values()[ThreadLocalRandom.current().nextInt(ChocolateType.values().length)]
//                : chocolateType;
//    }
//
//    /**
//     * Randomize the chance to have an alcoholic egg when the order allows to have some
//     *
//     * @param containsAlcohol some eggs may contain alcohol
//     * @return always false if containsAlcohol is false, randomly true otherwise
//     */
//    private boolean withAlcohol(boolean containsAlcohol) {
//        return ThreadLocalRandom.current().nextInt() % 2 == 0 && containsAlcohol;
//    }


}
