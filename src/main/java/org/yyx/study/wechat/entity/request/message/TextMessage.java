package org.yyx.study.wechat.entity.request.message;

/**
 * 普通文本消息类
 * Created by 叶云轩 on 2017/6/22-13:24
 * Concat ycountjavaxuan@outlook.com
 */
public class TextMessage extends BaseMessage {

    /**
     * 文本消息内容
     */
    private String Content;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        TextMessage that = (TextMessage) o;

        return Content != null ? Content.equals(that.Content) : that.Content == null;
    }

    public String getContent() {
        return Content;
    }

    public void setContent(String content) {
        Content = content;
    }

    @Override
    public int hashCode() {
        return Content != null ? Content.hashCode() : 0;
    }

    @Override
    public String toString() {
        return "TextMessage{" +
                "Content='" + Content + '\'' +
                '}';
    }
}
