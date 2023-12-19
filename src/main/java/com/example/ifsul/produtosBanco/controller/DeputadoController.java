package com.example.ifsul.produtosBanco.controller;

import com.example.ifsul.produtosBanco.dto.deputado.VincularEventoRequest;
import com.example.ifsul.produtosBanco.dto.evento.ListarEventosResponse;
import com.example.ifsul.produtosBanco.dto.deputado.CadastraDeputadoRequest;
import com.example.ifsul.produtosBanco.dto.deputado.DetalharDeputadoResponse;
import com.example.ifsul.produtosBanco.dto.deputado.ListarDeputadosResponse;
import com.example.ifsul.produtosBanco.dto.ReturnResponse;
import com.example.ifsul.produtosBanco.services.DeputadoService;
import com.example.ifsul.produtosBanco.services.EventosService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;

import java.util.List;

@Controller
@RequestMapping
public class DeputadoController {

    @Autowired
    private DeputadoService deputadoService;

    @Autowired
    private EventosService eventosService;

    @PostMapping("/cadastrar")
    @Transactional
    public ResponseEntity<ReturnResponse> cadastraDeputado(@RequestBody CadastraDeputadoRequest dados){
        var response = deputadoService.cadastraDeputado(dados);
        return ResponseEntity.status(response.status()).body(new ReturnResponse(response));
    }

    @GetMapping
    public String listarDeputadosView(Model model) {
        List<ListarDeputadosResponse> deputados = deputadoService.listarDeputados();
        model.addAttribute("deputados", deputados);
        return "listarDeputados"; // Thymeleaf template name without the extension
    }

    @GetMapping("/{id}")
    public String detalharDeputado(@PathVariable Long id, Model model){
        var response = deputadoService.detalharDeputado(id);
        model.addAttribute("deputado", response);
        return "paginaDeputado";
    }

    @GetMapping("/eventos/{id}")
    public String listarEventoPorDeputado(@PathVariable Long id, Model model){
        var response = deputadoService.listarEventos(id);
        model.addAttribute("eventos", response);
        return "listarEventos";
    }

    @PostMapping("/eventos/{id}")
    @Transactional
    public RedirectView  vincularEvento(@PathVariable Long id, @RequestParam("eventoId")Long idEvento){
        var response = deputadoService.adicionarEvento(id, idEvento);
        RedirectView redirectView = new RedirectView();
        redirectView.setUrl("http://localhost:8080/");
        return redirectView;
    }

    @GetMapping("/listarEventos/{id}")
    public String listarEventos(@PathVariable Long id, Model model){
        var eventos = eventosService.listarEventos();
        var deputado = deputadoService.detalharDeputado(id);
        model.addAttribute("deputado", deputado);
        model.addAttribute("eventos", eventos);
        return "vincularEvento";
    }
}
