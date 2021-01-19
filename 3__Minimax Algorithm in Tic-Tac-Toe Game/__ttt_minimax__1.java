/*
 * AI Lab Assignment 3: Tic Tac Toe Implementation with Minimax Algorithm
 * Name: Sagar Sikchi
 * Roll No.: 65
 * GR No.: 11810844
 */

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Scanner;
import java.util.Set;
import java.util.Map.Entry;

public class __ttt_minimax__1 {
    public static int countBlank = 0;
    public static int[][] matrix = new int[9][10]; // intermediate - 2D matrix
    public static int initial_score;
    public static int case1, case2, case3, case4;

    public static HashMap<String, HashMap<String, Integer>> MINIMAX_MAP = new HashMap<String, HashMap<String, Integer>>();

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

    public static String ID_ENCODER(int[] array) {
        String id = "";
        for (int i = 0; i < array.length; i++) {
            id += String.valueOf(array[i]);
        }
        return id;
    }

    public static int[] ID_DECODER(String id) {
        int[] array = new int[id.length()];
        for (int i = 0; i < id.length(); i++) {
            array[i] = Integer.parseInt(String.valueOf(id.charAt(i)));
        }
        return array;
    }

    public static HashMap<String, Integer> SupportFunction(int[] board, int turnSymbol) {
        initial_score = (turnSymbol == 1) ? Integer.MIN_VALUE : Integer.MAX_VALUE;
        possibleMoveGenerator(board, turnSymbol);
        HashMap<String, Integer> map = new HashMap<>();
        for (int i = 0; i < countBlank; i++) {
            map.put(ID_ENCODER(matrix[i]), initial_score);
        }
        return map;
    }

    public static Set<String> MINIMAX_ALGORITHM(int[] initial_board, int turnSymbol, int opponentSymbol) {
        int result_score = 0;
        // int depth = 0;
        // int factor = 1;
        int counter = 0;
        // int initial_score = (turnSymbol == 1) ? Integer.MIN_VALUE :
        // Integer.MAX_VALUE;
        // matrix = possibleMoveGenerator(matrix, initial_board, turnSymbol);
        // HashMap<String, Integer> map = new HashMap<>();
        // for (int i = 0; i < countBlank; i++) {
        // map.put(ID_ENCODER(matrix[i]), initial_score);
        // }
        MINIMAX_MAP.put("L0", SupportFunction(initial_board, turnSymbol));

        // depth += 1;
        // factor *= 10;
        counter = 0;
        // initial_score = (opponentSymbol == 1) ? Integer.MIN_VALUE :
        // Integer.MAX_VALUE;
        for (String ID : MINIMAX_MAP.get("L0").keySet()) {
            int[] board = ID_DECODER(ID);
            // matrix = possibleMoveGenerator(matrix, board, opponentSymbol);
            // HashMap<String, Integer> map_ = new HashMap<>();
            // for (int i = 0; i < countBlank; i++) {
            // map_.put(ID_ENCODER(matrix[i]), initial_score);
            // }
            MINIMAX_MAP.put("L1" + String.valueOf(counter),
                    nodeEvaluator(opponentSymbol, turnSymbol, SupportFunction(board, opponentSymbol)));
            // result_score = (turnSymbol == 1) ? Collections.min(MINIMAX_MAP.get("L1" + String.valueOf(counter)).values())
            //         : Collections.max(MINIMAX_MAP.get("L1" + String.valueOf(counter)).values());

            // updated statement -->
            result_score = (turnSymbol == 1) ? Collections.max(MINIMAX_MAP.get("L1" + String.valueOf(counter)).values())
                    : Collections.min(MINIMAX_MAP.get("L1" + String.valueOf(counter)).values());
            MINIMAX_MAP.get("L0").put(ID, result_score);
            counter++;
        }
        result_score = (turnSymbol == 1) ? Collections.min(MINIMAX_MAP.get("L0").values())
                : Collections.max(MINIMAX_MAP.get("L0").values());
        return getKeys(MINIMAX_MAP.get("L0"), result_score);

        // updated statement -->
        // result_score = (turnSymbol == 1) ? Collections.max(MINIMAX_MAP.get("L0").values())
        //         : Collections.min(MINIMAX_MAP.get("L0").values());
        // return getKeys(MINIMAX_MAP.get("L0"), result_score);
    }

    public static <K, V> Set<K> getKeys(HashMap<K, V> map, V value) {
        Set<K> keys = new HashSet<>();
        for (Entry<K, V> entry : map.entrySet()) {
            if (entry.getValue().equals(value)) {
                keys.add(entry.getKey());
            }
        }
        return keys;
    }

    public static void PrintStructure(int turnSymbol, int opponentSymbol) {
        List<String> keys = new ArrayList<>();
        for (String key : MINIMAX_MAP.keySet()) {
            keys.add(key);
        }
        Collections.sort(keys);
        for (String key : keys) {
            String s = ((key.equals("L0") ? "Level: 1\t\tWho's turn: " + turnSymbol
                    : "Level: 2\t\tWho's turn: " + opponentSymbol) + "\t\tKey: " + key);
            System.out.println(s + "\n");
            for (String ID : MINIMAX_MAP.get(key).keySet()) {
                Print(ID, key);
            }
            System.out.println("\n\n");
        }
    }

    public static void PrintBestStructure(Set<String> keys) {
        for (String ID : keys) {
            Print(ID, "L0");
        }
    }

    private static void Print(String ID, String key) {
        int[] board = ID_DECODER(ID);
        int score = MINIMAX_MAP.get(key).get(ID);
        System.out.print("Board: ");
        for (int i = 0; i < board.length - 1; i++) {
            System.out.print(((board[i] == 9) ? 0 : board[i]) + " | ");
        }
        System.out.println("\tScore: " + score + "\tBoard Index: " + board[board.length - 1]);
    }

    public static int[] mode(int[][] table) {
        ArrayList<Integer> list = new ArrayList<Integer>();
        for (int i = 0; i < table.length; i++) {
            for (int j = 0; j < table[0].length; j++) {
                list.add(table[i][j]);
            }
        }
        int[] vector = new int[list.size() + 1];
        for (int i = 0; i < list.size(); i++) {
            vector[i] = list.get(i);
        }
        vector[list.size()] = 0;
        return vector;
    }

    public static int indexOfstate(int[] table) {
        int index = 0;
        for (int i = 0; i < table.length; i++) {
            index += table[i] * Math.pow(3, 8 - i);
        }
        return index;
    }

    public static HashMap<String, Integer> nodeEvaluator(int turnSymbol, int opponentSymbol,
            HashMap<String, Integer> map) {
        int indexofZero = -1;
        int winning = 3 * turnSymbol;
        int blocking = 2 * opponentSymbol + turnSymbol;

        for (int i = 0; i < countBlank; i++) {
            int score = initial_score;
            indexofZero = matrix[i][matrix[0].length - 1];
            int x = matrix[i][indexofZero];
            switch (indexofZero) {
                case 0:
                    case1 = matrix[i][1] + matrix[i][2] + x;
                    case2 = matrix[i][3] + matrix[i][6] + x;
                    case3 = matrix[i][4] + matrix[i][8] + x;
                    break;

                case 1:
                    case1 = matrix[i][0] + matrix[i][2] + x;
                    case2 = matrix[i][4] + matrix[i][7] + x;
                    break;

                case 2:
                    case1 = matrix[i][0] + matrix[i][1] + x;
                    case2 = matrix[i][5] + matrix[i][8] + x;
                    case3 = matrix[i][4] + matrix[i][6] + x;
                    break;

                case 3:
                    case1 = matrix[i][0] + matrix[i][6] + x;
                    case2 = matrix[i][4] + matrix[i][5] + x;
                    break;

                case 4:
                    case1 = matrix[i][3] + matrix[i][5] + x;
                    case2 = matrix[i][1] + matrix[i][7] + x;
                    case3 = matrix[i][0] + matrix[i][8] + x;
                    case4 = matrix[i][2] + matrix[i][6] + x;
                    break;

                case 5:
                    case1 = matrix[i][2] + matrix[i][8] + x;
                    case2 = matrix[i][3] + matrix[i][4] + x;
                    break;

                case 6:
                    case1 = matrix[i][0] + matrix[i][3] + x;
                    case2 = matrix[i][7] + matrix[i][8] + x;
                    case3 = matrix[i][4] + matrix[i][2] + x;
                    break;

                case 7:
                    case1 = matrix[i][6] + matrix[i][8] + x;
                    case2 = matrix[i][1] + matrix[i][4] + x;
                    break;

                case 8:
                    case1 = matrix[i][6] + matrix[i][7] + x;
                    case2 = matrix[i][2] + matrix[i][5] + x;
                    case3 = matrix[i][0] + matrix[i][4] + x;
                    break;
            }

            // for corner cells
            if (indexofZero == 0 || indexofZero == 2 || indexofZero == 6 || indexofZero == 8) {
                if (case1 == winning || case2 == winning || case3 == winning) {
                    score = __ttt_minimax__1.POINTS.WIN.value;
                } else if (case1 == blocking || case2 == blocking || case3 == blocking) {
                    score = __ttt_minimax__1.POINTS.BLOCK.value;
                } else {
                    score = __ttt_minimax__1.POINTS.CORNER.value;
                }
            }

            // for edge cells
            else if (indexofZero == 1 || indexofZero == 3 || indexofZero == 5 || indexofZero == 7) {
                if (case1 == winning || case2 == winning) {
                    score = __ttt_minimax__1.POINTS.WIN.value;
                } else if (case1 == blocking || case2 == blocking) {
                    score = __ttt_minimax__1.POINTS.BLOCK.value;
                } else {
                    score = __ttt_minimax__1.POINTS.EDGE.value;
                }
            }

            // for middle cell
            else if (indexofZero == 4) {
                if (case1 == winning || case2 == winning || case3 == winning || case4 == winning) {
                    score = __ttt_minimax__1.POINTS.WIN.value;
                } else if (case1 == blocking || case2 == blocking || case3 == blocking || case4 == blocking) {
                    score = __ttt_minimax__1.POINTS.BLOCK.value;
                } else {
                    score = __ttt_minimax__1.POINTS.MIDDLE.value;
                }
            }
            if (turnSymbol == 1) {
                score *= -1;
            }
            map.put(ID_ENCODER(matrix[i]), score);
        }
        return map;
    }

    public static void possibleMoveGenerator(int[] table, int turnSymbol) {
        // reset board values
        for (int i = 0; i < table.length - 1; i++) {
            if (table[i] == 9) {
                table[i] = 0;
            }
        }
        countBlank = 0; // reset count of zeros to 0
        for (int i = 0; i < table.length - 1; i++) {
            if (table[i] == 0) {
                countBlank++;
            }
        }
        int indexofZero = -1;
        boolean isRepeat = false;
        for (int i = 0; i < countBlank; i++) {
            for (int j = 0; j < matrix[0].length - 1; j++) {
                /*
                 * Here, the blank cells having value 0 (from vector array) are mapped with
                 * value 9 res. in the intermediate 2D array for the ease of calculations in the
                 * nodeEvaluator() function
                 */
                if (table[j] == 0 && j > indexofZero && !isRepeat) {
                    matrix[i][matrix[0].length - 1] = j;
                    matrix[i][j] = turnSymbol;
                    indexofZero = j;
                    isRepeat = true;
                } else {
                    matrix[i][j] = (table[j] == 0) ? 9 : table[j];
                }
            }
            isRepeat = false;
        }
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
        int[] vector = new int[10]; // vector for 2D array - 1D array
        int index; // index of board position
        int inputSymbol; // input Symbol
        int comSymbol; // opponent's Symbol

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

        Set<String> keys = MINIMAX_ALGORITHM(vector, inputSymbol, comSymbol);

        System.out.print("Level: 0\t\tInitial Board: ");
        for (int i = 0; i < vector.length - 1; i++) {
            System.out.print(vector[i] + " | ");
        }
        System.out.println("\n");

        System.out.println("\nPossible Moves with Scores are:\n");
        PrintStructure(inputSymbol, comSymbol);
        System.out.println("\nBest Possible Moves with Scores are:\n");
        PrintBestStructure(keys);

        System.out.println("\n\n################## Thank You! ##################");
        scanner.close();
    }
}

// 2 2 1
// 1 0 2
// 0 0 1


// 2 1 0
// 1 2 1
// 0 2 0