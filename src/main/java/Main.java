import java.io.BufferedWriter;
import java.io.IOException;
import java.lang.reflect.Array;
import java.lang.reflect.Field;
import java.nio.file.Files;
import java.nio.file.Paths;

public class Main {
    public static void main(String args[]) throws Throwable {
        Student student = new Student("Gabriela");
        student.addGrade(5);
        student.addGrade(4);
        student.addGrade(3);

        Field[] fields = student.getClass().getDeclaredFields();
        for (Field field: fields) {
            field.setAccessible(true);
        }

        try {
            BufferedWriter writer = Files.newBufferedWriter(Paths.get("C:\\Users\\ticug\\Annotations\\src\\output.json"));

            JsonObject output = new JsonObject(student.getClass().getAnnotation(Name.class).value());
            for (Field field: fields) {
                if (field.isAnnotationPresent(Name.class)) {
                    if (field.getType().isArray()) {
                        JsonObject array = new JsonObject(field.getAnnotation(Name.class).value());
                        for (int i = 0; i < Array.getLength(field.get(student)); i++) {
                            array.put(String.valueOf(i+1), Array.get(field.get(student), i));
                        }
                        output.put(array);
                    } else {
                       output.put(field.getAnnotation(Name.class).value(), field.get(student));
                    }
                }
            }

            writer.write(output.toString());
            writer.close();

        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
}
