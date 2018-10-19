package org.yyx.wx.manage.menu.controller;

import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import org.yyx.wx.manage.ManageApplicationTests;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@FixMethodOrder(value = MethodSorters.NAME_ASCENDING)
public class MenuControllerTest extends ManageApplicationTests {

    /**
     * MenuControllerTest 日志输出
     */
    private static final Logger LOGGER = LoggerFactory.getLogger(MenuControllerTest.class);
    private MockMvc mockMvc;
    @Autowired
    private WebApplicationContext context;

    @Test
    public void queryMenu() throws Exception {
        String contentAsString = mockMvc.perform(get("/api/menu/list"))
                .andExpect(status().isOk())
                .andDo(print())
                .andReturn().getResponse().getContentAsString();
        LOGGER.info("{} -> [响应结果] {}", this.getClass().getName(), contentAsString);
    }

    @Before
    public void setUp() {
        mockMvc = MockMvcBuilders.webAppContextSetup(context)
                .build();
    }
}