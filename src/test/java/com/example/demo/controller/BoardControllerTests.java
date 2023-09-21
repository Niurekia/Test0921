package com.example.demo.controller;


import com.example.demo.dto.BoardDto;
import com.example.demo.restcontroller.BoardRestController;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(value={BoardController.class, BoardRestController.class})
public class BoardControllerTests {

    @Autowired
    MockMvc mvc;


    @DisplayName("GET /board/list 테스트")
    @Test
    public void t1() throws Exception{
        //given 초기상황,초기데이터 등 설정
        Long pageNo = 10L;

        //when 특정상황에서 수행

        //then 예상과 실제를 비교하여 기대결과 검증
        mvc.perform(get("/board/list")
                .param("pageNo", String.valueOf(pageNo)))
                .andExpect(status().isOk())
                .andDo(print());
    }

    @DisplayName("PUT /board/put 테스트")
    @Test
    public void t2() throws Exception {
        //given
        BoardDto dto=new BoardDto();
        dto.setNo(10L);
        dto.setTitle("Title");
        dto.setContents("Contents");
        dto.setWriter("board@put.test");

        ObjectMapper objectMapper=new ObjectMapper();
        String params=objectMapper.writeValueAsString(dto);

        //when

        //then
        mvc.perform(put("/board/put")
                .contentType(MediaType.APPLICATION_JSON)
                .content(params)
        )
                .andExpect(status().isOk())
                .andDo(print());


    }

}
