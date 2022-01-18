package com.company;
import java.util.Comparator;
public class sort implements Comparator<state>{
    @Override

    public int compare(state a, state b) {
        if(a.cost>b.cost)
            return 1;
        else if(a.cost<b.cost)
            return-1;
        return 0;
    }
}
