package com.example.entity;

import javax.persistence.*;

/**
 * Created by bangae1 on 2016-06-13.
 */
@Entity
@Table(name = "auth", schema = "dev", uniqueConstraints = @UniqueConstraint(columnNames = {"id"}))
public class AuthEntity {
    private Integer seq;
    private String id;
    private String role;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="seq")
    public Integer getSeq() {
        return seq;
    }

    public void setSeq(Integer seq) {
        this.seq = seq;
    }

    @Basic
    @Column(name="id")
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Basic
    @Column(name="role")
    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    @Override
    public String toString() {
        return "AuthEntity{" +
                "seq=" + seq +
                ", id='" + id + '\'' +
                ", role='" + role + '\'' +
                '}';
    }
}
