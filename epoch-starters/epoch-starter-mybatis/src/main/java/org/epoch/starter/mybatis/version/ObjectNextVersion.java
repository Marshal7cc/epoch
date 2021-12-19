package org.epoch.starter.mybatis.version;

import java.sql.Timestamp;

import tk.mybatis.mapper.version.NextVersion;
import tk.mybatis.mapper.version.VersionException;

/**
 * @author Marshal
 * @date 2021/3/15
 */
public class ObjectNextVersion implements NextVersion<Object> {

    @Override
    public Object nextVersion(Object current) throws VersionException {
        if (current == null) {
            throw new VersionException("当前版本号为空!");
        }
        if (current instanceof String) {
            return String.valueOf(Integer.parseInt(current.toString()) + 1);
        } else if (current instanceof Integer) {
            return (Integer) current + 1;
        } else if (current instanceof Long) {
            return (Long) current + 1L;
        } else if (current instanceof Timestamp) {
            return new Timestamp(System.currentTimeMillis());
        } else {
            throw new VersionException("默认的 NextVersion 只支持 String, Integer, Long 和 java.sql.Timestamp 类型的版本号，如果有需要请自行扩展!");
        }
    }
}
