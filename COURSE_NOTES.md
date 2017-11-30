# Helpful notes for the online course "Getting Started With Apache Cassandra"

I've followed the the online Udemy course [Getting Started With Apache Cassandra](https://www.udemy.com/apache-cassandra/), here you have [prove](https://www.udemy.com/certificate/UC-QVPGZFJR/). The course is interesting and cover many aspects of the Apache Cassandra database. However, the version it uses is 2.x while the current is 3.11.1, and there exist some references to tools that do not exist or have changes.

Here you have some notes I wrote down to help any student to follow the course and use Apache Cassandra 3.11.1.

## Installing Apache Cassandra 3.11 using Debian packages (apt tool)

Install Java 8 required by Cassandra 3.11 (Oracle version)

```
sudo add-apt-repository ppa:webupd8team/java
sudo apt-get update
sudo apt-get install oracle-java8-installer
```

Install Apache Cassandra 3.11 and tools (sstabledump)

```
echo "deb http://www.apache.org/dist/cassandra/debian 311x main" | sudo tee -a /etc/apt/sources.list.d/cassandra.sources.list
sudo apt-get install curl
curl https://www.apache.org/dist/cassandra/KEYS | sudo apt-key add -
sudo apt-get update
sudo apt-get install cassandra
sudo apt-get install cassandra-tools
```

## Location of folders

Location of files already created with correct permissions:
- Configuration files: `/etc/cassandra/`
- Data folder: `/var/lib/cassandra/`
- Logs folder: `/var/log/cassandra/`
- Start-up options (heap size, etc) can be configured in: `/etc/default/cassandra`
- Cassandra log file path is specified in file: `/etc/cassandra/logback.xml`

## Running Cassandra

You can start Cassandra with: `sudo service cassandra start`

You can stop it with: `sudo service cassandra stop`

Check if it is running: `sudo service cassandra status`

Check Cassandra status: `sudo nodetool status`

However, normally the service will start automatically. For this reason be sure to stop it if you need to make any configuration changes.

## Tools

**nodetool** gives information on the running instances of Cassandra.
* More information on nodetools commands: `sudo nodetool info`
* See the list of token ranges a node is handling: `sudo nodetool ring`

**cqlsh** is a command line shell for interacting with Cassandra through CQL: `cqlsh`


The tools `cassandra-cli` and `sstable2json` are not used anymore. Now you can use `sstabledump` instead: https://www.datastax.com/dev/blog/debugging-sstables-in-3-0-with-sstabledump

To dump a data file in JSON use:

```
sudo sstabledump <path_to_-Data.db_file>
```
	
To dump the Internal Representation Format just add the `-d` flag:

```
sudo sstabledump <path_to_-Data.db_file> -d
```

## External connection	

The Client Drivers can be found here: http://cassandra.apache.org/doc/latest/getting_started/drivers.html

To allow external connections I've used this configuration in file `cassandra.yaml` (need some review):

```
start_native_transport: true
native_transport_port: 9042
rpc_address: 0.0.0.0
broadcast_rpc_address: 192.168.154.130
```
