program contaLinhasColunasNulas;

(* Esse algoritmo conta quantas linhas/colunas nulas há em uma matriz. *)

var n, m: longint;
type matrizM = array [1..100, 1..100] of integer;
var matriz: matrizM;

procedure le_matriz (var m: matrizM; var contLinhas: longint; var contColunas: longint);
var i, j: longint;

begin
    for i := 1 to contLinhas do
    begin
        for j := 1 to contColunas do
        begin
            read(m[i,j]);
        end;
    end;
end;

function contaLinhasNulas (var m: matrizM; var contLinhas: longint; var contColunas: longint): longint;
var i, j, total: longint;    
    
begin
    total := 0;
    for i := 1 to contLinhas do
    begin
        for j := 1 to contColunas do
        begin
            if (m[i,j] <> 0) then
                break
            else
            begin
                if (j = contColunas) then
                    total := total + 1;
            end;    
        end;
    end;
    contaLinhasNulas := total;
end;

function contaColunasNulas (var m: matrizM; var contLinhas: longint; var contColunas: longint): longint;
var i, j, total: longint;    
    
begin
    total := 0;
    for j := 1 to contColunas do
    begin
        for i := 1 to contLinhas do
        begin
            if (m[i,j] <> 0) then
                break
            else
            begin
                if (i = contLinhas) then
                    total := total + 1;
            end;    
        end;
    end;
    contaColunasNulas := total;
end;

begin
    read(n, m);
    le_matriz(matriz, n, m);
    writeln('linhas: ', contaLinhasNulas(matriz, n, m));
    writeln('colunas: ', contaColunasNulas(matriz, n, m));
end.