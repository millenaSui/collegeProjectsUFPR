#ifndef IO_H
#define IO_H

#include <stdio.h>

#define TAM_LINHA 1024
#define TAM_CAMPO 128
#define TAM_IMPRESSAO 25

/*Definição da estrutura principal*/
typedef struct {
    char ***dados;
    int linhas;
    int colunas;
    char **variaveis;
    int *tamanhoMaxColuna;
} CSVData;

/*Funções auxiliares*/
void mergeSort(char ***arr, int col, int l, int r);
void merge(char ***arr, int col, int l, int m, int r);
void mergeSortReverse(char ***arr, int coluna, int inicio, int fim);
void mergeReverse(char ***arr, int coluna, int inicio, int meio, int fim);
void substituirPorMedia(CSVData *dadosCSV);
void substituirPorProximoValido(CSVData *dadosCSV);
void removerNaN(CSVData *dadosCSV);
int ehNumerica(CSVData *dadosCSV, int coluna);
void substituiDadosOriginais(CSVData *dadosOriginais, CSVData *dadosCopia);

/*Funções principais*/
CSVData *leCSV(FILE *csv, CSVData *dadosCSV);
void imprimeSumario(const CSVData *dadosCSV);
void imprimeDados(const CSVData *dadosCSV);
void imprimeDadosFiltrados(const CSVData *dadosCSV);
void descricaoDados(CSVData *dadosCSV);
void selecionaVariaveis(CSVData *dadosCSV);
void ordenaDados(CSVData *dadosCSV);
void dadosFaltantes(CSVData *dadosCSV);
void salvarDados(CSVData *dadosCSV);
void freeCSV(CSVData *dadosCSV);

#endif
