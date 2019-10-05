package com.kexinxin.util.hadoop;

import java.io.IOException;
import java.net.URI;
import java.util.List;
import java.util.StringTokenizer;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.mapreduce.Reducer;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;
import org.apache.lucene.index.IndexWriter;

import com.kexinxin.util.crawler.Crawler;
import com.kexinxin.util.crawler.CrawlerLuceneIndexService;

public class CrawlerMapReduce {

	public static class TokenizerMapper extends Mapper<Object, Text, Text, IntWritable> {

		private final static IntWritable one = new IntWritable(1);
		private Text word = new Text("ewr");

		public void map(Object key, Text value, Context context) throws IOException, InterruptedException {
			StringTokenizer itr = new StringTokenizer(value.toString());
			String CrawlerName = null;
			if (itr.hasMoreTokens()) {
				word.set(itr.nextToken());
				System.out.println(word.toString());
				CrawlerName = word.toString();
			}
			Crawler crawler;
			try {
				crawler = (com.kexinxin.util.crawler.Crawler) Class.forName("com.kexinxin.util.crawler."+CrawlerName).newInstance();
				crawler.start();
			} catch (InstantiationException e1) {
				e1.printStackTrace();
			} catch (IllegalAccessException e1) {
				e1.printStackTrace();
			} catch (ClassNotFoundException e1) {
				e1.printStackTrace();
			} catch (Exception e) {
				e.printStackTrace();
			}
			context.write(word, one);
		}
	}

	public static class IntSumReducer extends Reducer<Text, IntWritable, Text, IntWritable> {
		private IntWritable result = new IntWritable();

		public void reduce(Text key, Iterable<IntWritable> values, Context context)
				throws IOException, InterruptedException {
			try {
				IndexWriter indexWriter=CrawlerLuceneIndexService.getIndexWriter();
				if(indexWriter!=null){
					indexWriter.commit();
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
			
		}
	}
	
	public static void startMapReduceCrawler() throws IllegalArgumentException, IOException, ClassNotFoundException, InterruptedException{
		Configuration conf = new Configuration();
		String input = "hdfs://localhost:9000/user/kexinxin/crawlDistribute";
		String output = "hdfs://localhost:9000/output";

		Path path = new Path(output);
		FileSystem fileSystem = FileSystem.get(URI.create("hdfs://localhost:9000"), conf);// 根据path找到这个文件
		fileSystem.delete(path, true);
		fileSystem.close();

		Job job = Job.getInstance(conf, "word count");
		job.setJarByClass(CrawlerMapReduce.class);
		job.setMapperClass(TokenizerMapper.class);
		job.setCombinerClass(IntSumReducer.class);
		job.setReducerClass(IntSumReducer.class);
		job.setOutputKeyClass(Text.class);
		job.setOutputValueClass(IntWritable.class);

		FileInputFormat.addInputPath(job, new Path(input));
		FileOutputFormat.setOutputPath(job, new Path(output));
		//System.exit(job.waitForCompletion(true) ? 0 : 1);
		job.waitForCompletion(true);
	}
	
	public static void main(String[] args) throws Exception {
		Configuration conf = new Configuration();
		String input = "hdfs://localhost:9000/user/kexinxin/crawlDistribute";
		String output = "hdfs://localhost:9000/output";

		Path path = new Path(output);
		FileSystem fileSystem = FileSystem.get(URI.create("hdfs://localhost:9000"), conf);// 根据path找到这个文件
		fileSystem.delete(path, true);
		fileSystem.close();

		Job job = Job.getInstance(conf, "word count");
		job.setJarByClass(CrawlerMapReduce.class);
		job.setMapperClass(TokenizerMapper.class);
		job.setCombinerClass(IntSumReducer.class);
		job.setReducerClass(IntSumReducer.class);
		job.setOutputKeyClass(Text.class);
		job.setOutputValueClass(IntWritable.class);

		FileInputFormat.addInputPath(job, new Path(input));
		FileOutputFormat.setOutputPath(job, new Path(output));
		//System.exit(job.waitForCompletion(true) ? 0 : 1);
		job.waitForCompletion(true);
	}
}
