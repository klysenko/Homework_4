package ua.skillsup.practice;

import java.time.LocalDate;

public class Main {
    public static void main(String []args ) throws IllegalAccessException, InstantiationException {

        Human human = new Human();

        JsonParser jsonParser = new JsonParser();
        human.setFirstName(null);
        human.setLastName("Ivanow");
        human.setHobby("hj");
        human.setBirthDate(LocalDate.now().minusYears(30));

        String jsonFromHuman  = jsonParser.toJson(human);

        Human humanFromJson = jsonParser.fromJson(jsonFromHuman, Human.class);

        System.out.println(jsonFromHuman);
       // System.out.println(humanFromJson);
    }
}
