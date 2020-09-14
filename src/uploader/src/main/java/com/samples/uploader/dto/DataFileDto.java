package com.samples.uploader.dto;

import java.util.Date;

public class DataFileDto {
    private String blobName;
    private Long size;
    private Date timeUploaded;

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

    public Date getTimeUploaded() {
        return timeUploaded;
    }

    public void setTimeUploaded(Date timeUploaded) {
        this.timeUploaded = timeUploaded;
    }
}
