package desafioMediana;

import java.util.ArrayList;
import java.util.Scanner;

public class Principal {

	public static void main(String[] args) {
		ArrayList<Mediana> arlMediana = new ArrayList<>();
		int quantidadeItens;
		Scanner dados=new Scanner(System.in);
		Mediana mediana=new Mediana();		
		
		System.out.println("Digite o a quantidade de elementos desejados: ");
		quantidadeItens=dados.nextInt();
		
	   	if((mediana.verificaParidadeNumero(quantidadeItens)) || (quantidadeItens<3)) {
			 
			 System.err.println("\nO numero de elementos deve ser um numero impar e"
			 		            + "maior ou igual a 03(tres).");
			 System.err.println("\nPor favor comece novamente.");
		 }else {
			     	 for(int i=0; i<quantidadeItens; i++) {
			     		 	System.out.println("Digite o valor para o item("+i+"):");	 
		         	  		Mediana md=new Mediana(dados.nextInt());
		         	  		arlMediana.add(md); 	 /* adiciona os objetos ao arrayList */
			     	 }
		dados.close();
     	
		/* Ordeno a lista com o metodo ComparaNumero que implementa Comparator*/
		arlMediana.sort(new ComparaNumero());
		
		/*Imprimo a mediana*/
     	System.out.println("O valor da mediana e: "+ arlMediana.get(mediana.verificaPosicaoValorMedio(quantidadeItens))
							.getNumero()); 

		 }//fim do else
	}//fim da classe main
 }// fim da classe principal
