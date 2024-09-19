typedef struct tipoAbsDados {
    int num;
    int den;
} racional;

racional leRacional(racional racionalLido);

int validaRacional(racional fracao);

int imprimeRacional(racional fracao);

int calculaMDC (int valorUm, int valorDois);

racional simplificaRacional(racional racionalResultante, racional racionalSimplificada);

int somaFracao(racional racional1, racional racional2, racional racionalResultante, racional racionalSimplificada);

int subtraiFracao(racional racional1, racional racional2, racional racionalResultante, racional racionalSimplificada);

int multiplicaFracao(racional racional1, racional racional2, racional racionalResultante, racional racionalSimplificada);

int divideFracao(racional racional1, racional racional2, racional racionalResultante, racional racionalSimplificada);