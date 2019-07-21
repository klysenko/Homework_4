package ua.skillsup.practice;

import java.time.LocalDate;

public class Human {
    private String firstName;

    private String lastName;

    @JsonValue(name = "fun")
    private String hobby;

    @CustomDateFormat(format = "dd-MM-yyyy")
    private LocalDate birthDate;

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }


    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setHobby(String hobby) {
        this.hobby = hobby;
    }

    public void setBirthDate(LocalDate localDate) {
        this.birthDate = localDate;
    }

    @Override
    public String toString() {
        return "Human{" +
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", hobby='" + hobby + '\'' +
                ", birthDate=" + birthDate +
                '}';
    }
}

