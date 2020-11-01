package com.example.service;

import org.activiti.engine.delegate.DelegateExecution;
import org.activiti.engine.delegate.JavaDelegate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class StoreDocsService implements JavaDelegate {
    Logger LOGGER = LoggerFactory.getLogger(StoreDocsService.class);

    @Override
    public void execute(DelegateExecution execution) throws Exception {
        LOGGER.info("*** Executing Store Documents ***");
    }
}
