import java.util.Random;

public class GraphGenerator {
    public static void main(String[] args) {
        if (args.length < 2) {
            System.out.println("Trebuie introduse 2 argumente: n si k");
            return;
        }

        int n = Integer.parseInt(args[0]);
        int k = Integer.parseInt(args[1]);

        if (k > n) {
            System.out.println("k trebuie sa fie mai mic sau egal cu n");
            return;
        }

        long startTime = System.currentTimeMillis();

        int[][] matrice = generateRandomGraph(n, k);
        if (n <= 30_000) {
            printMatrix(matrice);
        }

        int m = NEdges(matrice);
        int[] degrees = CDegrees(matrice);
        int maxDegree = Gmax(degrees);
        int minDegree = Gmin(degrees);

        System.out.println("Nr edges (m): " + m);
        System.out.println("Max degree (Δ(G)): " + maxDegree);
        System.out.println("Min degree (δ(G)): " + minDegree);
        System.out.println("Suma degrees: " + suma(degrees));
        System.out.println("2 * m: " + (2 * m));

        if (n > 30_000) {
            long endTime = System.currentTimeMillis();
            System.out.println("timp exec: " + (endTime - startTime) + " ms");
        }
    }

    private static int[][] generateRandomGraph(int n, int k) {
        int[][] matrice = new int[n][n];
        Random random = new Random();

        for (int i = 0; i < k; i++) {
            for (int j = i + 1; j < k; j++) {
                matrice[i][j] = 1;
                matrice[j][i] = 1;
            }
        }

        for (int i = 0; i < k; i++) {
            for (int j = k; j < n; j++) {
                matrice[i][j] = random.nextInt(2);
                matrice[j][i] = matrice[i][j];
            }
        }

        for (int i = k; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                matrice[i][j] = random.nextInt(2);
                matrice[j][i] = matrice[i][j];
            }
        }

        return matrice;
    }

    private static void printMatrix(int[][] matrice) {
        for (int[] row : matrice) {
            for (int cell : row) {
                System.out.print(cell == 1 ? "■ " : "□ ");
            }
            System.out.println();
        }
    }

    private static int NEdges(int[][] matrix) {
        int count = 0;
        for (int i = 0; i < matrix.length; i++) {
            for (int j = i + 1; j < matrix[i].length; j++) {
                if (matrix[i][j] == 1) {
                    count++;
                }
            }
        }
        return count;
    }

    private static int[] CDegrees(int[][] matrix) {
        int[] degrees = new int[matrix.length];
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                if (matrix[i][j] == 1) {
                    degrees[i]++;
                }
            }
        }
        return degrees;
    }

    private static int Gmax(int[] array) {
        int max = array[0];
        for (int val : array) {
            if (val > max) {
                max = val;
            }
        }
        return max;
    }

    private static int Gmin(int[] array) {
        int min = array[0];
        for (int val : array) {
            if (val < min) {
                min = val;
            }
        }
        return min;
    }

    private static int suma(int[] array) {
        int suma = 0;
        for (int val : array) {
            suma = suma + val;
        }
        return suma;
    }
}