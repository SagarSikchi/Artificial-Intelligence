/*
 * AI Lab Assignment 2: 8-Puzzle Game Implementation using A* Algorithm
 * Name: Sagar Sikchi
 * Roll No.: 65
 * GR No.: 11810844
 */

import java.util.Scanner;
import java.util.ArrayList;

public class __EightPuzzle_aStar__ {
    public static int possibleMoves = 0;
    public static int[][] matrix = new int[4][9];
    public static ArrayList<Node> HeadNodes = new ArrayList<>();
    public static ArrayList<Node> PossibleNodes = new ArrayList<>();

    static class Node {
        int[] current_ = new int[9];
        float F_value;
        float G_value;
        float H_value;
        Node father;
        Node next;

        Node(int[] c_vector, float G, float H) {
            for (int i = 0; i < 9; i++) {
                current_[i] = c_vector[i];
            }
            // current_ = c_vector;
            G_value = G;
            H_value = H;
            F_value = G_value + H_value;
            father = null;
            next = null;
        }
    }

    Node OPEN;
    Node CLOSE;

    public static ArrayList<Node> PossibleNodesGenerator(int[][] matrix, Node BestNode, int[] Gstate) {
        ArrayList<Node> temp = new ArrayList<>();
        for (int i = 0; i < possibleMoves; i++) {
            Node newNode = new Node(matrix[i], BestNode.G_value + 1, (float) euclideanDistance(matrix[i], Gstate));
            newNode.father = BestNode;
            temp.add(newNode);
        }
        return temp;
    }

    public static __EightPuzzle_aStar__.Node Insert_In_the_LIST(__EightPuzzle_aStar__.Node hNode, Node curNode) {
        if (hNode == null) {
            // hNode = new Node(curNode.current_, curNode.G_value, curNode.H_value);
            // hNode.father = curNode.father;
            // hNode.next = curNode.next;
            hNode = curNode;
            // System.out.println("Check hNode is null");
        } else if (curNode.F_value < hNode.F_value) {
            curNode.next = hNode;
            hNode = curNode;
            // System.out.println("Check add on 1st");
        } else {
            Node current = hNode;
            Node prev = hNode;
            while (current != null) {
                if (curNode.F_value < current.F_value)
                    break;
                prev = current;
                current = current.next;
            }
            curNode.next = prev.next;
            prev.next = curNode;
            // System.out.println("Check add in middle/at last");
        }
        return hNode;
    }

    public static boolean areSame(Node _node1, Node _node2) {
        for (int i = 0; i < 9; i++) {
            if (_node1.current_[i] != _node2.current_[i]) {
                return false;
            }
        }
        return true;
    }

    public static __EightPuzzle_aStar__.Node supportFunction(__EightPuzzle_aStar__.Node _Node, Node _temp, int index) {
        Node hNode = _Node;
        Node tNode = null;
        while (hNode != null) {
            if (areSame(hNode, _temp)) {
                if (hNode.F_value > _temp.F_value) {
                    // System.out.println("Check 1.1.1");
                    if (tNode != null) {
                        tNode.next = hNode.next;
                        // System.out.println("Check tNode is not null");
                    } else {
                        _Node = null;
                        // System.out.println("Check tNode is null");
                    }
                    _Node = Insert_In_the_LIST(_Node, _temp);
                    HeadNodes.set(index, _Node);
                    // System.out.println("Check 2.2.2");
                    return _Node;
                }
            }
            tNode = hNode;
            hNode = hNode.next;
        }
        return _Node;
    }

    public static void Lists_Generator(__EightPuzzle_aStar__.Node OPEN, __EightPuzzle_aStar__.Node CLOSE,
            Node curNode) {
        if (curNode.father.father != null && areSame(curNode, curNode.father.father)) {
            return;
        } else {
            OPEN = supportFunction(OPEN, curNode, 0);
            // System.out.println("Check OPEN");
            CLOSE = supportFunction(CLOSE, curNode, 1);
            // System.out.println("Check CLOSE");
            OPEN = Insert_In_the_LIST(OPEN, curNode);
            // System.out.println("Check OPEN again");
            HeadNodes.set(0, OPEN);
        }
    }

    public static void Generator(__EightPuzzle_aStar__.Node OPEN, __EightPuzzle_aStar__.Node CLOSE) {
        for (int i = 0; i < PossibleNodes.size(); i++) {
            Lists_Generator(OPEN, CLOSE, PossibleNodes.get(i));
            // System.out.println("Check 3" + i);
            OPEN = HeadNodes.get(0);
            CLOSE = HeadNodes.get(1);
        }
    }

    public static void Print(Node node) {
        for (int i = 0; i < 9; i += 3) {
            for (int j = i; j < i + 3; j++) {
                System.out.print(node.current_[j] + " | ");
            }
        }
        System.out.printf("\tH-Value: " + "%-5s", node.H_value);
        System.out.printf("\tG-Value: " + "%-5s", node.G_value);
        System.out.printf("\tF-Value: " + node.F_value);
        System.out.println();
    }

    public static void PrintList(Node BestNode) {
        if (BestNode == null) {
            System.out.println("Start the Game...\n");
            return;
        } else {
            PrintList(BestNode.father);
            Print(BestNode);
            System.out.println(
                    "------------------------------------------------------------------------------------------------------");
            return;
        }
    }

    public static boolean validation(int[][] table) {
        boolean isZero = true;
        int _Sum = 0, _Product = 1;
        for (int i = 0; i < table.length; i++) {
            for (int j = 0; j < table[i].length; j++) {
                if (table[i][j] == 0 && isZero) {
                    isZero = false;
                } else if (table[i][j] > 0 && table[i][j] < 9) {
                    _Sum += table[i][j];
                    _Product *= table[i][j];
                } else {
                    return false;
                }
            }
        }
        if (_Sum != 36 || _Product != 40320) {
            return false;
        }
        return true;
    }

    public static int[] mode(int[][] table) {
        ArrayList<Integer> list = new ArrayList<Integer>();
        for (int i = 0; i < table.length; i++) {
            for (int j = 0; j < table[i].length; j++) {
                list.add(table[i][j]);
            }
        }
        int[] vector = new int[list.size()];
        for (int i = 0; i < vector.length; i++) {
            vector[i] = list.get(i);
        }
        return vector;
    }

    public static double euclideanDistance(int[] cState, int[] gState) {
        double eDistance = 0;
        for (int i = 0; i < 9; i++) {
            eDistance = eDistance + Math.pow(cState[i] - gState[i], 2);
        }
        return Math.sqrt(eDistance);
    }

    public static int[][] possibleMoveGenerator(int[] vector, int[][] matrix) {
        int indexOfGap = 0;
        ArrayList<Integer> PossibleMoves = new ArrayList<Integer>();
        for (int i = 0; i < vector.length; i++) {
            if (vector[i] == 0) {
                switch (i) {
                    case 0:
                        PossibleMoves.add(1);
                        PossibleMoves.add(3);
                        break;
                    case 1:
                        PossibleMoves.add(0);
                        PossibleMoves.add(2);
                        PossibleMoves.add(4);
                        break;
                    case 2:
                        PossibleMoves.add(1);
                        PossibleMoves.add(5);
                        break;
                    case 3:
                        PossibleMoves.add(0);
                        PossibleMoves.add(4);
                        PossibleMoves.add(6);
                        break;
                    case 4:
                        PossibleMoves.add(1);
                        PossibleMoves.add(3);
                        PossibleMoves.add(5);
                        PossibleMoves.add(7);
                        break;
                    case 5:
                        PossibleMoves.add(2);
                        PossibleMoves.add(4);
                        PossibleMoves.add(8);
                        break;
                    case 6:
                        PossibleMoves.add(3);
                        PossibleMoves.add(7);
                        break;
                    case 7:
                        PossibleMoves.add(4);
                        PossibleMoves.add(6);
                        PossibleMoves.add(8);
                        break;
                    case 8:
                        PossibleMoves.add(5);
                        PossibleMoves.add(7);
                        break;
                }
                possibleMoves = PossibleMoves.size();
                indexOfGap = i;
                break;
            }
        }
        for (int i = 0; i < possibleMoves; i++) {
            for (int j = 0; j < matrix[0].length; j++) {
                matrix[i][j] = vector[j];
            }
            int temp = matrix[i][indexOfGap];
            matrix[i][indexOfGap] = matrix[0][PossibleMoves.get(i)];
            matrix[i][PossibleMoves.get(i)] = temp;
        }
        return matrix;
    }

    public static void main(String[] args) {
        __EightPuzzle_aStar__ node = new __EightPuzzle_aStar__();
        final int[][] currentState = new int[3][3];
        int[] C_vector = new int[9];
        int[] G_vector = { 1, 2, 3, 8, 0, 4, 7, 6, 5 };
        boolean valid;
        Scanner scanner = new Scanner(System.in);
        System.out.println("\n##################### 8 - Puzzle Problem #####################\n");
        System.out.println("The Goal State is as follows:\n");
        for (int i = 0; i < 9; i += 3) {
            for (int j = i; j < i + 3; j++) {
                System.out.print(G_vector[j] + " | ");
            }
            System.out.println("\n------------");
        }
        System.out.println("\nEnter the Current State --> (Numbers from 0 to 9; 0 for Gap)\n");
        while (true) {
            for (int i = 0; i < 3; i++) {
                for (int j = 0; j < 3; j++) {
                    currentState[i][j] = scanner.nextInt();
                }
            }
            valid = validation(currentState);
            if (valid) {
                break;
            } else {
                System.out.println("Invalid Current State! Enter Again.\n");
            }
        }
        System.out.println("\n################################################################\n");
        C_vector = mode(currentState);

        HeadNodes.add(node.OPEN);
        HeadNodes.add(node.CLOSE);
        Node I_NODE = new Node(C_vector, 0, (float) euclideanDistance(C_vector, G_vector));
        // System.out.println("Check 01");

        node.OPEN = Insert_In_the_LIST(node.OPEN, I_NODE);
        HeadNodes.set(0, node.OPEN);
        // System.out.println("Check 02");

        Node BestNode = node.OPEN;
        node.OPEN = BestNode.next;
        BestNode.next = null;

        HeadNodes.set(0, node.OPEN);
        // System.out.println("Check 03");

        node.CLOSE = Insert_In_the_LIST(node.CLOSE, BestNode);
        HeadNodes.set(1, node.CLOSE);
        // System.out.println("Check 04");

        int counter = 1;

        while (BestNode.H_value != 0 && counter < 150) {
            // System.out.println(counter);
            matrix = possibleMoveGenerator(BestNode.current_, matrix);
            // System.out.println("Check 1");
            PossibleNodes = PossibleNodesGenerator(matrix, BestNode, G_vector);
            // System.out.println("check 2");
            Generator(node.OPEN, node.CLOSE);
            node.OPEN = HeadNodes.get(0);
            node.CLOSE = HeadNodes.get(1);

            BestNode = node.OPEN;
            node.OPEN = BestNode.next;
            BestNode.next = null;
            HeadNodes.set(0, node.OPEN);
            // System.out.println("Check 4");

            node.CLOSE = Insert_In_the_LIST(node.CLOSE, BestNode);
            HeadNodes.set(1, node.CLOSE);
            // System.out.println("Check 5");
            counter++;
            if (node.OPEN == null || node.CLOSE == null && counter > 1) {
                System.out.println("Path Cannot be found!");
                break;
            }
        }
        System.out.println();
        if (counter < 150) {
            PrintList(BestNode);
        } else {
            System.out.println("Path not found within 150 moves!");
            System.out.println("Some Initial moves are --->\n");
            PrintList(BestNode);
        }

        System.out.println("\n\n########################## Thank You! ##########################\n");
        scanner.close();
    }
}
