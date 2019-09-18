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
}
