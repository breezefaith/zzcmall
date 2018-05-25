package cn.breezefaith.service.impl;

import cn.breezefaith.entity.Record;
import cn.breezefaith.entity.User;
import cn.breezefaith.service.AbstractService;
import cn.breezefaith.service.IRecordService;
import cn.breezefaith.util.JSONUtil;
import org.springframework.stereotype.Service;
import redis.clients.jedis.Jedis;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service("recordService")
public class RecordServiceImpl extends AbstractService implements IRecordService {

    @Override
    public Record findById(String rid){
        return recordDao.findById(Integer.valueOf(rid));
    }

    @Override
    public List<Record> findAll(){
        return recordDao.findAll();
    }

    @Override
    public List<Record> findByUid(String uid){
        return recordDao.findByUid(Integer.valueOf(uid));
    }

    @Override
    public boolean pay(String token, Integer aid, Integer counts, Double cost) {
        Jedis jedis=jedisPool.getResource();
        try {
            User user=(User)JSONUtil.parseObject(jedis.get(token),User.class);
            //结算后清空购物车,并将购物车中数据存入数据库
            String items=jedis.get("cart"+token);
            jedis.del("cart"+token);
            return recordDao.addOne(user.getUid(),aid,counts,cost,items);
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        } finally {
            if (jedis!=null){
                jedisPool.returnResource(jedis);
            }
        }
    }

    @Override
    public List<Record> findByUser(String token) {
        Jedis jedis=jedisPool.getResource();
        try {
            User user=(User)JSONUtil.parseObject(jedis.get(token),User.class);
            return recordDao.findByUid(user.getUid());
        } catch (IOException e) {
            e.printStackTrace();
            return new ArrayList<Record>();
        } finally {
            if(jedis!=null){
                jedisPool.returnResource(jedis);
            }
        }

    }

    @Override
    public boolean updateRecord(String rid, String courier) {
        Record record=new Record();
        record.setRid(Integer.valueOf(rid));
        record.setCourierNumber(courier);
        if(recordDao.updateRecord(record)==false){
            return false;
        }else {
            return true;
        }
    }
}
