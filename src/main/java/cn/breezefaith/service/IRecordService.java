package cn.breezefaith.service;


import cn.breezefaith.entity.Record;

import java.util.List;

public interface IRecordService {

    Record findById(String rid);

    List<Record> findAll();

    List<Record> findByUid(String uid);

    boolean pay(String token, Integer aid, Integer counts, Double cost);

    List<Record> findByUser(String token);

    boolean updateRecord(String rid, String courier);
}
