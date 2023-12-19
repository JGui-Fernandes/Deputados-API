package com.example.ifsul.produtosBanco.dto.evento;

import com.example.ifsul.produtosBanco.entities.Evento;

public record CadastrarEventoRequest(
        String descricao,
        String data,
        String descricaoTipo,
        String uri,
        String situacao

) {

    public CadastrarEventoRequest(Evento evento){
        this(evento.getDescricao(), evento.getDataHoraInicio(), evento.getDescricaoTipo(), evento.getUri(), evento.getSituacao());
    }
}
