package Basics;

import java.util.Scanner;

public class Sum_Of_First_N_Num {
    public static void main(String[] args) {
        int sum = 0;
        Scanner s = new Scanner(System.in);
        int n = s.nextInt();
        sumOfFirstN1(n, sum);

    }

    //Parameterized Recursion
    private static void sumOfFirstN1(int n, int sum) {
        if (n == 0) {
            System.out.println(sum);
            return;
        }
        sumOfFirstN1(n - 1, sum + n);
    }

    //Functional Recursion

}

