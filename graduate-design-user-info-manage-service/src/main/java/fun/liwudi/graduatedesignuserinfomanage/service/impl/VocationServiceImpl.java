package fun.liwudi.graduatedesignuserinfomanage.service.impl;

import fun.liwudi.graduatedesignuserinfomanage.constants.Constants;
import fun.liwudi.graduatedesignuserinfomanage.domain.JsonResponse;
import fun.liwudi.graduatedesignuserinfomanage.domain.UserInfo;
import fun.liwudi.graduatedesignuserinfomanage.domain.Vocation;
import fun.liwudi.graduatedesignuserinfomanage.helper.JsonResponseHelper;
import fun.liwudi.graduatedesignuserinfomanage.mapper.VocationMapper;
import fun.liwudi.graduatedesignuserinfomanage.service.UserManageService;
import fun.liwudi.graduatedesignuserinfomanage.service.VocationService;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.stereotype.Service;
import java.util.*;
import javax.mail.*;
import javax.mail.internet.*;
import javax.activation.*;
import java.util.Properties;

/**
 * @author 李武第
 */
@Service
public class VocationServiceImpl implements VocationService {

    @Autowired
    private JsonResponseHelper jsonResponseHelper;

    @Autowired
    private UserManageService userManageService;

    @Autowired
    private VocationMapper vocationMapper;

    @Autowired
    private JavaMailSender mailSender;

    private Logger logger = LoggerFactory.getLogger(VocationServiceImpl.class);

    @Override
    public JsonResponse sendMail(Vocation vocation) {

        try{

            UserInfo userInfo = new UserInfo();
            userInfo.setUserCode(vocation.getUserCode());
            userInfo = userManageService.selectUserInfo(userInfo);
            if(StringUtils.equals("0",vocation.getIsAskForLeave())){
                SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
                simpleMailMessage.setFrom("154125960@qq.com");
                simpleMailMessage.setTo(vocation.getEmail());
                simpleMailMessage.setSubject("销假邮件");
                simpleMailMessage.setText("Dear,"
                        +"\n"
                        +"今因个人原因，以下时间段\n"
                        +vocation.getStartTime()
                        +"-"
                        +vocation.getEndTime()
                        +"的请假取消，望知悉批准。"
                        +"\n"
                        +userInfo.getUserName()
                        +"\n"
                        +"请点击以下链接同意"
                        +"http://localhost:9000/vocation/record?userCode=" + vocation.getUserCode()
                        +"&startTime=" + vocation.getStartTime()+
                        "&endTime=" + vocation.getEndTime()+
                        "&isApprove=1&isAskForLeave=0"
                        +"\n"
                        +"或点击此链接拒绝"
                        +"http://localhost:9000/vocation/record?userCode=" + vocation.getUserCode()
                        +"&startTime=" + vocation.getStartTime()+
                        "&endTime=" + vocation.getEndTime()+
                        "&isApprove=0&isAskForLeave=0");
                mailSender.send(simpleMailMessage);
                logger.info("Sent message successfully....");
            }
            else if(StringUtils.equals("1",vocation.getIsAskForLeave())) {
                SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
                simpleMailMessage.setFrom("154125960@qq.com");
                simpleMailMessage.setTo(vocation.getEmail());
                simpleMailMessage.setSubject("请假邮件");
                simpleMailMessage.setText("Dear,"
                        +"\n"
                        +"今因个人原因，需要在以下时间段"
                        +vocation.getStartTime()
                        +"-"
                        +vocation.getEndTime()
                        +"请假，望知悉批准。"
                        +"\n"
                        +userInfo.getUserName()
                        +"\n"
                        +"请点击以下链接同意"
                        +"http://localhost:9000/vocation/record?userCode=" + vocation.getUserCode()
                        +"&startTime=" + vocation.getStartTime()+
                        "&endTime=" + vocation.getEndTime()+
                        "&isApprove=1&isAskForLeave=1"
                        +"\n"
                        +"或点击此链接拒绝"
                        +"http://localhost:9000/vocation/record?userCode=" + vocation.getUserCode()
                        +"&startTime=" + vocation.getStartTime()+
                        "&endTime=" + vocation.getEndTime()+
                        "&isApprove=0&isAskForLeave=1");
                mailSender.send(simpleMailMessage);
                logger.info("Sent message successfully....");
            }
            return jsonResponseHelper.getJsonResponse(Constants.SUCCESS,0);

        }catch (Exception mex) {
            logger.error(mex.getMessage(),mex);
            return jsonResponseHelper.getJsonResponse(Constants.FAIL,1);
        }
    }

    @Override
    public void saveRecord(Vocation vocation) {
        vocation.setIsAskForLeave(StringUtils.equals("1",vocation.getIsAskForLeave())?"请假":"销假");
        vocation.setIsApprove(StringUtils.equals("0",vocation.getIsApprove())?"不同意":"同意");
        vocationMapper.save(vocation);
    }
}
