package com.rashmi.uploadfile.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.rashmi.uploadfile.model.FileMetaDataModel;

/**
 * @author rashmi
 * This is the Repository interface to implement the CRUD operations on a Database.
 */
public interface FileMetaDataRepository extends JpaRepository<FileMetaDataModel, Long> {

}
