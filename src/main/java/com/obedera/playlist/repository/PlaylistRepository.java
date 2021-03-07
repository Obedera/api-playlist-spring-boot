package com.obedera.playlist.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.obedera.playlist.model.Playlist;

@Repository
public interface PlaylistRepository extends JpaRepository<Playlist, Long> {
	
}
