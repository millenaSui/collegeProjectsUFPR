program numerosContrarios;
var n, m: longint;

function contrario (var n, m: longint): boolean;
var baseP, baseS, primeiro, segundo: longint;
begin
	baseP := 10;
	baseS := 10;
	primeiro := n mod baseP;
	segundo := m mod baseS;
	while (baseP <= n) do
	begin
		baseP := baseP * 10;
		primeiro := (n mod baseP) div (baseP div 10);
	end;
	if (primeiro = segundo) then
	begin
		contrario := true;
		while ((baseP div 10) > 1) do
		begin
			baseP := baseP div 10;
			baseS := baseS * 10;
			primeiro := (n mod baseP) div (baseP div 10);
			segundo := (m mod baseS) div (baseS div 10);
			if (primeiro = segundo) then
				contrario := true
			else
			begin
				contrario := false;
				break;
			end;
		end;
	end
	else
		contrario := false;
end;
		

begin
	read (n,m);
	if contrario(n,m) then
		writeln (n,' é o contrário de ',m)
	else
		writeln (n,' não é o contrário de ',m);
end.
