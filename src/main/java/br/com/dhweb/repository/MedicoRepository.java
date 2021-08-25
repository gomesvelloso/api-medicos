package br.com.dhweb.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.dhweb.model.Medico;

public interface MedicoRepository extends JpaRepository<Medico, Long>{
	
	/**
	 * SE for necessário criar consultas personalizadas, é só criar aqui.
	 * ex1: findByNome => Faz uma busca exata pelo nome
	 * ex2: findByNomeContaining => Não é busca exata. ele usa o like
	 */
	
	List<Medico> findByNome(String nome);
	List<Medico> findByNomeContaining(String nome);
	List<Medico> findByEmailContaining(String email);
	
	Optional<Medico> findByEmail(String email);
	

}
