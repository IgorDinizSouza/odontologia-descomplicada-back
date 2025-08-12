package com.odontologia.service;

import com.odontologia.dto.*;
import com.odontologia.model.Consultorio;
import com.odontologia.model.Usuario;
import com.odontologia.repository.ConsultorioRepository;
import com.odontologia.repository.UsuarioRepository;
import com.odontologia.security.JwtTokenProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class AuthService {
    
    private final AuthenticationManager authenticationManager;
    private final JwtTokenProvider jwtTokenProvider;
    private final UsuarioRepository usuarioRepository;
    private final ConsultorioRepository consultorioRepository;
    private final PasswordEncoder passwordEncoder;
    private final EmailService emailService;
    
    public LoginResponseDTO login(LoginDTO loginDTO) {
        Authentication authentication = authenticationManager.authenticate(
            new UsernamePasswordAuthenticationToken(loginDTO.getUsuario(), loginDTO.getSenha())
        );
        
        Usuario usuario = (Usuario) authentication.getPrincipal();
        String token = jwtTokenProvider.generateToken(usuario);
        
        LoginResponseDTO response = new LoginResponseDTO();
        response.setToken(token);
        response.setUsuario(new LoginResponseDTO.UsuarioResponseDTO(usuario));
        
        return response;
    }
    
    @Transactional
    public void cadastrarUsuario(CadastroUsuarioDTO cadastroDTO) {
        // Verificar se usuário já existe
        if (usuarioRepository.existsByUsuario(cadastroDTO.getUsuario())) {
            throw new RuntimeException("Usuário já existe");
        }
        
        if (usuarioRepository.existsByEmail(cadastroDTO.getEmail())) {
            throw new RuntimeException("Email já está em uso");
        }
        
        if (usuarioRepository.existsByCpf(cadastroDTO.getCpf())) {
            throw new RuntimeException("CPF já está em uso");
        }
        
        Usuario usuario = new Usuario();
        usuario.setNomeCompleto(cadastroDTO.getNomeCompleto());
        usuario.setEmail(cadastroDTO.getEmail());
        usuario.setTelefone(cadastroDTO.getTelefone());
        usuario.setCpf(cadastroDTO.getCpf());
        usuario.setCro(cadastroDTO.getCro());
        usuario.setEspecialidade(cadastroDTO.getEspecialidade());
        usuario.setUsuario(cadastroDTO.getUsuario());
        usuario.setSenha(passwordEncoder.encode(cadastroDTO.getSenha()));
        
        if (cadastroDTO.getConsultorioId() != null) {
            Consultorio consultorio = consultorioRepository.findById(cadastroDTO.getConsultorioId())
                .orElseThrow(() -> new RuntimeException("Consultório não encontrado"));
            usuario.setConsultorio(consultorio);
        }
        
        usuarioRepository.save(usuario);
    }
    
    @Transactional
    public void cadastrarConsultorio(CadastroConsultorioDTO cadastroDTO) {
        // Verificar se CNPJ já existe
        if (consultorioRepository.existsByCnpj(cadastroDTO.getCnpj())) {
            throw new RuntimeException("CNPJ já está em uso");
        }
        
        // Criar consultório
        Consultorio consultorio = new Consultorio();
        consultorio.setNomeConsultorio(cadastroDTO.getNomeConsultorio());
        consultorio.setCnpj(cadastroDTO.getCnpj());
        consultorio.setTelefone(cadastroDTO.getTelefone());
        consultorio.setEmail(cadastroDTO.getEmail());
        consultorio.setLogradouro(cadastroDTO.getEndereco().getLogradouro());
        consultorio.setNumero(cadastroDTO.getEndereco().getNumero());
        consultorio.setComplemento(cadastroDTO.getEndereco().getComplemento());
        consultorio.setBairro(cadastroDTO.getEndereco().getBairro());
        consultorio.setCidade(cadastroDTO.getEndereco().getCidade());
        consultorio.setEstado(cadastroDTO.getEndereco().getEstado());
        consultorio.setCep(cadastroDTO.getEndereco().getCep());
        consultorio.setObservacoes(cadastroDTO.getObservacoes());
        
        consultorio = consultorioRepository.save(consultorio);
        
        // Criar usuário responsável
        Usuario responsavel = new Usuario();
        responsavel.setNomeCompleto(cadastroDTO.getResponsavel().getNomeCompleto());
        responsavel.setCpf(cadastroDTO.getResponsavel().getCpf());
        responsavel.setCro(cadastroDTO.getResponsavel().getCro());
        responsavel.setTelefone(cadastroDTO.getResponsavel().getTelefone());
        responsavel.setEmail(cadastroDTO.getResponsavel().getEmail());
        responsavel.setUsuario(cadastroDTO.getResponsavel().getUsuario());
        responsavel.setSenha(passwordEncoder.encode(cadastroDTO.getResponsavel().getSenha()));
        responsavel.setPerfil(Usuario.PerfilUsuario.ADMIN);
        responsavel.setConsultorio(consultorio);
        
        usuarioRepository.save(responsavel);
    }
    
    public void esqueciSenha(EsqueciSenhaDTO esqueciSenhaDTO) {
        Usuario usuario = usuarioRepository.findByEmail(esqueciSenhaDTO.getEmail())
            .orElseThrow(() -> new RuntimeException("Email não encontrado"));
        
        String token = jwtTokenProvider.generatePasswordResetToken(usuario);
        emailService.enviarEmailRecuperacaoSenha(usuario.getEmail(), token);
    }
    
    public void redefinirSenha(RedefinirSenhaDTO redefinirSenhaDTO) {
        String username = jwtTokenProvider.getUsernameFromToken(redefinirSenhaDTO.getToken());
        Usuario usuario = usuarioRepository.findByUsuario(username)
            .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));
        
        usuario.setSenha(passwordEncoder.encode(redefinirSenhaDTO.getNovaSenha()));
        usuarioRepository.save(usuario);
    }
}
