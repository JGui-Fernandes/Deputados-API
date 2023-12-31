package com.example.ifsul.produtosBanco.dto.deputado;

import com.example.ifsul.produtosBanco.entities.Deputado;

public record ListarDeputadosResponse(
        Long id,
        String nome,
        String urlFoto,
        String siglaPartido,
        String siglaUf
) {

    public ListarDeputadosResponse(Deputado deputado) {
        this(deputado.getId(), deputado.getNome(), deputado.getUrlFoto(), deputado.getSiglaPartido(), deputado.getSiglaUf());
    }
}
