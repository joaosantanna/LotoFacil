/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package lotofacilapp.Modelo;

import java.util.ArrayList;

/**
 *
 * @author joaosantanna
 * 
 * faz a conferencia dos numeros e retorna o numero de acertos...
 * 
 */
public class ResultadoBusca {
    
    ArrayList <Integer> acertos ;
    int numeroAcertos;

    public ResultadoBusca(ArrayList<Integer> acertos, int numeroAcertos) {
        this.acertos = acertos;
        this.numeroAcertos = numeroAcertos;
    }

    public ArrayList<Integer> getAcertos() {
        return acertos;
    }

    public void setAcertos(ArrayList<Integer> acertos) {
        this.acertos = acertos;
    }

    public int getNumeroAcertos() {
        return numeroAcertos;
    }

    public void setNumeroAcertos(int numeroAcertos) {
        this.numeroAcertos = numeroAcertos;
    }
    
    public void imprimirResultado(){
        
        System.out.println("\n Numero de acertos:" + this.numeroAcertos);
        System.out.print("\n Numero certos:" );
        for (Integer i : acertos) {
            System.out.print(i + "\t");
        }
        
    }
    
}
