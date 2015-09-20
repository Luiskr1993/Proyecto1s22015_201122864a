/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package estructuras;

import java.io.FileWriter;
import java.io.PrintWriter;

/**
 *
 * @author Luiskr
 */
public class ListaBuses {
    
    NodoBus primero, ultimo;
    int contadorBuses;
    
    public ListaBuses(){
        this.primero = null;
        this.ultimo = null;
        this.contadorBuses = 0;
        
    }
    
    public void insertar(int idBus,Double horaInicio, Double horaFinal, String nombreRuta){
        NodoBus temp, nuevo;
        temp = primero;
        
        nuevo = new NodoBus(idBus, horaInicio, horaFinal, nombreRuta);
        
        if(primero == null){
            primero = nuevo;
            ultimo = primero;
        }
        else{
            ultimo.siguiente = nuevo;
            nuevo.anterior = ultimo;
            ultimo = nuevo;
        }
        contadorBuses++;
    
    }
    
    public NodoBus buscarBus(int id){
        
        NodoBus temp;
       
       temp = primero;
       
       if(temp == null){
           System.out.println("la lista esta vacia");
       }
       else{
           if(temp.idBus == id){
               return temp;
           }
           else{
               int i=1;
               while(temp.idBus != id && i<= contadorBuses){
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
    
    
     public String imprimir() {
        NodoBus temp;
        String cadena="";
        temp = primero;

        if (temp == null) {
            System.out.println("lista vacia");
        } else {

            System.out.println("********************************lista de buses*********************");
            for (int i = 1; i <= contadorBuses; i++) {

                if (temp == null) {
                    System.out.println("nulo en i: " + i);
                } else {
                    System.out.println("Bus: " + temp.idBus);
                    cadena += "\nBus: "+ temp.idBus + "\n";
                    temp = temp.siguiente;
                }

            }
            
            return cadena;

        }
        
        return cadena;

    }
    
    public void graficar(){
         NodoBus aux;
         
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
            fichero = new FileWriter("C:\\Users\\Luiskr\\Documents\\NetBeansProjects\\WebServiceEDD_1\\Graphviz\\ListaGeneralBuses.dot");
            pw = new PrintWriter(fichero);
 
            pw.println("digraph Elementos{");
            pw.println("node [shape=record];");
            pw.println("rankdir=LR;");
            
            for(int i = 1; i<= contadorBuses; i++){
                
                String codigo = "e_"+aux.idBus+"[label = \"ID_Bus: "+aux.idBus  + "\"];\n"; 
                
                pw.println(codigo);
                
                if(aux.siguiente != null){
                    String sentencia = "e_"+aux.idBus+"->e_"+aux.siguiente.idBus+";";
                    pw.println(sentencia);
                    String sentencia2 = "e_"+aux.siguiente.idBus+"->e_"+aux.idBus+";";
                    pw.println(sentencia2);
                
                    aux = aux.siguiente;
                }
                
            }
            pw.println("}\n");
           
            
            
            try {

//path del dot.exe,por lo general es la misma, pero depende de donde hayas instalado el paquete de Graphviz
                    String dotPath = "C:\\Program Files (x86)\\Graphviz2.38\\bin\\dot.exe";

//path del archivo creado con el codigo del graphviz que queremos
                    String fileInputPath = "C:\\Users\\Luiskr\\Documents\\NetBeansProjects\\WebServiceEDD_1\\Graphviz\\ListaGeneralBuses.dot";

//path de salida del grafo, es decir el path de la imagen que vamos a crear con graphviz
                    String fileOutputPath = "C:\\Users\\Luiskr\\Documents\\NetBeansProjects\\WebServiceEDD_1\\Graphviz\\ListaGeneralBuses.jpg";

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
