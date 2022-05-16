package com.example.aiassignment;
import java.util.Scanner;

public class Search {
    public static void main(String args[]){
        int row=3;
        int column=3;
        System.out.println("Hi!! This is the 8-puzzle. Please enter the number to select the heuristic.");
        System.out.println("Press 1: Uniform Cost Search");
        System.out.println("Press 2: A* with Misplaced tile heuristic");
        System.out.println("Press 3: A* with Manhattan distance heuristic");
        Scanner scanner = new Scanner(System.in);
        int input= scanner.nextInt();
        System.out.println("Please input the elements for initial state :");
        String initialArray[][]=new String[row][column];
        for(int i=0;i<row;i++){
            for (int j=0;j<column;j++){
                int val=scanner.nextInt();
                initialArray[i][j]=val+"";
                if(val == 0)
                {
                    initialArray[i][j] = " "; // if input is 0 take is as blank
                }
            }
        }

        long start = System.currentTimeMillis();
        Node node=new Node(initialArray,0,input);
        node.generalSearch(node,input);
        long end = System.currentTimeMillis();
        System.out.println("Time taken to execute " +
                (end - start) + "ms");
    }
    }


