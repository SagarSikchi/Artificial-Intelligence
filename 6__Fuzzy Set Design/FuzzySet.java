/*
 * Lab6: Design a Fuzzy Set for numbers close to 5
 * Name: Sagar Sikchi
 */

public class FuzzySet {
    private static int senitive_parameter_GAMMA;
    private static float[] INPUT_X = new float[]{0.5f, 1.5f, 2.5f, 3.5f, 4.5f, 5.0f, 6.5f, 7.5f, 8.5f, 9.5f};
    // private static float[] INPUT_X = new float[]{0.2f, 1.2f, 2.2f, 3.2f, 4.2f, 5.2f, 6.2f, 7.2f, 8.2f, 9.2f};
    private static float[][] MEMBERSHIP = new float[4][10];

    private static void printMemberships() {
        for(int i = 0; i < MEMBERSHIP.length; i++) {
            System.out.print("GAMMA: " + (i + 1) + ",  MEMBERSHIP:  ");
            for(int j = 0; j < MEMBERSHIP[0].length; j++) {
                System.out.printf("%-13f", MEMBERSHIP[i][j]);
            }
            System.out.println();
        }
    }

    private static float calculate_modValue(float x) {
        float a = 5 / Math.max(x, 5);
        float b = x / Math.max(x, 5);
        return senitive_parameter_GAMMA * (float) Math.abs(a - b);
    }

    private static float calculate_membership(float modValue) {
        float membershipOf_x = 0f;
        if(modValue == 0) {
            membershipOf_x = 1;
        } else if(modValue > 0 && modValue <= 1) {
            membershipOf_x = 1 - modValue;
        } else if(modValue > 1) {
            membershipOf_x = 0;
        }
        return membershipOf_x;
    }
    public static void main(String[] args) {
        System.out.println("\n######################### Fuzzy Set #########################\n");
        System.out.print("The 10 Input X's are (in between (0, 10)):  ");
        for(int i = 0; i < 10; i++) {
            System.out.print(INPUT_X[i] + "  ");
        }
        System.out.println("\n\nMEMBERSHIPS ARE:\n");
        for(senitive_parameter_GAMMA = 1; senitive_parameter_GAMMA < 5; senitive_parameter_GAMMA++) {
            for(int i = 0; i < 10; i++) {
                MEMBERSHIP[senitive_parameter_GAMMA-1][i] = calculate_membership(calculate_modValue(INPUT_X[i]));
            }
        }
        printMemberships();
        System.out.println("\n######################### Thank You #########################\n");
    }
}
