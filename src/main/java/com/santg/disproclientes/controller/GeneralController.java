package com.santg.disproclientes.controller;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import com.santg.disproclientes.entity.Cliente;
import com.santg.disproclientes.entity.Comentario;
import com.santg.disproclientes.entity.Vendedor;
import com.santg.disproclientes.repository.ClienteRepository;
import com.santg.disproclientes.repository.ComentarioRepository;
import com.santg.disproclientes.repository.VendedorRepository;
import com.santg.disproclientes.security.UserDetailsImpl;

@Controller
@RequestMapping("/")
public class GeneralController {
	
	@Autowired
	private ComentarioRepository comentarioRepository;
	
	@Autowired
	private VendedorRepository vendedorRepository;
	
	@Autowired
	private ClienteRepository clienteRepository;
	
	@GetMapping("/")
	public String home(Model model) {
		
		List<Cliente> clientes = clienteRepository.findAll();
		
		model.addAttribute("clientes", clientes);
		
		return "home";
	}

	@GetMapping("/detalles")
	public String detallesCliente(@RequestParam("clienteId") int id, Model model) {
		
		// get Cliente from repository using id
		Cliente cliente = clienteRepository.getOne(id);
		
		// get the current Vendedor
		UserDetailsImpl userDetails= (UserDetailsImpl) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		Vendedor vendedor = userDetails.getVendedor();
		
		model.addAttribute("vendedorActual", vendedor);
		
		
		// get Comentarios list from that Cliente
		List<Comentario> comentarios = cliente.getComentarios();
		
		Collections.sort(comentarios);
		
		// add the list to the model
		model.addAttribute("comentarios", comentarios);
		
		// add the Cliente to the model
		model.addAttribute("cliente", cliente);
		
		// add a new comment to use in the new comment form
		model.addAttribute("comentario", new Comentario());
		
		// get all the Vendedores
		List<Vendedor> vendedores = vendedorRepository.findAll();
		
		// add the Vendedores list to the model to use in the new comment form
		model.addAttribute("vendedores", vendedores);
		
		return "detalle-cliente";
	}
	
	@GetMapping("/agregarCliente")
	public String agregarCliente(Model model) {
		
		Cliente cliente = new Cliente();
		
		model.addAttribute("cliente", cliente);
		
		return "agregar-cliente-form";
	}
	
	@PostMapping("/guardarCliente")
	public String guardarCliente(@ModelAttribute("cliente") Cliente cliente) {
		
		clienteRepository.save(cliente);
		
		return "redirect:/";
	}
	
	@PostMapping("/guardarComentario")
	public String guardarComentario(@ModelAttribute("comentario") Comentario comentario, @RequestParam("clienteId") int clienteId) {
		
		Cliente cliente = clienteRepository.getOne(clienteId);
		
		List<Comentario> comentarios = cliente.getComentarios();
		
		if( comentarios == null ) {
			
			comentarios = new ArrayList<>();			
		}
		
		TimeZone.setDefault(TimeZone.getTimeZone("America/Argentina/Buenos_Aires"));
		comentario.setTimestamp(new Date());
		
		comentarios.add(comentario);
		
		comentarioRepository.save(comentario);
		
		return "redirect:/detalles?clienteId=" + clienteId;
	}
	
	@GetMapping("/borrarComentario")
	public String borrarComentario(@RequestParam("comentarioId") int comentId) {
		
		Comentario comentario = comentarioRepository.getOne(comentId);
		
		int clienteId = comentario.getCliente().getId();
		
		comentarioRepository.delete(comentario);
		
		return "redirect:/detalles?clienteId=" + clienteId;
	}
	
	@GetMapping("/buscarCliente")
	public String buscarCliente(@RequestParam("nombreCliente") String nombreCliente, Model model) {
		
		List<Cliente> clientes = clienteRepository.findByNombreContainingIgnoreCase(nombreCliente);
		
		model.addAttribute("clientes", clientes);
		
		return "home";
	}
}
