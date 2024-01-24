package pe.edu.vallegrande.classkeeper.service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pe.edu.vallegrande.classkeeper.model.entity.Student;
import pe.edu.vallegrande.classkeeper.model.entity.User;
import pe.edu.vallegrande.classkeeper.repository.UserRepository;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    //Listar todos usuarios con estado "A"
    public List<User> findAll() {
        return userRepository.findAllByStates("A");
    }

    //Listar todos usuarios con estado "i"
    public List<User> findAllInactivesUsers() {
        return userRepository.findAllByStates("I");
    }

    //Insertar un estudiante
    public User save(User user) {
        return userRepository.save(user);
    }

    //Buscar por Id
    public Optional<User> findById(Long id) {
        return userRepository.findById(id);
    }

    //Eliminado Logico
    @Transactional
        public Optional<User> inactive(Long id) {
        userRepository.inactiveUser(id);
        return userRepository.findById(id);
    }

    //Activar usuario
    @Transactional
    public Optional<User> active(Long id) {
        userRepository.activeUser(id);
        return userRepository.findById(id);
    }

    //Eliminado Fisico
    @Transactional
    public void delete(Long id) {
        userRepository.deleteUser(id);
    }

    //Actualizar usuario
    @Transactional
    public User update(Long id, User updatedUser) {
        Optional<User> optionalUser = userRepository.findById(id);
        if (optionalUser.isPresent()) {
            User existingUser = optionalUser.get();
            existingUser.setName(updatedUser.getName().trim());
            existingUser.setSurname(updatedUser.getSurname().trim());
            existingUser.setTypeDocument(updatedUser.getTypeDocument().trim());
            existingUser.setNumberDocument(updatedUser.getNumberDocument().trim());
            existingUser.setEmail(updatedUser.getEmail().trim());
            existingUser.setAddress(updatedUser.getAddress().trim());
            existingUser.setCelphone(updatedUser.getCelphone().trim());
            existingUser.setPassword(updatedUser.getPassword().trim());
            existingUser.setRol(updatedUser.getRol().trim());
            existingUser.setStates(updatedUser.getStates().trim());
            return userRepository.save(existingUser);
        } else {
            throw new RuntimeException("Usuario no encontrado con el ID: " + id);
        }
    }

}
