#include <stdio.h>
#include "lib_racionais.h"

int main() {
    racional primeiroRacional, segundoRacional, racionalResultante, racionalSimplificada;
    primeiroRacional = leRacional(primeiroRacional);
    validaRacional(primeiroRacional);
    if (primeiroRacional.den != 0) {
        segundoRacional = leRacional(segundoRacional);
        validaRacional(segundoRacional);
        while (primeiroRacional.den != 0 && segundoRacional.den != 0) {
            somaFracao(primeiroRacional, segundoRacional, racionalResultante, racionalSimplificada);
            subtraiFracao(primeiroRacional, segundoRacional, racionalResultante, racionalSimplificada);
            multiplicaFracao(primeiroRacional, segundoRacional, racionalResultante, racionalSimplificada);
            divideFracao(primeiroRacional, segundoRacional, racionalResultante, racionalSimplificada);
            primeiroRacional = leRacional(primeiroRacional);
            validaRacional(primeiroRacional);
            if (primeiroRacional.den != 0) {
                segundoRacional = leRacional(segundoRacional);
                validaRacional(segundoRacional);
            };    
        };
    };   
    return 0;
}