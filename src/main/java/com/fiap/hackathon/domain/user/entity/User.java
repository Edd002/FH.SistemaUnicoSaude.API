package com.fiap.hackathon.domain.user.entity;

import com.fiap.hackathon.domain.address.entity.Address;
import com.fiap.hackathon.domain.jwt.entity.Jwt;
import com.fiap.hackathon.domain.user.UserEntityListener;
import com.fiap.hackathon.domain.user.enumerated.UserTypeEnum;
import com.fiap.hackathon.domain.user.enumerated.constraint.UserConstraint;
import com.fiap.hackathon.domain.questionnaireuser.entity.QuestionnaireUser;
import com.fiap.hackathon.global.audit.Audit;
import com.fiap.hackathon.global.constraint.ConstraintMapper;
import com.fiap.hackathon.global.exception.UserTypeAdminNotAllowedException;
import com.fiap.hackathon.global.util.CryptoUtil;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.SQLRestriction;

import java.io.Serial;
import java.io.Serializable;
import java.util.List;

@Getter(value = AccessLevel.PUBLIC)
@Setter(value = AccessLevel.PROTECTED)
@Entity
@Table(name = "t_user")
@SQLDelete(sql = "UPDATE t_user SET deleted = true WHERE id = ?")
@SQLRestriction(value = "deleted = false")
@EntityListeners({ UserEntityListener.class })
@ConstraintMapper(constraintClass = UserConstraint.class)
public class User extends Audit implements Serializable {

    protected User() {}

    public User(@NonNull String name, @NonNull String email, @NonNull String login, @NonNull String passwordCryptoKey, @NonNull String password, @NonNull UserTypeEnum userType, @NonNull Address address) {
        this.setName(name);
        this.setEmail(email);
        this.setLogin(login);
        this.setEncryptedPassword(passwordCryptoKey, password);
        this.setUserType(userType);
        this.setAddress(address);
    }

    public User rebuild(@NonNull String name, @NonNull String email, @NonNull UserTypeEnum type, @NonNull Address address) {
        this.setName(name);
        this.setEmail(email);
        this.setUserType(type);
        this.setAddress(address);
        return this;
    }

    public User rebuild(@NonNull String login) {
        this.setLogin(login);
        return this;
    }

    @Serial
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(generator = "SQ_USER")
    @SequenceGenerator(name = "SQ_USER", sequenceName = "SQ_USER", schema = "public", allocationSize = 1)
    @Column(name = "id", nullable = false, updatable = false)
    private Long id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "email", nullable = false)
    private String email;

    @Column(name = "login", nullable = false)
    private String login;

    @Column(name = "password", nullable = false)
    private String password;

    @Column(name = "user_type", nullable = false)
    @Enumerated(EnumType.STRING)
    private UserTypeEnum userType;

    @OneToOne(fetch = FetchType.EAGER, cascade = { CascadeType.PERSIST, CascadeType.REMOVE, CascadeType.MERGE })
    @JoinColumn(name = "fk_address", nullable = false)
    private Address address;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "user", cascade = { CascadeType.PERSIST, CascadeType.REMOVE })
    private List<Jwt> jwts;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "user")
    private List<QuestionnaireUser> questionnaireUsers;

    @Transient
    private transient User userSavedState;

    public void saveState(User userSavedState) {
        this.userSavedState = userSavedState;
    }

    public void setEncryptedPassword(@NonNull String passwordCryptoKey, @NonNull String password) {
        this.password = CryptoUtil.newInstance(passwordCryptoKey).encode(password);
    }

    public void setUserType(@NonNull UserTypeEnum userType) {
        if (UserTypeEnum.isTypeAdmin(userType.name())) {
            throw new UserTypeAdminNotAllowedException("Usuários administradores são pré cadastrados pelo sistema e não podem ser persistidos.");
        }
        this.userType = userType;
    }
}