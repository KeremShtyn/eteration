package com.example.eteration.util.base;

import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

@Data
public class BaseDomain implements Serializable {

    private String id;

    private LocalDateTime createdDate;

}
