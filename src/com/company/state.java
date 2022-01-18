package com.company;
import java.time.Year;
import java.util.*;
import java.lang.String;
import java.io.*;
public class state {
    int row,column;
    String array [][];
    state parent;
    int X_Viechle;
    int Y_Viechle;
    int order_count =0;
    boolean []orders_p;
    boolean []orders_D;
    int cost =0;
    int weight =0;
    int insialX;
    int insialY;
    int heuristic=0;
    String step="in site";
    String Action="nothing";


    state(){}
    state(int row ,int column ,state parent,int X_Viechle,int Y_Viechle,int insialX,int insialY){
        this.row = row;
        this.column = column;
        this.array = new String[row][column];
        this.parent = parent;
        this.X_Viechle=X_Viechle;
        this.Y_Viechle= Y_Viechle;
        this.insialX=insialX;
        this.insialY= insialY;
        this.cost=parent.cost;
        this.order_count=parent.order_count;
        this.orders_D = new boolean[order_count];
        this.orders_p = new boolean[order_count];
        this.weight =parent.weight;
        for(int i =0; i<row ; i++){
            for (int j=0 ; j<column ;j++){
                this.array[i][j] = parent.array[i][j];
            }
        }

            for(int i =0; i<this.order_count ; i++){
                this.orders_D[i]=parent.orders_D[i];
            }


        for(int i =0; i<this.order_count ; i++){
            this.orders_p[i]=parent.orders_p[i];
        }



    }


    void insert (int row ,int column , int num){
        this.row=row;
        this.column=column;
        this.array=new String[row][column];
        File file = new File("src/level"+num+".txt");
        Scanner scanner =null;
        try {
             scanner = new Scanner(file);
        } catch (FileNotFoundException e) {
            e.printStackTrace();

        }
            for (int i =0; i<row;i++){
                for(int j=0;j<column;j++){
                    if (scanner.hasNext()){
                        {
                            array[i][j] = scanner.next();
                            if (array[i][j].equals("T")) {
                                this.X_Viechle = i;
                                this.Y_Viechle = j;
                                this.insialX=i;
                                this.insialY=j;
                            }
                            String cell=array[i][j];
                            if (cell.charAt(0)=='p')
                            {
                                this.order_count++;
                            }
                        }
                    }
                }
            }
            this.orders_p=new boolean[this.order_count];
            this.orders_D=new boolean[this.order_count];
    }
    public void print_grid(){
        String greenBold = "\033[32;1m";
        String redBold = "\033[31;1m";
        String whiteBold = "\033[35;1m";
        String color = "\033[34;1m";
        for(int i=0;i<this.row;i++){

            for(int j=0;j<this.column;j++){
                if(array[i][j].equals("T"))
                    System.out.print("\t"+greenBold+array[i][j] );
                else if(array[i][j].equals("#"))
                    System.out.print("\t"+redBold+array[i][j]);
                else if(array[i][j].equals("."))
                    System.out.print("\t"+whiteBold+array[i][j]);
                else
                    System.out.print("\t"+color+array[i][j]);

            }
            System.out.println();
        }
    }

    public boolean checkright(int X_Viechle, int Y_Viechle) {

        if ((Y_Viechle+1 < this.column) && (!array[X_Viechle][Y_Viechle + 1].equals("#"))) {
            return true;
        }
        return false;
    }
    public void rightmovment(state next_state) {
    next_state.array[next_state.X_Viechle][next_state.Y_Viechle + 1] = "T";
    if(parent==null)
    {
        next_state.array[next_state.X_Viechle][next_state.Y_Viechle] =".";
    }
    else{
        next_state.array[next_state.X_Viechle][next_state.Y_Viechle] = parent.array[next_state.X_Viechle ][next_state.Y_Viechle ];
    }
    next_state.X_Viechle = next_state.X_Viechle;
    next_state.Y_Viechle = next_state.Y_Viechle + 1;
    next_state.cost = next_state.cost+1;
}
    public ArrayList<state> moveright() {
        ArrayList<state> States=new ArrayList<>();

        if (checkright(this.X_Viechle,this.Y_Viechle)) {
            state next_state = new state( this.row ,this.column ,this ,this.X_Viechle,this.Y_Viechle,this.insialX,this.insialY);
            if(next_state.array[next_state.X_Viechle ][next_state.Y_Viechle+1].charAt(0) == 'p'){
                state next_state1 = new state( this.row ,this.column ,this ,this.X_Viechle,this.Y_Viechle,this.insialX,this.insialY);
                int index = Character.getNumericValue( next_state.array[next_state.X_Viechle][next_state.Y_Viechle+1].charAt(1));
                rightmovment(next_state1);
                States.add(next_state1);
                state next_state2 = new state( this.row ,this.column ,this ,this.X_Viechle,this.Y_Viechle,this.insialX,this.insialY);
                recive_order(index,next_state2);
                rightmovment(next_state2);
                States.add(next_state2);
            }
          else if(next_state.array[next_state.X_Viechle][next_state.Y_Viechle+1].charAt(0) == 'D'){
                int index = Character.getNumericValue( next_state.array[next_state.X_Viechle][next_state.Y_Viechle+1].charAt(1));
                rightmovment(next_state);
                States.add(next_state);
                state next_state2 = new state( this.row ,this.column ,this ,this.X_Viechle,this.Y_Viechle,this.insialX,this.insialY);
                rightmovment(next_state2);
                send_order(index,next_state2);
                States.add(next_state2);
            }
          else
            {
                rightmovment(next_state);
                States.add(next_state);
            }
            for(int i=0;i<States.size();i++){
                States.get(i).step="right";
            }
            return States;
        }
        return null;
    }


    public boolean checkleft(int X_Viechle, int Y_Viechle) {
        if ((Y_Viechle-1 >= 0) && (!array[X_Viechle][Y_Viechle - 1].equals("#"))) {
            return true;
        }
        return false;
    }
    public void leftmovment(state next_state) {
        next_state.array[next_state.X_Viechle][next_state.Y_Viechle - 1] = "T";
        if(parent==null)
        {
            next_state.array[next_state.X_Viechle][next_state.Y_Viechle] =".";
        }
        else{
            next_state.array[next_state.X_Viechle][next_state.Y_Viechle] = parent.array[next_state.X_Viechle ][next_state.Y_Viechle ];
        }
        next_state.X_Viechle = next_state.X_Viechle;
        next_state.Y_Viechle = next_state.Y_Viechle - 1;
        next_state.cost = next_state.cost+1;
    }
    public ArrayList<state> moveleft() {
        ArrayList<state> States=new ArrayList<>();

        if (checkleft(this.X_Viechle,this.Y_Viechle)) {
            state next_state = new state( this.row ,this.column ,this ,this.X_Viechle,this.Y_Viechle,this.insialX,this.insialY);
            if(next_state.array[next_state.X_Viechle][next_state.Y_Viechle-1].charAt(0) == 'p'){
                state next_state1 = new state( this.row ,this.column ,this ,this.X_Viechle,this.Y_Viechle,this.insialX,this.insialY);
                int index = Character.getNumericValue( next_state.array[next_state.X_Viechle][next_state.Y_Viechle-1].charAt(1));
                leftmovment(next_state1);
                States.add(next_state1);
                state next_state2 = new state( this.row ,this.column ,this ,this.X_Viechle,this.Y_Viechle,this.insialX,this.insialY);
                recive_order(index,next_state2);
                leftmovment(next_state2);
                States.add(next_state2);
            }
            else if(next_state.array[next_state.X_Viechle][next_state.Y_Viechle-1].charAt(0) == 'D'){
                int index = Character.getNumericValue( next_state.array[next_state.X_Viechle][next_state.Y_Viechle-1].charAt(1));
                leftmovment(next_state);
                States.add(next_state);
                state next_state2 = new state( this.row ,this.column ,this ,this.X_Viechle,this.Y_Viechle,this.insialX,this.insialY);
                leftmovment(next_state2);
                send_order(index,next_state2);
                States.add(next_state2);
            }
            else
            {
                leftmovment(next_state);
                States.add(next_state);
            }
            for(int i=0;i<States.size();i++){
                States.get(i).step="left";
            }
            return States;
        }
        return null;
    }


    public boolean checkup(int X_Viechle, int Y_Viechle) {
        if ((X_Viechle-1 >= 0 ) && (!array[X_Viechle-1][Y_Viechle].equals("#"))) {
            return true;
        }
        return false;
    }
    public void upmovment(state next_state) {
        next_state.array[next_state.X_Viechle -1][next_state.Y_Viechle ] = "T";
        if(parent==null)
        {
            next_state.array[next_state.X_Viechle][next_state.Y_Viechle] =".";
        }
        else{
            next_state.array[next_state.X_Viechle][next_state.Y_Viechle] = parent.array[next_state.X_Viechle ][next_state.Y_Viechle ];
        }
        next_state.X_Viechle = next_state.X_Viechle-1;
        next_state.Y_Viechle = next_state.Y_Viechle;
        next_state.cost = next_state.cost+1;
    }
    public ArrayList<state> moveup() {
        ArrayList<state> States=new ArrayList<>();
        if (checkup(this.X_Viechle,this.Y_Viechle)) {
            state next_state = new state( this.row ,this.column ,this ,this.X_Viechle,this.Y_Viechle,this.insialX,this.insialY);
            if(next_state.array[next_state.X_Viechle -1][next_state.Y_Viechle].charAt(0) == 'p'){
                int index = Character.getNumericValue( next_state.array[next_state.X_Viechle - 1][next_state.Y_Viechle].charAt(1));
                upmovment(next_state);
                States.add(next_state);
                state next_state2 = new state( this.row ,this.column ,this ,this.X_Viechle,this.Y_Viechle,this.insialX,this.insialY);
                recive_order(index,next_state2);
                upmovment(next_state2);
                States.add(next_state2);
            }
            else if(next_state.array[next_state.X_Viechle -1][next_state.Y_Viechle].charAt(0) == 'D'){
                int index = Character.getNumericValue( next_state.array[next_state.X_Viechle -1][next_state.Y_Viechle].charAt(1));
                upmovment(next_state);
                States.add(next_state);
                state next_state2 = new state( this.row ,this.column ,this ,this.X_Viechle,this.Y_Viechle,this.insialX,this.insialY);
                upmovment(next_state2);
                send_order(index,next_state2);
                States.add(next_state2);
            }
            else
            {
                upmovment(next_state);
                States.add(next_state);
            }
            for(int i=0;i<States.size();i++){
                States.get(i).step="up";
            }
            return States;
        }
        return null;
    }


    public boolean checkdown(int X_Viechle, int Y_Viechle) {
        if ((X_Viechle+1 < this.row ) && (!array[X_Viechle+1][Y_Viechle].equals("#"))) {
            return true;
        }
        return false;
    }
    void downmovment(state next_state) {
        next_state.array[next_state.X_Viechle +1][next_state.Y_Viechle ] = "T";
        if(parent==null)
        {
            next_state.array[next_state.X_Viechle][next_state.Y_Viechle] =".";
        }
        else{
            next_state.array[next_state.X_Viechle][next_state.Y_Viechle] = parent.array[next_state.X_Viechle][next_state.Y_Viechle ];
        }
        next_state.X_Viechle = next_state.X_Viechle+1;
        next_state.Y_Viechle = next_state.Y_Viechle;
        next_state.cost = next_state.cost+1;

    }
    public ArrayList<state> movedown() {
       ArrayList<state> States=new ArrayList<>();

        if (checkdown(this.X_Viechle,this.Y_Viechle)) {
            state next_state = new state( this.row ,this.column ,this ,this.X_Viechle,this.Y_Viechle,this.insialX,this.insialY);
            if(next_state.array[next_state.X_Viechle +1][next_state.Y_Viechle].charAt(0) == 'p'){
                int index = Character.getNumericValue(next_state.array[next_state.X_Viechle + 1][next_state.Y_Viechle].charAt(1));
                downmovment(next_state);
                States.add(next_state);
                state next_state2 = new state( this.row ,this.column ,this ,this.X_Viechle,this.Y_Viechle,this.insialX,this.insialY);
                recive_order(index,next_state2);
                downmovment(next_state2);
                States.add(next_state2);

            }
           else if(next_state.array[next_state.X_Viechle +1][next_state.Y_Viechle].charAt(0) == 'D'){
                int index = Character.getNumericValue(next_state.array[next_state.X_Viechle + 1][next_state.Y_Viechle].charAt(1)) ;
                downmovment(next_state);
                States.add(next_state);
                state next_state2 = new state( this.row ,this.column ,this ,this.X_Viechle,this.Y_Viechle,this.insialX,this.insialY);
                downmovment(next_state2);
                send_order(index,next_state2);
                States.add(next_state2);
            }
           else
            {
                downmovment(next_state);
                States.add(next_state);

            }
           for(int i=0;i<States.size();i++){
               States.get(i).step="down";
           }
            return States;
        }
        return null;
    }


    public void recive_order(int index,state next_state){
        if(next_state.orders_p[index]==false)
        {
            next_state.orders_p[index]=true;
            next_state.Action="receive";
            next_state.weight++;
            next_state.cost = cost+1;
        }
    }

    public void send_order(int index,state next_state){
        if(next_state.orders_p[index]==true && next_state.orders_D[index]==false)
        {
            next_state.orders_D[index]=true;
            next_state.Action="send";
            next_state.weight--;
            next_state.cost = cost-1;
        }
    }

    public ArrayList<state> children_generation() {
        ArrayList<state> all=new ArrayList<>();
        ArrayList<state> up=new ArrayList<>();
        ArrayList<state> down=new ArrayList<>();
        ArrayList<state> left=new ArrayList<>();
        ArrayList<state> right=new ArrayList<>();
        up=this.moveup();
        if(up!=null)
        {
            all.addAll(up);
        }
        down=this.movedown();
        if(down!=null)
        {
           all.addAll(down);
        }
        right=this.moveright();
        if(right!=null)
        {
            all.addAll(right);
        }
        left=this.moveleft();
        if(left!=null)
        {
            all.addAll(left);
        }
        return all;
    }

    public boolean matching(state a,state  b){
        if(a.X_Viechle!=b.X_Viechle || a.Y_Viechle!=b.Y_Viechle )
        {
            return false;
        }
        for(int i=0;i<a.orders_p.length;i++)
        {

            if(a.orders_p[i]!=b.orders_p[i])
            {
                return false;
            }
        }
        for(int i=0;i<a.orders_D.length;i++)
        {

            if(a.orders_D[i]!=b.orders_D[i])
            {
                return false;
            }
        }
    return true;
    }
    public boolean endGame(state gameState) {
        for(int i=0;i<gameState.orders_D.length;i++)
        {

            if(gameState.orders_D[i]==false)
            {
                return false;
            }
        }

        if(gameState.X_Viechle!=gameState.insialX || gameState.Y_Viechle!=gameState.insialY)
        {
            return  false;
        }
        System.out.println("end game");
        return true;

    }
}
