package com.rashmi.uploadfile.controller;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;



import com.rashmi.uploadfile.model.FileMetaDataModel;
import com.rashmi.uploadfile.repository.FileMetaDataRepository;

/**
 * @author rashmi
 * This is the Controller class implementing the File Upload REST API as a POST method.
 */
@Controller
@RestController
public class FileMetaDataController {
	
	private final Logger logger = LoggerFactory.getLogger(FileMetaDataController.class);
	
	private static String FOLDER = "/Users/rashmi/Desktop/UploadedFiles";
	
	@Autowired
	private FileMetaDataRepository fileMetaDataRepository;
	
	/**
	 * @param file
	 * @return
	 * @throws IOException
	 */
	@RequestMapping(value = "/api/upload", method = RequestMethod.POST, consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
	public @ResponseBody ResponseEntity<?> uploadFile(@RequestParam("file") MultipartFile file) throws IOException{
		
		logger.debug("File upload: Inside the POST method");
		logger.debug("fileName : " + file.getOriginalFilename());
		logger.debug("content: " + file.getContentType());
		
		if(file.isEmpty()) {
			return new ResponseEntity<String>("No file is selected, please select a file", HttpStatus.OK);
		}
		
		saveFile(file);
		
		return new ResponseEntity<String>("Successfully uploaded", HttpStatus.OK);
	}
	
	/**
	 * @param file
	 * @throws IOException
	 */
	private void saveFile(MultipartFile file) throws IOException {
		
			byte[] bytes = file.getBytes();
			Path path = Paths.get(FOLDER + file.getOriginalFilename());
			Files.write(path, bytes);
			saveDataToDB(file);
		
	}
	
	/**
	 * @param file
	 * @throws IOException
	 */
	private void saveDataToDB(MultipartFile file) throws IOException{
		
		FileMetaDataModel model = new FileMetaDataModel();
		model.setFileName(file.getOriginalFilename());
		model.setFileContent(file.getContentType());
		fileMetaDataRepository.save(model);
	}

}
