package BST;

public class Application {
    public static void main(String[] args) {

        ABB<Integer> arvore = new ABB<>();

        System.out.println("---------------------------------------------------------");
        System.out.println("Adicionando elementos: 42, 29, 60, 48, 39, 28...");
        arvore.add(42);
        arvore.add(29);
        arvore.add(60);
        arvore.add(48);
        arvore.add(39);
        arvore.add(28);


        ABB<Integer> arvoreClonada = arvore.clone();

        System.out.println("\nCaminhamento em ordem:");
        arvore.caminhamentoEmOrdem();
        
        System.out.println("\nMenor item da arvore: " + arvore.retornaMenor());
        
        System.out.println("\nArvore clonada: ");
        arvoreClonada.caminhamentoEmOrdem();

        System.out.println("\nArvore dos maiores ");
        ABB<Integer> maiores = arvoreClonada.obterSubconjuntoMaiores(39);
        maiores.caminhamentoEmOrdem();

        System.out.println("\nArvore dos menores ");
        ABB<Integer> menores = arvoreClonada.obterSubconjuntoMenores(39);
        menores.caminhamentoEmOrdem();

        System.out.println();
        System.out.println("O numero 42 é raiz? " + arvore.ehRaiz(42));

        System.out.println();
        System.out.println("Antecessor de 29: " + arvoreClonada.obterAntecessor(29));

        System.out.println();
        System.out.println("Sucessor de 39: " + arvoreClonada.obterSucessor(42));
        
        System.out.println("\nA árvore está vazia? " + arvore.isEmpty());
        System.out.println("---------------------------------------------------------");
    
    }
}
