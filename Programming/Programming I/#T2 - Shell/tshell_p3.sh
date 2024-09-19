#!/bin/bash

if [ $# -ne 2 ]; then
	echo "chamada do script deve conter ./tshell_p3.sh <arquivo xml> <arquivo csv>"
	exit 1
fi

axml=$1
if [ ! -f $axml ]; then
	echo "erro: arquivo $axml não existe"
	exit 2
else
    axml=$(realpath $axml)
fi

acsv=$2
acsv=$(realpath $acsv)

#etapa de limpeza
#xgrep /home/soft/xgrep/bin/xgrep
#xgrep -t -x "//PMID|//ArticleTitle|//Abstract|//KeywordList"

#convercao do arquivo xml para csv com xmlstarlet
xmlstarlet sel -t -m -v "concat(@*, ',', *)" -n "${axml%.gz}" > "${axml%.xml.gz}.csv"

#retorna prompt do usuario no mesmo diretorio em que estava ao executa-lo
cd -