# Pacote: model

## Descrição geral
Contém as entidades fundamentais do jogo de xadrez, como tabuleiros e peças. Essas classes são diretamente ligadas à representação do estado do jogo.

## Classes

### Classe: Board
- **Responsabilidade**: Representar o tabuleiro do jogo, manter as posições das peças e validar jogadas.
- **Atributos principais**:
  - `boardCells`: lista das casas do tabuleiro de xadrez (64 casas, 32 brancas e 32 pretas)
  - `pieces`: como o nome sugere, lista de peças (16 brancas e 16 pretas)
  - `piecesOnBoard`: map para agilizar algumas operações, mapeando o nome da casa "a1", "b2", etc, para qual peça está nesta casa 
  - `turn_0`: boolean para indicar qual o turno do jogo. 
    - `true`: turno das peças brancas jogar
    - `false`: turno das peças pretas jogar
  - `gameOver`: indica se o jogo acabou

- **Métodos principais**:
  - `move(String src, String dst, boolean perform)`: tenta executar um movimento de uma peça que esteja em src para dst.
  o parametro perform é usado para indicar se deseja efetuar o movimento. Dessa forma esta funcao pode ser usada para checar se um movimento é valido, passando perform como false.
  se nenhuma exceção for lançada, o movimento é valido. 
  Se perfor for true, então o movimento será efetuado, se for uma captura (peça inimiga em dst), a mesma será removida da posição, e a nova posição da peça em src é atualizada.
  Como esta é a unica função que altera o estado do jogo, um check é efetuado (verificando check-mate).
  - `whatsOnCell(String cell)`: Retorna um string com nome da peça na casa com nome `cell`

 [as funções abaixo são apenas para os testes. podem ser removidas posteriormente]
  - `getBoardSize()`: retorna a quantidade de casas do tabuleiro (espera-se que seja 64)
  - `getTotalPiecesAmount()`: retorna a quantidade de peças do tabuleiro (espera-se que seja 64)
  - `getWhitePiecesAmount()`: retorna a quantidade de peças brancas do tabuleiro (espera-se que seja 32)
- **Relações**: Interage com as classes em `piece` para posicionamento e movimentação. Também lança algumas exceções de `exception` 

### Classe: BasePiece
- **Responsabilidade**: A classe abstrata `BasePiece` serve como superclasse para todas as peças de xadrez no projeto `termjchess`. Seu objetivo é definir a interface comum e comportamentos básicos que qualquer peça do jogo deve implementar ou herdar, Permitindo assim que as peças concretas (como Rei, Rainha, Torre etc.) definam suas próprias regras de movimento enquanto compartilham um conjunto comum de operações.

---

## Atributos

- `protected BaseCell position`
  Representa a posição atual da peça no tabuleiro. Pode ser `null` se a peça foi capturada.

---

## Métodos Abstratos

- `boolean canMoveTo(BaseCell dst)`
  Verifica se a peça pode se mover para a célula de destino especificada.

- `void moveTo(BaseCell dst) throws IllegalChessMovementException`
  Move a peça para a célula de destino. Pode lançar exceção se o movimento for ilegal conforme as regras da peça.

- `boolean isW()`
  Retorna `true` se a peça for branca.

- `boolean isB()`
  Retorna `true` se a peça for preta.

- `String getName()`
  Retorna o nome da peça (por exemplo, "Black Queen", "White Rook").

- `ArrayList<String> getValidMoves()`
  Retorna uma lista de movimentos válidos para a peça a partir da posição atual.

- `ArrayList<String> getTrace(BaseCell dst)`
  Retorna o caminho (células intermediárias) até a célula de destino. Útil para verificar colisões em movimentos lineares.

---

## Métodos Concretos

- `boolean isSameType(BasePiece other)`
  Compara se a outra peça é do mesmo tipo (cor).

- `void kill()`
  Remove a peça do tabuleiro, definindo sua posição como null.

- `String getPositionName()`
  Retorna o nome da célula onde a peça está posicionada, ou uma string vazia se estiver "morta".


### Demais Classe

Uma outra classe abstrata para para cada peça. representando a interface daquela peça (por exemplo, se pode se mover para uma determinada casa (canMoveTo)).
E então, para cada peça temos duas classes concretas, uma para representar a peça branca e outra a peça preta. é nessa classe que funções mais específicas são implementadas, como isW(), ou getName().
Outro caso bem particular é o caso dos peões. diferente das outras peças que, tem as mesmas regras de movimento independente do tipo, os peões tem seus movimentos determinados pelo tipo da peça. 
