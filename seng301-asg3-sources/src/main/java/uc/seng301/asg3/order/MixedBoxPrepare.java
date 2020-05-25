package uc.seng301.asg3.order;

import uc.seng301.asg3.egg.HollowEggFactory;
import uc.seng301.asg3.egg.StuffedEggFactory;
import uc.seng301.asg3.packaging.PackagingType;

public class MixedBoxPrepare extends PrepareStrategy {
    public MixedBoxPrepare(PackagingType packagingType, HollowEggFactory hollowEggFactory, StuffedEggFactory stuffedEggFactory, boolean stuffed, boolean containsAlcohol) {
        super();
    }

    @Override
    public void prepareBox() {
        super.prepareBox();
    }
}
