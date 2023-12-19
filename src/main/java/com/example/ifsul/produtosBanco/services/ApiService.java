package com.example.ifsul.produtosBanco.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class ApiService {
	
	@Autowired
	private RestTemplate restTemplate;
	private final String URL_DEPUTADOS = "https://dadosabertos.camara.leg.br/api/v2/deputados?ordem=ASC&ordenarPor=nome";
	private final String URL_EVENTOS = "https://dadosabertos.camara.leg.br/api/v2/eventos?ordem=ASC&ordenarPor=dataHoraInicio";

	public String getDataDeputados() {
		ResponseEntity<String> responseEntity = restTemplate.getForEntity(URL_DEPUTADOS, String.class);
		String responseBody = responseEntity.getBody();

		return responseBody;

	}
	public String getDataEventos() {
		ResponseEntity<String> responseEntity = restTemplate.getForEntity(URL_EVENTOS, String.class);
		String responseBody = responseEntity.getBody();

		return responseBody;

	}

}
