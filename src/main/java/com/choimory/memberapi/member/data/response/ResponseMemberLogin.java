package com.choimory.memberapi.member.data.response;

import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.hateoas.RepresentationModel;

@Builder
@RequiredArgsConstructor
@Getter
public class ResponseMemberLogin extends RepresentationModel<ResponseMemberLogin> {
    private final String identity;
    private final String token;
}
