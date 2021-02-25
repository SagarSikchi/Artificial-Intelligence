/*
 * AI Lab Assignment 1: Tic Tac Toe Implementation
 * Name: Sagar Sikchi
 */

import java.util.ArrayList;
import java.util.Scanner;

public class __ttt__ {
    public static int countBlank = 0;
    public static int[] scores = new int[9];
    public static int score = 0, position = 0;
    public static int case1, case2, case3, case4;

    public enum POINTS {
        WIN(50), BLOCK(25), MIDDLE(4), CORNER(3), EDGE(2);

        private final int value;

        POINTS(final int newValue) {
            value = newValue;
        }

        public int getValue() {
            return value;
        }
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

    public static int indexOfstate(int[] table) {
        int index = 0;
        for (int i = 0; i < table.length; i++) {
            index += table[i] * Math.pow(3, 8 - i);
        }
        return index;
    }

    public static int nodeEvaluator(int[][] matrix, int inputSymbol, int comSymbol) {
        int indexofZero = -1;
        int winning = 3 * inputSymbol;
        int blocking = 2 * comSymbol + inputSymbol;

        for (int i = 0; i < countBlank; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                if (matrix[i][j] == 9 && j > indexofZero) {
                    indexofZero = j;
                    switch (indexofZero) {
                        case 0:
                            case1 = matrix[i][1] + matrix[i][2];
                            case2 = matrix[i][3] + matrix[i][6];
                            case3 = matrix[i][4] + matrix[i][8];
                            break;

                        case 1:
                            case1 = matrix[i][0] + matrix[i][2];
                            case2 = matrix[i][4] + matrix[i][7];
                            break;

                        case 2:
                            case1 = matrix[i][0] + matrix[i][1];
                            case2 = matrix[i][5] + matrix[i][8];
                            case3 = matrix[i][4] + matrix[i][6];
                            break;

                        case 3:
                            case1 = matrix[i][0] + matrix[i][6];
                            case2 = matrix[i][4] + matrix[i][5];
                            break;

                        case 4:
                            case1 = matrix[i][3] + matrix[i][5];
                            case2 = matrix[i][1] + matrix[i][7];
                            case3 = matrix[i][0] + matrix[i][8];
                            case4 = matrix[i][2] + matrix[i][6];
                            break;

                        case 5:
                            case1 = matrix[i][2] + matrix[i][8];
                            case2 = matrix[i][3] + matrix[i][4];
                            break;

                        case 6:
                            case1 = matrix[i][0] + matrix[i][3];
                            case2 = matrix[i][7] + matrix[i][8];
                            case3 = matrix[i][4] + matrix[i][2];
                            break;

                        case 7:
                            case1 = matrix[i][6] + matrix[i][8];
                            case2 = matrix[i][1] + matrix[i][4];
                            break;

                        case 8:
                            case1 = matrix[i][6] + matrix[i][7];
                            case2 = matrix[i][2] + matrix[i][5];
                            case3 = matrix[i][0] + matrix[i][4];
                            break;
                    }

                    // for corner cells
                    if (indexofZero == 0 || indexofZero == 2 || indexofZero == 6 || indexofZero == 8) {
                        if ((inputSymbol + case1 == winning) || (inputSymbol + case2 == winning)
                                || (inputSymbol + case3 == winning)) {
                            score = __ttt__.POINTS.WIN.value;
                            return indexofZero;
                        } else if ((inputSymbol + case1 == blocking) || (inputSymbol + case2 == blocking)
                                || (inputSymbol + case3 == blocking)) {
                            scores[indexofZero] = __ttt__.POINTS.BLOCK.value;
                        } else {
                            scores[indexofZero] = __ttt__.POINTS.CORNER.value;
                        }
                    }

                    // for edge cells
                    else if (indexofZero == 1 || indexofZero == 3 || indexofZero == 5 || indexofZero == 7) {
                        if ((inputSymbol + case1 == winning) || (inputSymbol + case2 == winning)) {
                            score = __ttt__.POINTS.WIN.value;
                            return indexofZero;
                        } else if ((inputSymbol + case1 == blocking) || (inputSymbol + case2 == blocking)) {
                            scores[indexofZero] = __ttt__.POINTS.BLOCK.value;
                        } else {
                            scores[indexofZero] = __ttt__.POINTS.EDGE.value;
                        }
                    }

                    // for middle cell
                    else if (indexofZero == 4) {
                        if ((inputSymbol + case1 == winning) || (inputSymbol + case2 == winning)
                                || (inputSymbol + case3 == winning) || (inputSymbol + case4 == winning)) {
                            score = __ttt__.POINTS.WIN.value;
                            return indexofZero;
                        } else if ((inputSymbol + case1 == blocking) || (inputSymbol + case2 == blocking)
                                || (inputSymbol + case3 == blocking) || (inputSymbol + case4 == blocking)) {
                            scores[indexofZero] = __ttt__.POINTS.BLOCK.value;
                        } else {
                            scores[indexofZero] = __ttt__.POINTS.MIDDLE.value;
                        }
                    }
                }
            }
        }
        return indexofZero;
    }

    public static int[][] possibleMoveGenerator(int[][] matrix, int[] table) {
        for (int i = 0; i < table.length; i++) {
            if (table[i] == 0) {
                countBlank++;
            }
        }
        for (int i = 0; i < countBlank; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                /*
                 * Here, the blank cells having value 0 (from vector array) are mapped with
                 * value 9 res. in the intermediate 2D array for the ease of calculations in the
                 * nodeEvaluator() function
                 */
                matrix[i][j] = (table[j] == 0) ? 9 : table[j];
            }
        }
        return matrix;
    }

    public static int validation(int[][] table) {
        int countX = 0;
        int countO = 0;
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (table[i][j] == 0 || table[i][j] == 1 || table[i][j] == 2) {
                    if (table[i][j] == 2) {
                        countO++;
                    } else if (table[i][j] == 1) {
                        countX++;
                    }
                } else {
                    return 2;
                }
            }
        }
        return Math.abs(countO - countX);
    }

    public static void printTable(int[][] table) {
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                System.out.print(table[i][j] + " ");
            }
            System.out.println();
        }
    }

    public static void main(String[] args) {

        System.out.println("##################  Tic Tac Toe  ##################" + "\n");

        int[][] tttTable = new int[3][3]; // user input - 2D array
        int[][] matrix = new int[9][9]; // intermediate - 2D matrix
        int[] vector = new int[9]; // vector for 2D array - 1D array
        int index; // index of board position
        int inputSymbol; // input Symbol
        int comSymbol; // opponent's Symbol
        boolean isOther = true; // boolean variable

        System.out.println("Enter the Tic Tac Toe Board Position" + "\n" + "For 'Blank' ---> 0" + "\n"
                + "For 'X' -------> 1" + "\n" + "For 'O' -------> 2" + "\n");

        Scanner scanner = new Scanner(System.in);
        while (true) {
            for (int i = 0; i < 3; i++) {
                for (int j = 0; j < 3; j++) {
                    tttTable[i][j] = scanner.nextInt();
                }
            }
            int valid = validation(tttTable);
            if (valid == 0 || valid == 1) {
                break;
            } else {
                System.out.println("Invalid Input!\nEnter Again.\n\n");
            }
        }

        /* convert 2D array into 1D vector array */
        vector = mode(tttTable);

        /* calculate index */
        index = indexOfstate(vector);
        System.out.print("\nIndex of current Board Position: " + index + "\n");

        System.out.print("\nEnter who's turn next (1 for X) or (2 for O): ");
        inputSymbol = scanner.nextInt();
        comSymbol = (inputSymbol == 1) ? 2 : 1;

        /* total possible moves */
        possibleMoveGenerator(matrix, vector);
        System.out.println("\nTotal Possible moves for " + inputSymbol + " " + "are: " + String.valueOf(countBlank));

        // printTable(matrix);

        /* best possible move */
        int position = nodeEvaluator(matrix, inputSymbol, comSymbol);

        System.out.println("\nVector form of 2D input array:");
        for (int i = 0; i < vector.length; i++) {
            System.out.print(vector[i] + "   ");
        }
        System.out.println("\n");

        if (score == __ttt__.POINTS.WIN.value) {
            System.out.println("Status of move: WINNING");
            System.out.println("Best possible position (index in vector array): " + position);
        } else {
            System.out.println("Scores of possible positions:");
            for (int i = 0; i < scores.length; i++) {
                System.out.print(scores[i] + "   ");
            }
            System.out.println("\n");
            int temp;
            for (int i = 0; i < scores.length; i++) {
                temp = scores[i];
                if (temp == __ttt__.POINTS.BLOCK.value) {
                    score = temp;
                    position = i;
                    System.out.println("Status of move: BLOCKING");
                    System.out.println("Best possible position (index in vector array): " + position);
                    isOther = false;
                    break;
                } else {
                    if (temp >= score) {
                        score = temp;
                        position = i;
                    }
                }
            }
            if (isOther) {
                System.out.println("Status of move: OTHER");
                System.out.println("Best possible position (index in vector array): " + position);
            }
        }
        System.out.println("\n\n################## Thank You! ##################");
        scanner.close();
    }
}
/*
 * 1 2 0 0 0 0 1 2 2
 */
