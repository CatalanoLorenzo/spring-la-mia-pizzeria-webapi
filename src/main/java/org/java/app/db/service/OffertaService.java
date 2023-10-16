package org.java.app.db.service;

import org.java.app.db.pojo.Offerta;
import org.java.app.db.pojo.repo.OffertaRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OffertaService {

	@Autowired
	OffertaRepo offertaRepo;

	public void OffertaSave(Offerta offerta) {
		offertaRepo.save(offerta);
	}
	public Offerta findById(int id) {

		return offertaRepo.findById(id).get();
	}
	public void offertaDelete(Offerta offerta) {
		 offertaRepo.delete(offerta);
		
	}
}
