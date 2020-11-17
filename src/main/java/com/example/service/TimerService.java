package com.example.service;

import java.util.Map;

import org.activiti.engine.delegate.DelegateExecution;
import org.activiti.engine.delegate.JavaDelegate;
import org.activiti.engine.impl.context.Context;
import org.activiti.engine.task.Task;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class TimerService implements JavaDelegate {
    Logger LOGGER = LoggerFactory.getLogger(TimerService.class);

    @Override
    public void execute(DelegateExecution execution) throws Exception {
        LOGGER.info("*** Executing Timer autocomplete ***");
        Task task = Context.getProcessEngineConfiguration().getTaskService().createTaskQuery().active().singleResult();  
        
        Map<String, Object> taskVariables = Context.getProcessEngineConfiguration().getTaskService().getVariables(task.getId());
        LOGGER.info("Task Variables: {}", taskVariables.keySet().toString());
        
        Context.getProcessEngineConfiguration().getTaskService().complete(task.getId());
        LOGGER.info("*** Task: {} autocompleted by timer ***", task.getId());
    }
}
