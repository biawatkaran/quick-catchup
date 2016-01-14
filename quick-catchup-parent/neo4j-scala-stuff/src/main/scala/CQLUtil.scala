import org.neo4j.cypher.{ExtendedExecutionResult, ExecutionEngine}
import org.neo4j.graphdb._

/**
 * Util for various graph operations.
 */
class CQLUtil {

  def createNodes(neo: GraphDatabaseService): Unit = {

    var first : Node = null
    var second : Node = null

    val tx: Transaction = neo.beginTx()

    implicit def string2relationshipType(x: String) = DynamicRelationshipType.withName(x)

    try{

      first = neo.createNode(DynamicLabel.label("USER"))
      first.setProperty("name", "first")

      second = neo.createNode(DynamicLabel.label("USER"))
      second.setProperty("name", "second")

      first.createRelationshipTo(second, "isRelatedTo": DynamicRelationshipType)

      tx.success()
      println("added nodes")
    } catch {

      case e: Exception => println(e)
    } finally {
      tx.finish
      println("finished transaction")
    }

  }

  def deleteNodeByName (neo: GraphDatabaseService, node: String) : Unit ={

    val engine = new ExecutionEngine(neo)

    engine.execute("MATCH (n {name:'" + node + "'}) DETACH DELETE n")
  }

  def printNodes (neo: GraphDatabaseService) : Unit = {

    val engine = new ExecutionEngine(neo)
    val result: ExtendedExecutionResult = engine.execute("MATCH (n) RETURN n LIMIT 25")
    println(result.dumpToString)
  }

}
