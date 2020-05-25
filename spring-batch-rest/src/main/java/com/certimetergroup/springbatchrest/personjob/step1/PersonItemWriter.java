package com.certimetergroup.springbatchrest.personjob.step1;

import com.certimetergroup.springbatchrest.personjob.model.Person;
import org.springframework.batch.item.ItemWriter;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class PersonItemWriter implements ItemWriter<Person> {

    @Override
    public void write(List<? extends Person> people) {
        people.forEach( p -> System.out.println( "##### " + p) );
    }

}
