package aplicacion;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */



/**
 *
 * @author basesdatos
 */
public class Libro {

    private Integer idLibro;
    private String titulo;
    private String isbn;
    private String editorial;
    private Integer paginas;
    private String ano;
    private java.util.List<String> autores;
    private java.util.List<Categoria> categorias;
    private java.util.List<Ejemplar> ejemplares;

    public Libro (Integer idLibro, String titulo, String isbn, String editorial, Integer paginas, String ano){
        this.idLibro=idLibro;
        this.titulo=titulo;
        this.isbn=isbn;
        this.editorial=editorial;
        this.paginas=paginas;
        this.ano=ano;
        autores = new java.util.ArrayList<String>();
        categorias = new java.util.ArrayList<Categoria>();
        ejemplares = new java.util.ArrayList<Ejemplar>();
    }

    public Integer getIdLibro (){
        return this.idLibro;
    }

    public String getTitulo (){
        return this.titulo;
    }

    public String getIsbn (){
        return this.isbn;
    }

    public String getEditorial (){
        return this.editorial;
    }

    public Integer getPaginas(){
        return this.paginas;
    }

    public String getAno (){
        return this.ano;
    }

    public java.util.List<String> getAutores (){
        return this.autores;
    }

    public void setAutores(java.util.List<String> autores){
        this.autores=autores;
    }

    public void addAutor (String autor){
        this.autores.add(autor);
    }

    public java.util.List<Categoria> getCategorias (){
        return this.categorias;
    }

    public void addCategoria (Categoria categoria){
        this.categorias.add(categoria);
    }

    public java.util.List<Ejemplar> getEjemplares (){
        return this.ejemplares;
    }
    
    public void addEjemplar (Ejemplar ejemplar){
        this.ejemplares.add(ejemplar);
    }
    
    public String getAutoresAsString(){
        String resultado="";
        Boolean inicial=true;

        for (String a:this.autores){
            if (inicial) {
                resultado=a;
                inicial=false;
            }
            else
            resultado=resultado+", "+a;
        }
        return resultado;
    }


}
