package busqueda;

public class Word {
	public String codigo;
	public String line;
	
	
	public String imprimirInsert(){
		
		return "insert into BNMVPE22_MENSAJES (B22_COD, B22_MODULO, B22_SUBMODULO, B22_MENSAJE, B22_ESTADO, B22_TIPO)"+
		"values ('"+codigo+"', 'ADMIN', '', '"+line+"', 'A', '');";
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((codigo == null) ? 0 : codigo.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Word other = (Word) obj;
		if (codigo == null) {
			if (other.codigo != null)
				return false;
		} else if (!codigo.equals(other.codigo))
			return false;
		return true;
	}
	
	
}
