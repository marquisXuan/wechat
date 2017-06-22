package org.yyx.study.wechat.entity.request.message;

/**
 * 链接消息
 * Created by 叶云轩 on 2017/6/22-14:01
 * Concat ycountjavaxuan@outlook.com
 */
public class LinkMessage extends BaseMessage {

    /**
     * 消息标题
     */
    private String Title;

    /**
     * 消息描述
     */
    private String Description;

    /**
     * 消息链接
     */
    private String Url;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;

        LinkMessage that = (LinkMessage) o;

        if (Title != null ? !Title.equals(that.Title) : that.Title != null) return false;
        if (Description != null ? !Description.equals(that.Description) : that.Description != null) return false;
        return Url != null ? Url.equals(that.Url) : that.Url == null;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String description) {
        Description = description;
    }

    public String getTitle() {

        return Title;
    }

    public void setTitle(String title) {
        Title = title;
    }

    public String getUrl() {
        return Url;
    }

    public void setUrl(String url) {
        Url = url;
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + (Title != null ? Title.hashCode() : 0);
        result = 31 * result + (Description != null ? Description.hashCode() : 0);
        result = 31 * result + (Url != null ? Url.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "LinkMessage{" +
                "Title='" + Title + '\'' +
                ", Description='" + Description + '\'' +
                ", Url='" + Url + '\'' +
                '}';
    }
}
