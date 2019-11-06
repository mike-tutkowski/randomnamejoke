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

        @Override
        public boolean equals(Object obj) {
            if (obj instanceof Value) {
                Value value = (Value)obj;

                return this.joke.equals(value.joke);
            }

            return false;
        }
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Joke) {
            Joke joke = (Joke)obj;

            return this.success.equals(joke.success) && this.value.equals(joke.value);
        }

        return false;
    }

    @Override
    public String toString() {
        return String.format("Joke: %s", value.getJoke());
    }
}
