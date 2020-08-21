package org.vorobiev.serviceCatalog;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


@RestController

public class processService {
    private ArrayList<Service> services;

    public processService() {

        ArrayList<Service> s = new ArrayList<Service>();
        s.add(new Service("premPayment", "Досрочное погашение"));
        s.add(new Service("changeSchedule", "Изменение графика"));
        s.add(new Service("restcuct", "Реструктуризация"));

        this.services = s;
    }

    @GetMapping("/version")
    public ResponseEntity<String> getVersion() throws InterruptedException {
        return new ResponseEntity<String>("{\"version\": \"" + "0.01" + "''\"}", HttpStatus.OK);
    }


    @RequestMapping(path = "/services",
            method = RequestMethod.GET,
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)

    public List<Service> getServices() {
        return services;

    }

    @RequestMapping(path = "/validate",
            method = RequestMethod.GET,
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)

    public Request getValidate(@RequestBody Request request) {

        int index = (int) Math.floor(Math.random() * (3));
        request.setDueDate(Date.from(ZonedDateTime.now().plusDays(index).toInstant()));
        return request;

    }

}

