package com.santg.disproclientes.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.santg.disproclientes.entity.Vendedor;
import com.santg.disproclientes.repository.VendedorRepository;

public class MyUserDetailsService implements UserDetailsService {

	@Autowired
	private VendedorRepository vendedorRepository;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		Vendedor vendedor = vendedorRepository.findByNombre(username);
		UserDetailsImpl userDetails = new UserDetailsImpl(vendedor);
		
		return userDetails;
	}

}
