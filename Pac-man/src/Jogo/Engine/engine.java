package Jogo.Engine;
import java.util.ArrayList ;
import java.util.Timer;
import java.util.TimerTask;

import Jogo.ElementosdoSistema.Fantasma;
import Jogo.ElementosdoSistema.Pac;
import Jogo.InterfaceGrafica.Tabuleiro;
import javafx.scene.canvas.Canvas;
/**Eh nessa clasee que acontece a dinamica do jogo:
 * eh onde se calcula a menor distancia de um fantasma
 *  ate o Pac-man e onde a mecanica do jogo esta implementada.
 */
public class engine {
    private Tabuleiro t;
    private Pac p;
    private Fantasma V;
    private Fantasma R;
    private Fantasma G;
    private Fantasma L;
    private int distancias[][];
    Joystick joystick;
    

    public engine(Joystick joystick){
        t = new Tabuleiro();
        V= new Fantasma('V', t,15,11); //Fantasma vermelho
        R= new Fantasma('R', t,15,16); //Fantasma rosa
        G= new Fantasma('G', t,15,12); //Fantasma verde
        L= new Fantasma('L', t,15,13);// Fantasma laranja
        this.joystick=joystick;
        distancias= new int[31][28];
    }

    /**
     * Preenche o tabuleiro de distancias ate encontrar os fantasmas.
     * O tabuleiro comeca com todas as distancias iguais a 0, q eh tido como infinito, e o Pac-man com distancia 1.
     * Em cada rodada sao definidas distancias cada vez maiores em posicoes validas  ate encontrar os dois fantasmas.
     * @param linha
     * @param coluna
     */
    private void calculadist(Pac P){
        int linha=P.getlinha();
        int coluna=P.getcoluna();
        for(int i=0;i<31;i++){
            for(int j=0;j<28;j++){
                distancias[i][j]=0;}}
        distancias[linha][coluna]=1;
        //Funciona como uma erupcao vulcanica : a cada rodada são marcados os pontos(ou a curva de nivel) que possue(m) uma distancia dada na matriz distancias
        //Como  escoamento de lava.
        //0 eh condiderado como distancia infinita e 1 , a posicao do pac (A menor)
        ArrayList<Integer> l= new ArrayList<Integer>();// fila de elementos que tiveram suas distancias definidas
        l.add(linha);
        l.add(coluna);
        int numpares=1;// armazena o numero de pares (linha e coluna) na fila
        int aux=0;// armazena o numpares da proxima rodada de definicoes
        int valor=2;//  define a distancia correspondente a rodada
        while(distancias[V.getlinha()][V.getcoluna()]==0 || distancias[R.getlinha()][R.getcoluna()]==0){//enquanto os fantasmas nao tiverem distancias definidas
            for(int i=0;i<numpares;i++){//
                linha=l.remove(0);
                coluna=l.remove(0);
                //Olha todas as posicoes q cercam o Pac e define suas distancias caso n seja parede ou ja tenha sido definida
                if(t.olhaposi(linha+1, coluna)!='|' && distancias[linha+1][coluna]==0){
                    distancias[linha+1][coluna]=valor; 
                    l.add(linha+1);// adiciona o ponto na lista de distancias definidas
                    l.add(coluna);
                    aux++;             
                }
                if(t.olhaposi(linha-1, coluna)!='|' && distancias[linha-1][coluna]==0){
                    distancias[linha-1][coluna]=valor;
                    l.add(linha-1);
                    l.add(coluna);
                    aux++;                     
                }
                if(linha==14 && coluna==0){//posicao extremo esquerdo do tabuleiro
                    if(distancias[14][27]==0){
                    distancias[14][27]=valor;
                    l.add(14);
                    l.add(27);
                    aux++;}      
                }
                else {
                    if(t.olhaposi(linha, coluna-1)!='|' && distancias[linha][coluna-1]==0){
                        distancias[linha][coluna-1]=valor;
                        l.add(linha);
                        l.add(coluna-1);
                        aux++;                     
                    }

                }
                if(linha==14 && coluna==27){// posicao extrema direita do tabuleiro
                    if(distancias[14][0]==0){
                        distancias[14][0]=valor;
                        l.add(14);
                        l.add(0);
                        aux++;} 

                }
                else{
                    if(t.olhaposi(linha, coluna+1)!='|' && distancias[linha][coluna+1]==0){
                        distancias[linha][coluna+1]=valor;
                        l.add(linha);
                        l.add(coluna+1);
                        aux++;                     
                    }

                }
            }
            numpares=aux;
            aux=0;
            valor++;// mais 1 rodada ,entao maior a distancia
            

        }
        
    }
    /**Faz os fantasmas vermelho e rosa perseguirem o pac */
    private int persegue(Fantasma X){
        //Vamos escolher a menor distancia dentre todas as 4 casas que cercam o fantasma
        //Lembrando que 0 é infinito
        int linha =X.getlinha();
        int coluna= X.getcoluna();
        int Direcoes[]= new int[4];//0-> cima; 1-> baixo; 2-> esquerda ; 3-> direita
        Direcoes[0]=distancias[linha-1][coluna];
        Direcoes[1]=distancias[linha+1][coluna];

        if(coluna==0) Direcoes[2]=distancias[14][27];// posicao extremo esquerdo do tabuleiro
        else Direcoes[2]=distancias[linha][coluna-1];

        if(coluna==27) Direcoes[3]=distancias[14][0];//posicao extrema direita do tabuleiro
        else Direcoes[3]=distancias[linha][coluna+1];
        // Agora vamos achar o index do menor valor
        int index=-1;
        int menor= 10000;// Esse valor serve para majorar :ser substituido assim q encontrar uma direcao diferente de zero (como ele eh maior q o numero de casa n tem problema)
        for(int i=0;i<4;i++){// Pega a posicao que esta com a menor distancia
            if(Direcoes[i]>0 && Direcoes[i]<menor) {// zero eh considerado infinito
                menor=Direcoes[i];
                index=i;}}
       return X.anda((char)(49+index));// 49 eh "1" em ascii

    }



    /**Funcao onde acontece todo o jogo */
    public void Play(Canvas canvas) {
        try{
        t.setcanvas(canvas);
        p= new Pac(t);
        joystick.setPac(p);
        Timer tpac = new Timer();//time do pac-man
        Timer tfant= new Timer();// time dos fantasmas
        TimerTask tarefaFantasma = new TimerTask(){
            @Override
            public void run(){
                calculadist(p);
                if (persegue(V)==-1)p.morreu();//se encontrar o pac-man tira uma vida dele
                if(persegue(R)==-1)p.morreu();
                if (G.andaRandom()==-1) p.morreu();
                if (L.andaRandom()==-1) p.morreu();
                
                }
        };

        TimerTask tarefapac = new TimerTask(){
            @Override
            public void run(){
                p.propaga();
                t.imprime();
                if(t.getvidas()<0) {// Se perder todas as vidas
                    tfant.cancel();//Fecha as Threads
                    tpac.cancel();
                    t.gameover();//Printa game over
                }
                }
        };
        tpac.scheduleAtFixedRate(tarefapac,0,150);
        tfant.scheduleAtFixedRate(tarefaFantasma,0,600);
        

        

        
        

    } catch(Exception e){
        System.out.println("Erro: "+e.getMessage());
    }
}

}