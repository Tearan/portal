package com.example.demo.rest;

import com.example.demo.bean.Advertisement;
import lombok.Getter;
import lombok.Setter;

import java.io.File;
import java.io.OutputStream;
import java.util.List;

/**
 * Created by marta on 15.08.17.
 */
@Getter
@Setter
public class AdvertisementResponse {
    private Advertisement advertisement;
    private List<String> files;
    private String Message;
 }
