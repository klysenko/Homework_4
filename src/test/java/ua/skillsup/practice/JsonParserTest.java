package ua.skillsup.practice;

import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.DisplayName;

import java.time.LocalDate;

import static org.assertj.core.api.Assertions.assertThat;

public class JsonParserTest {
    private JsonParser jsonParser;

    @Before
    public void setUp() {
        jsonParser = new JsonParser();
    }

    @DisplayName("Test returns json format from object")
    @Test
    public void shouldConvertHumanToJson() throws IllegalAccessException {
        //GIVEN
        final LocalDate birthDate = LocalDate.of(1987, 5, 27);
        String expected = "{\"firstName\"" + ":\"Kate\"" + ",\"lastName\"" + ":\"Lysenko\"" + ",\"fun\"" + ":\"java\"" + ",\"birthDate\"" + ":\"27-05-1987\"}";

        Human human = new Human();
        human.setFirstName("Kate");
        human.setLastName("Lysenko");
        human.setHobby("java");
        human.setBirthDate(birthDate);

        //WHEN
        String actualJsonHuman = jsonParser.toJson(human);

        //THEN
        assertThat(actualJsonHuman).isEqualTo(expected);
    }

    @DisplayName("Test should not include the field when value is null")
    @Test
    public void shouldConvertHumanToJsonWhenFirstNameIsNull() throws IllegalAccessException {
        //GIVEN
        final LocalDate birthDate = LocalDate.of(1987, 5, 27);
        String expected = "{\"lastName\"" + ":\"Lysenko\"" + ",\"fun\"" + ":\"java\"" + ",\"birthDate\"" + ":\"27-05-1987\"}";

        Human human = new Human();
        human.setFirstName(null);
        human.setLastName("Lysenko");
        human.setHobby("java");
        human.setBirthDate(birthDate);

        //WHEN
        String actualJsonHuman = jsonParser.toJson(human);

        //THEN
        assertThat(actualJsonHuman).isEqualTo(expected);
    }

    @DisplayName("Test should not include the field when value is null")
    @Test
    public void shouldConvertHumanToJsonWhenLastNameIsNull() throws IllegalAccessException {
        //GIVEN
        final LocalDate birthDate = LocalDate.of(1987, 5, 27);
        String expected = "{\"firstName\"" + ":\"Kate\"" + ",\"fun\"" + ":\"java\"" + ",\"birthDate\"" + ":\"27-05-1987\"}";

        Human human = new Human();
        human.setFirstName("Kate");
        human.setLastName(null);
        human.setHobby("java");
        human.setBirthDate(birthDate);

        //WHEN
        String actualJsonHuman = jsonParser.toJson(human);

        //THEN
        assertThat(actualJsonHuman).isEqualTo(expected);
    }

    @DisplayName("Test should not include the field when value is null")
    @Test
    public void shouldConvertHumanToJsonWhenHobbyIsNull() throws IllegalAccessException {
        //GIVEN
        final LocalDate birthDate = LocalDate.of(1987, 5, 27);
        String expected = "{\"firstName\"" + ":\"Kate\"" + ",\"lastName\"" + ":\"Lysenko\"" + ",\"birthDate\"" + ":\"27-05-1987\"}";

        Human human = new Human();
        human.setFirstName("Kate");
        human.setLastName("Lysenko");
        human.setHobby(null);
        human.setBirthDate(birthDate);

        //WHEN
        String actualJsonHuman = jsonParser.toJson(human);

        //THEN
        assertThat(actualJsonHuman).isEqualTo(expected);
    }

    @DisplayName("Test should not include the field when value is null")
    @Test
    public void shouldConvertHumanToJsonWhenBirthDateIsNull() throws IllegalAccessException {
        //GIVEN
        String expected = "{\"firstName\"" + ":\"Kate\"" + ",\"lastName\"" + ":\"Lysenko\"" + ",\"fun\"" + ":\"java\"}";

        Human human = new Human();
        human.setFirstName("Kate");
        human.setLastName("Lysenko");
        human.setHobby("java");
        human.setBirthDate(null);

        //WHEN
        String actualJsonHuman = jsonParser.toJson(human);

        //THEN
        assertThat(actualJsonHuman).isEqualTo(expected);
    }

    @Test
    public void shouldConvertToHumanfromJson() throws InstantiationException, IllegalAccessException {
        //GIVEN
        String expected = "Human{firstName='Kate', lastName='Lysenko', hobby='hobby', birthDate=1987-05-27}";
        final LocalDate birthDate = LocalDate.of(1987, 5, 27);

        String json = "{\"firstName\"" + ":\"Kate\"" + ",\"lastName\"" + ":\"Lysenko\"" + ",\"fun\"" + ":\"hobby\"" + ",\"birthDate\"" + ":\"27-05-1987\"}";

        Human human = new Human();
        human.setFirstName("Kate");
        human.setLastName("Lysenko");
        human.setHobby("hobby");
        human.setBirthDate(birthDate);

        //WHEN
        Human actualHuman = jsonParser.fromJson(json, Human.class);

        //THEN
        assertThat(actualHuman.toString()).isEqualTo(expected);
    }
}