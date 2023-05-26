package br.com.solidarityconnect.config;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

import br.com.solidarityconnect.models.Alimento;
import br.com.solidarityconnect.models.Doacao;
import br.com.solidarityconnect.models.Endereco;
import br.com.solidarityconnect.models.Usuario;
import br.com.solidarityconnect.repository.AlimentoRepository;
import br.com.solidarityconnect.repository.DoacaoRepository;
import br.com.solidarityconnect.repository.EnderecoRepository;
import br.com.solidarityconnect.repository.UsuarioRepository;


@Configuration
public class DatabaseSeeder implements CommandLineRunner{
    
    @Autowired
    AlimentoRepository alimentoRepository;

    @Autowired
    DoacaoRepository doacaoRepository;

    @Autowired
    EnderecoRepository enderecoRepository;

    @Autowired
    UsuarioRepository usuarioRepository;

    @Override
    public void run(String... args) throws Exception {
        Usuario usuario1 = new Usuario(1L, "SuperFresh", "contato@superfresh.com", "senha123", "12.345.678/9012-34", "(11) 98765-4321");
        Usuario usuario2 = new Usuario(2L, "La Bella Pasta", "contato@labellapasta.com", "senha456", "98.765.432/1098-76", "(21) 12345-6789");
        Usuario usuario3 = new Usuario(3L, "Green Mart", "contato@greenmart.com", "senha789", "45.678.901/2345-67", "(31) 55555-5555");
        Usuario usuario4 = new Usuario(4L, "Sushi World", "contato@sushiworld.com", "senhaabc", "76.543.210/9876-54", "(41) 98765-4321");
        Usuario usuario5 = new Usuario(5L, "FreshMart", "contato@freshmart.com", "senhaxyz", "23.456.789/0123-45", "(51) 12345-6789");
        usuarioRepository.saveAll(List.of(usuario1, usuario2, usuario3, usuario4, usuario5));

        Alimento alimento1 = new Alimento(1L, "Maçãs", LocalDate.of(2023, 6, 1), 50, "Perecível");
        Alimento alimento2 = new Alimento(2L, "Arroz", LocalDate.of(2024, 12, 31), 100, "Não Perecível");
        Alimento alimento3 = new Alimento(3L, "Pão de Forma", LocalDate.of(2023, 6, 15), 20, "Perecível");
        Alimento alimento4 = new Alimento(4L, "Iogurte", LocalDate.of(2023, 6, 5), 30, "Perecível");
        Alimento alimento5 = new Alimento(5L, "Feijão", LocalDate.of(2025, 12, 31), 80, "Não Perecível");
        alimentoRepository.saveAll(List.of(alimento1, alimento2, alimento3, alimento4, alimento5));

        enderecoRepository.saveAll(List.of(
            Endereco.builder()
                .idEndereco(1L)
                .logradouroEndereco("Rua da Abundância")
                .numeroEndereco(123)
                .cepEndereco("12345-678")
                .bairroEndereco("Centro Feliz")
                .ufEndereco("SP")
                .complementoEndereco("Apartamento 1")
                .usuario(usuario1)
                .build(),
            Endereco.builder()
                .idEndereco(2L)
                .logradouroEndereco("Avenida das Delícias")
                .numeroEndereco(456)
                .cepEndereco("98765-432")
                .bairroEndereco("Bairro Aconchego")
                .ufEndereco("RJ")
                .usuario(usuario2)
                .build(),
            Endereco.builder()
                .idEndereco(3L)
                .logradouroEndereco("Travessa dos Sonhos")
                .numeroEndereco(789)
                .cepEndereco("54321-987")
                .bairroEndereco("Bairro Harmonia")
                .ufEndereco("MG")
                .complementoEndereco("Casa 2")
                .usuario(usuario3)
                .build(),
            Endereco.builder()
                .idEndereco(4L)
                .logradouroEndereco("Rua das Maravilhas")
                .numeroEndereco(321)
                .cepEndereco("87654-321")
                .bairroEndereco("Bairro Encantado")
                .ufEndereco("PR")
                .usuario(usuario4)
                .build(),
            Endereco.builder()
                .idEndereco(5L)
                .logradouroEndereco("Avenida dos Sabores")
                .numeroEndereco(654)
                .cepEndereco("23456-789")
                .bairroEndereco("Centro Gastronômico")
                .ufEndereco("RS")
                .complementoEndereco("Sala 3")
                .usuario(usuario5)
                .build()
        ));

        doacaoRepository.saveAll(List.of(
            Doacao.builder()
                .idDoacao(1L)
                .dataDoacao(LocalDateTime.of(2023, 5, 31, 10, 0))
                .usuario(usuario1)
                .alimento(alimento1)
                .build(),
            Doacao.builder()
                .idDoacao(2L)
                .dataDoacao(LocalDateTime.of(2023, 6, 15, 15, 30))
                .usuario(usuario2)
                .alimento(alimento2)
                .build(),
            Doacao.builder()
                .idDoacao(3L)
                .dataDoacao(LocalDateTime.of(2023, 7, 1, 9, 45))
                .usuario(usuario3)
                .alimento(alimento3)
                .build(),
            Doacao.builder()
                .idDoacao(4L)
                .dataDoacao(LocalDateTime.of(2023, 8, 10, 14, 15))
                .usuario(usuario4)
                .alimento(alimento4)
                .build(),
            Doacao.builder()
                .idDoacao(5L)
                .dataDoacao(LocalDateTime.of(2023, 9, 20, 11, 30))
                .usuario(usuario5)
                .alimento(alimento5)
                .build()
        ));
    }
}
