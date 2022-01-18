package com.company;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
//
        // write your code here
        Scanner in = new Scanner(System.in);
        state goal=new state();
        Game new_game = new Game();
        System.out.println("choose algorithm:  ");
        System.out.println("1-usc  2-Astar(trial1)  3-Astar(trial2)   4-Astar(trial3)");
        int n = in.nextInt();
        if(n==1){
             goal=new_game.ucs();
        }
        if(n==2){
            goal=new_game.Astar1();
        }
        if(n==3){
            goal=new_game.Astar2();
        }
        if(n==4){
            goal=new_game.Astar3();
        }
        System.out.println("*****************************");
        if(goal!=null) {
            new_game.print_game(goal);
        }
        System.out.print("solution cost:  ");
        System.out.println(goal.cost+goal.heuristic);
        System.out.print("execution Time:  ");
        System.out.println(new_game.executionTime + "   seconds");
        System.out.print("number of nodes processed:  ");
        System.out.println(new_game.node_num);
    }
}
