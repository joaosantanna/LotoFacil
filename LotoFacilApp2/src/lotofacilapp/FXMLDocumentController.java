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
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

/**
 *
 * @author joaosantanna
 */
public class FXMLDocumentController implements Initializable {

    private DadosApostas banco = new DadosApostas();
    private Aposta ap;

    //checkbox utilizados para novo jogo
    @FXML
    private CheckBox megaCheck;
    @FXML
    private CheckBox facilCheck;
    @FXML
    private CheckBox maniaCheck;

    //checkbox utilizados para conferir jogos
    @FXML
    private CheckBox megaCheck2;
    @FXML
    private CheckBox facilCheck2;
    @FXML
    private CheckBox maniaCheck2;

    @FXML
    private Button botaoCarregarJogos;
    @FXML
    private Button botaoSalvarJogos;
    @FXML
    private Button botaoEditarJogo;
    @FXML
    private TextArea TextAreaTabela;

    @FXML
    private Button botaoApagarJogo;

    @FXML
    private TextField textFieldAPagar;

    @FXML
    private Button botaoNovoJogo;

    @FXML
    private TextArea TextAreaMonitor;

    @FXML
    private Button botaoConferir;

    @FXML
    private Button botaoListarApostas;

    @FXML
    private TextField textFieldNovoJogo;

    @FXML
    private TextField textFieldConferirResultado;

    @FXML
    private Button LimparTela;

    //metodos da interface
    @FXML
    void handleButtonActionNovoJogo(ActionEvent event) {

        ArrayList<Integer> numeros = new ArrayList<Integer>();
        //pega os numeros para a aposta
        String txt = textFieldNovoJogo.getText();

        //variavel para teste se algum tipo de jogo foi selecionado
        boolean tipoJogoSelecionado = false; // inicialmente nada selecionado

        String tipo = " ";
        if (megaCheck.isSelected()) {
            tipo = "Mega Sena";
            tipoJogoSelecionado = true;
        } else if (facilCheck.isSelected()) {
            tipo = "Loto Facil";
            tipoJogoSelecionado = true;
        } else if (maniaCheck.isSelected()) {
            tipo = "Loto Mania";
            tipoJogoSelecionado = true;
        } else {
            //se nenhum tipo de jogo selecionado ... enviar mensagem
            //para a tela ... depois colocar em janela de avisos!!
            this.erroSelacaodeJogo();
        }

        //se string vazia ou tipo de jogo não selecionado nao faz nada
        //so manda msg de erro
        if (txt.isEmpty() || (!tipoJogoSelecionado)) {
            //se campo vazio
            if (txt.isEmpty()) {
                this.erroSemNumeros();
            }
            //se nenhum tipo selecionado
            if (!tipoJogoSelecionado) {
                this.erroSelacaodeJogo();
            }

        } else {
            //se a string nao estiver vazia e jogo selecionado processa o resto
            //tratando a string para tirar só os numeros

            // bug - se o usuario entra primeiro com um espaco em brando da bug
            //tirar espacos em branco no inicio
            String s[] = txt.split(" ");

            for (String item : s) {
                numeros.add(Integer.parseInt(item));
            }

            ap = new Aposta(numeros, tipo);

            banco.adicionarAposta(ap);
            TextAreaTabela.setText(banco.listarApostas());
            

        }
           textFieldNovoJogo.clear(); //limpa campo
            maniaCheck.setSelected(false);
            facilCheck.setSelected(false);
            megaCheck.setSelected(false);
  
    }

    @FXML
    void handleButtonActionConferir(ActionEvent event) {

        ArrayList<Integer> numeros = new ArrayList<Integer>();
        String txt =textFieldConferirResultado.getText();

        //variavel para teste se algum tipo de jogo foi selecionado
        boolean tipoJogoSelecionado = false; // inicialmente nada selecionado

        
        if (megaCheck2.isSelected()) {
            tipoJogoSelecionado = true;
        } else if (facilCheck2.isSelected()) {
            tipoJogoSelecionado = true;
        } else if (maniaCheck2.isSelected()) {
            tipoJogoSelecionado = true;
        } else {
            //se nenhum tipo de jogo selecionado ... enviar mensagem
            //para a tela ... depois colocar em janela de avisos!!
            this.erroSelacaodeJogo();
        }

        //se string vazia ou tipo de jogo não selecionado nao faz nada
        //so manda msg de erro
        if (txt.isEmpty() || (!tipoJogoSelecionado)) {
            //se campo vazio
            if (txt.isEmpty()) {
                this.erroSemNumeros();
            }
            //se nenhum tipo selecionado
            if (!tipoJogoSelecionado) {
                this.erroSelacaodeJogo();
            } 
        }else {
                // bug - se o usuario entra primeiro com um espaco em brando da bug 
                //tirar espacos em branco no inicio

                String s[] = txt.split(" ");

                for (String item : s) {
                    numeros.add(Integer.parseInt(item));
                }
                ArrayList< ResultadoBusca> re = banco.conferirApostas(numeros);

                String resposta = "Numeros sorteados \n";
                
                //formata a resposta para mega sena apenas
                if (megaCheck2.isSelected()) {
                    resposta += "Mega Sena \n";
                    for (ResultadoBusca reBusca : re) {
                    //implementar avisos para cara um dos acertos e os valores pagos!!  

                        if (reBusca.getTipo().equals("Mega Sena")) {
                            resposta += "\n" + reBusca.getTipo() + "\n Numero de acertos : " + reBusca.getNumeroAcertos()
                                    + "\n Numeros :" + reBusca.getAcertos() + "\n";
                        }
                    }
                }
                //formata a resposta para a loto facil
                if (facilCheck2.isSelected()) {
                    resposta += "Loto Facil \n";
                    for (ResultadoBusca reBusca : re) {
                    //implementar avisos para cara um dos acertos e os valores pagos!!  

                        if (reBusca.getTipo().equals("Loto Facil")) {
                            resposta += "\n" + reBusca.getTipo() + "\n Numero de acertos : " + reBusca.getNumeroAcertos()
                                    + "\n Numeros :" + reBusca.getAcertos() + "\n";
                        }
                    }
                }

                //formata a resposta para a loto facil
                if (maniaCheck2.isSelected()) {
                    resposta += "Loto Mania \n";
                    for (ResultadoBusca reBusca : re) {
                    //implementar avisos para cara um dos acertos e os valores pagos!!  

                        if (reBusca.getTipo().equals("Loto Mania")) {
                            resposta += "\n" + reBusca.getTipo() + "\n Numero de acertos : " + reBusca.getNumeroAcertos()
                                    + "\n Numeros :" + reBusca.getAcertos() + "\n";
                        }
                    }
                }

                TextAreaTabela.setText(resposta);

            }
           textFieldConferirResultado.clear();
           maniaCheck2.setSelected(false);
            facilCheck2.setSelected(false);
            megaCheck2.setSelected(false);

        }
    

    @FXML
    void handleButtonActionLimparTela(ActionEvent event) {

        TextAreaTabela.clear();
        }

    @FXML
    void handleButtonActionApagarJogo(ActionEvent event) {

        int i = Integer.parseInt(textFieldAPagar.getText());
            i = i - 1; //ajuste ja que o array comeca em zero

            banco.remover(i);
            textFieldAPagar.clear();
            TextAreaTabela.setText(banco.listarApostas());

        }

    @FXML
    void handleButtonActionEditarJogo(ActionEvent event) {
        int i = Integer.parseInt(textFieldAPagar.getText());
        i = i - 1; //ajuste ja que o array comeca em zero
        
        Aposta ap = banco.getAposta(i);
        String txt = ap.getAposta();
        this.textFieldNovoJogo.setText(txt); // carrega os numeros no field
        String tipo = ap.getTipodeAposta();
        
        if(tipo.equals("Mega Sena")){
            this.megaCheck.setSelected(true);
        }
        if(tipo.equals("Loto Facil")){
            this.facilCheck.setSelected(true);
        }
        if(tipo.equals("Loto Mania")){
            this.maniaCheck.setSelected(true);
        }
        //remove a aposta do banco depois de recuperar.
        
            
    }
        
        
    @FXML
    void handleButtonActionBotaoCarregarJogos(ActionEvent event) {
        banco.carregarJogo();
            TextAreaTabela.setText(banco.listarApostas());
            TextAreaTabela.appendText("\n Jogos carregados na memoria com sucesso!!");

        }

    @FXML
    void handleButtonActionBotaoSalvarJogos(ActionEvent event) {

        banco.salvarJogos();

            TextAreaTabela.setText("Jogo salvo com sucesso!!");

        }
    
    @FXML
    void handleMenuClose(ActionEvent event) throws Throwable {
       System.exit(0);

    }
    
    @FXML
    void handleButtonActioListarApostas(ActionEvent event) {
        
        String apostas = banco.listarApostas();
            TextAreaTabela.setText(apostas);

        }

    
    @FXML
    void handleCheckMega(ActionEvent event) {
        maniaCheck.setSelected(false);
        facilCheck.setSelected(false);
        

    }

    @FXML
    void handleCheckFacil(ActionEvent event) {
        maniaCheck.setSelected(false);
        
        megaCheck.setSelected(false);

    }

    @FXML
    void handleCheckMania(ActionEvent event) {

        
        facilCheck.setSelected(false);
        megaCheck.setSelected(false);
    }

     @FXML
    void handleCheckMega2(ActionEvent event) {
            maniaCheck2.setSelected(false);
            facilCheck2.setSelected(false);
            
    }

    @FXML
    void handleCheckFacil2(ActionEvent event) {
            maniaCheck2.setSelected(false);
            
            megaCheck2.setSelected(false);
    }

    @FXML
    void handleCheckMania2(ActionEvent event) {
        
        facilCheck2.setSelected(false);
        megaCheck2.setSelected(false);
    }
    

   
private void erroSelacaodeJogo() {
        TextAreaTabela.setText("Você tem que selecionar um tipo de JOGO!!!!");
    }

    private void erroSemNumeros() {
        TextAreaTabela.setText("Você tem que digitar numeros para a aposta!!!!");
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        
     this.botaoEditarJogo.setDisable(true);
    }

}
