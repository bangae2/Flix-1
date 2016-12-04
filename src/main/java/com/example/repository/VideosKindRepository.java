package com.example.repository;

import com.example.entity.VideosKindEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * Created by bangae1 on 2016-07-16.
 */
public interface VideosKindRepository extends JpaRepository<VideosKindEntity, Integer> {

    @Query(value = "select * from video_kind where flag = true order by title1, title2", nativeQuery = true)
    public List<VideosKindEntity> findByFlag();

    @Query(value = "select * from video_kind where concat(title1, title2) like CONCAT('%',:text,'%') and flag = true order by title1, title2", nativeQuery = true)
    public List<VideosKindEntity> findSearch(@Param("text")String text);

    @Query(value = "select * from video_kind where genre like CONCAT('%',:genre,'%') and flag = true order by title1, title2", nativeQuery = true)
    public List<VideosKindEntity> findGenre(@Param("genre")String genre);
}
