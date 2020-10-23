package org.java.transformerbattle.service.impl;

import org.java.transformerbattle.dao.TransformerRepository;
import org.java.transformerbattle.entity.Transformer;
import org.java.transformerbattle.model.TransformerInData;
import org.java.transformerbattle.model.TransformerOutData;
import org.java.transformerbattle.service.DataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service("dataService")
public class DataServiceImpl implements DataService {

    @Autowired
    private TransformerRepository dataRepository;

    @Override
    public void create(TransformerInData request) throws Exception{
        Transformer transformer = new Transformer();
        transformer.setName(request.getName());
        transformer.setCourage(request.getCourage());
        transformer.setEndurance(request.getEndurance());
        transformer.setFirePower(request.getFirePower());
        transformer.setIntelligence(request.getIntelligence());
        transformer.setRank(request.getRank());
        transformer.setStrength(request.getStrength());
        transformer.setSpeed(request.getSpeed());
        if(request.getType().equalsIgnoreCase("A") || request.getType().equalsIgnoreCase("D")){
            transformer.setType(request.getType());
            dataRepository.save(transformer);
        }else{
            throw new Exception("Invalid transformer type");
        }
    }

    @Override
    public List<TransformerOutData> listAllTransformers() throws Exception{
        return dataRepository.findAll().stream().filter(Objects::nonNull).map(transformer -> {
            TransformerOutData data = new TransformerOutData();
            data.setCourage(transformer.getCourage());
            data.setEndurance(transformer.getEndurance());
            data.setFirePower(transformer.getFirePower());
            data.setId(transformer.getTransformerId());
            data.setIntelligence(transformer.getIntelligence());
            data.setName(transformer.getName());
            data.setSpeed(transformer.getSpeed());
            data.setStrength(transformer.getStrength());
            data.setRank(transformer.getRank());
            data.setType(transformer.getType());
            return data;
        }).collect(Collectors.toList());
    }

    @Override
    public void deleteById(int id) throws Exception {
        dataRepository.deleteById(id);
    }

    @Override
    public void updateTransformer(int id, TransformerInData transformer) throws Exception {
        Transformer existing = dataRepository.getOne(id);
        if(null!=existing){
            Transformer updateRequest = new Transformer();
            updateRequest.setCourage(transformer.getCourage());
            updateRequest.setEndurance(transformer.getEndurance());
            updateRequest.setFirePower(transformer.getFirePower());
            updateRequest.setTransformerId(id);
            updateRequest.setIntelligence(transformer.getIntelligence());
            updateRequest.setName(transformer.getName());
            updateRequest.setSpeed(transformer.getSpeed());
            updateRequest.setStrength(transformer.getStrength());
            updateRequest.setRank(transformer.getRank());
            if(transformer.getType().equalsIgnoreCase("A") || transformer.getType().equalsIgnoreCase("D")){
                updateRequest.setType(transformer.getType());
                dataRepository.save(updateRequest);
            }else{
                throw new Exception("Invalid transformer type");
            }
        }else{
            throw new Exception("Transfomer not found with given identifier");
        }
    }
}
