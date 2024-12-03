import java.util.Scanner;

/*
 * I/P:
 * Size, Array, Number of queries, Queries
 *
 * O/P:
 * Tell us how many times a particular query appears
 *
 */
public class Hashing {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int size = scanner.nextInt();
        int[] arr = new int[size];
        for (int i = 0; i < size; i++) {
            arr[i] = scanner.nextInt();
        }

        // Precompute
        int[] hash = new int[13];

        for (int i = 0; i < size; i++) {
            hash[arr[i]] += 1;
        }

        int q = scanner.nextInt();

        while (q != 0) {
            int number = scanner.nextInt();
            System.out.println(hash[number]);
            q--;
        }
    }
}
