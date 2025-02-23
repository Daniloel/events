package br.com.dnl.events.controller;


import br.com.dnl.events.dto.ErrorMessage;
import br.com.dnl.events.dto.SubscriptionResponse;
import br.com.dnl.events.entity.Subscription;
import br.com.dnl.events.entity.User;
import br.com.dnl.events.exception.EventNotFoundException;
import br.com.dnl.events.exception.SubscriptionConflictException;
import br.com.dnl.events.exception.UserIndicadorNotFoundException;
import br.com.dnl.events.service.SubscriptionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SubscriptionController {

    @Autowired
    SubscriptionService subscriptionService;

    @PostMapping({"/subscription/{prettyName}","/subscription/{prettyName}/{userId}"})
    public ResponseEntity<?> createSubscription (@PathVariable String prettyName, @RequestBody User subscriber,
                                                 @PathVariable(required = false) Long userId){
       try {
           SubscriptionResponse resultadoSubscription = subscriptionService.createNewSubscription(prettyName,subscriber,userId);
           if(resultadoSubscription !=null){
               return  ResponseEntity.ok(resultadoSubscription);
           }
       }catch (EventNotFoundException ex ){
        return ResponseEntity.status(404).body(new ErrorMessage(ex.getMessage()));
       }catch (SubscriptionConflictException ex){
         return ResponseEntity.status(409).body(new ErrorMessage(ex.getMessage()));
       }catch (UserIndicadorNotFoundException ex){
         return ResponseEntity.status(404).body(new ErrorMessage(ex.getMessage()));
       }

        return ResponseEntity.badRequest().build();
    }

}
