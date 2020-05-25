package com.certimetergroup.springbatchrest.personjob.listener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.BatchStatus;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.listener.JobExecutionListenerSupport;
import org.springframework.stereotype.Component;

@Component
public class PersonJobCompletionNotificationListener extends JobExecutionListenerSupport {

    private final Logger log = LoggerFactory.getLogger(PersonJobCompletionNotificationListener.class);


    @Override
    public void afterJob(JobExecution jobExecution) {
        if(jobExecution.getStatus() == BatchStatus.COMPLETED) {
            log.info("!!! JOB COMPLETED !!! Time to verify the results");

        } else {
            log.error("[FAILURE] status: " + jobExecution.getStatus().name());
            jobExecution.getAllFailureExceptions().forEach(exc -> log.error(exc.getMessage(), exc));
        }
    }

}
