package busqueda.DataStructures;

import busqueda.Arco;

import java.util.Arrays;


public class PriorityBusqueda<T> implements ColeccionBusqueda<T>{

    protected Arco<T>[] heap;
    protected int size;

    @Override
    public void adicionar(Arco<T> a) {
        if(size >= heap.length) resize();
        heap[size++] = a;
        Up();
    }

    @Override
    public boolean esvacia() { return size == 0; }

    @Override
    public Arco<T> obtener() { return (esvacia())? null: heap[0]; }

    @Override
    public void remover() {
        heap[0] = heap[--size];
        heap[size] = null;

        Down();
    }

    @Override
    public int size() {
        return this.size;
    }

    private void Up(){
        int index = size-1;

        while (index > 0 && (evaluar(heap[index]) < evaluar(heap[parent(index)]))){

            swap(index, parent(index));
            index = parent(index);
        }
    }

    private void Down(){
        int index = 0;
        while(childLeft(index) < size){
            int menor;
            if(evaluar(heap[childLeft(index)]) <= evaluar(heap[childRight(index)])){
                menor =childLeft(index);
            }else menor = childRight(index);

            if(evaluar(heap[menor]) < evaluar(heap[index])){
                swap(menor, index);
                index = menor;
            }
            else break;
        }
    }

    protected double evaluar(Arco<T> arco){
        return arco.costoTotal();
    }

    private int childLeft(int index){ return (2*index + 1 < size)? 2*index + 1: index; }

    private int childRight(int index){ return (2*index + 2 < size)? 2*index + 2: index; }

    private int parent(int index){ return (index-1)/2; }

    private void swap(int a, int b){
        Arco<T> aux = heap[a];
        heap[a] = heap[b];
        heap[b] = aux;
    }

    private void resize(){ heap = Arrays.copyOf(heap, heap.length*2); }


    public PriorityBusqueda(int capacidad){
        heap = new Arco[capacidad];
        size = 0;
    }

    public PriorityBusqueda(){ this(10); }

    public int getSize() {
        return size;
    }
}
