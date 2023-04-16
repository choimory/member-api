package com.choimory.memberapi.member.controller;

import com.choimory.memberapi.config.SpringRestDocsConfig;
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
        ResultActions when = mockMvc.perform(MockMvcRequestBuilders.get("/member/{identity}", identity)
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON));

        /*then*/
        when.andDo(MockMvcResultHandlers.print());
        if(isSuccess){
            when.andExpect(MockMvcResultMatchers.status().is(httpStatus.value()))
                    .andExpect(MockMvcResultMatchers.jsonPath("member.identity").value(identity))
                    .andExpect(MockMvcResultMatchers.jsonPath("member.password").doesNotExist());
        } else {
            when.andExpect(MockMvcResultMatchers.status().is(httpStatus.value()))
                    .andExpect(MockMvcResultMatchers.jsonPath("status").value(httpStatus.value()))
                    .andExpect(MockMvcResultMatchers.jsonPath("message").value(httpStatus.getReasonPhrase()))
                    .andExpect(MockMvcResultMatchers.jsonPath("member").doesNotExist());
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

    @Test
    void login() {
        /*given*/
        /*when*/
        /*then*/
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

}