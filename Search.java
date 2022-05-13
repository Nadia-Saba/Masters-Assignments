package com.example.aiassignment;
import java.util.Scanner;

public class Search {
    public static void main(String args[]){
        System.out.println("Hi!! This is the 8-puzzle. Please enter the number to select the heuristic.");
        System.out.println("Press 1: Uniform Cost Search");
        System.out.println("Press 2: A* with Misplaced tile heuristic");
        System.out.println("Press 3: A* with Manhattan distance heuristic");
        Scanner scanner = new Scanner(System.in);
        int input= scanner.nextInt();
        //System.out.println("Please input the elements for initial state :");
        //String initialArray[][]= new String[3][3];
        String[][] initialArray={{"1", "2", "3"},
                {"4", " ", "6"},
                {"7", "5", "8"}};
        /*Scanner sc=new Scanner(System.in);
        for (int i=0;i<initialArray.length;i++)
        {
            for(int j=0;j<initialArray.length;j++)
            {
                initialArray[i][j] = sc.nextLine();
                if(initialArray[i][j].length()!=1 || (initialArray[i][j].charAt(0)<'1' && initialArray[i][j].charAt(0)!=' ') || initialArray[i][j].charAt(0)>'8')
                {
                    System.out.println("Error: Number should be between 1 to 8");
                    return;
                }
            }
        }*/

        Node node=new Node(initialArray,0,input);
        node.generalSearch(node,input);
        }
    }


