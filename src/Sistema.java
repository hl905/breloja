import java.util.Scanner;


public class Sistema {
    public  static Cadastro mainC = new Cadastro(null, null, null, null);
    public  static Usuário mainU = new Usuário(mainC, null, null, null, false);
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
        Usuário.cadastroCompleto = false;
        Usuário.criarUsuários();
        Produto.criarLoja();
        menuPrincipal();
    }
    private static void Logo(){
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
    private static void resetarResposta(){ // Reseta resposta
        sc.nextLine();
        resposta = 0;
        respostaValida = false;
    }
    private static void exibirMeuPerfil(boolean set){
        if(set == true){
        mainU.meuPerfil = true; mainC.meuPerfil = true;
        }else{
        mainU.meuPerfil = false; mainC.meuPerfil = false;
        }
    }

    // ============================= Sessão de menus =============================
    // * * * Menu principal * * *
    private static void menuPrincipal(){
        Logo();
        if (login == true){
            System.out.println(Usuário.cadastroCompleto);
            if(Usuário.cadastroCompleto == false){
                System.out.println("\nSeja bem-vindo(a), "+mainC.getNome()+"!");
            }else{
                System.out.println("\nSeja bem-vindo(a), "+mainU.getNomePublico()+"!");
                Usuário.cadastroCompleto = true;
            }
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
            case 3: meuPerfil(); respostaValida = true; break;
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
            case 2: fazerLogin(); respostaValida = true; break;
            case 3: System.out.println("Fechando programa..."); System.exit(0);
            default: System.out.println("Resposta inválida."); break;
            }
            } // fim do while
        }
    }
    // * * * Menu de cadastro * * *
    private static void fazerCadastro(){
        // Cadastro inicial
        mudarNome(n, false);
        mudarCPF(c, false);
        mudarEmail(e, false);
        mudarSenha(s, false);

        // Mudar cadastro antes de confirmar
        while(respostaValida == false){
        Divisão(1);
        mainC.exibirDados();
        Divisão(1);

        System.out.println("Você deseja mudar alguma informação?");
        System.out.println("1. Sim");
        System.out.println("2. Não");
        resposta = sc.nextInt();
        switch (resposta){
            case 1:
                mudarCadastro();
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
    private static void meuPerfil(){
        exibirMeuPerfil(true);
        if(Usuário.cadastroCompleto == true){
            mainU.exibirDados();
        }else{
            mainC.exibirDados();
        }

        resetarResposta();
        System.out.println("Você gostaria de mudar alguma coisa?");
        while(respostaValida == false){
        System.out.println("1. Sim\n2. Não");
        resposta = sc.nextInt();
        switch(resposta){
            case 1: // Não tem respostaValida = true aqui para caso o usuário queira mudar mais coisas e para o programa não terminar repentinamente
                mudarCadastro();
                System.out.println("Você gostaria de mudar mais alguma coisa?"); // Caso o usuário queira mudar mais alguma coisa
                break;
            case 2:
                System.out.println("Você será redirecionado ao menu principal novamente...");
                menuPrincipal();
                respostaValida = true;
                break;
            default: System.out.println("Resposta inválida.");
        }
        }
    }

    // !! Métodos para mudar cadastro !!
    private static void mudarCadastro(){
        if(!Usuário.cadastroCompleto){ // cadastroCompleto = false
            System.out.println("\nQual informação você deseja mudar?");
            System.out.println("1. Nome\n2. CPF\n3. E-mail\n4. Senha\n5. Mudei de ideia");
            Divisão(0);
            resposta = sc.nextInt();
            switch (resposta){ 
                case 1:  mudarNome(n, false); break;
                case 2:   mudarCPF(c, false); break;
                case 3: mudarEmail(e, false); break;
                case 4: mudarSenha(s, false); break;
                case 5:
                System.out.println("Você será redirecionado ao menu principal...");
                menuPrincipal(); break;
                default: System.out.println("Resposta inválida.");
            }
        }else{ // cadastroCompleto = true
            System.out.println("\nQual informação você deseja mudar?");
            System.out.println("1. Nome\n2. CPF\n3. E-mail\n4. Senha\n5. Nome Público\n6. Estado\n7. Cidade\n8. Opção de contato\n9. Mudei de ideia");
            Divisão(0);
            resposta = sc.nextInt();
            switch (resposta){
                case 1:  
                    mudarNome(n, true); 
                    System.out.println("Nome alterado com sucesso.");
                break;
                case 2:   
                    mudarCPF(c, true); 
                    System.out.println("CPF alterado com sucesso.");
                    break;
                case 3: 
                    mudarEmail(e, true); 
                    System.out.println("E-mail alterado com sucesso.");
                    break;
                case 4: 
                    mudarSenha(s, true);
                    System.out.println("Senha alterada com sucesso.");
                    break;
                case 5: 
                    mudarNomePublico(np);
                    System.out.println("O seu nome público agora é "+mainU.getNomePublico()+".");
                    break;
                case 6:
                    System.out.println("Lembre-se: Ao mudar seu estado, você terá de obrigatoriamente mudar sua cidade.");
                    mudarEstado(es); 
                    System.out.println("Estado e cidade alterados com sucesso.");
                    break;
                case 7: 
                    mudarCidade(cd);
                    System.out.println("Cidade alterada com sucesso.");
                    break;
                case 8: 
                    mudarContato(co);
                    if(mainU.getContato() == true){
                        System.out.println("Agora usuários podem te contatar diretamente pelo seu E-mail.");
                    }else{
                        System.out.println("Usuários não podem te contatar mais pelo seu E-mail.");
                    }
                    break;
                case 9:
                System.out.println("Você será redirecionado ao menu principal...");
                menuPrincipal(); break;
                default: System.out.println("Resposta inválida.");
            }
        }
    }
    private static void mudarNome(String n, boolean cadastroCompleto){ // Nome
        if(cadastroCompleto == false){
            do{ // MainC
            System.out.println("Digite seu nome:");
            sc.nextLine(); // Buffer
            n = sc.nextLine();
            }while(!mainC.setNome(n));
            mainC.setNome(n);
        }else{ // MainU
            do{
            System.out.println("Digite seu nome:");
            sc.nextLine(); // Buffer
            n = sc.nextLine();
            }while(!mainU.setNome(n));
            mainU.setNome(n);
        }
        
    }
    // Pré cadastro inicial
    private static void mudarCPF(String c, boolean cadastroCompleto){ // CPF
        if(cadastroCompleto == false){ // MainC
            do{ System.out.println("Digite seu CPF (Somente números):");
            c = sc.nextLine();
            }while(!mainC.setCPF(c));
            mainC.setCPF(c);
        }else{ // MainU
            do{ System.out.println("Digite seu CPF (Somente números):");
            c = sc.nextLine();
            }while(!mainU.setCPF(c));
            mainU.setCPF(c);
        }
    }
    private static void mudarEmail(String e, boolean cadastroCompleto){ // Email
        if(cadastroCompleto == false){ // MainC
            do{ System.out.println("Digite seu E-mail:");
            e = sc.nextLine();
            }while(!mainC.setEmail(e));
            mainC.setEmail(e);
        }else{ // MainU
            do{ System.out.println("Digite seu CPF (Somente números):");
            c = sc.nextLine();
            }while(!mainU.setCPF(c));
            mainU.setCPF(c);
        }
    }
    private static void mudarSenha(String s, boolean cadastroCompleto){ // Senha
        if(cadastroCompleto == false){ // MainC
            do{ System.out.println("Digite sua senha (Ela deve ter 8 ou mais caractéres):");
            s = sc.nextLine();
            }while(!mainC.setSenha(s));
            mainC.setSenha(s);
        }else{ // MainU
            do{ System.out.println("Digite sua senha (Ela deve ter 8 ou mais caractéres):");
            s = sc.nextLine();
            }while(!mainC.setSenha(s));
            mainC.setSenha(s);
        }
    }
    // Pós cadastro inicial
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
        System.out.println("1. Sim\n2. Não");
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
    // * * * Fazer Login * * *
    private static void fazerLogin(){
        // Booleanas pros while
        boolean loginConfirmado = false; // While principal
        // Login e senha pedidos pelo sistema
        String eLogin;
        String sLogin;
        // Objetos de usuário e cadastro
        Usuário  usuárioLogin  = null;
        Cadastro cadastroLogin = null;

        resetarResposta();
        while(loginConfirmado == false){
        boolean  usuárioEncontrado  = false;
        boolean  cadastroEncontrado = false;

        // Passo 1: Pede as cretenciais.
        System.out.println("Digite seu E-mail.");
        eLogin = sc.nextLine();
        System.out.println("Digite sua senha.");
        sLogin = sc.nextLine();
        // Passo 2: Procura na lista de usuários de o usuário é um usuário (ou cadastro pendente) válido.
        for(int i = 0; i < Usuário.Usuários.size(); i++){ // Usuário
            Usuário usuárioAtual = Usuário.Usuários.get(i);
            if(usuárioAtual.getEmail().equals(eLogin) && usuárioAtual.getSenha().equals(sLogin)){
                usuárioLogin = usuárioAtual;
                usuárioEncontrado = true;
                break;
            }else continue;
        }
        for(int i = 0; i < Usuário.Cadastrados.size(); i++){ // Cadastro
            Cadastro cadastroAtual = Usuário.Cadastrados.get(i);
            if(cadastroAtual.getEmail().equals(eLogin) && cadastroAtual.getSenha().equals(sLogin)){
                cadastroLogin = cadastroAtual;
                cadastroEncontrado = true;
                break;
            }else continue;
        }
        // Passo 3: Confirma se você quer logar com este usuário, e pergunta se você gostaria de terminar o cadastro, caso você não tenha terminado tudo.
        if(usuárioEncontrado == true){ // Usuário
            resetarResposta();
            while(respostaValida == false){
                System.out.println("Você irá fazer login como o usuário "+ usuárioLogin.getNomePublico() +", você confirma que este é você?");
                System.out.println("1. Sim\n2. Não");
                resposta = sc.nextInt();
                switch(resposta){
                    case 1: // loginConfirmado = true
                        loginUsuário(usuárioLogin); // mainU = usuárioLogin
                        System.out.println("Você fez login com sucesso!");
                        Usuário.cadastroCompleto = true;
                        loginConfirmado = true; // Variável local
                        login = true; // Redireciona para a tela de logado
                        Divisão(1);
                        menuPrincipal();
                        respostaValida = true;
                        break;
                    case 2:
                        System.out.println("Por favor, coloque as cretenciais corretamente.");
                        usuárioEncontrado = false;
                        respostaValida = true;
                        break;
                    default: 
                        System.out.println("Resposta inválida.");
                        break;
                }
            }
        }else if(cadastroEncontrado == true){ // Cadastro
            resetarResposta();
            while(respostaValida == false){
                System.out.println("Você irá fazer login como o usuário "+ censurarNome(cadastroLogin.getNome()) +", você confirma que este é você?");
                System.out.println("(Estamos censurando o nome por questão de segurança.)");
                System.out.println("1. Sim\n2. Não");
                resposta = sc.nextInt();
                switch(resposta){
                    case 1: // loginConfirmado = true
                        loginCadastro(cadastroLogin); // mainC = cadastroLogin
                        System.out.println("Você fez login com sucesso!");
                        loginConfirmado = true; // Variável local
                        login = true; // Redireciona para a tela de logado
                        Divisão(0);
                        loginConfirmarCadastro();
                        respostaValida = true;
                        break;
                    case 2:
                        System.out.println("Por favor, coloque as cretenciais corretamente.");
                        cadastroEncontrado = false;
                        respostaValida = true;
                        break;
                    default: 
                        System.out.println("Resposta inválida.");
                        break;
                }
            }
        }else{
            System.out.println("Uma ou mais credenciais que você colocou são inválidas. Tente novamente.");
        }
        } // fim do while
    }

    private static void loginConfirmarCadastro(){
        resetarResposta();
        while(respostaValida == false){
            System.out.println("Notamos que você ainda não terminou o seu cadastro. Você pode está perdendo a oportunidade de vender seu produto na breLoja sem o seu cadastro completo.");
            System.out.println("Você gostaria de terminar o seu cadastro?");
            System.out.println("1. Sim\n2. Não");
            resposta = sc.nextInt();
            switch(resposta){
                case 1: completarCadastro(); respostaValida = true; break;
                case 2: 
                    System.out.println("Você será redirecionado a loja normalmente...");
                    respostaValida = true;
                    break;
                default: System.out.println("Resposta inválida."); break;
            }
        }
    }

    private static void loginUsuário(Usuário usuário){
        mainU.setNome(usuário.getNome());
        mainU.setCPF(usuário.getCPF());
        mainU.setEmail(usuário.getEmail());
        mainU.setSenha(usuário.getSenha());
        mainU.setNomePublico(usuário.getNomePublico());
        mainU.setEstado(usuário.getEstado());
        mainU.setCidade(usuário.getCidade());
        mainU.setContato(usuário.getContato());

        mainC.setNome(usuário.getNome());
        mainC.setCPF(usuário.getCPF());
        mainC.setEmail(usuário.getEmail());
        mainC.setSenha(usuário.getSenha());

        Usuário.cadastroCompleto = true;
        Produto.meusProdutosAtualizar(); // Pega os produtos do usuário logado
    }

    private static void loginCadastro(Cadastro cadastro){
        mainC.setNome(cadastro.getNome());
        mainC.setCPF(cadastro.getCPF());
        mainC.setEmail(cadastro.getEmail());
        mainC.setSenha(cadastro.getSenha());
    }

    private static String censurarNome(String nome){
        String[] nomeSeparado = nome.split(" "); // Separa o nome em uma string

        int ultimoIndex = nomeSeparado.length-1; // Pega o último index
        String ultimoNome = nomeSeparado[ultimoIndex].charAt(0) + "."; // Pega apenas a primeira letra e coloca um ponto
        String nomeCensurado = nomeSeparado[0] + " " + ultimoNome;

        return nomeCensurado; // Retorna o nome censurado

    }


    // * * * Menu de compras * * *
    private static void menuCompras(){
        resetarResposta();
        mostrarProdutos();
    }
    private static void mostrarProdutos(){
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
            case 5: // Ver seus produtos na loja
            if(Usuário.cadastroCompleto == false){ // Verifica se você terminou o cadastro
                respostaValida = true;
                completarCadastroPergunta();
            }else{
                if(Produto.meusProdutos.size() != 0){ // Vê se você tem algum produto
                respostaValida = true;
                meusProdutos();
                }else{
                    System.out.println("Você não possui nenhum produto na loja.");
                }
            }
            break;
            case 6: // Volta ao menu principal
            respostaValida = true;
            menuPrincipal();
            break;
        }
        }
        
    }
    // Coisas relacionadas a compra de produtos
    private static void escolherProdutoVer(){ // PRODUTOS DISPONÍVEIS = > 1. Checar um produto
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
    private static void escolherProdutoComprar(){ // PRODUTOS DISPONÍVEIS => 2. Adicionar ao carrinho
        resetarResposta();
        while(respostaValida == false){
            System.out.println("Escolha qual produto da lista você gostaria adicionar ao carrinho. (Somente número)");
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
    private static void itemSelecionado(int indexP){ // Item que o usuário decidiu selecionar no escolherProdutoVer()
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
            if(usuárioProduto.getNome() != mainU.getNome()){ // usuárioProduto != mainU
                respostaValida = true;
                carrinhoUnidades();
                break;
            }else{
                System.out.println("Você não pode comprar um produto de si mesmo.");
                Divisão(0);
                break;
            }
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
    private static void carrinhoUnidades(){ // O programa adiciona quantidade de unidades que o usuário quer adicionar no carrinho
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
                System.out.println("Você será redirecionado a loja novamente...");
                menuCompras();
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
    private static void carrinhoOpções(){ // PRODUTOS DISPONÍVEL => 3. Ver meu carrinho
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
    private static void carrinhoRemover(){
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
    private static void adicionarProduto(){ // PRODUTOS DISPONÍVEIS => 4. Adicionar produto a loja
        if(Usuário.cadastroCompleto == false){ // Te redireciona a região para concluir cadastro
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
            int index = Produto.Produtos.size() - 1; // Último item da lista de produtos
            Produto produtoAtual = Produto.Produtos.get(index);
            Produto.meusProdutos.add(produtoAtual);
            Divisão(1);
            menuCompras();
        }
    }
    private static void meusProdutos(){ // PRODUTOS DISPONÍVEIS => 5. Ver meus produtos
        resetarResposta();
        if(Usuário.cadastroCompleto == true){
            if(Produto.meusProdutos.size() != 0){ // Ver se não está vazio            
            Produto.meusProdutosMostrar();
            while(respostaValida == false){
                System.out.println("O que você gostaria de fazer?");
                System.out.println("1. Ver um produto"); // Ver classe itemSelecionado() e escolherProdutoVer() se possível como referencia
                System.out.println("2. Alterar um produto");
                System.out.println("3. Remover um produto");
                System.out.println("4. Remover todos os meus produtos");
                System.out.println("5. Voltar");
                resposta = sc.nextInt();
                switch(resposta){
                    case 1: // 1. Ver um produto
                    if(Produto.meusProdutos.size() != 0){
                        verMeuProduto();
                        respostaValida = true;
                        break;
                    }else{
                        System.out.println("Você não tem nenhum produto para realizar essa ação.");
                        break;
                    }
                    case 2: // 2. Alterar um produto
                    if(Produto.meusProdutos.size() != 0){
                        alterarMeuProdutoEscolher();
                    }else{
                        System.out.println("Você não tem nenhum produto para realizar essa ação.");
                        break;
                    }
                    case 3: // 3. Remover um produto
                    if(Produto.meusProdutos.size() != 0){
                        removerMeuProduto();
                    }else{
                        System.out.println("Você não tem nenhum produto para realizar essa ação.");
                        break;
                    }
                    case 4: // 4. Remover todos os meus produtos
                    if(Produto.meusProdutos.size() != 0){
                        limparProdutos();
                    }else{
                        System.out.println("Você não tem nenhum produto para realizar essa ação.");
                        break;
                    }
                    case 5: 
                        System.out.println("Você será redirecionado a loja novamente...");
                        menuCompras();
                        break;
                    default: System.out.println("Resposta inválida.");
                }
            }
            }else{ // Se estiver vazio...
                System.out.println("Você não adicionou nenhum produto a loja.");
                System.out.println("Você será redirecionado a loja novamente...");
                menuCompras();
            }
        }else{completarCadastroPergunta();}
    }
    private static void verMeuProduto(){ // 5. Ver meus produtos => 1. Ver um produto
        resetarResposta();
        System.out.println("Escolha qual produto você gostaria de ver. Digite 0 caso você tenha mudado de ideia.");
        while(respostaValida == false || resposta != 0){
            resposta = sc.nextInt();
            if(resposta != 0){            
                indexP = resposta-1;
                if(indexP > Produto.meusProdutos.size() || indexP < 0){ // evitar OutOfBounds
                    System.out.println("O número que você escolheu não está na lista. Reveja a lista e tente novamente.");
                }else{
                    Produto.meusProdutos.get(indexP).exibirProduto();
                    Divisão(1);
                    System.out.println("Clique Enter para sair.");
                    sc.nextLine();
                    meusProdutos();
                    respostaValida = true;
                }
            }else{
                System.out.println("Você será redirecionado ao menu novamente...");
                meusProdutos();
            }
        } // fim do while
    }
    private static void alterarMeuProdutoEscolher(){ // 5. Ver meus produto => 2. Alterar um produto
        resetarResposta();
        System.out.println("Escolha qual produto você gostaria de alterar. Digite 0 caso você tenha mudado de ideia.");
        while(respostaValida == false || resposta != 0){
            resposta = sc.nextInt();
            if(resposta != 0){            
                indexP = resposta-1;
                if(indexP > Produto.meusProdutos.size() || indexP < 0){ // evitar OutOfBounds
                    System.out.println("O número que você escolheu não está na lista. Reveja a lista e tente novamente.");
                }else{
                    System.out.println("Observação: Você pode manter as mesmas cretenciais simplesmente apertando Enter.");
                    alterarMeuProduto(indexP);
                }
            }else{
                System.out.println("Você será redirecionado ao menu novamente...");
                meusProdutos();
            }
        } // fim do while
    }
    private static void alterarMeuProduto(int index){ // Ação de alterar produto em si
        Produto produtoAtual = Produto.meusProdutos.get(index);
        String item;
        double preço;
        int estoque;
        String descrição;

        resetarResposta();
        while(respostaValida == false){
            // Parte de editar as cretenciais
            System.out.println("Qual vai ser o nome do seu produto?");
            sc.nextLine(); // buffer
            item = sc.nextLine();
            if(item == null) item = produtoAtual.getItem();
            System.out.println("Qual vai ser o preço?");
            preço = sc.nextDouble();
            if(String.valueOf(preço) == null) preço = produtoAtual.getPreço();
            System.out.println("Você quer quanto desse item em estoque?");
            estoque = sc.nextInt();
            if(String.valueOf(estoque) == null) estoque = produtoAtual.getEstoque();
            System.out.println("Dê uma descrição ao seu item.");
            sc.nextLine(); // buffer
            descrição = sc.nextLine();
            if(descrição == null) descrição = produtoAtual.getDescrição();

            produtoAtual.exibirProduto();
            Divisão(1);
            System.out.println("Você está satisfeito com as mudanças atuais?");
            System.out.println("1. Sim\n2. Não");
            resposta = sc.nextInt();
            switch(resposta){
                case 1:
                    System.out.println("Seu item foi alterado com sucesso.");
                    Produto.Produtos.get(index).setItem(item);
                    Produto.Produtos.get(index).setPreço(preço);
                    Produto.Produtos.get(index).setEstoque(estoque);
                    Produto.Produtos.get(index).setDescrição(descrição);
                    respostaValida = true;
                    meusProdutos();
                    break;
                case 2:
                    System.out.println("Você terá de repetir todas as perguntas novamente.");
                    break;
                default: System.out.println("Resposta inválida."); break;
            } // fim do switch case
        } // fim do while
    }
    private static void removerMeuProduto(){ // 5. Ver meus produtos => 3. Remover um produto
        resetarResposta();
        System.out.println("Escolha qual produto você gostaria de remover. Digite 0 caso você tenha mudado de ideia.");
        while(respostaValida == false || resposta != 0){
            resposta = sc.nextInt();
            if(resposta != 0){            
                indexP = resposta-1;
                if(indexP > Produto.meusProdutos.size() || indexP < 0){ // evitar OutOfBounds
                    System.out.println("O número que você escolheu não está na lista. Reveja a lista e tente novamente.");
                }else{
                    System.out.println("O seu produto foi removido com sucesso.");
                    // Remove de ambos os arrays
                    Produto.meusProdutos.remove(indexP);
                    Produto.Produtos.remove(indexP);
                    menuCompras();
                    respostaValida = true;
                }
            }else{
                System.out.println("Você será redirecionado ao menu novamente...");
                meusProdutos();
            }
        }
    }

    private static void limparProdutos(){ // 5. Ver meus produtos => 4. Remover todos os meus produtos
        resetarResposta();
        while(respostaValida == false){
            System.out.println("Você deseja realmente remover todos os seus produtos? Essa ação é irreversível.");
            System.out.println("1. Sim\n2. Não");
            resposta = sc.nextInt();
            switch(resposta){
            case 1: // Sim
                Produto.meusProdutos.clear(); // Limpa tudo
                System.out.println("Seus produtos foram totalmente deletados da nossa loja.");
                System.out.println("Você será redirecionado a loja novamente...");
                respostaValida = true;
                menuCompras();
                break;
            case 2: // Não
                System.out.println("Você será redirecionado ao menu novamente...");
                respostaValida = true;
                meusProdutos();
                break;
            default: System.out.println("Resposta inválida."); break;
            }
        }
    }
    // Completar cadastro
    private static void completarCadastroPergunta(){ // Pergunta antes de seguir com o cadastro completo
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
    private static void completarCadastro(){
        mudarNomePublico(np);
        mudarEstado(es);
        mudarContato(co);

        System.out.println("Seu cadastro agora está completo! Agora você pode vender itens na loja normalmente!");
        Usuário.cadastroCompleto = true;
        Usuário.Usuários.add(mainU);
        menuCompras();
    }

} // Fim da classe Sistema
