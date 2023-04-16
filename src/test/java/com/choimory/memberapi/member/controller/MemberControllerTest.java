package com.choimory.memberapi.member.controller;

import com.choimory.memberapi.config.SpringRestDocsConfig;
import com.choimory.memberapi.member.data.request.RequestMemberLogin;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.restdocs.AutoConfigureRestDocs;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.stream.Stream;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
@AutoConfigureRestDocs
@Import({SpringRestDocsConfig.class})
@ActiveProfiles({"dev", "local"})
class MemberControllerTest {
    @Autowired
    MockMvc mockMvc;

    @Autowired
    ObjectMapper objectMapper;

    @Test
    void findMe() {
        /*given*/
        /*when*/
        /*then*/
    }

    @DisplayName("타인 조회 테스트")
    @ParameterizedTest
    @MethodSource("findOtherMethodSource")
    void findOther(final boolean isSuccess, final String identity, final HttpStatus httpStatus) throws Exception {
        /*given*/
        /*when*/
        // ResultActions when = mockMvc.perform(RestDocumentationRequestBuilders.get("/member/{identity}", identity) - MockMvcRequestBuilder.get()이 pathRequest 지원 안하는 버전일시 사용
        ResultActions when = mockMvc.perform(get("/member/{identity}", identity)
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON));

        /*then*/
        when.andDo(MockMvcResultHandlers.print());
        if(isSuccess){
            when.andExpect(status().is(httpStatus.value()))
                    .andExpect(jsonPath("member.identity").value(identity))
                    .andExpect(jsonPath("member.password").doesNotExist());
        } else {
            when.andExpect(status().is(httpStatus.value()))
                    .andExpect(jsonPath("status").value(httpStatus.value()))
                    .andExpect(jsonPath("message").value(httpStatus.getReasonPhrase()))
                    .andExpect(jsonPath("member").doesNotExist());
        }
    }

    @Test
    void join() {
        /*given*/
        /*when*/
        /*then*/
    }

    @Test
    void update() {
        /*given*/
        /*when*/
        /*then*/
    }

    @DisplayName("로그인 테스트")
    @ParameterizedTest
    @MethodSource("loginMethodSource")
    void login(final boolean isSuccess, final String identity, final String password, final HttpStatus httpStatus) throws Exception {
        /*given*/
        RequestMemberLogin param = RequestMemberLogin.builder()
                .identity(identity)
                .password(password)
                .build();

        /*when*/
        ResultActions when = mockMvc.perform(post("/member/login")
                .content(objectMapper.writeValueAsString(param))
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON));

        /*then*/
        when.andDo(MockMvcResultHandlers.print());
        if(isSuccess){
            when.andExpect(status().is(httpStatus.value()))
                    .andExpect(jsonPath("identity").value(identity))
                    .andExpect(jsonPath("token").exists());
        } else {
            when.andExpect(status().is(httpStatus.value()));
        }
    }

    @Test
    void ban() {
        /*given*/
        /*when*/
        /*then*/
    }

    @Test
    void withdrawal() {
        /*given*/
        /*when*/
        /*then*/
    }

    static Stream<Arguments> findOtherMethodSource(){
        return Stream.<Arguments>builder()
                .add(Arguments.arguments(true, "choimory", HttpStatus.OK))
                .add(Arguments.arguments(false, "asdfasdf", HttpStatus.NOT_FOUND))
                .build();
    }

    static Stream<Arguments> loginMethodSource(){
        return Stream.<Arguments>builder()
                .add(Arguments.arguments(true, "choimory", "qwe123!@#", HttpStatus.OK))
                .add(Arguments.arguments(false, "choimory", "qweqwe", HttpStatus.BAD_REQUEST))
                .add(Arguments.arguments(false, "choimoryy", "qwe123!@#", HttpStatus.NOT_FOUND))
                .add(Arguments.arguments(false, "cho imory", "qwe123!@#", HttpStatus.NOT_FOUND))
                .add(Arguments.arguments(false, "choimory", "as dqwe123", HttpStatus.BAD_REQUEST))
                .add(Arguments.arguments(false, "", "asdqwe123", HttpStatus.BAD_REQUEST))
                .add(Arguments.arguments(false, "choimory", "", HttpStatus.BAD_REQUEST))
                .add(Arguments.arguments(false, null, "asdqwe123", HttpStatus.BAD_REQUEST))
                .add(Arguments.arguments(false, "choimory", null, HttpStatus.BAD_REQUEST))
                .build();
    }
}