package fun.liwudi.graduatedesignuserinfomanage.domain;

import com.alibaba.excel.annotation.ExcelProperty;

/**
 * @author 李武第
 */
public class UserInfoImport {

    public String getCompanyCode() {
        return companyCode;
    }

    public void setCompanyCode(String companyCode) {
        this.companyCode = companyCode;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserCode() {
        return userCode;
    }

    public void setUserCode(String userCode) {
        this.userCode = userCode;
    }

    public String getPassWord() {
        return passWord;
    }

    public void setPassWord(String passWord) {
        this.passWord = passWord;
    }

    public String getIdCard() {
        return idCard;
    }

    public void setIdCard(String idCard) {
        this.idCard = idCard;
    }

    @ExcelProperty(index = 0)
    String companyCode;
    @ExcelProperty(index = 1)
    String userName;
    @ExcelProperty(index = 2)
    String userCode;
    @ExcelProperty(index = 3)
    String passWord;
    @ExcelProperty(index = 4)
    String idCard;

    @Override
    public String toString() {
        return "UserInfoImport{" +
                "companyCode='" + companyCode + '\'' +
                ", userName='" + userName + '\'' +
                ", userCode='" + userCode + '\'' +
                ", passWord='" + passWord + '\'' +
                ", idCard='" + idCard + '\'' +
                '}';
    }
}
