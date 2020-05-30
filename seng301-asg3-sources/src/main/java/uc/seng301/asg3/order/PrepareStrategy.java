package uc.seng301.asg3.order;

import uc.seng301.asg3.egg.ChocolateEggFactory;
import uc.seng301.asg3.egg.ChocolateType;

public interface PrepareStrategy {
    ChocolateEggFactory getNextFactory(boolean Stuffed);
    ChocolateType getNextChocolateType();
}
