package fun.liwudi.zuulservice.domain;

import org.apache.commons.lang.StringUtils;

/**
 * @author 李武第
 */

public class UserTokenInfo {

    private String userName;

    private String passWord;

    private String time;

    public Boolean isEqual(UserTokenInfo userTokenInfo){
        if(StringUtils.equals(this.time,userTokenInfo.getTime())
        && StringUtils.equals(this.userName,userTokenInfo.getUserName())
        && StringUtils.equals(this.passWord,userTokenInfo.getPassWord())){
            return true;
        }
        return false;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassWord() {
        return passWord;
    }

    public void setPassWord(String passWord) {
        this.passWord = passWord;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    @Override
    public String toString() {
        return "UserTokenInfo{" +
                "userName='" + userName + '\'' +
                ", passWord='" + passWord + '\'' +
                ", time='" + time + '\'' +
                '}';
    }
}
