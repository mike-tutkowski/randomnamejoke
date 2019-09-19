package service;

import data.Joke;
import data.Person;

import util.Util;

@org.springframework.stereotype.Service
public class ServiceImpl implements Service {
    private static final String RANDOM_PERSON_URL = "http://uinames.com/api/";
    private static final String RANDOM_JOKE_URL = "http://api.icndb.com/jokes/random";

    public String getRandomNameJoke() throws Exception {
        Person randomPerson = getRandomPerson();

        return getRandomJoke(randomPerson);
    }

    private static Person getRandomPerson() throws Exception {
        return Util.executeRestGet(RANDOM_PERSON_URL, Person.class);
    }

    /**
     * Note: Currently the REST service we talk to here can return text such as &quot; to indicate a quotation mark.
     *   It can also make mistakes with apostrophe s (as on the next line):
     *   "Lucy Kirk'ss OSI network model has only one layer - Physical."
     *   At present, this is just passed as is back from this method.
     * @param person - instance of the Person class, used in building a custom joke.
     * @return a random joke as a String. The joke includes the person's first and last names.
     * @throws Exception - if we are not successful in retrieving a customized joke.
     */
    private static String getRandomJoke(Person person) throws Exception {
        String parameters = String.format("?firstName=%s&lastName=%s&limitTo=nerdy", person.getName(), person.getSurname());

        Joke joke = Util.executeRestGet(RANDOM_JOKE_URL + parameters, Joke.class);

        if (joke.isSuccess()) {
            return joke.getJoke();
        }

        throw new Exception("Unable to retrieve a joke.");
    }
}
