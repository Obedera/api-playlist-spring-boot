package com.obedera.playlist.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.obedera.playlist.dto.PlaylistDTO;
import com.obedera.playlist.service.PlaylistService;

@RestController
@RequestMapping("/playlist")
public class PlaylistController {
	
	
	@Autowired
	private PlaylistService playlistService;
	
	
	@GetMapping
	public ResponseEntity<List<PlaylistDTO>> listar(){
		if(playlistService.listar().isEmpty()) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok(playlistService.listar());
	}
	
	
	@PostMapping
	public ResponseEntity<PlaylistDTO> add(@RequestBody PlaylistDTO playlist){
		return ResponseEntity.ok(playlistService.addPlaylist(playlist));		
	}
	
	
}
