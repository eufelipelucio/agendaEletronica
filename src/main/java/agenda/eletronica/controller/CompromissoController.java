package agenda.eletronica.controller;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import agenda.eletronica.model.Compromisso;
import agenda.eletronica.model.Contato;
import agenda.eletronica.service.CompromissoService;
import agenda.eletronica.service.ContatoService;

@RestController
@RequestMapping("/compromisso")
public class CompromissoController {

	@Autowired
	CompromissoService serviceCompromisso;

    @Autowired
    ContatoService serviceContato;
		
	@PostMapping
	public ResponseEntity<Compromisso> salvar(@RequestBody Compromisso compromisso) {
		return ResponseEntity.status(HttpStatus.CREATED).body(serviceCompromisso.salvar(compromisso));
	}

	@PutMapping("/{idcompromisso}")
	public ResponseEntity<Object> alterar(@PathVariable("idcompromisso") Long idcompromisso,
			@RequestBody Compromisso compromisso) {
		return ResponseEntity.status(HttpStatus.OK).body(serviceCompromisso.alterar(idcompromisso, compromisso));

	}

	@DeleteMapping("/{idCompromisso}")
	public ResponseEntity<Object> excluir(@PathVariable("idCompromisso") Long idCompromisso) {
		serviceCompromisso.excluir(idCompromisso);
		return ResponseEntity.status(HttpStatus.NO_CONTENT).build();

	}

	@GetMapping
	public ResponseEntity<List<Compromisso>> getAll() {
		return ResponseEntity.status(HttpStatus.OK).body(serviceCompromisso.consultarTodos());
	}
	@GetMapping("/contato")
	public ResponseEntity<List<Compromisso>> getAllByContato(@RequestParam("id") Long id) {
		Contato contato = serviceContato.consultarPorId(id);
		return ResponseEntity.status(HttpStatus.OK).body(serviceCompromisso.consultarPorContato(contato));
	}
	@GetMapping("/data")
	public ResponseEntity<List<Compromisso>> getAllByData(@RequestParam("data") @DateTimeFormat(pattern = "yyyy-MM-dd")Date data) {
		return ResponseEntity.status(HttpStatus.OK).body(serviceCompromisso.consultarPorData(data));
	}
	
	@GetMapping("/intervalo-data")
	public ResponseEntity<List<Compromisso>> getAllByIntervaloData(@RequestParam("dataInicio") @DateTimeFormat(pattern = "yyyy-MM-dd") Date dataInicio,
			@RequestParam("dataFim") @DateTimeFormat(pattern = "yyyy-MM-dd") Date dataFim) {
		return ResponseEntity.status(HttpStatus.OK).body(serviceCompromisso.consultarEntreDatas(dataInicio, dataFim));
	}

	@GetMapping("/{idcompromisso}")
	public ResponseEntity<Object> consultar(@PathVariable("idcompromisso") Long idcompromisso) {
		return ResponseEntity.status(HttpStatus.FOUND).body(serviceCompromisso.consultarPorId(idcompromisso));
	}
	
}
