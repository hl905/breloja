<img width="298" height="177" alt="vivaldi_eivvBsp6UT" src="https://github.com/user-attachments/assets/6045578c-5631-4ff3-9ede-0dec15f7bc23" />

A Breloja é um projeto totalmente feito em Java que simula um Marketplace online similar a um brechó, onde as pessoas revendem produtos usados no sistema.

O método de navegação e interface de usuário é feito baseado no input de números do usuário para navegar pelos menus, sendo essencial o usuário ler antes de colocar o número. O sistema também possuí uma variável (respostaValida) para evitar que o sistema nunca pare repentinamente de funcionar

CONCEITOS DE PROGRAMAÇÃO ORIENTADA A OBJETOS UTILIZADOS:
* Classes:
  Meu projeto possui 5 classes (não contando a classe Main), incluindo
  - Cadastro (Lida com o cadastro inicial);
  - Usuário (Lida com o cadastro secundário para venda de produtos);
  - Produto (Lida com os objetos de produtos);
  - Carrinho (Lida com o sistema de carrinho) e;
  - Sistema (Lida com todo o sistema principal e interface de usuário do programa)
* Criação de Objetos:
  Meu projeto usa vários objetos e arrays diferentes para funcionar, especialmente na parte de usuários e produtos. O programa guarda eles em ArrayLists para a facilidade da criação dos mesmos para quem está mexendo no código
* Encapsulamento
  Várias variáveis dentro do projeto são encapsuladas e somente acessada por métodos públicos, especialmente se forem características de um construtor.
* Polimorfismo
 <img width="301" height="317" alt="image" src="https://github.com/user-attachments/assets/9d0462ff-a9f3-43a1-a752-7c5dba8a54b9" />

  A classe Usuário herda de Cadastro várias variáveis e métodos, incluindo o exibirDados().
* Cadastro, listagem e consulta de dados
  Você pode se cadastrar, os produtos são listados e você pode consultar os dados
  - Dos usuários;
  - Dos produtos e;
  - Do seu carrinho.
* Organização do código
  O código tem, em sua boa parte, vários comentários explicando o que cada coisa no código faz para diminuir a confusão de quem decidir abrir o código fonte.

