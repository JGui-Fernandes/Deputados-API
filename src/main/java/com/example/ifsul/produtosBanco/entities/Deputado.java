package com.example.ifsul.produtosBanco.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Deputado {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String nome;
	private String siglaPartido;
	private String siglaUf;
	private Long idLegislatura;
	private String urlFoto;
	private String email;
	@JsonIgnore
	@ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
	private List<Evento> eventos;

}
