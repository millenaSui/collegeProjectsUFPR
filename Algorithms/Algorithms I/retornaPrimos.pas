program retornaPrimos;
var i: longint;

function ehPrimo (valor: longint): boolean;
var divisor, resto: longint;
begin
	divisor := 2;
	if (divisor = valor) then
		ehPrimo := true
	else
		while (divisor < valor) do
		begin
			resto := valor mod divisor;
			if (resto = 0) then
			begin
				ehPrimo := false;
				break;
			end
			else
				ehPrimo := true;
			divisor := divisor + 1;
		end;
end;
		
			

begin
    for i:= 1 to 10000 do
        if ehPrimo(i) then
            writeln (i);
end.
