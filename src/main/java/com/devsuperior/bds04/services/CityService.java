package com.devsuperior.bds04.services;

import com.devsuperior.bds04.dto.CityDTO;
import com.devsuperior.bds04.entities.City;
import com.devsuperior.bds04.repositories.CityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CityService {

    @Autowired
    private CityRepository cityRepository;

   /* @Transactional(readOnly = true)
    public Page<CityDTO> findAllPaged(Pageable pageable) {
        Page<City> list = cityRepository.findAll(pageable);
        return list.map(x -> new CityDTO(x));
    }*/

    @Transactional(readOnly = true)
    public List<CityDTO> findAll() {
        List<City> list = cityRepository.findAll(Sort.by("name"));

        return list.stream()
                .map(x -> new CityDTO(x))
                .collect(Collectors.toList());
    }

    @Transactional
    public CityDTO insert(CityDTO dto) {
        City entity = new City();
        copyDtoToEntity(dto, entity);
        entity = cityRepository.save(entity);
        return new CityDTO(entity);
    }

    private void copyDtoToEntity(CityDTO dto, City entity) {
        entity.setName(dto.getName());
    }


}
