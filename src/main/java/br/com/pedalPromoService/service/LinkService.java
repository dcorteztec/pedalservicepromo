package br.com.pedalPromoService.service;

import java.io.Serializable;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.pedalPromoService.repository.LinkRepository;
import br.com.pedalPromoService.vo.Link;

@Service
public class LinkService implements Serializable{

	private static final long serialVersionUID = 1L;

	@Autowired
	private LinkRepository linkRepository;
	
	public void salvar(String link) {
		linkRepository.save(new Link(link));
    }
 
    public List<Link> findAll() {
       return linkRepository.findAll();
    }
 
    public long count() {
        return linkRepository.count();
    }
 
    public Link findById(String id) {
        return linkRepository.findOne(id);
    }
 
    public void delete(String id) {
    	linkRepository.delete(id);
    }
 
}
