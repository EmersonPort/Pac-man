package Jogo.Engine;

import Jogo.ElementosdoSistema.Pac;
import javafx.scene.input.KeyCode;
/** Classe responsavel por receber as informacoes do teclado e passalas para o Pac-man */
public class Joystick {
    private Pac p;
    private boolean Destravado;

    public Joystick(){
        Destravado= false;
    }

    /**Usa a Key para definir a orientacao do pac-man */
    public void Movimenta(KeyCode c){
        if(Destravado){
        
        switch (c) {
            // Char 1-> UP;  Char 2-> DOWN;  Char 3-> LEFT ; Char 4 -> RIGHT
            case UP:
                p.setchar('1');
                break;
            case DOWN:
                p.setchar('2');
                
                break;
            case LEFT:
                p.setchar('3');
                
                break;
            case RIGHT:
                p.setchar('4');
                break;
        
            default:
                break;
        }
        
    }
        

    }

    void setPac(Pac p){
        this.p =p;
        Destravado= true;
    }

    
}
