package org.dbpedia.spotlight.spot

import java.io._

import com.officedepot.cdap2.collection.CompactHashSet
import org.dbpedia.spotlight.io.WortschatzParser
import org.dbpedia.spotlight.model.SurfaceFormOccurrence
import org.dbpedia.spotlight.log.SpotlightLog

import scala.collection.JavaConversions._


/**
 * This is a temporary workaround to the common words problem.
 * It works as a blacklist of surface forms.
 * It currently packs also a parser and a serializer besides the filter.
 * TODO factor out the parser/serializer to a class in io or util.
 *
 * See also: SurfaceFormWhitelistSelector
 *
 * @author pablomendes
 */
class NonCommonWordSelector(val filename: String, val load: Boolean = true) extends UntaggedSpotSelector {

    val extension = ".CompactHashSet";
    val file = new File(filename+extension);

    val commonWords = if (load && (file exists)) unserialize else { serialize(parse); unserialize }

    def parse() = {
        WortschatzParser.parse(filename, 100)
    }

    def serialize(commonWords: CompactHashSet[String]) {
        SpotlightLog.info(this.getClass, " saving common words dictionary to disk ")
        val out = new ObjectOutputStream(new FileOutputStream(file))
        out.writeObject(commonWords)
        out.close()
    }

    def unserialize = {
        SpotlightLog.info(this.getClass, " loading serialized dictionary of common words ") 
        var set = new CompactHashSet[String]
        try {
            val in = new ObjectInputStream(new FileInputStream(file))
            set = in.readObject match {
                case s: CompactHashSet[String] => s
                case _ => throw new ClassCastException("Serialized Object is not of type CompactHashSet[String] ");
            }
            in.close();
        } catch {
            case e: Exception => SpotlightLog.info(this.getClass, "Error loading CommonWords %s", e.getMessage);
        }
        set
    }

    def isCommonWord(occ: SurfaceFormOccurrence) = {
        commonWords contains occ.surfaceForm.name
    }

    /**
     * Select only spots that are not common words (this is a workaround)
     */
    def select(occs: java.util.List[SurfaceFormOccurrence]) : java.util.List[SurfaceFormOccurrence] = {
        occs.filter( o => !isCommonWord(o) );
    }

    def main(args: Array[String]) {
        def usage = println(" Usage: scala -cp $CP NonCommonWordSelector words.txt ")
        args(0) match {
            case file: String => {
                new NonCommonWordSelector(file)
            }
            case _ => usage
        }
    }

}
