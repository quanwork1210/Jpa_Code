package com.jpa.jpaCode.Entity;

import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "account_payment")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AccountPayment extends  BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "balance")
    private double balance;

    @Column(name = "token")
    private String token;

    @ManyToOne
    @JoinColumn(name="user_id")
    private User user;

    @Version
    @Column(name = "version")
    private Integer version;

}

