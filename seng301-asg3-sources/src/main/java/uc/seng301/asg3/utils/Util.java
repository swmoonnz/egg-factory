package uc.seng301.asg3.utils;

import uc.seng301.asg3.egg.ChocolateEgg;

import java.util.List;
import java.util.ListIterator;

public class Util {

    /**
     *  Checks whether the egg can be put into the packaging or the
     *  content of the hollow egg based on whether they are the same egg or not
     *
     * @param content for eggs to be added into
     * @param egg the egg to be added into the list
     * @return the updated list
     */
    public static boolean sortEggs(List content, ChocolateEgg egg) {
        ListIterator<ChocolateEgg> eggItr = content.listIterator();
        ChocolateEgg previous;
        ChocolateEgg next;
        Integer index = 1;
        if (content.size() == 0) {
            return content.add(egg);
        }
        else {
            previous = eggItr.next();
            if (!previous.compareChocolate(egg)) {
                content.add(0, egg);
                return true;
            }
            else {
                while (eggItr.hasNext()) {
                    next = eggItr.next();
                    if (!(previous.compareChocolate(egg)) && !(next.compareChocolate(egg))) { //broken
                        content.add(index, egg);
                        return true;
                    }
                    else{
                        index ++;
                        previous = next;
                    }
                }
            }
        }
        return content.add(egg);
    }
}
