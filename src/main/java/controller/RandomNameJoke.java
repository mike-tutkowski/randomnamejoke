package controller;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import service.Service;

@RestController
class RandomNameJoke {
    private static final Logger LOGGER = Logger.getLogger(RandomNameJoke.class);

    @Autowired private Service service;

    @RequestMapping(value = "/", method = RequestMethod.GET)
    ResponseEntity<String> getRandomNameJoke() {
        String randomJoke;

        try {
            randomJoke = service.getRandomNameJoke();

            String msg = randomJoke + "\n";
            String formattedMsg = String.format("Successful response to client: '%s'", randomJoke);

            LOGGER.debug(formattedMsg);

            return new ResponseEntity<>(msg, HttpStatus.OK);
        }
        catch (Exception ex) {
            String errMsg = ex.getMessage() + "\n";
            String formattedMsg = String.format("Error response to client: '%s'", ex.getMessage());

            LOGGER.error(formattedMsg, ex);

            return new ResponseEntity<>(errMsg, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
