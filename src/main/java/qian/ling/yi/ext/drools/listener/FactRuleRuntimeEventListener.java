package qian.ling.yi.ext.drools.listener;

import com.alibaba.fastjson.JSON;
import org.kie.api.event.rule.ObjectDeletedEvent;
import org.kie.api.event.rule.ObjectInsertedEvent;
import org.kie.api.event.rule.ObjectUpdatedEvent;
import org.kie.api.event.rule.RuleRuntimeEventListener;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.LinkedHashMap;
import java.util.Map;



public class FactRuleRuntimeEventListener implements RuleRuntimeEventListener {

    private static Logger logger = LoggerFactory.getLogger(FactRuleRuntimeEventListener.class);


    @Override
    public void objectInserted(ObjectInsertedEvent objectInsertedEvent) {
        String info = JSON.toJSONString(objectInsertedEvent.getObject());
        logger.info("对象插入：{}", info);
    }

    @Override
    public void objectUpdated(ObjectUpdatedEvent objectUpdatedEvent) {
        Map<String, Object> data = new LinkedHashMap<>();
        data.put("object", objectUpdatedEvent.getObject());
        data.put("oldObject", objectUpdatedEvent.getOldObject());
        String info = JSON.toJSONString(data);
        logger.info("对象更新：{}", info);
    }

    @Override
    public void objectDeleted(ObjectDeletedEvent objectDeletedEvent) {
        String info = JSON.toJSONString(objectDeletedEvent.getOldObject());
        logger.info("对象删除：{}", info);
    }


}
