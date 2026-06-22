import java.util.ArrayList;

public class Carrinho {
    public static ArrayList<Produto> meuCarrinho = new ArrayList<>();
    private static double preçoTotal;

    public static void adicionarProduto(Produto produto){
        meuCarrinho.add(produto);
    }
    public static void removerProduto(Produto produto){
        meuCarrinho.remove(produto);
    }
    public static void Esvaziar(){
        meuCarrinho.clear();
    }

    public static void listarCarrinho(){ // Mostra os itens no carrinho
        preçoTotal = 0; // = 0; Para não acumular 
        int j = 0; // Coisas no carrinho, caso o usuário queira comprar o mesmo item em estoque
        System.out.println("========= MEU CARRINHO =========");
        for (int i = 0; i < meuCarrinho.size(); i++){
            meuCarrinho.get(i).listarItem(j);
            preçoTotal += meuCarrinho.get(i).getPreço();
            j++;
        }   
        Sistema.Divisão(1);
        System.out.println("PREÇO TOTAL: R$"+preçoTotal);
    } // PS.: Lembrar de colocar para verificar se o carrinho está vazio se o código na classe Sistema estiver errado

    public static void comprarTudo(){ // Compra tudo
        for(int i = 0; i < meuCarrinho.size(); i++){
        Produto produtoAtual = meuCarrinho.get(i);
        produtoAtual.setEstoque(produtoAtual.getEstoque() - 1);
        }
        System.out.println("Obrigado pela compra! Os estoques foram atualizados.");
        Esvaziar();
    }
}
