package com.example.menu.data;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="user")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private int id;

    @Column(name = "user_login")
    private String login;

    @ManyToMany(cascade = CascadeType.DETACH, fetch = FetchType.EAGER)
    @JoinTable(
            name = "meal_plan",
            joinColumns = { @JoinColumn(name = "mp_user_id") },
            inverseJoinColumns = { @JoinColumn(name = "mp_rec_id") })
    private Set<Recipe> recipes = new HashSet<>();
}
