package com.santg.disproclientes;

import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import com.santg.disproclientes.entity.Vendedor;
import com.santg.disproclientes.repository.VendedorRepository;

@SpringBootApplication
public class DispromedClientesApplication {

	public static void main(String[] args) {
		SpringApplication.run(DispromedClientesApplication.class, args);
	}
	
	
	@Bean
	ApplicationRunner applicationRunner(VendedorRepository vendedorRepository) {
		
		return args -> {
			
			Vendedor cuentaJefe = vendedorRepository.findByNombre("jefe");
			
			if (cuentaJefe == null ) {
				vendedorRepository.save(new Vendedor("jefe", "$2a$10$70cGClabHK6odYxKCoM1fe0M9GqLEVVyLUk1NwghUFowdLuOo.Uia", "JEFE,VENDEDOR"));
			}
			
			Vendedor cuentaVendedor = vendedorRepository.findByNombre("vendedor");
			
			if (cuentaVendedor == null ) {
				vendedorRepository.save(new Vendedor("vendedor", "$2a$10$70cGClabHK6odYxKCoM1fe0M9GqLEVVyLUk1NwghUFowdLuOo.Uia", "VENDEDOR"));
			}
			

		};
	}

}
