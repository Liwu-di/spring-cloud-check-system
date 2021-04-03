package fun.liwudi.graduatedesignuserinfomanage.domain;

import com.alibaba.excel.annotation.ExcelIgnore;
import com.alibaba.excel.annotation.ExcelProperty;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;

import java.math.BigDecimal;

/**
 * @author 李武第
 */
public class CheckInfoExport {
    @ExcelProperty("用户编码")
    String userCode;
    @ExcelProperty("打卡结果")
    String checkSuccess;
    @ExcelProperty("打卡IP")
    String checkIp;
    @ExcelProperty("打卡经度")
    BigDecimal checkAreaX;
    @ExcelProperty("打卡纬度")
    BigDecimal checkAreaY;
    @ExcelProperty("公司编码")
    String companyCode;
    @ExcelProperty("打卡日期")
    String checkDate;
    @ExcelIgnore
    int deleteFlag;

    public int getDeleteFlag() {
        return deleteFlag;
    }

    public void setDeleteFlag(int deleteFlag) {
        this.deleteFlag = deleteFlag;
    }

    public String getUserCode() {
        return userCode;
    }

    public void setUserCode(String userCode) {
        this.userCode = userCode;
    }

    public String getCheckSuccess() {
        return checkSuccess;
    }

    public void setCheckSuccess(String checkSuccess) {
        this.checkSuccess = checkSuccess;
    }

    public BigDecimal getCheckAreaX() {
        return checkAreaX;
    }

    public void setCheckAreaX(BigDecimal checkAreaX) {
        this.checkAreaX = checkAreaX;
    }

    public String getCheckIp() {
        return checkIp;
    }

    public void setCheckIp(String checkIp) {
        this.checkIp = checkIp;
    }

    public BigDecimal getCheckAreaY() {
        return checkAreaY;
    }

    public void setCheckAreaY(BigDecimal checkAreaY) {
        this.checkAreaY = checkAreaY;
    }

    public String getCompanyCode() {
        return companyCode;
    }

    public void setCompanyCode(String companyCode) {
        this.companyCode = companyCode;
    }

    public String getCheckDate() {
        return checkDate;
    }

    public void setCheckDate(String checkDate) {
        this.checkDate = checkDate;
    }

    @Override
    public String toString() {
        return "CheckInfo{" +
                "userCode='" + userCode + '\'' +
                ", checkSuccess=" + checkSuccess +
                ", checkAreaX=" + checkAreaX +
                ", checkIp='" + checkIp + '\'' +
                ", checkAreaY=" + checkAreaY +
                ", companyCode='" + companyCode + '\'' +
                ", checkDate='" + checkDate + '\'' +
                ", deleteFlag=" + deleteFlag +
                '}';
    }
}
