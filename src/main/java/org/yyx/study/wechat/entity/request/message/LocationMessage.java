package org.yyx.study.wechat.entity.request.message;

/**
 * 地理位置消息
 * Created by 叶云轩 on 2017/6/22-13:42
 * Concat ycountjavaxuan@outlook.com
 */
public class LocationMessage extends BaseMessage {
    /**
     * 地理位置维度
     */
    private String Location_X;
    /**
     * 地理位置经度
     */
    private String Location_Y;
    /**
     * 地图缩放大小
     */
    private String Scale;
    /**
     * 地理位置信息
     */
    private String Label;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        LocationMessage that = (LocationMessage) o;

        if (Location_X != null ? !Location_X.equals(that.Location_X) : that.Location_X != null) return false;
        if (Location_Y != null ? !Location_Y.equals(that.Location_Y) : that.Location_Y != null) return false;
        if (Scale != null ? !Scale.equals(that.Scale) : that.Scale != null) return false;
        return Label != null ? Label.equals(that.Label) : that.Label == null;
    }

    public String getLabel() {
        return Label;
    }

    public void setLabel(String label) {
        Label = label;
    }

    public String getLocation_X() {

        return Location_X;
    }

    public void setLocation_X(String location_X) {
        Location_X = location_X;
    }

    public String getLocation_Y() {

        return Location_Y;
    }

    public void setLocation_Y(String location_Y) {
        Location_Y = location_Y;
    }

    public String getScale() {
        return Scale;
    }

    public void setScale(String scale) {
        Scale = scale;
    }

    @Override
    public int hashCode() {
        int result = Location_X != null ? Location_X.hashCode() : 0;
        result = 31 * result + (Location_Y != null ? Location_Y.hashCode() : 0);
        result = 31 * result + (Scale != null ? Scale.hashCode() : 0);
        result = 31 * result + (Label != null ? Label.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "LocationMessage{" +
                "Location_X='" + Location_X + '\'' +
                ", Location_Y='" + Location_Y + '\'' +
                ", Scale='" + Scale + '\'' +
                ", Label='" + Label + '\'' +
                '}';
    }
}
