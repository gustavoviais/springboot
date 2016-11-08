package br.com.sb.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.sb.model.Cliente;

public interface ClienteRepository extends JpaRepository<Cliente, Integer> {

}
