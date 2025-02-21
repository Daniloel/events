package br.com.dnl.events.service;

import br.com.dnl.events.entity.Event;
import br.com.dnl.events.repository.EventPepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Locale;

@Service
public class EventService {

    @Autowired
    private EventPepository eventPepository; // Injeção de depedência o Autowired esta instanciando o objeto e diponibilizando

    public Event addNewEvent(Event event){
      //Gerar pretty Name
      event.setPrettyName(event.getTitle().toLowerCase().replaceAll(" ","-"));
      return eventPepository.save(event);
    }

    //Solicita uma lista comtodos os eventos
    public List<Event> getAllEvents(){

        return (List<Event>)eventPepository.findAll();

    }

   public Event getByPrettyName(String prettyName){
      return eventPepository.findByPrettyName(prettyName);
   }


}
