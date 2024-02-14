package com.example.service;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class UploadImageService {
    
    public String getUploadImageName(MultipartFile multipartFile){
      String fileName = multipartFile.getOriginalFilename();
	  // ファイルを保存する
	  try {
        // 保存先を定義
        String uploadPath = "src/main/resources/static/img/";
        byte[] bytes = multipartFile.getBytes();

        // 指定ファイルへ読み込みファイルを書き込み
        BufferedOutputStream stream = new BufferedOutputStream(
        new FileOutputStream(new File(uploadPath + fileName)));
        stream.write(bytes);
        stream.close();
      } catch (Exception e) {
        e.printStackTrace();
        fileName ="";
      }
      return fileName;       
    }
    
}
