package com.obedera.playlist.service;

import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.stereotype.Service;

import com.obedera.playlist.dto.UsuarioDTO;
import com.obedera.playlist.model.Usuario;
import com.obedera.playlist.repository.UsuarioRepository;
import com.obedera.playlist.security.CredenciaisDTO;
import com.obedera.playlist.security.CredenciaisRespDTO;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Service
public class UsuarioService {

	@Autowired
	private UsuarioRepository usuarioRepository;

	public UsuarioDTO addUsuario(UsuarioDTO item) {
		Usuario usuarioBd = new Usuario();
		usuarioBd.setAdmin(item.isAdmin());
		usuarioBd.setUsername(item.getUsername());
		usuarioBd.setSenha(item.getSenha());
		usuarioRepository.save(usuarioBd);
		return item;
	}

	public CredenciaisRespDTO login(CredenciaisDTO user) {

		CredenciaisRespDTO respUser = new CredenciaisRespDTO();
		Usuario usuarioBd = usuarioRepository.findByUsername(user.getUsername()).get();
		if (Objects.equals(usuarioBd.getSenha(), user.getPassword())) {
			String token = getJWTToken(usuarioBd.getUsername(), usuarioBd.getId());
			respUser.setToken(token);
			respUser.setPassword(usuarioBd.getSenha());
			respUser.setUsername(usuarioBd.getUsername());
			return respUser;
		} else {
			return new CredenciaisRespDTO();
		}

	}

	private String getJWTToken(String username, long id) {
		String secretKey = "318ffddb8a7dfa5fa53b36742c8d047ed6fbb2d856fb106bf4ef7e8f36aa35c6";
		List<GrantedAuthority> grantedAuthorities = AuthorityUtils.commaSeparatedStringToAuthorityList("ROLE_USER");

		String token = Jwts.builder().setId(Long.toString(id)).setSubject(username)
				.claim("authorities",
						grantedAuthorities.stream().map(GrantedAuthority::getAuthority).collect(Collectors.toList()))
				.setIssuedAt(new Date(System.currentTimeMillis()))
				.setExpiration(new Date(System.currentTimeMillis() + 600000))
				.signWith(SignatureAlgorithm.HS512, secretKey.getBytes()).compact();

		return "Bearer " + token;
	}
}
