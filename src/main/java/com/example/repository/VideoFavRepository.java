package com.example.repository;

import com.example.entity.VideoFavEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * Created by bangae1 on 2016-07-20.
 */
public interface VideoFavRepository extends JpaRepository<VideoFavEntity, Integer> {

    @Modifying
    @Query(value = "delete from video_fav where video_seq = :video_seq", nativeQuery = true)
    public void delete(@Param("video_seq")int video_seq);
}
