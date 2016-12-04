package com.example.service;

import com.example.entity.VideoLogEntity;

import java.util.List;

/**
 * Created by bangae1 on 2016-07-19.
 */
public interface VideoLogService {
    public void save(VideoLogEntity videoLogEntity);
    public VideoLogEntity findMaxDateByVideoKindSeq(int video_kind_seq);
    public List<VideoLogEntity> findMainAll(String id);
}
