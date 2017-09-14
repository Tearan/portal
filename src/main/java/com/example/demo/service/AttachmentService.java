package com.example.demo.service;

import groovy.util.logging.Log4j;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Created by marta on 12.08.17.
 */
@Component
@Log4j
public class AttachmentService {

    private static Logger LOG = Logger.getLogger(AttachmentService.class);

    @Value("${attachments.path.base}")
    private String uploadAttachmentPath;



    public void saveFileInDirectory(List<MultipartFile> file, String advId) {
        file.stream().forEach(f->{

            Path newDirectoryPath = Paths.get(uploadAttachmentPath +advId);
            InputStream is = null;
            OutputStream os = null;
            try{
                is = f.getInputStream();
                Files.createDirectories(newDirectoryPath);
                Path newFilePath = Paths.get(newDirectoryPath+"/"+f.getOriginalFilename().replaceAll("[ _]", "-"));
                Files.createFile(newFilePath);
                os = new FileOutputStream(newFilePath.toString());
                int read = 0;
                byte[] bytes = new byte[1024];
                while ((read = is.read(bytes)) != -1) {
                    os.write(bytes, 0, read);
                }
            }catch (IOException e){
                LOG.error("Cannot save file:"+f.getOriginalFilename()+", to advertisement:"+advId);
                LOG.error(e.getMessage());
                e.printStackTrace();
            }
        });
    }

    public List<File> getFiles(List<String> files, String advId) {
        List<File> listFiles = new ArrayList<>();

        try(Stream<Path> stream = Files.list(Paths.get(uploadAttachmentPath +advId))){
            listFiles = stream
                    .map(f-> new File(f.toString()))
                    .collect(Collectors.toList());

        } catch (IOException e) {
            e.printStackTrace();
        }

        return listFiles;
    }

}
