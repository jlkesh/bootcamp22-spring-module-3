package dev.jlkeesh.springadvanced.task;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
public class Task {
    private String id;
    private String name;
    private String description;
    private String label;
    private LocalDateTime createdAt;
}
