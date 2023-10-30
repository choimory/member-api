package com.choimory.memberapi.member.data.request;

import com.choimory.memberapi.member.entity.Member;
import com.choimory.memberapi.member.entity.MemberImage;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.hibernate.validator.constraints.Length;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import javax.validation.constraints.*;
import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Getter
public class RequestMemberJoin {
    //아이디
    @NotEmpty
    @NotBlank
    @Length(min = 8, max = 20)
    private final String identity;

    //비밀번호
    @NotEmpty
    @NotBlank
    @Length(min = 8, max = 20)
    private final String password;

    //이메일
    @Email
    private final String email;

    //자기소개
    @Max(1000)
    private final String profile;

    //이미지
    private final List<@Valid Image> images;

    @RequiredArgsConstructor
    @Getter
    public static class Image{
        private final MemberImage.Type type;
        private final MultipartFile file;
    }

    public Member toEntity (PasswordEncoder passwordEncoder, List<MemberImage> images){
        return Member.builder()
                .identity(identity)
                .password(passwordEncoder.encode(password))
                .email(email)
                .profile(profile)
                .memberImages(images)
                .build();
    }
}
