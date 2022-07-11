/**
 * @autor:Daniel Filho
 * IE:IBES/FACSAL
 * Disciplina: Inteligencia Artificial
 * 
 */
import java.util.InputMismatchException;
import java.util.Scanner;

public class Problema {

	//Variaveis
	public static int numeroCidades=0;            
	public static int numeroPopulacoesGeracao=0; 
	public static double distanciaCidade=0;        
	public static final int numeroMaximoGeracoes=100; //numero minimo de duas geracoes
	public static int controleGeracoes=0;          
	public static final int taxaSelecao=3;         //numero maximo usado para o elitismo do mais apto  
	public static boolean fazCruzamentoEMutacao=true;

	//Matrizes 
	public static int m_adjacencia[][]; //Matriz que representa a ligacao entre as cidades
	public static double m_distancias[][]; //Matriz de distancias entre as cidades
	public static int m_populacao[][];    //matriz de populcoes um populacao por linha
	public static double matrizMaisAptos[][];//matriz que armazena os mais  aptos por geracao e tambem sua aptidao
	public static int martrizSelecionaMaisAptos[][];//Matriz usada no elitismo de dos mais aptos por geracao
	//Vetores
	public static int vetorTemporario[]; //Vetor usado para auxiliar a geracao da populacao
	public static double vetorDistanciaTotal[]; //vetor usado para armazenar as distancia total  de uma rota rota uma por linha 
	public static double  vetorAptidaoPorPopulacao[]; //vetor usado para armazenar a aptidao de uma populacao uma por linha 


	public static MetodosAuxiliares aux=new MetodosAuxiliares();

	public static void main (String args[]){

		entradaDados();

		//Inicializa as matrizes 
		m_adjacencia=new int [numeroCidades][numeroCidades];
		m_distancias=new double [numeroCidades][numeroCidades];
		m_populacao=new int[numeroPopulacoesGeracao][numeroCidades];
		matrizMaisAptos=new double[numeroMaximoGeracoes][numeroCidades+3];//Guarda todos os mais aptos  sua aptidao e geracao


		//Inicializa os vetores;
		vetorTemporario=new int [numeroCidades];
		vetorDistanciaTotal=new double [numeroPopulacoesGeracao];
		vetorAptidaoPorPopulacao = new double [numeroPopulacoesGeracao];
		martrizSelecionaMaisAptos=new int [3][numeroCidades];


		m_distancias=aux.setMatrizNegativa(m_distancias, numeroCidades, numeroCidades);
		m_populacao=aux.setMatrizNegativa(m_populacao, m_populacao.length, numeroCidades);
		vetorTemporario=aux.setVetorNegativo(vetorTemporario);
		m_adjacencia=aux.preencheMatrizAdjacencia(m_adjacencia, numeroCidades);
		martrizSelecionaMaisAptos=aux.setMatrizNegativa(martrizSelecionaMaisAptos, 3, numeroCidades);


		preencheMatrizDist();


		while(controleGeracoes<numeroMaximoGeracoes){

			if(controleGeracoes==0){
				gerarPopulacao();		
				somaDistanciasRota();
				imprime();
				controleGeracoes++;
			}
			if(controleGeracoes>0){
				seleciona();
				m_populacao=aux.setMatrizNegativa(m_populacao, m_populacao.length, numeroCidades);
				gerarPopulacao();

				if(fazCruzamentoEMutacao){
					realizaCruzamento();

				}

				elegeMaisApto();
				somaDistanciasRota();

				imprime();
				controleGeracoes++;

			}

		}

		mostraMelhorResultado();
	}

	public static void entradaDados(){
		Scanner dados=new Scanner(System.in);

		try{
			do{
				System.out.println("Digite o numero de cidades:\n");
				numeroCidades=dados.nextInt();
				

				if(numeroCidades<2)
					System.out.println("\nO numero minimo de cidade e 2" );

			}while(numeroCidades<2);		
			//e preciso garantir que o numero de populcoes por geracao seja maior ou igual a numero de rotas para que o algoritmo tente gerar todas as rotas possives
			do{

				System.out.println("Digite o numero de populacoes por geracao: \n");
				numeroPopulacoesGeracao=dados.nextInt();

				if(numeroPopulacoesGeracao<2)
					System.out.println("O numero minimo de populacoes e: 2");

				if(numeroPopulacoesGeracao<aux.getNumeroRotas(numeroCidades) && numeroPopulacoesGeracao!=0)
					System.err.println("Atencao o numero minimo de populacoes para: " + numeroCidades + " cidades e: "  +aux.getNumeroRotas(numeroCidades));

			}while(numeroPopulacoesGeracao<aux.getNumeroRotas(numeroCidades) || numeroPopulacoesGeracao<2); 


		}catch (InputMismatchException ime ) {
			System.err.println("Atencao valor invalido" );
			entradaDados();
		}
	}

	public static double entradaDistancias(int ln,int col){

		Scanner dados=new Scanner(System.in);

		try{
			System.out.println("Digite a distancia entre as cidades:" +ln + " E " +col);
			distanciaCidade=dados.nextDouble();
			

		}catch(InputMismatchException ime){
			System.err.println("Atencao valor invalido" );
			entradaDistancias(ln, col);

		}

		return distanciaCidade;
	}
	/*Metodo que preenche a matriz de distancia.
	 * Nessa matriz cada valor corresponde a uma distancia de
	 * uma determinada rota. Caso o valor seja -1 entao nao
	 * existe nenhuma rota.
	 * ln=linha col=coluna
	 */ 
	public static void preencheMatrizDist(){

		for (int ln= 0; ln < numeroCidades; ln++) {

			for (int col = 0; col < numeroCidades; col++) { 
				//se a linha for diferente da coluna(pois, nao existe loop entre cidades) e matriz de adjacencia nessa possisao entao existe uma rota 	
				if((ln!=col) && (m_adjacencia[ln][col]==1)){
					if(m_distancias[col][ln]!=-1) {                   //se m_distancias[col][ln]!=-1 for diferente de -1 entao ja existe um valor para a rota
						m_distancias[ln][col]=m_distancias[col][ln]; // e a matriz vai receber ela mesma na posicao inversa [col] como linha e [ln] como coluna.
					}else{										  //caso contrario a distancia ainda nao foi informada entao e pedido ao usuario	

						m_distancias[ln][col]=entradaDistancias(ln, col);;
					}//fim do else do segundo if
				}//fim do primeiro if

				if((ln>0) && (m_adjacencia[ln][col]==1)){

					if((m_distancias[ln][col]==-1) && (m_distancias[col][ln]==-1)){
						m_distancias[ln][col]=entradaDistancias(ln, col);

					}//fim do if interno

				}//fim do if externo

			}//fim do for da coluna

		}//fim do for da linha
	}
	/*Metodo para gerar uma populacao esse metodo utiliza uma matriz de populacao
	 * onde cada linha representa uma populacao valida. Nesse caso uma populacao e um 
	 * conjunto de cidades representadas por um inteiro entre 0 e num_cidade -1.
	 * Uma cidade e valida se ela e unica em uma populacao.
	 * Utilizamos com algumas adaptacoes o metodo Random.nextInt() para gera um numero aleatorio. 
	 */
	public static void gerarPopulacao(){

		int num;
		for (int ln = 0; ln < m_populacao.length; ln++) {	

			for (int pos = 0; pos < vetorTemporario.length; pos++) {

				num=aux.getNumeroAleatorio(numeroCidades);//retorna um numero aleatorio entre 0 e o numero de cidades

				if((pos==0) && (num<=(numeroCidades-1))) // se for a primeira vez entao a cidade e valida e o vetor temporario recebe o numero da cidade 
					vetorTemporario[pos]=num;
				else{ //caso contrario temo que testa se a cidade ja existe no vetor

					do{
						/*se nao for verdadeiro incrementa a variavel isso foi feito pois a funcao 
Random.nextInt gerava o mesmo numero diversas vezes e  como a cidade ja existia no vetor entao o codigo ficava em loop*/		
						if((!aux.ehCidadeValida(num,vetorTemporario)) && !(num<=numeroCidades-1)){//se nao for verdadeiro
							num++;
							if((aux.ehCidadeValida(num,vetorTemporario)) && (num<=numeroCidades-1))   //testa novamente se o numero agora incrementado e valido se for sai do loop
								break;
						}else{
							if(num>=numeroCidades-1)
								num--;
							else 
								num=aux.getNumeroAleatorio(numeroCidades);// caso contrario gera outro numero aleatorio
						}

					}while(!aux.ehCidadeValida(num,vetorTemporario)); //Se nao for valido executa novamente o do-while
					vetorTemporario[pos]=num;			  //a cidade e valida entao o vetor temporario recebe o numero da cidade
				}//fim do else

			}//fim do for

			for (int col = 0; col <numeroCidades; col++) {

				if(m_populacao[ln][col]==-1){ 			//se o valor na matriz populacao for -1 entao ela ainda nao foi preenchida 
					m_populacao[ln][col]=vetorTemporario[col]; //a matriz populacao recebe o vetor temporario 
					vetorTemporario[col]=-1;					//libera o vetor para a proxima iteracao de geracao de uma populacao
				}//fim do if

			}//fim do for da coluna


		}//fim do for da linha

	}
	public static void somaDistanciasRota(){

		int vlrLinha=0,vlrColuna=0;
		double distanciaTotal=0;

		for (int ln = 0; ln < m_populacao.length; ln++) {
			distanciaTotal=0;
			for (int col = 0; col < numeroCidades-1; col++) {

				vlrLinha=m_populacao[ln][col];  //pega o valor ln e col da matriz m_populacao para usar como linha
				vlrColuna=m_populacao[ln][col+1]; //pega o valor ln col+1 para usar como linha
				distanciaTotal= distanciaTotal + m_distancias[vlrLinha][vlrColuna]; /*usando o respectivo endereco	de linha e coluna pega a
		    																	distancia da primeira para a segunda cidade e faz isso
		    																	para todas as cidades de uma populacao*/
			}//fim do for da coluna
			vetorDistanciaTotal[ln]=distanciaTotal; //a distancia total de uma rota e adicionada na linha do vetor;

		}//fim do for da linha

	}

	/*O metodo pegar a maior distancia de uma geracao  e subtrai  e armazena dos as diferencas
	 * soma todas as diferencas  depois divide cada diferenca pela soma total das diferencas
	 * e mutiplica por cem o resultado e a aptidao
	 * 
	 *  Se todas as distancias forem iguais entao a aptidao sera 100/numeroDePopulacoesGeracao
	 */
	public static double  calculaAptidao(double [] vetorDistancia,int posicao){

		double maiorValor=aux.getMaiorValor(vetorDistancia); //pega o maior valor no vetor de distancias
		double totalDiferenca=0;  
		double[] vetorDiferenca=new double[vetorDistancia.length]; //inicializa o vetor de diferenca 
		double aptidao=0;

		for (int ln = 0; ln < vetorDistancia.length; ln++) {

			totalDiferenca=totalDiferenca+(maiorValor - vetorDistancia[ln]); //acumula o total de diferencas
			vetorDiferenca[ln]=maiorValor-vetorDistanciaTotal[ln];  		//guarda a diferenca de cada distancia
		}

		aptidao=vetorDiferenca[posicao]=vetorDiferenca[posicao] / totalDiferenca * 100; //calcula a aptidao


		if(aux.saoTodosIguais(vetorDistancia)){ 							 //se todas as distancias sao iguais
																			 // entao aptidao de uma distancia e
			preencheAptidaoPopulacao(100 /numeroPopulacoesGeracao,posicao); // 100/numeroPopulacoesGeracao;
			return 100 /numeroPopulacoesGeracao;

		}

		preencheAptidaoPopulacao(aptidao,posicao);

		return aptidao;

	}
	public static int getPosicaoMaisApto(){

		int posicaoMaisApto=0;
		double maior=vetorAptidaoPorPopulacao[0];
		boolean saoIguais=aux.saoTodosIguais(vetorAptidaoPorPopulacao);

		if(saoIguais)//se sao todos iguais retorna uma posicao aleatoria
			posicaoMaisApto=aux.getNumeroAleatorio(numeroCidades);
		else
			for (int ln = 0; ln < vetorAptidaoPorPopulacao.length; ln++) {

				if(vetorAptidaoPorPopulacao[ln]>maior){
					posicaoMaisApto=ln;
					maior=vetorAptidaoPorPopulacao[ln];
				}
			}

		return posicaoMaisApto;

	}
	/* O metodo faz uso do elitismo.
	 * O metodo  tenta selecionar dois mais aptos de uma populacao para efetuar o cruzamento
	 * se nao for possivel selecionar dois entao nao ocorre cruzamento nem
	 * mutacao.
	 */
	public static void seleciona(){ 

		int pos=getPosicaoMaisApto(), linha=0;
		double melhorAptidao=vetorAptidaoPorPopulacao[pos]; //pega a aptidao do mais apto de uma populacao
		double proxMelhorAptidao=vetorAptidaoPorPopulacao[pos]; //pega a aptidao do mais apto de uma populacao
		double diferenca=0;


		for (int ln = 0; ln < vetorAptidaoPorPopulacao.length; ln++) {

			diferenca=melhorAptidao-vetorAptidaoPorPopulacao[ln];  //Tira a diferenca da melhor aptidao inclusive ela mesma

			if((diferenca!=0) && (diferenca<melhorAptidao)){      //se diferenca for menor que aptidao guardo a linha 
				//	e proxMelhorAptidao= A segunda melhor aptidao
				linha=ln;
				proxMelhorAptidao=vetorAptidaoPorPopulacao[ln];
			}

		}


		if(melhorAptidao==proxMelhorAptidao){//se verdadeiro significa que nao existe parceiro para cruzamento entao nao havera mutacao

			fazCruzamentoEMutacao=false;

			for (int col = 0; col < martrizSelecionaMaisAptos.length-1; col++) {

				martrizSelecionaMaisAptos[0][col]=m_populacao[pos][col];
			}
		}else{

			for (int ln = 0; ln < taxaSelecao; ln++) {

				for (int col = 0; col < numeroCidades; col++) {

					martrizSelecionaMaisAptos[ln][col]=m_populacao[pos][col];
				}
				pos=linha;
			}//fim do for da linha
		}//fim do else

	}

	/* O Metodo coloca os mais aptos eleitos
	 * na matriz de populacao
	 */
	public static void elegeMaisApto(){

		for (int ln = 0; ln < taxaSelecao; ln++) {

			for (int col = 0; col < numeroCidades; col++) {

				if(martrizSelecionaMaisAptos[ln][col]!=-1)
					m_populacao[ln][col]=martrizSelecionaMaisAptos[ln][col];
			}
		}

		martrizSelecionaMaisAptos=aux.setMatrizNegativa(martrizSelecionaMaisAptos, martrizSelecionaMaisAptos.length, numeroCidades);

	}


	/*convenciando que a primeira linha e do pai
	 * a segunda e da mae e a terceira do filho
	 * gerado
	 */
	public static void realizaCruzamento(){

		int vlrPai=aux.getNumeroAleatorio(numeroCidades);//pega um numero aleatori para usar como posicao do vetor
		int vlrMae=0;
		int[] vetortemp=new int [numeroCidades];
		boolean naoTerminou=false;
		vetortemp=aux.setVetorNegativo(vetortemp);//seta o valor -1 em todas as posicoes do vetor

		do{
			for (int col = 0; col < numeroCidades; col++) {

				if((col==0) && (vetortemp[col]==-1)){                     //se for a primeira iteracao
					vetortemp[col]=martrizSelecionaMaisAptos[0][vlrPai]; //pega o primeiro valor da matriz e coloca no vetor
				}else{

					vlrMae=aux.getNumeroAleatorio(numeroCidades); 							//gera uma posicao aleatoria
					vlrMae=martrizSelecionaMaisAptos[1][vlrMae]; 							//pega o valor na posicao do valor gerado
					if((aux.ehCidadeValida(vlrMae, vetortemp)) && (vetortemp[col]==-1) ){ 	//testa se o individuo(Cidade) existe no vetor
						vetortemp[col]=vlrMae;											   // se nao existir adiciona ao vetor				
					} // fim do if

					else{
						vlrPai=aux.getNumeroAleatorio(numeroCidades);
						vlrPai=martrizSelecionaMaisAptos[0][vlrPai];
						if((aux.ehCidadeValida(vlrPai, vetortemp)) && (vetortemp[col]==-1)){
							vetortemp[col]=vlrPai;
						}

					}//fim do else

				}//fim do else


			}//fim do for da coluna
			for (int i = 0; i < vetortemp.length; i++) {  //
				if(vetortemp[i]==-1)				  //	
					naoTerminou=true;
				else
					naoTerminou=false;
			}
		}while(naoTerminou);

		realizaMutacao(vetortemp);

	}
	/* realiza mutacao por permutacao
	 * troca as posicoes do vetor
	 */
	public static void realizaMutacao(int[] vetortemp) {

		int temp;
		int numero=aux.getNumeroAleatorio(numeroCidades);

		temp=vetortemp[numero];
		if(numero==numeroCidades-1)
			vetortemp[numero]=vetortemp[numero-1];
		else{
			vetortemp[numero]=vetortemp[numero+1];
			vetortemp[numero+1]=temp;
		}

		for (int ln = 0; ln < martrizSelecionaMaisAptos.length; ln++) {

			for (int col = 0; col < numeroCidades; col++) {

				if(martrizSelecionaMaisAptos[ln][col]==-1)
					martrizSelecionaMaisAptos[ln][col]=vetortemp[col];

			}
		}

	}

	public static void imprime(){

		System.out.println("\n\nGERACAO NUMERO:"+ controleGeracoes);
		for (int ln = 0; ln < m_populacao.length; ln++) {

			for (int col = 0; col < numeroCidades; col++) {

				System.out.print(m_populacao[ln][col] + " => ");
			}

			System.out.print(" Distancia = " +vetorDistanciaTotal[ln]);
			System.out.printf(" Aptidao= "+"%.2f %%",(calculaAptidao(vetorDistanciaTotal,ln)));
			System.out.print("\n");	
		}
		mostraMaisAptoGeracao();
		guardaMaisAptoGeracao(controleGeracoes);

	}
	public static void mostraMaisAptoGeracao(){

		int pos=getPosicaoMaisApto();

		System.out.print("\nMAIS APTO: ");
		for (int col = 0; col < numeroCidades; col++) {

			System.out.print(m_populacao[pos][col] + " => ");

		}
		System.out.print(" Distancia = " +vetorDistanciaTotal[pos]);
		System.out.printf(" Aptidao= " + "%.2f %%" , vetorAptidaoPorPopulacao[pos]);

	}
	public static void guardaMaisAptoGeracao(int controleGeracoes){

		int pos=getPosicaoMaisApto();
		double aptidao=vetorAptidaoPorPopulacao[pos];


		for (int col = 0; col < numeroCidades; col++) {

			matrizMaisAptos[controleGeracoes][col]=m_populacao[pos][col];

			if(col==numeroCidades-1){

				matrizMaisAptos[controleGeracoes][col+1]=aptidao;
				matrizMaisAptos[controleGeracoes][col+2]=controleGeracoes;
				matrizMaisAptos[controleGeracoes][col+3]=vetorDistanciaTotal[pos];
			}
		}
	}


	public static double[] preencheAptidaoPopulacao(double aptidao, int posicao ){

		vetorAptidaoPorPopulacao[posicao]=aptidao;


		return vetorAptidaoPorPopulacao;
	}

	public static void mostraMelhorResultado(){

		double	aptidao=matrizMaisAptos[0][numeroCidades];
		double distancia=matrizMaisAptos[0][numeroCidades+2];
		int posicao=0;	

		for (int ln = 0; ln < matrizMaisAptos.length; ln++) {

			if((matrizMaisAptos[ln][numeroCidades]>aptidao) && (matrizMaisAptos[ln][numeroCidades+2]<distancia) ){
				aptidao=matrizMaisAptos[ln][numeroCidades];
				posicao=ln;
			}
		}

		System.out.print("\n\nPROVAVEL MELHOR CAMINHO: ");
		for (int col = 0; col < numeroCidades; col++) {

			System.out.print((int)matrizMaisAptos[posicao][col] + " => ");

		}
		System.out.print(" Distancia = " +matrizMaisAptos[posicao][numeroCidades+2]);
		System.out.printf(" Aptidao= " + "%.2f %%" , matrizMaisAptos[posicao][numeroCidades]);
		System.out.println("  Geracao: "+ (int)matrizMaisAptos[posicao][numeroCidades+1]);

	}

}

