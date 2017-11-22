# wordcount-spark-streaming

Small experiment to get my hands dirty on spark streaming in a container. 
The jar file contains two classes. One of them reads files from the data directory - you need to drop AND TOUCH the file AFTER atarting the app. You can use the data.csv file for thsi purpose.
The other class reads data from a socket. We use nc to send data to the spark app and use data.csv to feed nc
the run.sh script has examples on how to build and run the different classes.