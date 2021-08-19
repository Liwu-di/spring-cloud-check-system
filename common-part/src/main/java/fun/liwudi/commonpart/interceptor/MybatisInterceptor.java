package fun.liwudi.commonpart.interceptor;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.apache.ibatis.executor.Executor;
import org.apache.ibatis.executor.statement.StatementHandler;
import org.apache.ibatis.mapping.BoundSql;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.plugin.*;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.*;
import java.sql.Connection;
/**
 * @author 李武第
 */
@Component
@Slf4j
@Intercepts({
        @Signature(
                type = Executor.class,
                method = "update",
                args = {MappedStatement.class,Object.class}),
        @Signature(
                method = "prepare",
                type = StatementHandler.class,
                args = {Connection.class,Integer.class})})
public class MybatisInterceptor implements Interceptor {

    @Override
    public Object intercept(Invocation invocation) throws Throwable {

        // 获取sql
        String sql = getSqlByInvocation(invocation);
        if (StringUtils.isBlank(sql)) {
            return invocation.proceed();
        }
        log.info(LocalDateTime.now().toString().concat(" 执行SQL： ").concat(sql));

        return invocation.proceed();
    }
    @Override
    public Object plugin(Object o) {
        return Plugin.wrap(o,this);
    }
    @Override
    public void setProperties(Properties properties) {
        log.warn(properties.toString());
    }

    /**
     * 获取sql语句
     * @param invocation
     * @return
     */
    private String getSqlByInvocation(Invocation invocation) {
        final Object[] args = invocation.getArgs();
        MappedStatement ms = (MappedStatement) args[0];
        Object parameterObject = args[1];
        BoundSql boundSql = ms.getBoundSql(parameterObject);
        return boundSql.getSql();
    }

}
