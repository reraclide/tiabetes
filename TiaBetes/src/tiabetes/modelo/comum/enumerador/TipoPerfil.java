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
	
	public static TipoPerfil getPerfil(int num){
		if (num == 0)
			return TipoPerfil.ADMINSTRADOR;
		else if (num == 1)
			return TipoPerfil.FUNCIONARIO;
		else 
			return TipoPerfil.CLIENTE;
		
	}
	
}
