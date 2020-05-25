package com.certimetergroup.springbatchrest.personjob.step1;

import com.certimetergroup.springbatchrest.personjob.model.Person;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.batch.item.ItemReader;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@Component
public class PersonItemReader implements ItemReader<Person> {

    private List<Person> people;
    private int INDEX = 0;



    @Override
    public Person read() {
        if( this.people == null )       this.people = this.retrievePeopleFromRestAPI(); // = Arrays.asList( new Person("Loris", "Cernich"), new Person("Gabriele", "Onida"), new Person("Nina", "Vizir"), new Person("Simone", "Ferraro"), new Person("Teseo", "Monciotti") );

        if ( INDEX < people.size() )    return people.get( INDEX++ );

        System.out.println("@@@@@   RESET PEOPLE   @@@@@");
        INDEX = 0;
        this.people = null;

        return null;
    }


    /******************************************************/
    /******************************************************/
    /******************************************************/


    private List<Person> retrievePeopleFromRestAPI(){
        System.out.println("@@@@@   RETRIEVE PEOPLE FROM REST API    @@@@@");
        ResponseEntity<String> response = new RestTemplate().getForEntity(
                "https://jsonplaceholder.typicode.com/users",
                String.class
        );
        JSONArray usersData = new JSONArray( response.getBody() );

        List<Person> output = new ArrayList<>();
        for(Object userOBJ : usersData) {
            JSONObject user = (JSONObject) userOBJ;
            Person person = new Person();
            person.setFirstName(user.getString("username"));
            person.setLastName(user.getString("name"));
            output.add(person);
        }
        return output;
    }

}
