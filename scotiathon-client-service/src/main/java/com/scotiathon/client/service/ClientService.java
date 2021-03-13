package com.scotiathon.client.service;

import com.scotiathon.client.dto.AuthResponseDTO;
import com.scotiathon.client.dto.ClientDTO;

public interface ClientService {

	public ClientDTO getClientByScotiaId(String scotiaId);

	public AuthResponseDTO login(ClientDTO clientDTO, String basicAuth);

}
