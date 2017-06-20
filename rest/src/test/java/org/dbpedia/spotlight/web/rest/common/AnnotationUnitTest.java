package org.dbpedia.spotlight.web.rest.common;


import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class AnnotationUnitTest {

    @Test
    public void ifTextIsNullEndIndexMustBeZero() {
        //Arrange
        AnnotationUnit annotationUnit = new AnnotationUnit();

        //Run
        int endIndex = annotationUnit.endIndex();

        //Check
        assertEquals(0, endIndex);
    }

    @Test
    public void occsNullImpliesInNullResources() {

        //Arrange
        AnnotationUnit annotationUnit = new AnnotationUnit();

        //Run
        annotationUnit.buildResources(null);

        //Check
        assertEquals(null, annotationUnit.getResources());
    }
}
