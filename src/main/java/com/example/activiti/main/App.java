package com.example.activiti.main;

import org.activiti.engine.*;
import org.activiti.engine.impl.cfg.StandaloneProcessEngineConfiguration;
import org.activiti.engine.repository.Deployment;
import org.activiti.engine.repository.ProcessDefinition;
import org.activiti.engine.runtime.Execution;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Task;

import java.util.List;
import java.util.Map;
import java.util.HashMap;

public class App
{
    public static void main( String[] args ) throws Exception
    {

//        DefaultAsyncJobExecutor demoAsyncJobExecutor = new DefaultAsyncJobExecutor();
//        demoAsyncJobExecutor.setCorePoolSize(10);
//        demoAsyncJobExecutor.setMaxPoolSize(50);
//        demoAsyncJobExecutor.setKeepAliveTime(10000);
//        demoAsyncJobExecutor.setMaxAsyncJobsDuePerAcquisition(50);

        ProcessEngineConfiguration cfg = new StandaloneProcessEngineConfiguration()
                .setJdbcUrl("jdbc:h2:mem:activiti;DB_CLOSE_DELAY=1000")
                .setJdbcUsername("sa")
                .setJdbcPassword("")
                .setJdbcDriver("org.h2.Driver")
                .setDatabaseSchemaUpdate(ProcessEngineConfiguration.DB_SCHEMA_UPDATE_TRUE)
//                .setAsyncExecutorActivate(true)
//                .setAsyncExecutorEnabled(true)
//                .setAsyncExecutor(demoAsyncJobExecutor)
                .setJobExecutorActivate(true)
                ;
        ProcessEngine processEngine = cfg.buildProcessEngine();
        String pName = processEngine.getName();
        String ver = ProcessEngine.VERSION;
        System.out.println("ProcessEngine [" + pName + "] Version: [" + ver + "]");

        RepositoryService repositoryService = processEngine.getRepositoryService();
        Deployment deployment = repositoryService.createDeployment()
                .addClasspathResource("MyProcess.bpmn").deploy();
        ProcessDefinition processDefinition = repositoryService.createProcessDefinitionQuery()
                .deploymentId(deployment.getId()).singleResult();
        System.out.println(
                "Found process definition ["
                        + processDefinition.getName() + "] with id ["
                        + processDefinition.getId() + "]");

        final Map<String, Object> variables = new HashMap<String, Object>();
        final RuntimeService runtimeService = processEngine.getRuntimeService();

        ProcessInstance id = runtimeService.startProcessInstanceByKey("myProcess", variables);
        System.out.println("Started Process Id: "+id.getId());
        try {
            final TaskService taskService = processEngine.getTaskService();
//            List<Task> tasks = taskService.createTaskQuery().active().list();
//            while (!tasks.isEmpty()) {
//                Task task = tasks.get(0);
//                taskService.complete(task.getId());
//                tasks = taskService.createTaskQuery().active().list();
//            }

        } catch (Exception e) {
            System.out.println(e.getMessage());
        } finally {

        }

        while(!runtimeService.createExecutionQuery().list().isEmpty()) {
        }
        processEngine.close();
    }


}
