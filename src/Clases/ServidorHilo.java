/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Clases;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Fernando
 */
public class ServidorHilo extends Thread {

    private DataInputStream in;
    private DataOutputStream out;
    private String nombreCliente;

    public ServidorHilo(DataInputStream in, DataOutputStream out, String nombreCliente) {
        this.in = in;
        this.out = out;
        this.nombreCliente = nombreCliente;
    }

    @Override
    public void run() {

        int opcion, opc;
        int limite;
        ArrayList<Integer> vector = new ArrayList<>();
        long TInicio, TFin, tiempo;

        while (true) {

            try {
                opcion = in.readInt();
                switch (opcion) {
                    case 1:
                        vector.clear();
                        TInicio = System.currentTimeMillis();
                        // leemos el vector suministrado por el cliente
                        limite = in.readInt();
                        for (int i = 0; i < limite; i++) {
                            vector.add(in.readInt());
                        }
                        // hacemos el llamado de nuestra clase
                        Mergesort mer = new Mergesort(vector);
                        // llamamos al método encargado de organizar el vector
                        mer.divideArrayElements(0, vector.size() - 1);
                        // avisamos a nuestro cliente que el vector ya está organizado
                        System.out.println("Arraylist Organizado, enviando vector al cliente");
                        // enviamos el vector a nuestro cliente
                        out.writeInt(vector.size());
                        for (int v : vector) {
                            out.writeInt(v);
                        }
                        TFin = System.currentTimeMillis();
                        tiempo = TFin - TInicio;
                        out.writeUTF("Tiempo de organizacion en milisegundos: " + tiempo);
                        break;

                    case 2:
                        vector.clear();
                        TInicio = System.currentTimeMillis();
                        // leemos el vector suministrado por el cliente
                        limite = in.readInt();
                        for (int i = 0; i < limite; i++) {
                            vector.add(in.readInt());
                        }
                        // convertimos el arraylist a un int array
                        int[] arr = new int[vector.size()];
                        for (int i = 0; i < vector.size(); i++) {
                            arr[i] = vector.get(i);
                        }
                        // llamamos la clase y el metodo para organizar el vector
                        int n = arr.length;
                        Heapsort ob = new Heapsort();
                        ob.sort(arr);
                        // avisamos a nuestro cliente que el vector ya está organizado
                        System.out.println("Arraylist Organizado, enviando al cliente");
                        // enviamos el vector a nuestro cliente
                        out.writeInt(arr.length);
                        for (int v : arr) {
                            out.writeInt(v);
                        }
                        TFin = System.currentTimeMillis();
                        tiempo = TFin - TInicio;
                        out.writeUTF("Tiempo de organizacion en milisegundos:  " + tiempo);
                        break;

                    case 3:
                        vector.clear();
                        opc = in.readInt();
                        switch (opc) {
                            case 2:
                                vector.clear();
                                TInicio = System.currentTimeMillis();
                                limite = in.readInt();
                                for (int i = 0; i < limite; i++) {
                                    vector.add(in.readInt());
                                }
                                // convertimos el arraylist a un int array
                                int[] arr2 = new int[vector.size()];
                                for (int i = 0; i < vector.size(); i++) {
                                    arr2[i] = vector.get(i);
                                }
                                // llamamos la clase y el metodo para organizar el vector
                                int n2 = arr2.length;
                                Quicksort quick = new Quicksort();
                                quick.quickSort(arr2, 0, n2 - 1);
                                // avisamos a nuestro cliente que el vector ya está organizado
                                System.out.println("Arraylist Organizado, enviando al cliente");
                                // enviamos el vector a nuestro cliente
                                out.writeInt(arr2.length);
                                for (int v : arr2) {
                                    out.writeInt(v);
                                }
                                TFin = System.currentTimeMillis();
                                tiempo = TFin - TInicio;
                                out.writeUTF("Tiempo de organizacion en milisegundos:  " + tiempo);
                                break;
                            case 1:
                                vector.clear();
                                TInicio = System.currentTimeMillis();
                                limite = in.readInt();
                                for (int i = 0; i < limite; i++) {
                                    vector.add(in.readInt());
                                }
                                // convertimos el arraylist a un int array
                                int[] arr3 = new int[vector.size()];
                                for (int i = 0; i < vector.size(); i++) {
                                    arr3[i] = vector.get(i);
                                }
                                // llamamos la clase y el metodo para organizar el vector
                                int n3 = arr3.length;
                                Quicksort2 quick2 = new Quicksort2();
                                quick2.quicksort(arr3, 0, n3 - 1);
                                // avisamos a nuestro cliente que el vector ya está organizado
                                System.out.println("Arraylist Organizado, enviando al cliente");
                                // enviamos el vector a nuestro cliente
                                out.writeInt(arr3.length);
                                for (int v : arr3) {
                                    out.writeInt(v);
                                }
                                TFin = System.currentTimeMillis();
                                tiempo = TFin - TInicio;
                                out.writeUTF("Tiempo de organizacion en milisegundos:  " + tiempo);
                                break;
                            default:
                                out.writeUTF("Solo numero del 1 al 2");
                        }
                        break;

                    case 4:
                        
                        break;
                    default:
                        out.writeUTF("Solo numero del 1 al 6");
                }

            } catch (IOException ex) {
                Logger.getLogger(ServidorHilo.class.getName()).log(Level.SEVERE, null, ex);
            }

        }

    }
}
