package com.api.cadastro.usuario.personapi.dto.response;

import lombok.Builder;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

@Data
@Builder
public class MessageResponseDTO {
    private String message;
}
