# TermJChess

**TermJChess** é uma implementação de xadrez jogável no terminal, desenvolvida em Java, com foco em aprendizado de **programação orientada a objetos**, organização modular e testes automatizados utilizando JUnit 5.

---

## Como compilar

Para compilar o projeto, utilize os scripts localizados no diretório `tools/`.

- **Compilar tudo (projeto e testes):**
  ```bash
  ./tools/compile_all.sh
  ```

- **Compilar apenas o projeto:**
  ```bash
  ./tools/compile.sh
  ```

- **Compilar apenas os testes:**
  ```bash
  ./tools/compile_tests.sh
  ```

---

## Como rodar

Após compilar, você pode executar o projeto e os testes com os seguintes comandos:

- **Executar o projeto:**
  ```bash
  ./tools/run.sh
  ```

- **Executar todos os testes:**
  ```bash
  ./tools/test_run.sh
  ```

- **Executar testes para uma classe específica:**
  ```bash
  ./tools/test_class.sh <relative_package_path>
  ```
  **Exemplo:**
  ```bash
  ./tools/test_class.sh model.board.Board
  ```

  Isso executará os testes da classe `BoardTest`, assumindo que seu nome completo seja `com.juber.termjchess.model.board.BoardTest`.

---

## Estrutura de pacotes

O projeto segue a convenção de pacotes Java, com o namespace principal sendo `com.juber.termjchess`, contendo subpacotes organizados por domínio como `model`, `service, entre outros.

---

## Dependências

- Java 17+
- [JUnit Platform Console Standalone 1.11.4]

O JAR do JUnit deve estar localizado em `lib/junit-platform-console-standalone-1.11.4.jar`.

---
