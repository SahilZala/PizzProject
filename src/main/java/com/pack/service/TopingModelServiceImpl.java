package com.pack.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pack.exception.NoTopingFoundException;
import com.pack.model.TopingModel;
import com.pack.repository.TopingModelRepository;

@Service
public class TopingModelServiceImpl implements TopingModelService{

	@Autowired
	TopingModelRepository topingModelRepository;
	
	@Override
	public List<TopingModel> getAllTopings() throws NoTopingFoundException{
		
		List<TopingModel> data = topingModelRepository.findAll();
		
		if(data.isEmpty()) {
			throw new NoTopingFoundException("no topings found");
		}
		
		return data;
	}

}
