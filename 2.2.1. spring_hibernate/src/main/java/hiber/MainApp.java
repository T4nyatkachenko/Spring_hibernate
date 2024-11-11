package hiber;

import hiber.config.AppConfig;
import hiber.model.Car;
import hiber.model.User;
import hiber.service.UserService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.sql.SQLException;
import java.util.List;
//?verifyServerCertificate=false&useSSL=false&requireSSL=false&useLegacyDatetimeCode=false&amp&serverTimezone=UTC
public class MainApp {
   public static void main(String[] args) throws SQLException {
      AnnotationConfigApplicationContext context = 
            new AnnotationConfigApplicationContext(AppConfig.class);

      UserService userService = context.getBean(UserService.class);

     // userService.add(new User("User1", "Lastname1", "user1@mail.ru"));
     // userService.add(new User("User2", "Lastname2", "user2@mail.ru"));
     // userService.add(new User("User3", "Lastname3", "user3@mail.ru"));
     // userService.add(new User("User4", "Lastname4", "user4@mail.ru"));

      userService.add(new User("User1", "Lastname1", "user1@mail.ru", new Car("BMW", 777)));
      userService.add(new User("User2", "Lastname2", "user2@mail.ru", new Car("Ford", 444)));
      userService.add(new User("User3", "Lastname3", "user3@mail.ru", new Car("Opel", 111)));



      List<User> users = userService.listUsers();
      for (User user : users) {
         System.out.println("Id = "+user.getId());
         System.out.println("First Name = "+user.getFirstName());
         System.out.println("Last Name = "+user.getLastName());
         System.out.println("Email = "+user.getEmail());
         System.out.println(user.getCar().toString());
         System.out.println();
      }

      System.out.println("Находим User по модели и серии Car:");

      User us = userService.findUserByCar("Ford", 444);
      System.out.println("Модель: Ford, серия: 444: ");
      System.out.println(us.getFirstName() + ", " +  us.getLastName());

      context.close();
   }
}
