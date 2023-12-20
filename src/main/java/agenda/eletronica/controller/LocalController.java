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

import agenda.eletronica.model.Local;
import agenda.eletronica.service.LocalService;

@RestController
@RequestMapping("/local") 
public class LocalController {
	
	@Autowired
	LocalService service;
	
	@PostMapping
	public ResponseEntity<Local> salvar(@RequestBody Local local) {
		service.salvar(local);
		return ResponseEntity.status(HttpStatus.CREATED).body(local);
	}
	@PutMapping("/{idlocal}")
	public ResponseEntity<Object> alterar(@PathVariable("idlocal") Long idlocal, @RequestBody Local local){
		return ResponseEntity.status(HttpStatus.OK).body(service.alterar(idlocal, local));

	}
	
	@DeleteMapping("/{idlocal}")
	public ResponseEntity<Object> delete(Long idlocal){
		service.excluir(idlocal);
		return ResponseEntity.status(HttpStatus.NO_CONTENT).build();

	}
	@GetMapping
	public ResponseEntity<List<Local>> consultar() {
		return ResponseEntity.status(HttpStatus.OK).body(service.consultarTodos());
	}
	@GetMapping("/{idlocal}")
	public ResponseEntity<Object> consultarPorId(@PathVariable("idlocal") Long idlocal) {
		
		try{
            Local local = service.consultarPorId(idlocal);
            return ResponseEntity.status(HttpStatus.FOUND).body(local);
        }
		catch(Exception e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Não encontrado");		
		}
	
    }
}
