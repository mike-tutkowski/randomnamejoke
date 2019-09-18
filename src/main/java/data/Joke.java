package data;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Joke {
    private static final String SUCCESS = "success";

    @JsonProperty("type") private String success;
    @JsonProperty("value") private Value value;

    public boolean isSuccess() {
        return SUCCESS.equals(success);
    }

    public String getJoke() {
        return value.getJoke();
    }

    @JsonIgnoreProperties(ignoreUnknown = true)
    private static class Value {
        @JsonProperty("joke") private String joke;

        String getJoke() {
            return joke;
        }
    }
}
