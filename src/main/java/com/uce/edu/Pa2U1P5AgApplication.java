package com.uce.edu;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.uce.edu.transferencia.repository.modelo.CuentaBancaria;
import com.uce.edu.transferencia.repository.modelo.Transferencia;
import com.uce.edu.transferencia.service.ICuentaBancariaService;
import com.uce.edu.transferencia.service.ITransferenciaService;

@SpringBootApplication
public class Pa2U1P5AgApplication implements CommandLineRunner {
	
	//
	@Autowired 
	private ITransferenciaService iTransferenciaService;
	
	/*Inyeccion de dependencias por construcor 
	 * 
 private ITransferenciaService iTransferenciaService;
	@Autowired
	public Pa2U1P5AgApplication(ITransferenciaService iTransServi) {
		this.iTransferenciaService=iTransServi;
	}*/
	
	/*Inyeccion de dependencias por metodo
	private ITransferenciaService iTransferenciaService;
	@Autowired
	public void setiTransferenciaService(ITransferenciaService iTransferenciaService) {
		this.iTransferenciaService = iTransferenciaService;
	}*/
	
	@Autowired
	private ICuentaBancariaService bancariaService;
	
	public static void main(String[] args) {
		SpringApplication.run(Pa2U1P5AgApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		//1 Crear cuenta
		CuentaBancaria cuentaOrigen= new CuentaBancaria();
		cuentaOrigen.setCedulaPropietario("1727450296");
		cuentaOrigen.setNumero("1234");
		cuentaOrigen.setSaldo(new BigDecimal(100));
		this.bancariaService.guardar(cuentaOrigen);
		
		CuentaBancaria cuentaDestino= new CuentaBancaria();
		cuentaDestino.setCedulaPropietario("1714224415");
		cuentaDestino.setNumero("1263");
		cuentaDestino.setSaldo(new BigDecimal(200));
		this.bancariaService.guardar(cuentaDestino);
		
		CuentaBancaria cuentaDeposito= new CuentaBancaria();
		cuentaDeposito.setCedulaPropietario("1712281193");
		cuentaDeposito.setNumero("5678");
		cuentaDeposito.setSaldo(new BigDecimal(80));
		this.bancariaService.guardar(cuentaDeposito);
		

		this.iTransferenciaService.realizar("1234","1263" , new BigDecimal(20));
		
		CuentaBancaria cuentaOrigen1 = this.bancariaService.buscar("1234");
		System.out.println(cuentaOrigen1);
		
		CuentaBancaria cuentaDestino1= this.bancariaService.buscar("1263");
		System.out.println(cuentaDestino1);
		
		this.iTransferenciaService.realizar("1234", "1263", new BigDecimal(50));
		this.iTransferenciaService.realizar("1263", "1234", new BigDecimal(10));
		
		
		//Construir un reporte del estado de cuenta de todas las transferecnias 
		int indice=0;
		List<Transferencia>lista=this.iTransferenciaService.movimientosBancarios();
		for (Transferencia trans:lista) {
			indice++;
			System.out.println("N° Transferencia: "+indice+": "+trans.toString());	
		}
		System.out.println(cuentaDeposito);
		this.bancariaService.deposito("5678", new BigDecimal(50));
		System.out.println(cuentaDeposito);
		
	}
}
