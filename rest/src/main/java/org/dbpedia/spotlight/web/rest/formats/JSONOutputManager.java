package org.dbpedia.spotlight.web.rest.formats;

import com.google.gson.Gson;
import org.dbpedia.spotlight.web.rest.common.AnnotationUnit;

public class JSONOutputManager {

    public static String parse(AnnotationUnit annotationUnit) {

        if (annotationUnit == null) {
            return "{}";
        }

        Gson gson = new Gson();
        return gson.toJson(annotationUnit);

    }
}
