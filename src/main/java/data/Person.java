package data;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Person {
    @JsonProperty("name") private String name;
    @JsonProperty("surname") private String surname;

    // Note: We could use a library like Lombok (https://projectlombok.org) instead of manually adding getters here.

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Person) {
            Person person = (Person)obj;

            return this.name.equals(person.name) && this.surname.equals(person.surname);
        }

        return false;
    }

    @Override
    public String toString() {
        return String.format("Person: First name = %s; Last name = %s", name, surname);
    }
}
