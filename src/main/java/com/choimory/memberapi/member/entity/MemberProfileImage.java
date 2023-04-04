package com.choimory.memberapi.member.entity;

import com.choimory.memberapi.common.entity.CommonDateTimeAt;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@NoArgsConstructor
@Getter
public class MemberProfileImage extends CommonDateTimeAt {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /*@ManyToOne
    @JoinColumn(name = "member_id")
    private Member member;*/

    //이미지 종류 코드
    @Enumerated(EnumType.STRING)
    private Type type;

    //원본 파일명
    private String originalFileName;

    //파일명
    private String fileName;

    //파일경로
    private String filePath;

    //파일크기
    private Long fileSize;

    //썸네일 파일명
    private String thumbNailFileName;

    //썸네일 파일경로
    private String thumbNamilFilePath;

    //썸네일 파일크기
    private String thumbNailFileSize;

    //이미지 종류 코드
    @AllArgsConstructor
    @Getter
    public enum Type {
        PROFILE,
        BACK_GROUND
    }

    @Builder(toBuilder = true)
    public MemberProfileImage(LocalDateTime createdAt, LocalDateTime modifiedAt, LocalDateTime deletedAt, Long id, Type type, String originalFileName, String fileName, String filePath, Long fileSize, String thumbNailFileName, String thumbNamilFilePath, String thumbNailFileSize) {
        super(createdAt, modifiedAt, deletedAt);
        this.id = id;
        this.type = type;
        this.originalFileName = originalFileName;
        this.fileName = fileName;
        this.filePath = filePath;
        this.fileSize = fileSize;
        this.thumbNailFileName = thumbNailFileName;
        this.thumbNamilFilePath = thumbNamilFilePath;
        this.thumbNailFileSize = thumbNailFileSize;
    }
}
