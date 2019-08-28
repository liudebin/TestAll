/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package qian.ling.yi.ext.drools.listener;

import org.kie.api.event.process.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class LogProcessEventListener implements org.kie.api.event.process.ProcessEventListener {

    private static Logger logger = LoggerFactory.getLogger(LogProcessEventListener.class);


    @Override
        public void beforeProcessStarted(ProcessStartedEvent pse) {
            logger.info(">>> BeforeProcessStarted: " + pse.getProcessInstance().getProcessId());
        }

        @Override
        public void afterProcessStarted(ProcessStartedEvent pse) {
            logger.info(">>> AfterProcessStarted: " + pse.getProcessInstance().getProcessId());
        }

        @Override
        public void beforeProcessCompleted(ProcessCompletedEvent pce) {
            logger.info(">>> BeforeProcessCompleted: " + pce.getProcessInstance().getProcessId());
        }

        @Override
        public void afterProcessCompleted(ProcessCompletedEvent pce) {
            logger.info(">>> AfterProcessCompleted: " + pce.getProcessInstance().getProcessId());
        }

        @Override
        public void beforeNodeTriggered(ProcessNodeTriggeredEvent pnte) {
            logger.info(">>> BeforeNodeTriggered: " + pnte.getNodeInstance().getNode().getName());
        }

        @Override
        public void afterNodeTriggered(ProcessNodeTriggeredEvent pnte) {
            logger.info(">>> AfterNodeTriggered: " + pnte.getNodeInstance().getNode().getName());
        }

        @Override
        public void beforeNodeLeft(ProcessNodeLeftEvent pnle) {
            logger.info(">>> BeforeNodeLeft: " + pnle.getNodeInstance().getNode().getName());
        }

        @Override
        public void afterNodeLeft(ProcessNodeLeftEvent pnle) {
            logger.info(">>> AfterNodeLeft: " + pnle.getNodeInstance().getNode().getName());
        }

        @Override
        public void beforeVariableChanged(ProcessVariableChangedEvent pvce) {
            logger.info(">>> BeforeVariableChanged: " + pvce.getVariableId() + " new Value: " + pvce.getNewValue() + " - old Value: " + pvce.getOldValue());
        }

        @Override
        public void afterVariableChanged(ProcessVariableChangedEvent pvce) {
            logger.info(">>> AfterVariableChanged: " + pvce.getVariableId() + " new Value: " + pvce.getNewValue() + " - old Value: " + pvce.getOldValue());
        }
}
