package org.dbpedia.spotlight.web.rest.formats;


import org.junit.Test;

import static junit.framework.Assert.assertEquals;

public class JSONOutputManagerTest {

    @Test
    public void annotationUnitNullMustBeAnEmptyJson() {
        // Arrange

        // Run
        String result = JSONOutputManager.parse(null);

        // Check
        assertEquals("{}", result);
    }
}
