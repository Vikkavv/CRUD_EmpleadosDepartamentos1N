package org.example;

import java.sql.SQLException;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Consola {

    private DAOEmpleado daoEmpleado;
    private DAODepartamento daoDepartamento;

    public Consola() throws SQLException {
        daoDepartamento = new DAODepartamento();
        daoEmpleado = new DAOEmpleado();
    }

    public String mainMenu(){
        String menu =
                """
                #############################################
                |         Departamentos y empleados         |
                #############################################
                |  1. Listar empleados                      |
                |  2. Listar departamentos                  |
                |  3. Listar empleados de un departamento   |
                |  4. Actualizar empleado                   |
                |  5. Actualizar departamento               |
                |  6. Eliminar empleado                     |
                |  7. Eliminar departamento                 |
                |  8. Eliminar empleados de un departamento |
                |  9. Insertar empleado                     |
                | 10. Insertar departamento                 |
                | 11. Salir del programa                    |
                #############################################
                """;
        return menu;
    }

    public String searchDeptMenu(){
        String menu =
                """
                #######################
                | Buscar Departamento |
                #######################
                ID o nombre del departamento: 
                """;
        return menu;
    }

    public String searchEmpMenu(){
        String menu =
                """
                ###################
                | Buscar Empleado |
                ###################
                ID o nombre del Empleado: 
                """;
        return menu;
    }

    public String insertEmpMenu(){
        String menu =
                """
                #####################
                | Insertar empleado |
                #####################
                Nombre: 
                """;
        return menu;
    }

    public String insertDeptMenu(){
        String menu =
                """
                #########################
                | Insertar departamento |
                #########################
                Nombre: 
                """;
        return menu;
    }

    public String listarEmpDeUnDeptMenu() throws SQLException {
        Scanner sc = new Scanner(System.in);
        System.out.println(searchDeptMenu());
        try{
            int result = sc.nextInt();
            return daoDepartamento.selectById(result).getEmpleados().toString();
        }catch(InputMismatchException e){
            String result = sc.nextLine();
            return daoDepartamento.selectByName(result).getEmpleados().toString();
        }
    }

    public DTODepartamento searchDpto() throws SQLException {
        Scanner sc = new Scanner(System.in);
        System.out.println(searchDeptMenu());
        try{
            int result = sc.nextInt();
            return daoDepartamento.selectById(result);
        }catch(InputMismatchException e){
            String result = sc.nextLine();
            return daoDepartamento.selectByName(result);
        }
    }

    public DTOEmpleado searchEmp() throws SQLException {
        Scanner sc = new Scanner(System.in);
        System.out.println(searchEmpMenu());
        try{
            int result = sc.nextInt();
            return daoEmpleado.selectById(result);
        }catch(InputMismatchException e){
            String result = sc.nextLine();
            return daoEmpleado.selectByName(result);
        }
    }

    public void scannAndPrintMenu() throws SQLException {
        boolean exit = false;
        while(!exit){
            System.out.println(mainMenu());
            Scanner scanner = new Scanner(System.in);
            int option;
            try{
                option = scanner.nextInt();
            }catch(InputMismatchException e){
                throw new RuntimeException("Introduce solo caracteres numéricos");
            }
            switch(option){
                case 1:
                    System.out.println(daoEmpleado.selectAll());
                    break;
                case 2:
                    System.out.println(daoDepartamento.selectAll());
                    break;
                case 3:
                    System.out.println(listarEmpDeUnDeptMenu());
                    break;
                case 4:
                    while (true){
                        DTOEmpleado emp = searchEmp();
                        System.out.printf("¿Este es el empleado que desea actualizar? \n %s(S/N)",emp);
                        Scanner sc = new Scanner(System.in);
                        if(sc.nextLine().equalsIgnoreCase("n")) break;
                        else{
                            System.out.println("¿Está seguro de que desea actualizar? (S/N)");
                            if(sc.nextLine().equalsIgnoreCase("n")) break;
                            else{
                                System.out.println("""
                                        ##################
                                        | Nuevos valores |
                                        ##################
                                        Nombre : 
                                        """);
                                emp.setNombre(sc.nextLine());
                                System.out.println("Edad: ");
                                emp.setEdad(sc.nextInt());
                                System.out.println("Id Departamento: ");
                                emp.setDptoId(sc.nextInt());
                                daoEmpleado.update(emp);
                                break;
                            }
                        }
                    }
                    break;
                case 5:
                    while (true){
                        DTODepartamento departamento = searchDpto();
                        System.out.printf("¿Este es el departamento que desea actualizar? \n %s(S/N)?", departamento);
                        Scanner sc = new Scanner(System.in);
                        if(sc.nextLine().equalsIgnoreCase("n")) break;
                        else{
                            System.out.println("¿Está seguro de que desea actualizar? (S/N)?");
                            if(sc.nextLine().equalsIgnoreCase("n")) break;
                            else{
                                System.out.println("""
                                        ################
                                        | Nuevo nombre |
                                        ################
                                        Nombre :
                                        """);
                                departamento.setNombre(sc.nextLine());
                                daoDepartamento.update(departamento);
                                break;
                            }
                        }
                    }
                    break;
                case 6:
                    while (true){
                        DTOEmpleado emp = searchEmp();
                        System.out.printf("¿Este es el empleado que desea eliminar? \n %s (S/N)", emp);
                        Scanner sc = new Scanner(System.in);
                        if(sc.nextLine().equalsIgnoreCase("n")) break;
                        else{
                            System.out.println("¿Está seguro de que desea eliminar? (S/N)");
                            if(sc.nextLine().equalsIgnoreCase("n")) break;
                            else{
                                daoEmpleado.delete(emp.getId());
                                break;
                            }
                        }
                    }
                    break;
                case 7:
                    while (true){
                        DTODepartamento departamento = searchDpto();
                        System.out.printf("¿Este es el departamento que desea eliminar?\n%s (S/N)", departamento);
                        Scanner sc = new Scanner(System.in);
                        if(sc.nextLine().equalsIgnoreCase("n")) break;
                        else{
                            System.out.println("¿Está seguro de que desea eliminar? (S/N)");
                            if(sc.nextLine().equalsIgnoreCase("n")) break;
                            else{
                                daoDepartamento.delete(departamento.getId());
                                break;
                            }
                        }
                    }
                    break;
                case 8:
                    while (true){
                        DTODepartamento departamento = searchDpto();
                        System.out.printf("¿Este es el departamento del que desea eliminar todos los empleados?\n %s (S/N)", departamento);
                        Scanner sc = new Scanner(System.in);
                        if(sc.nextLine().equalsIgnoreCase("n")) break;
                        else{
                            System.out.println("¿Está seguro de que los desea eliminar a todos? (S/N)");
                            String res = sc.nextLine();
                            if(res.equalsIgnoreCase("n")) break;
                            else if(res.equalsIgnoreCase("s")){
                                for(DTOEmpleado emp : departamento.getEmpleados()){
                                    daoEmpleado.delete(emp.getId());
                                }
                                break;
                            }
                        }
                    }
                    break;
                case 9:
                    Scanner sc = new Scanner(System.in);
                    DTOEmpleado empleado = new DTOEmpleado(null, 0, 0);
                    System.out.print(insertEmpMenu());
                    empleado.setNombre(sc.nextLine());
                    System.out.println("Edad: ");
                    empleado.setEdad(sc.nextInt());
                    System.out.println("Id de Departamento: ");
                    empleado.setDptoId(sc.nextInt());
                    daoEmpleado.insert(empleado);
                    sc.nextLine();
                    break;
                case 10:
                    Scanner sc2 = new Scanner(System.in);
                    DTODepartamento departamento = new DTODepartamento(null);
                    System.out.println(insertDeptMenu());
                    departamento.setNombre(sc2.nextLine());
                    daoDepartamento.insert(departamento);
                    break;
                case 11:
                    exit = true;
                    break;
            }
        }
    }
}
