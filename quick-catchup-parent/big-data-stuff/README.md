export MAVEN_OPTS="-Xmx2g -XX:MaxPermSize=512M -XX:ReservedCodeCacheSize=512m"


1. Orange: 
  Spendlytics (reading)


2. Voice Banking
3. Customer contact History

Kanban to create stories

YARN

HIVE / Impala

Apache Hadoop
=============
sftp -r hadoop@192.168.56.101 to copy files onto hadoop

Run in standalone mode.
Create your project and build jar file
make sure everything you copying onto ubunto is under same user access i.e. hadoop user so hadoop2.x versoin copied over is also with same user - chown -R new_owner_name dir_location_to_be_changed 	
once everything ready - run your program 
e.g.  ./bin/hadoop jar /home/hadoop/tmp/map-reduce-stuff-1.0-SNAPSHOT.jar com.stuff.bigdaa.MaxTemp /home/hadoop/tmp/input/data /home/hadoop/tmp/output
Name node: to hold meta information about files
Data node: to hold the actual data. 
Block cache: normally datanode reads from disk but for frequently used data, it's cahced in datanode memory called block cache
HDFS fedration: each namenode manages namespace (metadata for namespace), block pool - containing all the blocks for the files in namespace.
Secondary namenode: it acts as secondary namenode as name suggests and holds the check points for namenode (namespace, edit logs), but still it's SPOF. in case of failure admin starts new namenode which takes time to load up
HDFS HA (high avaliablity): to avoid above problem, HA introduced active-standby config. so active namenode has to have edit logs to shared storage so standby can access it. that's mainly done by QJM.
QJM: Quorum journal manager, run group of journal nodes and keep replica of 3 for edit logs. It's kind of similar to zookeeper but it does not uses it. It allows only one namenode to write to edit logs
Failover controller: When active node fails and standby node being down (rare chances), in this case switching from standby to active is done by failover controller. default uses zookeeper to ensure only one namenode is active
Fencing: in case of slow n/w active node is still active but failover controller thinks that's dead and causes failover transitions. HA implementation ensure that prev active node is prevented from doing any damage called fencing

Setup environment variables: HADOOP_HOME, HADOOP_CONF_DIR, HADOOP_USER_NAME

if you want to run something out of HADOOP_CONF_DIR then use params whilst running i.e. config directory is pointing to pseudo, but in order to run debugger use below which uses local job runner and should be able to put breakpoint
  hadoop jar hadoop-examples.jar com.stuff.bigdata.MaxTempDriver -fs file:/// -jt local \input\data \output


Name node - manages the meta data of the files, by formatting it goes to hdfs-site.xml file to locate the root and format the meta data
Data node - stores the actual data
clients contact namenode for file metadata or file modif and perform i/o directly with datanodes. both nodes have built in web to check cluster status, http://namenode-name:50070/
namenode reads fsimage on startup and merges edits(from normal files system log) to make any changes which is the job of checkpointnode/secondarynode/backupnode

To configure yarn, mapred-site.xml (framework to use), yarn-site.xml, resource manager and node manager are started by sbin/start-yarn.sh

In cluster, one machine is kept of resource manager, one for namenode, These are masters. web-app proxy server, mapreduce job history server can be on dedicated/shared. Rest machine acts as dataNode and nodeManager both.

-> core-site.xml
    fs.defaultFS NameNode URI
-> hdfs-site.xml	
    dfs.namenode.name.dir namdenode stores namespace and trx logs
	dfs.hosts / dfs.hosts.exclude list of datanode allowed/exclueded
	dfs.namenode.handler.count namenode server threads to handle datanode RPC
	dfs.datanode.data.dir datanode stores blocks

% start-dfs.sh
% start-yarn.sh
% mr-jobhistory-daemon.sh start historyserver

http://192.168.56.101:8088/cluster :: resource manager	
http://192.168.56.101:50070/dfshealth.html#tab-overview :: name node
http://192.168.56.101:19888/ :: history server

H1 had problems with jobtracker having all the task like resource management along with data processing leads to SPOF, 4000nodes, 40000 tasks
H2 is improved using resource manager, node manager. 10000 nodes, 100000 tasks
 YARN, snapshot, HA,HDFS Fedration
Yarn Schedules: 
 FIFO: large jobs takes overs resources so small job are stuck
 Capacity Scheduler: as soon as job is submitted a separate dedicated queue allows small job to start as soon it's submitted which means large jobs finish later. Queue capacity is reserved for the jobs in that queue
 Fair Scheduler: As soon as job is submitted resources are divided among running jobs and when done given to the running the job.
 Node manager default send heartbeat 1 per sec to resource manager (that has information about NM running containers, resources available for new containers)

Serailization: Structured object to byte stream is seralization and reverse is deseralization
Sequence file: log binary types. it has binary key-value, key is timestamp (longwritable) and value writable. These files have sync points so that reading can be done at any point in case reader crashes. seek method has to know
               the exact sync point otherwise gives error, to avoid use sync methhod which automatically takes you to the next sync point.
 
mvn eclipse:eclipse -DdownloadSources=true -DdownloadJavadocs=true


Apache Avro
===========
Data serialization system. As data is stored in file, it's schema is stored with it which makes it faster. Avro has column oriented format called Trevni

Apache Parquet
==============
In RDBMS each row has unique id generated by system. An index is always associated to the primmary key (which is row id) of that table

Column oriented tables, each column values are associated to system generated rowid.

parquet is build in mind with compression and encoding schemes on column data. column oriented file format based on google's Dremel

Apache Crunch
=============
This is to create data pipeline on top of mapreduce. We can transform data beforing ending up in hdfs

public class Tokenizer extends DoFn<String, String> {
  private static final Splitter SPLITTER = Splitter.onPattern("\\s+").omitEmptyStrings();

  @Override
  public void process(String line, Emitter<String> emitter) {
    for (String word : SPLITTER.split(line)) {
      emitter.emit(word);
    }
  }
}
----
  String inputPath = args[0];
  String outputPath = args[1];

  // Create an object to coordinate pipeline creation and execution.
  Pipeline pipeline = new MRPipeline(WordCount.class, getConf());
  
  // Reference a given text file as a collection of Strings.
  PCollection<String> lines = pipeline.readTextFile(inputPath);
  
  // Define a function that splits each line in a PCollection of Strings into
  // a PCollection made up of the individual words in the file.
  // The second argument sets the serialization format.
  PCollection<String> words = lines.parallelDo(new Tokenizer(), Writables.strings());
  
  // Take the collection of words and remove known stop words.
  PCollection<String> noStopWords = words.filter(new StopWordFilter());
  
  // The count method applies a series of Crunch primitives and returns
  // a map of the unique words in the input PCollection to their counts.
  PTable<String, Long> counts = noStopWords.count();
  
  // Instruct the pipeline to write the resulting counts to a text file.
  pipeline.writeTextFile(counts, outputPath);
  
  // Execute the pipeline as a MapReduce.
  PipelineResult result = pipeline.done();
  return result.succeeded() ? 0 : 1;
  
Apache Hive
===========
For data scientists by facebook. not suitable for machine learning algo. HiveQL Same like SQL. When query is issued then compliance is check against schema i.e. at runtime. about table meta data is stored in metastore usually stored
in relational database MySQL, Derby. Managed and external tables. When data is loaded from hdfs, file is moved to hive warehouse. In external table, the link from metastore is drawn to the actual data. on drop the link is eliminated
not the file as oppose to managed. partitioning and bucketing are way to load data
when creating table by using partition i.e. column, that means whilst loading the data, datasets would be created by partition id under hivewarehouse i.e. hdfs:/hive/warehouse/records/prjId=PRJ001/file1
																																													   /prjId=PRJ002/file1	
Data warehouse facilitates querying and managing large datasets. It has similar SQL commands called HiveQL

LOAD DATA LOCAL INPATH '<path>/u.data'
OVERWRITE INTO TABLE u_data;

CREATE TABLE u_data_new (
  userid INT,
  movieid INT,
  rating INT,
  weekday INT)
ROW FORMAT DELIMITED
FIELDS TERMINATED BY '\t';

add FILE weekday_mapper.py;

INSERT OVERWRITE TABLE u_data_new
SELECT
  TRANSFORM (userid, movieid, rating, unixtime)
  USING 'python weekday_mapper.py'
  AS (userid, movieid, rating, weekday)
FROM u_data;

SELECT weekday, COUNT(*)
FROM u_data_new
GROUP BY weekday;

Apache Giraph
=============
Interactive graph processing framework.

Apache Kafka
============
http://kafka.apache.org/documentation.html#introduction
It's messaging system based on pub/sub as commit log service

Apache Flume
============
service used to collecting/gathering/moving large amount of log data. It has agents [source, channel, sink]
https://flume.apache.org/FlumeDeveloperGuide.html

Kafka wins above Flume, 
Kafka is scalable without affecting performance, It's because does not track the consumers
pull model and other is push model
message durability, if agent dies(not recoverable) and message are not in sync then it's gone 

Apache Zookeeper
================
It's single service which maintains config, sync, group services. It allows the distributed services/processes to coordinate with each other. It creates znode on server and then maintains list of clients in heirarchical order

Apache Oozie
============
it's scheduler/workflow to manage hadoop jobs. triggered by time and data availabilty

Apache Solr
===========
Distributed indexing, replication, load balancing query. Variety of data is ingested into multiple collections. When data is fed into solr, it will index data using POST command. This is done to have search etc 

Apache Spark
============
Data analysis tool. Spark libraries MLlib, Spark SQL)

1. Easy to use
2. Fast
3. Generic engine (SQL queries, text processing, machine learning)

Data scientist - building model from data
Engineer - setup cluster, spark shell to solve parallel processing problems

Apache Pig
==========
By yahoo for data scientists as don't want to write map/red programs. join and complex mapred problems are solved with ease. It would be slower than map/red. It scan whole dataset but not small portions. Pig latin is language i.e. data
flow language



