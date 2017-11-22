#mvn package
#spark-submit --class wordcount.WordCount --master local[2] /myapp/target/wordcount-1.0.jar
nc -l -i 1 -p 9999 < data.csv &
spark-submit --class wordcount.NetWordCount --master local[2] /myapp/target/wordcount-1.0.jar localhost 9999
