package desafioElementosPares;

public class ElementosPares {

	private int elementos[];
	private int numeroElementos;
	private int numeroDiferenca;
	
public ElementosPares() {

}	

public void setNumeroDiferenca(int numeroDiferenca) {
	this.numeroDiferenca=numeroDiferenca;
}

public int getNumeroDiferenca() {
	return numeroDiferenca;
}
	
public void setNumeroElementos(int numeroElementos) {
	 this.numeroElementos = numeroElementos;
}

public int getNumeroElementos() {
	 return numeroElementos;
}


public int[] getElementos() {
	
	return elementos;
}

public void setElementos(int numeroElementos) {
	
	this.elementos=new int [numeroElementos];

}

public  int verificaElementosPares(int elementos[],int numeroDiferenca) {
	 this.numeroDiferenca=numeroDiferenca;
	  int elementoAtual;
	  int diferencaCalculada=0;
	  int controle=0;
	  
	for(int i=0;  i<elementos.length;i++) {
		
		    for(int j=0;j<elementos.length;j++) {
		    	
		    		elementoAtual=elementos[i];
				
		    		diferencaCalculada=elementoAtual - elementos[j];
				
				 	if(diferencaCalculada==numeroDiferenca) 
				 		controle=controle+1;
				}//fim do for de indice J
	}//fim do for de indice I
		
	return controle;
}//fim de verificaElementosPares
}