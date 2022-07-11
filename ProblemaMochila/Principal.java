/**
 * @autor: Daniel do Santos Filho
 * * IE:IBES/FACSAL
 * Disciplina: Inteligencia Artificial
 * 
 */
package mochila;

import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class Principal {

	//variaveis
	private  int quantidadeJoias=0;			   //quantidade de joias
	private int numeroIndividuos=10;		  //numero de individuos (solucao)
	private int numeroIteracoes=0;      	 // 
	private  double capacidadeMochila=0;    // Capacidade da mochila
	private double taxaSelecao=.70;        //  definindo uma taxa de selecao de 70 %
	private double taxaMutacao=00.05;     //   definindo uma taxa de mutaçao de 5  %
	private double taxaCruzamento=.30;   //    definindo uma taxa de cruzamento de 30%

	//Matrizes
	private int matrizPopulacao[][];      //conjunto de solucoes(individuos) uma solucao por linha
	private double vetorPrecoJoia[];      // vetor de precos das joias
	private double vetorPesoJoia[];      // vetor de peso das joias
	private ArrayList<Solucao> todasSolucoes=new ArrayList<Solucao>(); //ArrayList Guarda todas as solucose (peso,preco,indivi,geracao)

	//Instancias
	Scanner dados=new Scanner(System.in);
	public static Principal p=new Principal();


	public static void main(String[] args) {

		p.entradaDados();//Entrada dos dados pelo usuario numero de joias , Capacidade da mochila, peso e preco joias;
		p.gerapopulacao();//Gera a populaco inicial de forma aleatoria
		p.imprimeSaida(); //Imprime a primeira populaco

		while(!p.criterioParada(p.numeroIteracoes)){
			p.numeroIteracoes++;
			p.selecao();          //realiza a selecao
			p.cruzamento();      // realiza o cruzamento
			p.mutacao();        //realiza a mutaco
			p.imprimeSaida();  //imprime a saida

		}
		//Mosta melhor solucao
		if(p.criterioParada(p.numeroIteracoes)){
			p.mostraMelhorSolucao();
		}
	}
	public void entradaDados(){

		System.out.print("Digite o numero de joias: ");
		p.quantidadeJoias=p.dados.nextInt();

		System.out.print("Digite a capacidade da mochila:");
		p.capacidadeMochila=p.dados.nextDouble();
		
		if(p.capacidadeMochila<0) {
			p.capacidadeMochila=p.capacidadeMochila * (-1);
		}

		//inicializacao matriz e vetores
		p.matrizPopulacao=new int[p.numeroIndividuos][p.quantidadeJoias];
		p.vetorPrecoJoia=new double[p.quantidadeJoias];
		p.vetorPesoJoia=new double[p.quantidadeJoias];


		for (int pos = 0; pos < p.quantidadeJoias; pos++) {//preenche o vetor de peso das joias

			System.out.print("Digite o peso da joia ("+pos+"):");
			p.vetorPesoJoia[pos]=p.dados.nextDouble();
		}	

		for (int pos = 0; pos < p.quantidadeJoias; pos++) {//preenche o vetor de preco das joias

			System.out.print("Digite o preco da joia ("+pos+"):");
			p.vetorPrecoJoia[pos]=p.dados.nextDouble();
		}	
	}
	/* O metodo gera um conjunto de solucoes binairas(0 e 1)
	 * 1 representa que a joia esta na mochila 0 representa 
	 * que a joia nao esta na mochila
	 */
	public void gerapopulacao(){

		for (int ln = 0; ln < p.matrizPopulacao.length; ln++) {

			for (int col = 0; col < p.quantidadeJoias; col++) {

				p.matrizPopulacao[ln][col]=getNumeroAleatorio(2);//coloca na matriz um numero aleatorio ente 0 e 1
			}
		}
	}
	/*O algortimo para como dez mil iteracoes
	 */
	public boolean criterioParada(int iteracoes){

		if(iteracoes<10000) 
			return false;
		else
			return true;
	}
	/*Metodo recebe uma posicao da matriz de populacao
	 * para calcular a aptidao de uma solucao
	 * A aptidao e calculado dividindo o preco total de uma solucao pelo
	 * preco total de todas as solucoes * 100
	 * Ex: 
	 * 68/240 * 100 =28,33 %
	 */
	public double calculaAptidao(int posIndividuo){
		/*Se o peso total da solucao for maior que a 
		capacidade da mochila entao aptidao e igual a 0*/
		if(p.calculaPesoIndividuo(posIndividuo)>capacidadeMochila) 
			return 0;
		//return 1;
		else
			return  p.calculaPrecoIndividuo(posIndividuo)/p.calculaPrecoTotalSolucoes() * 100; /*Caso contrario retorna a aptidao*/
		//return p.calculaPrecoIndividuo(posIndividuo);
	}
	/* Calcula soma total dos valores de todas as solucoes de 
	 * uma populaco para efetuar o calculo da aptidao
	 */
	public double calculaPrecoTotalSolucoes(){
		double precoTotal=0;
		for (int ln = 0; ln <p.matrizPopulacao.length; ln++) {

			for (int col = 0; col < p.quantidadeJoias; col++) {
				if(p.matrizPopulacao[ln][col]==1)
					precoTotal+=p.vetorPrecoJoia[col];
			}
		}
		return precoTotal;
	}

	//Calcula o peso total de uma solucao recebe uma posisao da matriz de solucoes
	public double calculaPesoIndividuo(int posIndividuo){
		double pesoTotal=0;
		for(int col=0 ;col < p.vetorPesoJoia.length;col++){

			if(p.matrizPopulacao[posIndividuo][col]==1)
				pesoTotal+=p.vetorPesoJoia[col];
		}
		return pesoTotal;
	}
	//Calcula o valor total de uma solucao recebe uma posicao da matriz de solucoes
	public double calculaPrecoIndividuo(int posIndividuo){ 
		double precoTotal=0;
		for(int col=0 ;col < p.vetorPrecoJoia.length;col++){

			if(p.matrizPopulacao[posIndividuo][col]==1)
				precoTotal+=p.vetorPrecoJoia[col];
		}
		return precoTotal;
	}
	/*Soma a aptidao de uma populacao
	 */
	public double somaAptidaoTotal(){
		double AptidaoTotal=0;

		for (int pos = 0; pos < p.matrizPopulacao.length; pos++) {

			AptidaoTotal+=p.calculaAptidao(pos);
		}

		return AptidaoTotal;
	}
	public void selecao() {

		int [][] novaPopulacao=new int[p.numeroIndividuos][p.quantidadeJoias];
		int taxaSelecao=0,controle=0;
		// numero de individuos a serem selecionados
		taxaSelecao = (int)Math.ceil(numeroIndividuos*p.taxaSelecao); 

		while(controle < taxaSelecao) {
			int linha=p.roleta();
			for (int col = 0; col < p.quantidadeJoias; col++) {

				novaPopulacao[controle][col] = p.matrizPopulacao[linha][col];  //Adciona o individuo selecionado a matriz popula��o
			}
			controle++; //usado cmo linha da matriz nova populacao mantendo a matriz em ordem
		}
		p.matrizPopulacao=novaPopulacao; //Adiciona a populacao selecionada a matriz de populacao
	} // fim da selecao 
	public int roleta(){
		int individuo=0;
		double somaAptidao=p.somaAptidaoTotal(), valorSorteado=Math.random()* somaAptidao, soma=0;

		do{
			soma+=p.calculaAptidao(individuo);
			individuo++;
		}while((individuo<p.matrizPopulacao.length)&& (soma<valorSorteado));

		return individuo-1;
	}
	/*Efetuando cruzamento de um ponto
	 */
	public void cruzamento() {

		int taxaCruzamento = (int)Math.ceil(p.numeroIndividuos*p.taxaCruzamento);
		int taxaSelecao= (int)Math.ceil(p.numeroIndividuos*p.taxaSelecao);
		int posicaoPai=0 , posicaoMae=0, pontoCorte=(int) p.quantidadeJoias/2 ,posicao=taxaSelecao;
		int [] filho=new int[p.quantidadeJoias];
		int [][] populacaoCruzamaneto=p.matrizPopulacao;

		for (int idc = 0; idc < taxaCruzamento; idc++) {

			do{ //pega uma posicao para o pai e a mae de forma aleatoria
				posicaoMae=p.getNumeroAleatorio(taxaSelecao+1);
				posicaoPai=p.getNumeroAleatorio(taxaSelecao+1);
			}while(posicaoMae==posicaoPai);//garante que eu vou ter um pai e uma mae

			for (int i= 0; i < pontoCorte; i++) { 

				filho[i]=populacaoCruzamaneto[posicaoPai][i]; //filho recebe genes do pais
			}

			for (int j = 0; j < p.quantidadeJoias-pontoCorte; j++) {

				filho[pontoCorte+j]=populacaoCruzamaneto[posicaoMae][j+pontoCorte]; //filho recebe genes da mae
			}

			for (int i = 0; i < filho.length; i++) { 

				populacaoCruzamaneto[posicao][i]=filho[i]; //matriz de populacao do cruzamento recebe o filho gerado
			}
			posicao++;
		}
		p.matrizPopulacao=populacaoCruzamaneto;
	}
	/* O metodo invert os valores de uma linha e coluna
	 *  geradas de forma aleatoria se 1 entao 0 se 0 entao 1
	 */
	private void mutacao() {
		int taxaMutacao=(int)Math.ceil(p.numeroIndividuos*p.taxaMutacao);

		for(int i=0;i<taxaMutacao;i++){

			int linha=p.getNumeroAleatorio(p.numeroIndividuos); //pega uma linha aleatoria
			int coluna=p.getNumeroAleatorio(p.quantidadeJoias); //pega uma coluna aleatoria

			if(p.matrizPopulacao[linha][coluna]==0)
				p.matrizPopulacao[linha][coluna]=1;
			else
				p.matrizPopulacao[linha][coluna]=0;
		}
	}
	public  int getNumeroAleatorio(int limite){ 

		return new Random().nextInt(limite);//retorna um numero aleatorio entre 0 e limite-1;
	}
	public void imprimeSaida(){
		System.out.println(" ");
		System.out.println("Geracao: "+p.numeroIteracoes);
		System.out.println(" ");
		for (int posIndividuo = 0; posIndividuo < p.numeroIndividuos; posIndividuo++) { 

			Solucao individuo=new Solucao(); //Uma instancia de Solucoa
			individuo.setSolucao(""); 
			System.out.print("Solucao: ");
			for (int col = 0; col < p.quantidadeJoias; col++) {

				System.out.print("["+p.matrizPopulacao[posIndividuo][col] + "]");
				individuo.setSolucao(individuo.getSolucao()+" "+p.matrizPopulacao[posIndividuo][col]+" ");
			}
			/*Guarda todas as solucoes em um arrayList do tipo Solucao*/
			individuo.setAptidao(p.calculaAptidao(posIndividuo));
			individuo.setPeso(p.calculaPesoIndividuo(posIndividuo));
			individuo.setPreco(p.calculaPrecoIndividuo(posIndividuo));
			individuo.setGeracao(p.numeroIteracoes);

			p.todasSolucoes.add(individuo);

			System.out.printf(" Peso Total= "+"%.2f",p.calculaPesoIndividuo(posIndividuo));
			System.out.printf("   Preco Total=  "+"%.2f",p.calculaPrecoIndividuo(posIndividuo));
			System.out.printf("   Aptidao= "+"%.2f %%",(p.calculaAptidao(posIndividuo)));
			System.out.print("\n");	
		}
	}
	/* Procura dentro do arrayList a melhor
	 * Solucao e imprime
	 */
	public void mostraMelhorSolucao(){
		double maiorAptidao=todasSolucoes.get(0).getAptidao();
		int posicao=0;

		for (int pos = 0; pos < todasSolucoes.size(); pos++) {

			if(todasSolucoes.get(pos).getAptidao()>maiorAptidao){
				maiorAptidao=todasSolucoes.get(pos).getAptidao();
				posicao=pos;
			}
		}
		System.out.print("\nPROVAVEL MELHOR SOLUCAO: ");
		System.out.print("["+todasSolucoes.get(posicao).getSolucao()+"]");
		System.out.printf(" Peso Total= "+"%.2f",todasSolucoes.get(posicao).getPeso());
		System.out.printf("   Preco Total=  "+"%.2f",todasSolucoes.get(posicao).getPreco());
		System.out.printf("   Aptidao= "+"%.2f %%",todasSolucoes.get(posicao).getAptidao());
		System.out.print(" GERACAO: "+todasSolucoes.get(posicao).getGeracao());
		System.out.print("\n");	
	}
}