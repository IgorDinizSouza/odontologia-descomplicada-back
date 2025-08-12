package com.odontologia.controller;

import com.odontologia.dto.*;
import com.odontologia.service.AuthService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
@CrossOrigin(origins = {"http://localhost:3000"})
public class AuthController {
    
    private final AuthService authService;
    
    @PostMapping("/login")
    public ResponseEntity<LoginResponseDTO> login(@Valid @RequestBody LoginDTO loginDTO) {
        LoginResponseDTO response = authService.login(loginDTO);
        return ResponseEntity.ok(response);
    }
    
    @PostMapping("/cadastro-usuario")
    public ResponseEntity<String> cadastrarUsuario(@Valid @RequestBody CadastroUsuarioDTO cadastroDTO) {
        authService.cadastrarUsuario(cadastroDTO);
        return ResponseEntity.ok("Usuário cadastrado com sucesso!");
    }
    
    @PostMapping("/novo-consultorio")
    public ResponseEntity<String> cadastrarConsultorio(@Valid @RequestBody CadastroConsultorioDTO cadastroDTO) {
        authService.cadastrarConsultorio(cadastroDTO);
        return ResponseEntity.ok("Consultório cadastrado com sucesso!");
    }
    
    @PostMapping("/esqueci-senha")
    public ResponseEntity<String> esqueciSenha(@Valid @RequestBody EsqueciSenhaDTO esqueciSenhaDTO) {
        authService.esqueciSenha(esqueciSenhaDTO);
        return ResponseEntity.ok("Email de recuperação enviado!");
    }
    
    @PostMapping("/redefinir-senha")
    public ResponseEntity<String> redefinirSenha(@Valid @RequestBody RedefinirSenhaDTO redefinirSenhaDTO) {
        authService.redefinirSenha(redefinirSenhaDTO);
        return ResponseEntity.ok("Senha redefinida com sucesso!");
    }
}
