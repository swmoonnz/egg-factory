package uc.seng301.asg3.order;

import uc.seng301.asg3.egg.ChocolateType;
import uc.seng301.asg3.egg.HollowEggFactory;
import uc.seng301.asg3.egg.StuffedEggFactory;
import uc.seng301.asg3.packaging.Packaging;
import uc.seng301.asg3.packaging.PackagingType;


public interface PrepareStrategy {

    void prepare();
}
