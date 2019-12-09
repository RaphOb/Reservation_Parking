/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cours.ebenus.dao;

import java.util.List;

/**
 *
 * @author ElHadji
 */
public interface IDao<T> {

    public List<T> findAll(String query);

    public T findById(String query, int id);

    public List<T> findByCriteria(String query, String criteria, Object valueCriteria);

    public T create(String query, T t);

    public T update(String query, T t);

    public boolean delete(String query, T t);
}
