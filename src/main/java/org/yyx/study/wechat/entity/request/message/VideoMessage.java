package org.yyx.study.wechat.entity.request.message;

/**
 * 视频消息
 * Created by 叶云轩 on 2017/6/22-13:41
 * Concat ycountjavaxuan@outlook.com
 */
public class VideoMessage extends BaseMessage {

    /**
     * 视频消息媒体id，可以调用多媒体文件下载接口拉取数据。
     */
    private String MediaId;
    /**
     * 视频消息缩略图的媒体id，可以调用多媒体文件下载接口拉取数据。
     */
    private String ThumbMediaId;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;

        VideoMessage that = (VideoMessage) o;

        if (MediaId != null ? !MediaId.equals(that.MediaId) : that.MediaId != null) return false;
        return ThumbMediaId != null ? ThumbMediaId.equals(that.ThumbMediaId) : that.ThumbMediaId == null;
    }

    public String getMediaId() {

        return MediaId;
    }

    public void setMediaId(String mediaId) {
        MediaId = mediaId;
    }

    public String getThumbMediaId() {
        return ThumbMediaId;
    }

    public void setThumbMediaId(String thumbMediaId) {
        ThumbMediaId = thumbMediaId;
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + (MediaId != null ? MediaId.hashCode() : 0);
        result = 31 * result + (ThumbMediaId != null ? ThumbMediaId.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "VideoMessage{" +
                "MediaId='" + MediaId + '\'' +
                ", ThumbMediaId='" + ThumbMediaId + '\'' +
                '}';
    }
}
