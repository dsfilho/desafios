package desafioMediana;

public class Mediana {

	private int numero;
	private int quantidadeItens;
		
public Mediana(int numero) {
	    this.numero = numero;
}

public Mediana() {
	
}

public  boolean verificaParidadeNumero(double quantidadeItens2) {
	 
	 boolean ehNumeroPar=false;
	 
	 if(quantidadeItens2 % 2 == 0) { /* Se o resto da divisao por dois for 0 então o numero e par */
		 ehNumeroPar=true;
	 }
        
	return ehNumeroPar;
}

public int getNumero() {
	return numero;
}

public int getQuantidadeItensLista() {
	return quantidadeItens;
}

public  int verificaPosicaoValorMedio(int quantidadeItens) {
	    if(quantidadeItens < 9) {			
	    	return (int) Math.nextDown((quantidadeItens +1) / 2); /*retorna a posicao da mediana na lista, faco o arredondamento para baixo com Math.nextDown  */
	    }
	    return Math.round(quantidadeItens / 2); /*retorna a posicao da mediana na lista, faco o arredondamento da casa decimal com Math.Round*/
}

}