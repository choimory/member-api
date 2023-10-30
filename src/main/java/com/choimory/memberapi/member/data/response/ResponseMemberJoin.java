package com.choimory.memberapi.member.data.response;

import com.choimory.memberapi.member.controller.MemberController;
import lombok.Builder;
import lombok.Getter;
import org.springframework.hateoas.RepresentationModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;

@Getter
public class ResponseMemberJoin extends RepresentationModel<ResponseMemberJoin> {
    private final String identity;
    private final String email;

    @Builder
    public ResponseMemberJoin(String identity, String email) {
        this.identity = identity;
        this.email = email;

        //HATEOAS
        add(WebMvcLinkBuilder.linkTo(MemberController.class).withSelfRel());
        //add(WebMvcLinkBuilder.linkTo(MemberController.class).slash("login").withRel("로그인"));
        add(WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(MemberController.class).login(null)).withRel("로그인"));
    }
}
