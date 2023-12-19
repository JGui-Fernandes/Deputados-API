package com.example.ifsul.produtosBanco.controller;

import java.util.ArrayList;
import java.util.List;

import com.example.ifsul.produtosBanco.entities.Deputado;
import com.example.ifsul.produtosBanco.entities.Evento;
import com.example.ifsul.produtosBanco.services.ApiService;
import com.example.ifsul.produtosBanco.services.DeputadoService;
import com.example.ifsul.produtosBanco.services.EventosService;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.google.gson.Gson;

@RestController
public class ApiController {
	
	@Autowired
	private ApiService apiService;
	@Autowired
	private DeputadoService depService;

	@Autowired
	private EventosService eventosService;
	
	@GetMapping(value = "/data",  produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<String> getDataDeputado()  {
		String data = apiService.getDataDeputados();
		return ResponseEntity.ok(data);
	}
	
	@GetMapping("/clone")
	public List<Deputado> clone() {
		cloneEventos();
		try{
			String data = apiService.getDataDeputados();

			JSONObject jsnobject = new JSONObject(data);
			JSONArray jsonArray = jsnobject.getJSONArray("dados");
			ArrayList<Object> listdata = new ArrayList<Object>();

			System.out.println(jsonArray);
			for(int i = 0; i< 100; i++) {
				Deputado dep = new Gson().fromJson(jsonArray.get(i).toString(), Deputado.class);
	        	depService.criar(dep);
			}
		} catch( Exception ex){
			System.out.println("Não deu");
		}
		return depService.listar();
	}

	@GetMapping(value = "/dataEvento",  produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<String> getDataEvento()  {
		String data = apiService.getDataEventos();
		return ResponseEntity.ok(data);
	}

	public void cloneEventos(){
		String data = apiService.getDataEventos();

		JSONObject jsnobject = new JSONObject(data);
		JSONArray jsonArray = jsnobject.getJSONArray("dados");
		ArrayList<Object> listdata = new ArrayList<Object>();

		for(int i = 0; i< jsonArray.length(); i++) {
			Evento evento = new Gson().fromJson(jsonArray.get(i).toString(), Evento.class);
			eventosService.criar(evento);
		}
	}
	
}
