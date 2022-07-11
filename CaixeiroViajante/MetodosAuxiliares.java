import java.util.Random;


/*O metodo retorna um numero aleatorio entre
 * 0 e o valor de cidades-1. Por exemplo se o numero
 * de cidades for igual a 6 entao o metodo retornara
 * um numero entre 0 e 5.
 */
public class MetodosAuxiliares {

	
	/*O metodo retorna um numero aleatorio entre
	 * 0 e o valor de cidades-1. Por exemplo se o numero
	 * de cidades for igual a 6 entao o metodo retornara
	 * um numero entre 0 e 5.
	 */
	public  int getNumeroAleatorio(int numeroCidades){ 
		
		return new Random().nextInt(numeroCidades);
	}	
	
	
	//Calcula o numero de rotas	
	public  int getNumeroRotas(int numeroCidades){ 		
			return numeroCidades*(numeroCidades-1) /2;
	}	
	
	
	public  double[][] setMatrizNegativa(double matrizValores[][],int linha, int coluna){
		
		for (int ln= 0; ln < linha; ln++) {
				
				for (int col = 0; col < coluna; col++) {
					
					matrizValores[ln][col]=-1;
					
				}
		}
				
	return matrizValores;
}
	
	public  int[][] setMatrizNegativa(int matrizValores[][],int linha, int coluna){
		
		for (int ln= 0; ln < linha; ln++) {
				
				for (int col = 0; col < coluna; col++) {
					
					matrizValores[ln][col]=-1;
					
				}
		}
				
	return matrizValores;
}	
	
public  double[] setVetorNegativo(double vetorValores[]){
	
	for (int pos = 0; pos < vetorValores.length; pos++) {
		
		vetorValores[pos]=-1;
	}
	
	return vetorValores;
	
}
	
public  int[] setVetorNegativo(int vetorValores[]){
	
	for (int pos = 0; pos < vetorValores.length; pos++) {
		
		vetorValores[pos]=-1;
	}
	
	return vetorValores;
	
}

public  boolean saoTodosIguais(double[] v_distancias){
	boolean saoIguais=false;
	int cont=0;
	double valor=v_distancias[0];
for(int ln=0; ln < v_distancias.length;ln++){
		
		if(valor!=v_distancias[ln])
			cont++;
			
	}
	
	if(cont>=1)
	   saoIguais=false;
	else
		saoIguais=true;
	
	return saoIguais;
	
	
}


//Metodo que retorna o maior valor de um vetor
public  double getMaiorValor(double [] v_distancias){
	
	double maiorValor=v_distancias[0];
	
	
	for(int ln=0; ln < v_distancias.length;ln++){
		
		if(v_distancias[ln]>maiorValor)
			maiorValor=v_distancias[ln];
	}
	
	
	return maiorValor; 
}

/*Metodo que verifica se uma cidade nao existe mais de 
 * uma vez dentro de uma populacao(Cromossomo) se existir
 * o metodo retorna false. Ele recebe a cidade como parametro
 */
public  boolean ehCidadeValida(int num,int [] vetorTemporario){
	boolean ehvalido=false;
	int cont=0;
   
for (int pos = 0; pos <vetorTemporario.length; pos++) {
		
		if(vetorTemporario[pos]==num){  //Se o valor na posicao [pos] for igual ao numero a variavel cont e incrementada
				cont++;
				if(cont>=1)        //se cont for maior ou igual a 1 entao a cidade ja existe nessa populacao ehvalido=false
					ehvalido=false;
		}else{
			if(cont==0)         //se cont for igual a 0 entao essa cidade nao existe nessa populacao ehvalido=true
					ehvalido=true;
			
		 	} //fim do else
		
     	}//fim do for
	
 return ehvalido;
}



/* Metodo que cria a matriz de adjacencia que nos diz 
 * se existe aresta  sobre um vertice.
 * Nesse casso assuma vertice=CIDADE E aresta=ROTA.
 * O metodo percorre a matriz e toda vez que a linha 
 * for diferente da coluna a matriz na posicao [ln][col]
 * recebe 1 caso contrario recebe 0. Dessa maneira se existir
 * o valor 1 na posicao [1][0] existe uma rota entre a cidade
 * 0 e a cidade 1
 */
public  int [][] preencheMatrizAdjacencia(int[][] matrizAdjacencia,int numeroCidades){
	
//ln=linha col=coluna	
for (int ln= 0; ln < numeroCidades; ln++) {
		
		for (int col = 0; col < numeroCidades; col++) {
			
			if((numeroCidades>0) && (ln!=col))
					matrizAdjacencia[ln][col]=1;
				else
					matrizAdjacencia[ln][col]=0;
			}
		}

	return matrizAdjacencia;
	}
}