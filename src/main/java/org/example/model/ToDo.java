package org.example.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.Date;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ToDo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String description;

    @Temporal(TemporalType.DATE)
    private Date dueDate;

    @Enumerated(EnumType.STRING)
    private State state;
}
