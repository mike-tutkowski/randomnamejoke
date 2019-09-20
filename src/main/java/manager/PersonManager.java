package manager;

import data.Person;

import java.util.ArrayDeque;
import java.util.Deque;

import org.apache.log4j.Logger;

import util.Util;

// The purpose of this class is to hand out Person instances. Since requests can
// come into getRandomPerson from multiple threads simultaneously, that method
// must protect the shared data structure (called people) from corruption.
// I take a straight-forward approach to populating people: getRandomPerson first
// obtains the monitor on the people object, then checks to see if it is empty.
// In most cases, it will not be empty, so an item can be popped from the stack
// and returned. In the event that people is empty, I call populatePeople, which
// makes a call to the Util class to retrieve more people (Util does this in a
// generic way so that it is not aware of the domain-specific Person class). I
// then iterate through each returned person, pushing him/her onto the stack. The
// number of people that the Util class retrieves at a given time is dictated by
// the NUMBER_OF_PEOPLE constant.
//
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
