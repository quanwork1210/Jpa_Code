package com.jpa.jpaCode.Entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "users")
public class User extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "email")
    private String email;

    private Date joined_at;

    @OneToMany(mappedBy = "user")
    private List<Products> products;


    //    @Column(name ="lastName")
//    private String lastName;

//    @Column(name = "birthday")
//    private Date birthday;

    @OneToMany(mappedBy = "user")
    private List<AccountPayment> accountPayments;

}
