package ru.ponies.pink.domain.entity;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.Data;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;
import ru.ponies.pink.domain.entity.enums.SubjectType;
import ru.ponies.pink.service.impl.UserSerializer;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import java.util.List;
import java.util.UUID;

@Entity
@Data
public class Subject {
    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    @Type(type = "uuid-char")
    private UUID id;
    @ManyToMany(cascade = CascadeType.PERSIST)
    @JoinTable(name = "subject_user",
            joinColumns = @JoinColumn(name = "subject_id"),
            inverseJoinColumns = @JoinColumn(name = "user_id"))
    @JsonSerialize(using = UserSerializer.class)
    private List<User> users;
    @Enumerated(EnumType.STRING)
    @Column(name = "subject_type")
    private SubjectType subjectType;
    @OneToMany(mappedBy = "subject")
    private List<Strategy> strategies;
}
