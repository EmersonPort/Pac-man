package Jogo.ElementosdoSistema;

import Jogo.InterfaceGrafica.Tabuleiro;
/**Classe Mae : utilizada para criar todos os personagens do jogo 
 * como o Pac-man e os fantasmas. */
public abstract class  Personagem {
    char c;// carcter que representa o personagem no tabuleiro
    int linha;//posicao no tabuleiro
    int coluna;
    Tabuleiro t;
    Personagem(char representacao,Tabuleiro pt){
        c = representacao;
        t=pt;}

    public void setchar(char c){
            this.c=c;
        }

    /** Poe o simbolo do personagem na sua correspondente posicao do tabuleiro. */
    public void poeposi(){
        t.poeposi(c, linha, coluna);
    }
    /** Tenta por o personagem em uma posicao no tabuleiro , se for valida retorna 1 ,caso contrario 0 */
    public int setposi(int linha, int coluna){
        if(t.olhaposi(linha,coluna)=='0' ||t.olhaposi(linha,coluna)=='-' || t.olhaposi(linha,coluna)=='S'){ //Posicoes validas
        this.linha=linha;
        this.coluna=coluna;
        return 1; //OK
        }
        else return 0;   
    }

    /**Poe o personagem em uma posicao aleatoria valida */
    public void poerand(){
        while(setposi((int) (Math.random()*30),(int) (Math.random()*27))!=1);
        poeposi();
    }

    public int getlinha(){
        return linha;
    }

    public int getcoluna(){
        return coluna;
    }

    public void andaUP(){
        linha--;
    }

    public void andaDOWN(){
        linha++;
    }

    public void andaRIGHT(){
        if(coluna==27){// Extremo direito do tabuleiro
            coluna=0;
        }
        else coluna++;
    }

    public void andaLEFT(){
        if(coluna==0){// Extremo esquerdo do tabuleiro
            coluna=27;
        }
        else coluna--;
    }

    


    
}
