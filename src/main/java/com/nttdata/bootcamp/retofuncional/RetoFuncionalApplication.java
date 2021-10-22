package com.nttdata.bootcamp.retofuncional;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.nttdata.bootcamp.retofuncional.builder.UsuarioBuilder;
import com.nttdata.bootcamp.retofuncional.funcion.ConfigurarListaFuncion;
import com.nttdata.bootcamp.retofuncional.funcion.TelefonoFuncion;
import com.nttdata.bootcamp.retofuncional.repository.Aula;
import com.nttdata.bootcamp.retofuncional.repository.Usuario;

@SpringBootApplication
public class RetoFuncionalApplication {

	public static void main(String[] args) {
		SpringApplication.run(RetoFuncionalApplication.class, args);
		List<Usuario> usuarioList = new ArrayList<Usuario>();
		List<Usuario> usuarioList2 = new ArrayList<Usuario>();
		Aula aula = new Aula();
		Usuario usuario = new UsuarioBuilder().build(null, null, null, null);
		Usuario usuario2;
		// Interfaz propia para realizar diversos ajustes a la lista
		ConfigurarListaFuncion configurar = x -> {
			x.setNombre(x.getNombre().toUpperCase());
			x.setDireccion(x.getDireccion().trim());
			x.setDireccion(x.getDireccion().split(" ")[1]);
			return x;
		};
		// Interfaz propia para comprobar telefono
		TelefonoFuncion telefonoFuncion = (telefono) -> telefono.startsWith("6") || telefono.startsWith("7")
				|| telefono.startsWith("9");

		// Alimentando la lista 
		for (int i = 0; i < 10; i++) {
			usuario2 = usuario.clone();
			usuario2.setTelefono("" + Math.round(Math.random() * 1000000000));
			usuarioList.add(usuario2);
		}
		// Lista programacion funcional, Colletions.nCopies
		usuarioList2 = Collections.nCopies(5, usuario);
		// problema al no iterar en diferentes ejecuciones los telefonos, el ramdom se
		// aplica una vez para todos los telefonos
		usuarioList2.forEach(x -> x.setTelefono("" + Math.round(Math.random() * 1000000000)));
		usuarioList2.forEach(System.out::println);
		System.out.println();
		//Seteando la lista en nuestra aula 
		aula.setUsuarioList(usuarioList);
		System.out.println(aula);
		System.out.println();
		// Aplicando nuestra configuracion con la interfaz funcional
		aula.getUsuarioList().forEach(x -> {
			configurar.listar(x);
		});
		aula.getUsuarioList().forEach(System.out::println);

		System.out.println();
		//Probando streams y collectors, 
		System.out.println("Prueba Collectors : "
				+ aula.getUsuarioList().stream().map(Usuario::toString).collect(Collectors.toList()));
		System.out.println();
		System.out.println("Prueba Filters telefonos validos : "
				+ aula.getUsuarioList().stream().filter(x -> telefonoFuncion.valido(x.getTelefono()))
						.map(Usuario::getTelefono).collect(Collectors.toList()));
		System.out.println();
		//Probando mapeo de los telefonos a int 
		System.out.println("Telefono  mas grande: " + aula.getUsuarioList().stream()
				.mapToInt(x-> Integer.parseInt(x.getTelefono())).max().getAsInt());
		System.out.println();
		//Probando reduce  
		System.out.println("Telefono  reduce: " + aula.getUsuarioList().stream()
				.mapToInt(x-> Integer.parseInt(x.getTelefono())).reduce(2, (x,c)-> x+c));
		
		
	}

}
