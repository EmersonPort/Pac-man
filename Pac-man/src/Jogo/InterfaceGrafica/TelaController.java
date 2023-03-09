package Jogo.InterfaceGrafica;
import java.net.URL;
import java.util.ResourceBundle;
import Jogo.Engine.Joystick;
import Jogo.Engine.engine;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.scene.canvas.*;
import java.io.IOException;
/** Classe responsavel pela tela do menu e por iniciar o Jogo */
public class TelaController  implements Initializable  {
    private Stage stage;
    private Scene scene;
    private Joystick joystick;
    private engine engine;
    Canvas canvas;    


    @FXML
    private Button bSair;

    @FXML
    private Button bStart;
    
    @FXML
    private void clicouStart(ActionEvent event)  {
        try{
        joystick= new Joystick();
        AnchorPane root= FXMLLoader.<AnchorPane>load(getClass().getResource("TelaJogo.fxml"));// Carrega tela do jogo
        stage =(Stage) ((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        canvas = new Canvas(615,500);// Onde vai ser impresso o jogo
        root.getChildren().add(canvas);
        scene.setOnKeyPressed(new EventHandler<KeyEvent>(){// recebe a tecla do teclado e passa pro joystick
            @Override
            public void handle(KeyEvent event){
                joystick.Movimenta(event.getCode());
            }
        } );


        stage.setScene(scene);
        stage.show();
        stage.centerOnScreen(); }
        catch(IOException e){
            System.out.println("Erro ao começar o jogo: "+e.getMessage());
        }   
        engine= new engine(joystick);// inicia a engine do jogo
        try{engine.Play(canvas);}// comeca o jogo
        catch(RuntimeException e){
           System.out.println("Erro ao executar a engine : " + e.getMessage()); } 
    }
    
    @FXML
    private void clicouSair(ActionEvent event) {
        Stage stage = (Stage) bSair.getScene().getWindow();
        stage.close();
        
    }

    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
    }    
    
}
