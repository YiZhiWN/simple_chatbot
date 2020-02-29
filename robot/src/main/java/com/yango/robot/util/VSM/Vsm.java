package com.yango.robot.util.VSM;

import com.yango.robot.mapper.WordMapper;
import com.yango.robot.util.Nlpir.NlpirMethod;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.*;

@Component
public class Vsm {

    @Autowired
    Vsm vsm;
    @Autowired
    WordMapper wordMapper;

    //提取问题词,计算tf,tf=词个数/词总数
    public Map<String,Double> tf(String content){
        List<String> listWord = wordMapper.listword();
        for(int i=0;i<listWord.size();i++){
            NlpirMethod.NLPIR_AddUserWord(listWord.get(i)+" user");
        }
        NlpirMethod.NLPIR_SaveTheUsrDic();
        String result = NlpirMethod.NLPIR_ParagraphProcess(content, 0);
        //分词结果打印
        System.out.println("分词结果: " + result);
        //分词
        String[] fenciStr = result.split(" ");
        double fenciLength = fenciStr.length;
        //计算分词，对应个数
        Map<String,Double> tfMap=new HashMap();
        for(String o : fenciStr){
            if(tfMap.containsKey(o)){
                double count=tfMap.get(o)+1;
                tfMap.put(o,count/fenciLength);
            }else{
                tfMap.put(o,1/fenciLength);
            }
        }
        return tfMap;
    }

    //计算idf
    public Map<String,Double> idf(List<Map<String,Double>> list){
        Map<String,Double> idfMap=new HashMap();
        double idfValue = 0;
        for(int i=0;i<list.size();i++){
            Map<String,Double> tfMap = list.get(i);
            for(String a: tfMap.keySet()){
                double index = 0;
                if(idfMap.containsKey(a)){
                    continue;
                }
                for(Map<String,Double> it : list){
                    if(it.containsKey(a)){
                        index ++ ;
                    }
                }
                idfValue = index/(double)(list.size());
                idfMap.put(a,idfValue);
            }
        }
        return idfMap;
    }

    //w=tf * idf
    public double w(double tf,double idf){
        return tf * idf;
    }

    /**
     * 计算相似度,取出相似度最高的id,再从数据库中取出答案
     * @param v1 {词 : w}
     * @param v2 {词 : w}
     * @return
     */
    public double calCosSim(Map<String, Double> v1, Map<String, Double>
            v2) {
        double sclar = 0.0,norm1=0.0,norm2=0.0,similarity=0.0;
        Set<String> v1Keys = v1.keySet();
        Set<String> v2Keys = v2.keySet();
        Set<String> both= new HashSet<>();
        both.addAll(v1Keys);
        both.retainAll(v2Keys);
        for (String str1 : both) {
            sclar += v1.get(str1) * v2.get(str1);
        }
        for (String str1:v1.keySet()){
            norm1+=Math.pow(v1.get(str1),2);
        }
        for (String str2:v2.keySet()){
            norm2+=Math.pow(v2.get(str2),2);
        }
        similarity=sclar/Math.sqrt(norm1*norm2);
        return similarity;
    }
}
