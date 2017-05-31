package org.dbpedia.spotlight.web.rest.output;


import org.junit.Assert;
import org.junit.Test;

public class AnnotationTest {


    private static final String TEXT = "This is a test";

    @Test
    public void textNullMustProduceAnEmptyJSON() {
        //Arrange
        String text = null;
        String expected = "{\"annotation\":\"\"}";

        //Act
        Annotation annotation = new Annotation(text);
        String result = annotation.toJSON();


        //Check
        Assert.assertEquals(expected, result);
    }


    @Test
    public void textNullMustProduceAnEmptyXML() {
        //Arrange
        String text = null;
        String expected = "<annotation/>";

        //Act
        Annotation annotation = new Annotation(text);
        String result = annotation.toXML();


        //Check
        Assert.assertEquals(expected, result);
    }



    @Test
    public void textNullMustProduceAnEmptyToString() {
        //Arrange
        String text = null;
        String expected = "<annotation/>";

        //Act
        Annotation annotation = new Annotation(text);
        String result = annotation.toXML();


        //Check
        Assert.assertEquals(expected, result);
    }
}
