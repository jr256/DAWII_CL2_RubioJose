package pe.edu.cibertec.DAWIICL2RubioJose.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pe.edu.cibertec.DAWIICL2RubioJose.model.bd.Rol;


@Repository
public interface RolRepository extends JpaRepository<Rol, Integer> {

    Rol findByNomrol(String nombrerol);
}
