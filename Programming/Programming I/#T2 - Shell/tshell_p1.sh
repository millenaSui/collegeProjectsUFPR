#!/bin/bash

if [ $# -ne 3 ] || [ $# -ne 4 ] || [ $# -ne 5 ]; then
    echo "chamada do script deve conter ./tshell_p1.sh <diretorio origem> <diretorio destino> <termo1> [<termo2> [<termo3>]]"
    exit 1
fi

dpm=$1
if [ ! -d $dpm ]; then
    echo "erro: diretório $dpm não existe"
    exit 2
else
    dpm=$(realpath $dpm)
fi

dstn=$2
if [ ! -d $dstn ]; then
    mkdir $dstn
fi
dstn=$(realpath $dstn)

t1=$3
t2=$4
t3=$5

#para todo arquivo .csv presente no diretorio dpm, extrai a linha
#que contenha os termos primarios de busca fornecidos e direciona-a
#ao arquivo de mesmo nome no diretorio destino
cd $dpm
for arquivo in *.csv; 
do
        touch "$dstn/$arquivo"
        awk '(NR>1)' $arquivo |
        grep -iwF "$t1" >> $dstn/$arquivo
        grep -iwF "$t2" >> $dstn/$arquivo
        grep -iwF "$t3" >> $dstn/$arquivo
done

#retorna prompt do usuario no mesmo diretorio em que estava ao executa-lo
cd -