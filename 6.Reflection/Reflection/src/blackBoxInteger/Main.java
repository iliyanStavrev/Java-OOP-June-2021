package blackBoxInteger;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException, NoSuchFieldException {

        Scanner scanner = new Scanner(System.in);

        String input = scanner.nextLine();

        Class<BlackBoxInt> box =  BlackBoxInt.class;
        Constructor<BlackBoxInt> ctor = box.getDeclaredConstructor();
        ctor.setAccessible(true);
        BlackBoxInt boxInt = ctor.newInstance();

        Method[] declaredMethods = box.getDeclaredMethods();
        Field innerValue = box.getDeclaredField("innerValue");
        innerValue.setAccessible(true);


        while (!input.equals("END")){
            String[]tokens = input.split("_");
              String command = tokens[0];
              int value = Integer.parseInt(tokens[1]);
            Method method = Arrays.stream(declaredMethods).filter
                    (m -> m.getName().equals(command)).findFirst().get();
            method.setAccessible(true);
            method.invoke(boxInt,value);
            System.out.println(innerValue.getInt(boxInt));
            input = scanner.nextLine();
        }
    }
}
