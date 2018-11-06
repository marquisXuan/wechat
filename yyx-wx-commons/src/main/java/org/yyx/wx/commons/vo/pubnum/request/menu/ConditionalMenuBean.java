package org.yyx.wx.commons.vo.pubnum.request.menu;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 个性化菜单实体
 * <p>
 *
 * @author 叶云轩 at tdg_yyx@foxmail.com
 * @date 2018/10/14-16:54
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class ConditionalMenuBean extends MenuBean {

    private MatchruleBean matchrule;

}
