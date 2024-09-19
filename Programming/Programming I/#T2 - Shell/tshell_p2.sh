#!/bin/bash

if [ $# -ne 2 ]; then
    echo "chamada do script deve conter ./tshell_p2.sh <diretorio com arquivos csv> <diretorio de tabelas">
    exit 1
fi

dcsv=$1
dsin=$2

existe_diretorio() {
    if [ ! -d $1 ]; then
        echo "erro: diretório $dcsv não existe"
        exit 2
    fi  
}

if [ existe_diretorio $dcsv ]; then
    dcsv=$(realpath $dcsv)
fi

if [ existe_diretorio $dsin ]; then
    dsin=$(realpath $dsin)
fi

#gera como saida uma lista em ordem lexicografica dos fatores de transcricao
#o numero de artigos encontrados que contem algum dos sinonimos do fator
cd $dsin
for fattrans in *; 
do
        num_artigos=$(grep -iwF -f $fattrans $dcsv/*.csv | wc -l)
        echo "${fattrans%.*}:$num_artigos"
done

#retorna prompt do usuario no mesmo diretorio em que estava ao executa-lo
cd -