package ${basepackage}.dao;

import org.apache.ibatis.executor.statement.StatementHandler;
import org.apache.ibatis.mapping.BoundSql;
import org.apache.ibatis.plugin.*;
import org.apache.ibatis.session.RowBounds;

import java.lang.reflect.Field;
import java.sql.Connection;
import java.util.Properties;

/**
 * mybatis 分页拦截器，使用物理分页
 * 或者使用PageHelper插件，原理一样
 */
@Intercepts(@Signature(type = StatementHandler.class, method = "prepare", args = {Connection.class, Integer.class}))
public class PaginationInterceptor implements Interceptor {
    // select语句正则表达式匹配： ^代表开头位置 \s代表空格 $代表结尾位置 *代表任意多个 .代表任意字符
    private static final String REGEX = "^\\s*[Ss][Ee][Ll][Ee][Cc][Tt].*$";

    @Override
    public Object intercept(Invocation inv) throws Throwable {
        // 此时的target为RoutingStatementHandler类的实例
        StatementHandler target = (StatementHandler) inv.getTarget();
        // BoundSql类中有一个sql属性，即为待执行的sql语句
        BoundSql boundSql = target.getBoundSql();
        String sql = boundSql.getSql();
        // 如果sql语句是select语句的话，则进行查看是否需要分页处理
        if (sql.matches(REGEX)) {
            // delegate是RoutingStatementHandler通过mapper映射文件中设置的statementType来指定具体的StatementHandler
            Object delegate = readField(target, "delegate");
            if (delegate != null) {
                // rowBounds中绑定了我们自定义的分页信息，包括起始位置offset和取出记录条数limit
                RowBounds rowBounds = (RowBounds) readField(delegate, "rowBounds");
                // 如果rowBound不为空，且rowBounds的起始位置不为0，则代表我们需要进行分页处理
                if (rowBounds != null && rowBounds.getOffset() != RowBounds.NO_ROW_OFFSET) {
                    // buildSql(...)完成对sql语句的装配及rowBounds的重置操作
                    writeField(boundSql, "sql", buildSql(sql, rowBounds));
                }
            }
        }
        return inv.proceed();
    }

    /**
     * 装配SQL语句，并重置RowBounds中的offset和limit
     */
    private String buildSql(String oldSql, RowBounds rowBounds) throws IllegalAccessException {
        String sql = oldSql + " limit " + rowBounds.getOffset() + "," + rowBounds.getLimit();
        // 这两步是必须的，因为在前面置换好sql语句以后，实际的结果集就是我们想要的所以offset和limit必须重置为初始值
        writeField(rowBounds, "offset", RowBounds.NO_ROW_OFFSET);
        writeField(rowBounds, "limit", RowBounds.NO_ROW_LIMIT);
        return sql;
    }


    /**
     * 利用反射获取指定对象的指定属性
     */
    private Object readField(Object target, String fieldName) throws IllegalAccessException {
        Field field = null;
        // 遍历target的属性及其父类的属性
        for (Class<?> c = target.getClass(); c != null; c = c.getSuperclass()) {
            try {
                field = c.getDeclaredField(fieldName);
            } catch (NoSuchFieldException ex) {
                // 没找到该属性，则继承查找父类的属性，所以不处理该异常
            }
        }
        if (field != null) {
            field.setAccessible(true);
            return field.get(target);
        }
        return null;
    }

    /**
     * 利用反射为指定对象的指定属性写入值
     */
    private void writeField(Object target, String fieldName, Object value) throws IllegalAccessException {
        Field field = null;
        // 遍历target的属性及其父类的属性
        for (Class<?> c = target.getClass(); c != null; c = c.getSuperclass()) {
            try {
                field = c.getDeclaredField(fieldName);
            } catch (NoSuchFieldException ex) {
                // 没找到该属性，则继承查找父类的属性，所以不处理该异常
            }
        }
        if (field != null) {
            field.setAccessible(true);
            field.set(target, value);
        }
    }

    @Override
    public Object plugin(Object target) {
        // 通过Plugin的wrap(...)方法来实现代理类的生成操作
        return Plugin.wrap(target, this);
    }

    @Override
    public void setProperties(Properties props) {
        //
    }

}