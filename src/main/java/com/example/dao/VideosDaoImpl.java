package com.example.dao;

import com.example.entity.VideosEntity;
import com.example.repository.VideosRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by bangae1 on 2016-07-06.
 */
@Repository
public class VideosDaoImpl implements VideosDao {
    @Autowired
    private VideosRepository videosRepository;

    @Override
    public List<VideosEntity> findPaging(int s, int e) {
        return videosRepository.findAll(title_sort());
    }

    @Override
    public VideosEntity findOne(int id) {
//        return videosRepository.findOne(id);
        return videosRepository.findOne(id);
    }

    @Override
    public List<VideosEntity> findSearch(String text) {
        return this.videosRepository.findSearch(text);
    }

    @Override
    public List<VideosEntity> findGenre(String genre) {
        return this.videosRepository.findGenre(genre);
    }

    @Override
    public List<VideosEntity> findAllByVideoKindSeq(int video_kind_seq) {
        return this.videosRepository.findAllByVideoKindSeq(video_kind_seq);
    }

    @Override
    public void save(VideosEntity videosEntity) {
        this.videosRepository.save(videosEntity);
    }

    @Override
    public void delete(int video_seq) {
        this.videosRepository.delete(video_seq);
    }

    public Sort title_sort() {
        return new Sort(Sort.Direction.ASC, "title3");
    }
}
