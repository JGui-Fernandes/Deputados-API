package com.example.ifsul.produtosBanco.repositories;

import com.example.ifsul.produtosBanco.entities.Evento;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EventosRepository extends JpaRepository<Evento, Long> {
}
