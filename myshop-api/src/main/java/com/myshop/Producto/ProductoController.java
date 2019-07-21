package com.myshop.Producto;

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
public class ProductoController {
    
    @Autowired
    ProductoRepository productoRepository;
    
    @RequestMapping("/productos")
    public List<Producto> getProductos(){
        List<Producto> productos = new ArrayList<>();
        productoRepository.findAll().forEach(productos::add);
        
        return productos;
    }
    
    @RequestMapping("/productos/{id}")
    public Producto getProductos(@PathVariable int id){
        return productoRepository.findById(id).get();
    }
    
    @RequestMapping(method=RequestMethod.POST, value="/productos")
    public String saveProducto(@RequestBody Producto producto){
        productoRepository.save(producto);
        return "Producto creado!";
    }
    
}
