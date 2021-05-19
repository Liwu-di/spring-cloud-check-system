package fun.liwudi.graduatedesignuserinfomanage.domain;

import com.alibaba.excel.annotation.ExcelIgnore;
import com.alibaba.excel.annotation.ExcelProperty;

/**
 * @author 李武第
 */
public class Vocation {

    @ExcelProperty(value = "序号")
    private String id;
    @ExcelProperty(value = "用户编码")
    private String userCode;
    @ExcelProperty(value = "开始时间")
    private String startTime;
    @ExcelProperty(value = "结束时间")
    private String endTime;
    @ExcelProperty(value = "请假/销假")
    private String isAskForLeave;
    @ExcelIgnore
    private String email;
    @ExcelIgnore
    private String specialKey;
    @ExcelProperty(value = "审批结果")
    private String isApprove;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

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

    public String getIsApprove() {
        return isApprove;
    }

    public void setIsApprove(String isApprove) {
        this.isApprove = isApprove;
    }

    public String getSpecialKey() {
        return specialKey;
    }

    public void setSpecialKey(String specialKey) {
        this.specialKey = specialKey;
    }

    @Override
    public String toString() {
        return "Vocation{" +
                "userCode='" + userCode + '\'' +
                ", startTime='" + startTime + '\'' +
                ", endTime='" + endTime + '\'' +
                ", isAskForLeave='" + isAskForLeave + '\'' +
                ", email='" + email + '\'' +
                ", specialKey='" + specialKey + '\'' +
                ", isApprove='" + isApprove + '\'' +
                '}';
    }
}
