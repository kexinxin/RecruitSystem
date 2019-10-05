package com.kexinxin.util.hadoop;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;

public class CreateFileTest {
	 public static  void createFile()throws Exception{  
	        Configuration conf=new Configuration();  
	         FileSystem fs=FileSystem.get(conf);    
	         Path p =new Path("hdfs://localhost:9000/user/kexinxin/input/abc.txt");    
	         fs.createNewFile(p);    
	         //fs.create(p);    
	         fs.close();//释放资源    
	         System.out.println("创建文件成功.....");    
	          
	    }  
	
	public static void main(String[] args) throws Exception{
		createFile();
	}
}
