package com.odontologia.dto;

import com.odontologia.model.Usuario;
import lombok.Data;

@Data
public class LoginResponseDTO {
    private String token;
    private UsuarioResponseDTO usuario;
    
    @Data
    public static class UsuarioResponseDTO {
        private String id;
        private String nome;
        private String email;
        private String consultorioId;
        private Usuario.PerfilUsuario perfil;
        
        public UsuarioResponseDTO(Usuario usuario) {
            this.id = usuario.getId();
            this.nome = usuario.getNomeCompleto();
            this.email = usuario.getEmail();
            this.consultorioId = usuario.getConsultorio().getId();
            this.perfil = usuario.getPerfil();
        }
    }
}
