package service;

import manager.Manager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
class RandomNameJoke {
    @Autowired
    private Manager manager;

    @RequestMapping(value = "/", method = RequestMethod.GET)
    ResponseEntity<String> getRandomNameJoke() {
        String randomJoke;

        try {
            randomJoke = manager.getRandomNameJoke();

            String msg = randomJoke + "\n";

            System.out.print(msg);
            return new ResponseEntity<>(msg, HttpStatus.OK);
        }
        catch (Exception ex) {
            String errMsg = ex.getMessage() + "\n";
            ex.printStackTrace();

            return new ResponseEntity<>(errMsg, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public void setManager(Manager manager) {
        this.manager = manager;
    }
}
