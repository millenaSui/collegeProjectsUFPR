program crescimentoPopulacional;

(* Esse algoritmo verifica, de acordo com a taxa de crescimento populacional (Ta e Tb) de duas cidades 'a' e 'b', se o número de pessoas (Pa e Pb) da 
cidade com menos habitantes ultrapassará o número de pessoas da cidade com mais habitantes, e em caso positivo, em quantos anos isso ocorrerá. *)

var Pa, Pb, Ta, Tb, anos, armA, armB: real;

begin
	read (Pa, Pb, Ta, Tb);
	anos := 0;
	armA := Pa;
	armB := Pb;
	if (Pa < Pb) and (Ta < Tb) then
		writeln (0)
	else 
		if (Pa < Pb) and (Ta > Tb) then
		begin
			while (armA < armB) do
			begin
				armA := (armA * Ta) + ArmA;
				armB := (armB * Tb) + ArmB;
				anos := anos + 1;
			end;	
			writeln (anos:0:0);
		end
		else
			if (Pa > Pb) and (Ta < Tb) then
			begin	
				while (armA > armB) do
				begin 
					armA := (armA * Ta) + armA;
					armB := (armB * Tb) + armB;
					anos := anos + 1;
				end;
				writeln (anos:0:0);
			end
			else
				if (Pa > Pb) and (Ta > Tb) then
					writeln (0);
end.
