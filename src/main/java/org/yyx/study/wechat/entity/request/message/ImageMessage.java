package org.yyx.study.wechat.entity.request.message;

/**
 * 图片消息类
 * Created by 叶云轩 on 2017/6/22-13:35
 * Concat ycountjavaxuan@outlook.com
 */
public class ImageMessage extends BaseMessage {
    /**
     * 图片链接（由系统生成）
     */
    private String PicUrl;
    /**
     * 图片消息媒体id，可以调用多媒体文件下载接口拉取数据。
     */
    private String MediaId;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;

        ImageMessage that = (ImageMessage) o;

        if (PicUrl != null ? !PicUrl.equals(that.PicUrl) : that.PicUrl != null) return false;
        return MediaId != null ? MediaId.equals(that.MediaId) : that.MediaId == null;
    }

    public String getMediaId() {
        return MediaId;
    }

    public void setMediaId(String mediaId) {
        MediaId = mediaId;
    }

    public String getPicUrl() {

        return PicUrl;
    }

    public void setPicUrl(String picUrl) {
        PicUrl = picUrl;
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + (PicUrl != null ? PicUrl.hashCode() : 0);
        result = 31 * result + (MediaId != null ? MediaId.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "ImageMessage{" +
                "PicUrl='" + PicUrl + '\'' +
                ", MediaId='" + MediaId + '\'' +
                '}';
    }
}
