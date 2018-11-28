package org.yyx.wx.manage.menu.controller;

import com.alibaba.fastjson.JSONObject;
import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import org.yyx.wx.commons.vo.pubnum.base.menu.ButtonBean;
import org.yyx.wx.commons.vo.pubnum.response.menu.CustomerMenuResponse;
import org.yyx.wx.manage.ManageApplicationTests;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.yyx.wx.commons.bussinessenum.CustomerMenuEnum.click;
import static org.yyx.wx.commons.bussinessenum.CustomerMenuEnum.location_select;
import static org.yyx.wx.commons.bussinessenum.CustomerMenuEnum.pic_photo_or_album;
import static org.yyx.wx.commons.bussinessenum.CustomerMenuEnum.scancode_push;
import static org.yyx.wx.commons.bussinessenum.CustomerMenuEnum.view;

@FixMethodOrder(value = MethodSorters.NAME_ASCENDING)
public class MenuControllerTest extends ManageApplicationTests {

    /**
     * MenuControllerTest 日志输出
     */
    private static final Logger LOGGER = LoggerFactory.getLogger(MenuControllerTest.class);
    /**
     * 请求URL模板
     */
    private static final String URL_TEMPLATE = "/api/menu/";
    /**
     * MockMVC
     */
    private MockMvc mockMvc;
    /**
     * Web容器上下文
     */
    @Autowired
    private WebApplicationContext context;

    /**
     * 创建自定义菜单查询测试
     */
    @Test
    public void createMenu() throws Exception {
        CustomerMenuResponse customerMenuResponse = new CustomerMenuResponse();

        ButtonBean buttonBean = new ButtonBean();
        buttonBean.setName("叶云轩1");
        buttonBean.setType(click.toString());
        buttonBean.setKey("Key1");

        ButtonBean buttonBean1 = new ButtonBean();
        buttonBean1.setName("百度");
        buttonBean1.setUrl("http://www.baidu.com");
        buttonBean1.setType(view.toString());

        ButtonBean buttonBean2 = new ButtonBean();
        buttonBean2.setName("菜单");

        ButtonBean[] subButtonBeans = new ButtonBean[3];
        ButtonBean subButtonBean = new ButtonBean();
        subButtonBean.setName("拍照");
        subButtonBean.setType(click.toString());
        subButtonBean.setType(pic_photo_or_album.toString());
        subButtonBean.setKey("photo");
        subButtonBeans[0] = subButtonBean;

        ButtonBean subButtonBean1 = new ButtonBean();
        subButtonBean1.setName("地理");
        subButtonBean1.setType(click.toString());
        subButtonBean1.setType(location_select.toString());
        subButtonBean1.setKey("photo");
        subButtonBeans[1] = subButtonBean1;

        ButtonBean subButtonBean2 = new ButtonBean();
        subButtonBean2.setName("扫码");
        subButtonBean2.setType(click.toString());
        subButtonBean2.setType(scancode_push.toString());
        subButtonBean2.setKey("photo");
        subButtonBeans[2] = subButtonBean2;

        buttonBean2.setSub_button(subButtonBeans);

        ButtonBean[] buttonBeans = new ButtonBean[]{buttonBean, buttonBean1, buttonBean2};
        customerMenuResponse.setButton(buttonBeans);
        String content = JSONObject.toJSONString(customerMenuResponse);
        LOGGER.info("{} -> [菜单Json] {}", this.getClass().getName(), content);
        String contentAsString = mockMvc.perform(
                post(URL_TEMPLATE + "add")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(content))
                .andExpect(status().isOk())
                .andReturn().getResponse().getContentAsString();
        LOGGER.info("{} -> [响应结果] {}", this.getClass().getName(), contentAsString);
    }

    /**
     * 查询菜单测试
     *
     * @throws Exception 异常
     */
    @Test
    public void queryMenu() throws Exception {
        String contentAsString = mockMvc.perform(get(URL_TEMPLATE + "list"))
                .andExpect(status().isOk())
                .andReturn().getResponse().getContentAsString();
        LOGGER.info("{} -> [响应结果] {}", this.getClass().getName(), contentAsString);
    }


    @Before
    public void setUp() {
        mockMvc = MockMvcBuilders.webAppContextSetup(context)
                .build();
    }
}