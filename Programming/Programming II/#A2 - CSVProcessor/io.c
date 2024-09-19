#include "io.h"
#include <stdlib.h>
#include <string.h>
#include <ctype.h>
#include <math.h>
#include <stdbool.h>

#define MAX_COLUNAS 128

/*Funções auxiliares*/
void mergeSort(char ***arr, int col, int l, int r) {
    if (l < r) {
        // Encontra o ponto médio
        int meio = l + (r - l) / 2;

        // Ordena as metades esquerda e direita
        mergeSort(arr, col, l, meio);
        mergeSort(arr, col, meio + 1, r);

        // Mescla as metades ordenadas
        merge(arr, col, l, meio, r);
    }
}

void merge(char ***arr, int col, int l, int m, int r) {
    int i, j, k;
    int n1 = m - l + 1;
    int n2 = r - m;

    // Arrays temporários
    char ***L = (char ***)malloc(n1 * sizeof(char **));
    char ***R = (char ***)malloc(n2 * sizeof(char **));

    // Copia os dados para os arrays temporários
    for (i = 0; i < n1; i++)
        L[i] = arr[l + i];
    for (j = 0; j < n2; j++)
        R[j] = arr[m + 1 + j];

    // Mescla os arrays temporários de volta para arr[l..r]
    i = 0;
    j = 0;
    k = l;
    while (i < n1 && j < n2) {
        if (strcmp(L[i][col], R[j][col]) <= 0) {
            arr[k] = L[i];
            i++;
        } else {
            arr[k] = R[j];
            j++;
        }
        k++;
    }

    while (i < n1) {
        arr[k] = L[i];
        i++;
        k++;
    }

    while (j < n2) {
        arr[k] = R[j];
        j++;
        k++;
    }

    free(L);
    free(R);
}

void mergeSortReverse(char ***arr, int coluna, int inicio, int fim) {
    if (inicio < fim) {
        int meio = (inicio + fim) / 2;
        mergeSortReverse(arr, coluna, inicio, meio);
        mergeSortReverse(arr, coluna, meio + 1, fim);
        mergeReverse(arr, coluna, inicio, meio, fim);
    }
}

void mergeReverse(char ***arr, int coluna, int inicio, int meio, int fim) {
    int n1 = meio - inicio + 1;
    int n2 = fim - meio;

    char ***L = (char ***)malloc(n1 * sizeof(char **));
    char ***R = (char ***)malloc(n2 * sizeof(char **));

    for (int i = 0; i < n1; i++)
        L[i] = arr[inicio + i];
    for (int j = 0; j < n2; j++)
        R[j] = arr[meio + 1 + j];

    int i = 0, j = 0, k = inicio;
    while (i < n1 && j < n2) {
        if (strcmp(L[i][coluna], R[j][coluna]) >= 0) {
            arr[k++] = L[i++];
        } else {
            arr[k++] = R[j++];
        }
    }

    while (i < n1) {
        arr[k++] = L[i++];
    }

    while (j < n2) {
        arr[k++] = R[j++];
    }

    free(L);
    free(R);
}

bool filtroDados(char **linha, int colunaFiltragem, char *filtro, float valor) {
    float valorRegistro = atof(linha[colunaFiltragem]);
    if ((strcmp(filtro, "==") == 0 && valorRegistro == valor) ||
        (strcmp(filtro, ">") == 0 && valorRegistro > valor) ||
        (strcmp(filtro, ">=") == 0 && valorRegistro >= valor) ||
        (strcmp(filtro, "<") == 0 && valorRegistro < valor) ||
        (strcmp(filtro, "<=") == 0 && valorRegistro <= valor) ||
        (strcmp(filtro, "!=") == 0 && valorRegistro != valor)) {
        return true;
    }
    return false;
}

int cmpfunc(const void *a, const void *b) {
    return (*(double *)a - *(double *)b);
}

int comparaStrings(const void *a, const void *b) {
    return strcmp(*(const char **)a, *(const char **)b);
}

void substituirPorMedia(CSVData *dadosCSV) {
    printf("Substituindo NaN pela média de cada coluna...\n\n");
    for (int j = 0; j < dadosCSV->colunas; j++) {
        if (ehNumerica(dadosCSV, j)) {
            double soma = 0.0;
            int cont = 0;
            for (int i = 0; i < dadosCSV->linhas; i++) {
                if (strcmp(dadosCSV->dados[i][j], "NaN") != 0) {
                    soma += atof(dadosCSV->dados[i][j]);
                    cont++;
                }
            }
            double media = cont > 0 ? soma / cont : 0.0;
            for (int i = 0; i < dadosCSV->linhas; i++) {
                if (strcmp(dadosCSV->dados[i][j], "NaN") == 0) {
                    sprintf(dadosCSV->dados[i][j], "%.2lf", media);
                }
            }
        }
    }
}

void substituirPorProximoValido(CSVData *dadosCSV) {
    printf("Substituindo NaN pelo próximo valor válido em cada coluna...\n\n");
    for (int j = 0; j < dadosCSV->colunas; j++) {
        for (int i = 0; i < dadosCSV->linhas; i++) {
            if (strcmp(dadosCSV->dados[i][j], "NaN") == 0) {
                for (int k = i + 1; k < dadosCSV->linhas; k++) {
                    if (strcmp(dadosCSV->dados[k][j], "NaN") != 0) {
                        strcpy(dadosCSV->dados[i][j], dadosCSV->dados[k][j]);
                        break;
                    }
                }
            }
        }
    }
}

void removerNaN(CSVData *dadosCSV) {
    printf("Removendo linhas que contenham NaN em alguma coluna...\n\n");
    int novaLinhas = 0;
    for (int i = 0; i < dadosCSV->linhas; i++) {
        int temNaN = 0;
        for (int j = 0; j < dadosCSV->colunas; j++) {
            if (strcmp(dadosCSV->dados[i][j], "NaN") == 0) {
                temNaN = 1;
                break;
            }
        }
        if (!temNaN) {
            if (i != novaLinhas) {
                for (int j = 0; j < dadosCSV->colunas; j++)
                    strcpy(dadosCSV->dados[novaLinhas][j], dadosCSV->dados[i][j]);
            }
            novaLinhas++;
        }
    }
    dadosCSV->linhas = novaLinhas;
}

int ehNumerica(CSVData *dadosCSV, int coluna) {
    for (int i = 0; i < dadosCSV->linhas; i++) {
        if (strcmp(dadosCSV->dados[i][coluna], "NaN") != 0) {
            char *endptr;
            strtod(dadosCSV->dados[i][coluna], &endptr);
            if (*endptr != '\0')
                return 0;
        }
    }
    return 1;
}

void substituiDadosOriginais(CSVData *dadosOriginais, CSVData *dadosCopia) {
    /*Libera a memória alocada para os dados originais*/
    for (int i = 0; i < dadosOriginais->linhas; i++) {
        for (int j = 0; j < dadosOriginais->colunas; j++)
            free(dadosOriginais->dados[i][j]);
        free(dadosOriginais->dados[i]);
    }
    free(dadosOriginais->dados);
    for (int i = 0; i < dadosOriginais->colunas; i++)
        free(dadosOriginais->variaveis[i]);
    free(dadosOriginais->variaveis);
    free(dadosOriginais->tamanhoMaxColuna);

    /*Atribui os dados da cópia aos dados originais*/
    dadosOriginais->linhas = dadosCopia->linhas;
    dadosOriginais->colunas = dadosCopia->colunas;
    dadosOriginais->tamanhoMaxColuna = malloc(dadosCopia->colunas * sizeof(int));
    memcpy(dadosOriginais->tamanhoMaxColuna, dadosCopia->tamanhoMaxColuna, dadosCopia->colunas * sizeof(int));

    dadosOriginais->variaveis = malloc(dadosCopia->colunas * sizeof(char *));
    for (int i = 0; i < dadosCopia->colunas; i++)
        dadosOriginais->variaveis[i] = strdup(dadosCopia->variaveis[i]);

    dadosOriginais->dados = malloc(dadosCopia->linhas * sizeof(char **));
    for (int i = 0; i < dadosCopia->linhas; i++) {
        dadosOriginais->dados[i] = malloc(dadosCopia->colunas * sizeof(char *));
        for (int j = 0; j < dadosCopia->colunas; j++)
            dadosOriginais->dados[i][j] = strdup(dadosCopia->dados[i][j]);
    }
}

/*Funções principais*/
CSVData *leCSV(FILE *csv, CSVData *dadosCSV) {
    char linha[TAM_LINHA];
    
    if (fgets(linha, TAM_LINHA, csv) == NULL) {
        fprintf(stderr, "Arquivo com formato de cabeçalho inválido\n");
        exit(EXIT_FAILURE);
    }

    /*Aloca memória e armazena variáveis*/
    dadosCSV->variaveis = (char**)malloc(MAX_COLUNAS * sizeof(char*));
    dadosCSV->colunas = 0;
    char *token = strtok(linha, ",");
    while (token != NULL && dadosCSV->colunas < MAX_COLUNAS) {
        dadosCSV->variaveis[dadosCSV->colunas] = strdup(token);
        
        int tamanho_atual = strlen(dadosCSV->variaveis[dadosCSV->colunas]);
        if (dadosCSV->variaveis[dadosCSV->colunas][tamanho_atual - 1] == '\n') {
            dadosCSV->variaveis[dadosCSV->colunas][tamanho_atual - 1] = '\0';
        }

        dadosCSV->colunas++;
        token = strtok(NULL, ",");
    }

    /*Inicializa tamanho de colunas*/
    dadosCSV->tamanhoMaxColuna = (int *)malloc(dadosCSV->colunas * sizeof(int));
    for (int i = 0; i < dadosCSV->colunas; i++)
        dadosCSV->tamanhoMaxColuna[i] = strlen(dadosCSV->variaveis[i]) + 2;

    dadosCSV->linhas = 0;
    dadosCSV->colunas = 0;

    /*Lê dados após cabeçalho*/
    rewind(csv);
    fgets(linha, TAM_LINHA, csv); // Pula o cabeçalho

    while (fgets(linha, TAM_LINHA, csv) != NULL) {
        dadosCSV->linhas++;

        /*Atualiza o tamanho máximo de cada coluna*/
        token = strtok(linha, ",");
        int contColunas = 0;
        while (token != NULL) {
            int tamanho_atual = strlen(token);
            if (tamanho_atual > dadosCSV->tamanhoMaxColuna[contColunas]) {
                dadosCSV->tamanhoMaxColuna[contColunas] = tamanho_atual + 2;
            }
            token = strtok(NULL, ",");
            contColunas++;
        }
        if (contColunas > dadosCSV->colunas) {
            dadosCSV->colunas = contColunas;
        }
    }

    /*Aloca memória para os dados*/
    dadosCSV->dados = (char ***)malloc(dadosCSV->linhas * sizeof(char **));
    for (int i = 0; i < dadosCSV->linhas; i++) {
        dadosCSV->dados[i] = (char **)malloc(dadosCSV->colunas * sizeof(char *));
        for (int j = 0; j < dadosCSV->colunas; j++) {
            dadosCSV->dados[i][j] = (char *)malloc(TAM_CAMPO * sizeof(char));
        }
    }

    /*Imprime dados e substitui vazios por NaN*/
    rewind(csv);
    fgets(linha, TAM_LINHA, csv);
    int contLinhas = 0;
    while (fgets(linha, TAM_LINHA, csv) != NULL) {
        char *posicao = linha;
        while ((posicao = strstr(posicao, ",,")) != NULL) {
            memmove(posicao + 4, posicao + 1, strlen(posicao + 1) + 1);
            posicao[1] = 'N';
            posicao[2] = 'a';
            posicao[3] = 'N';
            posicao += 3;
        }
        token = strtok(linha, ",");
        int contColunas = 0;
        while (token != NULL) {
            strcpy(dadosCSV->dados[contLinhas][contColunas], token);
            token = strtok(NULL, ",");
            contColunas++;
        }
        contLinhas++;
    }
    return dadosCSV;
}

void imprimeSumario(const CSVData *dadosCSV) {

    /*Analisa tipos de variáveis e os imprime*/
    for (int i = 0; i < dadosCSV->colunas; i++) {
        char *tokenData = dadosCSV->dados[0][i];
        char emptyField[4] = "NaN";
        
        if (isdigit(*tokenData) || (!isdigit(*tokenData) && strcmp(tokenData, emptyField) == 0))
            printf("%s [N]", dadosCSV->variaveis[i]);
        else
            printf("%s [S]", dadosCSV->variaveis[i]);
        printf("\n");
    }
    printf("%d variáveis encontradas\n", dadosCSV->colunas);
}

void imprimeDados(const CSVData *dadosCSV) {
    
    /*Imprime o cabeçalho das variáveis*/
    printf("%-10s", ""); 
    for (int j = 0; j < dadosCSV->colunas; j++) {
        printf("%*s", +dadosCSV->tamanhoMaxColuna[j], dadosCSV->variaveis[j]);
    }
    printf("\n");

    /*Para arquivos com mais de dez linhas*/
    if (dadosCSV->linhas > 10) {

        /*Imprime as cinco primeiras linhas*/
        for (int i = 0; i < 5 && i < dadosCSV->linhas; i++) {
            printf("%-10d", i);
            for (int j = 0; j < dadosCSV->colunas; j++) {
                printf("%*s", +dadosCSV->tamanhoMaxColuna[j], dadosCSV->dados[i][j]);
            }
        }

        /*Imprime as reticências divisoras*/
        printf("%-*s", 10, "...");
        for (int j = 0; j < dadosCSV->colunas; j++)
            printf("%*s", +dadosCSV->tamanhoMaxColuna[j], "...");
        printf("\n");

        /*Imprime as cinco últimas linhas*/
        for (int i = dadosCSV->linhas - 5; i < dadosCSV->linhas; i++) {
            if (i >= 0) {
                printf("%-10d", i);
                for (int j = 0; j < dadosCSV->colunas; j++) {
                    printf("%*s", +dadosCSV->tamanhoMaxColuna[j], dadosCSV->dados[i][j]);
                }
            }
        }
        
    } else {
        for (int i = 0; i < dadosCSV->linhas; i++) {
            printf("%-10d", i);
            for (int j = 0; j < dadosCSV->colunas; j++) {
                printf("%*s", +dadosCSV->tamanhoMaxColuna[j], dadosCSV->dados[i][j]);
            }
        }
    }
    printf("\n");
    printf("[%d rows x %d columns]\n", dadosCSV->linhas, dadosCSV->colunas);
}

void imprimeDadosFiltrados(const CSVData *dadosCSV) {
    /* Entrada do filtro, variável e valor */
    printf("Entre com a variável que deseja filtrar: ");
    char colunaFiltragem[TAM_CAMPO];
    scanf(" %[^\n]", colunaFiltragem);

    printf("Entre com o filtro desejado (por exemplo, '=', '>', '<', '>=', '<='): ");
    char filtro[3];
    scanf(" %[^\n]", filtro);

    printf("Entre com o valor do filtro: ");
    float valor;
    scanf("%f", &valor);

    int indiceColunaFiltragem = -1;
    for (int i = 0; i < dadosCSV->colunas; i++) {
        if (strcmp(dadosCSV->variaveis[i], colunaFiltragem) == 0) {
            indiceColunaFiltragem = i;
            break; // Saia do loop assim que encontrar a coluna
        }
    }
    
    if (indiceColunaFiltragem == -1) {
        printf("Nome da coluna não encontrado.\n");
        return;
    }

    /*Imprime as primeiras cinco linhas filtradas*/
    int linhasImpressasCont = 0;
    for (int i = 0; i < dadosCSV->linhas; i++) {
        if (filtroDados(dadosCSV->dados[i], indiceColunaFiltragem, filtro, valor)) {
            printf("%-10d", i);
            for (int j = 0; j < dadosCSV->colunas; j++)
                printf("%*s", +dadosCSV->tamanhoMaxColuna[j], dadosCSV->dados[i][j]);
            linhasImpressasCont++;
            if (linhasImpressasCont >= 5)
                break;
        }
    }
    if (dadosCSV->linhas > 10) {
        /*Imprime as reticências divisoras*/
        printf("%-*s", 10, "...");
        for (int j = 0; j < dadosCSV->colunas; j++)
            printf("%*s", +dadosCSV->tamanhoMaxColuna[j], "...");
        printf("\n");

        /*Imprime as cinco últimas linhas*/
        linhasImpressasCont = 0;
        for (int i = dadosCSV->linhas - 5; i < dadosCSV->linhas; i++) {
            if (filtroDados(dadosCSV->dados[i], indiceColunaFiltragem, filtro, valor)) {
                printf("%-10d", i);
                for (int j = 0; j < dadosCSV->colunas; j++)
                    printf("%*s", +dadosCSV->tamanhoMaxColuna[j], dadosCSV->dados[i][j]);
                linhasImpressasCont++;
            }
        }
    }
}

void descricaoDados(CSVData *dadosCSV) {
    char variavel[TAM_CAMPO];
    printf("Entre com a variável: ");
    scanf("%s", variavel);

    /*Busca índice da variável*/
    int indiceVariavel = -1;
    for (int i = 0; i < dadosCSV->colunas; i++) {
        if (strcmp(dadosCSV->variaveis[i], variavel) == 0) {
            indiceVariavel = i;
            break;
        }
    }

    if (indiceVariavel == -1) {
        printf("Variável não encontrada.\n");
        return;
    }

    /*Verifica incidência de variável numérica*/
    int variavelNumerica = 1; //Assume que é numérica por padrão
    for (int i = 0; i < dadosCSV->linhas; i++) {
        char *valor_str = dadosCSV->dados[i][indiceVariavel];
        for (int j = 0; valor_str[j] != '\0'; j++) {
            if (!isdigit(valor_str[j]) && valor_str[j] != '.') {
                variavelNumerica = 0;
                break;
            }
        }
    }

    if (variavelNumerica) {
        int contador = 0;
        double soma = 0.0;
        double somaQuadrados = 0.0;
        double minimo = atof(dadosCSV->dados[0][indiceVariavel]);
        double maximo = atof(dadosCSV->dados[0][indiceVariavel]);
        double *valoresUnicos = (double *)malloc(dadosCSV->linhas * sizeof(double)); // Para armazenar valores únicos
        int totalValoresUnicos = 0;

        // Percorre os dados da variável para calcular as estatísticas
        for (int i = 0; i < dadosCSV->linhas; i++) {
            char *valor_str = dadosCSV->dados[i][indiceVariavel];
            double valor = atof(valor_str);
            contador++;

            soma += valor;
            somaQuadrados += valor * valor;

            /*Calcula mínimo e máximo*/
            if (valor < minimo)
                minimo = valor;
            if (valor > maximo)
                maximo = valor;

            /*Capta valores únicos*/
            int encontrado = 0;
            for (int j = 0; j < totalValoresUnicos; j++) {
                if (valoresUnicos[j] == valor) {
                    encontrado = 1;
                    break;
                }
            }
            if (!encontrado) {
                valoresUnicos[totalValoresUnicos] = valor;
                totalValoresUnicos++;
            }
        }

        /*Ordena valores por quick sort*/
        qsort(valoresUnicos, totalValoresUnicos, sizeof(double), cmpfunc);

        /*Calcula média*/
        double media = soma / contador;

        /*Calcula desvio-padrão*/
        double variancia = (somaQuadrados / contador) - (media * media);
        double desvio_padrao = sqrt(variancia);

        /*Calcula mediana*/
        double mediana;
        int meio = contador / 2;
        if (contador % 2 == 0) {
            double valor_meio1 = atof(dadosCSV->dados[meio - 1][indiceVariavel]);
            double valor_meio2 = atof(dadosCSV->dados[meio][indiceVariavel]);
            mediana = (valor_meio1 + valor_meio2) / 2.0;
        } else
            mediana = atof(dadosCSV->dados[meio][indiceVariavel]);

        /*Calcula moda*/
        int modaCont = 0;
        double moda = 0;
        for (int i = 0; i < totalValoresUnicos; i++) {
            int count = 0;
            for (int j = 0; j < dadosCSV->linhas; j++) {
                double valor = atof(dadosCSV->dados[j][indiceVariavel]);
                if (valor == valoresUnicos[i])
                    count++;
            }
            if (count > modaCont) {
                modaCont = count;
                moda = valoresUnicos[i];
            }
        }

        /*Imprime estatísticas de variáveis numéricas*/
        printf("Contador: %d\n", contador);
        printf("Média: %.1lf\n", media);
        printf("Desvio: %.1lf\n", desvio_padrao);
        printf("Mediana: %.1lf\n", mediana);
        printf("Moda: %.1lf\n", moda);
        printf("Mínimo: %.1lf\n", minimo);
        printf("Máximo: %.1lf\n", maximo);
        printf("Valores únicos: [");
        for (int i = 0; i < totalValoresUnicos; i++) {
            printf("%.1lf", valoresUnicos[i]);
            if (i < totalValoresUnicos - 1)
                printf(", ");
        }
        printf("]\n");

        free(valoresUnicos);
    } else {
        /*Inicializa variáveis e conta dados*/
        int contador = 0;
        int modaCont = 0;
        char moda[TAM_CAMPO];
        char **valoresUnicos = (char **)malloc(dadosCSV->linhas * sizeof(char *)); // Para armazenar valores únicos
        int totalValoresUnicos = 0;
        for (int i = 0; i < dadosCSV->linhas; i++) {
            char *valor = dadosCSV->dados[i][indiceVariavel];
            contador++;

            /*Conta valores nulos*/
            int encontrado = 0;
            for (int j = 0; j < totalValoresUnicos; j++) {
                if (strcmp(valoresUnicos[j], valor) == 0) {
                    encontrado = 1;
                    break;
                }
            }
            if (!encontrado) {
                valoresUnicos[totalValoresUnicos] = valor;
                totalValoresUnicos++;
            }

            /*Calcula moda*/
            int count = 0;
            for (int j = 0; j < dadosCSV->linhas; j++) {
                if (strcmp(dadosCSV->dados[j][indiceVariavel], valor) == 0)
                    count++;
            }
            if (count > modaCont) {
                modaCont = count;
                strcpy(moda, valor);
            }
        }

        /*Imprime estatísticas de variáveis não-numéricas*/
        printf("Contador: %d\n", contador);
        printf("Moda: %s %d vezes\n", moda, modaCont);
        printf("Valores únicos: [");

        /*Ordena os valores únicos em ordem alfabética*/
        qsort(valoresUnicos, totalValoresUnicos, sizeof(char *), comparaStrings);

        for (int i = 0; i < totalValoresUnicos; i++) {
            printf("%s", valoresUnicos[i]);
            if (i < totalValoresUnicos - 1)
                printf(", ");
        }
        printf("]\n");

        free(valoresUnicos);
    }
}

void ordenaDados(CSVData *dadosCSV) {
    char ***copiaDados = (char ***)malloc(dadosCSV->linhas * sizeof(char **));
    for (int i = 0; i < dadosCSV->linhas; i++) {
        copiaDados[i] = (char **)malloc(dadosCSV->colunas * sizeof(char *));
        for (int j = 0; j < dadosCSV->colunas; j++)
            copiaDados[i][j] = strdup(dadosCSV->dados[i][j]);
    }

    char variavel[TAM_CAMPO];
    printf("Entre com a variável para ordenação: ");
    scanf("%s", variavel);

    int indiceVariavel = -1;
    for (int i = 0; i < dadosCSV->colunas; i++) {
        if (strcmp(dadosCSV->variaveis[i], variavel) == 0) {
            indiceVariavel = i;
            break;
        }
    }

    if (indiceVariavel == -1) {
        printf("Variável não encontrada.\n");

        // Libera a memória da cópia
        for (int i = 0; i < dadosCSV->linhas; i++) {
            for (int j = 0; j < dadosCSV->colunas; j++)
                free(copiaDados[i][j]);
            free(copiaDados[i]);
        }
        free(copiaDados);
        return;
    }

    /*Requisição de opção de ordenação ascendente ou descendente*/
    char opcaoOrdem;
    printf("Selecione uma opção [A]scendente ou [D]escrescente: ");
    scanf(" %c", &opcaoOrdem);
    
    /*Aplica o algoritmo de merge sort e imprime cabeçalho e dados ordenados*/
    if (opcaoOrdem == 'A' || opcaoOrdem == 'a')
        mergeSort(copiaDados, indiceVariavel, 0, dadosCSV->linhas - 1);
    else if (opcaoOrdem == 'D' || opcaoOrdem == 'd')
        mergeSortReverse(copiaDados, indiceVariavel, 0, dadosCSV->linhas - 1);
    else {
        printf("Opção inválida.\n");

        /*Libera a memória dos dados cópia*/
        for (int i = 0; i < dadosCSV->linhas; i++) {
            for (int j = 0; j < dadosCSV->colunas; j++)
                free(copiaDados[i][j]);
            free(copiaDados[i]);
        }
        free(copiaDados);
        return;
    }

    /*Imprime o cabeçalho das variáveis*/
    printf("%-10s", "");
    for (int j = 0; j < dadosCSV->colunas; j++)
        printf("%*s", +dadosCSV->tamanhoMaxColuna[j], dadosCSV->variaveis[j]);
    printf("\n");

    /*Imprime as cinco primeiras linhas*/
    int max_rows = (dadosCSV->linhas > 10) ? 5 : dadosCSV->linhas;
    for (int i = 0; i < max_rows; i++) {
        printf("%-10d", i);
        for (int j = 0; j < dadosCSV->colunas; j++)
            printf("%*s", +dadosCSV->tamanhoMaxColuna[j], copiaDados[i][j]);
    }

    /*Para arquivos com mais de dez linhas, imprime as cinco últimas*/
    if (dadosCSV->linhas > 10) {
        printf("%-*s", 10, "...");
        for (int j = 0; j < dadosCSV->colunas; j++)
            printf("%*s", +dadosCSV->tamanhoMaxColuna[j], "...");
        printf("\n");

        for (int i = dadosCSV->linhas - 5; i < dadosCSV->linhas; i++) {
            if (i >= 0) {
                printf("%-10d", i);
                for (int j = 0; j < dadosCSV->colunas; j++)
                    printf("%*s", +dadosCSV->tamanhoMaxColuna[j], copiaDados[i][j]);
            }
        }
    }
    printf("\n");
    printf("[%d rows x %d columns]\n", dadosCSV->linhas, dadosCSV->colunas);

    /*Verifica gravação de dados em arquivo alternativo*/
    char opcao;
    printf("Deseja gravar um arquivo com os dados ordenados? [S|N] ");
    scanf(" %c", &opcao);
    if (opcao == 'S' || opcao == 's') {
        char nomeArquivo[TAM_CAMPO];
        printf("Entre com o nome do arquivo: ");
        scanf("%s", nomeArquivo);

        FILE *arquivo = fopen(nomeArquivo, "w");
        if (arquivo == NULL) {
            printf("Erro ao abrir o arquivo.\n");
            
            /*Libera a memória dos dados cópia*/
            for (int i = 0; i < dadosCSV->linhas; i++) {
                for (int j = 0; j < dadosCSV->colunas; j++)
                    free(copiaDados[i][j]);
                free(copiaDados[i]);
            }
            free(copiaDados);
            return;
        }

        /*Grava dados no arquivo solicitado*/
        for (int i = 0; i < dadosCSV->linhas; i++) {
            for (int j = 0; j < dadosCSV->colunas; j++) {
                fprintf(arquivo, "%s", copiaDados[i][j]);
                if (j < dadosCSV->colunas - 1)
                    fprintf(arquivo, ",");
            }
            fprintf(arquivo, "\n");
        }
        fclose(arquivo);
        printf("Arquivo gravado com sucesso.\n");
    }

    /*Verifica intenção de substituição de dados pós alteração*/
    printf("Deseja descartar os dados originais? [S|N]: ");
    scanf(" %c", &opcao);
    if (opcao == 'S' || opcao == 's') {
        for (int i = 0; i < dadosCSV->linhas; i++) {
            for (int j = 0; j < dadosCSV->colunas; j++) {
                free(dadosCSV->dados[i][j]);
                dadosCSV->dados[i][j] = strdup(copiaDados[i][j]);
            }
        }
        printf("Dados originais descartados.\n");
    }

    /*Libera a memória dos dados cópia*/
    for (int i = 0; i < dadosCSV->linhas; i++) {
        for (int j = 0; j < dadosCSV->colunas; j++)
            free(copiaDados[i][j]);
        free(copiaDados[i]);
    }
    free(copiaDados);
}

void selecionaVariaveis(CSVData *dadosCSV) {
    char variaveisSelecionadas[MAX_COLUNAS][TAM_CAMPO];
    int numVariaveisSelecionadas = 0;

    /*Requisição das variáveis para seleção*/
    printf("Entre com as variáveis que deseja selecionar (separadas por espaço): ");
    char entradaTemporaria[TAM_CAMPO];
    scanf(" %[^\n]", entradaTemporaria);
    char *token = strtok(entradaTemporaria, " ");
    while (token != NULL && numVariaveisSelecionadas < MAX_COLUNAS) {
        strcpy(variaveisSelecionadas[numVariaveisSelecionadas], token);
        token = strtok(NULL, " ");
        numVariaveisSelecionadas++;
    }

    /*Verifica se o número de variáveis selecionadas é maior que o limite*/
    if (numVariaveisSelecionadas == MAX_COLUNAS) {
        printf("Número máximo de variáveis atingido. Limite é %d.\n", MAX_COLUNAS);
        return;
    }

    /*Encontra os índices das variáveis selecionadas no cabeçalho original*/
    int indices_selecionados[MAX_COLUNAS];
    for (int i = 0; i < numVariaveisSelecionadas; i++) {
        int indice = -1;
        for (int j = 0; j < dadosCSV->colunas; j++) {
            if (strcmp(dadosCSV->variaveis[j], variaveisSelecionadas[i]) == 0) {
                indice = j;
                break;
            }
        }
        if (indice == -1) {
            printf("Variável %s não encontrada.\n", variaveisSelecionadas[i]);
            return;
        }
        indices_selecionados[i] = indice;
    }

    /*Imprime o cabeçalho das variáveis*/
    printf("%-10s", ""); // Espaço para o índice das linhas
    for (int i = 0; i < numVariaveisSelecionadas; i++)
        printf("%*s", +dadosCSV->tamanhoMaxColuna[i], variaveisSelecionadas[i]);
    printf("\n");

    /*Para arquivos com mais de dez linhas*/
    if (dadosCSV->linhas > 10) {

        /*Imprime as cinco primeiras linhas*/
        for (int i = 0; i < 5; i++) {
            printf("%-10d", i);
            for (int j = 0; j < numVariaveisSelecionadas; j++)
                printf("%*s", +dadosCSV->tamanhoMaxColuna[j], dadosCSV->dados[i][indices_selecionados[j]]);
            printf("\n");
        }

        /*Imprime as reticências divisoras*/
        printf("%-*s", 10, "...");
        for (int i = 0; i < numVariaveisSelecionadas; i++) {
            printf("%*s", +dadosCSV->tamanhoMaxColuna[i], "...");
        }
        printf("\n");

        /*Imprime as cinco últimas linhas*/
        for (int i = dadosCSV->linhas - 5; i < dadosCSV->linhas; i++) {
            if (i >= 0) {
                printf("%-10d", i);
                for (int j = 0; j < numVariaveisSelecionadas; j++)
                    printf("%*s", +dadosCSV->tamanhoMaxColuna[j], dadosCSV->dados[i][indices_selecionados[j]]);
            }
            printf("\n");
        }
    } else {
        for (int i = 0; i < dadosCSV->linhas; i++) {
            printf("%-10d", i);
            for (int j = 0; j < numVariaveisSelecionadas; j++)
                printf("%*s", +dadosCSV->tamanhoMaxColuna[j], dadosCSV->dados[i][indices_selecionados[j]]);
            printf("\n");
        }
    }
    printf("\n[%d rows x %d columns]\n", dadosCSV->linhas, dadosCSV->colunas);

    /*Verifica gravação de dados em arquivo alternativo*/
    char opcao;
    printf("Deseja gravar um arquivo com as variáveis selecionadas? [S|N] ");
    scanf(" %c", &opcao);
    if (opcao == 'S' || opcao == 's') {
        char nomeArquivo[TAM_CAMPO];
        printf("Entre com o nome do arquivo: ");
        scanf("%s", nomeArquivo);

        FILE *arquivo = fopen(nomeArquivo, "w");
        if (arquivo == NULL) {
            printf("Erro ao abrir o arquivo.\n");
            return;
        }

        /*Grava os dados no arquivo solicitado*/
        for (int i = 0; i < dadosCSV->linhas; i++) {
            for (int j = 0; j < numVariaveisSelecionadas; j++) {
                fprintf(arquivo, "%s", dadosCSV->dados[i][indices_selecionados[j]]);
                if (j < numVariaveisSelecionadas - 1) {
                    fprintf(arquivo, ",");
                }
            }
            fprintf(arquivo, "\n");
        }

        fclose(arquivo);
        printf("Arquivo gravado com sucesso.\n");
    }
}

void dadosFaltantes(CSVData *dadosCSV) {

    /*Cria cópia dos dados CSV*/
    CSVData dadosCSVcopia;
    dadosCSVcopia.linhas = dadosCSV->linhas;
    dadosCSVcopia.colunas = dadosCSV->colunas;

    dadosCSVcopia.tamanhoMaxColuna = malloc(dadosCSV->colunas * sizeof(int));
    memcpy(dadosCSVcopia.tamanhoMaxColuna, dadosCSV->tamanhoMaxColuna, dadosCSV->colunas * sizeof(int));

    dadosCSVcopia.variaveis = malloc(dadosCSV->colunas * sizeof(char *));
    for (int i = 0; i < dadosCSV->colunas; i++)
        dadosCSVcopia.variaveis[i] = strdup(dadosCSV->variaveis[i]);

    dadosCSVcopia.dados = malloc(dadosCSV->linhas * sizeof(char **));
    for (int i = 0; i < dadosCSV->linhas; i++) {
        dadosCSVcopia.dados[i] = malloc(dadosCSV->colunas * sizeof(char *));
        for (int j = 0; j < dadosCSV->colunas; j++)
            dadosCSVcopia.dados[i][j] = strdup(dadosCSV->dados[i][j]);
    }

    int opcao;
    do {
        printf("1) Listar registros com NaN\n");
        printf("2) Substituir por média\n");
        printf("3) Substituir pelo próximo valor válido\n");
        printf("4) Remover registros com NaN\n");
        printf("5) Voltar ao menu principal\n");
        printf("Entre com a opção desejada: ");
        scanf("%d", &opcao);

        switch (opcao) {
            case 1:
                int hasNaN = 0;
                int contLinhasNaN = 0;

                for (int j = 0; j < dadosCSVcopia.colunas; j++)
                    printf("%*s", +dadosCSVcopia.tamanhoMaxColuna[j], dadosCSVcopia.variaveis[j]);
                printf("\n");

                /*Verifica a existência de registros NaN*/
                for (int i = 0; i < dadosCSVcopia.linhas && contLinhasNaN < 5; i++) {
                    for (int j = 0; j < dadosCSVcopia.colunas; j++) {
                        if (strcmp(dadosCSVcopia.dados[i][j], "NaN") == 0) {
                            hasNaN = 1;
                            break;
                        }
                    }
                    
                    /*Se houverem registros NaN, os imprime*/
                    if (hasNaN && contLinhasNaN < 5) {
                        for (int j = 0; j < dadosCSVcopia.colunas; j++)
                            printf("%*s", +dadosCSVcopia.tamanhoMaxColuna[j], dadosCSVcopia.dados[i][j]);
                        hasNaN = 0; //Reseta a flag para verificar a próxima linha
                        contLinhasNaN++;
                    }
                }
                /*Se houverem mais de dez linhas, verifica qual delas tem NaN e as imprime*/
                if (dadosCSVcopia.linhas > 10 && contLinhasNaN == 5) {
                    contLinhasNaN = 0;
                    for (int j = 0; j < dadosCSVcopia.colunas; j++)
                        printf("%*s", +dadosCSVcopia.tamanhoMaxColuna[j], "...");
                    printf("\n");                    
                    for (int i = dadosCSVcopia.linhas - 1; i >= 0 && contLinhasNaN < 5; i--) {
                        for (int j = 0; j < dadosCSVcopia.colunas; j++) {
                            if (strcmp(dadosCSVcopia.dados[i][j], "NaN") == 0) {
                                hasNaN = 1;
                                break;
                            }
                        }
                        if (hasNaN) {
                            for (int j = 0; j < dadosCSVcopia.colunas; j++)
                                printf("%*s", +dadosCSVcopia.tamanhoMaxColuna[j], dadosCSVcopia.dados[i][j]);
                            hasNaN = 0; //Reseta a flag para verificar a próxima linha
                            contLinhasNaN++;
                        }
                    }
                }
                printf("\n");
                printf("[%d rows x %d columns]\n\n", dadosCSVcopia.linhas, dadosCSVcopia.colunas);
                break;
            case 2:
                substituirPorMedia(&dadosCSVcopia);
                break;
            case 3:
                substituirPorProximoValido(&dadosCSVcopia);
                break;
            case 4:
                removerNaN(&dadosCSVcopia);
                break;
            case 5:
                /*Verifica intenção de substituição de dados pós alteração*/
                char resposta;
                printf("\nDeseja descartar os dados originais? [S|N]: ");
                scanf("\n%c", &resposta);
                if (resposta == 'S' || resposta == 's') {
                    substituiDadosOriginais(dadosCSV, &dadosCSVcopia);
                    printf("Dados originais descartados!\n");
                }
                printf("Retornando ao menu principal...\n");
                break;
            default:
                printf("Opção inválida. Tente novamente.\n");
        }
    } while (opcao != 5);

    /*Libera memória alocada para a cópia dos dados CSV*/
    for (int i = 0; i < dadosCSVcopia.linhas; i++) {
        for (int j = 0; j < dadosCSVcopia.colunas; j++)
            free(dadosCSVcopia.dados[i][j]);
        free(dadosCSVcopia.dados[i]);
    }
    free(dadosCSVcopia.dados);

    for (int i = 0; i < dadosCSVcopia.colunas; i++)
        free(dadosCSVcopia.variaveis[i]);

    free(dadosCSVcopia.variaveis);
    free(dadosCSVcopia.tamanhoMaxColuna);
}

void salvarDados(CSVData *dadosCSV) {
    char nomeArquivo[100];
    printf("Entre com o nome do arquivo: ");
    scanf("%s", nomeArquivo);
    
    FILE *arquivo = fopen(nomeArquivo, "w");
    if (arquivo == NULL) {
        printf("Erro ao abrir o arquivo.\n");
        return;
    }

    /*Grava as variáveis no arquivo solicitado*/
    for (int i = 0; i < dadosCSV->colunas; i++) {
        fprintf(arquivo, "%s", dadosCSV->variaveis[i]);
        if (i < dadosCSV->colunas - 1)
            fprintf(arquivo, ",");
    }
    fprintf(arquivo, "\n");

    /*Grava os dados no arquivo solicitado*/
    for (int i = 0; i < dadosCSV->linhas; i++) {
        for (int j = 0; j < dadosCSV->colunas; j++) {
            fprintf(arquivo, "%s", dadosCSV->dados[i][j]);
            if (j < dadosCSV->colunas - 1)
                fprintf(arquivo, ",");
        }
    }
    fclose(arquivo);
    printf("Arquivo gravado com sucesso.\n");
}

void freeCSV(CSVData *dadosCSV) {
    free(dadosCSV->variaveis);
    for (int i = 0; i < dadosCSV->linhas; i++) {
        for (int j = 0; j < dadosCSV->colunas; j++)
            free(dadosCSV->dados[i][j]);
        free(dadosCSV->dados[i]);
    }
    free(dadosCSV->dados);
}