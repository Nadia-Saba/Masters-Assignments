package com.example.aiassignment;

import java.util.*;

public class Node implements Comparable<Node>{
    public int fn; // To calculate the heuristic
    public String[][] array; // Current array
    public int level; //level of the state it is in
    PriorityQueue<Node> priorityQueue= new PriorityQueue<Node>();
    static int inputValue=0;
    Set<Node> set=new HashSet<Node>();
    public Node(String[][] initialArray, int level, int input){
        this.level=level;
        this.array = Arrays.stream(initialArray).map(String[]::clone).toArray(String[][]::new);
       Node.inputValue=input;
       if(input==1){
           // if value ==1 calculate uniform cost search
           this.fn=level;
       }
        if( input==2){
            // if input is 2 call the misplaced tile method
          this.fn= misplacedTile()+level;
        }
        else if(input==3){
            //if input was 3 calculate manhattan distance for h(n)
            //level gives g(n)
           this.fn= manhattan()+level;
        }
    }
    String[][] goal={{"1", "2", "3"},
            {"4", "5", "6"},
            {"7", "8", " "}};

    public void generalSearch(Node node,int input){
        //add currentNode to priorityQueue
        priorityQueue.add(node);
        //adding the values to the set to make sure no repeated state is added
        set.add(node);
        while(!priorityQueue.isEmpty()) {
            // poll the top of priority queue
            Node currentNode=priorityQueue.poll();
            set.add(currentNode);
            resultPrint(currentNode);
            if (Arrays.deepEquals(currentNode.array, goal)) {
                System.out.println("Goal state is reached");
                break;
            }
            // each node polled will be checked for children. i.e each node is expanded and non repeated states are added
            //to the queue for moving further down.
            currentNode.expand(currentNode,set,priorityQueue);
        }
        for( Node node1 :set) {
            resultPrint(node1);
            System.out.println("f(n) :" + node1.fn);
            System.out.println("h(n) :" + (node1.fn - node1.level));
            System.out.println("g(n) :" + (node1.level));
        }
        System.out.println("Set size "+ set.size());
    }

    public void resultPrint( Node currentNode){
        int N = this.array.length;
        for (int l = 0; l < N; l++) {
            for (int m = 0; m < N; m++) {
                System.out.print(currentNode.array[l][m] + "\t");
            }
            System.out.println();
        }
    }
 public void expand(Node currentNode, Set<Node>set,PriorityQueue<Node> priorityQueue){
     int N = this.array.length;
     for (int i=0; i< N; i++)
     {
         for (int j = 0; j<N; j++)
         {
             if (currentNode.array[i][j].trim().isEmpty()) //search for the index of space in the state(where a tile can be moved)
             {
                 if(i-1>=0)			//checks weather a tile can be moved towards the top.
                 {
                     String[][] node =Arrays.stream(currentNode.array).map(String[]::clone).toArray(String[][]::new);
                     node = swap(node,i,j,i-1,j);
                     Node newNode = new Node(node,currentNode.level+1,Node.inputValue);
                         priorityQueue.add(newNode);
                 }
                 if(j-1>=0)			//checks weather a tile can be moved towards left of the space.
                 {
                     String[][] node =Arrays.stream(currentNode.array).map(String[]::clone).toArray(String[][]::new);
                     node = swap(node,i,j,i,j-1);
                     Node newNode = new Node(node,currentNode.level+1,Node.inputValue);
                         priorityQueue.add(newNode);

                 }
                 if(i+1<N)			//checks weather a tile can be moved towards downward.
                 {
                     String[][] node =Arrays.stream(currentNode.array).map(String[]::clone).toArray(String[][]::new);
                     node = swap(node,i,j,i+1,j);
                     Node newNode = new Node(node,currentNode.level+1,Node.inputValue);
                         priorityQueue.add(newNode);
                 }
                 if(j+1<N)			//checks weather a tile can be moved towards right side.
                 {
                     String[][] node =Arrays.stream(currentNode.array).map(String[]::clone).toArray(String[][]::new);
                     node = swap(node,i,j,i,j+1);
                     Node newNode = new Node(node,currentNode.level+1,Node.inputValue);
                         priorityQueue.add(newNode);
                 }
             }
         }
     }
 }
    private String[][] swap(String[][] a,int row1, int col1, int row2, int col2)
    {
        String[][] copyOfArray = a;
        String temp = copyOfArray[row1][col1];
        copyOfArray[row1][col1] = copyOfArray[row2][col2];
        copyOfArray[row2][col2] = temp;

        return copyOfArray;
    }
    private int misplacedTile()
    {
        int sum=0;
        for (int i = 0;i<3;i++)
        {
            for (int j = 0; j<3; j++)
            {
                if(this.array[i][j].trim().isEmpty())
                {
                    continue;
                }
                if(this.array[i][j] != goal[i][j])
                    sum+=1;
            }
        }
        return sum;
    }
    private int manhattan()
    {
        int sum=0;
        int[] index= new int[2];
        for (int i = 0;i<3;i++)
        {
            for (int j = 0; j<3; j++)
            {
                if(this.array[i][j].trim().isEmpty())
                {
                    continue;
                }
                index = find_index(Integer.parseInt(this.array[i][j]));
                sum = sum + (Math.abs(i-index[0])+Math.abs(j-index[1]));
            }
        }
        return sum;
    }
    private int[] find_index(int a)
    {
        int[] index = new int[2];
        for (int i = 0;i<3; i++)
        {
            for (int j = 0; j<3; j++)
            {
                if(goal[i][j].trim().isEmpty())
                {
                    continue;
                }
                if (goal[i][j].trim().equals(String.valueOf(a)))
                {
                    index[0]=i;
                    index[1]=j;
                    return index;
                }
            }
        }
        return index;
    }
    @Override
    public int compareTo(Node o) {
        if(this.fn==o.fn)
        {
            return ((this.manhattan() - o.manhattan()));
        }
        return this.fn-o.fn;
    }

 }


