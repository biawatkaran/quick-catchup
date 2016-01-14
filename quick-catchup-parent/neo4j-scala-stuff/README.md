# Neo4j CQL

To add or delete or print nodes in graph database 

***Basics:***
 
 Neo4j is graph database. This sample code is written in scala to test the basic operation in neo4j using their 
 Java Api and CQL query
 
 CQL commands
 
 * Node creation 
           
           create (node:Label {attr-name:"attr-val"})
           create(node:Label)-[relation-name:relation-type]->(another-node:Label)
 
 * print all the nodes 
 
           Match (n) Return n

***References:***

           http://neo4j.com/docs/stable/tutorials-java-embedded-new-index.html
           http://www.tutorialspoint.com/neo4j/neo4j_native_java_api_example.htm
 
  