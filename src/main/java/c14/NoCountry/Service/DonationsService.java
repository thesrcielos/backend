package c14.NoCountry.Service;

import c14.NoCountry.Entity.donations;
import c14.NoCountry.Repository.DonationsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DonationsService {
    @Autowired
    DonationsRepository dr;

    public donations save(donations dnt){
        return dr.save(dnt);
    }

    public List<donations> findByAll(){
        return dr.findAll();

    }
}
