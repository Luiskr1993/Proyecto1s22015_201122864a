/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package estructuras;

/**
 *
 * @author Luiskr
 */
public class NodoEstacionClave {
    
    int idEstacion;
    int fe;
    String password, nombre;
    
    NodoEstacionClave izquierda, derecha;
    
    public NodoEstacionClave(int idEstacion, String nombre,  String password){
        this.idEstacion = idEstacion;
        this.password = password;
        this.nombre = nombre;
        this.fe = 0;
        this.izquierda = null;
        this.derecha = null;
    }
    
}
