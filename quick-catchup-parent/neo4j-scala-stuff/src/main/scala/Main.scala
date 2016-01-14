import java.io._

import org.neo4j.graphdb.GraphDatabaseService
import org.neo4j.graphdb.factory.GraphDatabaseFactory

/**
 * Test Neo4j
 */
object Main {

  val DB_PATH: String = "D:\\DevTools\\Neo4j CE 2.3.0\\data"

  def main(args: Array[String]) {

    val neo: GraphDatabaseService = new GraphDatabaseFactory().newEmbeddedDatabase(new File(DB_PATH))


    val util: CQLUtil = new CQLUtil

    util.createNodes(neo)
    util.printNodes(neo)

    util.deleteNodeByName(neo, "first")
    util.deleteNodeByName(neo, "second")
    util.printNodes(neo)
  }

}
