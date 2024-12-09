package BST;

public class No<E> {
    private E item;
    private int altura;
    private No<E> esquerda;
    private No<E> direita;

    public No(){
        this.setItem(null);
        this.setDireita(null);
        this.setEsquerda(null);
        this.altura = 0;
    }

    public No(E item){
        this.setItem(item);
        this.setDireita(null);
        this.setEsquerda(null);
        this.altura = 0;
    }

    public No(E item, No<E> esquerda, No<E> direita){
        this.setItem(item);
        this.setDireita(direita);
        this.setEsquerda(esquerda);
        this.setAltura();
    }

    public E getItem() {
        return item;
    }

    public void setItem(E item) {
        this.item = item;
    }

    public No<E> getEsquerda() {
        return esquerda;
    }

    public void setEsquerda(No<E> esquerda) {
        this.esquerda = esquerda;
    }

    public No<E> getDireita() {
        return direita;
    }

    public void setDireita(No<E> direita) {
        this.direita = direita;
    }

    public int getAltura() {
        return altura;
    }

    private int getAltura(No<E> raiz){
        if(raiz == null){
            return -1;
        }else{
            return raiz.getAltura();
        }
    }

    public void setAltura() {
        int alturaEsq, alturaDir;
        alturaEsq = getAltura(esquerda);
        alturaDir = getAltura(direita);

        if(alturaEsq > alturaDir){
            this.altura = alturaEsq + 1;
        }else{
            this.altura = alturaDir +1;
        }
    }

    public int getFB() {
        int alturaEsq, alturaDir;
        alturaEsq = getAltura(esquerda);
        alturaDir = getAltura(direita);
        return alturaEsq - alturaDir;
    }

    /*public No<E> clone(){
        No<E> novoNo = new No<>(this.item);
        if(this.getEsquerda() != null){
            novoNo.setEsquerda(this.getEsquerda().clone());
        }
        if(this.getDireita() != null){
            novoNo.setDireita(this.getDireita().clone());
        }
        return novoNo;
    }*/

    public No<E> clone(){
        No<E> novoNo = new No<>(this.item);
        if(this.getEsquerda() != null){
            novoNo.setEsquerda(this.getEsquerda().clone());
        }
        if(this.getDireita() != null){
            novoNo.setDireita(this.getDireita().clone());
        }
        return novoNo;
    }
}
