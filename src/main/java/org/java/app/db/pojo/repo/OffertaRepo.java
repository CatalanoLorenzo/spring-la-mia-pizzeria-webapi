package org.java.app.db.pojo.repo;


import org.java.app.db.pojo.Offerta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OffertaRepo extends  JpaRepository<Offerta,Integer>
{
}
