package br.com.dnl.events.controller;


import br.com.dnl.events.entity.Event;
import br.com.dnl.events.service.EventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class EventController {

    //Injeção de depedência
    @Autowired
    private EventService eventService;


    @PostMapping("/events")
    public Event addNewEvent(@RequestBody Event newEvent){

        return eventService.addNewEvent(newEvent);

    }

    @GetMapping("/eventsAll")
    public List<Event> getAllEvents(){
        return eventService.getAllEvents();
    }

    @GetMapping("event/{prettyName}")
    public ResponseEntity<Event> getFindByPrettyName(@PathVariable String prettyName){

        Event evt = eventService.getByPrettyName(prettyName);

        if(evt !=null){
           return ResponseEntity.ok().body(evt);
        }else {
           return ResponseEntity.notFound().build();
        }

    }
}
