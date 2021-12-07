package com.ArleySebastian.ProyectoNerv.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ArleySebastian.ProyectoNerv.entity.Cliente;
import com.ArleySebastian.ProyectoNerv.repository.ClienteRepository;

@Service
public class ClienteServiceImpl implements ClienteService{

	@Autowired
	private ClienteRepository _clienteRepository;
	
	@Override
	@Transactional(readOnly=true)
	public List<Cliente> findAll() {
		
		return (List<Cliente>)_clienteRepository.findAll();
	}

	@Override
	@Transactional(readOnly=true)
	public Cliente findById(Long id) {
		
		return _clienteRepository.findById(id).orElse(null);
	}

	@Override
	@Transactional
	public Cliente save(Cliente cliente) {
		return _clienteRepository.save(cliente);
	}

	@Override
	@Transactional
	public void delete(Long id) {
		_clienteRepository.deleteById(id);
		
	}

	@Override
	public Iterable<Cliente> getAllclientes() {
		// TODO Auto-generated method stub
		return null;
	}

	 @Override
		public int guardar(Cliente c) {
	    	int res=0;
	    	Cliente cc=_clienteRepository.save(c);
	    	if(!cc.equals(null)) {
	    		res=1;
	    	}
	    			return res;
	    }

	@Override
	public Cliente buscarPorId(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void eliminar(Long id) {
		// TODO Auto-generated method stub
		
	}
}
