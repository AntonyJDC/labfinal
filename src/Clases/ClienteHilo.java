package Clases;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ClienteHilo extends Thread {

    private DataInputStream in;
    private DataOutputStream out;
    Scanner sn = new Scanner(System.in);

    public ClienteHilo(DataInputStream in, DataOutputStream out) {
        this.in = in;
        this.out = out;
    }

    @Override
    public void run() {

        String mensaje, tiempo;
        int opcion, opc = 0;
        boolean salir = false;
        int n = 0;
        ArrayList<Integer> vectorOrganizado = new ArrayList<>();
        ArrayList<Integer> vec = new ArrayList<>();
        int limite;

        while (!salir) {

            try {
                System.out.println(" ");
                System.out.println("1. Algoritmo de Mergesort");
                System.out.println("2. Algoritmo de Heapsort");
                System.out.println("3. Algoritmo de Quicksort");
                System.out.println("4. Salir");

                opcion = sn.nextInt();
                out.writeInt(opcion);

                switch (opcion) {
                    case 1:
                        vectorOrganizado.clear();
                        // pedimos el numero de datos del vector
                        System.out.println("Ingrese el numero de datos del vector");
                        n = sn.nextInt();
                        // añadimos la cantidad de datos requeridas al vector mediante el metodo genVector
                        vec = genVector(n);
                        // mandamos los datos del vector al servidor
                        out.writeInt(vec.size()); // limite en el servidor
                        for (int v : vec) {
                            out.writeInt(v);
                        }
                        // Leemos los datos suministrados por el servidor y los añadimos a un nuevo vector
                        limite = in.readInt();
                        for (int i = 0; i < limite; i++) {
                            vectorOrganizado.add(in.readInt());
                        }
                        // imprimimos el vector resultante
                        System.out.println("VECTOR ORGANIZADO:");
                        for (int i = 0; i < vectorOrganizado.size(); i++) {
                            System.out.print(vectorOrganizado.get(i) + " ");
                        }
                        System.out.println(" ");
                        tiempo = in.readUTF();
                        System.out.println(" " + tiempo);

                        break;
                    case 2:
                        vectorOrganizado.clear();
                        // pedimos el numero de datos del vector
                        System.out.println("Ingrese el numero de datos del vector");
                        n = sn.nextInt();
                        // añadimos la cantidad de datos requeridas al vector mediante el metodo genVector
                        vec = genVector(n);
                        // mandamos los datos del vector al servidor
                        out.writeInt(vec.size());
                        for (int v : vec) {
                            out.writeInt(v);
                        }
                        // Leemos los datos suministrados por el servidor y los añadimos a un nuevo vector
                        limite = in.readInt();
                        for (int i = 0; i < limite; i++) {
                            vectorOrganizado.add(in.readInt());
                        }
                        // imprimimos el vector resultante
                        System.out.println("VECTOR ORGANIZADO:");
                        for (int i = 0; i < vectorOrganizado.size(); i++) {
                            System.out.print(vectorOrganizado.get(i) + " ");
                        }
                        System.out.println(" ");
                        tiempo = in.readUTF();
                        System.out.println(" " + tiempo);
                        break;
                    case 3:
                        System.out.println("1. Pivote inicial");
                        System.out.println("2. pivote final");

                        opc = sn.nextInt();
                        out.writeInt(opc);
                        switch (opc) {
                            case 1:
                                vectorOrganizado.clear();
                                System.out.println("Ingrese el numero de datos del vector");
                                n = sn.nextInt();
                                // añadimos la cantidad de datos requeridas al vector mediante el metodo genVector
                                vec = genVector(n);
                                // mandamos los datos del vector al servidor
                                out.writeInt(vec.size());
                                for (int v : vec) {
                                    out.writeInt(v);
                                }
                                // Leemos los datos suministrados por el servidor y los añadimos a un nuevo vector
                                limite = in.readInt();
                                for (int i = 0; i < limite; i++) {
                                    vectorOrganizado.add(in.readInt());
                                }
                                // imprimimos el vector resultante
                                System.out.println("VECTOR ORGANIZADO:");
                                for (int i = 0; i < vectorOrganizado.size(); i++) {
                                    System.out.print(vectorOrganizado.get(i) + " ");
                                }
                                System.out.println(" ");
                                tiempo = in.readUTF();
                                System.out.println(" " + tiempo);
                                break;
                            case 2:
                                vectorOrganizado.clear();
                                System.out.println("Ingrese el numero de datos del vector");
                                n = sn.nextInt();
                                // añadimos la cantidad de datos requeridas al vector mediante el metodo genVector
                                vec = genVector(n);
                                // mandamos los datos del vector al servidor
                                out.writeInt(vec.size());
                                for (int v : vec) {
                                    out.writeInt(v);
                                }
                                // Leemos los datos suministrados por el servidor y los añadimos a un nuevo vector
                                limite = in.readInt();
                                for (int i = 0; i < limite; i++) {
                                    vectorOrganizado.add(in.readInt());
                                }
                                // imprimimos el vector resultante
                                System.out.println("VECTOR ORGANIZADO:");
                                for (int i = 0; i < vectorOrganizado.size(); i++) {
                                    System.out.print(vectorOrganizado.get(i) + " ");
                                }
                                System.out.println(" ");
                                tiempo = in.readUTF();
                                System.out.println(" " + tiempo);
                                break;
                            default:
                                mensaje = in.readUTF();
                                System.out.println(mensaje);
                        }
                        break;
                    case 4:
                        salir = true;
                        break;
                    default:
                        mensaje = in.readUTF();
                        System.out.println(mensaje);

                }
            } catch (IOException ex) {
                Logger.getLogger(ClienteHilo.class.getName()).log(Level.SEVERE, null, ex);
            }

        }

    }

    public ArrayList<Integer> genVector(int n) {
        ArrayList<Integer> vector = new ArrayList<Integer>();
        for (int i = 1; i <= n; i++) {
            System.out.println("Ingrese el numero: " + i);
            int num = sn.nextInt();
            vector.add(num);
        }
        return vector;
    }
}
