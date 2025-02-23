package br.com.dnl.events.repository;

import br.com.dnl.events.entity.Event;
import br.com.dnl.events.entity.Subscription;
import br.com.dnl.events.entity.User;
import org.springframework.data.repository.CrudRepository;

public interface SubscriptionRepository extends CrudRepository<Subscription,Long> {
    public Subscription findByEventAndSubscription(Event evt , User user);

}
