package Interfaces;

import java.util.List;

public interface GlobalInter<T> {
	public int add(T arg);
	public T edit(T arg);
	public int delete(T arg);
	public List<T> getAll();
	public T get(String nom);
	public T getById(long id);
}
