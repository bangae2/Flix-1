package com.example.service;

import com.example.config.HibernateProxyTypeAdapter;
import com.example.dao.VideosKindDao;
import com.example.entity.VideosKindEntity;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.List;

/**
 * Created by bangae1 on 2016-07-16.
 */
@Service
@PropertySource(value = "classpath:config.properties")
public class VideosKindServiceImpl implements VideosKindService {
    @Autowired
    private Environment env;

    @Autowired
    private VideosKindDao videosKindDao;

    @Override
    @Transactional(readOnly = true)
    public VideosKindEntity findOne(int id) {
        return this.videosKindDao.findOne(id);
    }

    @Override
    @Transactional(readOnly = true)
    public List<VideosKindEntity> findAll() {
        return this.videosKindDao.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public List<VideosKindEntity> findSearch(String text) {
        return this.videosKindDao.findSearch(text);
    }

    @Override
    @Transactional(readOnly = true)
    public List<VideosKindEntity> findGenre(String genre) {
        return this.videosKindDao.findGenre(genre);
    }

    @Override
    @Transactional(readOnly = true)
    public List<VideosKindEntity> findByFlag() {
        return this.videosKindDao.findByFlag();
    }

    @Override
    @Transactional
    public void save(VideosKindEntity videosKindEntity) {
        this.videosKindDao.save(videosKindEntity);
    }

    @Override
    @Transactional
    public String kindUp(VideosKindEntity videosKindEntity, MultipartFile multipartFile) {

        String cover_name = multipartFile.getOriginalFilename();
        String cover_path = cover_name.substring(0, cover_name.lastIndexOf("."));
        String realPath = env.getProperty("video.real.path") + env.getProperty("video.real.resource.path") + cover_path + "/cover/";
        File cover = new File(realPath);
        if(!cover.exists()) {
            cover.mkdirs();
        }
        videosKindEntity.setCover_path("/"+env.getProperty("video.real.resource.path") + cover_path + "/cover/");
        videosKindEntity.setCover_name(cover_name);
        GsonBuilder gsonBuilder = new GsonBuilder().registerTypeAdapterFactory(HibernateProxyTypeAdapter.FACTORY);
        Gson gson = gsonBuilder.create();
        VideosKindEntity returnVideoKind = null;

        try {
            this.videosKindDao.save(videosKindEntity);
            multipartFile.transferTo(new File(realPath+cover_name));
            returnVideoKind = this.videosKindDao.findOne(videosKindEntity.getVideo_kind_seq());

        } catch(Exception e) {
            System.out.println(e.toString());
        }
        return new Gson().toJson(returnVideoKind);
    }

    @Override
    @Transactional
    public void delete(int video_kind_seq) {
        VideosKindEntity videosKindEntity = this.videosKindDao.findOne(video_kind_seq);
        String path = env.getProperty("video.real.path") + videosKindEntity.getCover_path() + videosKindEntity.getCover_name();
        System.out.println(env.getProperty("video.real.path") + videosKindEntity.getCover_path() + videosKindEntity.getCover_name());

        File delDir = new File(path);
        try {
            this.videosKindDao.delete(video_kind_seq);
            delDir.delete();
        } catch(Exception  e) {

        }
    }
}
