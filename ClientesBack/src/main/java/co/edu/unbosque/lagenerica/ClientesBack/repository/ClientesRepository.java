package co.edu.unbosque.lagenerica.ClientesBack.repository;

import java.util.List;
import java.util.Optional;


import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import co.edu.unbosque.lagenerica.ClientesBack.model.*;

public interface ClientesRepository extends MongoRepository<Clientes, String>{
	@Query(value="{ 'cedulaCliente' : 0 }", fields="{ 'nombreClente' : 1}")
	List<Clientes> findBycedulaCliente(String cedulaCliente);

	//Optional<Clientes> findBycedulaCliente(int cedulaCliente).findSome(nombreCliente);
	//Optional<Clientes> findSomenombreCliente(String nombreCliente);
	Optional<Clientes> findBycedulaCliente(int cedulaCliente);

	void deleteBycedulaCliente(int cedulaCliente);
}