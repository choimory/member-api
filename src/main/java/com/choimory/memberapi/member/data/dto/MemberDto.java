package com.choimory.memberapi.member.data.dto;

import com.choimory.memberapi.member.entity.Member;
import com.choimory.memberapi.member.entity.MemberAuthority;
import com.choimory.memberapi.member.entity.MemberImage;
import com.choimory.memberapi.member.entity.MemberSuspension;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class MemberDto {
    private Long id;
    private String identity;

    @JsonIgnore
    private String password;

    private String email;
    private String profile;

    private List<MemberImageDto> memberImages;
    private MemberAuthorityDto memberAuthority;
    private List<MemberSuspensionDto> memberSuspensions;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss", timezone = "Asia/Seoul")
    private LocalDateTime createdAt;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss", timezone = "Asia/Seoul")
    private LocalDateTime modifiedAt;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss", timezone = "Asia/Seoul")
    private LocalDateTime deletedAt;

    public static MemberDto toDto (Member member) {
        return member == null
                ? null
                : MemberDto.builder()
                    .id(member.getId())
                    .identity(member.getIdentity())
                    .password(member.getPassword())
                    .email(member.getEmail())
                    .profile(member.getProfile())
                    .memberImages(member.getMemberImages().stream()
                            .map(MemberImageDto::toDto)
                            .collect(Collectors.toUnmodifiableList()))
                    .memberAuthority(MemberAuthorityDto.toDto(member.getMemberAuthority()))
                    .memberSuspensions(member.getMemberSuspensions().stream()
                            .map(MemberSuspensionDto::toDto)
                            .collect(Collectors.toUnmodifiableList()))
                    .createdAt(member.getCreatedAt())
                    .modifiedAt(member.getModifiedAt())
                    .deletedAt(member.getDeletedAt())
                .build();
    }

    public Boolean isSuspendedMember () {
        return memberSuspensions.stream()
                .anyMatch(memberSuspension -> {
                    LocalDateTime now = LocalDateTime.now();
                    LocalDateTime at = memberSuspension.getSuspendedAt();
                    LocalDateTime to = memberSuspension.getSuspendedTo();
                    return at.isBefore(now) && to.isAfter(now);
                });
    }


    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    @Getter
    public static class MemberImageDto {
        private Long id;
        private MemberImage.Type type;
        private String originalFileName;
        private String fileName;
        private String filePath;
        private Long fileSize;
        private String thumbNailFileName;
        private String thumbNailFilePath;
        private String thumbNailFileSize;

        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss", timezone = "Asia/Seoul")
        private LocalDateTime createdAt;
        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss", timezone = "Asia/Seoul")
        private LocalDateTime modifiedAt;
        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss", timezone = "Asia/Seoul")
        private LocalDateTime deletedAt;

        public static MemberImageDto toDto (MemberImage memberImage) {
            return memberImage == null
                    ? null
                    : MemberImageDto.builder()
                        .id(memberImage.getId())
                        .type(memberImage.getType())
                        .originalFileName(memberImage.getOriginalFileName())
                        .fileName(memberImage.getFileName())
                        .filePath(memberImage.getFilePath())
                        .fileSize(memberImage.getFileSize())
                        .thumbNailFileName(memberImage.getThumbNailFileName())
                        .thumbNailFilePath(memberImage.getThumbNailFilePath())
                        .thumbNailFileSize(memberImage.getThumbNailFileSize())
                        .createdAt(memberImage.getCreatedAt())
                        .modifiedAt(memberImage.getModifiedAt())
                        .deletedAt(memberImage.getDeletedAt())
                    .build();
        }
    }

    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    @Getter
    public static class MemberAuthorityDto {
        private Long id;
        private MemberAuthority.Level level;

        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss", timezone = "Asia/Seoul")
        private LocalDateTime createdAt;
        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss", timezone = "Asia/Seoul")
        private LocalDateTime modifiedAt;
        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss", timezone = "Asia/Seoul")
        private LocalDateTime deletedAt;

        public static MemberAuthorityDto toDto (MemberAuthority memberAuthority) {
            return memberAuthority == null
                    ? null
                    : MemberAuthorityDto.builder()
                        .id(memberAuthority.getId())
                        .level(memberAuthority.getLevel())
                        .createdAt(memberAuthority.getCreatedAt())
                        .modifiedAt(memberAuthority.getModifiedAt())
                        .deletedAt(memberAuthority.getDeletedAt())
                    .build();
        }
    }

    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    @Getter
    public static class MemberSuspensionDto {
        private Long id;
        private String reason;

        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss", timezone = "Asia/Seoul")
        private LocalDateTime suspendedAt;
        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss", timezone = "Asia/Seoul")
        private LocalDateTime suspendedTo;
        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss", timezone = "Asia/Seoul")
        private LocalDateTime createdAt;
        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss", timezone = "Asia/Seoul")
        private LocalDateTime modifiedAt;
        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss", timezone = "Asia/Seoul")
        private LocalDateTime deletedAt;

        public static MemberSuspensionDto toDto (MemberSuspension memberSuspension) {
            return memberSuspension == null
                    ? null
                    : MemberSuspensionDto.builder()
                        .id(memberSuspension.getId())
                        .reason(memberSuspension.getReason())
                        .suspendedAt(memberSuspension.getSuspendedAt())
                        .suspendedTo(memberSuspension.getSuspendedTo())
                        .createdAt(memberSuspension.getCreatedAt())
                        .modifiedAt(memberSuspension.getModifiedAt())
                        .deletedAt(memberSuspension.getDeletedAt())
                    .build();
        }
    }
}
