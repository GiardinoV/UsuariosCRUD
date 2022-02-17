package com.apiResrt.apiRest.Controllers;

import com.apiResrt.apiRest.Models.UsuarioModel;
import com.apiResrt.apiRest.Services.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Optional;

@RestController
@RequestMapping("/")
public class UsuarioController {
    @Autowired
    UsuarioService usuarioService;

    @GetMapping
    public ArrayList<UsuarioModel> obtenerUsuarios(){
        return usuarioService.obtenerUsuarios();
    }


   /* public ResponseEntity<Tutorial> getTutorialById(@PathVariable("id") long id) {
        Optional<Tutorial> tutorialData = tutorialRepository.findById(id);

        if (tutorialData.isPresent()) {
            return new ResponseEntity<>(tutorialData.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }*/

    @PostMapping
    public UsuarioModel guardarUsuario(@RequestBody UsuarioModel usuario){
        return this.usuarioService.guardarUsuario(usuario);
    }

    //Creo metodo para modificar datos por id
    @PutMapping("/usuario/{id}")
    public ResponseEntity<UsuarioModel> actualizarDatos(@PathVariable("id") Long id, @RequestBody UsuarioModel usuario){
        Optional<UsuarioModel> usuarioID = usuarioService.obtenerPorId(id);

        if (usuarioID.isPresent()){
            UsuarioModel _usuario = usuarioID.get();
            _usuario.setNomber(usuario.getNomber());
            _usuario.setEmail(usuario.getEmail());
            _usuario.setPrioridad(usuario.getPrioridad());
            return new ResponseEntity<>(usuarioService.guardarUsuario(_usuario),HttpStatus.OK);
        }else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping(path = "/{id}")
    public Optional<UsuarioModel> obtenerUsuarioPorId(@PathVariable("id") Long id){
        return this.usuarioService.obtenerPorId(id);
    }

    @GetMapping("/query")
    public ArrayList<UsuarioModel> obtenerUsuarioPorPrioridad(@RequestParam("prioridad") Integer prioridad){
        return this.usuarioService.obtenerPorPrioridad(prioridad);
    }

    //Creo metodo para buscar por email
    @GetMapping(path = "/email")
    public ArrayList<UsuarioModel> obtenerUsuarioPorEmail(@RequestParam("email") String email){
        return this.usuarioService.obtenerPorEmail(email);
    }

    @DeleteMapping(path = ("/{id}"))
    public String eliminarPorId(@PathVariable("id") Long id){
        boolean ok = this.usuarioService.eliminarUsuario(id);
        if (ok){
            return "Se eliminó el usuario con id "+id;
        }else{
            return "No pudo eliminar el usuario con id "+id;
        }
    }
}
