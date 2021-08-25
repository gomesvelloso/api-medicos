package br.com.dhweb;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.com.dhweb.model.Medico;
import br.com.dhweb.repository.MedicoRepository;
import br.com.dhweb.service.CatalogoMedicoService;

@RestController
@RequestMapping("/medicos")
public class MedicoController {
	
	@Autowired
	private MedicoRepository medicoRepository;
	@Autowired
	private CatalogoMedicoService catalogoMedicoService;
	
	@GetMapping
	public List<Medico> listar(){
		return medicoRepository.findAll();
	}
	
	@GetMapping("/{medicoId}")
	public ResponseEntity<Medico> buscar(@PathVariable Long medicoId) {
		
		return medicoRepository.findById(medicoId)
				.map(medico -> ResponseEntity.ok(medico))
				.orElse(ResponseEntity.notFound().build());
		
	}
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public Medico salvar(@Valid @RequestBody Medico medico) {
		return catalogoMedicoService.salvar(medico);
	}
	
	@PutMapping("/{medicoId}")
	public ResponseEntity<Medico> atualizar(@PathVariable Long medicoId, @RequestBody Medico medico){
		
		if(!medicoRepository.existsById(medicoId)) {
			return ResponseEntity.notFound().build();
		}
		medico.setId(medicoId);
		medico = catalogoMedicoService.salvar(medico); 
		return ResponseEntity.ok(medico);
	}
	
	@DeleteMapping("/{medicoId}")
	public ResponseEntity<Medico> excluir(@PathVariable Long medicoId){
		
		if(!medicoRepository.existsById(medicoId)){
			return ResponseEntity.notFound().build();
		}
		catalogoMedicoService.excluir(medicoId);
		return ResponseEntity.noContent().build();
		
	}
}
