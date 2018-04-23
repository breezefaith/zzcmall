package cn.breezefaith.service;

import cn.breezefaith.dao.IAddressDao;
import cn.breezefaith.dao.IItemDao;
import cn.breezefaith.dao.IRecordDao;
import cn.breezefaith.dao.IUserDao;
import org.springframework.beans.factory.annotation.Autowired;
import redis.clients.jedis.JedisPool;

import javax.annotation.Resource;

public abstract class AbstractService {
    @Autowired
    protected JedisPool jedisPool;

    @Resource(name = "userDao")
    protected IUserDao userDao;

    @Resource(name = "addressDao")
    protected IAddressDao addressDao;

    @Resource(name = "itemDao")
    protected IItemDao itemDao;

    @Resource(name = "recordDao")
    protected IRecordDao recordDao;
}
