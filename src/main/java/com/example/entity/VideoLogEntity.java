package com.example.entity;

import javax.persistence.*;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by bangae1 on 2016-07-19.
 */
@Entity
@Table(name = "video_log", schema = "dev")
public class VideoLogEntity {
    private String id;
    private int video_seq;
    private String insert_date;
    private int video_kind_seq;
    private VideosEntity videosEntity;

    public VideoLogEntity() {
    }

    @Basic
    @Column(name = "id", length = 20)
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Id
    @Column(name = "video_seq")
    public int getVideo_seq() {
        return video_seq;
    }

    public void setVideo_seq(int video_seq) {
        this.video_seq = video_seq;
    }

    @Basic
    @Column(name = "insert_date")
    public String getInsert_date() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy.MM.dd HH:mm:SS");
        insert_date = sdf.format(new Date());
        return insert_date;
    }

    public void setInsert_date(String insert_date) {
        this.insert_date = insert_date;
    }


    @Basic
    @Column(name = "video_kind_seq", nullable = false)
    public int getVideo_kind_seq() {
        return video_kind_seq;
    }

    public void setVideo_kind_seq(int video_kind_seq) {
        this.video_kind_seq = video_kind_seq;
    }

    @ManyToOne(cascade =  CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "video_seq", referencedColumnName = "video_seq", updatable = false, insertable = false)
    public VideosEntity getVideosEntity() {
        return videosEntity;
    }

    public void setVideosEntity(VideosEntity videosEntity) {
        this.videosEntity = videosEntity;
    }

    @Override
    public String toString() {
        return "VideoLogEntity{" +
                "id='" + id + '\'' +
                ", video_seq=" + video_seq +
                ", insert_date='" + insert_date + '\'' +
                ", video_kind_seq=" + video_kind_seq +
                ", videosEntity=" + videosEntity +
                '}';
    }
}
