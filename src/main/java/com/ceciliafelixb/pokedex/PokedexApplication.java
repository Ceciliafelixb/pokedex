package com.ceciliafelixb.pokedex;

import com.ceciliafelixb.pokedex.model.Pokemon;
import com.ceciliafelixb.pokedex.repository.PokedexRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.core.ReactiveMongoOperations;
import reactor.core.publisher.Flux;

@SpringBootApplication
public class PokedexApplication {

	public static void main(String[] args) {
		SpringApplication.run(PokedexApplication.class, args);
	}


	CommandLineRunner init (ReactiveMongoOperations operations,
							PokedexRepository repository){
		return args -> {
			Flux<Pokemon> pokedexFlux = Flux.just(
					new Pokemon(null, "Bulbassauro", "Semente", "OverGrow", 6.09),
					new Pokemon(null, "Charizard", "Fogo", "Blaze", 90.05),
					new Pokemon(null, "Caterpie", "Minhoca", "Poeira do Escudo", 2.09),
					new Pokemon(null, "Blastoise", "Marisco", "Torrente", 6.09))

					.flatMap(repository::save);

					pokedexFlux
							.thenMany(repository.findAll())
					        .subscribe(System.out::println);
		};
	}
}
