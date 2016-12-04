package com.example.controller;

import com.example.entity.VideosEntity;
import com.example.entity.VideosKindEntity;
import com.example.service.VideosKindService;
import com.example.service.VideosService;
import com.example.util.VideoRemoveUtil;
import com.google.common.io.ByteStreams;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.activation.MimetypesFileTypeMap;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.UUID;

/**
 * Created by bangae1 on 2016-07-16.
 */
@Controller
@RequestMapping("/viewer")
@PropertySource("classpath:config.properties")
public class ViewerController {
    @Autowired
    private Environment env;
    @Autowired
    private VideosService videosService;

    @Autowired
    private VideosKindService videosKindService;

    @RequestMapping(value = "/image/{video_seq}", method = RequestMethod.GET)
    @Transactional(readOnly = true)
    public HttpEntity<byte[]> imageView(@PathVariable("video_seq")Integer video_seq, HttpServletRequest req, HttpServletResponse res) {
        VideosEntity videosEntity = this.videosService.findOne(video_seq);
        String filePath = null;
        if(env.getProperty("debug_local").equals("Y")) {
            filePath = env.getProperty("video.real.path.debug")+videosEntity.getFile_path() + "thumbnail/" + videosEntity.getThumbnail();
        } else {
            filePath = env.getProperty("video.real.path")+videosEntity.getFile_path() + "thumbnail/" + videosEntity.getThumbnail();
        }

        File file = new File(filePath);

        byte[] imageBytes = null;
        String mimeType = "";
        FileInputStream fis = null;

        mimeType = new MimetypesFileTypeMap().getContentType(file);
        try {
            fis = new FileInputStream(file);
            imageBytes = IOUtils.toByteArray(fis);
        } catch (FileNotFoundException e) {
            VideosKindEntity videosKindEntity = videosKindService.findOne(videosEntity.getVideo_kind_seq());
            filePath = env.getProperty("video.real.path")+videosKindEntity.getCover_path()+ videosKindEntity.getCover_name();
            file = new File(filePath);
            System.out.println("cover Image load !!!");
            try {
                mimeType = new MimetypesFileTypeMap().getContentType(file);
                fis = new FileInputStream(file);
                imageBytes = IOUtils.toByteArray(fis);
            } catch (FileNotFoundException e1) {
                e1.printStackTrace();
            } catch (IOException e1) {
                e1.printStackTrace();
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.valueOf(mimeType));
        headers.setContentLength(file.length());

        return new HttpEntity<byte[]>(imageBytes, headers);
    }

    @RequestMapping(value = "/cover/{video_kind_seq}", method = RequestMethod.GET)
    @Transactional(readOnly = true)
    public HttpEntity<byte[]> coverView(@PathVariable("video_kind_seq")Integer video_kind_seq, HttpServletRequest req, HttpServletResponse res) {
        VideosKindEntity videosKindEntity = this.videosKindService.findOne(video_kind_seq);
        String filePath = null;
        if(env.getProperty("debug_local").equals("Y")) {
            filePath = env.getProperty("video.real.path.debug")+videosKindEntity.getCover_path() + videosKindEntity.getCover_name();
        } else {
            filePath = env.getProperty("video.real.path")+videosKindEntity.getCover_path() + videosKindEntity.getCover_name();
        }
        File file = new File(filePath);
        byte[] imageBytes = null;
        String mimeType = "";
        FileInputStream fis = null;

        mimeType = new MimetypesFileTypeMap().getContentType(file);
        try {
            fis = new FileInputStream(file);
            imageBytes = IOUtils.toByteArray(fis);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.valueOf(mimeType));
        headers.setContentLength(file.length());

        return new HttpEntity<byte[]>(imageBytes, headers);
    }

    @ResponseBody
    @RequestMapping(value = "/video/{video_seq}", method = RequestMethod.POST, produces = "plain/text;charset=utf-8")
    @Transactional
    public String video(@PathVariable("video_seq")Integer video_seq, HttpServletResponse res, HttpServletRequest req) {
        VideosEntity videosEntity = this.videosService.findOne(video_seq);
//        String temp = env.getProperty("video.temp.path");
//        String realPath = env.getProperty("video.real.path")+videosEntity.getFile_path()+videosEntity.getFile_name();
//        File file = new File(realPath);
//
//        String uuid = UUID.randomUUID().toString();
//        String fileName = file.getName();
//        fileName = fileName.substring(fileName.length() -4);
//        File eFile = new File(temp + uuid + fileName);
//        try {
//            FileUtils.copyFile(file, eFile);
//            VideoRemoveUtil util = new VideoRemoveUtil(temp + uuid + fileName);
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
        return videosEntity.getFile_path() + videosEntity.getFile_name();

    }

    public static void close(Closeable c) {
        if (c == null) return;
        try {
            c.close();
        } catch (IOException e) {
            //log the exception
        }
    }

}
