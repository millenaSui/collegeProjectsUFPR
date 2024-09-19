program converteParaDecimal;
var n: longint; 

function converteEmDecimal (var decimal: longint): longint;
var total, soma, base, dezena: longint;
begin
        dezena := 10;
        total := decimal mod dezena;
        base := 2;
        soma := total;
        while (dezena <= decimal) do
        begin
                dezena := dezena * 10;
                total := ((decimal mod dezena) div (dezena div 10));
			if (total <> 0) then
				soma := soma + base;
                base := base * 2;
        end;
        converteEmDecimal := soma;
end;

begin
    read (n);
    writeln (converteEmDecimal(n));
end.
