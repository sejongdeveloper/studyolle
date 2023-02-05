package com.studyolle.studyolle.modules.event;

import com.studyolle.studyolle.modules.account.Account;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.NamedAttributeNode;
import javax.persistence.NamedEntityGraph;
import javax.persistence.NamedSubgraph;
import java.time.LocalDateTime;

@NamedEntityGraph(
        name = "Enrollment.withEventAndStudy",
        attributeNodes = {
                @NamedAttributeNode(value = "event", subgraph = "study")
        },
        subgraphs = @NamedSubgraph(name = "study", attributeNodes = @NamedAttributeNode("study"))
)
@Entity
@Getter @Setter @EqualsAndHashCode(of = "id")
public class Enrollment {

    @Id @GeneratedValue
    private Long id;

    @ManyToOne
    private Event event;

    @ManyToOne
    private Account account;

    private LocalDateTime enrolledAt;

    private boolean accepted;

    private boolean attended;
}
