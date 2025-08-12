package com.odontologia.dto;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class CadastroConsultorioDTO {
    // Dados do consultório
    @NotBlank(message = "Nome do consultório é obrigatório")
    @Size(max = 100, message = "Nome do consultório deve ter no máximo 100 caracteres")
    private String nomeConsultorio;
    
    @NotBlank(message = "CNPJ é obrigatório")
    @Size(max = 18, message = "CNPJ deve ter no máximo 18 caracteres")
    private String cnpj;
    
    @NotBlank(message = "Telefone é obrigatório")
    @Size(max = 15, message = "Telefone deve ter no máximo 15 caracteres")
    private String telefone;
    
    @Email(message = "Email deve ser válido")
    @NotBlank(message = "Email é obrigatório")
    @Size(max = 100, message = "Email deve ter no máximo 100 caracteres")
    private String email;
    
    @Valid
    @NotNull(message = "Endereço é obrigatório")
    private EnderecoDTO endereco;
    
    @Size(max = 500, message = "Observações devem ter no máximo 500 caracteres")
    private String observacoes;
    
    // Dados do responsável
    @Valid
    @NotNull(message = "Dados do responsável são obrigatórios")
    private ResponsavelDTO responsavel;
    
    @Data
    public static class EnderecoDTO {
        @NotBlank(message = "Logradouro é obrigatório")
        @Size(max = 200, message = "Logradouro deve ter no máximo 200 caracteres")
        private String logradouro;
        
        @NotBlank(message = "Número é obrigatório")
        @Size(max = 10, message = "Número deve ter no máximo 10 caracteres")
        private String numero;
        
        @Size(max = 50, message = "Complemento deve ter no máximo 50 caracteres")
        private String complemento;
        
        @NotBlank(message = "Bairro é obrigatório")
        @Size(max = 100, message = "Bairro deve ter no máximo 100 caracteres")
        private String bairro;
        
        @NotBlank(message = "Cidade é obrigatória")
        @Size(max = 100, message = "Cidade deve ter no máximo 100 caracteres")
        private String cidade;
        
        @NotBlank(message = "Estado é obrigatório")
        @Size(max = 2, message = "Estado deve ter no máximo 2 caracteres")
        private String estado;
        
        @NotBlank(message = "CEP é obrigatório")
        @Size(max = 9, message = "CEP deve ter no máximo 9 caracteres")
        private String cep;
    }
    
    @Data
    public static class ResponsavelDTO {
        @NotBlank(message = "Nome completo é obrigatório")
        @Size(max = 100, message = "Nome completo deve ter no máximo 100 caracteres")
        private String nomeCompleto;
        
        @NotBlank(message = "CPF é obrigatório")
        @Size(max = 14, message = "CPF deve ter no máximo 14 caracteres")
        private String cpf;
        
        @NotBlank(message = "CRO é obrigatório")
        @Size(max = 20, message = "CRO deve ter no máximo 20 caracteres")
        private String cro;
        
        @NotBlank(message = "Telefone é obrigatório")
        @Size(max = 15, message = "Telefone deve ter no máximo 15 caracteres")
        private String telefone;
        
        @Email(message = "Email deve ser válido")
        @NotBlank(message = "Email é obrigatório")
        @Size(max = 100, message = "Email deve ter no máximo 100 caracteres")
        private String email;
        
        @NotBlank(message = "Usuário é obrigatório")
        @Size(max = 50, message = "Usuário deve ter no máximo 50 caracteres")
        private String usuario;
        
        @NotBlank(message = "Senha é obrigatória")
        @Size(min = 6, message = "Senha deve ter no mínimo 6 caracteres")
        private String senha;
    }
}
