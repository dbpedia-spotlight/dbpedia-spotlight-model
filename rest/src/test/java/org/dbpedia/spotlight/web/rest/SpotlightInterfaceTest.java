package org.dbpedia.spotlight.web.rest;

import org.dbpedia.spotlight.spot.SpotXmlParser;
import org.junit.Test;
import org.mockito.Mock;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class SpotlightInterfaceTest {


    private final String API_NAME = "SPOTLIGHT";

    private final String DEFAULT_SPOTTER = "SpotXmlParser";

    @Mock
    Server server;

    @Test
    public void nullParametersMustBeAcceptedWithoutSideEffects() {
        //Arrange
        SpotlightInterface spotlightInterface = new SpotlightInterface(API_NAME);

        //Act
        spotlightInterface.announce(null, 0, 0, null, null, null, true, null, null, null);
    }

    @Test
    public void getJSON() throws Exception {
        //Arrange
        SpotlightInterface spotlightInterface = mock(SpotlightInterface.class);

        when(server.getSpotter(DEFAULT_SPOTTER)).thenReturn(new SpotXmlParser());

        String text = "Test";
        String inUrl = "";
        double confidence = 0;
        int support = 0;
        String dbpediaTypesString = "";
        String sparqlQuery = "";
        String policy = "";
        boolean coreferenceResolution = false;
        String clientIp = "";
        String spotterName = "Default";
        String disambiguator = "Document";

        spotlightInterface.getJSON(text, inUrl, confidence, support, dbpediaTypesString, sparqlQuery, policy, coreferenceResolution, clientIp, spotterName, disambiguator);

    }

}

