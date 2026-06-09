import java.util.Scanner;

public class Sistema {
    public  static Cadastro mainC = new Cadastro("***", "000.000.000-00", "email@mail.com", "*******");
    public  static Usuário mainU = new Usuário(mainC, "***", "ES", "cidade", false);
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
    private static String np; // nome público
    private static String es; // estado
    private static String cd; // cidade
    private static boolean co; // contato disponível
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
    public static void exibirMeuPerfil(boolean set){
        if(set == true){
        mainU.meuPerfil = true; mainC.meuPerfil = true;
        }else{
        mainU.meuPerfil = false; mainC.meuPerfil = false;
        }
    }

    // ============================= Sessão de menus =============================
    // * * * Menu principal * * *
    public static void menuPrincipal(){
        Logo();
        if (login == true){
            System.out.println("\nSeja bem-vindo(a), "+mainC.getNome()+"!");
            resetarResposta();
            while(respostaValida == false){
            System.out.println("O que você deseja fazer?");
            System.out.println("1. Acessar produtos disponíveis");
            System.out.println("2. Adicionar um produto");
            System.out.println("3. Meu perfil");
            System.out.println("4. Sair");
            Divisão(0);
            resposta = sc.nextInt();
            switch(resposta){
            case 1: menuCompras(); respostaValida = true; break;
            case 2: adicionarProduto(); respostaValida = true; break;
            case 3:
            case 4: System.out.println("Fechando programa..."); System.exit(0);
            default: System.out.println("Resposta inválida."); break;
            }
            }
        }else{
            System.out.println("\nSeja bem-vindo(a) a breLoja!");
            while(respostaValida == false){
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
        mudarNome(n, false);
        mudarCPF(c, false);
        mudarEmail(e, false);
        mudarSenha(s, false);

        Divisão(1);
        mainC.exibirDados();
        Divisão(1);
        // Mudar cadastro antes de confirmar
        while(respostaValida == false){
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
    // * * * Meu Perfil * * *
    public static void meuPerfil(){
        exibirMeuPerfil(true);
        if(Usuário.cadastroCompleto == true){
            mainU.exibirDados();
        }else{
            mainC.exibirDados();
        }
        resetarResposta();
        while(respostaValida == false){
        System.out.println("Você gostaria de mudar alguma coisa?");
        System.out.println("1. Sim");
        System.out.println("2. Não");
        
        }
    }

    // !! Métodos para mudar cadastro !!
    public static void mudarCadastro(int resposta){
        if(!Usuário.cadastroCompleto){
        System.out.println("\nQual informação você deseja mudar?");
        System.out.println("1. Nome\n2. CPF\n3. E-mail\n4. Senha\n5. Mudei de ideia");
        Divisão(0);
        resposta = sc.nextInt();
        switch (resposta){
            case 1:  mudarNome(n, false); break;
            case 2:   mudarCPF(c, false); break;
            case 3: mudarEmail(e, false); break;
            case 4: mudarSenha(s, false); break;
            default: System.out.println("Resposta inválida.");
        } // fim do switch case
        }else{

        }
    }
    private static void mudarNome(String n, boolean cadastroCompleto){ // Nome
        do{ System.out.println("Digite seu nome:");
        sc.nextLine(); // Buffer
        n = sc.nextLine();
        }while(!mainC.setNome(n));
        mainC.setNome(n);
    }
    private static void mudarCPF(String c, boolean cadastroCompleto){ // CPF
        do{ System.out.println("Digite seu CPF (Somente números):");
        c = sc.nextLine();
        }while(!mainC.setCPF(c));
        mainC.setCPF(c);
    }
    private static void mudarEmail(String e, boolean cadastroCompleto){ // Email
        do{ System.out.println("Digite seu E-mail:");
        e = sc.nextLine();
        }while(!mainC.setEmail(e));
        mainC.setEmail(e);
    }
    private static void mudarSenha(String s, boolean cadastroCompleto){ // Senha
        do{ System.out.println("Digite sua senha (Ela deve ter 8 ou mais caractéres):");
        s = sc.nextLine();
        }while(!mainC.setSenha(s));
        mainC.setSenha(s);
    }
    private static void mudarNomePublico(String np){ // Nome público
        do{ System.out.println("Digite seu nome público.");
        sc.nextLine(); // Buffer
        np = sc.nextLine();
        }while(!mainU.setNomePublico(np));
    }
    private static void mudarEstado(String es){ // Mudar estado (e cidade)
        do{ System.out.println("Digite seu estado (Somente a sigla)");
        es = sc.nextLine();
        }while(!mainU.setEstado(es));
        mainU.setEstado(es);
        mudarCidade(cd); // Voce obrigatoriamente vai ter que mudar a cidade
    }
    private static void mudarCidade(String cd){ // Mudar cidade
        do{ System.out.println("Digite sua cidade.");
        cd = sc.nextLine();
        }while(!mainU.setCidade(cd));
        mainU.setCidade(cd);
    }
    private static void mudarContato(boolean co){ // Ativar ou desativar e-mail visível
        do{
        System.out.println("Você gostaria de deixar seu contato disponível para interessados no produto?");
        System.out.println("1. Sim\n2.Não");
        resposta = sc.nextInt();
        switch(resposta){
            case 1:
            co = true;
            respostaValida = true;
            break;
            case 2:
            co = false;
            respostaValida = true;
            break;
            default:
            System.out.println("Resposta inválida.");
        }
        }while(respostaValida == false);
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
        while(respostaValida == false){
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
            case 1: // Pede para você escolher o produto
            respostaValida = true;
            escolherProdutoVer();
            break;
            case 2: // Adiciona um produto no carrinho de uma vez
            respostaValida = true;
            escolherProdutoComprar();
            break;
            case 3: // Vê seu carrinho
            if (Carrinho.meuCarrinho.size() > 0){
                respostaValida = true;
                Carrinho.listarCarrinho();
                carrinhoOpções();
            }else{
                System.out.println("Seu carrinho está vazio no momento. Que tal adicionar alguma coisa nele?");
                Divisão(0);
            } 
            break;
            case 4: // Adiciona um produto a loja
            respostaValida = true;
            adicionarProduto();
            break;
            case 5:
            case 6: // Volta ao menu principal
            respostaValida = true;
            menuPrincipal();
            break;
        }
        }
        
    }
    // Coisas relacionadas a compra de produtos
    public static void escolherProdutoVer(){ // PRODUTOS DISPONÍVEIS = > 1. Checar um produto
        resetarResposta();   
        while(respostaValida == false){
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
        while(respostaValida == false){
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
        while(respostaValida == false){
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
    // ! ! ! Sistema de carrinho ! ! !
    public static void carrinhoUnidades(){ // O programa adiciona quantidade de unidades que o usuário quer adicionar no carrinho
        Produto produtoAtual = Produto.Produtos.get(indexP);
        int unidades;
        int unidadesAtuais = 0;
        if(produtoAtual.getUsuário() != mainU){ // Verifica se o usuário é o mesmo cadastrado
            if(Carrinho.meuCarrinho.size() != 0){ // IMPORTANTE: Vê se tem algo no carrinho
            for(int i = 0; i <= produtoAtual.getEstoque(); i++){ // Vê se o número de unidades já no carrinho foi esgotada, evita do usuário colocar várias unidades além do estoque
                if(Carrinho.meuCarrinho.get(i) == produtoAtual){unidadesAtuais++;}
            }
            }
            if(unidadesAtuais >= produtoAtual.getEstoque()){ // Estoque máximo
                System.out.println("Você já colocou o estoque máximo do produto no seu carrinho.");
                System.out.println("Você será redirecionado a loja novamente...");
                menuCompras();
                return;}
            System.out.println("Quantas unidades deste item você quer? (Digite 0 se você não quer mais comprar este item.)");
            unidades = sc.nextInt();
            if(unidades > produtoAtual.getEstoque()){ // Unidades > estoque
                System.out.println("Você está tentando comprar mais do que pode.");
            }else if(unidades <= 0){ // Sair
                System.out.println("Você será redirecionado a loja novamente...");
                menuCompras();
                return;
            }else{
                for(int i = 0; i < unidades; i++){Carrinho.adicionarProduto(produtoAtual);} // Index 0: 1 item somente
                System.out.println(unidades+" unidades foram adicionadas ao seu carrinho.");
                menuCompras();
            }
        }else{
            System.out.println("Você não pode comprar itens de si mesmo!");
            Divisão(1);
            menuCompras();
        }

    }
    public static void carrinhoOpções(){ // PRODUTOS DISPONÍVEL => 3. Ver meu carrinho
        resetarResposta();
        while(respostaValida == false){
            System.out.println("O que você deseja fazer no seu carrinho?");
            System.out.println("1. Finalizar compra");
            System.out.println("2. Remover um produto");
            System.out.println("3. Esvaziar tudo");
            System.out.println("4. Continuar comprando");
            resposta = sc.nextInt();
            switch(resposta){
            case 1: // Compra tudo e te redireciona pra loja
            respostaValida = true;
            Carrinho.comprarTudo();
            Divisão(1);
            menuCompras();
            break;
            case 2: // Escolher remover um produto
            if(Carrinho.meuCarrinho.size() > 0){            
            respostaValida = true;
            carrinhoRemover();
            }else{
            System.out.println("Seu carrinho está vazio.");
            }
            break;
            case 3: // Esvazia tudo
            respostaValida = true;
            Carrinho.Esvaziar();
            System.out.println("Carrinho totalmente esvaziado.");
            break;
            case 4: // Volta ao menu de compras
            respostaValida = true;
            menuCompras();
            break;
            } // fim do switch case
        }
    }
    public static void carrinhoRemover(){
        resetarResposta();
        while(respostaValida == false){
        System.out.println("Diga qual produto você gostaria de remover do carrinho. (Digite 0 se você quiser voltar ao carrinho sem remover nada.)");
        resposta = sc.nextInt();
        int index = resposta - 1; // Posição 1 = index 0 na lista meuCarrinho
        if(resposta != 0 && index < Carrinho.meuCarrinho.size()){ // Diferente de 0 & do tamanho adequado do carrinho
            Carrinho.removerProduto(Carrinho.meuCarrinho.get(index));
            System.out.println("O item foi removido com sucesso.");
            respostaValida = true;
        }else if(resposta == 0){
            System.out.println("Voltando ao seu carrinho...");
            break;
        }else{System.out.println("Resposta inválida. Verifique a lista dentro do carrinho novamente para ver a posição correta.");}
        }
        Divisão(0);
        Carrinho.listarCarrinho();
        carrinhoOpções();
    }
    
    // Adicionar produtos a loja + terminar cadastro
    public static void adicionarProduto(){
        if(!Usuário.cadastroCompleto){ // Te redireciona a região para concluir cadastro
            completarCadastroPergunta();
        }else{ // Criar produto
            String item;
            double preço;
            int estoque;
            String descrição;

            System.out.println("Qual vai ser o nome do seu produto?");
            sc.nextLine(); // buffer
            item = sc.nextLine();
            System.out.println("Qual vai ser o preço?");
            preço = sc.nextDouble();
            System.out.println("Você quer quanto desse item em estoque?");
            estoque = sc.nextInt();
            System.out.println("Dê uma descrição ao seu item.");
            sc.nextLine(); // buffer
            descrição = sc.nextLine();

            System.out.println("Seu item será adicionado a loja.");
            Produto.Produtos.add(new Produto(item, preço, estoque, descrição, mainU));
            Divisão(1);
            menuCompras();
        }
    }
    public static void completarCadastroPergunta(){
        resetarResposta();
        while(respostaValida == false){
            System.out.println("Para ter essa opção, você deve adicionar mais informações a sua conta. Você gostaria de completar seu cadastro?");
            System.out.println("1. Sim");
            System.out.println("2. Não");
            resposta = sc.nextInt();
            switch(resposta){
            case 1:
            completarCadastro();
            break;
            case 2:
            System.out.println("Você será redirecionado a loja novamente...");
            menuCompras();
            break;
            default: System.out.println("Respota inválida.");
            }
        }
    }
    public static void completarCadastro(){
        mudarNomePublico(np);
        mudarEstado(es);
        mudarContato(co);

        System.out.println("Seu cadastro agora está completo! Agora você pode vender itens na loja normalmente!");
        Usuário.cadastroCompleto = true;
        Usuário.Usuários.add(mainU);
        Divisão(1);
        menuCompras();
    }

} // Fim da classe Sistema
