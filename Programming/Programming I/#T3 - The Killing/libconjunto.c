#include <stdio.h>
#include <stdlib.h>
#include "libconjunto.h"

conjunto_t *cria_cjt(int max) {
    conjunto_t *c;
    if (!(c = malloc(sizeof(conjunto_t)))) {
        destroi_cjt(c);
    }
    (*c).max = max;
    (*c).card = 0;
    (*c).ptr = 0;
    if (!((*c).v = malloc(sizeof(int) * max))) {
        destroi_cjt(c);
    }
    return c;
}

conjunto_t *destroi_cjt(conjunto_t *c) {
    free((*c).v);
    (*c).v = NULL;
    free(c);
    return NULL;
}

int vazio_cjt(conjunto_t *c) {
    return cardinalidade_cjt(c) == 0;    
}

int cardinalidade_cjt(conjunto_t *c) {
    return (*c).card;
}

int insere_cjt(conjunto_t *c, int elemento) {
    int i;
    if (cardinalidade_cjt(c) == (*c).max)
        return 0;
    if (pertence_cjt(c, elemento))
        return 1;
    i = cardinalidade_cjt(c);
    while (i > 0 && (*c).v[i - 1] > elemento) {
        (*c).v[i] = (*c).v[i - 1];
        i--;
    }
    (*c).v[i] = elemento;
    (*c).card++;
    return 1;
}

int busca_binaria(conjunto_t *c, int elemento) {
    int inicio = 0;
    int fim = cardinalidade_cjt(c) - 1;
    int meio = fim / 2;

    while (inicio <= fim && (*c).v[meio] != elemento) {
        if (elemento > (*c).v[meio])
            inicio = meio + 1;
        else
            fim = meio - 1;
        meio = (inicio + fim) / 2;
    }
    if (inicio > fim)
        return -1;
    else
        return meio;
}

int retira_cjt(conjunto_t *c, int elemento) {
    int i;    
    if (!(pertence_cjt(c, elemento)))
        return 0;

    for (i = busca_binaria(c, elemento); i < cardinalidade_cjt(c) - 1; i++) {
        (*c).v[i] = (*c).v[i + 1];
    }
    (*c).card--;
    return 1;
}

int pertence_cjt(conjunto_t *c, int elemento) {
    return busca_binaria(c, elemento) != -1;
}

int contido_cjt(conjunto_t *c1, conjunto_t *c2) {
    int i;
    for (i = 0; i < cardinalidade_cjt(c1); i++) {
        if (!(pertence_cjt(c2, (*c1).v[i])))
            return 0;
    }
    return 1;
}

int sao_iguais_cjt(conjunto_t *c1, conjunto_t *c2) {
    if (cardinalidade_cjt(c1) == cardinalidade_cjt(c2) && contido_cjt(c1, c2))
        return 1;
    return 0;
}

conjunto_t *diferenca_cjt(conjunto_t *c1, conjunto_t *c2) {
    int i;
    conjunto_t *cjt_diferenca;
    if (!(cjt_diferenca = cria_cjt((*c1).max)))
        return NULL;
    for (i = 0; i < cardinalidade_cjt(c1); i++) {
        if (!(pertence_cjt(c2, (*c1).v[i])))
            insere_cjt(cjt_diferenca, (*c1).v[i]);
    }
    return cjt_diferenca;
}

conjunto_t *interseccao_cjt(conjunto_t *c1, conjunto_t *c2) {
    int i;
    conjunto_t *cjt_interseccao, *maior_cjt, *menor_cjt;
    if (!(cjt_interseccao = cria_cjt((*c1).max)))
        return NULL;
    if (cardinalidade_cjt(c1) > cardinalidade_cjt(c2)) {
        maior_cjt = c1;
        menor_cjt = c2;
    } else {
        maior_cjt = c2;
        menor_cjt = c1;
    }
    for (i = 0; i < cardinalidade_cjt(menor_cjt); i++) {
        if (pertence_cjt(maior_cjt, (*menor_cjt).v[i]))
            insere_cjt(cjt_interseccao, (*menor_cjt).v[i]);
    }
    return cjt_interseccao;
}

conjunto_t *uniao_cjt(conjunto_t *c1, conjunto_t *c2) {
    conjunto_t *cjt_uniao;
    if (!(cjt_uniao = cria_cjt((*c1).max)))
        return NULL;

    inicia_iterador_cjt(c1);
    inicia_iterador_cjt(c2);

    /*Se vetores C1 e C2 não chegaram no final*/
    while ((*c1).ptr < cardinalidade_cjt(c1) && (*c2).ptr < cardinalidade_cjt(c2))
    {
        /* Se elemento de C1 é menor que elemento de C2
           Insere elemento de C1 no vetor uniao*/
        if ((*c1).v[(*c1).ptr] < (*c2).v[(*c2).ptr] || (*c2).ptr == cardinalidade_cjt(c2)-1) {
            insere_cjt(cjt_uniao, (*c1).v[(*c1).ptr]);
            (*c1).ptr++;

            /* Se elemento de C2 é menor que elemento de C1
               Insere elemento de C2 no vetor uniao*/
        } else if ((*c1).v[(*c1).ptr] >= (*c2).v[(*c2).ptr] || (*c1).ptr == cardinalidade_cjt(c1)-1) {
            insere_cjt(cjt_uniao, (*c2).v[(*c2).ptr]);
            (*c2).ptr++;
        }
    }
    inicia_iterador_cjt(c1);
    inicia_iterador_cjt(c2);
    return cjt_uniao;
}

conjunto_t *copia_cjt(conjunto_t *c) {
    int i;
    conjunto_t *cjt_copia;
    if (!(cjt_copia = cria_cjt((*c).max)))
        return NULL;
    for (i = 0; i < cardinalidade_cjt(c); i++) {
        insere_cjt(cjt_copia, (*c).v[i]);
    }
    return cjt_copia;
}

conjunto_t *cria_subcjt_cjt(conjunto_t *c, int n) {
    int aleat;
    int i;
    if (n >= cardinalidade_cjt(c))
        return copia_cjt(c);
    conjunto_t *subcjt;
    if (!(subcjt = cria_cjt((*c).max)))
        return NULL;
    conjunto_t *disponiveis;
    if (!(disponiveis = copia_cjt(c))) {
        free(subcjt);
        return NULL;
    }
    for (i = 0; i < n; i++) {
        aleat = rand() % cardinalidade_cjt(disponiveis);
        insere_cjt(subcjt, (*disponiveis).v[aleat]);
        retira_cjt(disponiveis, (*disponiveis).v[aleat]);
    }
    if (vazio_cjt(subcjt)) {
        printf("subconjunto vazio\n");
        return 0;
    }
    destroi_cjt(disponiveis);
    return subcjt;
}

void imprime_cjt(conjunto_t *c) {
    int i;
    if (vazio_cjt(c)) {
        printf("conjunto vazio\n");
        return;
    }
    printf("%d", (*c).v[0]);
    for (i = 1; i < cardinalidade_cjt(c); i++) {
        printf(" %d", (*c).v[i]);
    }
    printf("\n");
}

void inicia_iterador_cjt(conjunto_t *c) {
    (*c).ptr = 0;
}

int incrementa_iterador_cjt(conjunto_t *c, int *ret_iterador) {
    if ((*c).ptr > cardinalidade_cjt(c))
        return 0;    
    *ret_iterador = (*c).v[(*c).ptr];
    (*c).ptr++;
    return 1;
}

int retira_um_elemento_cjt(conjunto_t *c) {
    int aleat = rand() % cardinalidade_cjt(c);
    int elemento = (*c).v[aleat];
    int i;
    for (i = aleat; i < cardinalidade_cjt(c)-1; i++) {
        (*c).v[i] = (*c).v[i + 1];
    }
    (*c).card--;
    return elemento;
}