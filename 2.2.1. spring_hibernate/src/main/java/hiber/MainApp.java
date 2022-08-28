package hiber;

import hiber.config.AppConfig;
import hiber.model.Car;
import hiber.model.User;
import hiber.service.CarService;
import hiber.service.UserService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.List;

public class MainApp {
   public static void main(String[] args) {
      AnnotationConfigApplicationContext context = 
            new AnnotationConfigApplicationContext(AppConfig.class);

      UserService userService = context.getBean(UserService.class);
      CarService carService = context.getBean(CarService.class);

      userService.add(new User("Ivan", "Ivanov", "user1@mail.ru"));
      userService.add(new User("Sergei", "Sergeev", "user2@mail.ru"));
      userService.add(new User("Olga", "Frolova", "user3@mail.ru"));
      userService.add(new User("Alina", "Demidova", "user4@mail.ru"));

      List<User> users = userService.listUsers();
      for (User user : users) {
         System.out.println("Id = "+user.getId());
         System.out.println("First Name = "+user.getFirstName());
         System.out.println("Last Name = "+user.getLastName());
         System.out.println("Email = "+user.getEmail());
         System.out.println();
      }

      carService.add(new Car("Opel", 404, users.get(2)));
      carService.add(new Car("Toyota", 505, users.get(0)));
      carService.add(new Car("Lada", 303, users.get(1)));
      carService.add(new Car("Ford", 202, users.get(3)));

      System.out.println("=======RESULT=======");
      System.out.println(carService.getOwner("Opel", 404));
      System.out.println(carService.getOwner("Toyota", 505));
      System.out.println(carService.getOwner("Ford", 202));
      System.out.println(carService.getOwner("Lada", 303));
      System.out.println("========RESULT======");

      context.close();
   }
}
