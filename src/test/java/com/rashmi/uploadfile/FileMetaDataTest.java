package com.rashmi.uploadfile;

import java.io.IOException;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.rashmi.uploadfile.controller.FileMetaDataController;

@RunWith(SpringJUnit4ClassRunner.class)
public class FileMetaDataTest {

	@Autowired
	FileMetaDataController fileMetaDataController;
	
	@Test
	@Transactional
	public void uploadFileTest() throws IOException {
		
		MultipartFile file = new MockMultipartFile("test.txt", "test".getBytes());
		ResponseEntity<?> resp = fileMetaDataController.uploadFile(file);
		Assert.assertNotNull(resp);
		Assert.assertEquals(HttpStatus.OK, resp.getStatusCode());
	}
}
