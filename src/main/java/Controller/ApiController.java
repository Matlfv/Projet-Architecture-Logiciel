package Controller;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@SpringBootApplication
@RestController
public class ApiController {

    public static void main(String[] args) {
        SpringApplication.run(ApiController.class, args);
    }

    @GetMapping("/hello")
    public String hello(@RequestParam(value = "name", defaultValue = "World") String name) {
        return String.format("Hello %s!", name);
    }

    /**
     * GET : Return rover position by roverId
     * @param roverId
     * @return
     */
    @RequestMapping(path = "/rover/position" , method = RequestMethod.GET)
    @ResponseBody
    public String getPositionOfRover(@RequestBody Integer roverId) {
        return (HttpStatus.OK + "Rover Position");
    }

    /**
     * GET : Rover move by command
     * @param command
     * @return
     */
    @RequestMapping(path = "/rover/move" , method = RequestMethod.POST)
    @ResponseBody
    public String handlePositionOfRover(@RequestBody String command) {
        return (HttpStatus.OK + "Rover has moved with the command : " + command);
    }

    /**
     * GET : Rover statut Alive or Dead
     * @return
     */
    @RequestMapping(path = "/rover/alive" , method = RequestMethod.GET)
    @ResponseBody
    public Boolean getStatutRover(@RequestBody Integer roverId) {
       return true;
    }

}
