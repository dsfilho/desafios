/*25-Desenvolva um algoritmo na forma de fluxograma, que seja capaz de  simular o jogo dos jarros que consiste no seguinte:
Juntar a quantidade pedida de �gua em �nico jarro.

Para este algoritmo considere o seguinte: Dois jarros jr_1 e jr_2 de volume m�ximo 5 e 3 respectivamente, e  atrav�s 
das opera��es encher , esvaziar e despejar deixar o jr_1 com volume igual a 4.


O algoritmo dever� permitir ao jogador selecionar um jarro, e uma opera��o repetindo isto at� que o jr1 esteja com volume igual a 4.


Considere para isso as seguintes regras:


1-Ao esvaizar um jarro seu volume final � zero. 
 
Exemplo:

 -escolhido o jr_1, com  volume_jr1=5 e a opera��o ="esvaziar o volume do jr1" o resultado �: volume_jr1=0


2-N�o � poss�vel escolher o valor para encher um jarro.Ou enche at� seu volume total ou completa o restante at� o total.

Exemplo: 
  -Escolhido o jr_1, com volume_jr1=3 e opera��o ="encher jr1" o volume do jr1 passa a ser igual �: volume_jr1=5


3-� possivel encher ou completar um jarro 

  -Exemplo: Escolhido o jr_2, com  volume_jr2=1 e  opera��o=" encher volume_jr2=3" o volume do jr1 passa a ser igual a:4.

4-� possivel despejar o conte�do de um jarro em outro at� o volume m�ximo do jarro de destino

  -Exemplo: escolhido o jr1 com jr1_volume=2 e  opera��o=" despejar jr1 em jr2" e jr2_volume=1 o volume final do jr2=3 e do jr1=0. */
  
#include <stdio.h>
#include <stdlib.h>
#define jr1VolumeMax 5
#define jr2VolumeMax 3
int jr1Volume=0,jr2Volume=0;
main(){
       
 int numeroJarro=0;
 char op;
 
 while(jr1Volume!=4) 
 {
 system("cls");
 printf("\n**************************");
 printf("\n Numeros de jarros=2");
 printf("\n volume do jarro 1=%i",jr1Volume);
 printf("\n volume do jarro 2=%i",jr2Volume);
 printf("\n**************************");
 printf("\n Escolha uma operacao:");
 printf("\n Encher(E)");
 printf("\n Esvaziar(V)");
 printf("\n Despejar(D)");
 printf("\n Resetar(R)");
 printf("\n\nDigite a opcao desejada:");
 scanf("%c",&op);
       
   if(op=='R' || op=='r'){
     jr1Volume=esvaziarJarro();
     jr2Volume=esvaziarJarro();
     printf("\n O jogo foi reiniciado!\n Pressione uma tecla para continuar.");
     system("pause>null");        
   }
       
   if(op=='v' || op=='V'){
    printf("\n\nDigite o numero do jarro:");
     scanf("%i",&numeroJarro);
    if(numeroJarro==1){
     jr1Volume=esvaziarJarro();
     printf("jarro 1=%i",jr1Volume); 
     system("pause>null"); 
     }else{
     jr2Volume=esvaziarJarro();
     printf("jarro 2=%i",jr2Volume); 
     system("pause>null");    
     }
   }  
   
   if(op=='e' || op=='E'){
     printf("\n\nDigite o numero do jarro:");
 scanf("%i",&numeroJarro);         
     if(numeroJarro==1){
          jr1Volume=encherJarro(numeroJarro);
           printf("jarro 1=%i",jr1Volume);
           system("pause>null");
     }else{
	  if(numeroJarro==2){
           jr2Volume=encherJarro(numeroJarro);
            printf("jarro 2=%i",jr2Volume);
            system("pause>null");
     }else {
		 printf("\nNumero do jarro invalido");	
		 system("pause>null");
		}
	 }//fim do if numeroJarro==2
   } // fim do if encher 
   
   
    if(op=='d' || op=='D'){
    printf("\n\nDigite o numero do jarro:");
    scanf("%i",&numeroJarro);
    if(numeroJarro<=0 || numeroJarro>2){
      printf("\nNumero do jarro invalido");
      system("pause>null");
    }else{
          despejarJarro(numeroJarro);   
    }//fim do else
   }// fim do op==d
       
       
       } //fim do while     
}//fim do main

int  esvaziarJarro(){
     
     int zeraJarro=0; 
     return zeraJarro;
      
      
}// fim do esvaziarJarro


int  encherJarro(int numeroJarro){
     
     if(numeroJarro==1){
     return 5;
     }
     else{
          return 3;           
       }
      
}// fim do encherJarro
 //despejarJarro(int numeroJarro,int *jr1Volume,int *jr2Volume)
 despejarJarro(int numeroJarro){
 
  if(numeroJarro==2){
                    
   for(;jr1Volume != jr1VolumeMax;jr2Volume--){
               
     if(jr2Volume >0){
            jr1Volume=jr1Volume + 1;
                        
     }else{
            break;
        } //fim do if   jr2Volume >0        
    }// fim do for
   }else{
    for(;jr2Volume != jr2VolumeMax;jr1Volume--){
                   
     if(jr1Volume > 0){
        jr2Volume=jr2Volume+1;
               
      }else {
          break;     
       } //fim do if   jr1Volume >0   
    } //fim do for    
   
 }//fim do else numeroJarro==2   
   
}//fim do despejarJarro