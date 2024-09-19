program converteFarenheit;

(* Esse algoritmo converte uma temperatura em Farenheit para Celsius e, baseando-se nos 
estados químicos da água, diz em qual estado químico estará nessa temperatura. *)

var F, C, fracionaria: real;

begin
	read (F);
	C := (F - 32) * (5/9);
	writeln (C:0:2);

	if (C <= 0) then
		writeln ('Sólido.')
	else
		if (C >= 100) then
			writeln ('Gasoso.')
		else
			writeln ('Líquido.')
end.
