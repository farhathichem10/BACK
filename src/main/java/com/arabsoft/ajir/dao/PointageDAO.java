package com.arabsoft.ajir.dao;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import com.arabsoft.ajir.entities.PointagePK;
import com.arabsoft.ajir.entities.Pointer;

public interface PointageDAO extends JpaRepository<Pointer, PointagePK> {
//	@Query(value= "select * from pointer WHERE date_point BETWEEN to_date(:date_debut, 'DD/MM/YYYY') AND to_date(:date_fin, 'DD/MM/YYYY') AND cod_soc =: 'codSoc' AND mat_pers =: 'mat_pers'",nativeQuery = true)
//	public List<Pointage> GetPointage(@Param("codSoc") String codSoc,@Param("mat_pers") String mat_pers,@Param("date_debut")Date date_debut,@Param("date_fin") Date date_fin );
	
	//@Query("select p from Pointer p where p.id.cod_soc=:x and p.id.mat_pers=:y")
	
	@Query(value="select * from Pointer  where (get_pere_by_pere(cod_soc,:x,:x)=:x or mat_pers=:y)\r\n"
			+ " and :y in(select mat_pers from service where cod_serv=:x)",nativeQuery=true)
	public List<Pointer> getPointage(@Param("x")String codeser,@Param("y")String mat);
	
	
}
