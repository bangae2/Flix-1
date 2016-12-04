package com.example.service;

import com.example.config.HibernateProxyTypeAdapter;
import com.example.dao.VideosDao;
import com.example.dao.VideosKindDao;
import com.example.entity.VideosEntity;
import com.example.entity.VideosKindEntity;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.List;

/**
 * Created by bangae1 on 2016-07-06.
 */
@Service
@PropertySource(value = "config.properties")
public class VideosServiceImpl implements VideosService {
    @Autowired
    private VideosDao videosDao;

    @Autowired
    private VideosKindDao videosKindDao;

    @Autowired
    private Environment env;

    @Override
    @Transactional(readOnly = true)
    public List<VideosEntity> findPaging(int page) {
        int view = 20;
        int start = 0;
        if(page == 1) {
            start = page;
        } else {
            start = ((page -1) * view) + 1;
        }
        int end = page * view;
        return videosDao.findPaging(start, end);
    }

    @Override
    @Transactional(readOnly = true, propagation = Propagation.REQUIRED)
    public VideosEntity findOne(int id) {
        return this.videosDao.findOne(id);
    }

    @Override
    public List<VideosEntity> findSearch(String text) {
        return this.videosDao.findSearch(text);
    }

    @Override
    public List<VideosEntity> findGenre(String genre) {
        return this.videosDao.findGenre(genre);
    }

    @Override
    public List<VideosEntity> findAllByVideoKindSeq(int video_kind_seq) {
        return this.videosDao.findAllByVideoKindSeq(video_kind_seq);
    }

    @Override
    public void save(VideosEntity videosEntity) {
        this.videosDao.save(videosEntity);
    }

    @Override
    public String movieUP(VideosEntity videosEntity, MultipartFile multipartFile) {
        String video_real_path = env.getProperty("video.real.path");
        String file_name = multipartFile.getOriginalFilename();
        videosEntity.setFile_name(file_name);
//        /attach/BreakingBad1/
        VideosKindEntity videosKindEntity = videosKindDao.findOne(videosEntity.getVideo_kind_seq());
        String cover_path = videosKindEntity.getCover_path();
        cover_path = cover_path.substring(0, cover_path.lastIndexOf("/"));
        cover_path = cover_path.substring(0, cover_path.lastIndexOf("/")+1);
        videosEntity.setFile_path(cover_path);
        videosEntity.setThumbnail(file_name.substring(0, file_name.lastIndexOf(".")+1)+"jpg");
        System.out.println(videosEntity.toString());

        this.videosDao.save(videosEntity);
        String distPath = video_real_path.substring(0, video_real_path.length()-1) + videosEntity.getFile_path() + videosEntity.getFile_name();
        System.out.println(distPath);
        try {
            multipartFile.transferTo(new File(distPath));
        } catch (IOException e) {
            e.printStackTrace();
        }

        GsonBuilder gsonBuilder = new GsonBuilder();
        Gson gson = gsonBuilder.registerTypeAdapterFactory(HibernateProxyTypeAdapter.FACTORY).create();
        return gson.toJson(videosEntity);
    }

    @Override
    public void movieDel(int video_seq) {
        VideosEntity videosEntity = this.videosDao.findOne(video_seq);
        String real_path = env.getProperty("video.real.path");
        String path = real_path.substring(0, real_path.length() -1)+videosEntity.getFile_path() + videosEntity.getFile_name();
        File file = new File(path);
        try {
            file.delete();
            this.videosDao.delete(video_seq);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
