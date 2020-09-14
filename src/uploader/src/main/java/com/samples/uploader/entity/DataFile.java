package com.samples.uploader.entity;

import java.sql.Timestamp;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "datafiles")
public class DataFile {
    @Id
    private String blobName;
    private Long size;
    private Timestamp timeUploaded;

    public String getBlobName() {
        return blobName;
    }

    public void setBlobName(String blobName) {
        this.blobName = blobName;
    }

    public Long getSize() {
        return size;
    }

    public void setSize(Long size) {
        this.size = size;
    }

    public Timestamp getTimeUploaded() {
        return timeUploaded;
    }

    public void setTimeUploaded(Timestamp timeUploaded) {
        this.timeUploaded = timeUploaded;
    }

    @Override
    public String toString() {
        return "DataFile{" +
               "blobName='" + blobName + "'" +
               ", size=" + size +
               ", timeUploaded=" + timeUploaded.toString() +
               "}";
    }
}
