package com.example.ifsul.produtosBanco.services;

import com.example.ifsul.produtosBanco.dto.evento.CadastrarEventoRequest;
import com.example.ifsul.produtosBanco.dto.MensagemResponse;
import com.example.ifsul.produtosBanco.dto.ReturnResponse;
import com.example.ifsul.produtosBanco.dto.evento.EditarEventoRequest;
import com.example.ifsul.produtosBanco.dto.evento.ListarEventosResponse;
import com.example.ifsul.produtosBanco.entities.Evento;
import com.example.ifsul.produtosBanco.repositories.DeputadoRepository;
import com.example.ifsul.produtosBanco.repositories.EventosRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class EventosService {

    @Autowired
    private EventosRepository eventosRepository;

    @Autowired
    private DeputadoRepository deputadoRepository;

    public Evento criar(Evento deputado) {
        return eventosRepository.save(deputado);
    }

    public ReturnResponse criaEvento(CadastrarEventoRequest dados){
        Evento eventos = new Evento(dados);
        eventosRepository.save(eventos);
        return new ReturnResponse("Evento criado!");
    }

    public MensagemResponse cadastraEvento(CadastrarEventoRequest dados) {
        Evento evento = new Evento(dados);
        eventosRepository.save(evento);

        return new MensagemResponse(201, "Evento criado com sucesso!");
    }

    public List<ListarEventosResponse> listarEventos() {
        var list = eventosRepository.findAll();
        return list.stream()
                .map(ListarEventosResponse::new)
                .collect(Collectors.toList());
    }

    public MensagemResponse editarEvento(Long id, EditarEventoRequest dados){
        var evento = eventosRepository.getReferenceById(id);
        boolean mudou = false;

        if (dados.uri() != null && !dados.uri().equals(evento.getUri())) {
            evento.setUri(dados.uri());
            mudou = true;
        }

        if (dados.situacao() != null && !dados.situacao().equals(evento.getSituacao())) {
            evento.setSituacao(dados.situacao());
            mudou = true;
        }

        if (dados.descricaoTipo() != null && !dados.descricaoTipo().equals(evento.getDescricaoTipo())) {
            evento.setDescricaoTipo(dados.descricaoTipo());
            mudou = true;
        }

        if (dados.descricao() != null && !dados.descricao().equals(evento.getDescricao())) {
            evento.setDescricao(dados.descricao());
            mudou = true;
        }

        eventosRepository.save(evento);
        if(mudou){
            return new MensagemResponse(200, "Evento editado com sucesso!");
        }
        return new MensagemResponse(200, "Nenhuma informação foi alterada");
    }

    public MensagemResponse deletarEvento(Long id){
        var evento = eventosRepository.getReferenceById(id);
        eventosRepository.delete(evento);

        return new MensagemResponse(202, "Evento deletado com sucesso!");
    }
}
