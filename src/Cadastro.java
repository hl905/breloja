public class Cadastro {
    private String nome;
    private String cpf;
    private String email;
    private String senha;
    public boolean meuPerfil; // ver se você está vendo seu próprio perfil ou de outra pessoa

    // Construtor
    public Cadastro(String nome, String cpf, String email, String senha){
        this.nome=nome;
        this.cpf=cpf;
        this.email=email;
        this.senha=senha;
    }

    // Getters
    public String getNome() { return nome; }
    public String getCPF()  { return cpf;  }
    public String getEmail(){ return email;}
    public String getSenha(){ return senha;}
    public void meuPerfil(boolean meuPerfil){this.meuPerfil = meuPerfil;}

    // Setters
    public boolean setNome(String nome){   // Nome
        if(nome == null) espaçoEmBranco();
        if(nome.length() >= 3){
            this.nome=nome;
            return true;
        }else{
            System.out.println("Nome inválido. Seu nome deve ter no mínimo 3 caractéres.");
            return false;
        }
    }
    public boolean setCPF(String cpf){     // CPF
        if (cpf == null) espaçoEmBranco();
        if (cpf.length() == 11 && CPFvalido(cpf)){
            formatarCPF(cpf);
            return true;
        } else if (VerificarCPF(cpf)){
            this.cpf = cpf;
            return true;
        } else {
            System.out.println("CPF inválido. Verifique se você colocou somente números ou a quantidade de dígitos esperados.");
            return false;
        }
    }   
    public boolean setEmail(String email){ // E-mail
        if (email == null) espaçoEmBranco();
        if (emailValido(email)){
            this.email=email;
            return true;
        }else{
            System.out.println("Endereço de E-mail inválido.");
            System.out.println("Exemplo de padrão esperado de E-mail: meu.nome@mail.com.br");
            return false;
        }
    }
    public boolean setSenha(String senha){ // Senha
        if (senha == null) espaçoEmBranco();
        if (senha.length() >= 8){
            this.senha = senha;
            return true;
        }else{
            System.out.println("Senha inválida. A senha tem que ter no mínimo 8 caractéres.");
            return false;
        }
    }

    // Funções para verificar se as informações são válidas
    // =================== Espaço em Branco ====================
    public static boolean espaçoEmBranco(){
        System.out.println("Espaço obrigatório.");
        return false;
    }
    // ========================== CPF ==========================
    private boolean CPFvalido(String cpf){ // Verifica se o CPF é somente composto de números
        try {  
        Double.parseDouble(cpf);             // Converte o String CPF para double
        return true;
        } catch(NumberFormatException e){  
        return false;
        } 
    }
    private void formatarCPF(String cpf){ // Adiciona os pontos e o hífen
        StringBuilder sb = new StringBuilder(cpf);
        sb.insert(9, '-');
        for (int i = 6; i >= 3; i -= 3) { // Adiciona os pontos de trás para frente
            sb.insert(i, '.');
        }
        this.cpf = sb.toString();
    }
    public boolean VerificarCPF(String cpf){ // Verifica se o CPF foi formatado
        if (cpf.length() != 14) return false;
        for (int i = 0; i < 14; i++){
        switch(i){
        default: // número
        if (Character.isDigit(cpf.charAt(i))){
            continue;
        }else{
            return false;
        }
        case 3: case 7: // ponto
        if (cpf.charAt(i) == '.'){
            continue;
        }else{
            return false;
        }
        case 11: // hífen
        if (cpf.charAt(i) == '-'){
            continue;
        }else{
            return false;
        }
        } // Fim do switch case
        } // Fim do for loop
        return true;
    }
    // ===================== E-mail ==========================
    public static boolean emailValido(String email) { // Verifica se o E-mail é válido
        int atLocation = email.indexOf('@');
        if (atLocation <= 0 || atLocation != email.lastIndexOf('@') || atLocation == email.length() - 1) return false;

        String localPart = email.substring(0, atLocation);
        String domainPart = email.substring(atLocation + 1);

        return validarLocal(localPart) && validarDominio(domainPart);
    }
    // Todos os for abaixo são para verificar caractéres inválidos dentro do
    // Nome, domínio e extensão dos endereços de e-mail. 

    // local = "meu.nome"
    // domain = "@mail"
    // extension = ".com.br"
    private static boolean validarLocal(String local) { // Validar o nome do e-mail
        if (local.isEmpty() || local.startsWith(".") || local.endsWith(".") || local.contains("..")) return false;
        for (char c : local.toCharArray()) { 
            if (!Character.isLetterOrDigit(c) && c != '.' && c != '_' && c != '%' && c != '+' && c != '-') return false;
        }
        return true;
    }
    private static boolean validarDominio(String domain) { // Validar o domínio (@mail)
        int lastDotLocation = domain.lastIndexOf('.');  // Ver quantos pontos depois do domínio
        if (lastDotLocation <= 0 || domain.contains("..")) return false;
        for (char c : domain.toCharArray()) { 
            if (!Character.isLetterOrDigit(c) && c != '.' && c != '-') return false;
        }
        String extension = domain.substring(lastDotLocation + 1); 
        return validarExtensao(extension);
    }
    private static boolean validarExtensao(String extension) { // Validar a extensão (.br)
        if (extension.length() < 2) return false;
        for (char c : extension.toCharArray()) { 
            if (!Character.isLetter(c)) return false;
        }
        return true;
    }

    public void exibirDados(){
        System.out.println("Nome: "+nome);
        System.out.println("CPF: "+cpf);
        System.out.println("E-mail: "+email);
        System.out.println("Senha: "+senha);
    }
    
}

