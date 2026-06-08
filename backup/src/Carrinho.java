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
    public static void listarCarrinho(){
        System.out.println("========= MEU CARRINHO =========");
        if(meuCarrinho.size() > 0){ // Ver se o carrinho está vazio ou não
        int j = 0; // Coisas no carrinho, caso o usuário queira comprar o mesmo item em estoque
        for (int i = 0; i < meuCarrinho.size(); i++){
            meuCarrinho.get(i).listarItem(j);
            preçoTotal += meuCarrinho.get(i).getPreço();
            j++;
        }   
        Sistema.Divisão(1);
        System.out.println("PREÇO TOTAL: R$"+preçoTotal);
        }else{
            System.out.println("Seu carrinho está vazio no momento. Que tal adicionar alguma coisa nele?");
        }
    }
}
