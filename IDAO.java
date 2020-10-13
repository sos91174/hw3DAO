package hw3DAO;

import java.util.List;

public interface IDAO<T> {
	boolean insert(T t);
	boolean update(T t);
	boolean delete(T t);
	T get(String id);
	T get(int id);
	List<T> getAll();
}
