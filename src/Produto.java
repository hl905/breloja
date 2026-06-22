import java.util.ArrayList;

public class Produto{
    private String item; // nome do item
    private double preço;
    private int estoque;
    private String descrição;
    private Usuário usuário; // de quem é o produto
    public static ArrayList<Produto> Produtos = new ArrayList<>(); // todos os produtos dentro da loja
    public static ArrayList<Produto> meusProdutos = new ArrayList<>(); // somente os produtos do usuário

    // Construtor
    public Produto(String item, double preço, int estoque, String descrição, Usuário usuário){
        this.item = item;
        this.preço = preço;
        this.estoque = estoque;
        this.descrição = descrição;
        this.usuário = usuário;
    }

    // Getters
    public String getItem(){return item;}
    public double getPreço(){return preço;}
    public int getEstoque(){return estoque;}
    public String getDescrição(){return descrição;}
    public Usuário getUsuário(){return usuário;}

    // Setters
    public void setItem(String item){this.item = item;}
    public void setPreço(double preço){this.preço = preço;}
    public void setEstoque(int estoque){this.estoque = estoque;}
    public void setDescrição(String descrição){this.descrição = descrição;}

    public void exibirProduto(){
        System.out.println("Nome: "+item);
        System.out.println("Vendido por: "+usuário.getNomePublico());
        System.out.println("Preço: R$ "+preço);
        System.out.println("Estoque: "+estoque);
        System.out.println("Descrição do item:\n"+descrição);
    }
    public void listarItem(int index){ // Método principal para listar um produto
        System.out.println((index+1)+". "+item+" - R$ "+preço);
    }
    public static void listarProdutos(){ // Listar produtos disponíveis na loja
        for (int i = 0; i < Produtos.size(); i++){
            Produtos.get(i).listarItem(i);
        }
    }
    public static void criarLoja(){
        Produtos.add(new Produto("Ralador de Queijo", 20, 1, "Ralador de queijo de quatro faces de escama grossa usado", Usuário.Usuários.get(0)));
        Produtos.add(new Produto("Playstation 4 Lacrado de lançamento", 3100, 1, "Playstation 4, nunca saiu do pacote", Usuário.Usuários.get(2)));
        Produtos.add(new Produto("Tênis da Nike", 290, 8, "Tênis da Nike usados porém em bom estado", Usuário.Usuários.get(4)));
        Produtos.add(new Produto("bolinhas de gude", 1, 20, "bolinhas de gude", Usuário.Usuários.get(3)));
        Produtos.add(new Produto("Blusa manga longa", 100, 4, "Coleção de blusas manga longa feminina de várias cores", Usuário.Usuários.get(1)));
    }
    public static void produtosUsuário(Usuário usuário){ // Ver produtos de um determinado usuário
        for(int i = 0; i < Produtos.size(); i++){
            Produto produtoAtual = Produto.Produtos.get(i);
            if(produtoAtual.getUsuário() == usuário){
                produtoAtual.listarItem(i);
            }
        }
    }
    public static void meusProdutosAtualizar(){ // Atualiza os produtos do usuário caso for necessário
        meusProdutos.clear();
        for(int i = 0; i < Produtos.size(); i++){
            Produto produtoAtual = Produtos.get(i);
            if(pegarUsuário(produtoAtual) == Sistema.mainU.getNome()){
                meusProdutos.add(produtoAtual);
            }
        }
    }
    public static String pegarUsuário(Produto produto){ // Serve para comparar o nome do usuário atual com o nome na lista de produtos 
            Usuário usuárioId = produto.getUsuário();
            String usuárioNome = usuárioId.getNome();

            return usuárioNome;
    }
    public static void meusProdutosMostrar(){ // Lista os produtos do usuário
        meusProdutosAtualizar(); // Atualiza os produtos.
        System.out.println("========= MEUS PRODUTOS =========");
        for (int i = 0; i < meusProdutos.size(); i++){
            meusProdutos.get(i).listarItem(i);
        }
        Sistema.Divisão(1);
    }
}
