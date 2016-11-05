package tiabetes.modelo.comum.enumerador;

public enum TipoPerfil {
	
	ADMINSTRADOR(0),
	FUNCIONARIO(1),
	CLIENTE(2);
	
	private final int valor;
	
	TipoPerfil(int valor){
		this.valor = valor;
	}
	
	public int getValor(){
		return valor;
	}
	
}
