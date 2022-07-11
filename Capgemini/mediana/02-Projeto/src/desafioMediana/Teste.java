package desafioMediana;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class Teste {
	  Mediana mediana=new Mediana();
	@Test
	void verificaParidadeNumero() {
		int numero=0;
		boolean obtido;
	    boolean esperado=true;
	    
		for(int i=2;i<10000;i=i+2) {
			numero=i;
			obtido=mediana.verificaParidadeNumero(numero);
		    assertEquals(esperado, obtido);
		}
}
	
	@Test
	/*Para 5 numeros a mediana  na posicao 2  
	 *Para 7 numeros a mediana  na posicao 3
	 *Para 9 numeros a mediana na posicao 4  
	 *  */
	void verificaPosicaoValorMedio() { 
		int qtdNumero=9;
		int esperado=4;
		double valorObtido;
		valorObtido=mediana.verificaPosicaoValorMedio(qtdNumero);
		assertEquals(esperado, valorObtido);
	}
	
}
