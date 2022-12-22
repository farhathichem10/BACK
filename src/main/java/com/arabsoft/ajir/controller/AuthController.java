package com.arabsoft.ajir.controller;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.LockedException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.arabsoft.ajir.dao.AdmUserRep;
import com.arabsoft.ajir.dao.AdrPersRep;
import com.arabsoft.ajir.entities.AdmUser;
import com.arabsoft.ajir.entities.AdrPers;
import com.arabsoft.ajir.entities.JwtResponse;
import com.arabsoft.ajir.entities.Libre_demande;
import com.arabsoft.ajir.entities.MessageResponse;
import com.arabsoft.ajir.entities.SignupRequest;
import com.arabsoft.ajir.entities.libreDemandek;
import com.arabsoft.ajir.jwt.JwtUtils;
import com.arabsoft.ajir.sevices.UserDetailsImpl;
import com.arabsoft.ajir.sevices.UserDetailsServiceImpl;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/auth")
public class AuthController {
	@Autowired
	AuthenticationManager authenticationManager;
	@Autowired
	JwtUtils jwtUtils;
	@Autowired
	AdmUserRep admUserRep;
	@Autowired
	PasswordEncoder encoder;
	@Autowired
	AdrPersRep adrPersRep;
	@Autowired UserDetailsServiceImpl uss;

	@PostMapping("/signin")
	public ResponseEntity<?> authenticateUser(@RequestBody LoginRequest loginRequest) {
	AdmUser user= admUserRep.findBymatpers(loginRequest.getMatpers()).get();
		//System.out.println("nbr"+admUserRep.findBymatpers(loginRequest.getMatpers()).get().getUse_nbessai());

		Authentication authentication = authenticationManager.authenticate(
				new UsernamePasswordAuthenticationToken(loginRequest.getMatpers(), loginRequest.getUsepswd()));

	//	System.out.println("authhhhh"+authentication);
		SecurityContextHolder.getContext().setAuthentication(authentication);
		String jwt = jwtUtils.generateJwtToken(authentication);
		  /*if (user != null) {
	            if (user.getUse_cptestatus()=="false" ) {
	                if (user.getUse_nbessai() < uss.MAX_FAILED_ATTEMPTS - 1) {
	                	uss.increaseFailedAttempts(user);
	                } else {
	                	uss.lock(user);
	                    System.out.println("Your account has been locked due to 3 failed attempts."
	                            + " It will be unlocked after 24 hours.");
	                }
	            } 
	        }*/
		
//System.out.println("jjjjjjjj"+jwt);
		
			
			if(new UsernamePasswordAuthenticationToken(loginRequest.getMatpers(), loginRequest.getUsepswd())!=null) {
		
		
			
		

		UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
System.out.println(userDetails.getRole_portail());
		return ResponseEntity.ok(new JwtResponse(jwt, "Bearer", userDetails.getUse_id(), userDetails.getUse_matricule(),
				userDetails.getUselogin(), userDetails.getUse_psw(), userDetails.getUse_lname(),
				userDetails.getUse_fname(), userDetails.getUse_nbessai(), userDetails.getUse_cptestatus(),
				userDetails.getUse_crtdt(), userDetails.getUse_lastcnx(), userDetails.getUse_nivadm(),
				userDetails.getUse_type(), userDetails.getUse_status(), userDetails.getUse_credt(),
				userDetails.getUse_updtdt(), userDetails.getUse_updtby(), userDetails.getCod_soc(),
				userDetails.getMat_pers(), userDetails.getCod_site(),userDetails.getRole_portail()));
	}else{
		
		user.setUse_nbessai(user.getUse_nbessai()+1);
		 return ResponseEntity.badRequest().body(new MessageResponse("email not exist"));

		
		
		
		
		
	}}

	String encrypt(@RequestParam String password) {
		try {
			// Create MessageDigest instance for MD5
			MessageDigest md = MessageDigest.getInstance("MD5");

			// Add password bytes to digest
			md.update(password.getBytes());

			// Get the hash's bytes
			byte[] bytes = md.digest();

			// This bytes[] has bytes in decimal format. Convert it to hexadecimal format
			StringBuilder sb = new StringBuilder();
			for (int i = 0; i < bytes.length; i++) {
				sb.append(Integer.toString((bytes[i] & 0xff) + 0x100, 16).substring(1));
			}

			// Get complete hashed password in hex format
			password = sb.toString();
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		return password;
	}

	@PostMapping("/signupt")
	public ResponseEntity<?> updateUclt(@RequestBody SignupRequest signUpRequest) {
		
		AdrPers adrp = adrPersRep.findByAdrelectroniqueo(signUpRequest.getAdrelectronique());
		Boolean d =adrPersRep.existsByAdrelectronique(signUpRequest.getAdrelectronique());
		
		if(!d) {
			 return ResponseEntity.badRequest().body(new MessageResponse("email not exist"));

		}
		signUpRequest.setId(adrp.getId());

		Boolean admuser = admUserRep.existsByUselogin(signUpRequest.getId().getMat_pers());

		String codSoc=adrp.getId().getCod_soc();
		String matP = adrp.getId().getMat_pers();
		
		System.out.println(codSoc);  
		System.out.println(matP);
if (adrp !=null)
{

	System.out.println(codSoc);
	System.out.println(matP);
	//System.out.println(admuser.getMat_pers());
	if(admuser){
		
		//AdmUser adm=admUserRep.findByUselogin(signUpRequest.getId().getMat_pers());
				
		

		 return ResponseEntity.accepted().body(new MessageResponse("mnadhem"));

	}
	else if(!admuser){
		AdmUser au = new AdmUser();
		//System.out.println(au.getUse_lname());
		au.setCod_soc(adrp.getId().getCod_soc());
		au.setMat_pers(adrp.getId().getMat_pers());
		au.setUse_login(adrp.getId().getMat_pers());
		au.setUse_lname(signUpRequest.getUse_lname());
		au.setUse_pswd(encoder.encode(signUpRequest.getUse_pswd()));

		System.out.println(encoder.encode(signUpRequest.getUse_pswd()));

		admUserRep.save(au);
	 return ResponseEntity.accepted().body(new MessageResponse("okkkkkkkkkkkkkkkk"));

	}
	
	/*else if(admuser){
		AdmUser au = new AdmUser();
		System.out.println(au.getUse_lname());
		au.setCod_soc(adrp.getId().getCod_soc());
		au.setMat_pers(adrp.getId().getMat_pers());
		au.setUse_login(adrp.getId().getMat_pers());
		au.setUse_lname("dddd");

		admUserRep.save(au);
	 return ResponseEntity.accepted().body(new MessageResponse("okkkkkkkkkkkkkkkk"));

	}*/
	else {	
		return ResponseEntity.badRequest().body(new MessageResponse("fffff"));
          }
	
}
return new ResponseEntity<>( HttpStatus.OK);
		} 

	@PostMapping("/login")
	public AdmUser loginUser(@RequestParam String login, @RequestParam String pass) {
		// AdmUser ad = new AdmUser();
		String pass2 = this.encrypt(pass);
		System.out.println(pass);
		AdmUser use = admUserRep.login2(login, pass2);

		// if (use.getUse_psw().equals(pass))
		// return ResponseEntity.accepted().body(new
		// MessageResponse("okkkkkkkkkkkkkkkk"));

		return use;
	}
	/*
	 * @PostMapping("/login") AdmUser Login(@RequestParam String mat,@RequestParam
	 * String pass) {
	 * 
	 * 
	 * return admUserRep.Login(mat, pass);
	 * 
	 * }
	 */
	
	@CrossOrigin
	@GetMapping("/getUser")
	public ResponseEntity<List<AdmUser>> getAllDemande() {

		return ResponseEntity.ok(admUserRep.findAll());
	}
	
	 @PutMapping("/setRole")
	 
	  public ResponseEntity<AdmUser> updateRh( @RequestBody AdmUser Ag) {
	  
	     Optional<AdmUser> AgData = admUserRep.findById(Ag.getUse_id());
	    if (AgData.isPresent()) {
	    	AdmUser agg = AgData.get();
	   agg.setRole_portail(Ag.getRole_portail());
	  
	      
	     return new ResponseEntity<>(admUserRep.save(agg), HttpStatus.OK);
	    } else {
	      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	    } }

		@CrossOrigin
		@GetMapping("/{mat}")
		public AdrPers GetmatChef(@PathVariable("mat") String mat){
			
			String matChef=this.adrPersRep.GetMatChef(mat);
			System.out.println(matChef);
			return this.adrPersRep.GetAdrChef(matChef);
		
		}
		

		
}
