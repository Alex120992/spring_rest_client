package ru.zateev.spring.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import ru.zateev.spring.rest.entity.Employee;

import java.util.List;

@Component
public class Communication {
    @Autowired
    private RestTemplate restTemplate;
    private final String URL = "http://localhost:8080/api/employees";

    public List<Employee> getAllEmployees() {

        ResponseEntity<List<Employee>> responseEntity = restTemplate.exchange(URL, HttpMethod.GET, null
                , new ParameterizedTypeReference<List<Employee>>() {
                });
        // 1 параметр - URL
        // , второй вид запроса, 3 - тело
        // , которое можно добавить к http request
        // , тип возвращаемых данных;
        List<Employee> allEmployees = responseEntity.getBody();
        // методя возвращающий список работников
        return allEmployees;
    }

    public Employee getEmployee(int id) {

        Employee employee = restTemplate.getForObject(URL+"/"+id,Employee.class );

        return employee;
    }

    public void saveEmployee(Employee employee) {
        int id = employee.getId();

        if(id == 0) {
            ResponseEntity<String> responseEntity = restTemplate.postForEntity(URL, employee, String.class);
            // String, т.к. возвращаемый объект будет возвращаться в видет json,
            // второй параметр, что мы добавляем в тело метода post

            System.out.println("new Employee was added "+ responseEntity.getBody());
        }
        else {
            restTemplate.put(URL, employee);
            System.out.println("Employee was changed "+ employee.getId());
        }
    }

    public void deleteEmployee (int id){
        System.out.println("delete employee"+" "+id);
        restTemplate.delete(URL+"/"+id);

    }
}
