package desafioElementosPares;

import java.util.InputMismatchException;
import java.util.Scanner;


public class Principal {

	public static void main(String[] args) {
	
     Scanner dados=new Scanner(System.in);
     ElementosPares elemPares=new ElementosPares();
     int controleLaco=0;
   
    do{
     		 
		         try{
		           	 System.out.print("Digite o número de elementos do vetor: ");
		        	  elemPares.setNumeroElementos(dados.nextInt());
		        	  elemPares.setElementos(elemPares.getNumeroElementos());
		           	  		
		        	  System.out.print("Digite o valor da diferenca: ");
		        	  elemPares.setNumeroDiferenca(dados.nextInt());
		        	  
		        	  		        	  
		        	  for(int i=0; i<elemPares.getElementos().length; i++) {
		        	       	System.out.print("Digite um valor para a poscao("+i+"):");	
		        	       	elemPares.getElementos()[i]=dados.nextInt();
		        	       	controleLaco++;
		     		 }
		        	
		         }catch (InputMismatchException e){ /*Capturo a exception pois ,somente numeros inteiros sao aceitos*/
		        	 	System.out.println();
		        	 	System.err.println("Recrutador...Algo aconteceu.Somente Valores inteiros sao permitidos.\n");
		        	 	dados.next();   
		         	}
		         
		        
     	}while (controleLaco==0); /*Controle será zero enquanto houver InputMisMatchException */	
     	 
     	dados.close();
  
     	System.out.println("\nExistem("+elemPares.verificaElementosPares(elemPares.getElementos(),elemPares.getNumeroDiferenca())+")"
     			+ "pares no vetor com diferenca de("+elemPares.getNumeroDiferenca()+")");
     	
	}//Fim da funcao main()
}//Fim da classe principal