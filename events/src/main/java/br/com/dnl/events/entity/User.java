package br.com.dnl.events.entity;


import jakarta.persistence.*;

@Entity
@Table(name ="tbl_user")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private  Long userId;

    @Column(name = "user_name" ,length = 255,nullable = false)
    private  String userName;

    @Column(name = "user_email",length = 255,nullable = false,unique = true)
    private  String userEmail;

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }
}
