package com.odontologia.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class LoginDTO {
    @NotBlank(message = "Usuário é obrigatório")
    private String usuario;
    
    @NotBlank(message = "Senha é obrigatória")
    private String senha;
}
