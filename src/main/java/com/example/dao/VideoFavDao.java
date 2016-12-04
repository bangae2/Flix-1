package com.example.dao;

import com.example.entity.VideoFavEntity;

import java.util.List;

/**
 * Created by bangae1 on 2016-07-20.
 */
public interface VideoFavDao {
    public List<VideoFavEntity> findAll();
    public VideoFavEntity findOne(int video_seq);
    public void save(VideoFavEntity videoFavEntity);
    public void delete(int video_seq);
}
