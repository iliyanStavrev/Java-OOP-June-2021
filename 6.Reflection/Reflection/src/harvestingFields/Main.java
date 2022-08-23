package harvestingFields;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);

		Class<RichSoilLand>clazz = RichSoilLand.class;
		Field[] declaredFields = clazz.getDeclaredFields();



		String input = scanner.nextLine();
		while (!input.equals("HARVEST")){
			switch (input){
				case "private":
					for (Field field : declaredFields) {
						if (Modifier.isPrivate(field.getModifiers())){
							System.out.printf("%s %s %s%n",
									Modifier.toString(field.getModifiers()),
									field.getType().getSimpleName(),field.getName());
						}
					}
					break;
				case "protected":
					for (Field field : declaredFields) {
						if (Modifier.isProtected(field.getModifiers())){
							System.out.printf("%s %s %s%n",
									Modifier.toString(field.getModifiers()),
									field.getType().getSimpleName(),field.getName());
						}
					}
					break;
				case "public":
					for (Field field : declaredFields) {
						if (Modifier.isPublic(field.getModifiers())){
							System.out.printf("%s %s %s%n",
									Modifier.toString(field.getModifiers()),
									field.getType().getSimpleName(),field.getName());
						}
					}
					break;
				case "all":
					for (Field field : declaredFields) {
							System.out.printf("%s %s %s%n",
									Modifier.toString(field.getModifiers()),
									field.getType().getSimpleName(),field.getName());
					}
					break;
			}
			input = scanner.nextLine();
		}

	}

}
