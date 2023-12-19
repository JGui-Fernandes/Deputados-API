package com.example.ifsul.produtosBanco.dto.evento;

import com.example.ifsul.produtosBanco.entities.Evento;

public record ListarEventosResponse(
        Long id,
        String descricao,
        String descricaoTipo,
        String data
) {
    public ListarEventosResponse (Evento eventos){
        this(eventos.getId(), eventos.getDescricao(), eventos.getDescricaoTipo(), eventos.getDataHoraInicio());
    }
}
