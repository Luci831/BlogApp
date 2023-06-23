package com.krishna.blog.services.impl;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.UUID;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.krishna.blog.services.FileService;

@Service
public class FileServiceImpl implements FileService{

	
	@Override
	public String uploadImage(String path, MultipartFile file) throws IOException {

		//file name =abs.png
		//path value in app.prop as /image
		String name= file.getOriginalFilename();
		
		String randomID=UUID.randomUUID().toString();
		
		String fileName1=randomID.concat(name.substring(name.lastIndexOf(".")));
		
		//fullpath
		//    /image/fileName1
		
		String filePath=path+ File.separator+fileName1;
		
		//create folder if not created
		File f=new File(path);
		if(!f.exists()) {
			f.mkdir();
		}
		
		//file copy
		Files.copy(file.getInputStream(), Paths.get(filePath));
		
		return fileName1;
	}

	@Override
	public InputStream getResource(String path, String fileName) throws FileNotFoundException {
		
		String filePath=path+ File.separator+fileName;
		
		InputStream i=new FileInputStream(filePath);
		
		return i;
	}

	
}
