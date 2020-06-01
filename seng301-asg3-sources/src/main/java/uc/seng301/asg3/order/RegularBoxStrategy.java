
package uc.seng301.asg3.order;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import uc.seng301.asg3.egg.ChocolateEggFactory;
import uc.seng301.asg3.egg.ChocolateType;
import java.util.concurrent.ThreadLocalRandom;

public class RegularBoxStrategy implements PrepareStrategy {
    private final PreparingOrder preparingOrder;
    protected Integer factoryIndex;
    protected Integer numberOfFillings;
    protected final Logger logger = LogManager.getLogger(PreparingOrder.class.getName());

    public RegularBoxStrategy(PreparingOrder po) {
        this.preparingOrder = po;
        numberOfFillings = preparingOrder.getStuffedEggFactory().getNumberOfFillings(preparingOrder.containsAlcohol) + 1; // we add 1 for the Hollow Egg
        factoryIndex = ThreadLocalRandom.current().nextInt(numberOfFillings);
    }

    /**
     * Gets the next factory
     *
     * @return an egg factory
     */
    @Override
    public ChocolateEggFactory getNextFactory(boolean stuffed) {
        if(factoryIndex % numberOfFillings != 0 && stuffed) {
            factoryIndex ++;
            return preparingOrder.getStuffedEggFactory();
        }
        else {
            factoryIndex ++;
            return preparingOrder.getHollowEggFactory();
        }
    }

    /**
     * For the regular box strategy, gets the chocolate type entered by the client
     *
     * @return the chocolate type entered by the client
     */
    @Override
    public ChocolateType getNextChocolateType() {
        return preparingOrder.chocolateType;
    }
}