
# TermJChess

**TermJChess** é uma implementação de xadrez jogável no terminal, desenvolvida em Java. O projeto tem como foco o aprendizado de **programação orientada a objetos**, organização modular e a prática de **testes automatizados** com JUnit 5.

---

## 🔧 Como compilar

Scripts para compilação estão disponíveis no diretório `tools/`, separados por sistema operacional:

### Linux

- **Compilar tudo (projeto e testes):**
  ```bash
  ./tools/linux/compile_all.sh
  ```

- **Compilar apenas o projeto:**
  ```bash
  ./tools/linux/compile.sh
  ```

- **Compilar apenas os testes:**
  ```bash
  ./tools/linux/compile_tests.sh
  ```

### Windows

- **Compilar tudo (projeto e testes):**
  ```bat
  tools\windows\compile_all.bat
  ```

- **Compilar apenas o projeto:**
  ```bat
  tools\windows\compile.bat
  ```

- **Compilar apenas os testes:**
  ```bat
  tools\windows\compile_tests.bat
  ```

---

## ▶️  Como rodar

Após compilar, utilize os seguintes scripts para executar o projeto e os testes.

### Linux

- **Executar o projeto:**
  ```bash
  ./tools/linux/run.sh
  ```

- **Executar todos os testes:**
  ```bash
  ./tools/linux/test_run.sh
  ```

- **Executar testes de uma classe específica:**
  ```bash
  ./tools/linux/test_class.sh <relative path name>
  ```
  onde 'relative path name' é o caminho relativo a com.juber.termjchess
  exemplo: para rodar os testes para a classe Board que está em com.juber.termjchess.model.board.Board
  ```bash
  ./tools/linux/test_class.sh model.board.Board
  ```
> Isso executará os testes da classe `BoardTest`, assumindo que seu nome completo seja `com.juber.termjchess.model.board.BoardTest`.



### Windows

- **Executar o projeto:**
  ```bat
  tools\windows\run.bat
  ```

- **Executar todos os testes:**
  ```bat
  tools\windows\test_run.bat
  ```

- **Executar testes de uma classe específica:**
  ```bat
  tools\windows\test_class.bat <relative path name>
  ```
  onde 'relative path name' é o caminho relativo a com.juber.termjchess
  exemplo: para rodar os testes para a classe Board que está em com.juber.termjchess.model.board.Board
  ```bash
  ./tools/linux/test_class.sh model.board.Board
  ```
> Isso executará os testes da classe `BoardTest`, assumindo que seu nome completo seja `com.juber.termjchess.model.board.BoardTest`.

---

## 📦 Dependências

- Java 17+
- [JUnit Platform Console Standalone 1.11.4]

O arquivo `.jar` do JUnit deve estar localizado em:

```
lib/junit-platform-console-standalone-1.11.4.jar
```

---

## 🖥️ Recomendações de terminal

O tabuleiro desenhado com caracteres ocupa bastante espaço. Para uma boa experiência visual, recomenda-se **diminuir o tamanho da fonte do terminal**. O tamanho **5** apresentou bons resultados durante os testes.

---

## 📃 Licença

Distribuído sob a [Licença MIT](LICENSE).
--
