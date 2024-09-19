#include <stdio.h>
#include <stdlib.h>
#include <time.h>
#include <string.h>
#include "ordenacao.h"

int imprimeVetor(int tam, int *vetor) {
	for (int i = 0; i < tam; i++) {
		printf("%d ", vetor[i]);
	};
	printf("\n");
	return vetor[tam];
};

// funções de criação para os vetores de teste
int *criaAleatorio(int tam) {
	int *vetor;
	if ( !(vetor = malloc(tam * sizeof(int))) ) {
		return NULL;
	};
	for (int i = 0; i < tam; i++) {
		vetor[i] = (rand() % tam) + 1;
	};
	return vetor;
};

int *criaMetadeOrdenado (int tam) {
	int *vetor;
	if ( !(vetor = malloc(tam * sizeof(int))) ) {
		return NULL;
	};
	for (int i = 0; i < (tam/2) + 1; i++) {
		vetor[i] = i;
	};
	for (int i = (tam/2) + 1; i < tam; i++) {
		vetor[i] = (rand() % tam) + 1;
	};
	return vetor;
};

int *criaOrdenado (int tam) {
	int *vetor;
	if ( !(vetor = malloc(tam * sizeof(int))) ) {
		return NULL;
	};
	for (int i = 0; i < tam; i++) {
		vetor[i] = i;
	};
	return vetor;
};

int *criaOrdenadoReverso(int tam) {
	int *vetor;
	int aux = 0;
	if ( !(vetor = malloc(tam * sizeof(int))) ) {
		return NULL;
	};
	for (int i = tam; i < 1; i--) {
		vetor[i] = vetor[aux];
		aux++;
	};
	return vetor;
};
// fim das funções de criação

int main(){
	char nome[MAX_CHAR_NOME];
	int idxBusca;
	int valor;
	scanf("%d", &valor);
	int contComp = 0;
	int vetorGrande = 10000;
	int vetorMedio = 500;
	int vetorPequeno = 10;

	getNome(nome);
	printf("Trabalho de %s\n", nome);
	printf("GRR %u\n", getGRR());
	srand(time(0));

	// medir o tempo
	clock_t start, end;//variáveis do tipo clock_t
    double total;

	// alocação dos respectivos vetores
	int *aleatorio, *aleatorioC, *metadeOrdenado, *metadeOrdenadoC, *ordenado, *ordenadoC, *ordenadoReverso, *ordenadoReversoC;

	if ( !(aleatorio = criaAleatorio(vetorGrande)) ) {
		printf("Falha fatal. Impossível alocar memória.");
		return 1;
	}
	if ( !(aleatorioC = malloc(vetorGrande * sizeof(int))) ) {
		printf("Falha fatal. Impossível alocar memória.");
		return 1;
	}
	memcpy(aleatorioC, aleatorio, vetorGrande * sizeof(int));
	if ( !(metadeOrdenado = criaMetadeOrdenado(vetorGrande)) ) {
		printf("Falha fatal. Impossível alocar memória.");
		return 1;
	}
	if ( !(metadeOrdenadoC = malloc(vetorGrande * sizeof(int))) ) {
		printf("Falha fatal. Impossível alocar memória.");
		return 1;
	}
	memcpy(metadeOrdenadoC, metadeOrdenado, vetorGrande * sizeof(int));
	if ( !(ordenado = criaOrdenado(vetorGrande)) ) {
		printf("Falha fatal. Impossível alocar memória.");
		return 1;
	}
	if ( !(ordenadoC = malloc(vetorGrande * sizeof(int))) ) {
		printf("Falha fatal. Impossível alocar memória.");
		return 1;
	}
	memcpy(ordenadoC, ordenado, vetorGrande * sizeof(int));
	if ( !(ordenadoReverso = criaOrdenadoReverso(vetorGrande)) ) {
		printf("Falha fatal. Impossível alocar memória.");
		return 1;
	}
	if ( !(ordenadoReversoC = malloc(vetorGrande * sizeof(int))) ) {
		printf("Falha fatal. Impossível alocar memória.");
		return 1;
	}
	memcpy(ordenadoReversoC, ordenadoReverso, vetorGrande * sizeof(int));
	// fim da alocação

	//início dos testes
	printf("\n******************************************************\n\n  Resultados das buscas sequencial e binária para vetor pequeno ordenado:\n\n");
	start = clock();
	idxBusca = buscaSequencial(ordenadoC, vetorPequeno, valor, &contComp);
	end = clock();
	total = ((double)end - start)/CLOCKS_PER_SEC;
	printf("Busca Sequencial\nIndex Elemento > %d\nComparações > %d\nTempo > %f\n\n", idxBusca, contComp, total);
	start = clock();
	idxBusca = buscaBinaria(ordenadoC, vetorPequeno, valor, &contComp);
	end = clock();
	total = ((double)end - start)/CLOCKS_PER_SEC;
	printf("Busca Binária\nIndex Elemento > %d\nComparações > %d\nTempo > %f\n\n******************************************************\n\n", idxBusca, contComp, total);
	contComp = 0;

	printf("  Resultados das buscas sequencial e binária para vetor grande ordenado:\n\n");
	start = clock();
	idxBusca = buscaSequencial(ordenadoC, vetorGrande, valor, &contComp);
	end = clock();
	total = ((double)end - start)/CLOCKS_PER_SEC;
	printf("Busca Sequencial\nIndex Elemento > %d\nComparações > %d\nTempo > %f\n\n", idxBusca, contComp, total);
	start = clock();
	idxBusca = buscaBinaria(ordenadoC, vetorGrande, valor, &contComp);
	end = clock();
	total = ((double)end - start)/CLOCKS_PER_SEC;
	printf("Busca Binária\nIndex Elemento > %d\nComparações > %d\nTempo > %f\n\n******************************************************\n\n", idxBusca, contComp, total);
	contComp = 0;

	printf("  Vetor pequeno aleatório (espera-se desordenado):\n\n");

	start = clock();
	contComp = insertionSort(aleatorioC, vetorPequeno);
	end = clock();
	total = ((double)end - start)/CLOCKS_PER_SEC;
	printf("Insertion Sort\nComparações entre elementos > %d\nTempo para ordenar > %f\n\n", contComp, total);

	start = clock();
	contComp = selectionSort(aleatorioC, vetorPequeno);
	end = clock();
	total = ((double)end - start)/CLOCKS_PER_SEC;
	printf("Selection Sort\nComparações entre elementos > %d\nTempo para ordenar > %f\n\n", contComp, total);

	start = clock();
	contComp = mergeSort(aleatorioC, vetorPequeno);
	end = clock();
	total = ((double)end - start)/CLOCKS_PER_SEC;
	printf("Merge Sort\nComparações entre elementos > %d\nTempo para ordenar > %f\n\n", contComp, total);

	start = clock();
	contComp = quickSort(aleatorioC, vetorPequeno);
	end = clock();
	total = ((double)end - start)/CLOCKS_PER_SEC;
	printf("Quick Sort\nComparações entre elementos > %d\nTempo para ordenar> %f\n\n", contComp, total);

	start = clock();
	contComp = heapSort(aleatorioC, vetorPequeno);
	end = clock();
	total = ((double)end - start)/CLOCKS_PER_SEC;
	printf("Heap Sort\nComparações entre elementos > %d\nTempo para ordenar > %f\n\n******************************************************\n\n", contComp, total);

	memcpy(aleatorioC, aleatorio, vetorGrande * sizeof(int));

	printf("  Vetor grande aleatório (espera-se desordenado):\n\n");

	start = clock();
	contComp = insertionSort(aleatorioC, vetorGrande);
	end = clock();
	total = ((double)end - start)/CLOCKS_PER_SEC;
	printf("Insertion Sort\nComparações entre elementos > %d\nTempo para ordenar > %f\n\n", contComp, total);

	start = clock();
	contComp = selectionSort(aleatorioC, vetorGrande);
	end = clock();
	total = ((double)end - start)/CLOCKS_PER_SEC;
	printf("Selection Sort\nComparações entre elementos > %d\nTempo para ordenar > %f\n\n", contComp, total);

	start = clock();
	contComp = mergeSort(aleatorioC, vetorGrande);
	end = clock();
	total = ((double)end - start)/CLOCKS_PER_SEC;
	printf("Merge Sort\nComparações entre elementos > %d\nTempo para ordenar > %f\n\n", contComp, total);

	start = clock();
	contComp = quickSort(aleatorioC, vetorGrande);
	end = clock();
	total = ((double)end - start)/CLOCKS_PER_SEC;
	printf("Quick Sort\nComparações entre elementos > %d\nTempo para ordenar> %f\n\n", contComp, total);

	start = clock();
	contComp = heapSort(aleatorioC, vetorGrande);
	end = clock();
	total = ((double)end - start)/CLOCKS_PER_SEC;
	printf("Heap Sort\nComparações entre elementos > %d\nTempo para ordenar > %f\n\n******************************************************\n\n", contComp, total);

	memcpy(aleatorioC, aleatorio, vetorGrande * sizeof(int));


	printf("  Vetor médio metade ordenado:\n\n");

	start = clock();
	contComp = insertionSort(metadeOrdenadoC, vetorMedio);
	end = clock();
	total = ((double)end - start)/CLOCKS_PER_SEC;
	printf("Insertion Sort\nComparações entre elementos > %d\nTempo para ordenar > %f\n\n", contComp, total);

	start = clock();
	contComp = selectionSort(metadeOrdenadoC, vetorMedio);
	end = clock();
	total = ((double)end - start)/CLOCKS_PER_SEC;
	printf("Selection Sort\nComparações entre elementos > %d\nTempo para ordenar > %f\n\n", contComp, total);

	start = clock();
	contComp = mergeSort(metadeOrdenadoC, vetorMedio);
	end = clock();
	total = ((double)end - start)/CLOCKS_PER_SEC;
	printf("Merge Sort\nComparações entre elementos > %d\nTempo para ordenar > %f\n\n", contComp, total);

	start = clock();
	contComp = quickSort(metadeOrdenadoC, vetorMedio);
	end = clock();
	total = ((double)end - start)/CLOCKS_PER_SEC;
	printf("Quick Sort\nComparações entre elementos > %d\nTempo para ordenar> %f\n\n", contComp, total);

	start = clock();
	contComp = heapSort(metadeOrdenadoC, vetorMedio);
	end = clock();
	total = ((double)end - start)/CLOCKS_PER_SEC;
	printf("Heap Sort\nComparações entre elementos > %d\nTempo para ordenar > %f\n\n******************************************************\n\n", contComp, total);

	memcpy(metadeOrdenadoC, metadeOrdenado, vetorGrande * sizeof(int));


	printf("  Vetor médio ordenado:\n\n");

	start = clock();
	contComp = insertionSort(ordenadoC, vetorMedio);
	end = clock();
	total = ((double)end - start)/CLOCKS_PER_SEC;
	printf("Insertion Sort\nComparações entre elementos > %d\nTempo para ordenar >%f\n\n", contComp, total);

	start = clock();
	contComp = selectionSort(ordenadoC, vetorMedio);
	end = clock();
	total = ((double)end - start)/CLOCKS_PER_SEC;
	printf("Selection Sort\nComparações entre elementos > %d\nTempo para ordenar >%f\n\n", contComp, total);

	start = clock();
	contComp = mergeSort(ordenadoC, vetorMedio);
	end = clock();
	total = ((double)end - start)/CLOCKS_PER_SEC;
	printf("Merge Sort\nComparações entre elementos > %d\nTempo para ordenar >%f\n\n", contComp, total);

	start = clock();
	contComp = quickSort(ordenadoC, vetorMedio);
	end = clock();
	total = ((double)end - start)/CLOCKS_PER_SEC;
	printf("Quick Sort\nComparações entre elementos > %d\nTempo para ordenar>%f\n\n", contComp, total);

	start = clock();
	contComp = heapSort(ordenadoC, vetorMedio);
	end = clock();
	total = ((double)end - start)/CLOCKS_PER_SEC;
	printf("Heap Sort\nComparações entre elementos > %d\nTempo para ordenar >%f\n\n******************************************************\n\n", contComp, total);

	memcpy(ordenadoC, ordenado, vetorGrande * sizeof(int));


	printf("  Vetor médio ordenado de trás pra frente:\n\n");

	start = clock();
	contComp = insertionSort(ordenadoReversoC, vetorMedio);
	end = clock();
	total = ((double)end - start)/CLOCKS_PER_SEC;
	printf("Insertion Sort\nComparações entre elementos > %d\nTempo para ordenar >%f\n\n", contComp, total);

	start = clock();
	contComp = selectionSort(ordenadoReversoC, vetorMedio);
	end = clock();
	total = ((double)end - start)/CLOCKS_PER_SEC;
	printf("Selection Sort\nComparações entre elementos > %d\nTempo para ordenar >%f\n\n", contComp, total);

	start = clock();
	contComp = mergeSort(ordenadoReversoC, vetorMedio);
	end = clock();
	total = ((double)end - start)/CLOCKS_PER_SEC;
	printf("Merge Sort\nComparações entre elementos > %d\nTempo para ordenar >%f\n\n", contComp, total);

	start = clock();
	contComp = quickSort(ordenadoReversoC, vetorMedio);
	end = clock();
	total = ((double)end - start)/CLOCKS_PER_SEC;
	printf("Quick Sort\nComparações entre elementos > %d\nTempo para ordenar>%f\n\n", contComp, total);

	start = clock();
	contComp = heapSort(ordenadoReversoC, vetorMedio);
	end = clock();
	total = ((double)end - start)/CLOCKS_PER_SEC;
	printf("Heap Sort\nComparações entre elementos > %d\nTempo para ordenar >%f\n\n", contComp, total);

	memcpy(ordenadoReversoC, ordenadoReverso, vetorGrande * sizeof(int));
	// fim dos testes

	// início dos free dos vetores
	free(aleatorio);
	free(aleatorioC);
	free(metadeOrdenado);
	free(metadeOrdenadoC);
	free(ordenado);
	free(ordenadoC);
	free(ordenadoReverso);
	free(ordenadoReversoC);
	// fim dos free dos vetores

	return 0;
}