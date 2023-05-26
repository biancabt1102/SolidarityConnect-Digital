package br.com.solidarityconnect.controllers;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.PagedModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import br.com.solidarityconnect.models.Alimento;
import br.com.solidarityconnect.models.Doacao;
import br.com.solidarityconnect.models.Usuario;
import br.com.solidarityconnect.repository.AlimentoRepository;
import br.com.solidarityconnect.repository.DoacaoRepository;
import br.com.solidarityconnect.repository.UsuarioRepository;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("solidarityconnect/api/alimento")
@Slf4j
public class AlimentoController {
    
	@Autowired
	AlimentoRepository alimentoRepository;

	@Autowired
	UsuarioRepository usuarioRepository;

	@Autowired
	DoacaoRepository doacaoRepository;

	@Autowired
    PagedResourcesAssembler<Object> assembler;

	@GetMapping
    public PagedModel<EntityModel<Object>> index(@RequestParam(required = false) String busca, @PageableDefault(size = 5) Pageable pageable){        
        log.info("Buscar Alimentos");

		Page<Alimento> alimento = (busca == null) ?
            alimentoRepository.findAll(pageable):
            alimentoRepository.findByNomeAlimentoContaining(busca, pageable);

        return assembler.toModel(alimento.map(Alimento::toEntityModel));
    }

	@GetMapping("{id}")
	public EntityModel<Alimento> show(@PathVariable Long id) {
		log.info("Buscar Alimento " + id);
		var alimento = findByAlimento(id);
		return alimento.toEntityModel();
	}

	@PostMapping
	public ResponseEntity<Object> create(@RequestBody @Valid Alimento alimento) {
		log.info("Cadastrando Alimento" + alimento);
		alimentoRepository.save(alimento);
		doacaoAlimento(alimento);
		return ResponseEntity
            .created(alimento.toEntityModel().getRequiredLink("self").toUri())
            .body(alimento.toEntityModel());
	}

	@DeleteMapping("{id}")
	public ResponseEntity<Object> delete(@PathVariable Long id) {
		log.info("Deletando Alimento");

		alimentoRepository.delete(findByAlimento(id));
		return ResponseEntity.noContent().build();
	}

	@PutMapping("{id}")
	public EntityModel<Alimento> update(@PathVariable @Valid Long id, @RequestBody Alimento alimento) {
        log.info("Alterar Alimento " + id);
		findByAlimento(id);

		alimento.setIdAlimento(id);
		alimentoRepository.save(alimento);
		return alimento.toEntityModel();
	}

	private Alimento findByAlimento(Long id) {
		return alimentoRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Alimento não encontrado"));
	}

	private void doacaoAlimento(Alimento alimento) {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
    	var username = authentication.getName();

		Usuario usuario = usuarioRepository.findByEmailUsuario(username)
		.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Usuário não encontrado"));

		Doacao doacao = new Doacao();
		doacao.setDataDoacao(LocalDateTime.now());
		doacao.setUsuario(usuario);
		doacao.setAlimento(alimento);
		doacaoRepository.save(doacao);
	}

}
