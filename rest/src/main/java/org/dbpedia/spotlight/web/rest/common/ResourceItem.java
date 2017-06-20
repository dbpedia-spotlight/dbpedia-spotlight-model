package org.dbpedia.spotlight.web.rest.common;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.gson.annotations.SerializedName;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.dbpedia.spotlight.web.rest.common.Constants.COMMA;


@Getter
@Setter
@NoArgsConstructor
public class ResourceItem {

    @JsonProperty("@URI")
    @SerializedName("@URI")
    private String uri;

    @JsonProperty("@support")
    @SerializedName("@support")
    private String support;

    @JsonProperty("@types")
    @SerializedName("@types")
    private String types;

    @JsonProperty("@surfaceForm")
    @SerializedName("@surfaceForm")
    private String surfaceForm;

    @JsonProperty("@offset")
    @SerializedName("@offset")
    private String offSet;

    @JsonProperty("@similarityScore")
    @SerializedName("@similarityScore")
    private String similarityScore;

    @JsonProperty("@percentageOfSecondRank")
    @SerializedName("@percentageOfSecondRank")
    private String percentageOfSecondRank;


    public Integer beginIndex() {
        try {
            return Integer.valueOf(offSet);
        } catch (NumberFormatException e) {
            return 0;
        }
    }

    public Integer endIndex() {
        if (surfaceForm != null) {
            return beginIndex() + surfaceForm.length();
        }

        return 0;
    }

    public List<String> typesList() {

        if (types != null && !types.isEmpty()) {
            return Arrays.asList(types.split(COMMA));
        }

        return new ArrayList<>();
    }

    public Double score() {
        try {
            return Double.valueOf(similarityScore);
        } catch (NumberFormatException e) {
            return 0d;
        }
    }

}
