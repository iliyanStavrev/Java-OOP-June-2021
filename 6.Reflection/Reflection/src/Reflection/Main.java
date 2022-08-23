package Reflection;

import java.lang.reflect.*;
import java.util.*;

public class Main {
    public static class MethodComparator implements Comparator<Method>{

        @Override
        public int compare(Method f,Method s) {
            return f.getName().compareTo(s.getName());
        }
    }
    public static void main(String[] args) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException {

     Class<Reflection> clazz =  Reflection.class;

        Method[] declaredMethods = clazz.getDeclaredMethods();
      Set<Method>getters = new TreeSet<>(new MethodComparator());
      Set<Method>setters = new TreeSet<>(new MethodComparator());
        for (Method method : declaredMethods) {
            if (method.getName().contains("get")){
                getters.add(method);
            }
            else if (method.getName().contains("set")){
                setters.add(method);
            }
        }
        Arrays.stream(clazz.getDeclaredFields())
                .filter(f -> !Modifier.isPrivate(f.getModifiers()))
                .sorted(Comparator.comparing(Field::getName))
                .forEach(f -> System.out.println(f.getName() + " must be private!"));



        for (Method getter : getters) {
          if (!Modifier.isPublic(getter.getModifiers())){
              System.out.printf("%s have to be public!%n",getter.getName());
          }
        }
        for (Method setter : setters) {
            if (!Modifier.isPrivate(setter.getModifiers())){
                System.out.printf("%s have to be private!%n",setter.getName());
            }
        }
    }
}
