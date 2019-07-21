package ua.skillsup.practice;

import java.lang.reflect.Field;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

public class JsonParser {
    private static final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern("dd-MM-yyyy");

    public String toJson(Object object) throws IllegalAccessException {
        Class<?> aClass = object.getClass();
        Field[] fields = aClass.getDeclaredFields();
        StringBuilder string = new StringBuilder("{");
        for (Field field : fields) {
            field.setAccessible(true);
            if (Objects.nonNull(field.get(object))) {
                String nameField = field.getName();
                Object fieldValue = field.get(object);
                if (field.isAnnotationPresent(JsonValue.class)) {
                    nameField = field.getAnnotation(JsonValue.class).name();
                }
                if (field.isAnnotationPresent(CustomDateFormat.class)) {
                    String format = field.getAnnotation(CustomDateFormat.class).format();
                    fieldValue = ((LocalDate) field.get(object)).format(DateTimeFormatter.ofPattern(format));
                }

                string.append("\"")
                        .append(nameField)
                        .append("\":\"")
                        .append(fieldValue)
                        .append("\",");
                field.setAccessible(false);
            }
        }
        //if (string.lastIndexOf(",") > 0) {string.deleteCharAt(string.length()-1);}
        string.deleteCharAt(string.lastIndexOf(","));
        string.append("}");

        return string.toString();
    }

    public <T> T fromJson(String json, Class<T> clazz) throws IllegalAccessException, InstantiationException {
        String[] arrayFieldValue = json.replaceAll("\"", "")
                .replaceAll("\\{", "")
                .replaceAll("}", "")
                .split(",");


        Map<String, String> mapField = Arrays.stream(arrayFieldValue)
                .map(s -> s.split(":"))
                .collect(Collectors.toMap(array -> array[0], array -> array[1]));

        T object = clazz.newInstance();
        for (Field field : clazz.getDeclaredFields()) {
            Object value = null;
            if (mapField.containsKey(field.getName())) {
                String fieldName = mapField.get(field.getName());
                if (field.isAnnotationPresent(CustomDateFormat.class)) {
                    value = LocalDate.parse(fieldName, DATE_TIME_FORMATTER);
                } else {
                    value = fieldName;
                }
            } else if (field.getAnnotation(JsonValue.class) != null) {
                value = mapField.get(field.getAnnotation(JsonValue.class).name());
            }
            field.setAccessible(true);
            field.set(object, value);
        }
        return object;
    }
}
