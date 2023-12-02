package com.uce.edu.inventario.service;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.uce.edu.inventario.repository.IBodegaRepository;
import com.uce.edu.inventario.repository.IInventarioRepository;
import com.uce.edu.inventario.repository.IProductoRepository;
import com.uce.edu.inventario.repository.modelo.Bodega;
import com.uce.edu.inventario.repository.modelo.Inventario;
import com.uce.edu.inventario.repository.modelo.Producto;
@Service
public class InventarioServiceImpl implements IInventarioService {
	@Autowired
	private IInventarioRepository inventarioRepository;
	@Autowired
	private IBodegaRepository bodegaRepository;
	@Autowired
	private IProductoRepository productoRepository;
	@Override
	public Inventario buscar(String codigoInv) {
		// TODO Auto-generated method stub
		return inventarioRepository.seleccionar(codigoInv);
	}

	@Override
	public void guardar(Inventario inventario) {
		this.inventarioRepository.insertar(inventario);

	}

	@Override
	public void actualizar(Inventario inventario) {
		this.inventarioRepository.actualizar(inventario);

	}

	@Override
	public void eliminar(String codigoInv) {
		this.inventarioRepository.eliminar(codigoInv);

	}

	@Override
	public void registrar(String codigoBodega, String codigoBarras, Integer stock) {
		Bodega bodega =this.bodegaRepository.seleccionar(codigoBodega);
		Producto pro = this.productoRepository.seleccionar(codigoBarras);
		
		Integer stockActual=pro.getStock();
		Integer stockFinal=stockActual+stock;
		pro.setStock(stockFinal);
		this.productoRepository.actualizar(pro);
		
		Inventario ivn = new Inventario();
		ivn.setCodigoInven("1235454");
		ivn.setFechaIngreso(LocalDateTime.now());
		ivn.setBodega(bodega);
		ivn.setProducto(pro);
		
		this.inventarioRepository.insertar(ivn);
		
		
	}

}
