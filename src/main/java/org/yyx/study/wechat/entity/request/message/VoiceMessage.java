package org.yyx.study.wechat.entity.request.message;

/**
 * 语音消息
 * Created by 叶云轩 on 2017/6/22-13:39
 * Concat ycountjavaxuan@outlook.com
 */
public class VoiceMessage extends BaseMessage {

    /**
     * 语音消息媒体id，可以调用多媒体文件下载接口拉取数据。
     */
    private String MediaID;
    /**
     * 语音格式，如amr，speex等
     */
    private String Format;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;

        VoiceMessage that = (VoiceMessage) o;

        if (MediaID != null ? !MediaID.equals(that.MediaID) : that.MediaID != null) return false;
        return Format != null ? Format.equals(that.Format) : that.Format == null;
    }

    public String getFormat() {
        return Format;
    }

    public void setFormat(String format) {
        Format = format;
    }

    public String getMediaID() {

        return MediaID;
    }

    public void setMediaID(String mediaID) {
        MediaID = mediaID;
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + (MediaID != null ? MediaID.hashCode() : 0);
        result = 31 * result + (Format != null ? Format.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "VoiceMessage{" +
                "MediaID='" + MediaID + '\'' +
                ", Format='" + Format + '\'' +
                '}';
    }
}
