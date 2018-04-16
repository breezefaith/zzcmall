package cn.breezefaith.initial;

import cn.breezefaith.dao.IItemDao;
import cn.breezefaith.entity.Item;
import cn.breezefaith.util.ImageUtil;
import cn.breezefaith.util.JSONUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import java.util.List;

@Repository("initialTool")
public class InitialTool {
    @Autowired
    private JedisPool jedisPool;

    @Resource(name="itemDao")
    private IItemDao itemDao;

    //spring加载过程中执行，将图片加载到内存中
    @PostConstruct
    public void setRedisCache(){
        Jedis jedis=jedisPool.getResource();
        List<Item> items=itemDao.findAll();

        jedis.set("items", JSONUtil.parseJSONString(items));
        for (Item item:items){
            jedis.set("image"+item.getIid(), ImageUtil.encodeImgageToBase64(item.getItemImage()));
        }
    }
}
