package com.etwicaksono.btpnonlineshop.service;

import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.concurrent.TimeUnit;

import org.springframework.stereotype.Service;

import io.minio.GetPresignedObjectUrlArgs;
import io.minio.MinioClient;
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
         return getLink(bucketName, objectName, 3600L);
      } catch (Exception e) {
         log.error(e.getMessage());
         return null;
      }
   }
}
