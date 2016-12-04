package com.example.dao;

import com.example.entity.VideoFavEntity;
import com.example.repository.VideoFavRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by bangae1 on 2016-07-20.
 */
@Repository
public class VideoFavDaoImpl implements VideoFavDao {
    @Autowired
    private VideoFavRepository videoFavRepository;

    @Override
    public List<VideoFavEntity> findAll() {
        return this.videoFavRepository.findAll();
    }

    @Override
    public VideoFavEntity findOne(int video_seq) {
        return this.videoFavRepository.findOne(video_seq);
    }

    @Override
    public void save(VideoFavEntity videoFavEntity) {
        this.videoFavRepository.save(videoFavEntity);
    }

    @Override
    public void delete(int video_seq) {
        this.videoFavRepository.delete(video_seq);
    }
}
