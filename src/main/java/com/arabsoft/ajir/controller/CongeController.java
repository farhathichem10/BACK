package com.arabsoft.ajir.controller;

import java.io.IOException;
import java.sql.Date;
import java.util.List;
import java.util.Optional;

import javax.websocket.server.PathParam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.arabsoft.ajir.dao.AutorisationDao;
import com.arabsoft.ajir.dao.CongeRep;
import com.arabsoft.ajir.dao.GetConge;
import com.arabsoft.ajir.dao.PointageDAO;
import com.arabsoft.ajir.dao.RetardDAO;
import com.arabsoft.ajir.dao.TypeBulletinDao;
import com.arabsoft.ajir.entities.Autorisation;
import com.arabsoft.ajir.entities.DemCng;
import com.arabsoft.ajir.entities.Pointer;
import com.arabsoft.ajir.entities.Retard;
import com.arabsoft.ajir.entities.SoldConge;
import com.arabsoft.ajir.entities.TypeBulletin;


@CrossOrigin(origins="*")
@RestController
@RequestMapping("/conge")

public class CongeController {

	@Autowired CongeRep con;
	@Autowired GetConge con2;

	@Autowired 
	TypeBulletinDao bulletinDao;
	
	@Autowired
	AutorisationDao autorisationDao;
	
	@Autowired RetardDAO retarddao;
	@Autowired PointageDAO pointagedao;
	
	@GetMapping("/getbycodesocandmatpers/{codesoc}/{matpers}")
	public List<DemCng>	getbycodesocandmatpers(@PathVariable("codesoc") String code,@PathVariable("matpers")String mat){
		return con.getbycodesocandmatpers(code, mat);}
	
	@GetMapping("/getcongebyeid/{codesoc}/{matpers}")
	public List<SoldConge>	getcongebyid(@PathVariable("codesoc") String code,@PathVariable("matpers")String mat){
		return con2.getcongebyid(code, mat);}
	
	@GetMapping("/getpointage/{codeServ}/{matpers}")
	public List<Pointer> getPointage(@PathVariable("codeServ") String codeServ,@PathVariable("matpers")String mat){
		return pointagedao.getPointage(codeServ, mat);
	}
	
	@GetMapping("/getretard/{codeServ}/{matpers}")
	public List<Retard> getRetard(@PathVariable("codeServ") String codeServ,@PathVariable("matpers")String mat){
		return retarddao.getRetard(codeServ, mat);
	}
	
	
	@GetMapping("/getauto/{codsoc}/{matpers}")
	public List<Autorisation> getAutorisation(@PathVariable("codsoc") String code,@PathVariable("matpers")String mat){
		return autorisationDao.getAutorisstion(code, mat);
	}
	


	@GetMapping("/getnom/{codesoc}/{matpers}")
	 public Optional<String>   getnom(@PathVariable("codesoc") String code,@PathVariable("matpers")String mat){
		return( Optional<String>)con.getnom(code, mat) ;}
	
	
	@GetMapping("/getprenom/{codesoc}/{matpers}")
	 public Optional<String>   getprenom(@PathVariable("codesoc") String code,@PathVariable("matpers")String mat){
		return( Optional<String>)con.getprenom(code, mat) ;}

	
	
	@GetMapping("/gettypebul")
	 public List<TypeBulletin>   getTypeBul(){
		return( List<TypeBulletin>)bulletinDao.getTypeBul() ;}
	
	
	
}



