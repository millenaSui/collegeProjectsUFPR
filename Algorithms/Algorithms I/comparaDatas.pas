program comparaDatas;

(* Esse algoritmo compara duas datas e indica se a primeira data informada é, ou não, anterior à segunda data. *)
var dia1, mes1, ano1, dia2, mes2, ano2: longint;

function ehAnterior (dia1, mes1, ano1, dia2, mes2, ano2: longint): boolean;
begin
	if (ano1 < ano2) then
		ehAnterior := true
	else
		if (ano1 > ano2) then
			ehAnterior := false
		else
			if (ano1 = ano2) then
				if (mes1 < mes2) then
					ehAnterior := true
				else
					if (mes1 > mes2) then
						ehAnterior := false
					else
						if (mes1 = mes2) then
							if (dia1 < dia2) then
								ehAnterior := true
							else
								if (dia1 > dia2) then
									ehAnterior := false;
end;

begin
    read (dia1, mes1, ano1, dia2, mes2, ano2);
    if eh_Anterior(dia1, mes1, ano1, dia2, mes2, ano2) then
        writeln ('A primeira data é anterior.')
    else
        writeln ('A primeira data não é anterior.');
end.
