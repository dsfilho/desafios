# Desafios de Programação

-  Problema do Caixeiro Viajante
-  Problema da Mochila
-  Problema do Jarro
-  Problema do Caixa Eletrônico


## Caxeiro Viajante - IA - Otimização Combinatória


Problema: "Suponha que um caixeiro viajante tenha que visitar ( N ) cidades diferentes, iniciando e encerrando sua viagem na primeira cidade. Suponha, também, que não importa a ordem com que as cidades são visitadas e que de cada uma delas pode-se ir diretamente a qualquer outra. O problema do caixeiro viajante consiste em descobrir a rota que torna mínima a viagem total.

Exemplificando o caso n = 4:
se tivermos quatro cidades A, B, C e D, uma rota que o caixeiro deve considerar poderia ser: saia de A e daí vá para B, dessa vá para C, e daí vá para D e então volte a A. Quais são as outras possibilidades ? "

No diretório CaixeiroViajante, proponho uma solução em JAVA utilizando algoritmo genético, solução desenvolvida enquanto estudava Inteligência Artificial na faculdade de ciência da computação, por isso ela está longe de ser completa ou a melhor solução.

## Problema da Mochila(Knapsack Problem) - IA - Otimização Combinatória

Problema: "O problema da mochila (em inglês, Knapsack problem) é um problema de optimização combinatória. O nome dá-se devido ao modelo de uma situação em que é necessário preencher uma mochila com objetos de diferentes pesos e valores. O objetivo é que se preencha a mochila com o maior valor possível, não ultrapassando o peso máximo.

O problema da mochila é um dos 21 problemas NP-completos de Richard Karp, exposto em 1972. A formulação do problema é extremamente simples, porém sua solução é mais complexa. Este problema é a base do primeiro algoritmo de chave pública (chaves assimétricas).

Normalmente este problema é resolvido com programação dinâmica, obtendo então a resolução exata do problema, mas também sendo possível usar PSE (procedimento de separação e evolução). Existem também outras técnicas, como usar algoritmo guloso, meta-heurística (algoritmos genéticos) para soluções aproximadas."

No diretório ProblemaMochila, proponho uma solução em JAVA utilizando algoritimo genético. Solução desenvolvida enquanto estudava Inteligência Artificial na faculdade de ciência da computação,por isso ela está longe de ser completa ou a melhor solução.

## Problema do Jarro - Um simples problema de lógica
Problema: Desenvolva um algoritmo  que seja capaz de  simular o jogo dos jarros que consiste no seguinte:

Juntar a quantidade pedida de água em único jarro.

Para este algoritmo considere o seguinte: Dois jarros jr_1 e jr_2 de volume máximo 5 e 3 respectivamente, e  através 
das operações encher , esvaziar e despejar deixar o jr_1 com volume igual a 4.


O algoritmo deverá permitir ao jogador selecionar um jarro, e uma operação repetindo isto até que o jr1 esteja com volume igual a 4.


Considere para isso as seguintes regras:


1-Ao esvaizar um jarro seu volume final é zero. 
 
Exemplo:

 -escolhido o jr_1, com  volume_jr1=5 e a operação ="esvaziar o volume do jr1" o resultado é: volume_jr1=0


2-Não é possível escolher o valor para encher um jarro.Ou enche até seu volume total ou completa o restante até o total.

Exemplo: 
  -Escolhido o jr_1, com volume_jr1=3 e operação ="encher jr1" o volume do jr1 passa a ser igual á: volume_jr1=5


3-É possivel encher ou completar um jarro 

  -Exemplo: Escolhido o jr_2, com  volume_jr2=1 e  operação=" encher volume_jr2=3" o volume do jr1 passa a ser igual a:4.

4-É possivel despejar o conteúdo de um jarro em outro até o volume máximo do jarro de destino

  -Exemplo: escolhido o jr1 com jr1_volume=2 e  operação=" despejar jr1 em jr2" e jr2_volume=1 o volume final do jr2=3 e do jr1=0. 

  No diretório ProblemaJarro, proponho uma solução em C . Solução desenvolvida enquanto estudava linguagem de programação estruturada na faculdade de ciência da computação,por isso ela está longe de ser completa ou a melhor solução.


  ## Problema do Caixa Eletrônico - Um simples problema de lógica

  Problema: Desenvolva um algoritmo para gerenciar os saques de um caixa eletrônico que deve possuir algum mecanismo para decidir o número de notas de cada valor que deve ser disponibilizado para o cliente que realizou o saque.

 -  O critério será o da "distribuição ótima" no sentido de que as notas de menor valor sejam distribuídas em 	      número mínimo possível.

 - Por exemplo, se a quantia solicitada for R$ 187,00, o programa deverá indicar uma nota de R$ 100,00, uma nota de R$ 50,00 , uma nota de R$ 20,00 , uma nota de R$ 10,00 , uma nota de R$ 5,00 e uma nota de R$ 2,00.

- O caixa eletrônico utiliza notas de R$ 100,00, R$ 50,00, R$ 20,00, R$ 10,00, R$ 5,00 e R$ 2,00.