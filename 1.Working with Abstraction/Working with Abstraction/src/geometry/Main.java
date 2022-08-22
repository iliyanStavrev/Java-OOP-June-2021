package geometry;

import java.util.Arrays;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        String[]tokens = scanner.nextLine().split("\\s+");
        int bottomLeftX = Integer.parseInt(tokens[0]);
        int bottomLeftY = Integer.parseInt(tokens[1]);
        int topRightX= Integer.parseInt(tokens[2]);
        int topRightY = Integer.parseInt(tokens[3]);
          Rectangle rectangle = new Rectangle(new Point2d(bottomLeftX,bottomLeftY),
                  new Point2d(topRightX,topRightY));

        int num = Integer.parseInt(scanner.nextLine());
        for (int i = 0; i < num; i++) {
            int[]toks = Arrays.stream(scanner.nextLine().split("\\s+"))
                    .mapToInt(Integer::parseInt).toArray();
            Point2d point2d = new Point2d(toks[0],toks[1]);
            System.out.println(rectangle.contains(point2d));
        }

    }
}
