import java.util.Scanner;

public class Sistema {
    public  static Cadastro mainU = new Cadastro("***", "000.000.000-00", "email@mail.com", "*******");
    public  static Scanner sc = new Scanner(System.in);
    public  static boolean login = false;
    // Variáveis de resposta
    private static int resposta = 0;
    private static boolean respostaValida = false;
    // Variáveis para colocar informação
    private static String n; // nome
    private static String c; // cpf
    private static String e; // email
    private static String s; // senha
    // Coisas dentro da loja
    private static int indexP;


    public static void Inicializar(){
        Usuário.criarUsuários();
        Produto.criarLoja();
        menuPrincipal();
    }
    public static void Logo(){
    System.out.println(" _              _           _       ");
    System.out.println("| |            | |         (_)      ");
    System.out.println("| |__  _ __ ___| |     ___  _  __ _ ");
    System.out.println("| '_ \\| '__/ _ \\ |    / _ \\| |/ _` |");
    System.out.println("| |_) | | |  __/ |___| (_) | | (_| |");
    System.out.println("|_.__/|_|  \\___|______\\___/| |\\__,_|");
    System.out.println("                          _/ |      ");
    System.out.println("                         |__/     ");
    }
    public static void Divisão(int tamanho){ // Divide sessões do sistema quando for necessário
        if (tamanho == 0){
            System.out.println("-----");
        }
        if (tamanho == 1){
            System.out.println("=================================");
        }
    }
    public static void resetarResposta(){ // Reseta resposta
        sc.nextLine();
        resposta = 0;
        respostaValida = false;
    }

    // ============================= Sessão de menus =============================
    // * * * Menu principal * * *
    public static void menuPrincipal(){
        Logo();
        if (login == true){
            System.out.println("\nSeja bem-vindo(a), "+mainU.getNome()+"!");
            while(!respostaValida){
            System.out.println("O que você deseja fazer?");
            System.out.println("1. Acessar produtos disponíveis");
            System.out.println("2. Adicionar um produto");
            System.out.println("3. Meu perfil");
            System.out.println("4. Sair");
            Divisão(0);
            resposta = sc.nextInt();
            switch(resposta){
            case 1: menuCompras(); respostaValida = true; break;
            case 2:
            case 3:
            case 4: System.out.println("Fechando programa..."); System.exit(0);
            default: System.out.println("Resposta inválida."); break;
            }
            }
        }else{
            System.out.println("\nSeja bem-vindo(a) a breLoja!");
            while(!respostaValida){
            System.out.println("O que você deseja fazer?");
            System.out.println("1. Criar conta");
            System.out.println("2. Fazer login");
            System.out.println("3. Sair");
            Divisão(0);
            resposta = sc.nextInt();
            switch(resposta){
            case 1: fazerCadastro(); respostaValida = true; break;
            case 3: System.out.println("Fechando programa..."); System.exit(0);
            default: System.out.println("Resposta inválida."); break;
            }
            } // fim do while
        }
    }
    // * * * Menu de cadastro * * *
    public static void fazerCadastro(){
        // Cadastro inicial
        mudarNome(n);
        mudarCPF(c);
        mudarEmail(e);
        mudarSenha(s);

        Divisão(1);
        mainU.exibirDados();
        Divisão(1);
        // Mudar cadastro antes de confirmar
        while(!respostaValida){
        System.out.println("Você deseja mudar alguma informação?");
        System.out.println("1. Sim");
        System.out.println("2. Não");
        resposta = sc.nextInt();
        switch (resposta){
            case 1:
                resposta = 0;
                mudarCadastro(resposta);
                break;
            case 2: System.out.println("Cadastro concluído com sucesso!"); respostaValida = true; break;
            default: System.out.println("Resposta inválida."); break;
        }
        } // Fim do while
        login = true;
        resetarResposta();
        Divisão(1);
        menuPrincipal();
    }
    // !! Métodos para mudar cadastro !!
    public static void mudarCadastro(int resposta){
        if(!cadastroCompleto){
        System.out.println("\nQual informação você deseja mudar?");
        System.out.println("1. Nome\n2. CPF\n3. E-mail\n4. Senha\n5. Mudei de ideia");
        Divisão(0);
        resposta = sc.nextInt();
        switch (resposta){
            case 1:  mudarNome(n); break;
            case 2:   mudarCPF(c); break;
            case 3: mudarEmail(e); break;
            case 4: mudarSenha(s); break;
            default: System.out.println("Resposta inválida.");
        } // fim do switch case
        }else{

        }
    }
    private static void mudarNome(String n){ // Nome
        do{ System.out.println("Digite seu nome:");
        sc.nextLine(); // Buffer
        n = sc.nextLine();
        }while(!mainU.setNome(n));
        mainU.setNome(n);
    }
    private static void mudarCPF(String c){ // CPF
        do{ System.out.println("Digite seu CPF (Somente números):");
        c = sc.nextLine();
        }while(!mainU.setCPF(c));
        mainU.setCPF(c);
    }
    private static void mudarEmail(String e){ // Email
        do{ System.out.println("Digite seu E-mail:");
        e = sc.nextLine();
        }while(!mainU.setEmail(e));
        mainU.setEmail(e);
    }
    private static void mudarSenha(String s){ // Senha
        do{ System.out.println("Digite sua senha (Ela deve ter 8 ou mais caractéres):");
        s = sc.nextLine();
        }while(!mainU.setSenha(s));
        mainU.setSenha(s);
    }
    private static void mudarNomePublico(String np){ // Nome público

    }
    private static void mudarEstado(String es){ // Mudar estado (e cidade)

    }
    private static void mudarCidade(String cd){ // Mudar cidade

    }
    private static void mudarContato(boolean co){ // Ativar ou desativar e-mail visível

    }



    // * * * Menu de compras * * *
    public static void menuCompras(){
        resetarResposta();
        mostrarProdutos();
    }
    public static void mostrarProdutos(){
        System.out.println("===== PRODUTOS DISPONÍVEIS =====");
        Produto.listarProdutos();
        Divisão(1);
        while(!respostaValida){
        System.out.println("O que você gostaria de fazer?");
        System.out.println("1. Checar um produto");
        System.out.println("2. Adicionar ao carrinho");
        System.out.println("3. Ver meu carrinho");
        System.out.println("4. Adicionar produto a loja");
        System.out.println("5. Ver meus itens");
        System.out.println("6. Voltar");
        Divisão(0);
        resposta = sc.nextInt();
        switch(resposta){
            case 1:
            respostaValida = true;
            escolherProdutoVer();
            break;
            case 2:
            respostaValida = true;
            escolherProdutoComprar();
            break;
            case 3:
            respostaValida = true;
            Carrinho.listarCarrinho();
            break;
            case 4:
            case 5:
            case 6:
            respostaValida = true;
            menuPrincipal();
            break;
        }
        resetarResposta();
        }
    }
    // Coisas relacionadas a compra de produtos
    public static void escolherProdutoVer(){ // PRODUTOS DISPONÍVEIS = > 1. Checar um produto
        resetarResposta();   
        while(!respostaValida){
            System.out.println("Escolha qual produto da lista você gostaria de checar. (Somente número)");
            resposta = sc.nextInt();
            resposta = resposta-1; indexP = resposta;
            if (resposta >= 0 && resposta <= Produto.Produtos.size()){
            Produto.Produtos.get(resposta).exibirProduto();
            Divisão(0);
            itemSelecionado(resposta);
            return;
            }else{
            System.out.println("Sua resposta não consta como número da lista.");
            }
        }
        Divisão(1);
        resetarResposta();
    }
    public static void escolherProdutoComprar(){
        resetarResposta();
        while(!respostaValida){
            System.out.println("Escolha qual produto da lista você gostaria de checar. (Somente número)");
            resposta = sc.nextInt();
            resposta = resposta-1; indexP = resposta;
            if (resposta >= 0 && resposta <= Produto.Produtos.size()){
            carrinhoUnidades();
            respostaValida = true;
            }else{
            System.out.println("Sua resposta não consta como número da lista.");
            }
        Divisão(1);
        resetarResposta();
        }
    }
    public static void itemSelecionado(int indexP){ // Item que o usuário decidiu selecionar em escolherProduto()
        Produto produtoAtual = Produto.Produtos.get(indexP);
        Usuário usuárioProduto = produtoAtual.getUsuário();
        while(!respostaValida){
            System.out.println("O que deseja fazer com o item atual?");
            System.out.println("1. Adicionar ao carrinho");
            System.out.println("2. Checar vendedor");
            System.out.println("3. Voltar");
            Divisão(0);
            resposta = sc.nextInt();
            switch(resposta){
            case 1: // 1. Adicionar ao carrinho
            respostaValida = true;
            carrinhoUnidades();
            break;
            case 2: // 2. Checar vendedor
            mainU.voceMesmo(false);
            usuárioProduto.exibirDados();
            Divisão(1);
            break;
            case 3: // 3. Voltar
            respostaValida = true;
            Divisão(1);
            menuCompras();
            break;
            }
        }
    }
    // nota para depois: impedir de voce comprar um produto que você mesmo criou
    public static void carrinhoUnidades(){ // A quantidade de unidades que o usuário quer adicionar no carrinho
        Produto produtoAtual = Produto.Produtos.get(indexP);
        int unidades;
        int unidadesAtuais = 0;
        if(Carrinho.meuCarrinho.size() != 0){ // IMPORTANTE: Vê se tem algo no carrinho
        for(int i = 0; i <= produtoAtual.getEstoque(); i++){ // Vê se o número de unidades já no carrinho foi esgotada, evita do usuário colocar várias unidades além do estoque
            if(Carrinho.meuCarrinho.get(i) == produtoAtual){unidadesAtuais++;}
        }
        }
        if(unidadesAtuais >= produtoAtual.getEstoque()){
            System.out.println("Você já colocou o estoque máximo do produto no seu carrinho.");
            System.out.println("Você será redirecionado a loja novamente...");
            menuCompras();
            return;}
        System.out.println("Quantas unidades deste item você quer? (Digite 0 se você não quer mais comprar este item.)");
        unidades = sc.nextInt();
        if(unidades > produtoAtual.getEstoque()){
            System.out.println("Você está tentando comprar mais do que pode.");
        }else if(unidades <= 0){
            System.out.println("Você será redirecionado a loja novamente...");
            menuCompras();
            return;
        }else{
            for(int i = 0; i <= unidades; i++){Carrinho.adicionarProduto(produtoAtual);}
            System.out.println(unidades+" unidades foram adicionadas ao seu carrinho.");
            Produto.Produtos.get(indexP).setEstoque(Produto.Produtos.get(indexP).getEstoque()-unidades); // Muda o estoque permanentemente
            menuCompras();
        }
    }
    
    // Adicionar produtos a loja + terminar cadastro
    public static void adicionarProduto(){
        if(!Usuário.cadastroCompleto){
            completarCadastroPergunta();
        }else{

        }
    }
    public static void completarCadastroPergunta(){
        resetarResposta();
        while(!respostaValida){
            System.out.println("Para ter essa opção, você deve adicionar mais informações a sua conta. Você gostaria de completar seu cadastro?");
            System.out.println("1. Sim");
            System.out.println("2. Não");
            resposta = sc.nextInt();
            switch(resposta){
            case 1:
            case 2:
            System.out.println("Você será redirecionado a loja novamente...");
            menuCompras();
            break;
            default: System.out.println("Respota inválida.");
            }
        }
    }
    public static void completarCadastro(){
        Usuário MainU = new Usuário(mainU, "", "", "", false);
        
    }
} // Fim da classe Sistema
