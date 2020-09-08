package com.samples.uploader.repository;

import com.samples.uploader.entity.DataFile;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DataFileRepository extends CrudRepository<DataFile, Long>{

}
