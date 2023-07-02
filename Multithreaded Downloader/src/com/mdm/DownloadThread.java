package com.mdm;
// This class will be responsible for coordinating the download process and managing multiple download threads. 
//It will provide methods for starting, pausing, resuming, and canceling downloads.

import java.io.*;
import java.net.URL;
import java.net.URLConnection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DownloadThread {
	private double task_offset;
	private int file_id;
	private double task_size;
	private File total_file;
	
	
	
	DownloadThread(int file_id,double task_offset,double task_size,File total){
		this.file_id = file_id;
		this.task_offset =task_offset;
		this.task_size = task_size;
		this.total_file = total;
	}
	
	void initiateDownload() {
		
	}
	void reportStatus() {
		
	}
}    