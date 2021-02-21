package com.ceciliafelixb.pokedex.repository;

import com.ceciliafelixb.pokedex.model.Pokemon;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

public interface PokedexRepository extends ReactiveMongoRepository <Pokemon,String> {


}
