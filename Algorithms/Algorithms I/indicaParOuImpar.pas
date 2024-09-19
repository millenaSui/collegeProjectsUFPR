program indicaParOuImpar;
var num: longint;

begin
	read (num);
	if ((num mod 2) = 0) then
		writeln ('É um valor par.')
	else
		writeln ('É um valor ímpar.')
end.
