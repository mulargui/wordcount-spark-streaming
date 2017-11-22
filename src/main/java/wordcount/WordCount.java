/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package wordcount;

import java.util.Arrays;
import java.util.regex.Pattern;

import scala.Tuple2;

import org.apache.spark.SparkConf;
import org.apache.spark.api.java.StorageLevels;
import org.apache.spark.streaming.Durations;
import org.apache.spark.streaming.api.java.JavaDStream;
import org.apache.spark.streaming.api.java.JavaPairDStream;
import org.apache.spark.streaming.api.java.JavaReceiverInputDStream;
import org.apache.spark.streaming.api.java.JavaStreamingContext;

/*
 * Counts words in UTF8 encoded, '\n' delimited text.
 */
 
public final class WordCount {
	private static final Pattern SEPARATOR = Pattern.compile(" ");
	private static final int BATCHTIME = 1;

	public static void main(String[] args) throws Exception {

		// Create the context with a 1 second batch size
		SparkConf sparkConf = new SparkConf().setAppName("WordCount").setMaster("local[2]");
		JavaStreamingContext ssc = new JavaStreamingContext(sparkConf, Durations.seconds(BATCHTIME));

		JavaDStream<String> words = ssc.textFileStream("file:///myapp/data") //; words.print();
			.flatMap(x -> Arrays.asList(SEPARATOR.split(x)).iterator());
		
		JavaPairDStream<String, Integer> wordCounts = words.mapToPair(s -> new Tuple2<>(s, 1))
			.reduceByKey((i1, i2) -> i1 + i2);

		wordCounts.print();
	
		ssc.start();
		ssc.awaitTermination();
	}
}