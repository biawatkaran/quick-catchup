# Apache Axis2

**Stock Application** 

To get price of a symbol in market and to update the price 

***Run:***

* Run mvn clean install - will generate wsdl file and war file
* Now run any web/app server tomcat, place the apache-axis2-stuff.war file inside webapps folder of Tomcat
* start the server, we should be able to launch our webservice


***Functions:***

* [Check WSDL File](http://localhost:8080/apache-axis2-stuff/services/StockQuoteService?wsdl)
* [Get Price](http://localhost:8080/apache-axis2-stuff/services/StockQuoteService/getPrice?symbol=IBM)
* [Update](http://localhost:8080/apache-axis2-stuff/services/StockQuoteService/update?symbol=IBM&price=40.0)
 
 In case of update the code is written not to return anything which is why update will display blank page. 
 This can be easily implemented by returning some OK output from that method. Please note you need to change services.xml
 accordingly


