package br.com.cotiinformatica.repositories;

public abstract class BaseRepository<T> {

    public abstract int criar(T obj) throws Exception;

}
