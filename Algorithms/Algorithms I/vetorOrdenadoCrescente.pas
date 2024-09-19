program vetorOrdenadoCrescente;
var n: longint;
const MIN = 1; MAX = 200;
type vetorV = array [MIN..MAX] of longint;
var vetor: vetorV;

procedure leVetor (var v: vetorV; tam: longint);
var i: longint;

begin
    for i := 1 to tam do
        read(v[i]);
end;

function ehOrdenadoCrescente (var v: vetorV; tam: longint): boolean;
var i: longint;

begin
    i := 1;
    ehOrdenadoCrescente := true;
    while (i < tam) do
    begin
        if (v[i] <= v[i+1]) then
            i := i + 1
        else
        begin
            ehOrdenadoCrescente := false;
            break;
        end;
    end;    
end;

procedure imprimeVetorInvertido (var v: vetorV; tam: longint);
var i, total: longint;

begin
    i := 1;
    for total := tam downto i do
        write (v[total], ' ');
end;

begin
    read(n);
    if (n > 0) then
    begin
        leVetor(vetor, n);
        if ehOrdenadoCrescente(vetor, n) then
            writeln ('Sim.')
        else
        begin
            writeln ('Não.');
        end;
        imprimeVetorInvertido(vetor, n);
    end
    else    
        writeln('Vetor vazio.')
end.