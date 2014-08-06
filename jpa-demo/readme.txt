+---------------------------------------------------+
	- Create a jboss module for mysql connector		|
+---------------------------------------------------+
<module xmlns="urn:jboss:module:1.1" name="com.mysql">
	<resources>
		<resource-root path="mysql-connector-java-5.1.31.jar" />
	</resources>
	<dependencies>
		<module name="javax.api" />
		<module name="javax.transaction.api" />
		<module name="javax.servlet.api" optional="true" />
	</dependencies>
</module>

+-------------------------------------------------------------------+
	- Create a data source configuration in standalone-full.xml		|
+-------------------------------------------------------------------+
<datasource jndi-name="java:jboss/datasources/MySQLDS" pool-name="MySQLDS" enabled="true" use-java-context="true">
    <connection-url>jdbc:mysql://localhost:3306/test</connection-url>
    <driver>mysql</driver>
    <security>
        <user-name>root</user-name>
        <password>root</password>
    </security>
</datasource>

+-----------------------------------+
	- Execute arquillian tests		|
+-----------------------------------+
mvn clean test -Parq-jbossas-remote