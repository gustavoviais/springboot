package br.com.sb.service;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.sb.model.Cliente;
import br.com.sb.repository.ClienteRepository;

@Service
public class ClienteService {	
	//vamos criar a persistencia com o BD
	@Autowired
	ClienteRepository clienteRepository;
	
	//Negócio
	public Cliente constroiCliente(Cliente cliente){
		return clienteRepository.save(cliente);
	}
	
	//vamos criar o método busca clientes...
	public Collection<Cliente> buscarTodos(){
		return clienteRepository.findAll();
	}
	
	//vamos criar um método para remover clientes...
	public void excluir(Cliente cliente){		
		clienteRepository.delete(cliente);
	}
	
	public Cliente buscaPorId(Integer id){
		return clienteRepository.findOne(id);
	}
	
	//vamos criar um método para alterar
	public Cliente alterar(Cliente cliente){
		return clienteRepository.save(cliente);
	}
}
