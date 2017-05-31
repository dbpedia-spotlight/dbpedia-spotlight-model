package org.dbpedia.spotlight.web.rest;

import org.dbpedia.spotlight.exceptions.InputException;
import org.junit.Assert;
import org.junit.Test;

public class ServerUtilsTest {

    @Test(expected = InputException.class)
    public void emptyTextMustThrowInputException() throws InputException {
        //Arrange
        String text = "";
        String url = "";

        //Act
        ServerUtils.getTextToProcess(text, url);
    }

    @Test(expected = InputException.class)
    public void nullTextMustThrowInputException() throws InputException {
        //Arrange
        String text = null;
        String url = "";

        //Act
        ServerUtils.getTextToProcess(text, url);
    }

    @Test(expected = InputException.class)
    public void nullWithEmptyTextMustThrowInputException() throws InputException {
        //Arrange
        String text = "";
        String url = null;

        //Act
        ServerUtils.getTextToProcess(text, url);
    }


    @Test
    public void urlMustReturnAContent() throws InputException {
        //Arrange
        String text = "";
        String url = "http://www.dbpedia-spotlight.org";

        //Act
        String result = ServerUtils.getTextToProcess(text, url);

        //Check
        Assert.assertNotNull(result);
    }

    @Test (expected= NullPointerException.class)
    public void printExceptionNullMustReturnNullPointerException() {
        //Act
        ServerUtils.print(null);

    }

    @Test (expected= NullPointerException.class)
    public void responseNullMustReturnNullPointerException() {
        //Act
        ServerUtils.ok(null);
    }
}
