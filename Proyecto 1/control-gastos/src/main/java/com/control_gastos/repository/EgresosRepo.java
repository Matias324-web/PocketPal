package com.control_gastos.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.control_gastos.entity.Egresos;

@Repository
public interface EgresosRepo  extends JpaRepository<Egresos,Integer>{

    public List<Egresos> findByidusuarioOrderByFechaDesc(int idusuario);

    @Query("SELECT e FROM Egresos e WHERE e.idusuario = :idusuario " + 
       "AND (:filtroDesc IS NULL OR LOWER(e.descripcion) LIKE LOWER(CONCAT('%', :filtroDesc, '%'))) " +
       "AND (:filtroCat IS NULL OR e.idcategoria = :filtroCat) " +
       "AND (:filtroMed IS NULL OR e.idmedio = :filtroMed) " +
       "ORDER BY e.fecha DESC")

       List<Egresos>Tablaconfiltros(
    @Param("idusuario") int idUsuario,
    @Param("filtroDesc") String filtroDesc,
    @Param("filtroCat") Integer filtroCat,
    @Param("filtroMed")Integer filtroMed
    );

    @Query("SELECT COALESCE(SUM(e.monto), 0.0) FROM Egresos e WHERE e.idusuario=:idusuario")
    Float Egresototal(@Param("idusuario")int idusuario);

   @Query("SELECT e.idcategoria, COALESCE(SUM(e.monto), 0.0) FROM Egresos e WHERE e.idusuario=:idusuario GROUP BY e.idcategoria ")
    List<Object[]>SumarTotalCategoriaEgresos(@Param("idusuario")int idusuario);

  @Query(value = """
        SELECT 
            DATE_FORMAT(fecha, '%Y-%m') AS mes_anio,
            COALESCE(SUM(Monto), 0.0) AS total
        FROM 
            egresos
        WHERE
            ID_Usuario = :usuarioId AND
            fecha >= DATE_SUB(CURDATE(), INTERVAL 12 MONTH)
        GROUP BY 
            mes_anio
        ORDER BY 
            mes_anio ASC
    """, nativeQuery = true)
    List<Object[]> sumarEgresosPorMes(@Param("usuarioId") int usuarioId);


    @Query(value="""
            SELECT COALESCE(MAX(e.Monto), 0.0) from egresos e Where e.ID_Usuario = :usuarioId
            """,nativeQuery=true)
    Float Mayorgasto(@Param("usuarioId") int usuarioId);

    @Query(value="""
            SELECT COALESCE(SUM(e.monto), 0.0) 
            FROM Egresos e 
            WHERE e.ID_Usuario = :usuarioId
            AND MONTH(e.fecha) = :mes   
            AND YEAR(e.fecha) = 2025  
            """, nativeQuery = true) 
    Float Totalgastopormes(@Param("usuarioId") int usuarioId,@Param("mes") Integer mes);

    @Query(value="""
            SELECT COALESCE(MAX(e.Monto), 0.0) from egresos e Where e.ID_Usuario = :usuarioId AND MONTH(e.fecha) = :mes  
            AND YEAR(e.fecha) = 2025  
            """,nativeQuery=true)
    Float Mayorgastopormes(@Param("usuarioId") int usuarioId,@Param("mes") Integer mes);


    @Query("SELECT e.idcategoria, COALESCE(SUM(e.monto), 0.0) FROM Egresos e WHERE e.idusuario=:idusuario AND MONTH(e.fecha) = :mes AND YEAR(e.fecha) = 2025 GROUP BY e.idcategoria  ")
    List<Object[]>SumarTotalCategoriaEgresospormes(@Param("idusuario")int idusuario,@Param("mes") Integer mes);


    public void deleteById(Integer id);

    
}
