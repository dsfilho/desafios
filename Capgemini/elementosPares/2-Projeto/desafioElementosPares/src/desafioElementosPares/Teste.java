package desafioElementosPares;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class Teste {
	 ElementosPares elemPares=new ElementosPares();
	@Test
	void VerificaElementosPares() {
		int numeroDiferenca=2;
		int elementos[]= {1,5,3,4,2}; /*Array de controle */
		int resultadoEsperado=3;
		int resultadoObitido=elemPares.verificaElementosPares(elementos, numeroDiferenca);
		assertEquals(resultadoEsperado, resultadoObitido);
	}
	
}
