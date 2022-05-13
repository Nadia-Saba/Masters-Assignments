package com.example.aiassignment;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.Scanner;

public class Solution {
    public static PriorityQueue<State> pq = new PriorityQueue<State>();
    public static ArrayList<State> expanded = new ArrayList<State>();
    public static String[][] goal={{"1", "2", "3"},
            {"4", "5", "6"},
            {"7", "8", "0"}};
        public static void main (String args[])
        {
            int i, j, rows, columns;
            rows = columns = 3;
            String[][] a = {{"1", "3", "2"},
                    {"5", "4", "6"},
                    {"7", "8", "0"}};
            System.out.println("Please input the elements for initial state :");
// The below code validates the input provided by the user and terminates for invalid input.
        /*for (i=0;i<a.length;i++)
        {
            for(j=0;j<a.length;j++)
            {
                a[i][j] = sc.nextLine();
                if(a[i][j].length()!=1 || (a[i][j].charAt(0)<'1' && a[i][j].charAt(0)!=' ') || a[i][j].charAt(0)>'8')
                {
                    System.out.println("Error: Input should be any number between 1 to 8 or a single space\nProgram Terminated");
                    return;
                }
            }
        }*/

            long startTime = System.currentTimeMillis();
            State state = new State(a, 0);
            pq.add(state);
            ArrayList<State> list = new ArrayList<State>();
            while (!pq.isEmpty()) {
                int visited;
                State current = pq.poll();    //returns and deletes the first node of the priority queue and store it in 'current' variable.
                expanded.add(current);            //Adds current object to the 'end' list<State> which holds all the expanded nodes
                if (Arrays.deepEquals(current.blocks, goal)) {
                    break;
                }
                list = current.expand(current); //expands the current node and the child nodes are stored in the list<State>
//Below code verify whether the node expanded is already visited by verifying in the 'end' array list
                for (State l : list) {
                    visited = 0;
                    for (State e : expanded) {
                        if (Arrays.deepEquals(l.blocks, e.blocks)) {
                            visited = 1;
                        }
                    }
                    if (visited == 1)
                        continue;
                    pq.add(l);
                }
                for (State states : expanded) {
                    for (int l = 0; l < 3; l++) {
                        for (int m = 0; m < 3; m++) {
                            System.out.print(states.blocks[l][m] + "\t");
                        }
                        System.out.println();
                    }
                    System.out.println("f(n) :" + states.f);
                    System.out.println("h(n) :" + (states.f - states.level));
                    System.out.println("g(n) :" + (states.level));
                    System.out.println('\n');
                }
                System.out.println("Total Nodes expanded :" + expanded.size());
                System.out.println("Total Nodes generated:" + (expanded.size() + pq.size()));
//Below code is responsible for calculating the total time taken for generating the nodes and display the output.
                long endTime = System.currentTimeMillis();
                System.out.println("Time Taken in milli seconds: " + (endTime - startTime));
            }
        }
    }
