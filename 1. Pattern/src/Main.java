public class Main {
    public static void main(String[] args) {
        pattern20(5);
    }

    //https://www.naukri.com/code360/problems/n-forest_6570177
    public static void pattern1(int n) {
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                System.out.print("* ");
            }
            System.out.println();
        }
    }

    //https://www.naukri.com/code360/problems/n-2-forest_6570178
    public static void pattern2(int n) {
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= i; j++) {
                System.out.print("*");
            }
            System.out.println();
        }
    }

    //https://www.naukri.com/code360/problems/n-triangles_6573689
    public static void pattern3(int n) {
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= i; j++) {
                System.out.print(j + " ");
            }
            System.out.println();
        }
    }

    //https://www.naukri.com/code360/problems/triangle_6573690
    public static void pattern4(int n) {
        for (int i = 0; i < n; i++) {
            for (int j = 0; j <= i; j++) {
                System.out.print(i + 1);
            }
            System.out.println();
        }
    }

    //https://www.naukri.com/code360/problems/seeding_6581892
    public static void pattern5(int n) {
        for (int i = 0; i < n; i++) {
            for (int j = n - i; j > 0; j--) {
                System.out.print("* ");
            }
            System.out.println();
        }
    }

    //https://www.naukri.com/code360/problems/reverse-number-triangle_6581889
    public static void pattern6(int n) {
        for (int i = 0; i < n; i++) {
            for (int j = 1; j <= n - i; j++) {
                System.out.print(j + " ");
            }
            System.out.println();
        }
    }

    //https://www.naukri.com/code360/problems/star-triangle_6573671
    public static void pattern7(int n) {
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n - i - 1; j++) {
                System.out.print(" ");
            }

            for (int j = 0; j <= i; j++) {
                System.out.print("*");
            }
            for (int j = 0; j < i; j++) {
                System.out.print("*");
            }

            for (int j = 0; j < n - i - 1; j++) {
                System.out.print(" ");
            }
            System.out.println();
        }
    }

    //https://www.naukri.com/code360/problems/reverse-star-triangle_6573685
    public static void pattern8(int n) {
        for (int i = 0; i < n; i++) {
            for (int j = n - i; j <= n; j++) {
                System.out.print(" ");
            }

            for (int j = 0; j <= n - i - 1; j++) {
                System.out.print("*");
            }
            for (int j = 0; j < n - i - 1; j++) {
                System.out.print("*");
            }

            for (int j = n - i; j <= n; j++) {
                System.out.print(" ");
            }
            System.out.println();
        }
    }

    //https://www.naukri.com/code360/problems/star-diamond_6573686
    public static void pattern9(int n) {
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n - i - 1; j++) {
                System.out.print(" ");
            }

            for (int j = 0; j <= i; j++) {
                System.out.print("*");
            }
            for (int j = 0; j < i; j++) {
                System.out.print("*");
            }

            for (int j = 0; j < n - i - 1; j++) {
                System.out.print(" ");
            }
            System.out.println();
        }
        for (int i = 0; i < n; i++) {
            for (int j = n - i + 1; j <= n; j++) {
                System.out.print(" ");
            }

            for (int j = 0; j <= n - i - 1; j++) {
                System.out.print("*");
            }
            for (int j = 0; j < n - i - 1; j++) {
                System.out.print("*");
            }

            for (int j = n - i + 1; j <= n; j++) {
                System.out.print(" ");
            }
            System.out.println();
        }
    }

    //https://www.naukri.com/code360/problems/rotated-triangle_6573688
    public static void pattern10(int n) {
        for (int i = 1; i <= (2 * n - 1); i++) {
            if (i <= n) {
                for (int j = 0; j < i; j++) {
                    System.out.print("*");
                }
            } else {
                for (int j = i; j < (2 * n); j++) {
                    System.out.print("*");
                }
            }
            System.out.println();
        }
    }

    //https://www.naukri.com/code360/problems/binary-number-triangle_6581890
    public static void pattern11(int n) {
        for (int i = 0; i < n; i++) {
            for (int j = 0; j <= i; j++) {
                if (i % 2 == 0) {
                    if (j % 2 == 0) {
                        System.out.print("1 ");
                    } else {
                        System.out.print("0 ");
                    }
                } else {
                    if (j % 2 == 0) {
                        System.out.print("0 ");
                    } else {
                        System.out.print("1 ");
                    }
                }
            }
            System.out.println();
        }
    }

    //https://www.naukri.com/code360/problems/number-crown_6581894
    public static void pattern12(int n) {
        for (int i = 0; i < n; i++) {
            for (int j = 0; j <= i; j++) {
                System.out.print(j + 1);
            }
            for (int j = (2 * i + 1); j < (2 * n - 1); j++) {
                System.out.print(" ");
            }
            for (int j = i; j >= 0; j--) {
                System.out.print(j + 1);
            }
            System.out.println();
        }
    }

    //https://www.naukri.com/code360/problems/increasing-number-triangle_6581893
    public static void pattern13(int n) {
        int count = 0;
        for (int i = 1; i <= n; i++) {
            for (int j = 0; j < i; j++) {
                count++;
                System.out.print(count + " ");
            }
            System.out.println();
        }
    }

    //https://www.naukri.com/code360/problems/increasing-letter-triangle_6581897
    public static void pattern14(int n) {
        char initChar = 'A';
        for (int i = 0; i < n; i++) {
            for (int j = 0; j <= i; j++) {
                System.out.print((char) (initChar + j) + " ");
            }
            System.out.println();
        }
    }

    //https://www.naukri.com/code360/problems/reverse-letter-triangle_6581906
    public static void pattern15(int n) {
        char initChar = 'A';
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n - i; j++) {
                System.out.print((char) (initChar + j) + " ");
            }
            System.out.println();
        }
    }

    //https://www.naukri.com/code360/problems/alpha-ramp_6581888
    public static void pattern16(int n) {
        char initChar = 'A';
        for (int i = 0; i < n; i++) {
            for (int j = 0; j <= i; j++) {
                System.out.print((char) (initChar + i) + " ");
            }
            System.out.println();
        }
    }

    //https://www.naukri.com/code360/problems/alpha-hill_6581921
    public static void pattern17(int n) {
        char initChar = 'A';
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n - i; j++) {
                System.out.print(" ");
            }
            for (int j = 0; j <= i; j++) {
                System.out.print((char) (initChar + j));
            }

            for (int j = 0; j < i; j++) {
                System.out.print((char) (initChar + i - j - 1));
            }
            System.out.println();
        }
    }

    //https://www.naukri.com/code360/problems/alpha-triangle_6581429
    public static void pattern18(int n) {
        char initChar = 'A';
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= i; j++) {
                System.out.print((char) (initChar + n - j) + " ");
            }
            System.out.println();
        }
    }

    //https://www.naukri.com/code360/problems/symmetric-void_6581919
    public static void pattern19(int n) {
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n - i; j++) {
                System.out.print("*");
            }
            for (int j = 0; j < 2 * i; j++) {
                System.out.print(" ");
            }
            for (int j = 0; j < n - i; j++) {
                System.out.print("*");
            }
            System.out.println();
        }

        for (int i = 0; i < n; i++) {
            for (int j = 0; j <= i; j++) {
                System.out.print("*");
            }
            for (int j = 2 * (n - i - 1); j > 0; j--) {
                System.out.print(" ");
            }
            for (int j = 0; j <= i; j++) {
                System.out.print("*");
            }
            System.out.println();
        }
    }

    //https://www.naukri.com/code360/problems/symmetry_6581914
    public static void pattern20(int n) {
        for (int i = 0; i < n; i++) {
            for (int j = 0; j <= i; j++) {
                System.out.print("*");
            }
            for (int j = 2 * (n - i - 1); j > 0; j--) {
                System.out.print(" ");
            }
            for (int j = 0; j <= i; j++) {
                System.out.print("*");
            }
            System.out.println();
        }
        for (int i = 0; i < n - 1; i++) {
            for (int j = 0; j < n - i - 1; j++) {
                System.out.print("*");
            }
            for (int j = 0; j < 2 * (i + 1); j++) {
                System.out.print(" ");
            }
            for (int j = 0; j < n - i - 1; j++) {
                System.out.print("*");
            }
            System.out.println();
        }
    }

    //https://www.naukri.com/code360/problems/ninja-and-the-star-pattern-i_6581920
    public static void pattern21(int n) {
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (i == 0 || j == 0 || i == n - 1 || j == n - 1) {
                    System.out.print("*");
                } else {
                    System.out.print(" ");
                }
            }
            System.out.println();
        }
    }
}