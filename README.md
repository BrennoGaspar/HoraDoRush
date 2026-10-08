# Hora do Rush 🛒⏳

Um jogo casual 2D focado na aplicação prática de Estruturas de Dados, simulando o gerenciamento de estado e a correria de um supermercado contra o relógio.

## ✨ Objetivo

O **Hora do Rush** foi criado para traduzir conceitos fundamentais de Ciência da Computação e arquitetura de software para um ambiente interativo. O jogador deve preencher seu carrinho de compras arrastando os produtos exatos exigidos por uma lista, lidando com tempo limite e ordenação correta.

## ⚙️ Como Executar

Por se tratar de um projeto Java Desktop construído com a engine JSGE, o projeto deve ser executado localmente:
1. Clone este repositório: `git clone https://github.com/seu-usuario/horadorush.git`
2. Abra a pasta do projeto na sua IDE de preferência (NetBeans, IntelliJ, Eclipse).
3. Certifique-se de que a biblioteca **JSGE** está configurada no `build path`.
4. Execute o arquivo principal: `Main.java`

## 🚀 Funcionalidades

* ➕ **Geração Dinâmica:** Produtos, posições na prateleira e listas de compras geradas de forma pseudoaleatória a cada nova partida.
* 🖱️ **Física Interativa:** Sistema robusto de *Drag and Drop* para movimentação de produtos.
* ⏱️ **Game Loop e Timer:** Relógio em tempo real que gerencia a condição de derrota.
* 🎮 **Máquina de Estados (State Machine):** Navegação fluida entre Menu, Jogo (Playing), Tela de Vitória e Tela de Derrota.
* 📊 **Três Níveis de Dificuldade:** Escalabilidade de tempo e complexidade de itens.

## 🧠 Estruturas de Dados & Arquitetura (Foco do Projeto)

O grande diferencial deste projeto é a forma como as mecânicas de jogo operam diretamente sobre estruturas de dados clássicas, simulando desafios reais do mercado de trabalho (como gerenciamento de estado e filas de processamento):

* 📚 **Pilhas (Stacks - LIFO):** 
  * **Onde:** `Carrinho.java`
  * **Como:** O carrinho de compras opera como uma Pilha. O último produto solto no carrinho é o primeiro (e único) que pode ser retirado caso o jogador cometa um erro.
  * **No mundo real:** Pilhas são a base da arquitetura da JVM (Method Stack), de sistemas de *Undo/Redo* e de algoritmos de busca em profundidade (DFS).
* 🚶‍♂️ **Filas (Queues - FIFO):** 
  * **Onde:** `ListaCompras.java`
  * **Como:** Utiliza uma `ArrayBlockingQueue` para determinar a sequência rigorosa de itens que o jogador deve coletar para validar a condição de vitória.
  * **No mundo real:** Filas formam a espinha dorsal de sistemas escaláveis no backend, como Apache Kafka ou RabbitMQ, processando requisições assíncronas e mensageria sem travar o servidor.
* ⚡ **Listas Indexadas (Arrays & ArrayLists):** 
  * **Onde:** `Produtos.java` e `HoraDoRush.java`
  * **Como:** Usadas para gerenciar a renderização dos sprites, evitar sobreposição de *hitboxes* (coordenadas ocupadas) e acessar rapidamente os dados na memória em tempo constante `O(1)`.

## 🖼️ Níveis de Dificuldade

O sistema escala o tamanho das estruturas de dados conforme o nível escolhido:

| Dificuldade | Tempo Máximo | Itens na Lista (Fila) | Produtos na Tela (Array) |
| :--- | :--- | :--- | :--- |
| **🟢 Fácil** | 5 Minutos | 5 Itens | 10 Produtos |
| **🟡 Médio** | 4 Minutos | 7 Itens | 15 Produtos |
| **🔴 Difícil** | 3 Minutos | 9 Itens | 20 Produtos |

## 🛠️ Tecnologias Utilizadas

* ☕ **Java** (Linguagem base e manipulação de coleções)
* 🎮 **JSGE** (Java Simple Game Engine - Renderização gráfica e captura de inputs)
* 📐 **Arquitetura MVC** (Separação clara entre Engine, Model, View e Input)
* 🎨 **Fontes e Tipografia Customizadas** (Integração com AWT/GraphicsEnvironment)

## 💡 Ideia do Projeto

O projeto foi desenvolvido com foco em:
* 🖥️ Sair da teoria e aplicar Estruturas de Dados em um contexto interativo.
* 🔄 Compreender o ciclo de vida de objetos em memória (evitando *memory leaks* com dados estáticos).
* 🕹️ Aprender o funcionamento interno de um Game Loop (`create`, `update`, `draw`).
* 📁 Desenvolver um projeto organizado, modular e com código limpo visando boas práticas de engenharia de software.

## 👨‍💻 Autores
*  Brenno Gaspar Pinto
*  Victor Altran Soares 
