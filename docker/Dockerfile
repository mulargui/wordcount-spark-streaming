FROM ubuntu:latest

# Install Java
RUN DEBIAN_FRONTEND=noninteractive apt-get -qq update && apt-get -qq -y install default-jre default-jdk
ENV JAVA_HOME /usr/lib/jvm/default-java
ENV PATH $PATH:$JAVA_HOME/bin	

# Install maven
RUN apt-get -qq install -y maven

# Install Scala
# RUN  apt-get -qq install -y wget \
# 	&& wget -q http://downloads.lightbend.com/scala/2.11.8/scala-2.11.8.deb \
# 	&& dpkg -i scala-2.11.8.deb
# ENV SCALA_HOME  /usr/local/scala
# ENV PATH $PATH:$SCALA_HOME/bin	

# Install Python
# installed by default in ubuntu (python3)
ENV PYSPARK_PYTHON python3

# Install R
# RUN apt-get -qq install -y r-base

# Install Spark
RUN  apt-get -qq install -y curl \
	&& curl -s http://mirrors.koehn.com/apache/spark/spark-2.2.0/spark-2.2.0-bin-hadoop2.7.tgz | tar -xz -C /usr/local/ \
	&& ln -s /usr/local/spark-2.2.0-bin-hadoop2.7 /usr/local/spark \
	&& sed 's/INFO/ERROR/' /usr/local/spark/conf/log4j.properties.template > /usr/local/spark/conf/log4j.properties \
	&& sed -i 's/WARN/ERROR/' /usr/local/spark/conf/log4j.properties
ENV SPARK_HOME /usr/local/spark
ENV PATH $PATH:$SPARK_HOME/bin

#other utilities needed
RUN  apt-get -qq install -y netcat

# Expose ports for monitoring.
# SparkContext web UI on 4040 -- only available for the duration of the application.
# Spark master’s web UI on 8080.
# Spark worker web UI on 8081.
EXPOSE 4040 8080 8081

# ENTRYPOINT ["/usr/local/spark/bin/spark-shell --master local[2]"]	
# ENTRYPOINT ["/usr/local/spark/bin/spark-submit --master local[2] program params"]	
