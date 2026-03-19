package br.com.cotiinformatica.repositories;

public abstract class BaseRepository<T> {

    public abstract void criar(T obj) throws Exception;

}
