package dev.jlkeesh.springadvanced.entities;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.domain.AuditorAware;
import org.springframework.data.jpa.domain.AbstractAuditable;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@EntityListeners(AuditingEntityListener.class)
@Builder
public class Vocabulary {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    @Column(nullable = false)
    private String word;

    @Column(length = 1000, nullable = false)
    private String definition;

    @Column(length = 1000)
    private String examples;

    @CreatedDate
    @Column(nullable = false)
    private LocalDateTime createdAt;

    @CreatedBy
    @Column(nullable = false)
    private String userID;

    @Override
    public String toString() {
        return "Vocabulary{" +
                "id='" + id + '\'' +
                ", words='" + word + '\'' +
                ", definition='" + definition + '\'' +
                ", examples='" + examples + '\'' +
                '}';
    }
}

