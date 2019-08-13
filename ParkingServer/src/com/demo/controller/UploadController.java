package com.demo.controller;

import com.jfinal.core.Controller;
import com.jfinal.upload.UploadFile;

public class UploadController extends Controller{

	public void uploadFileMobile(){
		UploadFile f = getFile();
		renderJson("upload/"+f.getFileName());
	}
}
