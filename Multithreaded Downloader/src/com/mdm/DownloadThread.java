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
	private DownloadFile total_file; 
	private FileOutputStream fout;
	private byte[] buff = new byte[DownMgr.BUFFER_SIZE];
	private int seq_num;
	private long Downloaded=0;
	private long chunk_size;

	
	
	
	DownloadThread(int file_id,long task_offset,long end_range,DownloadFile total,int seq_num,long chunk_size) throws FileNotFoundException{
		this.file_id = file_id;
		this.task_offset =task_offset;
		this.end_range = end_range;
		this.total_file = total;
		this.seq_num = seq_num;
		this.chunk_size = chunk_size;
		
		// Writing Each task portion to the file system
		this.fout = new FileOutputStream(total_file.getFile_desti()+"."+total_file.getFile_name()+"."+seq_num+".tmp");
	}
	
	public DownloadThread() {
		// TODO Auto-generated constructor stub
	}

	public void run() {
		
		try {
			System.out.println(this.getName()+" STARTED....."+" offset : "+ task_offset+" endrange : "+end_range);

			HttpURLConnection huc = (HttpURLConnection)new URL(total_file.getFile_url()).openConnection();
			huc.setRequestProperty("Range", "bytes="+task_offset+"-"+end_range);
			InputStream data = huc.getInputStream();

			int bytesREAD;

			while((bytesREAD=data.read(buff))!=-1) {				
				fout.write(buff,0,bytesREAD);
				Downloaded+=bytesREAD;
			
			}		
//			if(Downloaded == chunk_size) {
//				System.out.println(this.getName()+" DONE.....");
//			}
//			else {
//				System.out.println(this.getName()+" INCOMPLETE.....!!!> ["+Downloaded+"]/["+(end_range-task_offset)+"]");
//			}
			System.out.println(this.getName()+" DONE.....");
			data.close();
			huc.disconnect();
			
		}
		
		catch(Exception e){System.out.println(e);}
		
	}


	

}    