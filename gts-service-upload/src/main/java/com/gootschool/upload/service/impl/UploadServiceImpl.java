package com.gootschool.upload.service.impl;

import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClientBuilder;
import com.aliyun.oss.model.CannedAccessControlList;
import com.aliyun.vod.upload.impl.UploadVideoImpl;
import com.aliyun.vod.upload.req.UploadStreamRequest;
import com.aliyun.vod.upload.resp.UploadStreamResponse;
import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.vod.model.v20170321.DeleteVideoRequest;
import com.aliyuncs.vod.model.v20170321.DeleteVideoResponse;
import com.aliyuncs.vod.model.v20170321.GetVideoInfoRequest;
import com.aliyuncs.vod.model.v20170321.GetVideoInfoResponse;
import com.gootschool.common.code.RevanCodeEnum;
import com.gootschool.common.handler.RevanException;
import com.gootschool.common.response.RevanResponse;
import com.gootschool.upload.config.AliOSSProperties;
import com.gootschool.upload.service.IUploadService;
import com.gootschool.upload.utils.AliyunVodClient;
import org.apache.commons.lang3.StringUtils;
import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.List;
import java.util.UUID;

@Service
@EnableConfigurationProperties(AliOSSProperties.class)
public class UploadServiceImpl implements IUploadService {

    @Autowired
    private AliOSSProperties aliOSSProperties;

    @Override
    public RevanResponse upload(MultipartFile file, String hostName) {
        // Endpoint以杭州为例，其它Region请按实际情况填写。
        String endpoint = this.aliOSSProperties.getEndpoint();
        // 云账号AccessKey有所有API访问权限，建议遵循阿里云安全最佳实践，创建并使用RAM子账号进行API访问或日常运维，请登录 https://ram.console.aliyun.com 创建。
        String accessKeyId = this.aliOSSProperties.getAccessKeyId();
        String accessKeySecret = this.aliOSSProperties.getAccessKeySecret();
        String bucketName = this.aliOSSProperties.getBucketName();
        String fileHost = this.aliOSSProperties.getFileHost();
        String uploadUrl = null;

        // 创建OSSClient实例。
        OSS ossClient = new OSSClientBuilder().build(endpoint, accessKeyId, accessKeySecret);

        if (!ossClient.doesBucketExist(bucketName)) {
            // 创建bucket
            ossClient.createBucket(bucketName);
            // 设置oss实例的访问权限：公共读
            ossClient.setBucketAcl(bucketName, CannedAccessControlList.PublicRead);
        }

        // 上传文件流。
        InputStream inputStream = null;
        try {
            inputStream = file.getInputStream();
            // 构建日期路径：avatar/2019/12/12/文件名
            String filePath = new DateTime().toString("yyyy/MM/dd");
            // 文件名：uuid.扩展名
            String originalFilename = file.getOriginalFilename();
            String fileName = UUID.randomUUID().toString();
            String fileType = originalFilename.substring(originalFilename.lastIndexOf("."));
            String newName = fileName + fileType;
            if (StringUtils.isNotBlank(hostName)) {
                fileHost = hostName;
            }
            String fileUrl = fileHost + "/" + filePath + "/" + newName;

            // 文件上传到阿里云
            ossClient.putObject(bucketName, fileUrl, inputStream);

            // 关闭OSSClient。
            ossClient.shutdown();

            // 拼接url地址
            uploadUrl = "http://" + bucketName + "." + endpoint + "/" + fileUrl;

        } catch (Exception e) {
            e.printStackTrace();
            throw new RevanException(RevanCodeEnum.UPLOAD_FAIL);
        }

        return RevanResponse.ok().data("url", uploadUrl);
    }

    @Override
    public RevanResponse uploadVideo(MultipartFile file) {
        try {
            //获取上传文件名称
            //视频名称.mp4
            String fileName = file.getOriginalFilename();
            String title = fileName.substring(0, fileName.lastIndexOf("."));
            String accessKeyId = this.aliOSSProperties.getAccessKeyId();
            String accessKeySecret = this.aliOSSProperties.getAccessKeySecret();

            UploadStreamRequest request = new UploadStreamRequest(accessKeyId, accessKeySecret,
                    title, fileName, file.getInputStream());
            UploadVideoImpl uploader = new UploadVideoImpl();
            UploadStreamResponse response = uploader.uploadStream(request);
            String videoId = null;
            if (response.isSuccess()) {
                videoId = response.getVideoId();
            } else { //如果设置回调URL无效，不影响视频上传，可以返回VideoId同时会返回错误码。其他情况上传失败时，VideoId为空，此时需要根据返回错误码分析具体错误原因
                videoId = response.getVideoId();
            }
            return RevanResponse.ok().data("videoId", videoId);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RevanException(RevanCodeEnum.PARAM_FAIL);
        }
    }


    @Override
    public RevanResponse deleteVideoByVideoIds(List<String> videoIds) {
        try {
            DefaultAcsClient client = AliyunVodClient.initVodClient(this.aliOSSProperties.getAccessKeyId(),
                    this.aliOSSProperties.getAccessKeySecret());
            DeleteVideoRequest request = new DeleteVideoRequest();
            String videos = StringUtils.join(videoIds.toArray(), ",");
            request.setVideoIds(videos);
            DeleteVideoResponse response = client.getAcsResponse(request);
            return RevanResponse.ok().data("response", response);
        }
        catch (com.aliyuncs.exceptions.ClientException e) {
            return RevanResponse.ok().message("视频已删除");
        }
        catch (Exception e) {
            e.printStackTrace();
            throw new RevanException(RevanCodeEnum.PARAM_FAIL);
        }
    }

    @Override
    public RevanResponse deleteVideoById(String videoId) {
        try {
            DefaultAcsClient client = AliyunVodClient.initVodClient(this.aliOSSProperties.getAccessKeyId(),
                    this.aliOSSProperties.getAccessKeySecret());
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
            DefaultAcsClient client = AliyunVodClient.initVodClient(this.aliOSSProperties.getAccessKeyId(),
                    this.aliOSSProperties.getAccessKeySecret());

            GetVideoInfoRequest request = new GetVideoInfoRequest();
            request.setVideoId(videoId);
            GetVideoInfoResponse response = client.getAcsResponse(request);
            return RevanResponse.ok().data("videoInfo", response);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RevanException(RevanCodeEnum.PARAM_FAIL);
        }
    }
}
