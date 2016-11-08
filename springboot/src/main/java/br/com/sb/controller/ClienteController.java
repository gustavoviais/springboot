package br.com.sb.controller;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import br.com.sb.model.Cliente;

@RestController
public class ClienteController {
	
	//simular BD
	Map<Integer, Cliente> clientes = new HashMap<>();
	Integer nextNumber=1;
	
	//Negócio
	private Cliente constroiCliente(Cliente cliente){
			
		cliente.setId(nextNumber);
		nextNumber++;
		
		clientes.put(cliente.getId(), cliente);
		
		return cliente;
	}
	
	//vamos criar o método busca clientes...
	private Collection<Cliente> buscarTodos(){
		return clientes.values();
	}
	
	//vamos criar um método para remover clientes...
	public void excluir(Cliente cliente){		
		clientes.remove(cliente.getId());
	}
	
	private Cliente buscaPorId(Integer id){
		return clientes.get(id);
	}
	
	//vamos criar um método para alterar
	private Cliente alterar(Cliente cliente){
		clientes.put(cliente.getId(), cliente);
		return cliente;
	}
	
	//End point
	//GET=EXIBIR DADOS; POST=GRAVAR DADOS, DELETE=DELETAR DADOS, PUT=EDITAR DADOS
	//POST POIS VAI INSERIR UM DADO...
	@RequestMapping(method=RequestMethod.POST, value="/clientes", consumes=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Cliente> cadastrarCliente(@RequestBody Cliente cliente){ //@RequestBody le o corpo json e atribui ao cliente
		
		//método cadastrarCliente irá retornar o cliente cadastrado como entidade...
		Cliente clienteCadastrado = constroiCliente(cliente);
		
		return new ResponseEntity<>(clienteCadastrado, HttpStatus.CREATED);
	}
	
	@RequestMapping(method=RequestMethod.GET, value="/clientes", produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Collection<Cliente>> buscarTodosClientes(){ //@RequestBody le o corpo json e atribui ao cliente
		
		//método cadastrarCliente irá retornar o cliente cadastrado como entidade...
		Collection<Cliente> clientesBuscados = buscarTodos();
		
		return new ResponseEntity<>(clientesBuscados, HttpStatus.OK);
	}
	
	@RequestMapping(method=RequestMethod.DELETE, value="/clientes/{id}")
	public ResponseEntity<Cliente> excluirCliente(@PathVariable Integer id){//@PathVariable é a variavel {id}
		Cliente clienteEncontrado = buscaPorId(id);
		
		if(clienteEncontrado==null){
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		
		excluir(clienteEncontrado);
		return new ResponseEntity<>(HttpStatus.OK);
	}
	
	@RequestMapping(method=RequestMethod.PUT, value="/clientes", consumes=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Cliente> alterarCliente(@RequestBody Cliente cliente){
		Cliente clienteAlterado = alterar(cliente);
		
		return new ResponseEntity<>(clienteAlterado, HttpStatus.OK);
	}
}
