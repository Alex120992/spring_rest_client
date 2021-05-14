package ru.zateev.spring.rest;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import ru.zateev.spring.rest.configuration.MyConfig;
import ru.zateev.spring.rest.entity.Employee;

import java.util.List;

/**
 * Hello world!
 */
public class App {
    public static void main(String[] args)   {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(MyConfig.class);

        Communication communication = context.getBean("communication"
                , Communication.class);
//        List<Employee> allEmployees = communication.getAllEmployees();
//        Employee employee = communication.getEmployee(9);
//        System.out.println(employee);

//        Employee employee = new Employee("Ivan","Sokolov", "IT", 340);
//        employee.setId(10);
//        communication.saveEmployee(employee);



        communication.deleteEmployee(9);
    }
}
