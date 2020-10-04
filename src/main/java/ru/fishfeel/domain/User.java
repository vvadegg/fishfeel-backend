package ru.fishfeel.domain;

import lombok.Data;
import lombok.Value;
import org.hibernate.validator.constraints.Length;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import ru.fishfeel.validator.UniqueEmail;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "users")
@Data
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull(message = "не должен быть пустым")
    @NotBlank(message = "не должен быть пустым")
    @Email(regexp="[A-Za-z0-9._%-+]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,4}", message = "должен быть указан верно")
    @Length(max = 150, message = "длина должна быть максимум 150 символов")
    @Column(name = "email", unique = true)
    @UniqueEmail()
    private String email;

    @NotNull(message = "должно быть указано")
    @Length(min = 3, max = 50, message = "длина должна быть от 3 до 50 символов")
    @NotBlank(message = "должно быть указано")
    @Column(name = "first_name")
    private String firstName;

    @Length(min = 3, max = 50, message = "длина должна быть от 3 до 50 символов")
    @Column(name = "last_name")
    private String lastName;

    @NotNull(message = "пароль должен быть")
    @Length(min = 8, message = "пароль должен быть минимум 8 символов")
    @Column(name = "password")
    private String password;

    @CreatedDate
    @Column(name = "created")
    private Date created;

    @LastModifiedDate
    @Column(name = "updated")
    private Date updated;

    @Enumerated(EnumType.STRING)
    @Column(name = "status")
    private Status status;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "user_roles",
                joinColumns = {@JoinColumn(name = "user_id", referencedColumnName = "id")},
                inverseJoinColumns = {@JoinColumn(name = "role_id", referencedColumnName = "id")})
    private List<Role> roles;

    @PrePersist
    protected void onCreate(){
        created = new Date();
        updated = new Date();
    }

    @PreUpdate
    protected void onUpdate(){
        updated = new Date();
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName.trim();
    }

    public void setEmail(String email) {
        this.email = email.toLowerCase();
    }

    public void setLastName(String lastName) {
        this.lastName = lastName.trim();
    }
}
