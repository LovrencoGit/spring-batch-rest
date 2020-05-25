package com.certimetergroup.springbatchrest.personjob.step1;

import com.certimetergroup.springbatchrest.personjob.model.Person;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.batch.item.ItemProcessor;
import org.springframework.stereotype.Component;

@Component
public class PersonItemProcessor implements ItemProcessor<Person, Person> {

    private final Logger log = LoggerFactory.getLogger( this.getClass() );

    @Override
    public Person process(final Person person) {
        final String firstName = person.getFirstName().toUpperCase();
        final String lastName = person.getLastName().toUpperCase();

        final Person processedPerson = new Person(firstName, lastName);
        //log.info("Converting (" + person + ") into (" + processedPerson + ")");

        return processedPerson;
    }

}
