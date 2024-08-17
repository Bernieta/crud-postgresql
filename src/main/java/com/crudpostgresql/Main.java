package com.crudpostgresql;

import com.crudpostgresql.controllers.UserController;
import com.crudpostgresql.entities.UserEntity;
import com.crudpostgresql.services.UserServices;

import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        UserController userController = new UserController(new UserServices());
        boolean exit = false;
        UserEntity user;
        int id;
        String names;
        int age;
        String email;
        do {
            System.out.println("Selecciona una opción");
            System.out.println("1. Mostrar usuarios");
            System.out.println("2. Mostrar usuario por id");
            System.out.println("3. Crear un nuevo usuario");
            System.out.println("4. Actualizar un usuario");
            System.out.println("5. Eliminar un usuario");
            System.out.println("6. Salir");
            System.out.print("Opción: ");
            int option = sc.nextInt();
            switch (option) {
                case 1 -> {
                    List<UserEntity> users = userController.getAllUsers();
                    if (users != null) {
                        System.out.println("+-----------------------------------------------------------------+");
                        users.forEach(System.out::println);
                        System.out.println("+-----------------------------------------------------------------+");
                    } else
                        System.out.println("No hay usuarios registrados");
                }
                case 2 -> {
                    System.out.print("Ingresa el id del usuario: ");
                    id = sc.nextInt();
                    user = userController.getUserById(id);
                    String message = (user != null) ? "Usuario: " + user : "No existe el usuario con el id = " + id;
                    System.out.println("+-----------------------------------------------------------------+");
                    System.out.println(message);
                    System.out.println("+-----------------------------------------------------------------+");
                }
                case 3 -> {
                    System.out.print("Ingresa el nombre: ");
                    names = sc.next();
                    System.out.print("Ingresa la edad: ");
                    age = sc.nextInt();
                    System.out.print("Ingresa el email: ");
                    email = sc.next();
                    user = userController.createUser(new UserEntity(names, age, email));
                    String message = (user != null) ? "Usuario creado: " + user : "No fue posible crear el usuario";
                    System.out.println("+-----------------------------------------------------------------+");
                    System.out.println(message);
                    System.out.println("+-----------------------------------------------------------------+");
                }
                case 4 -> {
                    System.out.print("Ingresa el id del usuario a actualizar: ");
                    id = sc.nextInt();
                    user = userController.getUserById(id);
                    if (user == null) {
                        System.out.println("+-----------------------------------------------------------------+");
                        System.out.println("No existe el usuario con el id = " + id);
                        System.out.println("+-----------------------------------------------------------------+");
                        continue;
                    }
                    System.out.println("+-----------------------------------------------------------------+");
                    System.out.println("Usuario a actualizar: " + user);
                    System.out.println("+-----------------------------------------------------------------+");
                    System.out.print("Ingresa el nombre: ");
                    names = sc.next();
                    System.out.print("Ingresa la edad: ");
                    age = sc.nextInt();
                    System.out.print("Ingresa el email: ");
                    email = sc.next();
                    user = userController.updateUser(new UserEntity(id, names, age, email));
                    String message = (user != null) ? "Usuario actualizado: " + user
                            : "No fue posible actualizar el usuario";
                    System.out.println("+-----------------------------------------------------------------+");
                    System.out.println(message);
                    System.out.println("+-----------------------------------------------------------------+");
                }
                case 5 -> {
                    System.out.print("Ingresa el id del usuario a eliminar: ");
                    id = sc.nextInt();
                    user = userController.getUserById(id);
                    if (user == null) {
                        System.out.println("+-----------------------------------------------------------------+");
                        System.out.println("No existe el usuario con el id = " + id);
                        System.out.println("+-----------------------------------------------------------------+");
                        continue;
                    }
                    user = userController.deleteUser(id);
                    System.out.println("+-----------------------------------------------------------------+");
                    System.out.println("Usuario eliminado: " + user);
                    System.out.println("+-----------------------------------------------------------------+");
                }
                case 6 -> exit = true;
                default -> System.out.println("Opción invalida");
            }
        } while (!exit);
    }
}