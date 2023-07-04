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
	public static final int BUFFER_SIZE = 10000;
	private DownloadFile total_file = new DownloadFile();
	
	DownMgr( DownloadFile total_file){
		this.total_file = total_file;
	}
	
	
	public void taskDistrib(int thread_count ) throws FileNotFoundException, InterruptedException {

		int id = total_file.getFile_id();
		long offset ;
		long end_range;
		long task_size = total_file.getFile_size();
		long  chunk_size = (task_size/thread_count);
		DownloadThread[] threads = new DownloadThread[thread_count];
		
		
		//System.out.print(chunk_size);
		
		
		for (int i=0;i<thread_count;i++) {
//			offset = (i==0)?i*chunk_size:(i*chunk_size)+1;
			offset = i*chunk_size;
			end_range = (i==thread_count-1)?task_size:offset+chunk_size-1;
			
			DownloadThread thread =new DownloadThread(id,offset,end_range,total_file,i,chunk_size);
			thread.start();
			
			threads[i]=thread;
		}
		

		for (DownloadThread thread : threads) {
		    try {
		        thread.join();
		    } catch (InterruptedException e) {

		    }
		}
		System.out.println("ALL DONE....!!!");
	
	}
	
	public void combineAndClean(DownloadFile total_file, int thread_count) throws IOException {
		System.out.println("Cleaner Started.....");
		
		FileOutputStream fout = new FileOutputStream(total_file.getFile_desti()+total_file.getFile_name());
		byte[] buffer = new byte[BUFFER_SIZE];
		for(int i=0;i<thread_count;i++) {
			InputStream part = new FileInputStream(total_file.getFile_desti()+"."+total_file.getFile_name()+"."+i+".tmp");
			File current_file = new File(total_file.getFile_desti()+"."+total_file.getFile_name()+"."+i+".tmp");
			int bytesREAD;
			while((bytesREAD=part.read(buffer))!=-1) {
				fout.write(buffer,0,bytesREAD);
			}
			current_file.delete();
		}
		fout.close();
	}
	
	
	public static void main(String[] args) throws IOException, InterruptedException {
		Scanner sc = new Scanner(System.in);
		
		System.out.println("ENTER THE DOWNLOAD URL: ");
		String url = sc.next();
		
		System.out.println("ENTER THE DOWNLOAD FILENAME: ");
		String file_name = sc.next();
		
		System.out.println("ENTER THE DOWNLOAD FILE DESTINATION: ");
		String destination = sc.next();
		
		System.out.println("ENTER THE AMOUNT OF THREADS: ");
		int thread_count = sc.nextInt();
		//public static Queue<KeyMappings<Integer,String>> All_tasks = new LinkedList<>();
		DownloadFile total_file = new DownloadFile(1,url,file_name,destination);
		
		
		
		
		DownMgr mgr = new DownMgr(total_file);
		System.out.println("TOTAL TASK SIZE: "+total_file.getFile_size());
		mgr.taskDistrib(thread_count);
		mgr.combineAndClean(total_file, thread_count);
		
		
		
		
		
	}
	
	
}
