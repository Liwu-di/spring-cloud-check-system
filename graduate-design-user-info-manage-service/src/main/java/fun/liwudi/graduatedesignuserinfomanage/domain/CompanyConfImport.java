package fun.liwudi.graduatedesignuserinfomanage.domain;

import com.alibaba.excel.annotation.ExcelProperty;

import java.math.BigDecimal;

/**
 * @author 李武第
 */
public class CompanyConfImport {
    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    @Override
    public String toString() {
        return "CompanyConfImport{" +
                "companyName='" + companyName + '\'' +
                ", companyCode='" + companyCode + '\'' +
                ", areaIp='" + areaIp + '\'' +
                ", areaGpsX='" + areaGpsX + '\'' +
                ", areaGpsY='" + areaGpsY + '\'' +
                ", checkArea='" + checkArea + '\'' +
                '}';
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

    public String getAreaGpsX() {
        return areaGpsX;
    }

    public void setAreaGpsX(String areaGpsX) {
        this.areaGpsX = areaGpsX;
    }

    public String getAreaGpsY() {
        return areaGpsY;
    }

    public void setAreaGpsY(String areaGpsY) {
        this.areaGpsY = areaGpsY;
    }

    public String getCheckArea() {
        return checkArea;
    }

    public void setCheckArea(String checkArea) {
        this.checkArea = checkArea;
    }

    @ExcelProperty(index = 0,value = "公司编码")
    String companyName;
    @ExcelProperty(index = 1,value = "公司名称")
    String companyCode;
    @ExcelProperty(index = 2,value = "打卡ip地址")
    String areaIp;
    @ExcelProperty(index = 3,value = "经度")
    String areaGpsX;
    @ExcelProperty(index = 4,value = "纬度")
    String areaGpsY;
    @ExcelProperty(index = 5,value = "打卡半径")
    String checkArea;

}
