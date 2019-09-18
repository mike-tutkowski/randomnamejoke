package service;

import manager.Manager;
import manager.ManagerImpl;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
class RandomNameJoke {
    private Manager manager = new ManagerImpl();

    @RequestMapping(value = "/", method = RequestMethod.GET)
    ResponseEntity<String> getRandomNameJoke() {
        String randomJoke;

        try {
            randomJoke = manager.getRandomJoke();

            String msg = randomJoke + "\n";

            return new ResponseEntity<>(msg, HttpStatus.OK);
        }
        catch (Exception ex) {
            String errMsg = ex.getMessage() + "\n";

            return new ResponseEntity<>(errMsg, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
