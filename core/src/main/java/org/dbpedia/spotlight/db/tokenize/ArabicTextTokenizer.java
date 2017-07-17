package org.dbpedia.spotlight.db.tokenize;

import edu.stanford.nlp.ling.CoreAnnotations;
import edu.stanford.nlp.ling.CoreLabel;
import edu.stanford.nlp.util.CoreMap;
import org.dbpedia.spotlight.db.model.Stemmer;
import org.dbpedia.spotlight.db.model.StringTokenizer;
import org.dbpedia.spotlight.db.model.TokenTypeStore;
import org.dbpedia.spotlight.model.Feature;
import org.dbpedia.spotlight.model.Text;
import org.dbpedia.spotlight.model.Token;
import org.dbpedia.spotlight.model.TokenType;

import java.io.File;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by wlu on 06.07.17.
 */
public class ArabicTextTokenizer extends BaseTextTokenizer {
    private ArabicStringTokenizer arabicStringTokenizer;
    private Set<String> stopwords = new HashSet<>();

    public ArabicTextTokenizer(TokenTypeStore tokenTypeStore, Stemmer stemmer, File stopwordsFile) {
        super(tokenTypeStore, stemmer);
        arabicStringTokenizer = new ArabicStringTokenizer(stemmer, stopwordsFile);
    }

    @Override
    public scala.collection.immutable.List<Token> tokenize(Text text) {
        List<Token> toks = new ArrayList<>();

        for(CoreMap sentence: this.arabicStringTokenizer.getAnnotation(text.text())) {
            List<CoreLabel> tokens = sentence.get(CoreAnnotations.TokensAnnotation.class);
            for (int i = 0; i < tokens.size(); i++) {
                CoreLabel token = tokens.get(i);
                String word = token.word();
                int offset = token.get(CoreAnnotations.CharacterOffsetBeginAnnotation.class);
                Token tok;
                if (!stopwords.contains(word)) {
                    tok = new Token(word, offset, getStemmedTokenType(word));
                } else {
                    tok = new Token(word, offset, TokenType.STOPWORD());
                }

                if (i == tokens.size()-1) {
                    tok.setFeature(new Feature("end-of-sentence", true));
                }

                toks.add(tok);
            }
        }


        return scala.collection.JavaConversions.asScalaIterable(toks).toList();
    }

    @Override
    public StringTokenizer getStringTokenizer() {
        return this.arabicStringTokenizer;
    }
}
