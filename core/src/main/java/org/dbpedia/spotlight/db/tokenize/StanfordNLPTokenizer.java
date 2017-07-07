package org.dbpedia.spotlight.db.tokenize;

import edu.stanford.nlp.ling.CoreAnnotations;
import edu.stanford.nlp.ling.CoreLabel;
import edu.stanford.nlp.pipeline.Annotation;
import edu.stanford.nlp.pipeline.StanfordCoreNLP;
import edu.stanford.nlp.util.CoreMap;
import opennlp.tools.util.Span;
import org.dbpedia.spotlight.db.model.Stemmer;
import scala.collection.Seq;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/**
 * Created by wlu on 06.07.17.
 */
public class StanfordNLPTokenizer extends BaseStringTokenizer {
    private StanfordCoreNLP corenlp;
    private Set<String> stopwords = new HashSet<>();

    public StanfordNLPTokenizer(Stemmer stemmer) {
        super(stemmer);
        Properties props = new Properties();
        try {
            props.load(getClass().getResourceAsStream("/StanfordCoreNLP-arabic.properties"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        try (BufferedReader br = new BufferedReader(new InputStreamReader(getClass().getResourceAsStream("/arabic-stopwords.txt")))) {
            String line;
            while ((line = br.readLine()) != null) {
                stopwords.add(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        this.corenlp = new StanfordCoreNLP(props);
    }

    @Override
    public Seq<String> tokenizeUnstemmed(String text) {
        return scala.collection.JavaConversions.asScalaBuffer(getTokens(text));
    }

    public List<String> getTokens(String text) {
        Annotation document = new Annotation(text);
        this.corenlp.annotate(document);

        List<CoreMap> sentences = document.get(CoreAnnotations.SentencesAnnotation.class);
        System.out.println(sentences.size());

        List<String> words = new ArrayList<>();
        for(CoreMap sentence: sentences) {
            for (CoreLabel token: sentence.get(CoreAnnotations.TokensAnnotation.class)) {
                String word = token.word();
                words.add(word);
            }
        }
        return words;
    }

    public List<CoreMap> getAnnotation(String text) {
        Annotation document = new Annotation(text);
        this.corenlp.annotate(document);

        List<CoreMap> sentences = document.get(CoreAnnotations.SentencesAnnotation.class);
        return sentences;
    }

    @Override
    public Span[] tokenizePos(String text) {
        return new Span[0];
    }

    @Override
    public void setThreadSafe(boolean isThreadSafe) {

    }
}
