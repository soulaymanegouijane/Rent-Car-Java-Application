package Interfaces;

import java.util.List;

import Exceptions.AjoutExceptions;

public interface GlobalInter<T> {
	public int add(T arg) throws AjoutExceptions;
	public T edit(T arg);
	public int delete(T arg);
	public List<T> getAll();
	public T get(String nom);
	public T getById(long id);
}
