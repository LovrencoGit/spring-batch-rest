package com.certimetergroup.springbatchrest.personjob.configuration;

import com.certimetergroup.springbatchrest.personjob.listener.PersonJobCompletionNotificationListener;
import com.certimetergroup.springbatchrest.personjob.model.Person;
import com.certimetergroup.springbatchrest.personjob.step1.PersonItemProcessor;
import com.certimetergroup.springbatchrest.personjob.step1.PersonItemReader;
import com.certimetergroup.springbatchrest.personjob.step1.PersonItemWriter;
import org.springframework.batch.core.*;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class PersonJobConfiguration {

    @Autowired
    public JobBuilderFactory jobBuilderFactory;
    @Autowired
    public StepBuilderFactory stepBuilderFactory;


    @Autowired
    private PersonJobCompletionNotificationListener personLISTENER;
    @Autowired
    private PersonItemReader personREADER;
    @Autowired
    private PersonItemProcessor personPROCESSOR;
    @Autowired
    private PersonItemWriter personWRITER;



    @Bean
    public Step personSTEP1() {
        return stepBuilderFactory.get("personSTEP1")
                .<Person, Person> chunk(10)
                .reader(    this.personREADER      )
                .processor( this.personPROCESSOR   )
                .writer(    this.personWRITER      )
                .build();
    }


    @Bean
    public Job personJOB() {
        return jobBuilderFactory.get("importUserJob")
                .incrementer( new RunIdIncrementer() )
                .listener(  this.personLISTENER      )
                .flow(      this.personSTEP1()  )
                .end()
                .build();
    }

}
