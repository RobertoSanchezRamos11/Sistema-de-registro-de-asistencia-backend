package pe.edu.vallegrande.classkeeper.repository;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import pe.edu.vallegrande.classkeeper.model.entity.Student;
import pe.edu.vallegrande.classkeeper.model.entity.User;

import java.util.List;

public interface UserRepository extends JpaRepository<User, Long> {

    List<User> findAllByStates(String states);

    //Eliminado Logico
    @Modifying
    @Query(value = "update User u set u.states = 'I' where u.id = ?1")
    void inactiveUser(Long id);

    //Activar usuario
    @Modifying
    @Query(value = "update User u set u.states = 'A' where u.id = ?1")
    void activeUser(Long id);

    //Eliminado Fisico
    @Modifying
    @Query(value = "delete User u where u.id = ?1")
    void deleteUser(Long id);

}
