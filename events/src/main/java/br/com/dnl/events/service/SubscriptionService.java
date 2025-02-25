package br.com.dnl.events.service;


import br.com.dnl.events.dto.SubscriptionRankingByUser;
import br.com.dnl.events.dto.SubscriptionRankingItem;
import br.com.dnl.events.dto.SubscriptionResponse;
import br.com.dnl.events.entity.Event;
import br.com.dnl.events.entity.Subscription;
import br.com.dnl.events.entity.User;
import br.com.dnl.events.exception.EventNotFoundException;
import br.com.dnl.events.exception.SubscriptionConflictException;
import br.com.dnl.events.exception.UserIndicadorNotFoundException;
import br.com.dnl.events.repository.EventPepository;
import br.com.dnl.events.repository.SubscriptionRepository;
import br.com.dnl.events.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.IntStream;

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
    public SubscriptionResponse createNewSubscription (String eventName , User user,Long userId){



        //Recupar o evento pelo nome
        Event evento = eventPepository.findByPrettyName(eventName);
        if(evento == null){
            throw new EventNotFoundException("O evento" + eventName + "não existe");
        }

        //Recupero Usuario se existir
        User userRecuperado = userRepository.findByUserEmail(user.getUserEmail());
        if(userRecuperado == null){
            userRecuperado = userRepository.save(user);
        }

        User indicador = null;
        if (userId != null){
            indicador = userRepository.findById(userId).orElse(null);
            if(indicador == null){
                throw new UserIndicadorNotFoundException("O usuario "+userId+"não existe");
            }
        }
        //Gravar o Usuario no Banco
        //User usuario = userRepository.save(user);


         Subscription subs = new Subscription();
        //Coloco o evento ue recuperei na incrição
          subs.setEvent(evento);
        //atribuir o Usuario como assinante
          subs.setSubscription(userRecuperado);
          subs.setIndication(indicador);

        Subscription tmpSub = subscriptionRepository.findByEventAndSubscription(evento,userRecuperado);
        if(tmpSub != null){
            throw new SubscriptionConflictException("Já existe o usuario " + userRecuperado.getUserName() + " ja  esta isncrito para este  evento");
        }

        //Salvar a inscrição
          Subscription saveSubs = subscriptionRepository.save(subs);

          return new SubscriptionResponse(saveSubs.getSubscription_number(),"http://codeCraft.com/"+ saveSubs.getEvent().getPrettyName()+"/"+ saveSubs.getSubscription().getUserId()) ;
    }


    public List<SubscriptionRankingItem> getCompleteRanking(String prettyName){

        Event evt = eventPepository.findByPrettyName(prettyName) ;
        if (evt == null){
            throw new EventNotFoundException("Ranking doo evento " + prettyName + "não existe");
        }

        return subscriptionRepository.generateRanking(evt.getEventId());
    }

    public SubscriptionRankingByUser  getRanlinkgByUser(String prettyName ,Long userId){
        List<SubscriptionRankingItem> ranking = getCompleteRanking(prettyName);

        //Filtro da lista
        SubscriptionRankingItem item = ranking.stream().filter(i->i.userId().equals(userId)).findFirst().orElse(null);
        if(item == null){
            throw new UserIndicadorNotFoundException("O usuario" + userId+ "não tem indicações");

        }
        Integer posicao = IntStream.range(0,ranking.size())
                .filter(pos -> ranking.get(pos).userId().equals(userId))
                .findFirst().getAsInt();
        return new SubscriptionRankingByUser(item,posicao+1);
    }



}
