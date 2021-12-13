package co.edu.unbosque.lagenerica.ClientesBack.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


import co.edu.unbosque.lagenerica.ClientesBack.model.Clientes;
import co.edu.unbosque.lagenerica.ClientesBack.repository.ClientesRepository;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/api")

public class ClientesController {
	@Autowired
	ClientesRepository clientesRepository;
	
	@PostMapping("/db_clientes")
	public ResponseEntity<Clientes> createCliente(@RequestBody Clientes clientes) {
		try {
			Clientes _clients = clientesRepository.save(new Clientes(clientes.getCedulaCliente(), clientes.getDireccionCliente(), clientes.getEmailCliente(), clientes.getNombreCliente(), clientes.getTelefonoCliente()));
			return new ResponseEntity<>(_clients, HttpStatus.CREATED);
			}catch (Exception e) {
				return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
			}
	}
	
	@GetMapping("/db_clientes")
	  public ResponseEntity<List<Clientes>> getAllProducto(@RequestParam(required = false) String cedulaCliente) {
		  try {
			    List<Clientes> clients = new ArrayList<Clientes>();

			    if (cedulaCliente == null)
			      clientesRepository.findAll().forEach(clients::add);
			    else
			      //getClientesBycedulaCliente(cedulaCliente);
			      //Optional<Clientes> clientesData = clientesRepository.findBycedulaCliente(cedulaCliente);
			      //clientesRepository.findBycedulaCliente(cedulaCliente).forEach(clients::add);

			    if (clients.isEmpty()) {
			      return new ResponseEntity<>(HttpStatus.NO_CONTENT);
			    }

			    return new ResponseEntity<>(clients, HttpStatus.OK);
			  } catch (Exception e) {
			    return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
			  }
	  }
	
	  @DeleteMapping("/db_clientes")
	  public ResponseEntity<HttpStatus> deleteAllProducto() {
		  try {
			    clientesRepository.deleteAll();
			    return new ResponseEntity<>(HttpStatus.NO_CONTENT);
			  } catch (Exception e) {
			    return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
			  }
	  }
	  
	  @DeleteMapping("/db_clientes/{cedulaCliente}")
	  public ResponseEntity<HttpStatus> deleteBycedulaCliente(@PathVariable("cedulaCliente") int cedulaCliente) {
		  try {
			    clientesRepository.deleteBycedulaCliente(cedulaCliente);
			    return new ResponseEntity<>(HttpStatus.NO_CONTENT);
			  } catch (Exception e) {
			    return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
			  }
	  }
	  
	  @PutMapping("/db_clientes/{cedulaCliente}")
	  public ResponseEntity<Clientes> updateClientes(@PathVariable("cedulaCliente") int cedulaCliente, @RequestBody Clientes clientes) {
		  Optional<Clientes> clientesData = clientesRepository.findBycedulaCliente(cedulaCliente);

		  if (clientesData.isPresent()) {
		    Clientes _clients = clientesData.get();
		    _clients.setCedulaCliente(cedulaCliente);
		    _clients.setDireccionCliente(clientes.getDireccionCliente());
		    _clients.setEmailCliente(clientes.getEmailCliente());
		    _clients.setNombreCliente(clientes.getNombreCliente());
		    _clients.setTelefonoCliente(clientes.getTelefonoCliente());
		    		    	       
		    return new ResponseEntity<>(clientesRepository.save(_clients), HttpStatus.OK);
		  } else {
		    return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		  }
	  }
	  
	  @GetMapping("/db_clientes/{cedulaCliente}")
	  public ResponseEntity<Clientes> getClientesBycedulaCliente(@PathVariable("cedulaCliente") int cedulaCliente) {
		  Optional<Clientes> clientesData = clientesRepository.findBycedulaCliente(cedulaCliente);
//		  Optional<Clientes> clientesData = clientesRepository.findSomenombreCliente(cedulaCliente));
		  if (clientesData.isPresent()) {
		    return new ResponseEntity<>(clientesData.get(), HttpStatus.OK);
		  } else {
		    return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		  }
	  }
}

