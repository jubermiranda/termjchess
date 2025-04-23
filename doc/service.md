# Documentação do Pacote `service`

**Pacote:** `com.juber.termjchess.service`

## Visão Geral

O pacote `service` contém as classes responsáveis pela lógica de serviço e coordenação entre os diversos componentes do jogo de xadrez. Ele atua como a camada intermediária entre os modelos de dados (`model`) e os mecanismos de interação, como a interface de usuário.

Essas classes implementam as regras de negócio, controlam o fluxo do jogo e garantem a integridade do estado da aplicação.

---

### `ChessSpriteXProvider`

Responsável por fornecer diversos sprites para serem impressos, como char[][]. esses sprites estão em arquivos de texto em src/main/resource;

- **Métodos principais**:

- `char[][] EmptySprite(int rows, int cols)`
retorna um sprite 'vazio' de tamanho rows X cols, preenchidos com espaçoes (' ')

- `char[][] LabelSprite(char type, int n)`
carrega o sprite de label das laterais. o parametro type pode ser:
  'r' (label das linhas)
  'c' (label das colunas)
o parametro n indica qual linha ou coluna é o label

- demais sprites:
  sprites de cada peça (WPawnSprite -> sprite do peão branco), sprite da logo. 

---

### `GraphicsProvider`

Interface para representar a interface do jogo para o usuário.
expões métodos comuns, como iniciar a engine, desenhar um frame, etc.
Esta interface é usada na classe principal do jogo, que pode operar independente da interface usada.
inicialmente foi desenvolvido uma interface para terminal, mas se futuramente fosse desenvolvida uma interface gráfica por exemplo, a mesma poderia usar esta interface e facilmente ser adicionada à classe principal do jogo.

---

### `TerminalGraphicsX `

Uma interface que implementa GraphicsProvider. 
A mesma foi desenvolvida para ser uma interface de terminal, modo texto.
os elementos do jogo são desenhados usando caracteres.
Esta classe está ligada a classe Board, compartilhando o mesmo map (Map<String, BasePiece> piecesOnBoard).
esta abordagem pode ser melhorada posteriormente para mater o encapsulamento. entretanto foi escolhida atualmente para evitar repetidas funçoes entre essas classes, visto que TerminalGraphicsX precisa constantemente saber onde estão as peças para desenha-las.
a engine é startada passando esse map. A partir dai podemos chamar drawnNewFrame() que desenha um frame representando o estado autal das peças.

- **Detalhes**:
Possui uma forte relação com TermPrinter.

Possui um atributo char[][] representando o frame.
Quando a classe é criada, solicita a TermPrinter para desenhar o board inicial vazio.

- **A funcção drawnNewFrame**:
Um loop itera sobre todas as casas (a1 a h8) e verifica no map se existe uma peça nesta casa.
escolhe o sprite apropriado (verificando o tipo, ou se não existe, um  sprite vazio) e requisita a `ChessSpriteXProvider`.
Então redesenha cada casa com seu sprite apropriado.
esta parte pode ser otimizada, criando um outro frame 'cache' para monitorar se houve alguma mudança, e apenas redesenhar as casas que mudaram.

---

