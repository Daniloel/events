package br.com.dnl.events.repository;

import br.com.dnl.events.entity.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends CrudRepository<User,Long> {
    public User findByEmail(String email); //REcupera o susario por email
}

