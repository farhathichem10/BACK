package com.arabsoft.ajir.dao;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.arabsoft.ajir.entities.AbsanteismeByLibelle;


public interface AbsanteismeByLibelleDAO extends JpaRepository<AbsanteismeByLibelle, String>{

	
	@Query(value="select to_char(d.dat_debut,'yyyy') DATE_HIST,j.lib_mot,SUM(D.NBR_JOURS)nombre\r\n"
			+ "from dem_cng d,service s,motif_j j,personnel p\r\n"
			+ "where d.cod_soc = p.cod_soc\r\n"
			+ "and d.mat_pers = p.mat_pers\r\n"
			+ "and d.valid ='O'\r\n"
			+ "and d.cod_serv = s.cod_serv\r\n"
			+ "and d.code_m = j.cod_m and to_char(d.dat_debut,'yyyy')=:annee \r\n"
			+ "and to_char(d.dat_debut,'yyyy') > 2010\r\n"
			+ "and s.lib_serv ='Agence  Laghouat'\r\n"
			+ "group by to_char(d.dat_debut,'yyyy') ,j.lib_mot",nativeQuery = true)
	public List<AbsanteismeByLibelle> GetAbsanteismeByLibelle(@Param("annee") String annee);
	
}
