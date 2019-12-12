package com.gootschool.upload.alioss;

import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClientBuilder;
import com.gootschool.upload.config.AliOSSProperties;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.ByteArrayInputStream;

@SpringBootTest
@RunWith(SpringRunner.class)
@EnableConfigurationProperties(AliOSSProperties.class)
public class AliOssTest {

    @Autowired
    private AliOSSProperties properties;

    /**
     * 创建存储空间
     */
    @Test
    public void createStroage() {
        // Endpoint以杭州为例，其它Region请按实际情况填写。
        String endpoint = "http://" + this.properties.getEndpoint();
        // 阿里云主账号AccessKey拥有所有API的访问权限，风险很高。强烈建议您创建并使用RAM账号进行API访问或日常运维，请登录 https://ram.console.aliyun.com 创建RAM账号。
        String accessKeyId = this.properties.getAccessKeyId();
        String accessKeySecret = this.properties.getAccessKeySecret();
        String bucketName = "gootschool2";

        // 创建OSSClient实例。
        OSS ossClient = new OSSClientBuilder().build(endpoint, accessKeyId, accessKeySecret);

        // 创建存储空间。
        ossClient.createBucket(bucketName);

        // 关闭OSSClient。
        ossClient.shutdown();

    }


    /**
     * 上传文件
     */
    @Test
    public void uploadFile() {
        // Endpoint以杭州为例，其它Region请按实际情况填写。
        String endpoint = "http://" + this.properties.getEndpoint();
        // 阿里云主账号AccessKey拥有所有API的访问权限，风险很高。强烈建议您创建并使用RAM账号进行API访问或日常运维，请登录 https://ram.console.aliyun.com 创建RAM账号。
        String accessKeyId = this.properties.getAccessKeyId();
        String accessKeySecret = this.properties.getAccessKeySecret();
        String bucketName = this.properties.getBucketName();
        // <yourObjectName>上传文件到OSS时需要指定包含文件后缀在内的完整路径，例如abc/efg/123.jpg。
        String objectName = "1.txt";

        // 创建OSSClient实例。
        OSS ossClient = new OSSClientBuilder().build(endpoint, accessKeyId, accessKeySecret);

        // 上传内容到指定的存储空间（bucketName）并保存为指定的文件名称（objectName）。
        String content = "Hello OSS";
        ossClient.putObject(bucketName, objectName, new ByteArrayInputStream(content.getBytes()));

        // 关闭OSSClient。
        ossClient.shutdown();

    }

}
