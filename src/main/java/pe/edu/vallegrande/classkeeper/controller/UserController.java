package pe.edu.vallegrande.classkeeper.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pe.edu.vallegrande.classkeeper.model.entity.Student;
import pe.edu.vallegrande.classkeeper.model.entity.User;
import pe.edu.vallegrande.classkeeper.service.UserService;

import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "*")
@RestController
@RequiredArgsConstructor
@RequestMapping("/v1/users")
public class UserController {

    private final UserService userService;

    // Lista todos los registros
    @GetMapping
    public ResponseEntity<List<User>> findAll() {
        List<User> users = userService.findAll();
        users.forEach(user -> {
            user.setName(user.getName().trim());
            user.setSurname(user.getSurname().trim());
            user.setTypeDocument(user.getTypeDocument().trim());
            user.setNumberDocument(user.getNumberDocument().trim());
            user.setEmail(user.getEmail().trim());
            user.setAddress(user.getAddress().trim());
            user.setCelphone(user.getCelphone().trim());
            user.setPassword(user.getPassword().trim());
        });
        return ResponseEntity.ok(users);
    }

    @GetMapping("/inactives")
    public ResponseEntity<List<User>> findAllInactive() {
        return ResponseEntity.ok(userService.findAllInactivesUsers());
    }

    // Agrega un Registro
    @PostMapping
    public ResponseEntity<User> save(@RequestBody User user) {
        return ResponseEntity.ok(userService.save(user));
    }

    // Busca por su id
    @GetMapping("/{id}")
    public ResponseEntity<Optional<User>> findById(@PathVariable Long id) {

        Optional<User> userOptional = userService.findById(id);

        userOptional.ifPresent(user -> {
            user.setName(user.getName().trim());
            user.setSurname(user.getSurname().trim());
            user.setTypeDocument(user.getTypeDocument().trim());
            user.setNumberDocument(user.getNumberDocument().trim());
            user.setEmail(user.getEmail().trim());
            user.setAddress(user.getAddress().trim());
            user.setCelphone(user.getCelphone().trim());
            user.setPassword(user.getPassword().trim());
            // ... (repite para otros campos)
        });

        return ResponseEntity.ok(userOptional);
    }

    // Elimina un registro (Fisico)
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        userService.delete(id);
        return ResponseEntity.noContent().build();
    }

    //Eliminado Logico
    @DeleteMapping("/inactive/{id}")
    public ResponseEntity<Optional<User>> inactive(@PathVariable Long id) {
        return ResponseEntity.ok(userService.inactive(id));
    }

    //Activar usuario
    @PutMapping("/active/{id}")
    public ResponseEntity<Optional<User>> active(@PathVariable Long id) {
        return ResponseEntity.ok(userService.active(id));
    }


    // Actualiza un registro
    @PutMapping("/{id}")
    public ResponseEntity<User> update(@PathVariable Long id, @RequestBody User updateUser) {

        updateUser.setName(updateUser.getName().trim());
        updateUser.setSurname(updateUser.getSurname().trim());
        updateUser.setTypeDocument(updateUser.getTypeDocument().trim());
        updateUser.setNumberDocument(updateUser.getNumberDocument().trim());
        updateUser.setEmail(updateUser.getEmail().trim());
        updateUser.setAddress(updateUser.getAddress().trim());
        updateUser.setCelphone(updateUser.getCelphone().trim());
        updateUser.setPassword(updateUser.getPassword().trim());

        User user = userService.update(id, updateUser);
        return ResponseEntity.ok(user);
    }


}
