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

import br.com.solidarityconnect.models.Usuario;
import br.com.solidarityconnect.repository.UsuarioRepository;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("api/usuarios")
@Slf4j
public class UsuarioController {

	@Autowired
	UsuarioRepository usuarioRepository;

	@Autowired
    PagedResourcesAssembler<Object> assembler;

	@GetMapping
    public PagedModel<EntityModel<Object>> index(@RequestParam(required = false) String busca, @PageableDefault(size = 5) Pageable pageable){        
        log.info("Buscar Usuários");

		Page<Usuario> usuarios = (busca == null) ?
            usuarioRepository.findAll(pageable):
            usuarioRepository.findByNomeContaining(busca, pageable);

        return assembler.toModel(usuarios.map(Usuario::toEntityModel));
    }

	@GetMapping("{id}")
	public EntityModel<Usuario> show(@PathVariable Long id) {
		log.info("Buscar Usuário " + id);
		var usuario = findByUsuario(id);
		return usuario.toEntityModel();
	}

	@PostMapping
	public ResponseEntity<Object> create(@RequestBody @Valid Usuario usuario) {
		log.info("Cadastrando Usuário" + usuario);
		usuarioRepository.save(usuario);
		return ResponseEntity
            .created(usuario.toEntityModel().getRequiredLink("self").toUri())
            .body(usuario.toEntityModel());
	}

	@DeleteMapping("{id}")
	public ResponseEntity<Object> delete(@PathVariable Long id) {
		log.info("Deletando Usuário");

		usuarioRepository.delete(findByUsuario(id));
		return ResponseEntity.noContent().build();
	}

	@PutMapping("{id}")
	public EntityModel<Usuario> update(@PathVariable @Valid Long id, @RequestBody Usuario usuario) {
        log.info("Alterar Usuário " + id);
		findByUsuario(id);

		usuario.setId(id);
		usuarioRepository.save(usuario);
		return usuario.toEntityModel();
	}

	private Usuario findByUsuario(Long id) {
		return usuarioRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Usuário não encontrado"));
	}

}