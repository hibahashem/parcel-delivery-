package com.company;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.PriorityQueue;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

public class Game {
    state Initial_state;
    state current_state;
    int node_num=0;
    long start;
    long end;
    double executionTime;

    public Game() {
        Initial_state = new state();
        this.current_state = new state();
        Scanner in = new Scanner(System.in);
        System.out.println("* the information for level 1&2&3 is:width=4,length=7");
        System.out.println("* the information for level 4 is:width=4,length=6");
        System.out.println("enter the  level number");
        int n = in.nextInt();
        System.out.println("enter the  width");
        int w = in.nextInt();
        System.out.println("enter the  length");
        int l = in.nextInt();
        this.Initial_state.insert(w, l,n);
    }

    public state ucs()
    {
        start=System.nanoTime();
        ArrayList<state> visited = new ArrayList<>();
        ArrayList<state> children = new ArrayList<>();
        PriorityQueue<state> open_state = new PriorityQueue<state>(new sort());
        if (Initial_state.endGame(Initial_state))
            return Initial_state;
        open_state.add(Initial_state);
        while (!open_state.isEmpty()) {
            current_state = open_state.poll();
            node_num++;
            if (current_state.endGame(current_state)){
                end=System.nanoTime();
                executionTime=(double)(end-start)/1000000000;
                return current_state;
            }
            visited.add(current_state);
            children = current_state.children_generation();
            for (int i = 0; i < children.size(); i++) {
                int k = 0;
                int v = 0;
                for (int j = 0; j < visited.size(); j++) {
                    if (children.get(i).matching(visited.get(j), children.get(i))) {
                        v = 1;
                    }
                }
                Iterator<state> value = open_state.iterator();
                while (value.hasNext()) {
                    state next = value.next();
                    if (current_state.matching(children.get(i), next)) {
                        if (children.get(i).cost < next.cost) {
                            value.remove();
                        } else
                            k = 1;
                    }
                }
                if (k == 1 || v == 1)
                    continue;
                open_state.add(children.get(i));
            }
        }

        return null;
    }

    public state Astar1() {
        start=System.nanoTime();
        ArrayList<state> visited = new ArrayList<>();
        ArrayList<state> children = new ArrayList<>();
        PriorityQueue<state> open_state = new PriorityQueue<state>(new sortA());
        if (Initial_state.endGame(Initial_state))
            return Initial_state;
        open_state.add(Initial_state);
        while (!open_state.isEmpty()) {
            current_state = open_state.poll();
            node_num++;
            if (current_state.endGame(current_state)){
                end=System.nanoTime();
                executionTime=(double)(end-start)/1000000000;
                return current_state;
            }
            visited.add(current_state);
            children = current_state.children_generation();
            for (int i = 0; i < children.size(); i++) {
                children.get(i).heuristic = heuristic1(children.get(i).X_Viechle, children.get(i).Y_Viechle, children.get(i).insialX, children.get(i).insialY
                        , children.get(i).parent.array, children.get(i).order_count, children.get(i).parent.orders_p);
                int k = 0;
                int v = 0;
                for (int j = 0; j < visited.size(); j++) {
                    if (children.get(i).matching(visited.get(j), children.get(i))) {
                        if (children.get(i).cost + children.get(i).heuristic >= visited.get(j).cost + visited.get(j).heuristic)
                            v = 1;
                    }
                }
                Iterator<state> value = open_state.iterator();
                while (value.hasNext()) {
                    state next = value.next();
                    if (current_state.matching(children.get(i), next)) {
                        if (children.get(i).cost + children.get(i).heuristic < next.cost + next.heuristic) {
                            value.remove();
                        } else
                            k = 1;
                    }
                }
                if (k == 1 || v == 1)
                    continue;
                open_state.add(children.get(i));
            }
        }

        return null;
    }

    public state Astar2() {
        start=System.nanoTime();
        ArrayList<state> visited = new ArrayList<>();
        ArrayList<state> children = new ArrayList<>();
        PriorityQueue<state> open_state = new PriorityQueue<state>(new sortA());
        if (Initial_state.endGame(Initial_state))
            return Initial_state;
        open_state.add(Initial_state);
        while (!open_state.isEmpty()) {
            current_state = open_state.poll();
            node_num++;
            if (current_state.endGame(current_state)){
                end=System.nanoTime();
                executionTime=(double)(end-start)/1000000000;
                return current_state;
            }
            visited.add(current_state);
            children = current_state.children_generation();
            for (int i = 0; i < children.size(); i++) {
                children.get(i).heuristic = heuristic2(children.get(i), 0);
                int k = 0;
                int v = 0;
                for (int j = 0; j < visited.size(); j++) {
                    if (children.get(i).matching(visited.get(j), children.get(i))) {
                        if (children.get(i).cost + children.get(i).heuristic >= visited.get(j).cost + visited.get(j).heuristic)
                            v = 1;
                    }
                }
                Iterator<state> value = open_state.iterator();
                while (value.hasNext()) {
                    state next = value.next();
                    if (current_state.matching(children.get(i), next)) {
                        if (children.get(i).cost + children.get(i).heuristic < next.cost + next.heuristic) {
                            value.remove();
                        } else
                            k = 1;
                    }
                }
                if (k == 1 || v == 1)
                    continue;
                open_state.add(children.get(i));
            }
        }

        return null;
    }

    public state Astar3() {
        start=System.nanoTime();
        ArrayList<state> visited = new ArrayList<>();
        ArrayList<state> children = new ArrayList<>();
        PriorityQueue<state> open_state = new PriorityQueue<state>(new sortA());
        if (Initial_state.endGame(Initial_state))
            return Initial_state;
        open_state.add(Initial_state);
        while (!open_state.isEmpty()) {
            current_state = open_state.poll();
            node_num++;
            if (current_state.endGame(current_state)){
                end=System.nanoTime();
                executionTime=(double)(end-start)/1000000000;
                return current_state;
            }
            visited.add(current_state);
            children = current_state.children_generation();
            for (int i = 0; i < children.size(); i++) {
                children.get(i).heuristic = heuristic3(children.get(i));
                int k = 0;
                int v = 0;
                for (int j = 0; j < visited.size(); j++) {
                    if (children.get(i).matching(visited.get(j), children.get(i))) {
                        if (children.get(i).cost + children.get(i).heuristic >= visited.get(j).cost + visited.get(j).heuristic)
                            v = 1;
                    }
                }
                Iterator<state> value = open_state.iterator();
                while (value.hasNext()) {
                    state next = value.next();
                    if (current_state.matching(children.get(i), next)) {
                        if (children.get(i).cost + children.get(i).heuristic < next.cost + next.heuristic) {
                            value.remove();
                        } else
                            k = 1;
                    }
                }
                if (k == 1 || v == 1)
                    continue;
                open_state.add(children.get(i));
            }
        }

        return null;
    }


    public int heuristic1(int currentx, int currenty, int distx, int disty, String[][] arr, int pcount, boolean[] orders_p) {
        int cost = 0;
        while (currentx != distx || currenty != disty) {
            if (currentx > distx) {
                for (int i = 0; i < pcount; i++) {
                    if (arr[currentx][currenty].equals("p" + i) && orders_p[i] == false) {
                        cost++;
                    }
                }
                currentx--;
                cost++;
            }
            if (currentx < distx) {
                for (int i = 0; i < pcount; i++) {
                    if (arr[currentx][currenty].equals("p" + i) && orders_p[i] == false) {
                        cost++;
                    }
                }
                currentx++;
                cost++;
            }
            if (currenty < disty) {
                for (int i = 0; i < pcount; i++) {
                    if (arr[currentx][currenty].equals("p" + i) && orders_p[i] == false) {
                        cost++;
                    }
                }
                currenty++;
                cost++;
            }
            if (currenty > disty) {
                for (int i = 0; i < pcount; i++) {
                    if (arr[currentx][currenty].equals("p" + i) && orders_p[i] == false) {
                        cost++;
                    }
                }
                currenty--;
                cost++;
            }
        }
        return cost;
    }

    public pair getxandy(state currentState, int index, String s) {
        String dname = s + index;
        pair xyindex = new pair();
        for (int i = 0; i < currentState.row; i++) {
            for (int j = 0; j < currentState.column; j++) {
                if (currentState.array[i][j].equals(dname)) {
                    xyindex.x = i;
                    xyindex.y = j;
                }
            }
        }
        return xyindex;
    }

    public int heuristic2(state currentState, int index) {
        int currentx = currentState.X_Viechle;
        int currenty = currentState.Y_Viechle;
        int cost = 0;
        pair dindex = getxandy(currentState, index, "D");
        pair pindex = getxandy(currentState, index, "p");

        if (currentState.orders_p[index] == false) {
            cost = cost + heuristic1(currentx, currenty, pindex.x, pindex.y, currentState.parent.array, currentState.order_count, currentState.parent.orders_p);
            cost = cost + heuristic1(pindex.x, pindex.y, dindex.x, dindex.y, currentState.parent.array, currentState.order_count, currentState.parent.orders_p);
        } else {
            if (currentState.orders_D[index] == false)
                cost = cost + heuristic1(currentx, currenty, dindex.x, dindex.y, currentState.parent.array, currentState.order_count, currentState.parent.orders_p);
        }
        cost = cost + heuristic1(dindex.x, dindex.y, currentState.insialX, currentState.insialY, currentState.parent.array, currentState.order_count, currentState.parent.orders_p);
        return cost;
    }

    public int heuristic3(state currentState) {
        int maxcost = 0;
        for (int i = 0; i < currentState.order_count; i++) {
            int cost = heuristic2(currentState, i);
            if (cost > maxcost)
                maxcost = cost;
        }
        return maxcost;
    }

    public void print_game(state goal){
        ArrayList<state> path=new ArrayList();
        ArrayList<String> steps=new ArrayList();
        ArrayList<String> Actions=new ArrayList();
        path.add(goal);
        steps.add(goal.step);
        Actions.add(goal.Action);
        goal.print_grid();
        System.out.println("this is the goal");
        System.out.println("this is the path solution and steps :");
        while ((goal.parent != null)) {
            path.add(0,goal.parent);
            steps.add(0,goal.parent.step);
            Actions.add(0,goal.parent.Action);
            goal=goal.parent;
        }
        for(int i=0;i<path.size();i++){
            path.get(i).print_grid();
            System.out.println(path.get(i).step +","+ path.get(i).Action);
            // System.out.println(goal.parent.cost+goal.parent.heuristic);
            System.out.println("*******************************");
        }
       // System.out.println(steps);
      //  System.out.println(Actions);
    }
}


