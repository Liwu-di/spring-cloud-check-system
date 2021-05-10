package fun.liwudi.graduatedesignverifyservice.helper;

import fun.liwudi.graduatedesignverifyservice.domain.UserConf;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.support.atomic.RedisAtomicLong;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.concurrent.TimeUnit;

/**
 * @author 李武第
 */
@Component
public class RedisKeyHelper {

    @Autowired
    private RedisTemplate redisTemplate;

    private long no;

    private static String MEAL_NO_PREFIX = "LIWUDI:ZANGANYI:BICODE:";

    @Autowired
    private RedisAtomicLong counter;

    /**
     * @desc: 生成取餐号
     */
    public String generateMealNo(UserConf userConf) {
        LocalDate now = LocalDate.now();
        LocalTime localTime = LocalTime.now();
        String yyyyMMdd = now.format(DateTimeFormatter.ofPattern("yyyyMMdd"));
        String hhMMss = localTime.format(DateTimeFormatter.ofPattern("hhmmss"));
        String key = MEAL_NO_PREFIX + userConf.getKey() + userConf.getUserCode();
        //24小时后失效
        counter.expire(24, TimeUnit.HOURS);
        no = counter.incrementAndGet();
        String noStr = String.format("%03d", no);
        redisTemplate.opsForValue().set(key,yyyyMMdd+hhMMss+noStr);
        return yyyyMMdd +hhMMss+ noStr;
    }
}
