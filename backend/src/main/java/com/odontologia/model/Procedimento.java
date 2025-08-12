package com.odontologia.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "procedimentos")
@Data
@EqualsAndHashCode(callSuper = false)
public class Procedimento {
    
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;
    
    @NotBlank
    @Size(max = 100)
    @Column(nullable = false)
    private String nome;
    
    @NotBlank
    @Size(max = 50)
    @Column(nullable = false)
    private String categoria;
    
    @NotNull
    @DecimalMin(value = "0.0", inclusive = false)
    @Column(nullable = false, precision = 10, scale = 2)
    private BigDecimal preco;
    
    @NotNull
    @Column(nullable = false)
    private Integer duracao; // em minutos
    
    @Column(columnDefinition = "TEXT")
    private String descricao;
    
    @Column(name = "materiais_necessarios", columnDefinition = "TEXT")
    private String materiaisNecessarios;
    
    @Column(name = "instrucoes_pre", columnDefinition = "TEXT")
    private String instrucoesPre;
    
    @Column(name = "instrucoes_pos", columnDefinition = "TEXT")
    private String instrucoesPos;
    
    @Column(columnDefinition = "TEXT")
    private String contraindicacoes;
    
    @Column(nullable = false)
    private Boolean ativo = true;
    
    @CreationTimestamp
    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt;
    
    @UpdateTimestamp
    @Column(name = "updated_at")
    private LocalDateTime updatedAt;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "consultorio_id", nullable = false)
    private Consultorio consultorio;
    
    @OneToMany(mappedBy = "procedimento", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Agendamento> agendamentos;
}
