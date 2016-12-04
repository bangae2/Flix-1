package com.example.controller;

import com.example.config.HibernateProxyTypeAdapter;
import com.example.entity.VideoLogEntity;
import com.example.entity.VideosEntity;
import com.example.entity.VideosKindEntity;
import com.example.service.VideosKindService;
import com.example.service.VideosService;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartRequest;

import java.io.*;
import java.util.List;

/**
 * Created by bangae1 on 2016-07-21.
 */
@Controller
@RequestMapping("/admin")
@PropertySource(value = "classpath:config.properties")
public class AdminController {
    @Autowired
    private Environment env;

    @Autowired
    private VideosKindService videosKindService;

    @Autowired
    private VideosService videosService;

    @RequestMapping(value = "/kind", method = RequestMethod.GET)
    public String kind(Model model) {
        List<VideosKindEntity> kinds = videosKindService.findByFlag();
        model.addAttribute("kinds", kinds);
        model.addAttribute("movies", videosService.findAllByVideoKindSeq(kinds.get(0).getVideo_kind_seq()));
        return "admin/kind";
    }

    @RequestMapping(value = "/findMovie/{video_kind_seq}", method = RequestMethod.POST, produces = "application/json;charset=utf-8")
    @ResponseBody
    public String findKind(Model model, @PathVariable("video_kind_seq")int video_kind_seq) {
        List<VideosEntity> lists = this.videosService.findAllByVideoKindSeq(video_kind_seq);
        for(VideosEntity ve : lists) {
            ve.setVideoLogEntities(null);
        }
        GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.registerTypeAdapterFactory(HibernateProxyTypeAdapter.FACTORY);
        Gson gson = gsonBuilder.create();
        return gson.toJson(lists);
    }

    @RequestMapping(value = "/kindUP", method = RequestMethod.POST, produces = "application/json;charset=utf-8")
    @ResponseBody
    public String kindUP(@RequestParam("thumbnail")MultipartFile file, @ModelAttribute("cmd")VideosKindEntity videosKindEntity) {
//        MultipartFile file = multipartRequest.getFile("thumbnail");
        return this.videosKindService.kindUp(videosKindEntity, file);
    }

    @RequestMapping(value = "/kindDel/{video_kind_seq}", method = RequestMethod.POST, produces = "plain/text;charset=utf-8")
    @ResponseBody
    public String kindDel(@PathVariable("video_kind_seq")int video_kind_seq) {
        this.videosKindService.delete(video_kind_seq);
        return "";
    }

    @RequestMapping(value = "/movieDel/{video_seq}", method = RequestMethod.POST, produces = "plain/text;charset=utf-8")
    @ResponseBody
    public String movieDel(@PathVariable("video_seq")int video_seq) {
        this.videosService.movieDel(video_seq);
        return "";
    }

    @RequestMapping(value = "/movieUP", method = RequestMethod.POST, produces = "application/json;charset=utf-8")
    @ResponseBody
    public String movieUP(@RequestParam("movie")MultipartFile multipartFile, @ModelAttribute("cmd")VideosEntity videosEntity) {
        return this.videosService.movieUP(videosEntity, multipartFile);
    }

    @RequestMapping(value="/thumbnail/{video_kind_seq}", method=RequestMethod.POST, produces = "text/plain;charset=UTF-8")
    @ResponseBody
    public String kind_thumbnail(@PathVariable("video_kind_seq")int video_kind_seq) {
        VideosKindEntity videoKindEntity = this.videosKindService.findOne(video_kind_seq);
        String coverPath = videoKindEntity.getCover_path();
        String realPath = env.getProperty("video.real.path");
        String path = realPath.substring(0, realPath.length() -1)+lastIndexOfLoop(coverPath, "/", 2) + "/";
        String command = realPath + env.getProperty("video.real.resource.path") + "thumbnailAuto.sh "+path;
        System.out.println(realPath + env.getProperty("video.real.resource.path") + "thumbnailAuto.sh "+path);
        Runtime runtime = Runtime.getRuntime();
        try {
            Process process = runtime.exec(command);
            InputStream is = process.getInputStream();
            InputStreamReader isr = new InputStreamReader(is);
            BufferedReader br = new BufferedReader(isr);
            String line = "";
            while((line = br.readLine()) != null) {
                System.out.println(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "seuccess";
    }

    public String lastIndexOfLoop(String str, String targetStr, int index) {
        String strs = str;
        int i= 0;
        while(i < index) {
            strs = str.substring(0, strs.lastIndexOf(targetStr));
            i++;
        }
        return strs;
    }
}
