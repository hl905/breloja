import java.util.ArrayList;

public class Usuário extends Cadastro{
    private String nomePublico;
    private String estado;
    private String cidade;
    private boolean contato;
    public boolean meuPerfil; // serve para o exibirDados
    public static boolean cadastroCompleto = false;
    public static ArrayList<Cadastro> Cadastrados = new ArrayList<>();
    public static ArrayList<Usuário> Usuários = new ArrayList<>();

    // Construtor
    public Usuário(Cadastro cadastro, String nomePublico, String estado, String cidade, boolean contato){
        super(cadastro.getNome(),cadastro.getCPF(),
              cadastro.getEmail(),cadastro.getSenha());
        if (nomePublico == null){ // nome = nome público, até ser mudado
        this.nomePublico = cadastro.getNome();
        }else{
        this.nomePublico = nomePublico;
        }
        this.estado = estado;
        this.cidade = cidade;
        this.contato = contato;
    }
    public static void criarUsuários(){
        Cadastrados.add(new Cadastro("João Castro de Lima","197.385.331-04","jcastrolima@bol.com.br","castrolima1979"));
        Cadastrados.add(new Cadastro("Laura Beatriz de Souza Flores","249.517.216-43","laurabb1996@gmail.com","4amojesus4"));
        Cadastrados.add(new Cadastro("Matheus Gomes Monteiro","665.322.679-45","matheusbeyblade@gmail.com","f@%j3&k9b%"));
        Cadastrados.add(new Cadastro("Césio Lobo Castello","749.163.900-32","castellocontato@yahoo.com","F12ARMstrong"));
        Cadastrados.add(new Cadastro("Gabriella Soares da Costa","739.284.165-37","gsc459@gmail.com","dorothy281913%"));
        Cadastrados.add(new Cadastro("Demonstração Teste", "012.345.678-90", "teste@mail.com", "12345678"));
        Usuários.add(new Usuário(Cadastrados.get(0), "CASTRO929323", "MT", "Cuiabá", true));
        Usuários.add(new Usuário(Cadastrados.get(1), "Desapegos da Laura", "MG", "Montes Claros", false));
        Usuários.add(new Usuário(Cadastrados.get(2), "matt_", "SC", "Florianópolis", false));
        Usuários.add(new Usuário(Cadastrados.get(3), "Castello Lobo", "RS", "Porto Alegre", true));
        Usuários.add(new Usuário(Cadastrados.get(4), "Gabriella88372", "BA", "Salvador", true));
    }

    // Getters
    public String  getNomePublico(){return nomePublico;}
    public String  getEstado(){return estado;}
    public String  getCidade(){return cidade;}
    public boolean getContato(){return contato;}
    // Setters
    public boolean setNomePublico(String nomePublico){
        if (nomePublico == null){
            System.out.println("Seu nome será o mesmo que seu nome público. Você pode alterar essa opção no futuro.");
            nomePublico = getNome(); 
            return true;
        }
        this.nomePublico = nomePublico;
        return true;
    }
    public boolean setEstado(String estado){
        if (estado == null) espaçoEmBranco();
        if (estadoValido(estado)){
            this.estado = estado;
            return true;
        }
        return false;
    }
    public boolean setCidade(String cidade){
        if (cidade == null) espaçoEmBranco();
        this.cidade = cidade;
        return true;
    }
    public void setContato(boolean contato){this.contato = contato;}
    @Override
    public void meuPerfil(boolean meuPerfil){this.meuPerfil = meuPerfil;}

    // Métodos
    @Override
    public void exibirDados(){ // Exibe os dados tanto do usuário como de outros vendedores
        if(meuPerfil == true){        
            System.out.println("Nome da Conta: "+getNome());
            if (!getNome().equals(getNomePublico())){ // nome = nome público
            System.out.println("Nome Público: "+nomePublico);
            }
            System.out.println("CPF: "+getCPF());
            System.out.println("Cidade/estado: "+cidade+" - "+estado);
            System.out.println("E-mail: "+getEmail());
            System.out.print("Contato disponível?: "); contatoDisponível();
            System.out.println("Senha: "+getSenha());
        }else{
            System.out.println("Nome do Vendedor: "+nomePublico);
            System.out.println("Cidade/estado: "+cidade+" - "+estado);
            if(contato == true){
                System.out.println("E-mail de Contato: "+getEmail());
            }
        }
    }
    public void contatoDisponível(){ // Transforma 'true' e 'false' em 'sim' e 'não'
        if (contato == true){
            System.out.println("Sim");
        }else{
            System.out.println("Não");
        }
    }

    // Funções para verificar se informações estão completas
    // ================== ESTADO ==========================
    private boolean estadoValido(String estado){
        if(estado.length() == 2){
            for(int i = 0; i < 2; i++){
                if (!Character.isLetter(estado.charAt(i))){ // Se o caráctere no index i não for uma letra...
                System.out.println("Estado inválido. O sistema quer somente a sigla do seu estado.");
                }
            } 
            return true; // Se o for loop der tudo certo...
        }
        System.out.println("Por favor, coloque apenas a sigla do seu estado, e não o nome completo.");
        return false; // Se o programa perceber que tem menos ou mais de 2 letras
    }

}
