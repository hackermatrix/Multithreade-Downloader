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
	private double task_offset;
	private int file_id;
	private double end_range;
	private File total_file;
	private ReentrantLock lock;  
	private FileOutputStream fout;
	private byte[] buff = new byte[1000];
	
	
	
	DownloadThread(int file_id,double task_offset,double end_range,File total,ReentrantLock lock,FileOutputStream fout){
		this.file_id = file_id;
		this.task_offset =task_offset;
		this.end_range = end_range;
		this.total_file = total;
		this.lock = lock;
		this.fout = fout;
	}
	
	public void run() {
		try {
			HttpURLConnection huc = (HttpURLConnection)new URL(total_file.getFile_url()).openConnection();
			InputStream data = huc.getInputStream();
			int bytesREAD;
			while((bytesREAD=data.read(buff))!=-1) {
				fout.write(buff);
				
			}
			
			
			
		}
		catch(Exception e){System.out.println(e);}
		
	}

}    