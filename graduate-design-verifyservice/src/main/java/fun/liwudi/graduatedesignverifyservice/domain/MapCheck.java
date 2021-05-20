package fun.liwudi.graduatedesignverifyservice.domain;

/**
 * @author 李武第
 */
public class MapCheck {

    private String userCode;

    private Position position;

    public String getUserCode() {
        return userCode;
    }

    public void setUserCode(String userCode) {
        this.userCode = userCode;
    }

    public Position getPosition() {
        return position;
    }

    public void setPosition(Position position) {
        this.position = position;
    }

    @Override
    public String toString() {
        return "MapCheck{" +
                "userCode='" + userCode + '\'' +
                ", position=" + position +
                '}';
    }
}
