package Jogo.ElementosdoSistema;

import java.util.Random;

import Jogo.InterfaceGrafica.Tabuleiro;
/**Classe responsavel por representar os Fantasmas */
public class Fantasma extends Personagem {
    private char anterior;//Salva o q havia na casa antes do fantasma passar sobre ela
    public Fantasma(char representacao,Tabuleiro pt,int plinha,int pcoluna){
        super(representacao, pt);   
        linha=plinha;
        coluna=pcoluna;
    }

    /**Faz o fantasma ir em uma direcao definida pelo caracter prox
     * har 1-> UP;  Char 2-> DOWN;  Char 3-> LEFT ; Char 4 -> RIGHT
     */
    public int anda(char prox){
        char cprox;// caracter da proxima posicao no tabuleiro
        switch (prox) {
            case '1':
                cprox=t.olhaposi(linha-1,coluna);  
                if(cprox!='V' && cprox!='R' && cprox!='G' && cprox!='L' && cprox!='A'){// so se move se n tiver fantasma na frente
                    t.poeposi(anterior, linha, coluna);//restaura o  carcater q havia  anterioriormente ao fantasma passar
                    anterior=cprox;// salva o caracter
                    andaUP(); 
                    poeposi();
                }
                        
                break;
            case '2':
                cprox=t.olhaposi(linha+1,coluna);   
                if(cprox!='V' && cprox!='R' && cprox!='G' && cprox!='L' && cprox!='A'){// so se move se n tiver fantasma na frente
                    t.poeposi(anterior, linha, coluna);//restaura o  carcater q havia  anterioriormente ao fantasma passar
                    anterior=cprox;// salva o caracter
                    andaDOWN(); 
                    poeposi();
                }                
                break;
            case '3':
            if(coluna==0){//Extremo esquerdo do tabuleiro
                cprox=t.olhaposi(14,27);
            }
            else cprox=t.olhaposi(linha,coluna-1);  
            if(cprox!='V' && cprox!='R' && cprox!='G' && cprox!='L' && cprox!='A'){// so se move se n tiver fantasma na frente
                t.poeposi(anterior, linha, coluna);//restaura o  carcater q havia  anterioriormente ao fantasma passar
                anterior=cprox;// salva o caracter
                andaLEFT(); 
                poeposi();
            }               
                break;
            case '4':
            if(coluna==27){//Extremo direito do tabuleiro
                cprox=t.olhaposi(14,0);
            }
            else cprox=t.olhaposi(linha,coluna+1);
            if(cprox!='V' && cprox!='R' && cprox!='G' && cprox!='L' && cprox!='A'){// so se move se n tiver fantasma na frente
                t.poeposi(anterior, linha, coluna);//restaura o  carcater q havia  anterioriormente ao fantasma passar
                anterior=cprox;// salva o caracter
                andaRIGHT(); 
                poeposi();
            }               
                break;        
            default:
                break;
        }

        if(anterior=='1' || anterior=='2' || anterior=='3' || anterior=='4') {
            anterior='-';// caso o fantasma encontre o pac-man , o pac n fica salvo
            return -1;// retorno de sinal que mata o pac man
            }

        
            return 1;
    }
    /** Anda para uma posicao valida , mas escolhida aleatoriamente */
    public int andaRandom(){
        Random r = new Random();
        char prox='0';
        int n;
        while(true){
        switch (n=r.nextInt(4)) {
            case 0:
                prox=t.olhaposi(linha-1,coluna);
                break;
            case 1:
                prox=t.olhaposi(linha+1,coluna);
                break;
            case 2:
            if(coluna==0){//Extremo esquerdo do tabuleiro
                prox=t.olhaposi(14,27);
            }
            else prox=t.olhaposi(linha,coluna-1);
            break;
            case 3:
            if(coluna==27){//Extremo direito do tabuleiro
                prox=t.olhaposi(14,0);
            }
            else prox=t.olhaposi(linha,coluna+1);
            break;        
            default:
                break;
        }
        if(prox=='-' || prox=='0' || prox== 'S' || prox=='B'){//posicoes validas para o Fantasma
            anda((char)(49+n));// 49 eh '1' em ascii break 
            return 1; // posicao ok
        }
        if(prox=='1' || prox=='2' || prox=='3' || prox=='4') return -1; //Encontrou o pac-man
     }
     
    }

        
    
    
}
