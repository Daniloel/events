package br.com.dnl.events.repository;
//Class de interface é uma classe de contrato.

import br.com.dnl.events.entity.Event;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EventPepository extends CrudRepository<Event ,Long> {
    public Event findByPrettyName(String prettyName);
}
