package com.example.repository;

import com.example.entity.VideosEntity;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * Created by bangae1 on 2016-07-06.
 */
public interface VideosRepository extends JpaRepository<VideosEntity, Integer> {
    @Query(value = "select a.* from (select a.*, @ROWNUM  \\:= @ROWNUM  + 1 as rnum from videos a, (select @ROWNUM  \\:= 0) b) a where a.rnum >= :start and a.rnum <= :end order by title3", nativeQuery = true)
    public List<VideosEntity> findPaging(@Param("start")int start, @Param("end") int end);

    @Query(value = "select * from videos where title3 like CONCAT('%',:text,'%')", nativeQuery = true)
    public List<VideosEntity> findSearch(@Param("text")String text);

    @Query(value = "select a.* from videos a inner join video_kind b on a.video_kind_seq = b.video_kind_seq and b.genre like CONCAT('%',:genre,'%')", nativeQuery = true)
    public List<VideosEntity> findGenre(@Param("genre")String genre);

    @Query(value = "select a.* from videos a inner join video_kind b on a.video_kind_seq = b.video_kind_seq and a.video_kind_seq = :video_kind_seq order by title3 ", nativeQuery = true)
    public List<VideosEntity> findAllByVideoKindSeq(@Param("video_kind_seq")int video_kind_seq);
}
