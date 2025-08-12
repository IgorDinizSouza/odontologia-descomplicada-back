package com.odontologia.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class CadastroUsuarioDTO {
    @NotBlank(message = "Nome completo é obrigatório")
    @Size(max = 100, message = "Nome completo deve ter no máximo 100 caracteres")
    private String nomeCompleto;
    
    @Email(message = "Email deve ser válido")
    @NotBlank(message = "Email é obrigatório")
    @Size(max = 100, message = "Email deve ter no máximo 100 caracteres")
    private String email;
    
    @NotBlank(message = "Telefone é obrigatório")
    @Size(max = 15, message = "Telefone deve ter no máximo 15 caracteres")
    private String telefone;
    
    @NotBlank(message = "CPF é obrigatório")
    @Size(max = 14, message = "CPF deve ter no máximo 14 caracteres")
    private String cpf;
    
    @Size(max = 20, message = "CRO deve ter no máximo 20 caracteres")
    private String cro;
    
    @Size(max = 100, message = "Especialidade deve ter no máximo 100 caracteres")
    private String especialidade;
    
    @NotBlank(message = "Usuário é obrigatório")
    @Size(max = 50, message = "Usuário deve ter no máximo 50 caracteres")
    private String usuario;
    
    @NotBlank(message = "Senha é obrigatória")
    @Size(min = 6, message = "Senha deve ter no mínimo 6 caracteres")
    private String senha;
    
    private String consultorioId;
}
