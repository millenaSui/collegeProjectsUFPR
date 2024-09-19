program somaValoresDeUmVetor;
var n, i, cont: longint;
var valor, somaPares, somaImpares: real;
var v: array [1..201] of real;

begin
	read (n);
	somaPares := 0;
	somaImpares := 0;
	if (n = 0) then
		writeln ('Vetor vazio.')
	else
	begin
		for i := 1 to n do
		begin
			read (valor);
			v[i] := valor;
		end;
		for cont := 1 to n do
		begin
			if (v[cont] > 0) and (cont mod 2 = 0) then
				somaPares := somaPares + v[cont];
		end;
		for cont := 1 to n do
		begin
			if (v[cont] < 0) and (cont mod 2 <> 0) then
				somaImpares := somaImpares + v[cont];
		end;
		if (somaImpares = 0) then
			writeln ('Divisão por zero.')
		else
			writeln ((somaPares / somaImpares):0:2);
	end;
end.
