package c14.NoCountry.Service;

import c14.NoCountry.Entity.Rols;
import c14.NoCountry.Repository.RolsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RolsService {
    @Autowired
    RolsRepository rr;

    public Rols save(Rols rls){
        return rr.save(rls);
    }

    public List<Rols> findByAll(){
        return rr.findAll();

    }
}
