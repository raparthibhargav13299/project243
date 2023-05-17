package com.stackroute.graphqueryservice.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class Program {
    private String createdBy;
    private String programTitle;
    private String domainName;
    private int duration;
    private String description;

    private String imageName;
    private String imageType;
    private byte[] picByte;
}


