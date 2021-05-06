package fun.liwudi.graduatedesignverifyservice.helper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.support.atomic.RedisAtomicLong;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.concurrent.TimeUnit;

/**
 * @author 李武第
 */
@Component
public class RedisKeyHelper {

    @Autowired
    private RedisTemplate<String, Object> redisTemplate;

    private static String MEAL_NO_PREFIX = "LIWUDI:ZANGANYI:BICODE:";

    /**
     * @desc: 生成取餐号
     */
    public String generateMealNo() {
        LocalDate now = LocalDate.now();
        String yyyyMMdd = now.format(DateTimeFormatter.ofPattern("yyyyMMdd"));
        String key = MEAL_NO_PREFIX + yyyyMMdd;
        RedisAtomicLong counter = new RedisAtomicLong(key, redisTemplate.getConnectionFactory());
        //24小时后失效
        counter.expire(24, TimeUnit.HOURS);
        long no = counter.incrementAndGet();
        String noStr = String.format("%03d", no);
        return yyyyMMdd + noStr;
    }
}
