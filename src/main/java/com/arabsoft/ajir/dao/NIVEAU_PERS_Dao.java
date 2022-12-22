package com.arabsoft.ajir.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.arabsoft.ajir.entities.ID_Nivau_Pers;
import com.arabsoft.ajir.entities.NIVEAU_PERS;

public interface NIVEAU_PERS_Dao extends JpaRepository<NIVEAU_PERS, ID_Nivau_Pers>{
	@Query(value = "select \r\n"
			+ "       t.cod_soc ,\r\n"
			+ "       t.mat_pers  ,  \r\n"
			+ "       t.num_niveau ,\r\n"
			+ "       t.cod_niveau ,\r\n"
			+ "       t.code_domaine   ,\r\n"
			+ "       t.code_option  ,\r\n"
			+ "       t.observation  ,\r\n"
			+ "       t.cod_etab,\r\n"
			+ "       t.niveau_ent  ,\r\n"
			+ "       t.date_niveau  ,\r\n"
			+ "       t.date_fin ,\r\n"
			+ "       t.date_deb ,\r\n"
			+ "       t.id_niveau_pers ,\r\n"
			+ "       t.cod_user    ,\r\n"
			+ "       pk_get_lib.GET_PARAM_NIVE$LIB_NIVEAU(t.cod_niveau) diplome,\r\n"
			+ "       pk_get_lib.GET_LISTE_DOMA$LIB_DOMAINE(t.code_domaine) specialite,\r\n"
			+ "       pk_get_lib.GET_PARAM_ORGA$LIB_ORG('ETAB_DIP',t.cod_etab) libetablissement\r\n"
			
			+ "from niveau_pers t \r\n"
			+ "where t.cod_soc=:codSoc and t.mat_pers=:mat\r\n" , nativeQuery = true)
	public List<NIVEAU_PERS> getNivPers(@Param("codSoc") String codSoc,@Param("mat") String mat);
	

}
