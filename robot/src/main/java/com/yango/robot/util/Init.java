package com.yango.robot.util;

import com.yango.robot.mapper.AskMapper;
import com.yango.robot.mapper.WordMapper;
import com.yango.robot.pojo.Ask;
import com.yango.robot.util.Nlpir.NlpirMethod;
import com.yango.robot.util.Redis.RedisUtil;
import com.yango.robot.util.VSM.Vsm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import java.util.*;

/**
 * 初始化，计算idf，w存入redis
 */
@Component
public class Init  implements ApplicationRunner {
    @Autowired
    Vsm vsm;
    @Autowired
    AskMapper askMapper;
    @Autowired
    RedisUtil redisUtil;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        redisUtil.set("max",askMapper.maxId());
        redisUtil.set("min",askMapper.minId());
        List<Map<String,Double>> list = new LinkedList<>();
        //id,词,频率
        Map<Integer,Map<String,Double>> wRedis= new HashMap<>();
        List<Ask> listAsk = askMapper.listask();

        for(Ask a : listAsk){
            Map<String,Double> tfMap = vsm.tf(a.getQuestion());
            list.add(tfMap);
            wRedis.put(a.getId(),tfMap);
        }
        Map<String,Double> idfMap = vsm.idf(list);
        for(String a : idfMap.keySet()){
            redisUtil.set(a,idfMap.get(a));
        }
        for(Integer a : wRedis.keySet()){
            Map<String,Double> tf = wRedis.get(a);
            Map<String,Double> wSetRedis = new HashMap<>();
            for(String b : tf.keySet()){
                double w;
                w = vsm.w(tf.get(b),(double)redisUtil.get(b));
                wSetRedis.put(b,w);
            }
            redisUtil.set(String.valueOf(a),wSetRedis);
        }
    }
}
