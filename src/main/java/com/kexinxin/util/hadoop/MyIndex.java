package com.kexinxin.util.hadoop;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.cn.smart.SmartChineseAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field.Store;
import org.apache.lucene.document.StringField;
import org.apache.lucene.document.TextField;
import org.apache.lucene.index.DirectoryReader;
import org.apache.lucene.index.IndexReader;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexWriterConfig;
import org.apache.lucene.index.Term;
import org.apache.lucene.queryparser.classic.QueryParser;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.Query;
import org.apache.lucene.search.ScoreDoc;
import org.apache.lucene.search.TopDocs;
import org.apache.lucene.store.Directory;

import org.apache.lucene.util.Version;
import org.apache.solr.store.hdfs.HdfsDirectory;

//import org.wltea.analyzer.lucene.IKAnalyzer;

/**
 * 
 * 将索引存储在Hadoop2.2的HDFS上
 * 
 * QQ技术交流群： 1号群： 324714439 如果满员了请加2号群 2号群： 206247899
 * 
 * 
 **/
public class MyIndex {
	public static IndexReader reader;
	public static Directory directory;
	public static void createFile() throws Exception {
		Configuration conf = new Configuration();
		FileSystem fs = FileSystem.get(conf);
		Path p = new Path("hdfs://localhost:9000/user/kexinxin/input/abc.txt");
		fs.createNewFile(p);
		// fs.create(p);
		fs.close();// 释放资源
		System.out.println("创建文件成功.....");

	}

	public static void main(String[] args) throws Exception {
		add();

		// createFile();
		// long a=System.currentTimeMillis();
		// add();
		// long b=System.currentTimeMillis();
		// System.out.println("耗时: "+(b-a)+"毫秒");

		// query("lucene");

		// query("l");

		query("全文");

		// delete("3");//删除指定ID的数据
	}

	/***
	 * 得到HDFS的writer
	 * 
	 **/
	public static IndexWriter getIndexWriter() throws Exception {

		// Analyzer analyzer=new IKAnalyzer(true);
		// Analyzer analyzer = new StandardAnalyzer(Version.LUCENE_CURRENT);

		Analyzer analyzer = new SmartChineseAnalyzer(Version.LUCENE_48);

		IndexWriterConfig config = new IndexWriterConfig(Version.LUCENE_48, analyzer);
		Configuration conf = new Configuration();

		conf.set("fs.defaultFS", "hdfs://localhost:9000/");
		// conf.set("mapreduce.framework.name", "yarn");
		// conf.set("yarn.resourcemanager.address", "192.168.46.32:8032");
		// Path p1 =new Path("hdfs://10.2.143.5:9090/root/myfile/my.txt");
		// Path path=new Path("hdfs://10.2.143.5:9090/root/myfile");
		Path path = new Path("hdfs://localhost:9000/user/kexinxin/input");
		// HdfsDirectory directory=new HdfsDirectory(path, conf);

		HdfsDirectory directory = new HdfsDirectory(path, conf);

		IndexWriter writer = new IndexWriter(directory, config);

		return writer;

	}

	public static void add() throws Exception {

		IndexWriter writer = getIndexWriter();

		for (int i = 0; i < 10; i++) {
			Document doc = new Document();
			doc.add(new StringField("id", i + "", Store.YES));
			doc.add(new TextField("name", "lucene是一款非常优秀的全文检索框架" + i, Store.YES));
			doc.add(new TextField("content", "今天发工资了吗" + i, Store.YES));
			writer.addDocument(doc);
			if (i % 1000 == 0) {
				writer.commit();
			}
		}
		writer.commit();
		System.out.println("索引3条数据添加成功!");
		writer.close();
		
		Configuration conf = new Configuration();
		conf.set("fs.defaultFS", "hdfs://localhost:9000/");
		Path path = new Path("hdfs://localhost:9000/user/kexinxin/input");
		directory = new HdfsDirectory(path, conf);
		reader = DirectoryReader.open(directory);
	}

	/***
	 * 添加索引
	 * 
	 **/
	public static void add(Document d) throws Exception {

		IndexWriter writer = getIndexWriter();
		writer.addDocument(d);
		writer.forceMerge(1);
		writer.commit();
		System.out.println("索引10000条数据添加成功!");
		writer.close();
	}

	/**
	 * 根据指定ID 删除HDFS上的一些数据
	 * 
	 * 
	 **/
	public static void delete(String id) throws Exception {

		IndexWriter writer = getIndexWriter();
		writer.deleteDocuments(new Term("id", id));// 删除指定ID的数据
		writer.forceMerge(1);// 清除已经删除的索引空间
		writer.commit();// 提交变化

		System.out.println("id为" + id + "的数据已经删除成功.........");

	}

	public static void query(String queryTerm) throws Exception {
		if(reader==null){
			System.out.println("本次检索内容:  " + queryTerm);
			Configuration conf = new Configuration();
			conf.set("fs.defaultFS", "hdfs://localhost:9000/");
			Path path = new Path("hdfs://localhost:9000/user/kexinxin/input");
			directory = new HdfsDirectory(path, conf);
			reader = DirectoryReader.open(directory);
		}
		long a = System.currentTimeMillis();
		System.out.println("总数据量: " + reader.numDocs());
		IndexSearcher searcher = new IndexSearcher(reader);
		Analyzer analyzer = new SmartChineseAnalyzer(Version.LUCENE_48);
		QueryParser parse = new QueryParser(Version.LUCENE_48, "name", analyzer);

		Query query = parse.parse(queryTerm);

		TopDocs docs = searcher.search(query, 100);

		System.out.println("本次命中结果:   " + docs.totalHits + "  条");
		for (ScoreDoc sc : docs.scoreDocs) {
			System.out.println("评分:  " + sc.score + "  id : " + searcher.doc(sc.doc).get("id") + "  name:   "
					+ searcher.doc(sc.doc).get("name") + "   字段内容: " + searcher.doc(sc.doc).get("content"));

		}
		long b = System.currentTimeMillis();
		System.out.println("耗时:" + (b - a) + " 毫秒");
		System.out.println("检索完毕...............");
	}
}