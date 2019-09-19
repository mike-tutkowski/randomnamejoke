package manager;

import data.Person;

import java.util.ArrayDeque;
import java.util.Deque;

import org.apache.log4j.Logger;

import util.Util;

public class PersonManager {
    private static final Logger LOGGER = Logger.getLogger(PersonManager.class);
    private static final String RANDOM_PERSON_URL = "http://uinames.com/api/";
    private static final int NUMBER_OF_PEOPLE = 100;

    private static final Deque<Person> people = new ArrayDeque<>();

    public static synchronized Person getRandomPerson() throws Exception {
        if (people.isEmpty()) {
            populatePeople();
        }

        return people.pop();
    }

    // private method that should only be called from code that has already obtained the
    // monitor on the people object.
    private static void populatePeople() throws Exception {
        LOGGER.debug("Need to retrieve more people.");

        String urlWithParam = RANDOM_PERSON_URL + "?amount=" + NUMBER_OF_PEOPLE;
        Person[] newPeople = Util.executeRestGet(urlWithParam, Person[].class);

        for (Person person : newPeople) {
            LOGGER.debug(person);

            people.push(person);
        }
    }
}
