package br.com.dnl.events.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "tbl_subscription")
public class Subscription {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "subscription_number")
    private Long subscriptionNumber;

    @ManyToOne
    @JoinColumn(name = "event_id")
    private Event event;

    @ManyToOne
    @JoinColumn(name = "subscribed_user_id")
    private User subscription;

    @ManyToOne
    @JoinColumn(name = "indication_user_id",nullable = true)
    private User indication;

    public Long getSubscription_number() {
        return subscriptionNumber;
    }

    public void setSubscription_number(Long subscription_number) {
        this.subscriptionNumber = subscription_number;
    }

    public Event getEvent() {
        return event;
    }

    public void setEvent(Event event) {
        this.event = event;
    }

    public User getSubscription() {
        return subscription;
    }

    public void setSubscription(User subscription) {
        this.subscription = subscription;
    }

    public User getIndication() {
        return indication;
    }

    public void setIndication(User indication) {
        this.indication = indication;
    }
}
