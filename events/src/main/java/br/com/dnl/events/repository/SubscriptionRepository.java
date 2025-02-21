package br.com.dnl.events.repository;

import br.com.dnl.events.entity.Subscription;
import org.springframework.data.repository.CrudRepository;

public interface SubscriptionRepository extends CrudRepository<Subscription,Long> {
}
