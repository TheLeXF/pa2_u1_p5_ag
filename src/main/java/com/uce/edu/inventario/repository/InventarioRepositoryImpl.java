package com.uce.edu.inventario.repository;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.uce.edu.inventario.repository.modelo.Inventario;
@Repository
public class InventarioRepositoryImpl implements IInventarioRepository {
	
	private static List<Inventario> base = new ArrayList<Inventario>();
	@Override
	public Inventario seleccionar(String codigoInv) {
		for (Inventario inventario : base) {
			if (inventario.getCodigoInven().equals(codigoInv)) {

				Inventario ivn = new Inventario();
				ivn.setCodigoInven(inventario.getCodigoInven());
				ivn.setProducto(inventario.getProducto());
				ivn.setBodega(inventario.getBodega());
				return ivn;
			}
		}
			
		return null;
	}
	
	public Inventario seleccionarEliminar(String codigo) {
		for (Inventario inventario : base) {
			if (inventario.getCodigoInven().equals(codigo)) {

				return inventario;
			}
		}
		return null;
	}

	@Override
	public void insertar(Inventario inventario) {
		// TODO Auto-generated method stub
		base.add(inventario);
	}

	@Override
	public void actualizar(Inventario inventario) {
		this.eliminar(inventario.getCodigoInven());
		this.insertar(inventario);
	}

	@Override
	public void eliminar(String codigo) {
		Inventario inventario = this.seleccionarEliminar(codigo);
		base.remove(inventario);
	}

}
