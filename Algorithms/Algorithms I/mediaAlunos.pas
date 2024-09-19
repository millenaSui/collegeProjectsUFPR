program mediaAlunos;

(* Esse algoritmo calcula a média ponderada de notas de um certo aluno e indica se foi 
aprovado ou reprovado de acordo com esse valor *)

var i, n, p1, p2, p3, media: longint;

function mediaPonderada (var p1, p2, p3: longint): longint;
begin
	mediaPonderada := (p1 + p2 * 2 + p3 * 3) div 6;
end; 

function aprovado (media: longint): boolean;
begin
	if (media >= 50) then
		aprovado := true
	else
		aprovado := false;
end;

begin
    read (n);
    for i:= 1 to n do
    begin
        read (p1, p2, p3);
        media:= mediaPonderada(p1, p2, p3);
        if aprovado(media) then
            writeln ('aluno ',i,' aprovado com media: ', media)
        else
            writeln ('aluno ',i,' reprovado com media: ', media);
    end;
end.
