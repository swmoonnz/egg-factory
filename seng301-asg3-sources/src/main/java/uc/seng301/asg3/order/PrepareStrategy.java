package uc.seng301.asg3.order;

import uc.seng301.asg3.egg.ChocolateType;
import uc.seng301.asg3.egg.HollowEggFactory;
import uc.seng301.asg3.egg.StuffedEggFactory;
import uc.seng301.asg3.packaging.Packaging;
import uc.seng301.asg3.packaging.PackagingType;


public abstract class PrepareStrategy {

    public Packaging packaing;
    public HollowEggFactory hollowEggFactory;
    public StuffedEggFactory stuffedEggFactory;
    public boolean stuffed;
    public ChocolateType chocolateType;
    public boolean containsAlcohol;
    protected PackagingType packagingType;


//    public PrepareStrategy(Packaging packaging, HollowEggFactory hollowEggFactory, StuffedEggFactory stuffedEggFactory, boolean stuffed, ChocolateType chocolateType, boolean containsAlcohol ) {
//        this.packaing = packaging;
//        this.hollowEggFactory = hollowEggFactory;
//        this.stuffedEggFactory = stuffedEggFactory;
//        this.stuffed = stuffed;
//        this.chocolateType = chocolateType;
//        this.containsAlcohol = containsAlcohol;
//    }

    public void prepareBox() {
        throw new UnsupportedOperationException("Packaging could not be filled.");

    }


    public abstract void prepareBox(Packaging packaging, HollowEggFactory hollowEggFactory, StuffedEggFactory stuffedEggFactory, boolean stuffed, ChocolateType chocolateType, boolean containsAlcohol);
}
