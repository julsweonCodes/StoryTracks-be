package com.T4.storyTracks.controller;

import com.T4.storyTracks.dto.*;
import com.T4.storyTracks.entity.BlogImgEntity;
import com.T4.storyTracks.entity.BlogPostEntity;
import com.T4.storyTracks.repository.BlogRepository;
import com.T4.storyTracks.service.BlogService;
import com.T4.storyTracks.service.S3Service;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.MapperFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.json.JsonMapper;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Description;
import org.springframework.http.*;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.service.invoker.HttpRequestValues;

import java.io.IOException;
import java.util.*;


//@Controller
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/blog")
public class BlogController {

    public final BlogRepository blogRepository;
    public final BlogService blogService;
    private final S3Service s3Service;
    JsonMapper mapper = JsonMapper.builder().configure(MapperFeature.USE_WRAPPER_NAME_AS_PROPERTY_NAME, true).build();

    @PostMapping("/generate")
    public ResponseEntity<?> generateAIText(@RequestBody GenInputDTO genInputDTO) {
        Map<String, String> genAIList = new HashMap<>();
        ImgMetaDTO imgMetaDTO = genInputDTO.getImgMetaDTO();

        genAIList = blogService.createPost(imgMetaDTO, genInputDTO.getOgText());
        return new ResponseEntity<>(genAIList, HttpStatus.OK);
    }

    @PostMapping("/save")
    public ResponseEntity<?> savePost(
            @RequestPart("blogPost") NewPostDTO newPostDTO, // JSON 데이터 처리
            @RequestPart("files") List<MultipartFile> files // 파일 처리
            ) {
        try {
            BlogPostDTO postDTO = new BlogPostDTO(newPostDTO.getTitle(), newPostDTO.getOgText(), newPostDTO.getAiGenText());
            BlogPostEntity postEntity = blogService.savePost(postDTO); // insert into post

            List<Map<String, String>> imgSaveList = new ArrayList<>();
            for (BlogImgDTO imgDTO : newPostDTO.getImgSaveList()) {
                //imgUrl = blogService.saveImgS3(imgDTO, postId);
                blogService.saveImgList(imgDTO, postEntity); // insert into imgs
            }


            for (MultipartFile file : files) {
                System.out.println("파일 이름: " + file.getOriginalFilename());
                System.out.println("파일 크기: " + file.getSize() + " bytes");
                System.out.println("파일 타입: " + file.getContentType());
                if (!file.isEmpty()) {
                    try {
                        String fileUrl = s3Service.uploadFile(file, "uploadtest1");
                    } catch (IOException e) {
                        return ResponseEntity.status(500).body("Failed to upload file: " + e.getMessage());
                    }
                }
            }

            return new ResponseEntity<>(postEntity.getPostId(), HttpStatus.OK);
        } catch (Exception e) {
            return ResponseEntity.status(500).body("File upload failed: " + e.getMessage());
        }

/*
        BlogPostDTO postDTO = new BlogPostDTO(newPostDTO.getTitle(), newPostDTO.getOgText(), newPostDTO.getAiGenText());
        BlogPostEntity postEntity = blogService.savePost(postDTO); // insert into post

        // String imgUrl = "";
        List<Map<String, String>> imgSaveList = new ArrayList<>();
        for (BlogImgDTO imgDTO : newPostDTO.getImgSaveList()) {
            //imgUrl = blogService.saveImgS3(imgDTO, postId);
            blogService.saveImgList(imgDTO, postEntity); // insert into imgs
        }

        // Handle file uploads
        for (MultipartFile file : files) {
            if (!file.isEmpty()) {
                try {
                    String fileUrl = s3Service.uploadFile(file, "uploadtest1");
                    return ResponseEntity.ok(fileUrl);
                } catch (IOException e) {
                    return ResponseEntity.status(500).body("Failed to upload file: " + e.getMessage());
                }
            }
        }

        return new ResponseEntity<>(postEntity.getPostId(), HttpStatus.OK);*/
    }

}
