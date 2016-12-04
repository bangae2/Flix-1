package com.example.dao;

import com.example.entity.VideosEntity;

import java.util.List;

/**
 * Created by bangae1 on 2016-07-06.
 */
public interface VideosDao {
    public List<VideosEntity> findPaging(int s, int e);
    public VideosEntity findOne(int id);
    public List<VideosEntity> findSearch(String text);
    public List<VideosEntity> findGenre(String genre);
    public List<VideosEntity> findAllByVideoKindSeq(int video_kind_seq);
    public void save(VideosEntity videosEntity);
    public void delete(int video_seq);
}
