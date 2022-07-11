/*
Faculdade: IBES/FACSAL
Disciplina: Linguagem de Programação Estruturada
Professor: Cláudio Barbosa
Turno: Nortuno
Semestre: 2º
Aluno: Daniel Dos Santos Filho
*/

#include<stdio.h>
#include<stdlib.h>
main ()

{
	/*declaração e inicialização das variáveis que contém a quantidade de notas*/

	int cdl2=500, cdl5=500, cdl10=400, cdl20=400, cdl50=300, cdl100=200;


	/*declaração da variável saldo do tipo long int pois o valor inicial do saldo não caberia em um int*/

	long int saldo,devolve_saldo;


	/*variável para a leitura dos valores*/

	int vlr_desejado;


	/*variáveis auxiliares*/

	int vlr_rest=0, cont_saque=0,qtd_notas, vlr_notas;


	/*variáveis que controlam a quantidade de notas*/

	int dois=0, cinco=0, dez=0, vinte=0, cinquenta=0, cem=0;


	/* variável para controlar o laço*/

	char op='s'; /*variável inicializada com s para entrar no laço*/


	/*cálculo do saldo inicial do caixa em R$*/

	saldo = cdl2 * 2 + cdl5 * 5 + cdl10 *10 + cdl20 *20;
	saldo = saldo + cdl50 * 50 + cdl100 * 100;



	while((op=='s') || (op=='S'))

	{
	   clrscr();
	   printf("\t\t\t#################################\t\t\t");
	   printf("\n\t\t\t ##    Controle de caixa   ## \t\t\t");
	   printf("\n\t\t\t###############################\t\t\t");

	   printf("\n\n #################################\t\t\t");
	   printf("\n ## Cédulas de R$2,00   = %d ##",cdl2);
	   printf("\n ## Cédulas de R$5,00   = %d ##",cdl5);
	   printf("\n ## Cédulas de R$10,00  = %d ##",cdl10);
	   printf("\n ## Cédulas de R$20,00  = %d ##",cdl20);
	   printf("\n ## Cédulas de R$50,00  = %d ##",cdl50);
	   printf("\n ## Cédulas de R$100,00 = %d ##",cdl100);
	   printf("\n ##--------------------------------------##");
	   printf("\n ##Saldo  dispon\xA1vel = R$ %ld  ##",saldo);
	   printf("\n ##--------------------------------------##");


	 	/*só pede para digitar um valor se cont_saque==0 isso evita que ao voltar pro laço o valor que o usuário digitou se perca*/

		if (cont_saque==0)
		{
	  		printf("\n Digite o valor do saque(R$): ");
	  		scanf("%d",&vlr_desejado);
	 	}

		else
		{
			printf("\n Digite o valor do saque(R$): %d",cont_saque);
			printf("\n\n Voc\x88 receber\xA0: "); /* os codigos \x88 e \xA0 correspondem as letras acentuadas em hexadecimal na tabela ascii*/
		}


		if (dois>0)
		{
			printf("\n\n %d Nota(s) de R$ 2,00",dois);
			dois=0;
		}


		if (cinco>0)
		{
			printf("\n %d Nota(s) de R$ 5,00",cinco);
			cinco=0;
		}


		if (dez>0)
		{
			printf("\n %d Nota(s) de R$ 10,00",dez);
			dez=0;
		}


		if (vinte>0)
		{
			printf("\n %d Nota(s) de R$ 20,00",vinte);
			vinte=0;
		}


		if (cinquenta>0)
		{
			printf("\n %d Nota(s) de R$ 50,00",cinquenta);
			cinquenta=0;
		}


		if (cem>0)
		{
			printf("\n %d Nota(s) de R$ 100,00",cem);
			cem=0;
		}


		if(cont_saque>0)
		{
			printf("\n\n\tDeseja efetuar novo saque? (S/N): ");
			getchar();
			scanf("%c",&op);
			cont_saque=0;

			/*evita que depois de um saque caso um valor inválido por ex: w 	 seja selecionado e o programa aceite, pois vlr_desejado contém o valor do último saque*/

			vlr_desejado=0;

			/*volta pro inicio do laço*/
			continue;
	    	}



		/*testa se o valor digitado está na faixa permitida maior ou igual a 2 e menor ou igual a 500*/

		if ((vlr_desejado<2) || (vlr_desejado>500))
		{
			printf("\n\n\tValor inválido!");
			printf("\n\n\tO Valor deve estar entre R$2,00 e R$500,00");
			printf("\n\n\tPressione uma tecla para digitar um novo valor.");
			system("pause>nul");

			/*volta para o início do laço pois o valor digitado não está na faixa permitida*/
			continue;

		}

/*se o valor desejado for igual a
três imprime na tela que não é possível entregar o
valor solicitado, pois o caixa não tem notas de um real*/

		if (vlr_desejado==3)
		{
			printf("\n\n\tOperação impossível! Não há cédula de R$ 1,00");
			printf("\n\n\tPressione uma tecla para digitar um novo valor.");
			system("pause>nul");

			/*volta ao início do laço*/
			continue;

		 }



		/*testa se o valor digitado é maior que o saldo disponível*/

		if(vlr_desejado>saldo)
		{
			printf("\n\n\tSaldo insuficiente!");
			printf("\n\n\tPressione uma tecla para digitar um novo valor.");
			system("pause>nul");

			/*volta para o início do laço, pois o valor digitado é maior que o saldo disponível*/

			/*volta ao início do laço*/
			continue;
		}



		/*começo do bloco que determina a entrega das notas*/

		/*as variaveis auxiliares recebem o valor digitado do usuário*/

			vlr_rest = vlr_desejado;
			cont_saque = vlr_desejado;

		/*a variável devolve_saldo recebe o saldo inicial do caixa */

			devolve_saldo = saldo;

		if (vlr_rest>=150)
		{
			if(cdl100!=0)
			{

			/* Nesse bloco vlr_notas recebe o valor em R$ das cédulas de 100, depois a variável cem recebe vlr_rest dividido por 100, obviamente o valor será truncado, pois as variáveis são do tipo int, e esse valor será usado para cont
rolar a quantidade de cédulas de cem que serão entregues, é efetuada o mod do valor restante por 100 nesse ponto vlr_rest contém o restante do valor que não foi entregue após isso o valor do saldo é decrementado em função do valor da variável cem*/

          		vlr_notas = cdl100*100;
		  	cem = vlr_rest / 100;
		  	qtd_notas = cem*100;
		  	cdl100 = cdl100 - cem;
		  	vlr_rest = vlr_rest % 100;
		  	saldo = saldo - (cem*100);

		 	/*fim do if que verifica se cdl100!=0*/
			}


			/*nesse bloco é efetuado um teste para verificar se o valor em R$ de qtd_notas é maior que o valor  de vlr_notas, se for então o saldo é devolvido ao caixa e vlr_rest recebe qtd_notas, o valor que foi decrementado de cdl100
é devolvido ao mesmo e a variável cem recebe 0, para que ao voltar para início do laço ele não imprima a quantidade de notas  de 100 a serem entregues. Isso evita que caso a quantidade de notas não for suficiente para entreguar o valor desejado a v
ariável da cédula passe a ser um valor negativo*/

			if (qtd_notas>vlr_notas)
			{

				saldo = saldo + (cem*100);
				vlr_rest = qtd_notas;
				cdl100 = cdl100 + cem;
				cem = 0;
			}

			if ((vlr_rest == 3) || (vlr_rest == 1))
			{
				cdl100 = cdl100 + 1;
				cem = cem - 1;
				vlr_rest = vlr_rest + 100;

			/*fim do if que verifica se o resto==1 ou resto==3*/
			}

		/*fim do if que verifica se vlr>=150*/
		}



		if (vlr_rest>=60)
		{

		 	if(cdl50!=0)
		  	{

			/*nesse bloco vlr_notas recebe o valor em R$ das cédulas de 50, depois a variável cinquenta recebe vlr_rest dividido por 50, obviamente o valor será truncado, pois as variáveis são do tipo int, e esse valor será usado para c
ontrolar a quantidade de cédulas de cinquenta que serão entregues, é efetuada o mod do valor restante por 50 nesse ponto vlr_rest contém o restante do valor que não foi entregue após isso o valor do saldo é decrementado em função do valor da variáv
el cinquenta*/

				vlr_notas = cdl50*50;
		   		cinquenta = vlr_rest / 50;
		   		qtd_notas = cinquenta*50;
		   		cdl50 = cdl50 - cinquenta;
		   		vlr_rest = vlr_rest % 50;
		   		saldo = saldo - (cinquenta*50);

			/*fim do if que verifica se cdl50!=0*/
			}

			/*nesse bloco é efetuado um teste para verificar se o valor em R$ de qtd_notas é maior que o valor  de vlr_notas, se for então o saldo é devolvido ao caixa e vlr_rest recebe qtd_notas, o valor que foi decrementado de cdl50 é
 devolvido ao mesmo e a variável cinquenta recebe 0, para que ao voltar para início do laço ele não imprima a quantidade de notas  de 50 a serem entregues. Isso evita que caso a quantidade de notas não for suficiente para entreguar o valor desejado
 a variável da cédula passe a ser um valor negativo*/

			if (qtd_notas>vlr_notas)
			{
				saldo = saldo + (cinquenta*50);
				vlr_rest = qtd_notas;
				cdl50 = cdl50 + cinquenta;
				cinquenta = 0;
			}

			if ((vlr_rest == 3) || (vlr_rest == 1))
			{
				cdl50 = cdl50 + 1;
				cinquenta = cinquenta - 1;
				vlr_rest = vlr_rest + 50;

			/*fim do if que verifica se o resto==1 ou resto==3*/
			}

	   	/*fim do if que verifica se vlr_resto>=60*/
		}



		if (vlr_rest>=30)
		{

			if(cdl20!=0)
			{

			/*nesse bloco vlr_notas recebe o valor em R$ das cédulas de 20, depois a variável vinte recebe vlr_rest dividido por 20, obviamente o valor será truncado, pois as variáveis são do tipo int, e esse valor será usado para contr
olar a quantidade de cédulas de vinte que serão entregues, é efetuada o mod do valor restante por 20 nesse ponto vlr_rest contém o restante do valor que não foi entregue após isso o valor do saldo é decrementado em função do valor da variável vinte
*/

		 		vlr_notas = cdl20*20;
		 		vinte = vlr_rest / 20;
		 		qtd_notas = vinte*20;
		  		cdl20 = cdl20 - vinte;
		  		vlr_rest = vlr_rest % 20;
		  		saldo = saldo - (vinte*20);

			/*fim do if que verifica se cdl20!=0*/
			}


			/*nesse bloco é efetuado um teste para verificar se o valor em R$ de qtd_notas é maior que o valor  de vlr_notas, se for então o saldo é devolvido ao caixa e vlr_rest recebe qtd_notas, o valor que foi decrementado de cdl20 é
 devolvido ao mesmo e a variável vinte recebe 0, para que ao voltar para início do laço ele não imprima a quantidade de notas  de 20 a serem entregues. Isso evita que caso a quantidade de notas não for suficiente para entreguar o valor desejado a v
ariável da cédula passe a ser um valor negativo*/

			if (qtd_notas>vlr_notas)
			{

				saldo = saldo + (vinte*20);
				vlr_rest = qtd_notas;
				cdl20 = cdl20 + vinte;
				vinte = 0;
			}

			if ((vlr_rest == 3) || (vlr_rest == 1))
			{
				cdl20 = cdl20 + 1;
				vinte = vinte - 1;
				vlr_rest = vlr_rest + 20;

			/*fim do if que verifica se o resto==1 ou resto==3*/
			}

    		/*fim do if que verifica se vlr>=30*/
		}



		if (vlr_rest>=15)
		{

			if(cdl10!=0)
			{

			/*nesse bloco vlr_notas recebe o valor em R$ das cédulas de 10, depois a variável dez recebe vlr_rest dividido por 10, obviamente o valor será truncado, pois as variáveis são do tipo int, e esse valor será usado para control
ar a quantidade de cédulas de dez que serão entregues, é efetuada o mod do valor restante por 10 nesse ponto vlr_rest contém o restante do valor que não foi entregue após isso o valor do saldo é decrementado em função do valor da variável dez*/

				vlr_notas = cdl10*10;
		  		dez = vlr_rest / 10;
		  		qtd_notas = dez*10;
		  		cdl10 = cdl10 - dez;
		  		vlr_rest = vlr_rest % 10;
		  		saldo = saldo - (dez*10);

			/*fim do if que verifica se cdl10!=0*/
			}


			/*nesse bloco é efetuado um teste para verificar se o valor em R$ de qtd_notas é maior que o valor  de vlr_notas, se for então o saldo é devolvido ao caixa e vlr_rest recebe qtd_notas, o valor que foi decrementado de cdl10 é
 devolvido ao mesmo e a variável dez recebe 0, para que ao voltar para início do laço ele não imprima a quantidade de notas  de 10 a serem entregues. Isso evita que caso a quantidade de notas não for suficiente para entreguar o valor desejado a var
iável da cédula passe a ser um valor negativo*/

		 	if (qtd_notas>vlr_notas)
			{
				saldo = saldo + (dez*10);
				vlr_rest = qtd_notas;
				cdl10 = cdl10 + dez;
				dez = 0;
			}

			if ((vlr_rest == 3) || (vlr_rest == 1))
			{
				cdl10 = cdl10 + 1;
				dez = dez - 1;
				vlr_rest = vlr_rest + 10;

			/*fim do if que verifica se o resto==1 ou resto==3*/
			}

       		/*fim do if que verifica se vlr_rest>=15*/
		}



		if (vlr_rest>=5)
		{

			if(cdl5!=0)
			{

			/*nesse bloco vlr_notas recebe o valor em R$ das cédulas de 5, depois a variável cinco recebe vlr_rest dividido por 5, obviamente o valor será truncado, pois as variáveis são do tipo int, e esse valor será usado para control
ar a quantidade de cédulas de cinco que serão entregues, é efetuada o mod do valor restante por 5 nesse ponto vlr_rest contém o restante do valor que não foi entregue após isso o valor do saldo é decrementado em função do valor da variável cinco*/

		  		vlr_notas = cdl5*5;
		  		cinco = vlr_rest / 5;
		  		qtd_notas = cdl5*cinco;
		  		cdl5 = cdl5 - cinco;
		  		vlr_rest = vlr_rest % 5;
		    		saldo = saldo - (cinco*5);

			/*fim do if que verifica se cdl5!=0*/
			}


			/*nesse bloco é efetuado um teste para verificar se o valor em R$ de qtd_notas é maior que o valor  de vlr_notas, se for então o saldo é devolvido ao caixa e vlr_rest recebe qtd_notas, o valor que foi decrementado de cdl5 é
devolvido ao mesmo e a variável cinco recebe 0, para que ao voltar para início do laço ele não imprima a quantidade de notas  de 5 a serem entregues. Isso evita que caso a quantidade de notas não for suficiente para entreguar o valor desejado a var
iável da cédula passe a ser um valor negativo*/

		  	if (qtd_notas>vlr_notas)
			{

				saldo = saldo + (cinco*5);
				vlr_rest = qtd_notas;
				cdl5 = cdl5 + cinco;
				cinco = 0;
			}

			if ((vlr_rest == 3) || (vlr_rest == 1))
			{
				cdl5 = cdl5 + 1;
				cinco = cinco - 1;
				vlr_rest = vlr_rest + 5;

			/*fim do if que verifica se o resto==1 ou resto==3*/
			}

		/*fim do if que testa se vlr_rest>=5*/
		}



		if (vlr_rest>=1)
		{

			if(cdl2!=0)
		  	{

			/*nesse bloco vlr_notas recebe o valor em R$ das cédulas de 2, depois a variável dois recebe vlr_rest dividido por 2, obviamente o valor será truncado, pois as variáveis são do tipo int, e esse valor será usado para controla
r a quantidade de cédulas de dois que serão entregues, é efetuada o mod do valor restante por 2 nesse ponto vlr_rest contém o restante do valor que não foi entregue após isso o valor do saldo é decrementado em função do valor da variável dois*/

				dois = vlr_rest / 2;
				cdl2 = cdl2 - dois;
		   		vlr_rest = vlr_rest % 2;
		   		saldo = saldo - (dois*2);

			/*fim do if que verifica se cdl2!=0*/
			}
		/*fim do if que testa se vlr_rest>=1*/
		}


		/*se o valor restante armazenado em vlr_rest for > 0 então o caixa esta sem cédulas e não pode entregar o valor solicitado dessa forma o saldo é restituído juntamente com a quantidade de cédulas. Uma mensagem de erro é mostrado ao u
suário pedindo para que pressione uma tecla, depois disso volta ao início do laço*/

		if (vlr_rest>0)
		{
			printf("\n\n\tImpossível entregar esse valor!");
			printf("\n\n\tPressione uma tecla para efetuar um novo saque.");
			vlr_rest = 0;
			saldo = devolve_saldo;
			cdl2 = cdl2 + dois;
			cdl5 = cdl5 + cinco;
			cdl10 = cdl10 + dez;
	   		cdl20 = cdl20 + vinte;
	  		cdl50 = cdl50 + cinquenta;
			cdl100 = cdl100 + cem;
	  		dois = 0;
			cinco = 0;
			dez = 0;
			vinte = 0;
			cinquenta = 0;
			cem = 0;
			cont_saque = 0;
			system("pause>nul");
			continue;
		}


	}/*fim do while*/


	printf("\n\tObrigado por utilizar nosso Programa!");
	system("pause>nul");

}/*fim da funcao main*/