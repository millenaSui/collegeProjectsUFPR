program imprimeNumeroInvertido;
var num, unidade, dezena, centena: longint;

begin
	read (num);
	centena := num div 100;
	dezena := (num mod 100) div 10;
	unidade := num mod 10;
	writeln (unidade, dezena, centena);
end.
