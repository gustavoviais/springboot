package br.com.sb.controller;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import br.com.sb.model.Cliente;
import br.com.sb.service.ClienteService;

@RestController
public class ClienteController {
	
	@Autowired //injeção de dependencia spring boot
	ClienteService clienteService;
	
	//End point
	//GET=EXIBIR DADOS; POST=GRAVAR DADOS, DELETE=DELETAR DADOS, PUT=EDITAR DADOS
	//POST POIS VAI INSERIR UM DADO...
	@RequestMapping(method=RequestMethod.POST, value="/clientes", consumes=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Cliente> cadastrarCliente(@RequestBody Cliente cliente){ //@RequestBody le o corpo json e atribui ao cliente
		
		//método cadastrarCliente irá retornar o cliente cadastrado como entidade...
		Cliente clienteCadastrado = clienteService.constroiCliente(cliente);
		
		return new ResponseEntity<>(clienteCadastrado, HttpStatus.CREATED);
	}
	
	@RequestMapping(method=RequestMethod.GET, value="/clientes", produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Collection<Cliente>> buscarTodosClientes(){ //@RequestBody le o corpo json e atribui ao cliente
		
		//método cadastrarCliente irá retornar o cliente cadastrado como entidade...
		Collection<Cliente> clientesBuscados = clienteService.buscarTodos();
		
		return new ResponseEntity<>(clientesBuscados, HttpStatus.OK);
	}
	
	@RequestMapping(method=RequestMethod.DELETE, value="/clientes/{id}")
	public ResponseEntity<Cliente> excluirCliente(@PathVariable Integer id){//@PathVariable é a variavel {id}
		Cliente clienteEncontrado = clienteService.buscaPorId(id);
		
		if(clienteEncontrado==null){
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		
		clienteService.excluir(clienteEncontrado);
		return new ResponseEntity<>(HttpStatus.OK);
	}
	
	@RequestMapping(method=RequestMethod.PUT, value="/clientes", consumes=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Cliente> alterarCliente(@RequestBody Cliente cliente){
		Cliente clienteAlterado = clienteService.alterar(cliente);
		
		return new ResponseEntity<>(clienteAlterado, HttpStatus.OK);
	}
}
