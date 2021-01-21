package com.example.service;

import org.activiti.engine.delegate.DelegateExecution;
import org.activiti.engine.delegate.JavaDelegate;
import org.activiti.engine.task.Task;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class TimerService implements JavaDelegate {
    Logger LOGGER = LoggerFactory.getLogger(TimerService.class);

    @Override
    public void execute(DelegateExecution execution) throws Exception {
        LOGGER.info("*** Executing Timer autocomplete ***");
        Task task = execution.getEngineServices().getTaskService().createTaskQuery().active().singleResult();
        execution.getEngineServices().getTaskService().setVariableLocal(task.getId(), "taskLocalVar", "task_local_var_value");
        execution.getEngineServices().getTaskService().complete(task.getId());
        LOGGER.info("*** Task: {} autocompleted by timer ***", task.getId());
    }
}
