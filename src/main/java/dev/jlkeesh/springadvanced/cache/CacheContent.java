package dev.jlkeesh.springadvanced.cache;


import lombok.*;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class CacheContent<T> {
    private LocalDateTime expiresAt;
    private T body;
}
