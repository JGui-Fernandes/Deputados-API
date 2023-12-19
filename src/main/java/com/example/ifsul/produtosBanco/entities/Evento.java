package com.example.ifsul.produtosBanco.entities;

import com.example.ifsul.produtosBanco.dto.evento.CadastrarEventoRequest;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Evento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String uri;
    private String dataHoraInicio;
    private String situacao;
    private String descricaoTipo;
    @Column(columnDefinition = "LONGTEXT")
    private String descricao;

    @JsonIgnore
    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinTable(
            name = "deputado_eventos",
            joinColumns = @JoinColumn(name = "deputado_id"),
            inverseJoinColumns = @JoinColumn(name = "eventos_id"))
    private List<Deputado> deputados;

    public Evento(String descricao, String data, String cidade, String situacao, String uri) {
        this.descricao = descricao;
        this.dataHoraInicio = data;
        this.descricaoTipo = cidade;
        this.situacao = situacao;
        this.uri = uri;
    }

    public Evento(CadastrarEventoRequest dados){
        this.descricao = dados.descricao();
        this.dataHoraInicio = dados.data();
        this.situacao = dados.situacao();
        this.descricaoTipo = dados.descricaoTipo();
        this.uri = dados.uri();
    }
}
