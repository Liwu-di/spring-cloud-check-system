package fun.liwudi.graduatedesignuserinfomanage.domain;

import java.math.BigDecimal;

/**
 * @author 李武第
 */
public class CompanyConf {

    String companyName;
    String companyCode;
    String areaIp;
    BigDecimal areaGpsX;
    BigDecimal areaGpsY;
    BigDecimal checkArea;

    public BigDecimal getCheckArea() {
        return checkArea;
    }

    public void setCheckArea(BigDecimal checkArea) {
        this.checkArea = checkArea;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getCompanyCode() {
        return companyCode;
    }

    public void setCompanyCode(String companyCode) {
        this.companyCode = companyCode;
    }

    public String getAreaIp() {
        return areaIp;
    }

    public void setAreaIp(String areaIp) {
        this.areaIp = areaIp;
    }

    public BigDecimal getAreaGpsX() {
        return areaGpsX;
    }

    public void setAreaGpsX(BigDecimal areaGpsX) {
        this.areaGpsX = areaGpsX;
    }

    public BigDecimal getAreaGpsY() {
        return areaGpsY;
    }

    public void setAreaGpsY(BigDecimal areaGpsY) {
        this.areaGpsY = areaGpsY;
    }

    @Override
    public String toString() {
        return "CompanyConf{" +
                "companyName='" + companyName + '\'' +
                ", companyCode='" + companyCode + '\'' +
                ", areaIp='" + areaIp + '\'' +
                ", areaGpsX=" + areaGpsX +
                ", areaGpsY=" + areaGpsY +
                ", checkArea=" + checkArea +
                '}';
    }
}
