package org.datacan.can_eco.Service.Supplier;

import org.datacan.can_eco.Exception.NotFound;
import org.datacan.can_eco.Model.Industrial;
import org.datacan.can_eco.Repository.IndustrialRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class AdminIndustrialService {

    private final IndustrialRepository industrialRepository;

    @Autowired
    public AdminIndustrialService(IndustrialRepository industrialRepository) {
        this.industrialRepository = industrialRepository;
    }

    public Industrial createIndustrial(Industrial industrial) {
        return industrialRepository.save(industrial);
    }

    public Industrial updateIndustrial(UUID id, Industrial industrial) {
        industrialRepository.findById(id)
                .orElseThrow(() -> new NotFound("There is no Industrial product with this id!"));
        industrial.setId(id);
        return industrialRepository.save(industrial);
    }

    public void deleteIndustrial(UUID id) {
        industrialRepository.findById(id)
                .orElseThrow(() -> new NotFound("There is no Industrial product with this id!"));
        industrialRepository.deleteById(id);
    }

}
