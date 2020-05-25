package com.certimetergroup.springbatchrest.personjob.controller;

import com.certimetergroup.springbatchrest.personjob.scheduler.PersonJobScheduler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/job/person")
public class PersonJobController {

    @Autowired
    private PersonJobScheduler personJobScheduler;



    @RequestMapping("/invoke")
    public ResponseEntity<String> handle() throws Exception {

        personJobScheduler.schedule();

        return ResponseEntity.ok().body("Batch job has been invoked");
    }

}
