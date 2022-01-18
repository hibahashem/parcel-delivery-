package com.company;
import java.util.Comparator;
public class sortA implements Comparator<state>{
    @Override

    public int compare(state a, state b) {
        if(a.cost+a.heuristic>b.cost+b.heuristic)
            return 1;
        else if(a.cost+a.heuristic<b.cost+b.heuristic)
            return-1;
          return 0;

    }
}
