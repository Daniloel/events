package br.com.dnl.events.repository;

import br.com.dnl.events.dto.SubscriptionRankingItem;
import br.com.dnl.events.entity.Event;
import br.com.dnl.events.entity.Subscription;
import br.com.dnl.events.entity.User;
import org.hibernate.annotations.Parameter;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface SubscriptionRepository extends CrudRepository<Subscription,Long> {
    public Subscription findByEventAndSubscription(Event evt , User user);

//    @Query(value = "SELECT indication_user_id, COUNT(subscription_number) AS quantidade ,user_name"
//           +"FROM tbl_subscription"
//           +"INNER JOIN tbl_user ON tbl_subscription.indication_user_id = tbl_user.user_id"
//           +"WHERE  indication_user_id is NOT NULL"
//           +"  and event_Id = :eventId"
//           +"GROUP BY indication_user_id"
//           +"order by quantidade desc", nativeQuery = true)
//    public List<SubscriptionRankingItem> generateRanking(@Param("eventId") Long eventId);

    @Query(value = "SELECT indication_user_id, COUNT(subscription_number) AS quantidade, user_name " +
                   "FROM tbl_subscription " +
                   "INNER JOIN tbl_user ON tbl_subscription.indication_user_id = tbl_user.user_id " +
                   "WHERE indication_user_id IS NOT NULL " +
                   "AND event_Id = :eventId " +
                   "GROUP BY indication_user_id, user_name " +  // Adicionado user_name no GROUP BY
                   "ORDER BY quantidade DESC", nativeQuery = true)
    public List<SubscriptionRankingItem> generateRanking(@Param("eventId") Long eventId);


}
