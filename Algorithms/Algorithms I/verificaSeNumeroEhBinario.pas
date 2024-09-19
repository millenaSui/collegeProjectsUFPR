program verificaSeNumeroEhBinario;
var n: longint;

function ehBinario (valor: longint): boolean;
var base, total: longint;
begin
	base := 10;
	total := valor mod base;
	if (total = 0) or (total = 1) then
	begin
		ehBinario := true;
		while (base div 10 <= valor) and (total = 0) or (total = 1) do
		begin
			total := (valor mod base * 10) div base;
			if (total <> 0) and (total <> 1) then
				ehBinario := false
			else
				ehBinario := true;
			base := base * 10;
		end;
	end
	else 
		ehBinario := false;
end;
		

begin
    read (n);
    if ehBinario(n) then
        writeln ('Sim.')
    else
        writeln ('Não.');
end.
