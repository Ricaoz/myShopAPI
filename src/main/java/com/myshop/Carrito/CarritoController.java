/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.myshop.Carrito;

import com.myshop.Producto.Producto;
import com.myshop.Producto.ProductoRepository;
import com.myshop.Usuario.Usuario;
import com.myshop.Usuario.UsuarioRepository;
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
public class CarritoController {
    
    @Autowired
    public CarritoRepository carritoRepository;
    @Autowired
    public UsuarioRepository usuarioRepository;
    @Autowired
    public ProductoRepository productoRepository;
    
    @RequestMapping("/carrito")
    public List<Carrito> getCarritos(){
        List<Carrito> carritos = new ArrayList<>();
        carritoRepository.findAll().forEach(carritos::add);
        
        return carritos;
    }
    
    @RequestMapping("/carrito/{id}")
    public Carrito getCarrito(@PathVariable String id){
        System.out.println("getCarrito");
        Carrito c = carritoRepository.findById(id).get();
        //return new CarritoResponse(c.getIdCarritoUsuario(), c.getProductoList(), c.getTotal());
        return c;
    }
    
    @RequestMapping(method=RequestMethod.POST, value="/carrito")
    public Carrito createCarrito(@RequestBody Carrito carrito){
        Usuario usuario = usuarioRepository.findById(carrito.getIdCarritoUsuario()).get();
        carrito.setUsuario(usuario);
        return carritoRepository.save(carrito);
    }
    
    @RequestMapping(method=RequestMethod.DELETE, value="/carrito/{idCarrito}/{productoId}")
    public Carrito eliminarProductoCarrito(@PathVariable String idCarrito, @PathVariable int productoId){
        Carrito carrito = carritoRepository.findById(idCarrito).get();
        List<Producto> productos = carrito.getProductoList();
        Producto producto = productoRepository.findById(productoId).get();
        boolean b = productos.remove(producto);
        System.out.println("remove: " + b);
        carrito.setProductoList(productos);
        carrito.setTotal((float) (carrito.getTotal() - producto.getPrecio()));
        return carritoRepository.save(carrito);
    }
    
    @RequestMapping(method=RequestMethod.POST, value="/carrito/{id}")
    public Carrito agregarProducto(@RequestBody Producto producto, @PathVariable String id){
        Carrito carrito = carritoRepository.findById(id).get();
        List<Producto> productos = carrito.getProductoList();
        productos.add(producto);
        carrito.setProductoList(productos);
        carrito.setTotal((float) (carrito.getTotal() + producto.getPrecio()));
        return carritoRepository.save(carrito);
    }
    
}
