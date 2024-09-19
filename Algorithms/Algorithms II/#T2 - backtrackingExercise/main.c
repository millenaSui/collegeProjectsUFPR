#include<stdio.h>
#include<stdlib.h>
#include<string.h>

typedef struct caixa{
    int presente;
    int valor;
    int peso;
} caixa_t;

typedef struct saco{
    int pesoMax;
    int pesoTotal;
    int valorTotal;
    int nPresente;
    caixa_t **p;
} saco_t;

typedef struct fabrica{
    int nPresentes;
    saco_t *sacoNoel;
    saco_t *sacoAux;
    caixa_t **presentes;
} fabrica_t;



void incendio(fabrica_t *Fabrica){
    free (Fabrica->sacoNoel->p);
    free (Fabrica->sacoNoel);
    free (Fabrica->sacoAux->p);
    free (Fabrica->sacoAux);
    for (int i = 1; i <= Fabrica->nPresentes; i++)
        free (Fabrica->presentes[i]);
    free(Fabrica->presentes);
    free (Fabrica);
}

fabrica_t *criaFabrica(){
    fabrica_t *Fabrica;
    if (!(Fabrica = malloc(sizeof(fabrica_t))))
        return NULL;
    scanf("%d", &(Fabrica->nPresentes));
    if (!(Fabrica->presentes = malloc(sizeof(caixa_t) * (Fabrica->nPresentes + 1)))){
        free (Fabrica);
        return NULL;
    }
    if (!(Fabrica->sacoNoel = malloc(sizeof(saco_t)))){
        free (Fabrica->presentes);
        free (Fabrica);
        return NULL;
    }
    if (!(Fabrica->sacoNoel->p = malloc(sizeof(caixa_t) * ((Fabrica->nPresentes) + 1)))){
        free (Fabrica->sacoNoel);
        free (Fabrica->presentes);
        free (Fabrica);
        return NULL;
    }
    Fabrica->sacoNoel->pesoTotal = 0;
    Fabrica->sacoNoel->valorTotal = 0;
    Fabrica->sacoNoel->nPresente = 0;
    scanf("%d", &(Fabrica->sacoNoel->pesoMax));
    if (!(Fabrica->sacoAux = malloc(sizeof(saco_t)))){
        free (Fabrica->presentes);
        free (Fabrica->sacoNoel->p);
        free (Fabrica->sacoNoel);
        free (Fabrica);
        return NULL;
    }
    if (!(Fabrica->sacoAux->p = malloc(sizeof(caixa_t) * ((Fabrica->nPresentes) + 1)))){
        free (Fabrica->sacoAux);
        free (Fabrica->sacoNoel->p);
        free (Fabrica->sacoNoel);
        free (Fabrica->presentes);
        free (Fabrica);
        return NULL;
    }
    Fabrica->sacoAux->pesoTotal = 0;
    Fabrica->sacoAux->valorTotal = 0;
    Fabrica->sacoAux->nPresente = 0;
    Fabrica->sacoAux->pesoMax = Fabrica->sacoNoel->pesoMax;
    return Fabrica;
}

void embrulhaPresentes(fabrica_t *Fabrica){
    for (int i = 1; i <= Fabrica->nPresentes; i++){
        Fabrica->presentes[i] = malloc(sizeof(caixa_t));
        Fabrica->presentes[i]->presente = i;
        scanf("%d %d", &(Fabrica->presentes[i]->valor), &(Fabrica->presentes[i]->peso));
    }
}

void tiraDoSaco(fabrica_t *Fabrica){
    Fabrica->sacoAux->pesoTotal -= Fabrica->sacoAux->p[Fabrica->sacoAux->nPresente]->peso;
    Fabrica->sacoAux->valorTotal -= Fabrica->sacoAux->p[Fabrica->sacoAux->nPresente]->valor;
    Fabrica->sacoAux->nPresente--;
}

void mostraSaco(fabrica_t *Fabrica){
    int i;
    for (i = 1; i < Fabrica->sacoNoel->nPresente; i++)
        printf("%d ", Fabrica->sacoNoel->p[i]->presente);
    printf("%d\n", Fabrica->sacoNoel->p[i]->presente);
    printf("%d\n", Fabrica->sacoNoel->valorTotal);
}

void separaPresentes(fabrica_t *Fabrica, int nPosicao, int nItem){
    if (nPosicao >= Fabrica->nPresentes)
        return;
    while (nItem <= Fabrica->nPresentes){
        Fabrica->sacoAux->p[nPosicao] = Fabrica->presentes[nItem];
        Fabrica->sacoAux->pesoTotal += Fabrica->presentes[nItem]->peso;
        Fabrica->sacoAux->valorTotal += Fabrica->presentes[nItem]->valor;
        Fabrica->sacoAux->nPresente += 1;
        if (Fabrica->sacoAux->pesoTotal <= Fabrica->sacoAux->pesoMax){
            if (Fabrica->sacoAux->valorTotal > Fabrica->sacoNoel->valorTotal){
                Fabrica->sacoNoel->valorTotal = Fabrica->sacoAux->valorTotal;
                Fabrica->sacoNoel->pesoTotal = Fabrica->sacoAux->pesoTotal;
                Fabrica->sacoNoel->nPresente = Fabrica->sacoAux->nPresente;
                for (int k = 1; k <= Fabrica->sacoNoel->nPresente; k++)
                    Fabrica->sacoNoel->p[k] = Fabrica->sacoAux->p[k];
            }
            separaPresentes(Fabrica, nPosicao+1, nItem+1);
        }
        tiraDoSaco(Fabrica);
        nItem++;
    }
}

int main(){
    int nPosicao = 1;
    int nItem = 1;
    fabrica_t *Fabrica;
    if (!(Fabrica = criaFabrica()))
        return -1;
    embrulhaPresentes(Fabrica);
    separaPresentes(Fabrica, nPosicao, nItem);
    mostraSaco(Fabrica);
    incendio(Fabrica);
    return 0;
}











