import java.util.ArrayList;
import java.util.HashMap;

public class JsonObject {
    private String name;
    private HashMap<Object, Object> fields;
    private ArrayList<JsonObject> objects;
    public JsonObject(String s) {
        name = s;
        fields = new HashMap<>();
        objects = new ArrayList<>();
    }

    public void put(Object name, Object value) {
        fields.put(name, value);
    }

    public void put(JsonObject object) {
        objects.add(object);
    }

    public String toString() {
        String output = "{\n";
        output += "\t\"" +  name + "\":{\n";
        for (int i = 0; i < objects.size(); i++) {
            if ((i == objects.size() - 1) && (fields.isEmpty())) output += objects.get(i).toStringComponent(1) + "\n";
            else output += objects.get(i).toStringComponent(1) + ",\n";
        }
        int n = fields.size();
        for (Object key: fields.keySet()) {
            --n;
            if (n == 0) output += "\t\t\"" + key + "\":" + toJson(fields.get(key)) + "\n";
            else output += "\t\t\"" + key + "\":" + toJson(fields.get(key)) + ",\n";
        }
        output += "\t}\n";
        output += "}";
        return output;
    }

    private Object toJson(Object input) {
        try {
            if (input.getClass().equals(String.class)) {
                return "\"" + input + "\"";
            } else return input;
        } catch (NullPointerException e) {
            e.printStackTrace();
        }
        return "";
    }

    public String toStringComponent(int n) {
        String output = "";
        for (int i = 0; i < n; i++) {
            output += "\t";
        }
        output += "\t\"" +  name + "\":{\n";
        for (int i = 0; i < objects.size(); i++) {
            for (int j = 0; j < n; j++) {
                output += "\t";
            }
            if (i == objects.size() - 1) output += "\t\t\"" + objects + "\n";
            else output += "\t\t\"" + objects + ",\n";
        }
        int size = fields.size();
        for (Object key: fields.keySet()) {
            --size;
            for (int i = 0; i < n; i++) {
                output += "\t";
            }
            if (size == 0) output += "\t\t\"" + key + "\":" + toJson(fields.get(key)) + "\n";
            else output += "\t\t\"" + key + "\":" + toJson(fields.get(key)) + ",\n";
        }
        for (int i = 0; i < n; i++) {
            output += "\t";
        }
        output += "\t}\n";
        return output;
    }
}
