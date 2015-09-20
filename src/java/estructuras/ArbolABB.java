package estructuras;

import java.io.FileWriter;
import java.io.PrintWriter;

/**
 *
 * @author Luiskr
 */

public class ArbolABB {

    NodoABB raiz;
    
    int contadorNodos;

    String cadena="";
    public ArbolABB() {
        this.raiz = null;
        this.contadorNodos = 0;
    }

    public void insertarElemento(int id, String nombre, String apellido, String password) {

        NodoABB nuevo = new NodoABB(id, nombre, apellido, password);

        if (raiz == null) {
            raiz = inserta(nuevo, raiz);
        } else {
            raiz = inserta(nuevo, raiz);
        }

    }

    public NodoABB inserta(NodoABB nuevo, NodoABB raiz) {

        if (raiz == null) {
            //el nodo es nulo entonces se inserta alli el nodo
            raiz = nuevo;
            System.out.println("SubRaiz: " + raiz.id);

        } else {
            if (nuevo.id < raiz.id) {
                //es menor que la raiz entonces se inserta a la izquierda
                raiz.izquierda = inserta(nuevo, raiz.izquierda);
                raiz.fe = altura(raiz.derecha) - altura(raiz.izquierda);

                if (raiz.fe < -1) {
                    if (raiz.izquierda.fe == -1) {
                        System.out.println("Hay que hacer una rotacion I.I");
                        raiz = rotarII(raiz, raiz.izquierda);
                    } else if (raiz.izquierda.fe == 1) {
                        System.out.println("hay que hacer una rotacion I.D.");
                        raiz = rotarID(raiz, raiz.izquierda, raiz.izquierda.derecha);
                    }
                }
                System.out.println("Se inserto a la izquierda");

            } else if (nuevo.id > raiz.id) {
                //es mayor que la raiz entonces se inserta a la derecha
                raiz.derecha = inserta(nuevo, raiz.derecha);
                raiz.fe = altura(raiz.derecha) - altura(raiz.izquierda);

                if (raiz.fe > 1) {
                    if (raiz.derecha.fe == 1) {
                        System.out.println("hay que hacer una rotacion D.D");
                        raiz = rotarDD(raiz, raiz.derecha);
                    } else if (raiz.derecha.fe == -1) {
                        System.out.println("hay que hacer una rotacion D.I.");
                        raiz = rotarDI(raiz, raiz.derecha, raiz.derecha.izquierda);
                    }
                }
                System.out.println("Se inserto a la derecha");

            } else if (nuevo.id == raiz.id) {
                System.out.println("El elemento ya existe dentro del arbol");
            }

            // return raiz;
        }
        contadorNodos++;

        return raiz;

    }

    public int altura(NodoABB raiz) {
        if (raiz == null) {
            return 0;
        } else {
            int profI = altura(raiz.izquierda);
            int profD = altura(raiz.derecha);
            if (profI > profD) {
                return profI + 1;
            } else {
                return profD + 1;
            }
        }

    }

    public NodoABB rotarII(NodoABB n, NodoABB n1) {

        n.fe = 0;
        n1.fe = 0;

        n.izquierda = n1.derecha;
        n1.derecha = n;
        n = n1;

        return n;
    }

    public NodoABB rotarID(NodoABB n, NodoABB n1, NodoABB n2) {

        if (n2.fe == -1) {
            n.fe = 1;
            n1.fe = 0;
            n2.fe = 0;
        }
        if (n2.fe == 0) {
            n.fe = 0;
            n1.fe = 0;
            n2.fe = 0;
        }
        if (n2.fe == 1) {
            n.fe = 0;
            n1.fe = -1;
            n2.fe = 0;
        }

        n1.derecha = n2.izquierda;
        n2.izquierda = n1;
        n.izquierda = n2.derecha;
        n2.derecha = n;
        n = n2;

        return n;
    }

    public NodoABB rotarDD(NodoABB n, NodoABB n1) {
        n.fe = 0;
        n1.fe = 0;

        n.derecha = n1.izquierda;
        n1.izquierda = n;
        n = n1;

        return n;
    }

    public NodoABB rotarDI(NodoABB n, NodoABB n1, NodoABB n2) {

        if (n2.fe == -1) {
            n.fe = 0;
            n1.fe = 1;
            n2.fe = 0;
        }
        if (n2.fe == 0) {
            n.fe = 0;
            n1.fe = 0;
            n2.fe = 0;
        }
        if (n2.fe == 1) {
            n.fe = -1;
            n1.fe = 0;
            n2.fe = 0;
        }

        n1.izquierda = n2.derecha;
        n2.derecha = n1;
        n.derecha = n2.izquierda;
        n2.izquierda = n;
        n = n2;

        return n;
    }

    public String inOrden(NodoABB raiz) {
        
        if (raiz.izquierda != null) {
            inOrden(raiz.izquierda);
        }

        System.out.println("ID: " + raiz.id);
        cadena += "ID: "+ raiz.id + "  Nombre: "+raiz.nombre + "  ||  ";

        if (raiz.derecha != null) {
            inOrden(raiz.derecha);
        }
        
        
        return cadena;

    }

    public boolean busca(int id) {

        NodoABB encontrado;

        encontrado = buscar(raiz, id);

        if (encontrado == null) {
            return false;
        } else {
            return true;
        }
    }
    
     public String busca2(int id) {

        NodoABB encontrado;

        encontrado = buscar(raiz, id);

        if (encontrado == null) {
            return encontrado.password;
        } else {
            return "";
        }
    }
    

    public NodoABB buscar(NodoABB raiz, int id) {

        if (raiz != null) {

            if (raiz.id == id) {
                return raiz;
            } else if (raiz.id > id) {
                return buscar(raiz.izquierda, id);
            } else if (raiz.id < id) {
                return buscar(raiz.derecha, id);
            }
        }
        return null;

    }

    public boolean insertarBus(int idChofer, int idBus, int idDia, Double horaInicio, Double horaFinal, String nombreRuta) {
        NodoABB temp;
        NodoFecha temp2;
        NodoBus temp3;

        temp = buscar(raiz, idChofer);//buscar en el arbol AVL de chofer al chofer especifico

        if (temp == null) {
            return false;
        } else {
            temp2 = temp.fechas.buscarFecha(idDia);//busca en la lista de fechas del chofer encontrado la fecha en la que se desea asignar el bus

            if (temp2 == null) {//si la lista de fechas del chofer esta vacia, entonces se insertara la fecha en la lista
                temp.fechas.insertar(idDia, idBus);
                temp2 = temp.fechas.buscarFecha(idDia);

                if (temp2 != null) {
                    temp2.lista.insertar(idBus, horaInicio, horaFinal, nombreRuta);
                    return true;
                }
            } else {
                temp2.lista.insertar(idBus, horaInicio, horaFinal, nombreRuta);
                return true;
            }
        }

        return false;

    }

    public void graficar() {

        NodoABB aux;
        NodoABB iz, der;

        aux = raiz;

        FileWriter fichero = null;
        PrintWriter pw = null;

        if (aux == null) {
            System.out.println("No hay elementos en la lista");
        } else {

            try {
                fichero = new FileWriter("C:\\Users\\Luiskr\\Documents\\NetBeansProjects\\WebServiceEDD_1\\Graphviz\\arbolChoferes.dot");
                pw = new PrintWriter(fichero);

                pw.println("digraph Arbol{");
                pw.println("node [shape=record];");

                if (aux != null) {
                    grafica(fichero, aux);
                }

                pw.println("}\n");

                try {

//path del dot.exe,por lo general es la misma, pero depende de donde hayas instalado el paquete de Graphviz
                    String dotPath = "C:\\Program Files (x86)\\Graphviz2.38\\bin\\dot.exe";

//path del archivo creado con el codigo del graphviz que queremos
                    String fileInputPath = "C:\\Users\\Luiskr\\Documents\\NetBeansProjects\\WebServiceEDD_1\\Graphviz\\arbolChoferes.dot";

//path de salida del grafo, es decir el path de la imagen que vamos a crear con graphviz
                    String fileOutputPath = "C:\\Users\\Luiskr\\Documents\\NetBeansProjects\\WebServiceEDD_1\\Graphviz\\arbolChoferes.jpg";

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
                    rt.exec(cmd);

                } catch (Exception ex) {
                    ex.printStackTrace();
                } finally {
                }

            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                try {
                    // Nuevamente aprovechamos el finally para 
                    // asegurarnos que se cierra el fichero.
                    if (null != fichero) {
                        fichero.close();
                    }
                } catch (Exception e2) {
                    e2.printStackTrace();
                }
            }

        }
    }

    public void grafica(FileWriter fichero, NodoABB raiz) {

        PrintWriter pw = new PrintWriter(fichero);

        NodoABB aux = raiz;

        String a = "";
        if (aux != null) {
            String sentencia = "n_" + aux.id + "[label = \"<izq> |<dat> ID:" + aux.id + " \\n Nombre: " + aux.nombre + " \\n Apellido: " + aux.apellido + "\\n F.E:  " + aux.fe + " |<der>\"]";

            pw.println(sentencia);

            if (raiz.izquierda != null) {
                String s1 = "n_" + aux.id + ":izq -> n_" + aux.izquierda.id + ":dat;";
                pw.println(s1);
                grafica(fichero, aux.izquierda);
            }

            if (raiz.derecha != null) {
                String s2 = "n_" + aux.id + ":der -> n_" + aux.derecha.id + ":dat;";
                pw.println(s2);
                grafica(fichero, aux.derecha);
            }
        }
    }

}
