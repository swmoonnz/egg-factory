package uc.seng301.asg3.order;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import uc.seng301.asg3.egg.ChocolateEggFactory;
import uc.seng301.asg3.egg.ChocolateType;
import uc.seng301.asg3.packaging.PackagingType;
import java.util.concurrent.ThreadLocalRandom;

public class RegularHollowEggStrategy implements PrepareStrategy {

    private final PreparingOrder preparingOrder;
    protected Integer hollowsUsed;
    protected Integer chocolateTypeIndex;
    protected Integer factoryIndex;
    protected Integer numberOfChocolateTypes;
    protected Integer numberOfFillings;

    protected final Logger logger = LogManager.getLogger(PreparingOrder.class.getName());

    public RegularHollowEggStrategy(PreparingOrder po) {
        this.preparingOrder = po;
        hollowsUsed = 0;
        numberOfFillings = preparingOrder.getStuffedEggFactory().getNumberOfFillings(preparingOrder.containsAlcohol) + 1; // for Hollow Egg
        numberOfChocolateTypes = 3;
        chocolateTypeIndex = ThreadLocalRandom.current().nextInt(numberOfChocolateTypes);
        factoryIndex = ThreadLocalRandom.current().nextInt(numberOfFillings);
        preparingOrder.packaging.addChocolateEgg(preparingOrder.produceEgg(preparingOrder.getHollowEggFactory(), getChocolateType(), false));
        numberOfChocolateTypes = 4;
    }

    /**
     * Get the next egg factory
     *
     * @return an egg factory
     */
    @Override
    public ChocolateEggFactory getNextFactory(boolean stuffed) {
        if (stuffed) {
            if (factoryIndex % numberOfFillings != 0 && stuffed) {
                factoryIndex ++;
                return preparingOrder.getStuffedEggFactory();
            }
            else if (hollowsLeft()) {
                factoryIndex ++;
                hollowsUsed ++;
                return preparingOrder.getHollowEggFactory();
            }
            else{
                factoryIndex ++;
                return preparingOrder.getStuffedEggFactory();
            }
        }
        else {
            factoryIndex ++;
            return preparingOrder.getHollowEggFactory();
        }
    }

    /**
     * Checks if a hollow egg can be added
     *
     * @return true if hollow egg can be added or else false
     */
    private boolean hollowsLeft() {
        return hollowsUsed < Math.round(preparingOrder.quantity * 0.25);
    }

    /**
     * Get a chocolate type based on client selection. If crunchy is selected it will change to a white chocolate type
     *
     * @return a random chocolate type, or this order chocolate type if packaging type requires it
     * @see PackagingType
     */
    private ChocolateType getChocolateType() {
        ChocolateType result;
        if (preparingOrder.chocolateType.equals(ChocolateType.CRUNCHY)) {
            result = ChocolateType.WHITE;
        }
        else {
            result = preparingOrder.chocolateType;
        }
        return result;
    }

    /**
     * Gets the chocolate type for the inner eggs based on the clients selection
     * @return
     */
    @Override
    public ChocolateType getNextChocolateType() {
        return preparingOrder.chocolateType;
    }
}