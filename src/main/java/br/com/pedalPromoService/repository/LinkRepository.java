package br.com.pedalPromoService.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import br.com.pedalPromoService.vo.Link;

public interface LinkRepository extends MongoRepository<Link, String>{

}
