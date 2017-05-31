package org.dbpedia.spotlight.web.rest.formats;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SpotlightConfiguration {

    private final String URL = "%s/%s/";

    private final Double defaultConfidence = 0.5d;

    private String spotlightURL = "http://www.dbpedia-spotlight.com";

    private String jsonContext = "http://www.jsonld-context.io/api/v1?ontology=http://persistence.uni-leipzig.org/nlp2rdf/ontologies/nif-core,http://www.w3.org/2005/11/its/rdf";


    public static SpotlightConfiguration configuration() {
        return new SpotlightConfiguration();
    }
}
