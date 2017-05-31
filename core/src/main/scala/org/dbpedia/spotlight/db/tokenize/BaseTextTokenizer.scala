package org.dbpedia.spotlight.db.tokenize

import org.dbpedia.spotlight.db.model.{Stemmer, StringTokenizer, TextTokenizer, TokenTypeStore}
import org.dbpedia.spotlight.model.{Feature, Text, Token, TokenType}


abstract class BaseTextTokenizer(tokenTypeStore: TokenTypeStore, stemmer: Stemmer) extends TextTokenizer {

  def tokenize(text: Text): List[Token]

  def tokenizeMaybe(text: Text) {
    if(text.feature("tokens").isEmpty)
      text.setFeature(new Feature("tokens", tokenize(text)))
  }

  protected def getStemmedTokenType(token: String): TokenType = tokenTypeStore.getTokenType(stemmer.stem(token))

  def getStringTokenizer: StringTokenizer

}
