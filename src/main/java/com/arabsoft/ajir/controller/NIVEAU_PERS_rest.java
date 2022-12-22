package com.arabsoft.ajir.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.arabsoft.ajir.dao.NIVEAU_PERS_Dao;
import com.arabsoft.ajir.entities.NIVEAU_PERS;

@RestController
@CrossOrigin("*")
@RequestMapping("/NIVEAU_PERS")
public class NIVEAU_PERS_rest {
	@Autowired NIVEAU_PERS_Dao niv;
	@GetMapping("/getniv/{cod}/{mat}")
	public List <NIVEAU_PERS> getniv(@PathVariable("cod")String cod,@PathVariable("mat")String mat){
		return niv.getNivPers(cod,mat);}

}
