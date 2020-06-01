package uc.seng301.asg3.order;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import uc.seng301.asg3.egg.ChocolateEggFactory;
import uc.seng301.asg3.egg.ChocolateType;
import java.util.concurrent.ThreadLocalRandom;

public class MixedHollowEggStrategy implements PrepareStrategy {

    private final PreparingOrder preparingOrder;
    protected Integer crunchiesUsed;
    protected Integer chocolateTypeIndex;
    protected Integer factoryIndex;
    protected Integer numberOfChocolateTypes;
    protected Integer numberOfFillings;

    protected final Logger logger = LogManager.getLogger(PreparingOrder.class.getName());

    public MixedHollowEggStrategy(PreparingOrder po) {
        this.preparingOrder = po;
        numberOfFillings = preparingOrder.getStuffedEggFactory().getNumberOfFillings(preparingOrder.containsAlcohol) + 1; // for Hollow Egg
        numberOfChocolateTypes = 3;
        chocolateTypeIndex = ThreadLocalRandom.current().nextInt(numberOfChocolateTypes);
        factoryIndex = ThreadLocalRandom.current().nextInt(numberOfFillings);
        crunchiesUsed = 0;
        preparingOrder.packaging.addChocolateEgg(preparingOrder.produceEgg(preparingOrder.getHollowEggFactory(), getNextChocolateType(), false));
        numberOfChocolateTypes = 4;
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
     * Method to get the next chocolate type by checking if a crunchy is allowed or not
     *
     * @return a chocolate type
     */
    @Override
    public ChocolateType getNextChocolateType() {
        ChocolateType result;
        result = ChocolateType.values()[chocolateTypeIndex];
        if (!areCrunchiesLeft()) {
            if (chocolateTypeIndex + 1 < numberOfChocolateTypes) {
                chocolateTypeIndex++;
            } else {
                chocolateTypeIndex = 0;
            }
        } else {
            if (result == ChocolateType.CRUNCHY) {
                crunchiesUsed++;
            }
            if (!areCrunchiesLeft()) {
                numberOfChocolateTypes = 3;
                chocolateTypeIndex = 0;
            } else {
                if (chocolateTypeIndex + 1 < numberOfChocolateTypes) {
                    chocolateTypeIndex++;
                } else {
                    chocolateTypeIndex = 0;
                }
            }
        }
        return result;
    }

    /**
     * Checks that adding a crunchy egg will not make it higher than 10% of the requirement
     *
     * @return true if crunchy type can be added, else returns false
     */
    private boolean areCrunchiesLeft() {
        return crunchiesUsed < (preparingOrder.quantity * 0.1);
    }

}