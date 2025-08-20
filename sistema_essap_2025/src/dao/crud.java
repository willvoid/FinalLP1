package dao;

import java.util.List;

public interface crud<T> {
	    public void insertar(T obj);
	    public void actualizar(T obj);
	    public void eliminar(T obj);
	    public List<T> listar(String t);
}

