package com.T4.storyTracks.dto;

import com.T4.storyTracks.entity.BlogImgEntity;
import com.T4.storyTracks.service.S3Service;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
public class BlogImgDTO {

    private Long imgId;
    private Long postId;
    private String geoLat;
    private String geoLong;
    private String fileName;
//    private MultipartFile imgFile;
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private LocalDateTime imgDtm;
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private LocalDateTime rgstDtm;
//    private String imgDtm;
//    private String rgstDtm;
    private String thumbYn;
//    private BlogPostEntity blogPost;

    public BlogImgDTO(Long imgId, Long postId, String geoLat, String geoLong, String fileName, LocalDateTime imgDtm, String thumbYn) {
        this.imgId = imgId;
        this.postId = postId;
        this.geoLat = geoLat;
        this.geoLong = geoLong;
        this.fileName = fileName;
        this.imgDtm = imgDtm;
        this.thumbYn = thumbYn;
    }

    public static BlogImgDTO toImgDTO(BlogImgEntity blogImgEntity) {
        SimpleDateFormat dtFormat = new SimpleDateFormat("yyyyMMddkkmmss");
        S3Service s3Service = new S3Service();

        BlogImgDTO blogImgDTO = new BlogImgDTO();
        blogImgDTO.setImgId(blogImgEntity.getImgId());
        blogImgDTO.setPostId(blogImgEntity.getBlogPost().getPostId());
        blogImgDTO.setGeoLat(blogImgEntity.getGeoLat());
        blogImgDTO.setGeoLong(blogImgEntity.getGeoLong());
//        blogImgDTO.setImgPath(blogImgEntity.getImgPath());
        blogImgDTO.setFileName(s3Service.getFileUrl(blogImgEntity.getImgPath()));
        blogImgDTO.setImgDtm(blogImgEntity.getImgDtm());
        blogImgDTO.setRgstDtm(blogImgEntity.getRgstDTm());
        blogImgDTO.setThumbYn(blogImgEntity.getThumbYn());

        return blogImgDTO;

    }
}
