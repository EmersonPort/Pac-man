package Jogo.ElementosdoSistema;

import Jogo.InterfaceGrafica.Tabuleiro;
/**Classe responsavel por representar o Pac-man */
public class Pac extends Personagem {
    private int score;
    private int pacdots;
    private int pilulas;
    public Pac(Tabuleiro pt){//O char representa o sentido/orientacao do movimento
        //no caso do pac-man  cada orientacao tem uma imagem
        super('1', pt);// Char 1-> UP;  Char 2-> DOWN;  Char 3-> LEFT ; Char 4 -> RIGHT
        t.setvidas(3);
        score=0;
        pacdots=0;
        pilulas=0;
        nasce();
        t.printvidas();

    }
    
    /**Aumenta o score do pa-man.
     * Recebe um Char que eh da posicao para onde o Pac vai
     * @param c
     */
    private void incScore(char c){
        switch (c) {
            case '0':
                score= score +10;
                pacdots++;
                
                break;
            case 'S':
                score= score +50;
                pilulas++;


                break;
        
            default:
                break;
        }

    }
    /**Diminue o numero de vidas do Pac man e faz ele renascer dnv, se tiver vidas */
    public void morreu(){
        int vidas= t.getvidas();
        vidas--;
        t.setvidas(vidas);
        if(vidas >=0){
            t.poeposi('-', linha, coluna);//limpa a posicao anterior
            nasce(); // nasce  dnv
            t.printvidas();

        }
        
    }

    private void nasce(){
        linha=23;
        coluna=13;
        poeposi();

    }
    /**Faz o Pac-man andar na direcao que ele  esta olhando.
     * Para cima , para baixo , para um lado, por exemplo.
     */
    public  void anda(){
        //O char representa o sentido/orientacao do movimento
        //no caso do pac-man  cada orientacao tem uma imagem
        // Char 1-> UP;  Char 2-> DOWN;  Char 3-> LEFT ; Char 4 -> RIGHT
        switch (c) {
            case '1':
            andaUP();
                
                break;
            case '2':
            andaDOWN();
                
                break;

            case '3':
            andaLEFT();
                
                break;

            case '4':
            andaRIGHT();
                
                break;
        
            default:
                break;
        }
        
    }
    /**
     * A propagacao serve para o pac-man continuar se movendo ate encontrar um obstaculo ou morrer.
     * @return
     */
    public int propaga(){
        char prox='0';// char da proxima posicao do pac
        // Char 1-> UP;  Char 2-> DOWN;  Char 3-> LEFT ; Char 4 -> RIGHT
        switch (c) {
            case '1':
            prox=t.olhaposi(linha-1,coluna);
                
                break;
            case '2':
            prox=t.olhaposi(linha+1,coluna);
                
                break;

            case '3':
            if(coluna==0){//Extremo esquerdo do tabuleiro
                prox=t.olhaposi(14,27);
            }
            else prox=t.olhaposi(linha,coluna-1);
            
                
                break;

            case '4':
            if(coluna==27){//Extremo direito do tabuleiro
                prox=t.olhaposi(14,0);
            }
            else prox=t.olhaposi(linha,coluna+1);
                
                break;
        
            default:
                break;
        }
        if(prox=='0' || prox=='-'  || prox=='S'){// se for igual a um pac-dot ou vazio ou uma pilula 
            incScore(prox);
            t.poeposi('-', linha, coluna);// limpa a posicao onde estava o pac
            anda();
            poeposi();
            if (prox=='S') return 2;
            return 1;//eh uma posicao valida
        }

        if(prox=='V' || prox=='R' || prox=='G' || prox=='L'){// Se encontrar um fantasma
            morreu();
            return -1;            
        }
        // se o prox for = ao | ou B n faz nada, pq eh parede
        return 0;
    }


    
      


    
}
