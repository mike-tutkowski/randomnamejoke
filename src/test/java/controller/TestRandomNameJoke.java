package controller;

import manager.Manager;

import org.junit.Assert;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
class TestRandomNameJoke {
    @Mock private Manager mockManager;
    @InjectMocks private RandomNameJoke randomNameJoke;

    @BeforeEach
    void init() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void testRandomNameJokeSuccess() throws Exception {
        final String JOKE = "Mike Tutkowski says there are 10 types of people in the world: those who understand binary and those who don't.";

        when(mockManager.getRandomNameJoke()).thenReturn(JOKE);

        ResponseEntity<String> responseEntity = randomNameJoke.getRandomNameJoke();

        verify(mockManager, times(1)).getRandomNameJoke();

        Assert.assertEquals(responseEntity.getStatusCode(), HttpStatus.OK);
        Assert.assertEquals(responseEntity.getBody(), JOKE + "\n");
    }

    @Test
    void testRandomNameJokeFailure() throws Exception {
        final String ERROR_MSG = "Unable to access the REST service.";
        Exception exception = new Exception(ERROR_MSG);

        doThrow(exception).when(mockManager).getRandomNameJoke();

        ResponseEntity<String> responseEntity = randomNameJoke.getRandomNameJoke();

        verify(mockManager, times(1)).getRandomNameJoke();

        Assert.assertEquals(responseEntity.getStatusCode(), HttpStatus.INTERNAL_SERVER_ERROR);
        Assert.assertEquals(responseEntity.getBody(), ERROR_MSG + "\n");
    }
}
