/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.myshop.Carrito;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.myshop.Producto.Producto;
import com.myshop.Usuario.Usuario;
import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 *
 * @author rlmoralesr
 */
@Entity
@Table(name = "carrito")
@NamedQueries({
    @NamedQuery(name = "Carrito.findAll", query = "SELECT c FROM Carrito c")
    , @NamedQuery(name = "Carrito.findByTotal", query = "SELECT c FROM Carrito c WHERE c.total = :total")
    , @NamedQuery(name = "Carrito.findByIdCarritoUsuario", query = "SELECT c FROM Carrito c WHERE c.idCarritoUsuario = :idCarritoUsuario")})
public class Carrito implements Serializable {

    private static final long serialVersionUID = 1L;
    @Basic(optional = false)
    @NotNull
    @Column(name = "total")
    private float total;
    @Id
    @Basic(optional = false)
    @Size(min = 1, max = 2147483647)
    @Column(name = "id_carrito_usuario")
    private String idCarritoUsuario;
    @JoinTable(name = "producto_carrito", joinColumns = {
        @JoinColumn(name = "id_carrito", referencedColumnName = "id_carrito_usuario")}, inverseJoinColumns = {
        @JoinColumn(name = "id_producto", referencedColumnName = "id_producto")})
    @ManyToMany(cascade = CascadeType.ALL)
    private List<Producto> productoList;
    @JoinColumn(name = "id_carrito_usuario", referencedColumnName = "correo", insertable = false, updatable = false)
    @OneToOne(optional = false)
    @JsonIgnore
    private Usuario usuario;

    public Carrito() {
    }

    public Carrito(String idCarritoUsuario) {
        this.idCarritoUsuario = idCarritoUsuario;
    }

    public Carrito(String idCarritoUsuario, float total) {
        this.idCarritoUsuario = idCarritoUsuario;
        this.total = total;
    }

    public float getTotal() {
        return total;
    }

    public void setTotal(float total) {
        this.total = total;
    }

    public String getIdCarritoUsuario() {
        return idCarritoUsuario;
    }

    public void setIdCarritoUsuario(String idCarritoUsuario) {
        this.idCarritoUsuario = idCarritoUsuario;
    }

    public List<Producto> getProductoList() {
        return productoList;
    }

    public void setProductoList(List<Producto> productoList) {
        this.productoList = productoList;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idCarritoUsuario != null ? idCarritoUsuario.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Carrito)) {
            return false;
        }
        Carrito other = (Carrito) object;
        if ((this.idCarritoUsuario == null && other.idCarritoUsuario != null) || (this.idCarritoUsuario != null && !this.idCarritoUsuario.equals(other.idCarritoUsuario))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.myshop.Carrito[ idCarritoUsuario=" + idCarritoUsuario + " ]";
    }
    
}
