package com.arabsoft.ajir.controller;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.arabsoft.ajir.dao.PersonnelRep;
import com.arabsoft.ajir.entities.Personnel;
import com.arabsoft.ajir.sevices.PersonnelService;
@CrossOrigin("*")
@RestController
@RequestMapping(value="/infoPers")
public class PersonnelControler {

	@Autowired
	PersonnelService personnelService;
	@Autowired
	PersonnelRep personnelRep;
	@CrossOrigin
	@PostMapping("/getPers")
	public Personnel getInfosPers(@RequestBody Personnel p) {
		String codSoc = p.getCod_soc();
		String matPers = p.getMat_pers();
		System.out.println("codSoc & matPers : "+codSoc+" || "+matPers);
		return this.personnelService.getpersInfo(codSoc, matPers);
	}
	
	
	@GetMapping("/getPers/{codSoc}/{matPaers}")
	public Personnel getInfosPerse(@PathVariable("codSoc") String c,@PathVariable("matPaers") String p) {

		return this.personnelService.getpersInfo(c, p);
	}

	@GetMapping("/getPers22/{cod_serv}/{mat_pers}")
	public List<Personnel> getInfosPers2(@PathVariable("cod_serv")String cod_serv,@PathVariable ("mat_pers")String mat_pers) {
		
		
		return this.personnelService.getpersInfo2(cod_serv,mat_pers);
	}

	
}
