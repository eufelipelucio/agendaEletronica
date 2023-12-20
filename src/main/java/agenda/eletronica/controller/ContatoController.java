package agenda.eletronica.controller;

import java.util.List;

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
import org.springframework.web.bind.annotation.RestController;

import agenda.eletronica.model.Contato;
import agenda.eletronica.service.ContatoService;

@RestController
@RequestMapping("/contatos")
public class ContatoController {
	
	@Autowired
	ContatoService service;
	
	@PostMapping
	public ResponseEntity<Contato> salvar(@RequestBody Contato contato) {
		return ResponseEntity.status(HttpStatus.CREATED).body(service.salvar(contato));
	}
	
	@PutMapping("/{idcontato}")
	public ResponseEntity<Object> alterar(@PathVariable("idcontato") Long idcontato,
			@RequestBody Contato contato) {
		return ResponseEntity.status(HttpStatus.OK).body(service.alterar(idcontato, contato));

	}
	
	@DeleteMapping("/{idcontato}")
	public ResponseEntity<Object> excluir(@PathVariable("idcontato") Long idcontato) {
		service.excluir(idcontato);
		return ResponseEntity.status(HttpStatus.NO_CONTENT).build();

	}
	
	@GetMapping
	public ResponseEntity<List<Contato>> consultarTodos() {
		return ResponseEntity.status(HttpStatus.OK).body(service.consultarTodos());
	}
	
	@GetMapping("/{idcontato}")
	public ResponseEntity<Object> consultar(@PathVariable("idcontato") Long idcontato) {	
		return ResponseEntity.status(HttpStatus.OK).body(service.consultarPorId(idcontato));
	}
}
