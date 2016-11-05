package tiabetes.modelo.comum.util;

import tiabetes.modelo.comum.enumerador.TipoRetorno;

public class Retorno {
	
	private String mensagem;
	private TipoRetorno tipo;
	
	private Retorno(TipoRetorno tipo, String mensagem){
		this.tipo = tipo;
		this.setMensagem(mensagem);
	}
	
	public boolean sucesso(){
		return (this.tipo == TipoRetorno.SUCESSO
			 || this.tipo == TipoRetorno.AVISO);
	}
	
	public String getMensagem() {
		return mensagem;
	}
	public void setMensagem(String mensagem) {
		this.mensagem = mensagem;
	}
	
	public boolean aviso(){
		return (this.tipo == TipoRetorno.AVISO);
	}
	
	public boolean arquitetura(){
		return (this.tipo == TipoRetorno.ARQUITETURA);
	}
	
	public boolean funcional(){
		return (this.tipo == TipoRetorno.FUNCIONAL);
	}
	
	public static Retorno getSucesso(){
		return new Retorno(TipoRetorno.SUCESSO, "");
	}
	
	public static Retorno getSucesso(String mensagem){
		return new Retorno(TipoRetorno.SUCESSO, mensagem);
	}
	
	public static Retorno getAviso(String mensagem){
		return new Retorno(TipoRetorno.AVISO, mensagem);
	}
	
	public static Retorno getFuncional(String mensagem){
		return new Retorno(TipoRetorno.FUNCIONAL, mensagem);
	}
	
	public static Retorno getArquitetura(String mensagem){
		return new Retorno(TipoRetorno.ARQUITETURA, mensagem);
	}
	
	public static Retorno getArquitetura(Exception ex){
		return new Retorno(TipoRetorno.ARQUITETURA, ex.getMessage());
	}
	
}

