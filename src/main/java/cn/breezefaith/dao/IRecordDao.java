package cn.breezefaith.dao;

import cn.breezefaith.entity.Record;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("recordDao")
public interface IRecordDao {
    Record findById(@Param("rid") Integer rid);

    List<Record> findAll();

    List<Record> findByUid(@Param("uid")Integer uid);

    boolean addOne(@Param("uid") Integer uid, @Param("aid") Integer aid, @Param("counts") Integer counts, @Param("cost") Double cost);
}
