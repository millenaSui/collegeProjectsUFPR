#include "ordenacao.h"

#include <string.h>

void getNome(char nome[]){
	//substitua por seu nome
	strncpy(nome, "Millena Suiani Costa", MAX_CHAR_NOME);
	nome[MAX_CHAR_NOME-1] = '\0';//adicionada terminação manual para caso de overflow
}

unsigned int getGRR(){
	return 20221243;
}

void swapElements(int *a, int *b) {
    int aux = *a;
    *a = *b;
    *b = aux;
}

int buscaSequencial(int vetor[], int tam, int valor, int* contComp) {
	if (tam < 1) {
		return -1;
	};
	(*contComp) += 1;
	if (valor == vetor[tam]) {
		return tam-1;
	};
	return buscaSequencial(vetor, tam-1, valor, contComp);
}


int auxBuscaBinaria(int vetor[], int init, int tam, int valor, int *contComp) {
    int mid = (tam + init) / 2;
	if (init > tam) {
        return -1;
    };
	(*contComp) += 1;
	if (valor == vetor[mid]) {
		return mid-1;
    };
	if (valor < vetor[mid]) {
		return auxBuscaBinaria(vetor, init, mid-1, valor, contComp);
    };
	return auxBuscaBinaria(vetor, mid+1, tam, valor, contComp);
};

int buscaBinaria(int vetor[], int tam, int valor, int* contComp){
	*(contComp) = 0;
	return auxBuscaBinaria(vetor, 0, tam-1, valor, contComp);
}

int auxInsertionSort(int vetor[], int init, int tam) {
    int i;
    int contComp = 0;
    for (i = tam; i > init; i--) { 
        if (vetor[i] < vetor[i-1]) { 
            swapElements(vetor + i, vetor + i-1);
        };
        contComp += 1;
    };
    return contComp;
}

int insertionSort(int vetor[], int tam){	
    int init = 0; 
    if (tam-1 == init) { 
        return 0;
    }; 
    return auxInsertionSort(vetor, init, tam) + insertionSort(vetor, tam-1);
}

int auxSelectionSort(int vetor[], int i, int tam, int *contComp) {
    int auxMax = tam;
    for (i = 0; i < tam; i++) {
        if (vetor[auxMax] < vetor[i]) {
            auxMax = i;
        };
        *(contComp) += 1;
    };
    return auxMax;
}

int selectionSort(int vetor[], int tam){
    int init = 0;
    int contComp = 0;
    if (tam == init) {
        return 0;
    };
    int aux = auxSelectionSort(vetor, init, tam-1, &contComp);
    swapElements(vetor + tam, vetor + aux);
    return selectionSort(vetor, tam-1) + contComp;
}

int auxMergeSort(int vetor[], int init, int tam) {
    int contComp = 0;
    if (init == tam) {
        return 0;
    };
    int mid = (init + tam) / 2;
    contComp += auxMergeSort(vetor, init, mid);
    contComp += auxMergeSort(vetor, mid+1, tam);
    return merge(vetor, init, mid, tam) + contComp;
}

int copy(int vetor[], int vetorAux[], int init, int tam) {
    int i = 0;
    while (i <= tam-init) {
        vetor[init+i] = vetorAux[i];
        i++;
    };
    return vetor[tam];
}

int merge(int vetor[], int init, int mid, int tam) {
    int cont = 0;
    int contComp = 0;
    int i = init;
    int j = mid + 1;
    int varAux;
    int vetorAux[tam-init];
    if (init >= tam) {
        return 0;
    };
    while (cont <= tam-init) {
        if (j > tam || (i <= mid && vetor[i] <= vetor[j])) {
            varAux = i;
            i++;
        } else {
            varAux = j;
            j++;
        };
        vetorAux[cont] = vetor[varAux];
        cont++;
        contComp += 1;
    };
    copy(vetor, vetorAux, init, tam);
    return contComp;
}

int mergeSort(int vetor[], int tam){
    return auxMergeSort(vetor, 0, tam);
}

int partition(int vetor[], int init, int tam, int *contComp) {
    int i = init - 1;
    for (int cont = init; cont <= tam - 1; cont++) {
        if (vetor[cont] < vetor[tam]) {
            i++;
            swapElements(vetor + i, vetor + cont);
        };
        *(contComp) += 1;
    };
    swapElements(vetor + (i+1), vetor + tam);
    return i + 1;
};

int auxQuickSort(int vetor[], int init, int tam, int *contComp) {
    if (init < tam) {
        int mid = partition(vetor, init, tam, contComp);
        auxQuickSort(vetor, init, mid-1, contComp);
        auxQuickSort(vetor, mid+1, tam, contComp);
    };
    return *contComp;
};

int quickSort(int vetor[], int tam){ 
    int contComp = 0;
    return auxQuickSort(vetor, 0, tam, &contComp);
}

int maxHepify(int vetor[], int init, int tam) {
    int contComp = 2;
    int maior = init;
    int filhoL = (2 * init) + 1;
    int filhoR = (2 * init) + 2;
    if (filhoL < tam && vetor[filhoL] > vetor[maior]) {
        maior = filhoL;
    };
    if (filhoR < tam && vetor[filhoR] > vetor[maior]) {
        maior = filhoR;
    };
    if (maior != init) {
        swapElements(vetor + init, vetor + maior);
        return maxHepify(vetor, maior, tam) + 2;
    };
    return contComp;
}

int heapSort(int vetor[], int tam){
    int contComp = 0;
    for (int i = (tam/2)-1; i >= 0; i--) {
        contComp += maxHepify(vetor, i, tam) - 1;
    };
    for (int i = tam-1; i>= 0; i--) {
        swapElements(vetor + 0, vetor + i);
        contComp += maxHepify(vetor, 0, i);
    };
    return contComp;
}