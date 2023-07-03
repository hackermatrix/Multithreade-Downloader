package com.mdm;
import java.net.*;
import java.util.*;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.io.*;

/*
 * Create a download manager to coordinate and manage multiple download tasks. 
 * This manager class can maintain a queue of download tasks and distribute them among the available download threads.
 * It should handle the start, pause, resume, and cancel operations for each download.
 */
public class DownMgr {
	
	public void setFileInfo(String url, File total_file) throws IOException {
		//Creating URL representation and URL connection
		try {
		
		URL url_rep = new URL(url);
		HttpURLConnection uc = (HttpURLConnection)url_rep.openConnection(); // establishing connection
		//
		total_file.setFile_size(uc.getContentLength());
		total_file.setFile_type(uc.getContentType());
		
		}
		
		catch(Exception e) {System.out.print(e);}
	}
	
	
	
	
	public void taskDistrib(File total_file,int thread_count ) {
		int id = total_file.getFile_id();
		double offset ;
		double end_range;
		double task_size = total_file.getFile_size();
		double chunk_size = task_size/thread_count;
		ReentrantLock lock;  
		FileOutputStream fout = new FileOutputStream(total_file.getFile_desti());
		
		for (int i=0;i<thread_count;i++) {
			offset = i*chunk_size;
			end_range = (i==thread_count-1)?task_size-1:offset+chunk_size;
			
			DownloadThread Tinstatnce =new DownloadThread(id,offset,end_range,total_file,lock,fout);
			Tinstance.start();
		}
	
	}
	
	
	
	public static void main(String[] args) {
		String url = "https://cwh-full-next-space.fra1.digitaloceanspaces.com/downloads/videos/python-tutorial-easy-for-beginners/Python_ChapterWise_Notes.rar";
		String file_name = "DEMO_file";
		String destination = "/home/popeye/testing-download/";
		//public static Queue<KeyMappings<Integer,String>> All_tasks = new LinkedList<>();
		File total_file = new File(1,url,file_name,destination);
		int thread_count =10;
		
		
		
		
		
	}
	
	
}
