package com.etwicaksono.btpnonlineshop.service;

import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.concurrent.TimeUnit;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import io.minio.BucketExistsArgs;
import io.minio.GetPresignedObjectUrlArgs;
import io.minio.MakeBucketArgs;
import io.minio.MinioClient;
import io.minio.ObjectWriteResponse;
import io.minio.PutObjectArgs;
import io.minio.RemoveObjectArgs;
import io.minio.errors.ErrorResponseException;
import io.minio.errors.InsufficientDataException;
import io.minio.errors.InternalException;
import io.minio.errors.InvalidResponseException;
import io.minio.errors.ServerException;
import io.minio.errors.XmlParserException;
import io.minio.http.Method;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j
public class MinioService {
   private final MinioClient minioClient;

   private String getLink(String bucket, String filename, Long expiry)
         throws InvalidKeyException, ErrorResponseException, InsufficientDataException, InternalException,
         InvalidResponseException, XmlParserException, ServerException, IllegalArgumentException, IOException,
         NoSuchAlgorithmException {
      return minioClient.getPresignedObjectUrl(
            GetPresignedObjectUrlArgs.builder()
                  .method(Method.GET)
                  .bucket(bucket)
                  .object(filename)
                  .expiry(Math.toIntExact(expiry), TimeUnit.SECONDS)
                  .build());
   }

   public String generateMinioURL(String bucketName, String objectName) {
      try {
         if (objectName == null) {
            return null;
         }
         return getLink(bucketName, objectName, 3600L);
      } catch (Exception e) {
         log.error(e.getMessage());
         return null;
      }
   }

   public ObjectWriteResponse upload(String bucketName, String fileName, MultipartFile file)
         throws ErrorResponseException, InsufficientDataException, InternalException, InvalidKeyException,
         InvalidResponseException, IOException, NoSuchAlgorithmException, ServerException, XmlParserException {

      log.info(String.format("Check is bucket %s exist", bucketName));
      boolean isBucketExist = minioClient.bucketExists(BucketExistsArgs.builder().bucket(bucketName).build());
      log.info(String.format("Bucket %s exist: %s", bucketName, isBucketExist));
      if (!isBucketExist) {
         log.info("Creating bucket: " + bucketName);
         minioClient.makeBucket(MakeBucketArgs.builder().bucket(bucketName).build());
      }

      // Upload file to MinIO server
      return minioClient.putObject(
            PutObjectArgs.builder()
                  .bucket(bucketName)
                  .object(fileName)
                  .stream(file.getInputStream(), file.getSize(), -1)
                  .contentType(file.getContentType())
                  .build());
   }

   // delete file from MinIO server
   public void delete(String bucketName, String objectName)
         throws ErrorResponseException, InsufficientDataException, InternalException, InvalidKeyException,
         InvalidResponseException, IOException, NoSuchAlgorithmException, ServerException, XmlParserException {
      RemoveObjectArgs target = RemoveObjectArgs.builder().bucket(bucketName).object(objectName).build();
      minioClient.removeObject(target);
   }
}
