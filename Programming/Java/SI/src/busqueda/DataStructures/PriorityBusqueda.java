package busqueda.DataStructures;

import busqueda.Arco;

import java.util.Arrays;


public class PriorityBusqueda<T> implements ColeccionBusqueda<T>{

    protected Arco[] heap;
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

    protected void Up(){
        int index = size-1;
        while (index > 0 && (heap[index].costoTotal() < heap[parent(index)].costoTotal())){
            swap(index, parent(index));
            index = parent(index);
        }
    }

    protected void Down(){
        int index = 0;
        while(childLeft(index) < size){
            int menor;
            if(heap[childLeft(index)].costoTotal() <= heap[childRight(index)].costoTotal()){
                menor =childLeft(index);
            }else menor = childRight(index);

            if(heap[menor].costoTotal() < heap[index].costoTotal()){
                swap(menor, index);
                index = menor;
            }
            else break;
        }
    }

    protected int childLeft(int index){ return (2*index + 1 < size)? 2*index + 1: index; }

    protected int childRight(int index){ return (2*index + 2 < size)? 2*index + 2: index; }

    protected int parent(int index){ return (index-1)/2; }

    protected void swap(int a, int b){
        Arco aux = heap[a];
        heap[a] = heap[b];
        heap[b] = aux;
    }

    private void resize(){ heap = Arrays.copyOf(heap, heap.length*2); }


    public PriorityBusqueda(int capacidad){
        heap = new Arco[capacidad];
        size = 0;
    }

    public PriorityBusqueda(){ this(10); }
}
