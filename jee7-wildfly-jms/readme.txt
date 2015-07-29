----------------------------------------------------------------------------------------------------------------------------
- create application user with guest role
----------------------------------------------------------------------------------------------------------------------------
- start jboss instance
$ standalone.bat -c standalone-full.xml
----------------------------------------------------------------------------------------------------------------------------	
- open another console and start jboss cli	
$ jboss-cli.bat --connect
$ jms-queue add --queue-address=TestQueue --entries=queue/test
$ jms-topic add --topic-address=TestTopic --entries=topic/test

$ jms-queue add --queue-address=RequestQueue --entries=queue/requestQueue
$ jms-queue add --queue-address=ResponseQueue --entries=queue/responseQueue
----------------------------------------------------------------------------------------------------------------------------
- jms queue and topic entry will be added in standalone-full.xml under messaging subsystem for HornetQ server.
<subsystem xmlns="urn:jboss:domain:messaging:1.4">
    <hornetq-server>
		<jms-destinations>
		    <jms-queue name="TestQueue">
		        <entry name="queue/test"/>
		    </jms-queue>
		    <jms-topic name="TestTopic">
		        <entry name="topic/test"/>
		    </jms-topic>
    	</jms-destinations>
	</hornetq-server>	
</subsystem>		
----------------------------------------------------------------------------------------------------------------------------