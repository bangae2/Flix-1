package com.example.entity;

import javax.persistence.*;
import java.util.List;

/**
 * Created by bangae1 on 2016-06-27.
 */
@Entity
@Table(name = "video_kind", schema = "dev")
public class VideosKindEntity {
    private int video_kind_seq = 0;
    private String cover_name;
    private String cover_path;
    private String actor;
    private String country;
    private String genre;
    private String title1;
    private String title2;
    private double star;
    private String start_date;
    private boolean flag;
    private List<VideosEntity> videosEntities;

    @Id
    @Column(name = "video_kind_seq")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public int getVideo_kind_seq() {
        return video_kind_seq;
    }

    public void setVideo_kind_seq(int video_kind_seq) {
        this.video_kind_seq = video_kind_seq;
    }

    @Basic
    @Column(name = "cover_name")
    public String getCover_name() {
        return cover_name;
    }

    public void setCover_name(String cover_name) {
        this.cover_name = cover_name;
    }

    @Basic
    @Column(name = "cover_path")
    public String getCover_path() {
        return cover_path;
    }

    public void setCover_path(String cover_path) {
        this.cover_path = cover_path;
    }

    @Basic
    @Column(name = "actor")
    public String getActor() {
        return actor;
    }

    public void setActor(String actor) {
        this.actor = actor;
    }

    @Basic
    @Column(name = "country")
    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    @Basic
    @Column(name = "genre")
    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    @Basic
    @Column(name = "title1")
    public String getTitle1() {
        return title1;
    }

    public void setTitle1(String title1) {
        this.title1 = title1;
    }

    @Basic
    @Column(name = "title2")
    public String getTitle2() {
        return title2;
    }

    public void setTitle2(String title2) {
        this.title2 = title2;
    }

    @Basic
    @Column(name = "star")
    public double getStar() {
        return star;
    }

    public void setStar(double star) {
        this.star = star;
    }

    @Basic
    @Column(name ="start_date")
    public String getStart_date() {
        return start_date;
    }

    public void setStart_date(String start_date) {
        this.start_date = start_date;
    }

    @Basic
    @Column(name ="flag")
    public boolean isFlag() {
        return flag;
    }

    public void setFlag(boolean flag) {
        this.flag = flag;
    }

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "video_kind_seq")
    public List<VideosEntity> getVideosEntities() {
        return videosEntities;
    }

    public void setVideosEntities(List<VideosEntity> videosEntities) {
        this.videosEntities = videosEntities;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        VideosKindEntity that = (VideosKindEntity) o;

        if (video_kind_seq != that.video_kind_seq) return false;
        if (cover_name != null ? !cover_name.equals(that.cover_name) : that.cover_name != null) return false;
        if (cover_path != null ? !cover_path.equals(that.cover_path) : that.cover_path != null) return false;
        if (title1 != null ? !title1.equals(that.title1) : that.title1 != null) return false;
        if (title2 != null ? !title2.equals(that.title2) : that.title2 != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = video_kind_seq;
        result = 31 * result + (cover_name != null ? cover_name.hashCode() : 0);
        result = 31 * result + (cover_path != null ? cover_path.hashCode() : 0);
        result = 31 * result + (title1 != null ? title1.hashCode() : 0);
        result = 31 * result + (title2 != null ? title2.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "VideosKindEntity{" +
                "videoKindSeq=" + video_kind_seq +
                ", coverName='" + cover_name + '\'' +
                ", coverPath='" + cover_path + '\'' +
                ", actor='" + actor + '\'' +
                ", country='" + country + '\'' +
                ", genre='" + genre + '\'' +
                ", title1='" + title1 + '\'' +
                ", title2='" + title2 + '\'' +
                ", star=" + star +
                ", start_date='" + start_date + '\'' +
                ", flag=" + flag +
                '}';
    }
}
