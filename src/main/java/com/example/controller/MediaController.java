package com.example.controller;

import com.example.entity.UsersEntity;
import com.example.entity.VideoFavEntity;
import com.example.entity.VideoLogEntity;
import com.example.entity.VideosEntity;
import com.example.service.VideoFavService;
import com.example.service.VideoLogService;
import com.example.service.VideosKindService;
import com.example.service.VideosService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * Created by bangae1 on 2016-07-16.
 */
@Controller
@RequestMapping("/media")
public class MediaController {
    @Autowired
    private VideosService videosService;

    @Autowired
    private VideosKindService videosKindService;

    @Autowired
    private VideoLogService videoLogService;

    @Autowired
    private VideoFavService videoFavService;

    @RequestMapping(value = "/list/{video_kind_seq}", method = RequestMethod.GET)
    public String mediaList(@PathVariable("video_kind_seq")Integer video_kind_seq, Model model) {
        List<VideosEntity> videosEntities = this.videosService.findAllByVideoKindSeq(video_kind_seq);
        VideoLogEntity videoLogEntity = new VideoLogEntity();
        try {
            videoLogEntity = this.videoLogService.findMaxDateByVideoKindSeq(video_kind_seq);
            videoLogEntity.getVideosEntity().getVideo_seq();
        } catch(NullPointerException e) {
            System.out.println("result empty!");
            videoLogEntity = new VideoLogEntity();
            videoLogEntity.setVideosEntity(videosEntities.get(0));
            System.out.println(videoLogEntity.toString());
        }
        model.addAttribute("videos", videosEntities);
        model.addAttribute("videoKind",this.videosKindService.findOne(video_kind_seq));
        model.addAttribute("videoLog", videoLogEntity);
        try {
            model.addAttribute("videoFav", this.videoFavService.findOne(videoLogEntity.getVideosEntity().getVideo_seq()));
        } catch(NullPointerException e) {
            VideoFavEntity videoFavEntity = new VideoFavEntity();
            model.addAttribute("videoFav", videoFavEntity);
        }
        return "pages/media";
    }

    @RequestMapping(value = "/slist/{video_seq}", method = RequestMethod.GET)
    public String media(@PathVariable("video_seq")Integer video_seq, Model model) {

        VideosEntity videosEntity = this.videosService.findOne(video_seq);
        List<VideosEntity> videosEntities = this.videosService.findAllByVideoKindSeq(videosEntity.getVideo_kind_seq());
        VideoLogEntity videoLogEntity = new VideoLogEntity();
        videoLogEntity.setVideosEntity(videosEntity);
        model.addAttribute("videos", videosEntities);
        model.addAttribute("videoKind",this.videosKindService.findOne(videosEntity.getVideo_kind_seq()));
        model.addAttribute("videoLog", videoLogEntity);
        model.addAttribute("videoFav", this.videoFavService.findOne(videoLogEntity.getVideosEntity().getVideo_seq()));
        return "pages/media";
    }

    @RequestMapping(value = "/log/save/{video_kind_seq}/{video_seq}", method = RequestMethod.POST)
    @ResponseBody
    public String mediaLog(@PathVariable("video_seq")Integer video_seq, @PathVariable("video_kind_seq")Integer video_kind_seq) {
        UsersEntity usersEntity = (UsersEntity) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        VideoLogEntity videoLogEntity = new VideoLogEntity();
        videoLogEntity.setVideo_seq(video_seq);
        videoLogEntity.setVideo_kind_seq(video_kind_seq);
        videoLogEntity.setId(usersEntity.getId());
        videoLogService.save(videoLogEntity);
        return "success";
    }
}
