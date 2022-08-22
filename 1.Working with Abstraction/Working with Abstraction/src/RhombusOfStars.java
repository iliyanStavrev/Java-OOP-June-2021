import java.util.Scanner;

public class RhombusOfStars {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int num = Integer.parseInt(scanner.nextLine());

        upperSide(num);
        downSide(num);
    }

    private static void downSide(int num) {
        for (int i = 1; i <= num; i++) {
            for (int j = 0; j < i; j++) {
                System.out.print(" ");
            }
            for (int j = 0; j < num -  i; j++) {
                System.out.print("* ");
            }

            System.out.println();
        }
    }

    private static void upperSide(int num) {
        for (int i = 1; i <= num; i++) {
            for (int j = 0; j < num -  i; j++) {
                System.out.print(" ");
            }
            for (int j = 0; j < i; j++) {
                System.out.print("* ");
            }
            System.out.println();
        }
    }

}
