package com.uce.edu;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.uce.edu.inventario.repository.IProductoRepository;
import com.uce.edu.inventario.repository.modelo.Bodega;
import com.uce.edu.inventario.repository.modelo.Inventario;
import com.uce.edu.inventario.repository.modelo.Producto;
import com.uce.edu.inventario.service.IBodegaService;
import com.uce.edu.inventario.service.IInventarioService;
import com.uce.edu.inventario.service.IProductoService;
import com.uce.edu.transferencia.repository.modelo.CuentaBancaria;
import com.uce.edu.transferencia.repository.modelo.Transferencia;
import com.uce.edu.transferencia.service.ICuentaBancariaService;
import com.uce.edu.transferencia.service.ITransferenciaService;

@SpringBootApplication
public class Pa2U1P5AgApplication implements CommandLineRunner {
	
	//
	@Autowired 
	private IProductoService productoService;
	@Autowired
	private IBodegaService bodegaService;
	@Autowired
	private IInventarioService inventarioService;
	
	
	public static void main(String[] args) {
		SpringApplication.run(Pa2U1P5AgApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		
		Producto p1=new Producto();
		p1.setCodigoBarras("123456");
		p1.setNombre("HP 15 Laptop");
		p1.setStock(0);
		this.productoService.guardar(p1);

		Producto p2=new Producto();
		p2.setCodigoBarras("45685");
		p2.setNombre("Teclado HP");
		p2.setStock(0);
		this.productoService.guardar(p2);
		
		
		Bodega b1 = new Bodega();
		b1.setCapacidad(300);
		b1.setCodigo("12345");
		b1.setNombre("Bodega 1");
		b1.setDireccion("America");
		this.bodegaService.guardar(b1);
		
		this.inventarioService.registrar(b1.getCodigo(), p1.getCodigoBarras(), 50);
		this.inventarioService.registrar(b1.getCodigo(), p2.getCodigoBarras(), 100);
		this.inventarioService.registrar(b1.getCodigo(), p1.getCodigoBarras(), 20);
		
		System.out.println(this.productoService.buscar("123456"));
		System.out.println(this.productoService.buscar("45685"));
	}
}
