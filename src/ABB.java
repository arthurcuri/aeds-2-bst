package BST;

import java.util.NoSuchElementException;

public class ABB<E extends Comparable<E>> {
    private No<E> raiz;

    public ABB() {
        raiz = null;
    }

    public boolean isEmpty() {
        return raiz == null;
    }

    public E localizar(E item) {
        return localizar(raiz, item);
    }

    private E localizar(No<E> raizArvore, E item) {
        int comparacoes;
        if (raizArvore == null) {
            throw new NoSuchElementException("");
        }
        comparacoes = item.compareTo(raizArvore.getItem());
        if (comparacoes == 0) {
            return raizArvore.getItem();
        } else if (comparacoes < 0) {
            return localizar(raizArvore.getEsquerda(), item);
        } else {
            return localizar(raizArvore.getDireita(), item);
        }
    }

    public void add(E item) {
        this.raiz = add(raiz, item);
    }

    protected No<E> add(No<E> raizArvore, E item) {
        int comparacoes;
        if (raizArvore == null) {
            raizArvore = new No<>(item);
        } else {
            comparacoes = item.compareTo(raizArvore.getItem());
            if (comparacoes == 0) {
                throw new RuntimeException("");
            } else if (comparacoes < 0) {
                raizArvore.setEsquerda(add(raizArvore.getEsquerda(), item));
            } else {
                raizArvore.setDireita(add(raizArvore.getDireita(), item));
            }
        }
        return raizArvore;
    }

    public void remove(E item) {
        this.raiz = removerItem(raiz, item);
    }

    protected No<E> removerItem(No<E> raizArvore, E item) {
        int comp;
        if (raizArvore == null) {
            throw new IllegalStateException("");
        }
        comp = item.compareTo(raizArvore.getItem());
        if (comp == 0) {
            if (raizArvore.getDireita() == null) {
                raizArvore = raizArvore.getEsquerda();
            } else if (raizArvore.getEsquerda() == null) {
                raizArvore = raizArvore.getDireita();
            } else {
                raizArvore.setEsquerda(removerItemAntecessor(raizArvore, raizArvore.getEsquerda()));
            }
        } else if (comp < 0) {
            raizArvore.setEsquerda(removerItem(raizArvore.getEsquerda(), item));
        } else {
            raizArvore.setDireita(removerItem(raizArvore.getDireita(), item));
        }
        return raizArvore;
    }

    protected No<E> removerItemAntecessor(No<E> removido, No<E> raizArvore) {
        if (raizArvore.getDireita() != null) {
            raizArvore.setDireita(removerItemAntecessor(removido, raizArvore.getDireita()));
        } else {
            removido.setItem(raizArvore.getItem());
            raizArvore = raizArvore.getEsquerda();
        }
        return raizArvore;
    }

    public void caminhamentoEmOrdem() {
        if (isEmpty()) {
            throw new IllegalStateException("");
        }
        caminhamentoEmOrdem(raiz);
    }

    public void caminhamentoEmOrdem(No<E> raizArvore) {
        if (raizArvore != null) {
            caminhamentoEmOrdem(raizArvore.getEsquerda());
            System.out.println(raizArvore.getItem().toString());
            caminhamentoEmOrdem(raizArvore.getDireita());
        }
    }

    public E retornaMenor() {
        if (isEmpty()) {
            throw new IllegalStateException("");
        }
        return retornaMenor(raiz);
    }

    public E retornaMenor(No<E> raizArvore) {
        if (raizArvore.getEsquerda() != null) {
            return retornaMenor(raizArvore.getEsquerda());
        } else {
            return raizArvore.getItem();
        }
    }

    public ABB<E> cloneSemNo() {
        ABB<E> clone = new ABB<>();
        return cloneSemNo(raiz, clone);
    }

    private ABB<E> cloneSemNo(No<E> raizArvore, ABB<E> bst) {
        if (raizArvore != null) {
            cloneSemNo(raizArvore.getEsquerda(), bst);
            bst.add(raizArvore.getItem());
            cloneSemNo(raizArvore.getDireita(), bst);
        }
        return bst;
    }

    /*public ABB<E> obterSubconjuntoMaiores(E item) {
        if (localizar(item) == null) {
            throw new IllegalStateException("");
        }
        ABB<E> bst = new ABB<>();
        obterSubconjuntoMaiores(raiz, item, bst);
        return bst;
    }

    private void obterSubconjuntoMaiores(No<E> raizArvore, E item, ABB<E> bst) {
        int comp;
        if (raizArvore != null) {
            comp = item.compareTo(raizArvore.getItem());
            if (comp <= 0) {
                bst.add(raizArvore.getItem());
            }
            obterSubconjuntoMaiores(raizArvore.getEsquerda(), item, bst);
            obterSubconjuntoMaiores(raizArvore.getDireita(), item, bst);
        }
    }

    public ABB<E> obterSubconjuntoMenores(E item) {
        if (localizar(item) == null) {
            throw new NoSuchElementException("");
        }
        ABB<E> bst = new ABB<>();
        obterSubconjuntoMenores(raiz, item, bst);
        return bst;
    }

    private void obterSubconjuntoMenores(No<E> raizArvore, E item, ABB<E> bst) {
        int comp;
        if (raizArvore != null) {
            comp = item.compareTo(raizArvore.getItem());
            if (comp >= 0) {
                bst.add(raizArvore.getItem());
            }
            obterSubconjuntoMenores(raizArvore.getEsquerda(), item, bst);
            obterSubconjuntoMenores(raizArvore.getDireita(), item, bst);
        }
    }

    public boolean ehRaiz(E item) {
        return item.equals(raiz.getItem());
    }

    public E obterAntecessor(E item) {
        return obterAntecessor(raiz, item, null);
    }

    private E obterAntecessor(No<E> raizArvore, E item, No<E> pai) {
        int comp;
        if (raizArvore == null) {
            throw new IllegalStateException("");
        }
        comp = item.compareTo(raizArvore.getItem());
        if (comp == 0) {
            if (raizArvore.getEsquerda() != null) {
                return antecessor(raizArvore.getEsquerda());
            } else if (pai != null) {
                return pai.getItem();
            } else {
                throw new IllegalStateException("");
            }
        } else if (comp < 0) {
            return obterAntecessor(raizArvore.getEsquerda(), item, raizArvore);
        } else {
            return obterAntecessor(raizArvore.getDireita(), item, raizArvore);
        }
    }

    private E antecessor(No<E> raizArvore) {
        if (raizArvore.getDireita() == null) {
            return raizArvore.getItem();
        }
        return antecessor(raizArvore.getDireita());
    }

    public E obterSucessor(E item) {
        return obterSucessor(raiz, item, null);
    }

    private E obterSucessor(No<E> raizArvore, E item, No<E> pai) {
        int comp;
        if (raizArvore == null) {
            throw new NoSuchElementException("");
        }
        comp = item.compareTo(raizArvore.getItem());
        if (comp == 0) {
            if (raizArvore.getDireita() != null) {
                return sucessor(raizArvore.getDireita());
            } else if (pai != null) {
                return pai.getItem();
            } else {
                throw new IllegalStateException("");
            }
        } else if (comp < 0) {
            return obterSucessor(raizArvore.getEsquerda(), item, raizArvore);
        } else {
            return obterSucessor(raizArvore.getDireita(), item, raizArvore);
        }
    }

    public E sucessor(No<E> raizArvore) {
        if (raizArvore.getEsquerda() != null) {
            return sucessor(raizArvore.getEsquerda());
        }
        return raizArvore.getItem();
    }

    public ABB<E> clone(){
        ABB<E> bst = new ABB<>();
        if(this.raiz != null){
            bst.raiz = this.raiz.clone();
        }
        return bst;
    }*/

    public boolean ehRaiz(E item){
        return raiz.getItem().equals(item);
    }

    public ABB<E> obterSubconjuntoMenores(E item){
        ABB<E> bst = new ABB<>();
        obterSubconjuntoMenores(raiz, item, bst);
        return bst;
    }

    private void obterSubconjuntoMenores(No<E> raizArvore, E item, ABB<E> bst){
        int comp;
        if(raizArvore != null){
            comp = item.compareTo(raizArvore.getItem());
            if(comp >= 0){
                bst.add(raizArvore.getItem());
            }
            obterSubconjuntoMenores(raizArvore.getEsquerda(), item ,bst);
            obterSubconjuntoMenores(raizArvore.getDireita(), item, bst);
        }
    }

    public E obterAntecessor(E item){
        return obterAntecessor(raiz, item, null);
    }

    private E obterAntecessor(No<E> raizArvore, E item, No<E> pai){
        int comp;
        if(raizArvore == null){
            throw new NoSuchElementException("");
        }
        comp = item.compareTo(raizArvore.getItem());
        if(comp == 0){
            if(raizArvore.getEsquerda() != null){
                return antecessor(raizArvore.getEsquerda());
            }else if(pai != null){
                return pai.getItem();
            }else{
                throw new IllegalStateException("");
            }
        }else if(comp < 0){
            return obterAntecessor(raizArvore.getEsquerda(), item, raizArvore);
        }else{
            return obterAntecessor(raizArvore.getDireita(), item, raizArvore);
        }
    }

    private E antecessor(No<E> raizArvore){
        if(raizArvore.getDireita() != null){
            return antecessor(raizArvore.getDireita());
        }
        return raizArvore.getItem();
    }

    public ABB<E> clone(){
        ABB<E> bst = new ABB<>();
        if(this.raiz != null){
            bst.raiz = this.raiz.clone();
        }
        return bst;
    }

    public E obterSucessor(E item){
        return obterSucessor(raiz, item, null);
    }

    private E obterSucessor(No<E> raizArvore, E item, No<E> pai) {
        int comp;
        if (raizArvore == null) {
            throw new NoSuchElementException("");
        }
        comp = item.compareTo(raizArvore.getItem());
        if (comp == 0) {
            if (raizArvore.getDireita() != null) {
                return sucessor(raizArvore.getDireita());
            } else if (pai != null) {
                return pai.getItem();
            } else {
                throw new IllegalStateException("");
            }
        } else if (comp < 0) {
            return obterSucessor(raizArvore.getEsquerda(), item, raizArvore);
        } else {
            return obterSucessor(raizArvore.getDireita(), item, raizArvore);
        }
    }

    private E sucessor(No<E> raizArvore){
        if(raizArvore.getEsquerda()!=null){
            sucessor(raizArvore.getEsquerda());
        }
        return raizArvore.getItem();
    }

    public ABB<E> obterSubconjuntoMaiores(E item){
        ABB<E> bst = new ABB<>();
        obterSubconjuntoMaiores(raiz, item, bst);
        return bst;
    }

    private void obterSubconjuntoMaiores(No<E> raizArvore, E item, ABB<E> bst){
        int comp;
        if(raizArvore != null){
            comp = item.compareTo(raizArvore.getItem());
            if(comp <= 0){
                bst.add(raizArvore.getItem());
            }
            obterSubconjuntoMaiores(raizArvore.getEsquerda(), item, bst);
            obterSubconjuntoMaiores(raizArvore.getDireita(), item, bst);
        }
    }
}
