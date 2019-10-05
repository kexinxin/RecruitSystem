package com.kexinxin.util.crawler;

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

public class CrawlerLuceneIndexService {
	private static IndexWriter writer=null;
	
	public static void add() throws Exception {
		IndexWriter writer = getIndexWriter();
		for (int i = 6; i < 10000; i++) {
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
		writer.close();
	}
	
	
	public static void add(String question,String type) throws Exception {
		IndexWriter writer = getIndexWriter();
		Document doc = new Document();
		doc.add(new TextField("question",question, Store.YES));
		doc.add(new TextField("type",type, Store.YES));
		writer.addDocument(doc);
		//writer.commit();
		//writer.close();
	}
	
	
	/***
	 * 得到HDFS的writer
	 * 
	 **/
	public static IndexWriter getIndexWriter() throws Exception {
			
			if(writer==null){
				Analyzer analyzer=new SmartChineseAnalyzer(Version.LUCENE_48);
				IndexWriterConfig config = new IndexWriterConfig(Version.LUCENE_48, analyzer);
				Configuration conf = new Configuration();
				conf.set("fs.defaultFS", "hdfs://localhost:9000/");
				Path path = new Path("hdfs://localhost:9000/user/kexinxin/input");
				HdfsDirectory directory = new HdfsDirectory(path, conf);
				writer = new IndexWriter(directory, config);
				
			}
			return writer;
	}
	
	public static void query(String queryTerm) throws Exception {
		System.out.println("本次检索内容:  " + queryTerm);
		Configuration conf = new Configuration();
		conf.set("fs.defaultFS", "hdfs://localhost:9000/");
		Path path = new Path("hdfs://localhost:9000/user/kexinxin/input");
		Directory directory = new HdfsDirectory(path, conf);
		IndexReader reader = DirectoryReader.open(directory);
		System.out.println("总数据量: " + reader.numDocs());
		long a = System.currentTimeMillis();
		IndexSearcher searcher = new IndexSearcher(reader);
		
		Analyzer analyzer=new SmartChineseAnalyzer(Version.LUCENE_48);
		QueryParser parse = new QueryParser(Version.LUCENE_48, "type", analyzer);
		Query query = parse.parse(queryTerm);
		TopDocs docs = searcher.search(query, 100);
		
		System.out.println("本次命中结果:   " + docs.totalHits + "  条");
		for (ScoreDoc sc : docs.scoreDocs) {
			System.out.println("评分:  " + sc.score + "  type : " + searcher.doc(sc.doc).get("type") + "  question:   "
					+ searcher.doc(sc.doc).get("question"));

		}
		long b = System.currentTimeMillis();
		System.out.println("第一次耗时:" + (b - a) + " 毫秒");

		reader.close();
		directory.close();

		System.out.println("检索完毕...............");
	}
	
	
	public static void main(String[] args) throws Exception{
		CrawlerLuceneIndexService service=new CrawlerLuceneIndexService();
		//service.add();
		service.query("编程");
	}
}
