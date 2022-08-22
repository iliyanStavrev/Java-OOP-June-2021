package RandomArrayList;

import java.util.concurrent.ThreadLocalRandom;

public class Main {
    public static void main(String[] args) {

        RandomArrayList<Integer> arrayList = new RandomArrayList();

        ThreadLocalRandom.current().ints(50)
                .forEach(arrayList::add);

        System.out.println(arrayList.getRandomElement());

    }
}
