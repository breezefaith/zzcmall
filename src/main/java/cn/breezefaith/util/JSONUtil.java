package cn.breezefaith.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;

public class JSONUtil {
    public static String parseJSONString(Object obj){
        ObjectMapper mapper=new ObjectMapper();
        try {
            return ""+mapper.writeValueAsString(obj)+"";
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            return "{}";
        }
    }
    public static Object parseObject(String string,Class t) throws IOException {
        ObjectMapper mapper=new ObjectMapper();
        return mapper.readValue(string,t);
    }
//    public static void main(String[] args) {
//
//    }
}
