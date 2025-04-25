# Documentação: Arquitetura do Jogo

Este documento descreve os principais componentes do jogo TermJChess, destacando suas responsabilidades e interações.

---

## 🎮 `TermJChessGame.java`

**Pacote:** `com.juber.termjchess.service`

### Descrição

Classe principal responsável pela inicialização e controle do fluxo do jogo. Ela gerencia a configuração inicial, o loop principal do jogo e a interação com o usuário por meio da interface escolhida por parâmetro na inicialização.


### Responsabilidades

- Inicializar o tabuleiro.
- Inicializar a interface escolhida
- Controlar o loop principal do jogo, alternando entre os turnos (peças brancas e peças pretas) e verificando condições de término.
- Delegar a renderização da interface para a implementação de `GraphicsProvider`.

---

## 🖼️ `GraphicsProvider.java`

**Pacote:** `com.juber.termjchess.service`

### Descrição

Interface que define os métodos necessários para a renderização da interface do jogo. Ela permite a abstração da camada gráfica, possibilitando diferentes implementações (ex: terminal, GUI).

### Métodos Principais

- `void startEngine(Map<String, BasePiece> pieces)` 
  Inicia o processo de renderização do jogo, recebendo um map que informa as posições das peças.

- `void drawnNewFrame()`
  Desenha um novo 'frame' que representa o estado 'autal' do jogo.

- `String getUserInput();`
  Captura a entrada do usuário.

### Propósito

Facilitar a substituição ou adição de novas interfaces gráficas sem alterar a lógica principal do jogo.

---

## 🖥️ `TerminalGraphicsX.java`

**Pacote:** `com.juber.termjchess.graphics.terminal`

### Descrição

Implementação da interface `GraphicsProvider` que utiliza o terminal para interagir com o usuário. Ela é responsável por exibir o tabuleiro, mensagens e capturar entradas via terminal.

### Características

- Utiliza caracteres ASCII para representar o tabuleiro e as peças.
- Captura entradas do usuário por meio do console.
- Exibe mensagens e atualizações diretamente no terminal.

### Estrutura Simplificada

```java
public class TerminalGraphicsX implements GraphicsProvider {
    @Override
    public void renderBoard(Board board) {
        // Lógica para exibir o tabuleiro no terminal
    }

    @Override
    public void displayMessage(String message) {
        System.out.println(message);
    }

    @Override
    public String getUserInput() {
        // Lógica para capturar entrada do usuário
    }
}
```

---

## 🔄 Relações Entre Componentes

- `TermJChessGame` depende de uma implementação de `GraphicsProvider` para interagir com o usuário.
- `TerminalGraphicsX` é uma implementação concreta de `GraphicsProvider` que utiliza o terminal como meio de interação.

---

## 🔧 Possíveis Extensões Futuras

- Implementação de uma interface gráfica utilizando bibliotecas como JavaFX ou Swing.
- Criação de uma interface web para permitir jogos online.
- Suporte a diferentes idiomas na interface.
