#ifndef ORDENACAO_H_
#define ORDENACAO_H_

#define MAX_CHAR_NOME 50

//a função getNome deve colocar o seu nome dentro da chamada
//seu nome pode ter no máximo MAX_CHAR_NOME - 1 caracteres
void getNome(char nome[]);

//a função a seguir deve retornar o seu número de GRR
unsigned int getGRR();

//função que troca os elementos
void swapElements(int* a, int* b);

//a busca sequencial é a busca ingênua
int buscaSequencial(int vetor[], int tam, int valor, int* numComparacoes);

//busca binária no vetor
int auxBuscaBinaria(int vetor[], int init, int tam, int valor, int* numComparacoes);

int buscaBinaria(int vetor[], int tam, int valor, int* numComparacoes);

//ordenação por insertion sort
int auxInsertionSort(int vetor[], int init, int tam);

int insertionSort(int vetor[], int tam);

//ordenação por selection sort
int auxSelectionSort(int vetor[], int i, int tam, int* contComp);

int selectionSort(int vetor[], int tam);

//ordenação por merge sort
int auxMergeSort(int vetor[], int init, int tam);

int copy(int vetor[], int vetorAux[], int init, int tam);

int merge(int vetor[], int init, int mid, int tam);

int mergeSort(int vetor[], int tam);

//ordenação por quick sort
int partition(int vetor[], int init, int tam, int* contElementos);

int auxQuickSort(int vetor[], int init, int tam, int* contElementos);

int quickSort(int vetor[], int tam);

//ordenação por heap sort
int maxHepify(int vetor[], int init, int tam);

int heapSort(int vetor[], int tam);

#endif // ORDENACAO_H_