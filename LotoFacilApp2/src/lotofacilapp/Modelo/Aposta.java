/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package lotofacilapp.Modelo;

import java.io.Serializable;
import java.util.ArrayList;

/**
 *
 * @author joaosantanna
 * 
 * essa classe representa uma unica aposta com seus numeros todos inteiros
 * essa aposta pode ser uma aposta do tipo : lotofacil, megasena e até mesmo
 * outros tipos , dependendo da implementacao 
 * o array de inteiros não tem tamanho definido logo podemos fazer varios tipos
 * diferentes de apostas ou mesmo apostar em mais numeros ( tipo bolão onde 
 * se aposta mais de 6 numeros na megasena)
 * 
 */
public class Aposta implements Serializable{

    public Aposta() {
    }

    public Aposta(ArrayList<Integer> numeros) {
        this.numeros = numeros;
    }
    
    // vai guarda os numeros de 1 aposta
    private ArrayList < Integer> numeros;
    //vai guardar o tipo de aposta se mega, lotofacil , etc...
    private String tipodeAposta;
    
    
    public void adicionar(ArrayList<Integer> valores){
        
        for (int i : valores) {
            numeros.add(i);
        }
        
    }
    
    public ResultadoBusca conferir(ArrayList<Integer> valores){
        int numAcertos = 0;
        
        ArrayList <Integer> acertos = new ArrayList<Integer>();
        
        
        
       for (int i : valores) {
           if( numeros.contains(i)){//se o numero esta na aposta
               numAcertos ++;
               acertos.add(i);
           }
        }
        
        
        
        ResultadoBusca re = new ResultadoBusca(acertos,numAcertos);
        
        return re;
        
    }
    
    public String getAposta(){
        String ret="\t";
        for (Integer i : numeros) {
            
            ret += i.intValue() + "\t";
            
        }
        return ret;
    }

    public String getTipodeAposta() {
        return tipodeAposta;
    }

    public void setTipodeAposta(String tipodeAposta) {
        this.tipodeAposta = tipodeAposta;
    }
    
    
}
