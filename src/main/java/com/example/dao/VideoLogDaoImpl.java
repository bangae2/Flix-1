package com.example.dao;

import com.example.entity.VideoLogEntity;
import com.example.repository.VideoLogRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by bangae1 on 2016-07-19.
 */
@Repository
public class VideoLogDaoImpl implements VideoLogDao {
    @Autowired
    private VideoLogRepository videoLogRepository;

    public void save(VideoLogEntity videoLogEntity) {
        VideoLogEntity videoLogEntity1 = videoLogRepository.findByVideoSeq(videoLogEntity.getVideo_seq(), videoLogEntity.getId());
        if(videoLogEntity1 == null) {
            this.videoLogRepository.save(videoLogEntity);
        } else {
            this.videoLogRepository.update(videoLogEntity.getId(), videoLogEntity.getInsert_date(), videoLogEntity.getVideo_seq(), videoLogEntity.getVideo_kind_seq());
        }
    }

    public VideoLogEntity findMaxDateByVideoKindSeq(int video_kind_seq) {
        return this.videoLogRepository.findMaxDateByVideoKindSeq(video_kind_seq);
    }

    @Override
    public List<VideoLogEntity> findMainAll(String id) {
        return this.videoLogRepository.findMainAll(id);
    }
}
