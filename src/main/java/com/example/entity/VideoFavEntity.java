package com.example.entity;

import javax.persistence.*;

/**
 * Created by bangae11 on 2016-06-20.
 */
@Entity
@Table(name = "video_fav", schema = "dev")
public class VideoFavEntity {
    private Integer video_seq;
    private String id;
    private VideosEntity videosEntity;

    @Id
    @Column(name = "video_seq")
    public Integer getVideo_seq() {
        return video_seq;
    }

    public void setVideo_seq(Integer video_seq) {
        this.video_seq = video_seq;
    }
    
    @Basic
    @Column(name = "id")
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "video_seq", referencedColumnName = "video_seq", updatable = false, insertable = false)
    public VideosEntity getVideosEntity() {
        return videosEntity;
    }

    public void setVideosEntity(VideosEntity videosEntity) {
        this.videosEntity = videosEntity;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        VideoFavEntity that = (VideoFavEntity) o;

        if (video_seq != null ? !video_seq.equals(that.video_seq) : that.video_seq != null) return false;
        if (id != null ? !id.equals(that.id) : that.id != null) return false;

        return true;
    }



    @Override
    public String toString() {
        return "VideoFavouritesEntity{" +
                ", video_seq=" + video_seq +
                ", id='" + id + '\'' +
                '}';
    }
}
