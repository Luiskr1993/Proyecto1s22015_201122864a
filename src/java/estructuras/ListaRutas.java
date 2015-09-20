/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package estructuras;

import java.io.FileWriter;
import java.io.PrintWriter;
import javax.swing.JOptionPane;

/**
 *
 * @author Luiskr
 */
public class ListaRutas {
    NodoRuta primero;
    NodoRuta ultimo;
    
    int contadorRutas;
    
    public ListaRutas(){
        this.contadorRutas = 0;
        this.primero = null;
        this.ultimo = null;
    }
    
    
    public void insertar(int idEstacion){
        
        NodoRuta temp, nuevo;
        temp = primero;
        
        nuevo = new NodoRuta(idEstacion);
        
        if(primero == null){
            primero = nuevo;
            ultimo = primero;
        }
        else{
            ultimo.siguiente = nuevo;
            nuevo.anterior = ultimo;
            ultimo = nuevo;
        }
        contadorRutas++;
    }
    
   public NodoRuta buscarRuta(int id){
       
       NodoRuta temp;
       
       temp = primero;
       
       if(temp == null){
           System.out.println("la lista esta vacia");
       }
       else{
           if(temp.idEstacion == id){
               return temp;
           }
           else{
               int i=1;
               while(temp.idEstacion != id && i<= contadorRutas){
                   if(temp.siguiente != null){
                       temp = temp.siguiente;
                   }
                   else{
                       return null;
                   }
                   i++;
                   
               }
               
               
               
               return temp;
           }
       }
       
       return null;
   }
   
   public void eliminarNodo(int id){
        NodoRuta temp, temp2;
        temp = primero;
        
        if(temp == null){
                JOptionPane.showMessageDialog(
               null,
                "No se encontro el elemento a eliminar");
        }
        else{
            while(temp.idEstacion != id){
                if(temp.siguiente != null){
                    temp = temp.siguiente;
                }
                
                else if(temp.anterior == null){
                        primero = null;
                }
              
            }
            
            if(temp == primero){
                primero = temp.siguiente;
                
            }
            else{
                
                if(temp == ultimo){
                    ultimo = temp.anterior;
                    //ultimo.siguiente = null;
                }
                else{
                    temp2 = temp.siguiente;
                    
                    temp2.anterior = temp.anterior;
                    temp.anterior.siguiente = temp2;
                }
                
            }
            
            
        }
        
        contadorRutas--;
        
    }
   
   
    public String imprimir() {
        NodoRuta temp;
        String cadena="";
        temp = primero;

        if (temp == null) {
            System.out.println("lista vacia");
        } else {

            System.out.println("********************************lista de Fechas*********************");
            for (int i = 1; i <= contadorRutas; i++) {

                if (temp == null) {
                    System.out.println("nulo en i: " + i);
                } else {
                    System.out.println("Dia: " + temp.idEstacion);
                    cadena += "Estacion: "+ temp.idEstacion + "\n";
                    temp = temp.siguiente;
                }

            }
            return cadena;

        }
        
        return cadena;

    }
   
   public void graficar(){
         NodoRuta aux;
         
         aux = primero;
         FileWriter fichero = null;
        PrintWriter pw = null;
        
        if(aux == null){
            System.out.println("No hay elementos en la lista");
        }
        else
        {
            
            try
        {
            fichero = new FileWriter("C:\\Users\\Luiskr\\Documents\\NetBeansProjects\\WebServiceEDD_1\\Graphviz\\ListaRutas.dot");
            pw = new PrintWriter(fichero);
 
            pw.println("digraph Elementos{");
            pw.println("node [shape=record];");
            pw.println("rankdir=LR;");
            
            for(int i = 1; i<= contadorRutas; i++){
                
                String codigo = "e_"+aux.idEstacion+"[label = \"ID_Estacion: "+aux.idEstacion  + "\"];\n"; 
                
                pw.println(codigo);
                
                if(aux.siguiente != null){
                    String sentencia = "e_"+aux.idEstacion+"->e_"+aux.siguiente.idEstacion+";";
                    pw.println(sentencia);
                    String sentencia2 = "e_"+aux.siguiente.idEstacion+"->e_"+aux.idEstacion+";";
                    pw.println(sentencia2);
                
                    aux = aux.siguiente;
                }
                
            }
            pw.println("}\n");
           
            
            
            try {

//path del dot.exe,por lo general es la misma, pero depende de donde hayas instalado el paquete de Graphviz
                    String dotPath = "C:\\Program Files (x86)\\Graphviz2.38\\bin\\dot.exe";

//path del archivo creado con el codigo del graphviz que queremos
                    String fileInputPath = "C:\\Users\\Luiskr\\Documents\\NetBeansProjects\\WebServiceEDD_1\\Graphviz\\ListaRutas.dot";

//path de salida del grafo, es decir el path de la imagen que vamos a crear con graphviz
                    String fileOutputPath = "C:\\Users\\Luiskr\\Documents\\NetBeansProjects\\WebServiceEDD_1\\Graphviz\\ListaRutas.jpg";

//tipo de imagen de salida, en este caso es jpg
                    String tParam = "-Tjpg";

                    String tOParam = "-o";

//concatenamos nuestras direcciones. Lo que hice es crear un vector, para poder editar las direcciones de entrada y salida, usando las variables antes inicializadas

//recordemos el comando en la consola de windows: C:\Archivos de programa\Graphviz 2.21\bin\dot.exe -Tjpg grafo1.txt -o grafo1.jpg Esto es lo que concatenamos en el vector siguiente:

String[] cmd = new String[5];
cmd[0] = dotPath;
cmd[1] = tParam;
cmd[2] = fileInputPath;
cmd[3] = tOParam;
cmd[4] = fileOutputPath;

//Invocamos nuestra clase 

Runtime rt = Runtime.getRuntime();

//Ahora ejecutamos como lo hacemos en consola

rt.exec( cmd );

} catch (Exception ex) {
ex.printStackTrace();
}  finally {
}
                
 
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
           try {
           // Nuevamente aprovechamos el finally para 
           // asegurarnos que se cierra el fichero.
           if (null != fichero)
              fichero.close();
           } catch (Exception e2) {
              e2.printStackTrace();
           }
        }
            
        }
        
     }
}
