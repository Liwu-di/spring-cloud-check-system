package fun.liwudi.graduatedesignverifyservice.domain;

/**
 * @author 李武第
 */
public class Position {

    private String Q;
    private String R;
    private String lng;
    private String lat;

    public String getQ() {
        return Q;
    }

    public void setQ(String q) {
        Q = q;
    }

    public String getR() {
        return R;
    }

    public void setR(String r) {
        R = r;
    }

    public String getLng() {
        return lng;
    }

    public void setLng(String lng) {
        this.lng = lng;
    }

    public String getLat() {
        return lat;
    }

    public void setLat(String lat) {
        this.lat = lat;
    }

    @Override
    public String toString() {
        return "Position{" +
                "Q='" + Q + '\'' +
                ", R='" + R + '\'' +
                ", lng='" + lng + '\'' +
                ", lat='" + lat + '\'' +
                '}';
    }
}
