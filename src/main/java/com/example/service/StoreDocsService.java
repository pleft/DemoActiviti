package com.example.service;

import java.util.Map;

import org.activiti.engine.delegate.DelegateExecution;
import org.activiti.engine.delegate.JavaDelegate;
import org.activiti.engine.impl.context.Context;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class StoreDocsService implements JavaDelegate {
    Logger LOGGER = LoggerFactory.getLogger(StoreDocsService.class);

    @Override
    public void execute(DelegateExecution execution) throws Exception {
        LOGGER.info("*** Executing Store Documents ***");
        Map<String, Object> execVariables = Context.getProcessEngineConfiguration().getRuntimeService().getVariables(execution.getId());
        LOGGER.info("Exec Variables: {}", execVariables.keySet().toString());
    }
}
