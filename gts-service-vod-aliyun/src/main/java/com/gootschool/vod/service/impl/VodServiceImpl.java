package com.gootschool.vod.service.impl;

import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.vod.model.v20170321.*;
import com.gootschool.common.code.RevanCodeEnum;
import com.gootschool.common.handler.RevanException;
import com.gootschool.common.response.RevanResponse;
import com.gootschool.vod.config.AliOSSProperties;
import com.gootschool.vod.service.IVodService;
import com.gootschool.vod.utils.AliyunVodClient;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@EnableConfigurationProperties(AliOSSProperties.class)
public class VodServiceImpl implements IVodService {

    @Autowired
    private AliOSSProperties properties;


    @Override
    public RevanResponse deleteVideoByVideoIds(List<String> videoIds) {
        try {
            DefaultAcsClient client = AliyunVodClient.initVodClient(this.properties.getAccessKeyId(),
                    this.properties.getAccessKeySecret());
            DeleteVideoRequest request = new DeleteVideoRequest();
            String videos = StringUtils.join(videoIds.toArray(), ",");
            request.setVideoIds(videos);
            DeleteVideoResponse response = client.getAcsResponse(request);
            return RevanResponse.ok().data("response", response);
        } catch (com.aliyuncs.exceptions.ClientException e) {
            return RevanResponse.ok().message("视频已删除");
        } catch (Exception e) {
            e.printStackTrace();
            throw new RevanException(RevanCodeEnum.PARAM_FAIL);
        }
    }

    @Override
    public RevanResponse deleteVideoById(String videoId) {
        try {
            DefaultAcsClient client = AliyunVodClient.initVodClient(this.properties.getAccessKeyId(),
                    this.properties.getAccessKeySecret());
            DeleteVideoRequest request = new DeleteVideoRequest();
            request.setVideoIds(videoId);
            DeleteVideoResponse response = client.getAcsResponse(request);
            return RevanResponse.ok().data("response", response);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RevanException(RevanCodeEnum.PARAM_FAIL);
        }
    }

    @Override
    public RevanResponse fetchVideoInfoById(String videoId) {
        try {
            DefaultAcsClient client = AliyunVodClient.initVodClient(this.properties.getAccessKeyId(),
                    this.properties.getAccessKeySecret());

            GetVideoInfoRequest request = new GetVideoInfoRequest();
            request.setVideoId(videoId);
            GetVideoInfoResponse response = client.getAcsResponse(request);
            return RevanResponse.ok().data("videoInfo", response);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RevanException(RevanCodeEnum.PARAM_FAIL);
        }
    }

    @Override
    public RevanResponse fetchVideoPlayAuth(String videoId) {
        try {
            DefaultAcsClient client = AliyunVodClient.initVodClient(this.properties.getAccessKeyId(),
                    this.properties.getAccessKeySecret());
            GetVideoPlayAuthRequest request = new GetVideoPlayAuthRequest();
            request.setVideoId(videoId);
            GetVideoPlayAuthResponse playAuthResponse = client.getAcsResponse(request);
            return RevanResponse.ok().data("playauth", playAuthResponse.getPlayAuth());
        } catch (Exception e) {
            e.printStackTrace();
            throw new RevanException(RevanCodeEnum.PARAM_FAIL);
        }
    }
}
