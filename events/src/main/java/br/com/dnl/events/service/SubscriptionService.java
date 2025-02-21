package br.com.dnl.events.service;


import br.com.dnl.events.entity.Event;
import br.com.dnl.events.entity.Subscription;
import br.com.dnl.events.entity.User;
import br.com.dnl.events.repository.EventPepository;
import br.com.dnl.events.repository.SubscriptionRepository;
import br.com.dnl.events.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SubscriptionService {
    //Insjeção de depedência de três classe
    @Autowired
    UserRepository userRepository;

    @Autowired
    SubscriptionRepository subscriptionRepository;

    @Autowired
    EventPepository eventPepository;

    //Para criar uma inscrição eu preciso passar o noeme do evento e o nome do usuário
    public Subscription createNewSubscription (String eventName , User user){



        //Recupar o evento pelo nome
        Event evento = eventPepository.findByPrettyName(eventName);
        //Gravar o Usuario no Banco
        User usuario = userRepository.save(user);


         Subscription subs = new Subscription();
        //Coloco o evento ue recuperei na incrição
          subs.setEvent(evento);
        //atribuir o Usuario como assinante
          subs.setSubscription(user);
        //Salvar a inscrição
          Subscription saveSubs = subscriptionRepository.save(subs);

          return saveSubs;
    }



}
