package qian.ling.yi.ext.json.fastJson;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.google.gson.JsonElement;
import com.google.gson.JsonIOException;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import org.junit.Test;

import java.util.*;

/**
 * TODO
 *
 * @date: 2019/3/7.
 * @author: guobin.liu@holaverse.com
 */

public class sdfasdfa {

    String arrayStr = "{\"valueName1\":{\"firstname\":{\"oldname\":\"timi\",\"newname\":\"tim\"}," +
            "\"lastname\":\"James\"," +
            "\"array\":[{\"a\":0},{\"a\":2},{\"a\":2}]},\"age\":\"33\",\"edu\":\"tim\"}";
    String baseStr =  "{\"valueName1\":{\"firstname\":{\"oldname\":\"timi\",\"newname\":\"tim\"}," +
            "\"lastname\":\"James\"}," +
            "\"age\":\"33\",\"edu\":\"tim\"}";

    public static void main(String[] args) throws Exception {

        String str = "{\"valueName1\":{\"firstname\":\"timi\",\"lastname\":\"James\"},\"age\":\"6\",\"edu\":\"SDU\"}";

        JsonParser parser = new JsonParser();
        JsonElement tree = parser.parse(str);//构建json树
        //System.out.println(tree.isJsonObject());//true
        JsonObject jo = (JsonObject)tree;//强制转换的目的是之后可以使用entrySet
        //System.out.println(jo.keySet());
        //System.out.println(jo.entrySet());
        for(Map.Entry entry : jo.entrySet()){//遍历构建的json树
            JsonElement tmpValue = (JsonElement) entry.getValue();
            if(tmpValue.isJsonObject()){
                //将取出的键值对的值进行判定，如果值是JsonObject类型，说明内部嵌套了键值对，所以需要进一步处理
                JsonObject jo1 = (JsonObject)tmpValue;
                for(Map.Entry entry1 : jo1.entrySet()){
                    //遍历第二层的json键值对
                    System.out.println(entry1.getKey());
                    System.out.println(entry1.getValue().toString());
                    System.out.println("---------------");

                }
            }else {
                //处理没有嵌套的json键值对
                System.out.println(entry.getKey());
                System.out.println(entry.getValue());
                System.out.println("---------------");
            }
        }
        System.out.println("++++--------------------++++++++");

    }



    @Test
    public void GsonTest(){
        JsonParser parser = new JsonParser();
        JsonElement tree = parser.parse(baseStr);
        //System.out.println(tree.isJsonObject());//true
        JsonObject jo = (JsonObject)tree;
        JsonObject fa = new JsonObject();
        String father = "";
        //System.out.println(flatten(jo,fa,father));
        JsonObject resultJo = flatten(jo,fa,father);
        for(Map.Entry entry : resultJo.entrySet()){
            System.out.println(entry.getKey().toString());
            System.out.println(entry.getValue().toString());
        }
    }

    @Test
    public void testFastjson(){
        JSONObject jsonObject = JSON.parseObject(baseStr);

        JSONObject fa = new JSONObject();
        String father = "";
        //System.out.println(flatten(jo,fa,father));
        Map<String, Object> stringObjectMap = jsonConvertMap(jsonObject);
        for(Map.Entry entry : stringObjectMap.entrySet()){
            System.out.println(entry.getKey().toString());
            System.out.println(entry.getValue().toString());
        }

    }


    private static JsonObject flatten(JsonObject object, JsonObject flattened, String father) {
        //flatten递归函数实现对多层json的扁平化处理解析，第三个形参仅仅用来保留外层的键并在之后进行拼接

        if (flattened == null) {
            flattened = new JsonObject();
        }
        // Iterator<String> keys =(Iterator<String>) object.keySet();
        for (Map.Entry entry : object.entrySet()) {
            String midFather = entry.getKey().toString();
            String tmp = father;
            JsonElement tmpVa = (JsonElement) entry.getValue();
            try {
                if (tmpVa.isJsonObject()) {
                    //检测到多层json的时候进行递归处理
                    tmp = tmp + "." + midFather;//当前层键与之前外层键进行拼接
                    //System.out.println("aaa"+entry.getKey().toString()+"--------"+tmp);
                    flatten(object.getAsJsonObject(entry.getKey().toString()), flattened, tmp);
                } else {
                    //当前层的值没有嵌套json键值对，直接将键值对添加到flattened中
                    String nowKeyTmp = father + "." + entry.getKey().toString();
                    String nowKey = nowKeyTmp.substring(1, nowKeyTmp.length());
                    flattened.add(nowKey, ((JsonElement) entry.getValue()));
                }
            } catch (JsonIOException e) {
                System.out.println(e);
            }
        }

        return flattened;

    }




    public static Map<String, Object> jsonConvertMap(JSONObject json){
        Map<String, Object> map = new HashMap<>();
        if(json != null && json.size() > 0){
            for(String k : json.keySet()){
                Object v = json.get(k);
                if(v instanceof JSONArray){
                    List<Map<String, Object>> list = new ArrayList<Map<String,Object>>();
                    Iterator<Object> it = ((JSONArray)v).iterator();
                    while(it.hasNext()){
                        JSONObject json2 = (JSONObject) it.next();
                        list.add(jsonConvertMap(json2));
                    }
                    map.put(k, list);
                } else if(v instanceof JSONObject){
                    List<Map<String, Object>> list = new ArrayList<Map<String,Object>>();
                    JSONObject child = (JSONObject) v;
                    for(String stk: child.keySet()){
                        Object value = child.get(stk);
                        if(value instanceof JSONArray){
                            Iterator<Object> it = ((JSONArray)value).iterator();
                            while(it.hasNext()){
                                JSONObject json2 = (JSONObject) it.next();
                                list.add(jsonConvertMap(json2));
                            }
                        }else{
                            map.put(stk, value.toString());
                        }
                    }
                    if(list.size() > 0){
                        for(int m = 0;m<list.size();m++){
                            Map<String, Object> chMap = list.get(m);
                            for(String chKey : chMap.keySet()){
                                map.put(chKey, chMap.get(chKey).toString());
                            }
                        }
                    }
                }else{
                    map.put(k, v);
                }
            }
        }
        return map;
    }


}
