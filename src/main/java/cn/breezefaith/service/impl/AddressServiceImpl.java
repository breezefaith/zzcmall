package cn.breezefaith.service.impl;

import cn.breezefaith.entity.User;
import cn.breezefaith.service.AbstractService;
import cn.breezefaith.service.IAddressService;
import cn.breezefaith.util.JSONUtil;
import org.springframework.stereotype.Service;
import redis.clients.jedis.Jedis;

import java.io.IOException;

@Service("addressService")
public class AddressServiceImpl extends AbstractService implements IAddressService {

    @Override
    public boolean deleteAddress(String token, Integer aid) throws IOException {
        if(aid!=null&&aid>=0){
            Jedis jedis=jedisPool.getResource();
            User user=(User) JSONUtil.parseObject(jedis.get(token),User.class);
            if(addressDao.deleteByAid(aid)==1){
                user.setAddresses(addressDao.findByUid(user.getUid()));
                //redis层做修改
                jedis.set(token,JSONUtil.parseJSONString(user));
                if(jedis!=null){
                    jedisPool.returnResource(jedis);
                }
                return true;
            }
            if(jedis!=null){
                jedisPool.returnResource(jedis);
            }
        }
        return false;
    }

    @Override
    public boolean addAddress(String token, String name, String phone, String postCode, String address) throws IOException {
        if(name!=null&&phone!=null&&postCode!=null&&address!=null){
            Jedis jedis=jedisPool.getResource();
            User user=(User)JSONUtil.parseObject(jedis.get(token),User.class);
            if(addressDao.addAddress(user.getUid(),name,phone,postCode,address)==true){
                user.setAddresses(addressDao.findByUid(user.getUid()));
                //redis层做修改
                jedis.set(token,JSONUtil.parseJSONString(user));
                if(jedis!=null){
                    jedisPool.returnResource(jedis);
                }
                return true;
            }
            if(jedis!=null){
                jedisPool.returnResource(jedis);
            }
        }
        return false;
    }

    @Override
    public boolean updateAddress(String token, Integer aid,String name, String phone, String postCode, String address) throws IOException {
        if(aid!=null&&name!=null&&phone!=null&&postCode!=null&&address!=null){
            Jedis jedis=jedisPool.getResource();
            User user=(User)JSONUtil.parseObject(jedis.get(token),User.class);
            if(addressDao.updateAddress(aid,name,phone,postCode,address)==true){
                user.setAddresses(addressDao.findByUid(user.getUid()));
                //redis层做修改
                jedis.set(token,JSONUtil.parseJSONString(user));
                if(jedis!=null){
                    jedisPool.returnResource(jedis);
                }
                return true;
            }
            if(jedis!=null){
                jedisPool.returnResource(jedis);
            }
        }
        return false;
    }
}
