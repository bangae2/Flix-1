package com.example.service;

import com.example.entity.VideosKindEntity;
import org.springframework.data.repository.query.Param;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * Created by bangae1 on 2016-07-16.
 */
public interface VideosKindService {
    public VideosKindEntity findOne(int id);
    public List<VideosKindEntity> findAll();
    public List<VideosKindEntity> findSearch(String text);
    public List<VideosKindEntity> findGenre(String genre);
    public List<VideosKindEntity> findByFlag();
    public void save(VideosKindEntity videosKindEntity);
    public String kindUp(VideosKindEntity videosKindEntity, MultipartFile multipartFile);
    public void delete(int video_kind_seq);
}
