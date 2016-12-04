package com.example.controller;

import com.example.entity.UsersEntity;
import com.example.entity.VideoFavEntity;
import com.example.service.VideoFavService;
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
 * Created by bangae1 on 2016-07-20.
 */
@Controller
@RequestMapping("/fav")
public class VideoFavController {
    @Autowired
    private VideoFavService videoFavService;

    @RequestMapping(value = "/save/{video_seq}", method = RequestMethod.POST)
    @ResponseBody
    public String save(@PathVariable("video_seq")int video_seq) {
        VideoFavEntity videoFavEntity = this.videoFavService.findOne(video_seq);
        if(videoFavEntity == null) {
            VideoFavEntity favouritesEntity = new VideoFavEntity();
            UsersEntity usersEntity = (UsersEntity) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            favouritesEntity.setId(usersEntity.getId());
            favouritesEntity.setVideo_seq(video_seq);
            this.videoFavService.save(favouritesEntity);
            return "S";
        } else {
            this.videoFavService.delete(video_seq);
            return "D";
        }

    }

    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public String list(Model model) {
        List<VideoFavEntity> lists = videoFavService.findAll();
        model.addAttribute("favs", lists);
        return "pages/fav";
    }
}
