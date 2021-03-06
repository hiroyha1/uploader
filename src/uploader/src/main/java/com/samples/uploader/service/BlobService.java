package com.samples.uploader.service;

import java.io.InputStream;
import java.io.OutputStream;
import java.sql.Timestamp;
import java.time.Instant;
import java.util.Date;

import com.azure.storage.blob.BlobClient;
import com.azure.storage.blob.BlobContainerClient;
import com.azure.storage.blob.BlobServiceClient;
import com.azure.storage.blob.BlobServiceClientBuilder;
import com.samples.uploader.dto.DataFileDto;
import com.samples.uploader.entity.DataFile;
import com.samples.uploader.exception.AlreadyExistsException;
import com.samples.uploader.exception.ResourceNotFoundException;
import com.samples.uploader.repository.DataFileRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BlobService {
    private final BlobServiceClient serviceClient = new BlobServiceClientBuilder()
        .connectionString(System.getenv("STORAGE_CONNECTION_STRING"))
        .buildClient();
    @Autowired
    private DataFileRepository dataFileRepository;

    public void createContainer(String container) {
        BlobContainerClient containerClient = serviceClient.getBlobContainerClient(container);
        if (containerClient.exists()) {
            throw new AlreadyExistsException(String.format("%s already exists.", container));
        }
        containerClient.create();
    }

    public DataFileDto upload(String container, String blobName, InputStream data, long size) {
        BlobContainerClient containerClient = serviceClient.getBlobContainerClient(container);
        if (!containerClient.exists()) {
            createContainer(container);
        }
        BlobClient blobClient = containerClient.getBlobClient(blobName);
        blobClient.upload(data, size);
        Date now = Date.from(Instant.now());
        Timestamp timeUploaded = new Timestamp(now.getTime());

        DataFile dataFile = new DataFile();
        dataFile.setBlobName(blobName);
        dataFile.setSize(size);
        dataFile.setTimeUploaded(timeUploaded);

        dataFileRepository.save(dataFile);

        DataFileDto dataFileDto = new DataFileDto();
        dataFileDto.setBlobName(blobName);
        dataFileDto.setSize(size);
        dataFileDto.setTimeUploaded(now);
        return dataFileDto;
    }

    public void download(String container, String blobName, OutputStream stream)
    {
        BlobContainerClient containerClient = serviceClient.getBlobContainerClient(container);
        BlobClient blobClient = containerClient.getBlobClient(blobName);
        if (!blobClient.exists()) {
            throw new ResourceNotFoundException(String.format("%s does not exist.", blobName));
        }
        blobClient.download(stream);
    }
}
