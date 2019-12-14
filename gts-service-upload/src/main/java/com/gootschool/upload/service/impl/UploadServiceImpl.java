package com.gootschool.upload.service.impl;

import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClientBuilder;
import com.aliyun.oss.model.CannedAccessControlList;
import com.gootschool.common.code.RevanCodeEnum;
import com.gootschool.common.handler.RevanException;
import com.gootschool.common.response.RevanResponse;
import com.gootschool.upload.config.AliOSSProperties;
import com.gootschool.upload.service.IUploadService;
import net.sf.jsqlparser.expression.DateTimeLiteralExpression;
import org.apache.commons.lang3.StringUtils;
import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
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
}
