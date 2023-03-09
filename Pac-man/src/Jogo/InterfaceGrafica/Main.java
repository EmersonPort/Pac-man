package Jogo.InterfaceGrafica;


import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
/**
 * A Main inicia o modo grafico
 */
public class Main extends Application {
    
    @Override
    public void start(Stage stage) throws Exception {
       try{ 
        Parent root = FXMLLoader.load(getClass().getResource("menu.fxml"));
        
        Scene scene = new Scene(root);
        
        //stage.setResizable(false);
        stage.setScene(scene);
        Image icone = new Image("File:src/Jogo/imagens/R.png");// icone q vai aparecer na barra de aplicativos em execucao
        stage.getIcons().add(icone);
        stage.setTitle("Pac-Man");//titulo da tela
        stage.show();}
        catch(IOException e){
            System.out.println("Erro :" + e.getMessage());
        }
        catch(Exception e){
            System.out.println("Erro :" + e.getMessage());
        }
    }

    public static void main(String[] args) {
        launch(args);
        
    }
    
}
