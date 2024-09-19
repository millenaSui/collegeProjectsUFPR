program calculaFibonacci.pas;
var N, i, soma, fibAt, fibArm: longint;

begin
	read (N);
	i := 2;
	fibArm := 0;
	fibAt := 1;
	soma := 0;

	while (i <= N) do
	begin
		soma := soma + fibAt;
		fibAt := fibAt + fibArm;
		fibArm := fibAt - fibArm;
		i := i + 1;
	end;
	writeln (soma);
end.
