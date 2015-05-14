package com.welovecoding.data.base;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;

import java.io.Serializable;

@NoRepositoryBean
public interface BaseRepository<E, PK extends Serializable> extends JpaRepository<E, PK> {

}
