/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lotofacilapp;

import lotofacilapp.Modelo.Aposta;
import lotofacilapp.Modelo.DadosApostas;
import lotofacilapp.Modelo.ResultadoBusca;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javax.swing.JOptionPane;

/**
 *
 * @author joaosantanna
 */
public class FXMLDocumentController implements Initializable {

    private DadosApostas banco = new DadosApostas();
    private Aposta ap;

    @FXML
    private CheckBox megaCheck;
    @FXML
    private CheckBox facilCheck;

    @FXML
    private Button botaoCarregarJogos;
    @FXML
    private Button botaoSalvarJogos;
    @FXML
    private TextArea TextAreaTabela;

    @FXML
    private Button botaoApagarJogo;

    @FXML
    private TextField LabelAPagar;

    @FXML
    private Button botaoNovoJogo;

    @FXML
    private TextArea TextAreaMonitor;

    @FXML
    private Button botaoConferir;

    @FXML
    private TextField labelNovoJogo;

    @FXML
    private TextField labelConferirResultado;

    @FXML
    private Button LimparTela;

    @FXML
    void handleButtonActionNovoJogo(ActionEvent event) {

        ArrayList<Integer> numeros = new ArrayList<Integer>();
        String txt = labelNovoJogo.getText();

        //se string nao vazia
        if (txt.isEmpty()) {
        } else {
            //tratando a string para tirar só os numeros

            // bug - se o usuario entra primeiro com um espaco em brando da bug
            //tirar espacos em branco no inicio
            String s[] = txt.split(" ");

            for (int i = 0; i < s.length; i++) {

                numeros.add(Integer.parseInt(s[i]));
            }
            ap = new Aposta(numeros);
            
            //testa tipo
            
            if(megaCheck.isSelected())ap.setTipodeAposta("Megasena");
            if(facilCheck.isSelected())ap.setTipodeAposta("Lotofacil");
            
            banco.adicionarAposta(ap);
            TextAreaTabela.setText(banco.listarApostas());

        }
        labelNovoJogo.clear();
    }

    @FXML
    void handleButtonActionConferir(ActionEvent event) {

        ArrayList<Integer> numeros = new ArrayList<Integer>();
        String txt = labelConferirResultado.getText();

        //se string nao vazia
        if (txt.isEmpty()) {
        } else {
            // bug - se o usuario entra primeiro com um espaco em brando da bug 
            //tirar espacos em branco no inicio

            String s[] = txt.split(" ");

            for (int i = 0; i < s.length; i++) {

                numeros.add(Integer.parseInt(s[i]));
            }
            ArrayList< ResultadoBusca> re = banco.conferirApostas(numeros);

            String resposta = "Numeros sorteados \n";

            int i = 1;
            for (ResultadoBusca reBusca : re) {
                //implementar avisos para cara um dos acertos e os valores pagos!!
                if (reBusca.getNumeroAcertos() == 15) {
                    resposta += "\n PARABENS VOCE GANHOU NA LOTOFACIL!!!! \n";
                }

                resposta += "\n Jogo " + i + " Numero de acertos : " + reBusca.getNumeroAcertos()
                        + "\n Numeros :" + reBusca.getAcertos() + "\n";
                i++;
            }

            TextAreaMonitor.setText(resposta);

        }
        labelConferirResultado.clear();

    }

    @FXML
    void handleButtonActionLimparTela(ActionEvent event) {

        TextAreaMonitor.clear();
    }

    @FXML
    void handleButtonActionApagarJogo(ActionEvent event) {

        int i = Integer.parseInt(LabelAPagar.getText());
        i = i - 1; //ajuste ja que o array comeca em zero

        banco.remover(i);
        LabelAPagar.clear();
        TextAreaTabela.setText(banco.listarApostas());

    }

    @FXML
    void handleButtonActionBotaoCarregarJogos(ActionEvent event) {
        banco.carregarJogo();
        TextAreaTabela.setText(banco.listarApostas());
        TextAreaMonitor.setText("Jogos carregados na memoria com sucesso!!");

    }

    @FXML
    void handleButtonActionBotaoSalvarJogos(ActionEvent event) {

        banco.salvarJogos();

        TextAreaMonitor.setText("Jogo salvo com sucesso!!");

    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

}
