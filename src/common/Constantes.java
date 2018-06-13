package common;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

public class Constantes {

	
	public static final List<String> ext_permitidos=new ArrayList<String>(){
		{
			add("java");
			add("css");
		}
		};
		
	public static final List<String> name_excluidos=new ArrayList<String>(){
		{
			add("EAR");
		}
	};
}
