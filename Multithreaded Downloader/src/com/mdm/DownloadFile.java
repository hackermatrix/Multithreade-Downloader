package com.mdm;
/*
Hold information about the file to be downloaded, such as URL, file name, file size, etc.
Divide the file into smaller portions and assign each portion to a DownloadThread.
Provide methods to retrieve information about the file and its assigned portion.
 */

import java.net.HttpURLConnection;
import java.net.URL;

public class DownloadFile{
	private int file_id;
	private long file_size;
	private String file_type;
	private String file_name;
	private String file_url;
	private String status;
	private String file_desti;
	
	
	
	DownloadFile(int id,String url,String file_name,String file_desti){
		this.file_id = id;
		this.file_url = url;
		this.file_name = file_name;
		this.file_desti = file_desti;	
		
		try {
			
		URL url_rep = new URL(url);
		HttpURLConnection uc = (HttpURLConnection)url_rep.openConnection(); // establishing connection

		this.file_size = uc.getContentLengthLong();
		this.file_type=uc.getContentType();
		
		}
		
		catch(Exception e) {System.out.print(e);}
	}
	



	public DownloadFile() {
		// TODO Auto-generated constructor stub
	}




	public long getFile_size() {
		return file_size;
	}
	public void setFile_size(long file_size) {
		this.file_size = file_size;
	}
	public String getFile_type() {
		return file_type;
	}
	public void setFile_type(String file_type) {
		this.file_type = file_type;
	}
	public String getFile_name() {
		return file_name;
	}
	public void setFile_name(String file_name) {
		this.file_name = file_name;
	}
	public int getFile_id() {
		return file_id;
	}
	public void setFile_id(int file_id) {
		this.file_id = file_id;
	}
	public String getFile_url() {
		return file_url;
	}
	public void setFile_url(String file_url) {
		this.file_url = file_url;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getFile_desti() {
		return file_desti;
	}
	public void setFile_desti(String file_desti) {
		this.file_desti = file_desti;
	}
	
}
