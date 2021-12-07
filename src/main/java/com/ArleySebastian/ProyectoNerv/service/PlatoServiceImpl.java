package com.ArleySebastian.ProyectoNerv.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ArleySebastian.ProyectoNerv.entity.Plato;
import com.ArleySebastian.ProyectoNerv.repository.PlatoRepository;



@Service
public class PlatoServiceImpl implements PlatoService{
	@Autowired
	private PlatoRepository _platoRepository;
	
	
	 @Override
	public int guardar(Plato z) {
    	int res=0;
    	Plato zz=_platoRepository.save(z);
    	if(!zz.equals(null)) {
    		res=1;
    	}
    			return res;
    }
	
	@Override
	@Transactional(readOnly=true)
	public List<Plato> findAll() {
		return (List<Plato>)_platoRepository.findAll();
	}

	@Override
	@Transactional(readOnly=true)
	public Plato findById(Long id) {
		return _platoRepository.findById(id).orElse(null);
	}

	@Override
	@Transactional
	public Plato save(Plato plato) {
		return _platoRepository.save(plato);
	}

	@Override
	@Transactional
	public void delete(Long id) {
		_platoRepository.deleteById(id);
		
	}

	@Override
	@Transactional
	public void actualizarStock(int cantidad, Long id) {
		_platoRepository.actualizarStock(cantidad, id);
	}

	@Override
	@Transactional
	public void actualizarEstado(Long id) {
		_platoRepository.actualizarEstado(id);
		
	}
	@Override
	public Optional<Plato>listarId(Long id){
		return _platoRepository.findById(id);
		
	}





	@Override
	public Iterable<Plato> getAllzapatos() {
		// TODO Auto-generated method stub
		return null;
	}





	@Override
	public Plato buscarPorId(Long id) {
		// TODO Auto-generated method stub
		return null;
	}





	@Override
	public void eliminar(Long id) {
		// TODO Auto-generated method stub
		
	}
}
