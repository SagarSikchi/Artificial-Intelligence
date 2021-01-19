public class SPL {
    private static int learning_rate_C = 1;
    private static int learning_signal_R = 0;
    private static float[] delta_w = new float[4];
    private static int[] desired_output_dk = new int[] { 1, -1, 1 };
    private static int[] actual_output_ok = new int[3];

    private static float net_summation(float[] input_vector, float[] weight_vector) {
        float net = 0F;
        for (int i = 0; i < input_vector.length; i++) {
            net += (float) input_vector[i] * weight_vector[i];
        }
        return net;
    }

    private static int fnet(float net) {
        if (net < 0f)
            return -1;
        else
            return 1;
    }

    private static void printWeights(float[][] weights) {
        System.out.println("Printing the weight vectors:\n");
        for (int i = 0; i < 4; i++) {
            System.out.print("W" + (i + 1) + " ----->\t");
            for (int j = 0; j < 4; j++) {
                System.out.print(weights[i][j] + "\t");
            }
            if (i < 3) {
                System.out
                        .print("\tDesired Output: " + desired_output_dk[i] + "\tActual Output: " + actual_output_ok[i]);
            }
            System.out.println();
        }
    }

    private static float[][] update_weights(float[][] inputs, float[][] weights) {
        int allCorrect = 0;
        int EPOCHs = 0;
        while (allCorrect != 3) {
            allCorrect = 0;
            for (int counter = 0; counter < 3; counter++) {
                float net = net_summation(inputs[counter], weights[counter]);
                actual_output_ok[counter] = fnet(net);
                learning_signal_R = desired_output_dk[counter] - actual_output_ok[counter];
                // System.out.println(learning_signal_R);
                for (int i = 0; i < 4; i++) {
                    delta_w[i] = (float) learning_rate_C * learning_signal_R * inputs[counter][i];
                }
                for (int i = 0; i < 4; i++) {
                    weights[counter + 1][i] = weights[counter][i] + delta_w[i];
                }
                if (learning_signal_R == 0F)
                    allCorrect++;
            }
            EPOCHs++;
            System.out.println("Number of EPOCHs: " + EPOCHs);
            printWeights(weights);
            weights[0] = weights[3]; // update initial weight vector for next EPOCH
            System.out.println(
                    "----------------------------------------------------------------------------------------------------");
        }
        return weights;
    }

    public static void main(String[] args) {
        System.out.println("\n################### Single Perceptron Learning ###################\n");
        float[][] inputs = new float[][] { { 1, -2, 1.5f, 0 }, { 1, -0.5f, -2, -1.5f }, { 0, 1, -1, 1.5f } };
        float[] initial_weight = new float[] { 1, -1, 0, 0.5f };
        float[][] weights = new float[4][4];
        weights[0] = initial_weight;
        System.out.println("Given Data:\n");
        System.out.println("Learning Rate (C): " + learning_rate_C);
        System.out.print("\nInput Vectors:\n");
        for (int i = 0; i < 3; i++) {
            System.out.print("X" + (i + 1) + " ----->\t");
            for (int j = 0; j < 4; j++) {
                System.out.print(inputs[i][j] + "\t");
            }
            System.out.print("\tDesired Output: " + desired_output_dk[i]);
            System.out.println();
        }
        System.out.println("\nInitial Weight Vector:");
        System.out.print("W1 ----->\t" + initial_weight[0] + "\t" + initial_weight[1] + "\t" + initial_weight[2] + "\t"
                + initial_weight[3]);
        System.out.println(
                "\n----------------------------------------------------------------------------------------------------");
        System.out.println("\n\nWeight Vectors are:\n");
        weights = update_weights(inputs, weights);
        // printWeights(weights);
        System.out.println("\n################### Thank You ###################\n\n");
    }
}