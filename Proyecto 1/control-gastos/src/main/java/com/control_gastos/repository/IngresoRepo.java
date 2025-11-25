package com.control_gastos.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.control_gastos.entity.Ingresos;

@Repository
public interface IngresoRepo extends JpaRepository<Ingresos,Integer> {

    public List<Ingresos> findByidusuarioOrderByFechaDesc(int idusuario);

    public List<Ingresos> findByidusuarioAndDescripcionContainingIgnoreCaseOrderByFechaDesc(int idusuario,String descripcion);

    @Query("SELECT i FROM Ingresos i WHERE i.idusuario = :idusuario " + 
       "AND (:filtroDesc IS NULL OR LOWER(i.descripcion) LIKE LOWER(CONCAT('%', :filtroDesc, '%'))) " +
       "AND (:filtroCat IS NULL OR i.id_categoria = :filtroCat) " +
       "AND (:filtroMed IS NULL OR i.id_medio = :filtroMed) " +
       "ORDER BY i.fecha DESC")

    List<Ingresos>Tablaconfiltros(
    @Param("idusuario") int idUsuario,
    @Param("filtroDesc") String filtroDesc,
    @Param("filtroCat") Integer filtroCat,
    @Param("filtroMed")Integer filtroMed
    );


    @Query("SELECT COALESCE(SUM(i.monto), 0.0) FROM Ingresos i WHERE i.idusuario=:idusuario ")
    Float Ingresototal(@Param("idusuario")int idusuario);




    @Query("SELECT i.id_categoria, COALESCE(SUM(i.monto), 0.0) FROM Ingresos i WHERE i.idusuario=:idusuario GROUP BY i.id_categoria ")
    List<Object[]>SumarTotalCategoriaIngresos(@Param("idusuario")int idusuario);


    
    @Query(value = """
        SELECT 
            DATE_FORMAT(fecha, '%Y-%m') AS mes_anio,
            COALESCE(SUM(Monto), 0.0) AS total
        FROM 
            ingresos
        WHERE
            ID_Usuario = :usuarioId AND
            fecha >= DATE_SUB(CURDATE(), INTERVAL 12 MONTH)
        GROUP BY 
            mes_anio
        ORDER BY 
            mes_anio ASC
    """, nativeQuery = true)
    List<Object[]> sumarIngresosPorMes(@Param("usuarioId") int usuarioId);

   @Query(value="""
            SELECT COALESCE(MAX(i.Monto), 0.0) from ingresos i where i.ID_Usuario = :idusuario
            """,nativeQuery=true)
    Float mayoringreso(@Param("idusuario")int idusuario);

     @Query(value="""
            SELECT COALESCE(SUM(i.monto), 0.0) 
            FROM ingresos i 
            WHERE i.ID_Usuario = :idusuario
            AND MONTH(i.fecha) = :mes     
            AND YEAR(i.fecha) = 2025 
            """, nativeQuery = true) 
    Float Totalingresopormes(
        @Param("idusuario") int idusuario, 
        @Param("mes") Integer mes
    );


    @Query(value="""
            SELECT COALESCE(MAX(i.Monto), 0.0) from ingresos i where i.ID_Usuario = :idusuario AND MONTH(i.fecha) = :mes     
            AND YEAR(i.fecha) = 2025 
            """,nativeQuery=true)
    Float mayoringresopormes(@Param("idusuario")int idusuario,@Param("mes") Integer mes);


    @Query("SELECT i.id_categoria, COALESCE(SUM(i.monto), 0.0) FROM Ingresos i WHERE i.idusuario=:idusuario AND MONTH(i.fecha) = :mes AND YEAR(i.fecha) = 2025 GROUP BY i.id_categoria  ")
    List<Object[]>SumarTotalCategoriaIngresospormes(@Param("idusuario")int idusuario,@Param("mes") Integer mes);

    public void deleteById(Integer id);



}
