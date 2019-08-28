package qian.ling.yi.ext.drools.listener;

import com.alibaba.fastjson.JSON;
import org.kie.api.definition.rule.Rule;
import org.kie.api.event.rule.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;


public class ExecPathAgendaEventListener implements AgendaEventListener {

    private static Logger logger = LoggerFactory.getLogger(ExecPathAgendaEventListener.class);

    private int count = 0;


    @Override
    public void matchCreated(MatchCreatedEvent matchCreatedEvent) {
        logger.info("{}", matchCreatedEvent.toString());
    }

    @Override
    public void matchCancelled(MatchCancelledEvent matchCancelledEvent) {
        logger.info("{}", matchCancelledEvent.toString());
    }

    @Override
    public void beforeMatchFired(BeforeMatchFiredEvent beforeMatchFiredEvent) {
        Rule rule = beforeMatchFiredEvent.getMatch().getRule();
        logger.info("####    进入规则： [{}].[{}]    ####", rule.getPackageName(), rule.getName());
        List<Object> facts = beforeMatchFiredEvent.getMatch().getObjects();
        String infoStr = getInfoString(facts);
        logger.info("规则开始，当前信息：[{}]", infoStr);
        dbLogEnterRule(infoStr);
    }


    private void dbLogEnterRule(String infoStr){
        logger.info("dbLogEnterRule  {}", infoStr);
    }

    private String getInfoString(List<Object> facts) {
        Map<String, Object> info = new LinkedHashMap<>();
        info.put("规则激发计数器", count);
        info.put("触发规则的Facts", facts);
        return JSON.toJSONString(info);
    }

    private void dbLogLeaveRule(String infoStr){
        dbLogEnterRule(infoStr);
    }


    @Override
    public void afterMatchFired(AfterMatchFiredEvent afterMatchFiredEvent) {
        Rule rule = afterMatchFiredEvent.getMatch().getRule();
        List<Object> facts = afterMatchFiredEvent.getMatch().getObjects();
        String infoStr = getInfoString(facts);
        logger.info("规则结束，当前信息：[{}]", infoStr);
        dbLogLeaveRule(infoStr);
        logger.info("####    离开规则： [{}].[{}]    ####", rule.getPackageName(), rule.getName());

    }

    @Override
    public void agendaGroupPopped(AgendaGroupPoppedEvent agendaGroupPoppedEvent) {

    }

    @Override
    public void agendaGroupPushed(AgendaGroupPushedEvent agendaGroupPushedEvent) {

    }

    @Override
    public void beforeRuleFlowGroupActivated(RuleFlowGroupActivatedEvent ruleFlowGroupActivatedEvent) {

    }

    @Override
    public void afterRuleFlowGroupActivated(RuleFlowGroupActivatedEvent ruleFlowGroupActivatedEvent) {

    }

    @Override
    public void beforeRuleFlowGroupDeactivated(RuleFlowGroupDeactivatedEvent ruleFlowGroupDeactivatedEvent) {

    }

    @Override
    public void afterRuleFlowGroupDeactivated(RuleFlowGroupDeactivatedEvent ruleFlowGroupDeactivatedEvent) {

    }
}
