package com.mdm;
// This class will be responsible for coordinating the download process and managing multiple download threads. 
//It will provide methods for starting, pausing, resuming, and canceling downloads.

import java.io.*;
import java.net.URL;
import java.net.HttpURLConnection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.locks.ReentrantLock;

public class DownloadThread extends Thread{
	private long task_offset;
	private int file_id;
	private long end_range;
	private File total_file;
	private ReentrantLock lock;  
	private FileOutputStream fout;
	private byte[] buff = new byte[4096];
	
	
	
	DownloadThread(int file_id,long task_offset,long end_range,File total,ReentrantLock lock,FileOutputStream fout){
		this.file_id = file_id;
		this.task_offset =task_offset;
		this.end_range = end_range;
		this.total_file = total;
		this.lock = lock;
		this.fout = fout;
	}
	
	public void run() {
		
		try {
			System.out.println(this.getName()+" STARTED.....");
			HttpURLConnection huc = (HttpURLConnection)new URL(total_file.getFile_url()).openConnection();
			huc.setRequestProperty("Range", "bytes="+task_offset+"-"+end_range);
			InputStream data = huc.getInputStream();

			int bytesREAD;
			lock.lock();

			while((bytesREAD=data.read(buff))!=-1) {
				System.out.println(this.getName()+" WRITING.....");
				

				fout.write(buff,0,bytesREAD);
				
			
			}
			lock.unlock();
			

			data.close();
			huc.disconnect();
			System.out.println(this.getName()+" DONE.....");
		}
		
		catch(Exception e){System.out.println(e);}
		
	}


	

}    