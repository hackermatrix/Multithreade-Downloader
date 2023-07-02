package com.mdm;
import java.net.*;
import java.io.*;

/*
 * Create a download manager to coordinate and manage multiple download tasks. 
 * This manager class can maintain a queue of download tasks and distribute them among the available download threads.
 * It should handle the start, pause, resume, and cancel operations for each download.
 */
public class DownMgr {
	//THIS is a queue
	String url = "https://cwh-full-next-space.fra1.digitaloceanspaces.com/downloads/videos/python-tutorial-easy-for-beginners/Python_ChapterWise_Notes.rar";
	String file_name = "DEMO_file";
	String destination = "/home/popeye/testing-download";
	File total_file = new File(1,url,file_name,destination);
	
	
	public void setFileInfo() throws IOException {
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
	
	public void taskDistrib() {
		int id = total_file.getFile_id();
		double offset = 0;
		double task_size = total_file.getFile_size();
		
	DownloadThread Tinstatnce =new DownloadThread(id,offset,task_size,total_file);
	
	}
	
	
}
