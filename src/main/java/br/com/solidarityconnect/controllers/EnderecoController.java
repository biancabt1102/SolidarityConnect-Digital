package br.com.solidarityconnect.controllers;

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

import br.com.solidarityconnect.models.Endereco;
import br.com.solidarityconnect.models.Usuario;
import br.com.solidarityconnect.repository.EnderecoRepository;
import br.com.solidarityconnect.repository.UsuarioRepository;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("solidarityconnect/api/endereco")
@Slf4j
public class EnderecoController {
	@Autowired
	UsuarioRepository usuarioRepository;
    
	@Autowired
	EnderecoRepository enderecoRepository;

	@Autowired
    PagedResourcesAssembler<Object> assembler;

	@GetMapping
    public PagedModel<EntityModel<Object>> index(@RequestParam(required = false) String busca, @PageableDefault(size = 5) Pageable pageable){        
        log.info("Buscar Endereços");

		Page<Endereco> enderecos = (busca == null) ?
            enderecoRepository.findAll(pageable):
            enderecoRepository.findByLogradouroEnderecoContaining(busca, pageable);

        return assembler.toModel(enderecos.map(Endereco::toEntityModel));
    }

	@GetMapping("{id}")
	public EntityModel<Endereco> show(@PathVariable Long id) {
		log.info("Buscar Endereço " + id);
		var endereco = findByEndereco(id);
		return endereco.toEntityModel();
	}

	@PostMapping
	public ResponseEntity<Object> create(@RequestBody @Valid Endereco endereco) {
		log.info("Cadastrando Endereço" + endereco);
		endereco.setUsuario(encontrandoUsuario());
		enderecoRepository.save(endereco);
		return ResponseEntity
            .created(endereco.toEntityModel().getRequiredLink("self").toUri())
            .body(endereco.toEntityModel());
	}

	
	@DeleteMapping("{id}")
	public ResponseEntity<Object> delete(@PathVariable Long id) {
		log.info("Deletando Endereço");
		
		enderecoRepository.delete(findByEndereco(id));
		return ResponseEntity.noContent().build();
	}
	
	@PutMapping("{id}")
	public EntityModel<Endereco> update(@PathVariable @Valid Long id, @RequestBody Endereco endereco) {
        log.info("Alterar Endereço " + id);
		findByEndereco(id);
		
		endereco.setIdEndereco(id);
		endereco.setUsuario(encontrandoUsuario());
		enderecoRepository.save(endereco);
		return endereco.toEntityModel();
	}
	
	private Endereco findByEndereco(Long id) {
		return enderecoRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Endereço não encontrado"));
	}

	private Usuario encontrandoUsuario() {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		var username = authentication.getName();
	
		Usuario usuario = usuarioRepository.findByEmailUsuario(username)
		.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Usuário não encontrado"));
		return usuario;
	}
}

