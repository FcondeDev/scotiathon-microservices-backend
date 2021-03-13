package com.scotiathon.client.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.scotiathon.client.dto.AuthResponseDTO;
import com.scotiathon.client.dto.ClientDTO;
import com.scotiathon.client.dto.JsonDTO;
import com.scotiathon.client.service.ClientService;

@RestController
public class ClientController {

	@Autowired
	private ClientService clientService;

	@GetMapping("clients/details")
	public ResponseEntity<JsonDTO<ClientDTO>> getClientByScotiaId(@RequestParam("scotiaId") String scotiaId) {
		return new ResponseEntity<>(new JsonDTO<>(clientService.getClientByScotiaId(scotiaId)), HttpStatus.OK);
	}

	@PostMapping("clients/login")
	public ResponseEntity<JsonDTO<AuthResponseDTO>> login(@RequestBody ClientDTO clientDTO,
			@RequestHeader("Authorization") String basicAuth) {
		return new ResponseEntity<>(new JsonDTO<>(clientService.login(clientDTO, basicAuth)), HttpStatus.OK);
	}

}
