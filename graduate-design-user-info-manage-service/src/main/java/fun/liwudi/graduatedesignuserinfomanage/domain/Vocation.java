package fun.liwudi.graduatedesignuserinfomanage.domain;

/**
 * @author 李武第
 */
public class Vocation {

    private String userCode;
    private String startTime;
    private String endTime;
    private String isAskForLeave;
    private String email;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUserCode() {
        return userCode;
    }

    public void setUserCode(String userCode) {
        this.userCode = userCode;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public String getIsAskForLeave() {
        return isAskForLeave;
    }

    public void setIsAskForLeave(String isAskForLeave) {
        this.isAskForLeave = isAskForLeave;
    }

    @Override
    public String toString() {
        return "Vocation{" +
                "userCode='" + userCode + '\'' +
                ", startTime='" + startTime + '\'' +
                ", endTime='" + endTime + '\'' +
                ", isAskForLeave='" + isAskForLeave + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}
