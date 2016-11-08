package br.com.sb.service;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Service;

import br.com.sb.model.Cliente;

@Service
public class ClienteService {
	//simular BD
	private Map<Integer, Cliente> clientes = new HashMap<>();
	private Integer nextNumber=1;
	
	//Negócio
	public Cliente constroiCliente(Cliente cliente){
			
		cliente.setId(nextNumber);
		nextNumber++;
		
		clientes.put(cliente.getId(), cliente);
		
		return cliente;
	}
	
	//vamos criar o método busca clientes...
	public Collection<Cliente> buscarTodos(){
		return clientes.values();
	}
	
	//vamos criar um método para remover clientes...
	public void excluir(Cliente cliente){		
		clientes.remove(cliente.getId());
	}
	
	public Cliente buscaPorId(Integer id){
		return clientes.get(id);
	}
	
	//vamos criar um método para alterar
	public Cliente alterar(Cliente cliente){
		clientes.put(cliente.getId(), cliente);
		return cliente;
	}
}
