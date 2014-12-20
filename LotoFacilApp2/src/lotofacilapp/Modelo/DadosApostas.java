/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lotofacilapp.Modelo;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author joaosantanna
 * 
 * Essa classe vai representar nosso banco de apostas e vai conter as diversas 
 * apostas guardadas no arraylist de apostas.
 */
public class DadosApostas implements Serializable {

    private ArrayList<Aposta> dados;

    public DadosApostas() {
        dados = new ArrayList<Aposta>();
    }

    public void adicionarAposta(Aposta ap) {
        dados.add(ap);
    }

    public void remover(int i) {
        dados.remove(i);
    }
    
    public String listarApostas(){
        
        String resultado="";
        int i=1;
        for (Aposta aposta : dados) {
         resultado += aposta.getTipodeAposta()+" Jogo "+ i +" : "+ aposta.getAposta() + "\n";
         i++;
        }
        return resultado;
        
    }
    
    
    public ArrayList< ResultadoBusca> conferirApostas(ArrayList<Integer>  numerosSorteados){
        
         ArrayList< ResultadoBusca> re = new ArrayList<ResultadoBusca>(); 
         
         for (Aposta ap : dados) {
             
            re.add(ap.conferir(numerosSorteados));
        }
         
        return re;
    }

    public void salvarJogos() {

        try {
            FileOutputStream fs = new FileOutputStream("jogos.lot");

            ObjectOutputStream os = new ObjectOutputStream(fs);

            os.writeObject(dados);

            os.close();

        } catch (FileNotFoundException ex) {
            Logger.getLogger(DadosApostas.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(DadosApostas.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public ArrayList<Aposta> carregarJogo() {

        try {
            FileInputStream fs = new FileInputStream("jogos.lot");

            ObjectInputStream os = new ObjectInputStream(fs);

            
            dados = (ArrayList<Aposta>) os.readObject();
            
            os.close();

        } catch (FileNotFoundException ex) {
            Logger.getLogger(DadosApostas.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(DadosApostas.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(DadosApostas.class.getName()).log(Level.SEVERE, null, ex);
        }

        
        return dados;
    }

}
