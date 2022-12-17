package hiber;

import hiber.config.AppConfig;
import hiber.model.Car;
import hiber.model.User;
import hiber.service.UserService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.sql.SQLException;
import java.util.List;

public class MainApp {
   public static void main(String[] args) throws SQLException {
      AnnotationConfigApplicationContext context = 
            new AnnotationConfigApplicationContext(AppConfig.class);

      User user1 = new User("Valentin", "Fedorov", "grind33@mail.ru");
      User user2 = new User("Ksenia", "Boldyreva", "ks.bokdyreva@bk.ru");
      User user3 = new User("Natalia", "Fedorova", "NF@mail.ru");
      User user4 = new User("Igor", "Pereverzev", "p668@mail.ru");
      Car car1 = new Car(1, "Almera");
      Car car2 = new Car(2, "Teana");
      Car car3 = new Car(3, "Bluebird");
      Car car4 = new Car(4, "Macan");

      UserService userService = context.getBean(UserService.class);


      user1.setCarEmp(car1);
      user2.setCarEmp(car2);
      user3.setCarEmp(car3);
      user4.setCarEmp(car4);
      userService.add(user1);
      userService.add(user2);
      userService.add(user3);
      userService.add(user4);

//      userService.add(new User("User1", "Lastname1", "user1@mail.ru"));
//      userService.add(new User("User2", "Lastname2", "user2@mail.ru"));
//      userService.add(new User("User3", "Lastname3", "user3@mail.ru"));
//      userService.add(new User("User4", "Lastname4", "user4@mail.ru"));




      List<User> users = userService.listUsers();
      for (User user : users) {
         System.out.println("Id = "+user.getId());
         System.out.println("First Name = "+user.getFirstName());
         System.out.println("Last Name = "+user.getLastName());
         System.out.println("Email = "+user.getEmail());
         System.out.println("Car model = " + user.getCarEmp());
         System.out.println();
      }

      UserService userService2 = context.getBean(UserService.class);

      List<User> users2 = userService2.getUserByModelSeries(2, "Teana");
      for (User user : users2) {
         System.out.println("Id = "+user.getId());
         System.out.println("First Name = "+user.getFirstName());
         System.out.println("Last Name = "+user.getLastName());
         System.out.println("Email = "+user.getEmail());
         System.out.println();
      }


      context.close();


   }
}
