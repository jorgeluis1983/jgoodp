package readwrite;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileFilter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import busqueda.Word;
import common.Constantes;

public class Read {

	private String word=":9071";
	private String rooot="C:\\pr\\fileunico";
	static final int longitud=25;
	
	public List<Word> listFiles(String path,String word)throws Exception{
		this.word=word;
		this.rooot=path;
		try{
			File file=new File(rooot);
			List<Word> lista=new ArrayList();
			recorridoRecursivo(file,lista);
			System.out.println("size: "+lista.size());
			return lista;
			
			/*Set<Word> s=new  HashSet<Word>(lista);
			List<Word> lista2=new ArrayList(s);
			
			java.util.Collections.sort(lista2, new Comparator<Word>(){
				 @Override
               public int compare(Word lhs, Word rhs) {
				 return lhs.codigo.compareTo( rhs.codigo) < 0 ? -1 : (lhs.codigo.compareTo( rhs.codigo) > 0 ? 1 : 0) ;
               }
			});	*/
			
			/*for(Word w:lista2){
				System.out.println(w.imprimirInsert());
			}*/
			
		}catch(Exception e){
			e.printStackTrace();
			throw e;
		}
	}
	
	private boolean containsExclude(File root){
		for(String a:Constantes.name_excluidos){
			if(root.getName().contains(a)){
				return true;
			}
		}
		return false;
	}
	
	private boolean containsInclude(File file){
		
		for(String a:Constantes.ext_permitidos){
			if(file.getName().contains("."+a)){
				return true;
			}
		}
		return false;
	}
	
	public void recorridoRecursivo(File root,List<Word> lista)throws Exception{
		
		if(!containsExclude(root)){
			File[] ff=findFiles(root);
		
			if(ff!=null && ff.length>0){
				for(File f:ff){
					if(containsInclude(f)){
						List<Word> l=findPalabra(f);
						if(l!=null && l.size()>0){
							lista.addAll(l);
						}
					}
				}
			}
			File[] a=findDirectories(root);
			if(a!=null && a.length>0){
				for(File f:a){
					recorridoRecursivo(f,lista);
				}
			}
		}
		
	}
	
	public List<Word> findReplacePalabra(File file){
		List<Word> lista=new ArrayList();
		
		StringBuilder sb=new StringBuilder();
		BufferedReader br=null;
		try {
			br = new BufferedReader(new FileReader(file));
			
		    String line = br.readLine();
		    boolean existeWord=false;
		    while (line != null) {
		        sb.append(line);
		        sb.append("\n");
		    	if(line.contains(word)){
		        	Word w=new Word();
		        	int i=line.indexOf(word);
		        	w.codigo=line.substring(i+longitud,i+longitud+6);
		        	w.line=line.substring(i+longitud+6+3);
		        	
		        	existeWord=true;
		        	
		        	lista.add(w);
		        }
		        line = br.readLine();
		    }
		    
		    if(existeWord){
		    	String old=sb.toString();
		    	String newe=old.replaceAll(word, "mensajeComponent.getMsj");
		    	FileWriter writer = new FileWriter(file);
	             writer.write(newe);
	             writer.close();
		    }
		   
		    return lista;
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
		    try {
				br.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
		return null;
	}
	
	
	public List<Word> findPalabra(File file){
		List<Word> lista=new ArrayList();
		
		BufferedReader br=null;
		try {
			br = new BufferedReader(new FileReader(file));
			
		    String line = br.readLine();
		    
		    while (line != null) {
		        if(line.contains(word)){
		        	Word w=new Word();
		        	//int i=line.indexOf(word)
		        	//w.codigo=line.substring(i+longitud,i+longitud+6)
		        	w.line=file.getCanonicalPath() +" : "+line;
		        	System.out.println(file.getCanonicalPath() +" : "+line);
		        	lista.add(w);
		        }
		        line = br.readLine();
		    }
		   
		    return lista;
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
		    try {
				br.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
		return null;
	}
	
	public File[] findDirectories(File root) { 
        
		return root.listFiles(new FileFilter() {
            public boolean accept(File f) {
                return f.isDirectory();
            }});
    }

    public File[] findFiles(File root) {
        return root.listFiles(new FileFilter() {
            public boolean accept(File f) {
                return f.isFile();
            }});
    }
}
