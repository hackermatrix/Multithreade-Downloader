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
	private File total_file = new File();
	
	DownMgr( File total_file){
		this.total_file = total_file;
	}
	
	
	public void taskDistrib(int thread_count ) throws FileNotFoundException, InterruptedException {

		int id = total_file.getFile_id();
		long offset ;
		long end_range;
		long task_size = total_file.getFile_size();
		
		long  chunk_size = task_size/thread_count;
		//System.out.print(chunk_size);
		ReentrantLock lck = new ReentrantLock();
		
		
		FileOutputStream fout = new FileOutputStream(total_file.getFile_desti()+total_file.getFile_name());
		
		for (int i=0;i<thread_count;i++) {
			offset = i*chunk_size;
			end_range = (i==thread_count-1)?task_size-1:offset+chunk_size;
			
			DownloadThread Tinstatnce =new DownloadThread(id,offset,end_range,total_file,lck,fout);
			Tinstatnce.start();
		}
	
	}
	
	
	
	public static void main(String[] args) throws IOException, InterruptedException {
		String url = "https://cwh-full-next-space.fra1.digitaloceanspaces.com/downloads/videos/python-tutorial-easy-for-beginners/Python_ChapterWise_Notes.rar";
		String file_name = "DEMO_file.rar";
		String destination = "/home/popeye/testing-download/";
		//public static Queue<KeyMappings<Integer,String>> All_tasks = new LinkedList<>();
		File total_file = new File(1,url,file_name,destination);
		int thread_count =4;
		
		
		
		DownMgr mgr = new DownMgr(total_file);
		mgr.taskDistrib(thread_count);
		
		
		
		
		
	}
	
	
}
