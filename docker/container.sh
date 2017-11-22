sudo docker stop SPARK
sudo docker rm -f SPARK
sudo docker run -ti --name SPARK -p 4040:4040 -p 8080:8080 -p 8081:8081 -v /vagrant/apps/wordcount-spark-streaming:/myapp spark /bin/bash
