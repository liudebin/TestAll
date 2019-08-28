package qian.ling.yi.ext.drools.listener;


import org.kie.api.event.rule.*;
import org.kie.api.runtime.KieSession;

public class TriggerRulesEventListener implements AgendaEventListener {

    private KieSession ksession;

    public void setKieSession(KieSession kieSession) {
        this.ksession = kieSession;
    }

    public TriggerRulesEventListener() {
    }

    public void matchCreated(MatchCreatedEvent event) {
    }

    public void matchCancelled(MatchCancelledEvent event) {
    }

    public void beforeMatchFired(BeforeMatchFiredEvent event) {
    }

    public void afterMatchFired(AfterMatchFiredEvent event) {
    }

    public void agendaGroupPopped(AgendaGroupPoppedEvent event) {
    }

    public void agendaGroupPushed(AgendaGroupPushedEvent event) {
    }

    public void beforeRuleFlowGroupActivated(RuleFlowGroupActivatedEvent event) {
    }

    public void afterRuleFlowGroupActivated(RuleFlowGroupActivatedEvent event) {
        this.ksession.fireAllRules();
    }

    public void beforeRuleFlowGroupDeactivated(RuleFlowGroupDeactivatedEvent event) {
    }

    public void afterRuleFlowGroupDeactivated(RuleFlowGroupDeactivatedEvent event) {
    }


}
