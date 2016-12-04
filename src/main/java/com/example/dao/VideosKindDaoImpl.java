package com.example.dao;

import com.example.entity.VideosKindEntity;
import com.example.repository.VideosKindRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Repository;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by bangae1 on 2016-07-16.
 */
@Repository
public class VideosKindDaoImpl implements VideosKindDao {
    @Autowired
    private VideosKindRepository videosKindRepository;

    public VideosKindEntity findOne(int id) {
        return this.videosKindRepository.findOne(id);
    }

    public List<VideosKindEntity> findAll() {
        return this.videosKindRepository.findAll(sort("title1", "title2"));
    }

    @Override
    public List<VideosKindEntity> findSearch(String text) {
        return this.videosKindRepository.findSearch(text);
    }

    @Override
    public List<VideosKindEntity> findGenre(String genre) {
        return this.videosKindRepository.findGenre(genre);
    }

    @Override
    public List<VideosKindEntity> findByFlag() {
        return this.videosKindRepository.findByFlag();
    }

    @Override
    public void save(VideosKindEntity videosKindEntity) {
        this.videosKindRepository.save(videosKindEntity);
    }

    @Override
    public void delete(int video_kind_seq) {
        this.videosKindRepository.delete(video_kind_seq);
    }

    public Sort sort(String column) {
        return new Sort(Sort.Direction.ASC, new String[]{column});
    }
    public Sort sort(String column, String column2) {
        return new Sort(Sort.Direction.ASC, new String[]{column, column2});
    }
}
