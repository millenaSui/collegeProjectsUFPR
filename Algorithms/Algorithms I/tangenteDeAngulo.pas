program tangenteDeAngulo;

(* Algoritmo que verifica se existe tangente com base no ângulo informado. *)
const termos = 6;
var angulo, tg: real;

function calculaSeno (x: real): real;
var soma, num: real;
    den, sinal, i, n: longint;
begin
    soma := 0;
    num := x;
    den := 1;
    sinal := 1;
    n := 2;

    for i := 1 to termos do
    begin
        soma := soma + (sinal * num / den);
        num := num * x * x;
        den := den * n * (n + 1);
        sinal := -sinal;
        n := n + 2;
    end;
    calculaSeno := soma
end;

function calculaCosseno (x: real): real;
var soma, num: real;
    den, sinal, i, n: longint;
begin
    soma := 1;
    num := x * x;
    den := 2;
    sinal := -1;
    n := 3;

    for i := 1 to (termos - 1) do
    begin
        soma := soma + (sinal * num / den);
        num := num * x * x;
        den := den * n * (n + 1);
        sinal := -sinal;
        n := n + 2;
    end;
    calculaCosseno := soma
end;

function existeTangente (x: real; var tangente: real): boolean;
begin
    if calculaCosseno(x) = 0 then
        existeTangente := false
    else
    begin
        existeTangente := true;
        tangente := calculaSeno(x)/calculaCosseno(x);
    end;
end;

begin
    read (angulo);
    if existeTangente(angulo, tg) then
        writeln(tg:0:3)
    else
        writeln('Não existe tangente de ',angulo:0:5);
end.
