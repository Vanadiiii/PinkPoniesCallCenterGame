package ru.ponies.pink.domain.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.List;
import java.util.UUID;

@Data
@Entity
@Table
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Strategy {

    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    @Type(type = "uuid-char")
    private UUID id;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, mappedBy = "strategy")
    private List<Reward> reward;

    @Column(name = "is_complete")
    private Boolean isComplete;

    @ManyToOne
    private Subject subject;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, mappedBy = "strategy")
    private List<Condition> conditions;

}
