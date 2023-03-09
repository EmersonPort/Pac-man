package Jogo.InterfaceGrafica;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

/** Classe responsavel por imprimir todas as coisas q aparecem na tela do Jogo */
public class Tabuleiro {
    private Image Parede;
    private Image right,left,up,down;// Pac-man em suas posicoes
    private Image verm,verd,rosa,laran, azul; //Fantasmas
    private Image corac,dot,pilula,nada,barreira,gameover;
    private int vidas;
    Canvas canvas;
    

    //Tabuleiro do jogo
    private char Matriz[][]= { 
        {'|','|','|','|','|','|','|','|','|','|','|','|','|','|','|','|','|','|','|','|','|','|','|','|','|','|','|','|'},
        {'|','0','0','0','0','0','0','0','0','0','0','0','0','|','|','0','0','0','0','0','0','0','0','0','0','0','0','|'},
        {'|','0','|','|','|','|','0','|','|','|','|','|','0','|','|','0','|','|','|','|','|','0','|','|','|','|','0','|'},
        {'|','S','|','|','|','|','0','|','|','|','|','|','0','|','|','0','|','|','|','|','|','0','|','|','|','|','S','|'},
        {'|','0','|','|','|','|','0','|','|','|','|','|','0','|','|','0','|','|','|','|','|','0','|','|','|','|','0','|'},
        {'|','0','0','0','0','0','0','0','0','0','0','0','0','0','0','0','0','0','0','0','0','0','0','0','0','0','0','|'},
        {'|','0','|','|','|','|','0','|','|','0','|','|','|','|','|','|','|','|','0','|','|','0','|','|','|','|','0','|'},
        {'|','0','|','|','|','|','0','|','|','0','|','|','|','|','|','|','|','|','0','|','|','0','|','|','|','|','0','|'},
        {'|','0','0','0','0','0','0','|','|','0','0','0','0','|','|','0','0','0','0','|','|','0','0','0','0','0','0','|'},
        {'|','|','|','|','|','|','0','|','|','|','|','|','-','|','|','-','|','|','|','|','|','0','|','|','|','|','|','|'},
        {'|','|','|','|','|','|','0','|','|','|','|','|','-','|','|','-','|','|','|','|','|','0','|','|','|','|','|','|'},
        {'|','|','|','|','|','|','0','|','|','-','-','-','-','-','-','-','-','-','-','|','|','0','|','|','|','|','|','|'},
        {'|','|','|','|','|','|','0','|','|','-','-','-','-','-','-','-','-','-','-','|','|','0','|','|','|','|','|','|'},
        {'|','|','|','|','|','|','0','|','|','-','|','|','|','B','B','|','|','|','-','|','|','0','|','|','|','|','|','|'},
        {'-','-','-','-','-','-','0','-','-','-','|','-','-','-','-','-','-','|','-','-','-','0','-','-','-','-','-','-'},
        {'|','|','|','|','|','|','0','|','|','-','|','-','-','-','-','-','-','|','-','|','|','0','|','|','|','|','|','|'},
        {'|','|','|','|','|','|','0','|','|','-','|','|','|','|','|','|','|','|','-','|','|','0','|','|','|','|','|','|'},
        {'|','|','|','|','|','|','0','|','|','-','-','-','-','-','-','-','-','-','-','|','|','0','|','|','|','|','|','|'},
        {'|','|','|','|','|','|','0','|','|','-','|','|','|','|','|','|','|','|','-','|','|','0','|','|','|','|','|','|'},
        {'|','|','|','|','|','|','0','|','|','-','|','|','|','|','|','|','|','|','-','|','|','0','|','|','|','|','|','|'},
        {'|','0','0','0','0','0','0','0','0','0','0','0','0','|','|','0','0','0','0','0','0','0','0','0','0','0','0','|'},
        {'|','0','|','|','|','|','0','|','|','|','|','|','0','|','|','0','|','|','|','|','|','0','|','|','|','|','0','|'},
        {'|','0','|','|','|','|','0','|','|','|','|','|','0','|','|','0','|','|','|','|','|','0','|','|','|','|','0','|'},
        {'|','S','0','0','|','|','0','0','0','0','0','0','0','-','-','0','0','0','0','0','0','0','|','|','0','0','S','|'},
        {'|','|','|','0','|','|','0','|','|','0','|','|','|','|','|','|','|','|','0','|','|','0','|','|','0','|','|','|'},
        {'|','|','|','0','|','|','0','|','|','0','|','|','|','|','|','|','|','|','0','|','|','0','|','|','0','|','|','|'},
        {'|','0','0','0','0','0','0','|','|','0','0','0','0','|','|','0','0','0','0','|','|','0','0','0','0','0','0','|'},
        {'|','0','|','|','|','|','|','|','|','|','|','|','0','|','|','0','|','|','|','|','|','|','|','|','|','|','0','|'},
        {'|','0','|','|','|','|','|','|','|','|','|','|','0','|','|','0','|','|','|','|','|','|','|','|','|','|','0','|'},
        {'|','0','0','0','0','0','0','0','0','0','0','0','0','0','0','0','0','0','0','0','0','0','0','0','0','0','0','|'},
        {'|','|','|','|','|','|','|','|','|','|','|','|','|','|','|','|','|','|','|','|','|','|','|','|','|','|','|','|'},
    
    };
    public Tabuleiro(){
        try{// careega as imagens do tabuleiro
            Parede= new Image("File:src/Jogo/imagens/parede.png");
            right= new Image("File:src/Jogo/imagens/R.png");
            down= new Image("File:src/Jogo/imagens/D.png");
            left= new Image("File:src/Jogo/imagens/L.png");
            up= new Image("File:src/Jogo/imagens/U.png");
            verm= new Image("File:src/Jogo/imagens/verm.png");
            dot= new Image("File:src/Jogo/imagens/dot.png");
            rosa= new Image("File:src/Jogo/imagens/rosa.png");
            pilula= new Image("File:src/Jogo/imagens/pilula.png");
            nada= new Image("File:src/Jogo/imagens/nada.png");
            barreira = new Image("File:src/Jogo/imagens/barreira.png");
            verd= new Image("File:src/Jogo/imagens/verd.png");
            laran= new Image("File:src/Jogo/imagens/laran.png");
            corac= new Image("File:src/Jogo/imagens/corac.png");
            azul= new Image("File:src/Jogo/imagens/azul.png");
        }
        catch(Exception e){System.out.println("Erro ao carregar imagens : " + e.getMessage());}
       }
    
    public void setcanvas(Canvas canvas){
        this.canvas=canvas;

    }

    /**Imprime o tabuleiro na tela do Jogo. */
    public void imprime() throws RuntimeException {
        GraphicsContext gc = canvas.getGraphicsContext2D();
          
        for(int i=0;i<31;i++){
            for(int j=0;j<28;j++){
                // 1,2,3 e 4 sao as posicoes do pac man , 0 eh dot ,- eh nada, etc
                // Char 1-> UP;  Char 2-> DOWN;  Char 3-> LEFT ; Char 4 -> RIGHT
                if(Matriz[i][j]=='|') gc.drawImage(Parede,200+15*j,15*i,15,15);
                else if(Matriz[i][j]=='0') gc.drawImage(dot,200+15*j,15*i,15,15);
                else if(Matriz[i][j]=='-') gc.drawImage(nada,200+15*j,15*i,15,15);
                else if(Matriz[i][j]=='A') gc.drawImage(azul,200+15*j,15*i,15,15);
                else if(Matriz[i][j]=='B') gc.drawImage(barreira,200+15*j,15*i,15,15);
                else if(Matriz[i][j]=='1') gc.drawImage(up,200+15*j,15*i,15,15);
                else if(Matriz[i][j]=='2') gc.drawImage(down,200+15*j,15*i,15,15);
                else if(Matriz[i][j]=='3') gc.drawImage(left,200+15*j,15*i,15,15);
                else if(Matriz[i][j]=='4') gc.drawImage(right,200+15*j,15*i,15,15);                
                else if(Matriz[i][j]=='V') gc.drawImage(verm,200+15*j,15*i,15,15);
                else if(Matriz[i][j]=='R') gc.drawImage(rosa,200+15*j,15*i,15,15);
                else if(Matriz[i][j]=='S') gc.drawImage(pilula,200+15*j,15*i,15,15);
                else if(Matriz[i][j]=='G') gc.drawImage(verd,200+15*j,15*i,15,15);
                else if(Matriz[i][j]=='L') gc.drawImage(laran,200+15*j,15*i,15,15);
                
                //System.out.print(Matriz[i][j]);
            }
            //System.out.printf("\n");
        }
    }
    
    public char olhaposi(int linha,int coluna){
        return Matriz[linha][coluna];
    }

    public void poeposi(char c,int linha,int coluna){
         Matriz[linha][coluna]=c;
    }

/** Atualiza o numero de vidas do Pac na tela do jogo */
public void printvidas(){
    GraphicsContext gc = canvas.getGraphicsContext2D();
    for(int i=0;i<vidas;i++){
        gc.drawImage(corac,185,15*i,15,15);
    }
    for(int i=vidas;i<31;i++){
        gc.drawImage(nada,185,15*i,15,15);

    }

}

public void setvidas(int vidas){
    this.vidas=vidas;
}

public int getvidas(){
    return vidas;
}
/**Imprime Game over sobre a tela do Jogo */
public void gameover(){
    gameover = new Image("File:src/Jogo/imagens/gameover.png");
    GraphicsContext gc = canvas.getGraphicsContext2D();
    gc.drawImage(gameover,210,0,400,400);

}
   
}
