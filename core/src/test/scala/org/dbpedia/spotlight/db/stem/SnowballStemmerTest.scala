package org.dbpedia.spotlight.db.stem

import org.junit.Assert._
import org.junit.Test
/**
 * Tests SnowballStemmer
 * @author dav009
 */
class SnowballStemmerTest  {

  @Test
  def englishStemmer(){
    val snowballStemmer = new SnowballStemmer("EnglishStemmer")
    assertTrue( "buy".equals(snowballStemmer.stem("buying")))
    assertTrue( "poni".equals(snowballStemmer.stem("ponies")))
  }
}
