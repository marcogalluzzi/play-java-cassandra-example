# Play Framework and Apache Cassandra example application

This is a simple application that shows how Play can access Apache Cassandra. 

The functionality is based on the two examples shown in the online Udemy course [Getting Started With Apache Cassandra](https://www.udemy.com/apache-cassandra/). However, the course's examples have been changed to use the last version of Cassandra (currently the version 3.11.1) and the Play Framework technology using an MVC pattern. See my course notes [here](COURSE_NOTES.md) if you want to follow this course and also use the last version of Cassandra. 

The base Play code I've used to start this example comes from Lightbend and can be found here: https://github.com/playframework/play-java-starter-example

The versions used in this example are:
- Play Framework 2.6.7
- DataStax Java Driver 3.3.0 
- Apache Cassandra 3.11.1

## Requirements

**Step 1**: You need a running instance of Apache Cassandra 3.11.1 with the following keyspaces and tables (TO DO adding sources):
- Keyspace: home_security
  - Tables: activity, home
- Keyspace: vehicle_tracker
  - Tables: location
   
**Step 2**: Change the file `additional.conf` to set the following values:
   - `cassandra.contact-point`: an IP address of your Cassandra cluster
   - `googlemap.api-key`: the google maps API key in order to show data on a map

## Running

After running the application with `sbt run` we have 3 different URL to see the example working.
1) `http://localhost:9000/` shows an example page with some info about Play. The interesting thing is that this page already test the connection with Cassandra. In the page header you will see the Apache Cassandra version if the connection goes well.
2) `http://localhost:9000/track` displays the Vehicle Tracking example.
3) `http://localhost:9000/alarms` displays the Home Alarm System example.
