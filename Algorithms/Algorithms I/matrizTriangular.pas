program matrizTriangular;
var n, numLinhas, numColunas: longint;
type matrizM = array [1..100, 1..100] of integer;
var matriz: matrizM;

procedure leMatriz (var m: matrizM; var contLinhas: longint; var contColunas: longint);
var i, j: longint;

begin
    for i := 1 to contLinhas do
    begin
        for j := 1 to contColunas do
        begin
            read (m[i,j]);
        end;
    end;
end;

function verificaMatrizTriangInf (var m: matrizM; var contLinhas: longint; var contColunas: longint): boolean;
var i, j, cont: longint;

begin
    verificaMatrizTriangInf := true;
    cont := 0;
    for i := 1 to contLinhas - 1 do
    begin
        for j := (2 + cont) to contColunas do
        begin            
            if (m[i,j] <> 0) then
            begin
                verificaMatrizTriangInf := false;
                break;
            end;
        end;
        cont := cont + 1;
    end;
end;

function verificaMatrizTriangSup (var m: matrizM; var contLinhas: longint; var contColunas: longint): boolean;
var i, j, cont: longint;

begin
    verificaMatrizTriangSup := true;
    cont := 1;
    while (cont < contColunas) do
    begin
        for i := 2 to contLinhas do
        begin
            for j := 1 to cont do
            begin
                if (m[i,j] <> 0) then
                begin
                    verificaMatrizTriangSup := false;
                    break;
                end;
            end;
            cont := cont + 1;
        end;
    end;
end;

begin
    read(n);
    numLinhas := n;
    numColunas := n;
    leMatriz(matriz, numLinhas, numColunas);
    if verificaMatrizTriangInf(matriz, numLinhas, numColunas) or verificaMatrizTriangSup(matriz, numLinhas, numColunas) then
        writeln('Sim.')
    else
        writeln('Não.');
end.