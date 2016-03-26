package pe.edu.cibertec.proyemp.jpa.test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import pe.edu.cibertec.proyemp.jpa.domain.Departamento;
import pe.edu.cibertec.proyemp.jpa.domain.Empleado;

public class JPATest {

	private EntityManager manager;
	
	public JPATest(EntityManager manager) {
		this.manager = manager;
	}
	
	public static void main(String[] args) {
		EntityManagerFactory factory = Persistence.createEntityManagerFactory("persintenceUnit");
		
		EntityManager manager = factory.createEntityManager();
		
		JPATest test = new JPATest(manager);
		
		EntityTransaction tx =  manager.getTransaction();
		tx.begin();
		
		try {
//			test.crearEmpleados();
//			test.CrearEmpleados2();
			test.mostrarEmpleado();
		} catch (Exception e) {
			e.printStackTrace();
		}
		tx.commit();
		
		test.listarEmpleados();
		
		System.out.println(".. done");
	}
	
	private void crearEmpleados() {
		int nroDeEmpleados = manager.createQuery("Select a from Empleado a", Empleado.class).getResultList().size();
		if (nroDeEmpleados == 0) {
			Departamento departamento = new Departamento("Java");
			manager.persist(departamento);
			
			manager.persist(new Empleado("Bob", departamento));
			manager.persist(new Empleado("Mike", departamento));
		}
	}
	
	private void CrearEmpleados2() {
		Departamento departamento = new Departamento("Java");
		
		Empleado empleado1 = new Empleado("Bob");
		Empleado empleado2 = new Empleado("Mike");
		
//		List<Empleado> empleados = new ArrayList<Empleado>();
//		empleados.add(empleado1);
//		empleados.add(empleado2);
//		
//		departamento.setEmpleados(empleados);
		
		departamento.setEmpleados(Arrays.asList(empleado1, empleado2));
		
		manager.persist(departamento);
	}
	
	private void listarEmpleados() {
		List<Empleado> resultList = manager.createQuery("Select a from Empleado a", Empleado.class).getResultList();
		
		System.out.println("Nro de Empleados: " + resultList.size());
		for (Empleado next : resultList){
			System.out.println("Siguiente empleado: " + next);
		}
	}
	
//	Muestra al Empleado con Id = 1
	private void mostrarEmpleado() {
//		Empleado empleado = manager.createQuery("Select a From Empleado a where a.id = 1", Empleado.class).getSingleResult();
		
		Empleado empleado = manager.find(Empleado.class, new Long(1));
		
		System.out.println(empleado);
	}
}
