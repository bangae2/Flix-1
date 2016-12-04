package com.example.service;

import com.example.dao.VideoFavDao;
import com.example.entity.VideoFavEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by bangae1 on 2016-07-20.
 */
@Service
public class VideoFavServiceImpl implements VideoFavService {
    @Autowired
    private VideoFavDao videoFavDao;

    @Override
    public List<VideoFavEntity> findAll() {
        return this.videoFavDao.findAll();
    }

    @Override
    public VideoFavEntity findOne(int video_seq) {
        return this.videoFavDao.findOne(video_seq);
    }

    @Override
    @Transactional
    public void save(VideoFavEntity videoFavEntity) {
        this.videoFavDao.save(videoFavEntity);
    }


    @Override
    @Transactional
    public void delete(int video_seq) {
        this.videoFavDao.delete(video_seq);
    }
}
