package br.com.dhweb.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.dhweb.exception.NegocioException;
import br.com.dhweb.model.Medico;
import br.com.dhweb.repository.MedicoRepository;

//é um componente spring que representa um componente de regra de negócio
@Service
public class CatalogoMedicoService {
	
	@Autowired
	private MedicoRepository medicoRepository;
	
	//O metodo deve ser executao dentro de uma transação
	@Transactional
	public Medico salvar(Medico medico) {
		
		boolean emailEmUso = medicoRepository.findByEmail(medico.getEmail())
				.stream()
				.anyMatch(medicoExistente -> !medicoExistente.equals(medico));
		
		if(emailEmUso) {
			throw new NegocioException("Já existe um cliente cadastrado com esse e-mail");
		}
		return medicoRepository.save(medico);
	}
	
	public void excluir(Long meidocId) {
		medicoRepository.deleteById(meidocId);
	}

}
