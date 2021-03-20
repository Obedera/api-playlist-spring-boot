package com.obedera.playlist.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.obedera.playlist.dto.UsuarioDTO;
import com.obedera.playlist.security.CredenciaisDTO;
import com.obedera.playlist.security.CredenciaisRespDTO;
import com.obedera.playlist.service.UsuarioService;

@RestController
@RequestMapping("/api/v1/usuario")
public class UsuarioController {
	
	@Autowired
	private UsuarioService usuarioService;
	
	
	@PostMapping
	public ResponseEntity<UsuarioDTO> add(@RequestBody UsuarioDTO user){
		return ResponseEntity.ok(usuarioService.addUsuario(user));
	}
	
	@PostMapping(path = "/login")
	public ResponseEntity<CredenciaisRespDTO> add(@RequestBody CredenciaisDTO user){
		return ResponseEntity.ok(usuarioService.login(user));
	}
}
