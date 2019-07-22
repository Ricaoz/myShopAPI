/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.myshop.Usuario;

import com.myshop.Carrito.Carrito;
import com.myshop.Carrito.CarritoRepository;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author rlmoralesr
 */
@RestController
public class UsuarioController {
    
    @Autowired
    UsuarioRepository usuarioRepository;
    @Autowired
    public CarritoRepository carritoRepository;
    
    @RequestMapping("/usuarios")
    public List<Usuario> getUsuarios(){
        List<Usuario> usuarios = new ArrayList<>();
        usuarioRepository.findAll().forEach(usuarios::add);
        return usuarios;
    }
    
    @RequestMapping("/usuarios/{id}")
    public Usuario getUsuario(@PathVariable String id){
        return usuarioRepository.findById(id).get();
    }
    
    @RequestMapping(method=RequestMethod.POST, value="/usuarios")
    public String saveUser(@RequestBody Usuario usuario){
        usuarioRepository.save(usuario);
        Carrito carrito = new Carrito(usuario.getCorreo());
        carritoRepository.save(carrito);
        return "Usuario creado!";
    }
    
    @RequestMapping(method=RequestMethod.PUT, value="/usuarios")
    public String updateUser(@RequestBody Usuario usuario){
        usuarioRepository.save(usuario);
        
        return "Usuario modificado!";
    }
}
