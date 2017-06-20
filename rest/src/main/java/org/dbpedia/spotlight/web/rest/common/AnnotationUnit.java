package org.dbpedia.spotlight.web.rest.common;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.gson.annotations.SerializedName;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.dbpedia.spotlight.model.DBpediaResourceOccurrence;
import org.dbpedia.spotlight.web.rest.Server;

import java.util.ArrayList;
import java.util.List;

import static org.dbpedia.spotlight.web.rest.common.Constants.COMMA;

@Getter
@Setter
@NoArgsConstructor
public class AnnotationUnit {

    @JsonProperty("@text")
    @SerializedName("@text")
    private String text;

    @JsonProperty("@confidence")
    @SerializedName("@confidence")
    private String confidence;

    @JsonProperty("@support")
    @SerializedName("@support")
    private String support;

    @JsonProperty("@types")
    @SerializedName("@types")
    private String types;

    @JsonProperty("@sparql")
    @SerializedName("@sparql")
    private String sparql;

    @JsonProperty("@policy")
    @SerializedName("@policy")
    private String policy;

    @JsonProperty("Resources")
    @SerializedName("Resources")
    private List<ResourceItem> resources;

    public Integer endIndex() {
        if (text != null) {
            return text.length();
        }
        return 0;
    }

    public Integer beginIndex() {
        return 1;
    }

    public void buildResources(List<DBpediaResourceOccurrence> occs) {

        if (occs != null && !occs.isEmpty()) {
            this.resources = new ArrayList<>();

            occs.forEach(occ -> {
                ResourceItem resource = new ResourceItem();

                resource.setUri(Server.getPrefixedDBpediaURL(occ.resource()));
                resource.setTypes((occ.resource().types()).mkString(COMMA));
                resource.setSurfaceForm(occ.surfaceForm().name());
                resource.setOffSet(String.valueOf(occ.textOffset()));
                resource.setSupport(String.valueOf(occ.resource().support()));
                resource.setSimilarityScore(String.valueOf(occ.similarityScore()));
                resource.setPercentageOfSecondRank(String.valueOf(occ.percentageOfSecondRank()));

                this.resources.add(resource);

            });
        }
    }
}
