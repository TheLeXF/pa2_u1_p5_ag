package com.uce.edu.inventario.service;

import com.uce.edu.inventario.repository.modelo.Inventario;

public interface IInventarioService {
	
	public Inventario buscar(String codigoInv);

	public void guardar(Inventario inventario);

	public void actualizar(Inventario inventario);

	public void eliminar(String codigoInv);
	
	public void registrar (String codigoBodega,String codigoBarras, Integer stock);

}
