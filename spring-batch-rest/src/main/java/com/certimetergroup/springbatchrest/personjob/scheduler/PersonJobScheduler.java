package com.certimetergroup.springbatchrest.personjob.scheduler;

import org.springframework.batch.core.*;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.repository.JobExecutionAlreadyRunningException;
import org.springframework.batch.core.repository.JobInstanceAlreadyCompleteException;
import org.springframework.batch.core.repository.JobRestartException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class PersonJobScheduler {

    @Autowired
    private JobLauncher personJobLuncher;
    @Autowired
    private Job personJOB;



    @Scheduled(cron = "*/5 * * * * *")
    public void schedule() throws JobParametersInvalidException, JobExecutionAlreadyRunningException, JobRestartException, JobInstanceAlreadyCompleteException {

        System.out.println(">>>>> START - Person Job Started at :" + new Date());

        JobParameters param = new JobParametersBuilder()
                .addString("MyJobID", String.valueOf(System.currentTimeMillis()))
                .toJobParameters();
        JobExecution execution = personJobLuncher.run(personJOB, param);

        System.out.println("<<<<< END - Person Job finished with status: " + execution.getStatus().name());
    }

}
