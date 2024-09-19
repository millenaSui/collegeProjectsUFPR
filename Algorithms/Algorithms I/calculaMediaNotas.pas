program calculaMediaNotas;
var N, somaPesos, somaNotas, media: real;
var P: longint;

begin
	read (N, P);
	somaNotas := 0;
	somaPesos := 0;
	while (N <> 0) and (P <> 0) do
	begin
		somaNotas := somaNotas + (N * P);
		somaPesos := somaPesos + P;
		read (N, P);
	end;
	media := somaNotas / somaPesos;
	writeln (media:0:2);
end.
