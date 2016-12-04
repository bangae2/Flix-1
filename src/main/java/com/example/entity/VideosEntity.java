package com.example.entity;

import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * Created by bangae11 on 2016-06-19.
 */
@Entity
@Table(schema = "dev", name = "videos")
public class VideosEntity {
    private int video_seq;
    private int video_kind_seq;
    private String title3;
    private String story;
    private String reg_date;
    private String file_name;
    private String file_path;
    private String thumbnail;
    private List<VideoLogEntity> videoLogEntities;

    public VideosEntity() {
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="video_seq")
    public int getVideo_seq() {
        return video_seq;
    }

    public void setVideo_seq(int video_seq) {
        this.video_seq = video_seq;
    }

    @Basic
    @Column(name="video_kind_seq")
    public int getVideo_kind_seq() {
        return video_kind_seq;
    }

    public void setVideo_kind_seq(int video_kind_seq) {
        this.video_kind_seq = video_kind_seq;
    }

    @Basic
    @Column(name = "title3")
    public String getTitle3() {
        return title3;
    }

    public void setTitle3(String title3) {
        this.title3 = title3;
    }

    @Basic
    @Column(name = "story", length = 6000)
    @Type(type="text")
    public String getStory() {
        return story;
    }

    public void setStory(String story) {
        this.story = story;
    }

    @Basic
    @Column(name = "file_name")
    public String getFile_name() {
        return file_name;
    }

    public void setFile_name(String file_name) {
        this.file_name = file_name;
    }

    @Basic
    @Column(name = "file_path")
    public String getFile_path() {
        return file_path;
    }

    public void setFile_path(String file_path) {
        this.file_path = file_path;
    }

    @Basic
    @Column(name = "thumbnail")
    public String getThumbnail() {
        return thumbnail;
    }

    public void setThumbnail(String thumbnail) {
        this.thumbnail = thumbnail;
    }

    @Basic
    @Column(name = "reg_date")
    public String getReg_date() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy.MM.dd");
        reg_date = sdf.format(new Date());
        return reg_date;
    }

    public void setReg_date(String reg_date) {
        this.reg_date = reg_date;
    }

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "videosEntity")
    public List<VideoLogEntity> getVideoLogEntities() {
        return videoLogEntities;
    }

    public void setVideoLogEntities(List<VideoLogEntity> videoLogEntities) {
        this.videoLogEntities = videoLogEntities;
    }

    @Override
    public String toString() {
        return "VideosEntity{" +
                "video_seq=" + video_seq +
                ", video_kind_seq=" + video_kind_seq +
                ", title3='" + title3 + '\'' +
                ", story='" + story + '\'' +
                ", reg_date='" + reg_date + '\'' +
                ", file_name='" + file_name + '\'' +
                ", file_path='" + file_path + '\'' +
                ", thumbnail='" + thumbnail + '\'' +
                '}';
    }
}
