package org.dbpedia.spotlight.web.rest.formats;

import org.dbpedia.spotlight.model.DBpediaResourceOccurrence;
import org.dbpedia.spotlight.model.OntologyType;
import org.dbpedia.spotlight.model.SurfaceFormOccurrence;
import org.nlp2rdf.NIF;
import org.nlp2rdf.bean.NIFBean;
import org.nlp2rdf.bean.NIFType;
import org.nlp2rdf.nif21.impl.NIF21;

import java.util.ArrayList;
import java.util.List;

import static org.dbpedia.spotlight.web.rest.common.Constants.SLASH;

public class NIFWrapper {

    private SpotlightConfiguration configuration;

    private List<NIFBean> entities = new ArrayList<>();

    private NIFBean beanContext;

    private String baseURI;


    public NIFWrapper(SpotlightConfiguration configuration) {

        this.configuration = configuration;

        this.baseURI = configuration.getSpotlightURL();

        formatBaseURI();
    }

    public NIFWrapper(SpotlightConfiguration configuration, String baseURI) {

        this.configuration = configuration;

        this.baseURI = baseURI;

        formatBaseURI();
    }

    public void context(String mention) {

        int beginIndex = 0;
        int endIndex = mention.length();

        NIFBean.NIFBeanBuilder contextBuilder = new NIFBean.NIFBeanBuilder();

        contextBuilder.context(baseURI, beginIndex, endIndex).mention(mention).nifType(NIFType.CONTEXT);

        beanContext = new NIFBean(contextBuilder);

    }

    private void formatBaseURI() {
        if (baseURI != null && !baseURI.isEmpty() &&
                !SLASH.equals(baseURI.substring(baseURI.length() - 1))) {
            baseURI = baseURI.concat(SLASH);
        }
    }

    public void entityFromResource ( List<DBpediaResourceOccurrence> occs, String text) {

        NIFBean.NIFBeanBuilder entity = new NIFBean.NIFBeanBuilder();


        if (occs != null) {
            if(occs.size() == 0){
                this.context(text);
            }else {
                occs.forEach(resourceItem -> {
                    this.context(resourceItem.context().text());
                    entity.mention(resourceItem.surfaceForm().name());
                    entity.beginIndex(resourceItem.textOffset());
                    entity.endIndex(resourceItem.textOffset() + resourceItem.surfaceForm().name().length());
                    entity.annotator(configuration.getSpotlightURL());
                    entity.taIdentRef(resourceItem.resource().getFullUri());
                    List<String> listTypes = new ArrayList<String>();
                    for (OntologyType otype : resourceItem.resource().getTypes()) {
                        listTypes.add(otype.getFullUri());
                    }
                    entity.types(listTypes);
                    entity.score(resourceItem.similarityScore());
                    entity.context(baseURI, resourceItem.textOffset(), resourceItem.textOffset() +
                            resourceItem.surfaceForm().name().length());
                    entities.add(new NIFBean(entity));
                });
            }
        }


    }


    public void entity( List<SurfaceFormOccurrence> occs) {

        NIFBean.NIFBeanBuilder entity = new NIFBean.NIFBeanBuilder();


        if (occs != null) {

            occs.forEach(resourceItem -> {
                this.context(resourceItem.context().text());
                entity.mention(resourceItem.surfaceForm().name());
                entity.beginIndex(resourceItem.textOffset());
                entity.endIndex(resourceItem.textOffset() + resourceItem.surfaceForm().name().length());
                entity.annotator(configuration.getSpotlightURL());
                //entity.taIdentRef(resourceItem.getUri());
                // entity.types(resourceItem.typesList());
                //entity.score(resourceItem.score());
                entity.context(baseURI, resourceItem.textOffset(), resourceItem.textOffset() +
                        resourceItem.surfaceForm().name().length());
                entities.add(new NIFBean(entity));
            });
        }


    }

    public String getNIF(String outputFormat) {

        List<NIFBean> entitiesToProcess = new ArrayList<>(entities.size());

        if(beanContext != null) {
            entitiesToProcess.add(beanContext);
            entitiesToProcess.addAll(entities);
        }

        NIF nif = new NIF21(entitiesToProcess);

        return process(nif, outputFormat);

    }

    private String process(NIF nif, String outputFormat) {

        if (outputFormat != null && SemanticMediaType.TEXT_TURTLE.equalsIgnoreCase(outputFormat)) {
            return nif.getTurtle();
        } else if (outputFormat != null && SemanticMediaType.APPLICATION_LD_JSON.equalsIgnoreCase(outputFormat)) {
            return nif.getJSONLD(configuration.getJsonContext());
        } else if (outputFormat != null && SemanticMediaType.APPLICATION_N_TRIPLES.equalsIgnoreCase(outputFormat)) {
            return nif.getNTriples();
        } else  if (outputFormat != null && SemanticMediaType.APPLICATION_XML_RDF.equalsIgnoreCase(outputFormat)){
            return nif.getRDFxml();
        }

        return nif.getTurtle();

    }
}
