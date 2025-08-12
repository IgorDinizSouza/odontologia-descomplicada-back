package com.odontologia.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "consultorios")
@Data
@EqualsAndHashCode(callSuper = false)
public class Consultorio {
    
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;
    
    @NotBlank
    @Size(max = 100)
    @Column(name = "nome_consultorio", nullable = false)
    private String nomeConsultorio;
    
    @NotBlank
    @Size(max = 18)
    @Column(nullable = false, unique = true)
    private String cnpj;
    
    @NotBlank
    @Size(max = 15)
    @Column(nullable = false)
    private String telefone;
    
    @Email
    @NotBlank
    @Size(max = 100)
    @Column(nullable = false)
    private String email;
    
    @NotBlank
    @Size(max = 200)
    @Column(nullable = false)
    private String logradouro;
    
    @NotBlank
    @Size(max = 10)
    @Column(nullable = false)
    private String numero;
    
    @Size(max = 50)
    private String complemento;
    
    @NotBlank
    @Size(max = 100)
    @Column(nullable = false)
    private String bairro;
    
    @NotBlank
    @Size(max = 100)
    @Column(nullable = false)
    private String cidade;
    
    @NotBlank
    @Size(max = 2)
    @Column(nullable = false)
    private String estado;
    
    @NotBlank
    @Size(max = 9)
    @Column(nullable = false)
    private String cep;
    
    @Column(columnDefinition = "TEXT")
    private String observacoes;
    
    @Column(nullable = false)
    private Boolean ativo = true;
    
    @CreationTimestamp
    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt;
    
    @UpdateTimestamp
    @Column(name = "updated_at")
    private LocalDateTime updatedAt;
    
    @OneToMany(mappedBy = "consultorio", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Usuario> usuarios;
    
    @OneToMany(mappedBy = "consultorio", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Paciente> pacientes;
    
    @OneToMany(mappedBy = "consultorio", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Procedimento> procedimentos;
}
