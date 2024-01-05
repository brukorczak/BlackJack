# Jogo de Blackjack em Java

## Visão Geral

Esta é uma implementação simples do clássico jogo de cartas Blackjack em Java. O jogo inclui uma interface gráfica do usuário construída usando o Swing. Os jogadores podem interagir com o jogo clicando em botões para "Pedir", "Ficar", "Jogar +" e "Sair". O jogo segue as regras padrão do Blackjack, onde o objetivo do jogador é obter um valor de mão o mais próximo possível de 21 sem ultrapassá-lo.

## Como Jogar

1. **Iniciando o Jogo**
   - Execute a classe `Jogo` para abrir a tela de boas-vindas.
   - Clique no botão "START!!" para iniciar o jogo de Blackjack.

2. **Interface do Jogo**
   - A interface do jogo inclui uma representação gráfica das mãos do jogador e do dealer.
   - O botão "Pedir" permite que o jogador solicite cartas adicionais.
   - O botão "Ficar" sinaliza que o jogador terminou de receber cartas.
   - O botão "Jogar +" reinicia o jogo para uma nova rodada.
   - O botão "Sair" encerra o jogo.

3. **Fluxo do Jogo**
   - O dealer e o jogador recebem inicialmente duas cartas cada.
   - Os jogadores podem escolher "Pedir" para receber cartas adicionais ou "Ficar" para parar de receber cartas.
   - O dealer então recebe cartas até que o valor de sua mão seja pelo menos 17.
   - O vencedor é determinado com base nos valores das mãos, com o objetivo de se aproximar o máximo possível de 21 sem ultrapassá-lo.

4. **Resultado do Jogo**
   - O jogo anuncia o resultado, seja uma vitória, bust, derrota ou empate.
   - Mensagens como "Você Ganhou!" ou "Você Perdeu!" são exibidas com base nos resultados.

## Estrutura do Código

- A lógica do jogo está implementada na classe `BlackJack`, que inclui o loop principal do jogo, a representação gráfica e as interações com os botões.
- As classes `Baralho`, `Carta`, `Dealer` e `Jogador` fornecem as estruturas de dados necessárias e funcionalidades para gerenciar o jogo.

## Dependências

- Este jogo utiliza o Java Swing para a interface gráfica do usuário.
- Certifique-se de ter as imagens necessárias nos diretórios `/cards` e `/img` para as imagens de cartas e plano de fundo.

## Como Executar

1. Compile os arquivos Java usando um compilador Java.
2. Execute a classe `Jogo` para iniciar o jogo.

Sinta-se à vontade para explorar e modificar o código para aprimorar o jogo ainda mais!
