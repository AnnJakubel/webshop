package ee.annjakubel.webshop.controller;

import ee.annjakubel.webshop.model.database.Person;
import ee.annjakubel.webshop.model.request.input.LoginData;
import ee.annjakubel.webshop.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthenticationController {

    @Autowired
    PersonRepository personRepository;

    @PostMapping
    public ResponseEntity<Boolean> signup(@RequestBody Person person) {
        //person.setPassword(); hashimine
        personRepository.save(person);
        return ResponseEntity.ok().body(true);
    }

    @PostMapping("login") // {email: "m@m.com, password: "123"}
    public ResponseEntity<Boolean> login(@RequestBody LoginData loginData) {
        //person.setPassword();
        return ResponseEntity.ok().body(true);
    }
}
