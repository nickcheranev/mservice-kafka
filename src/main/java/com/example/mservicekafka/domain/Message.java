package com.example.mservicekafka.domain;

import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Message {
    // {"id":"1","message":"World"}
    private Long id;
    
    private String message;
}
