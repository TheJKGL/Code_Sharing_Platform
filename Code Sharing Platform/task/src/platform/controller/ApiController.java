package platform.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import platform.model.InputCode;
import platform.model.SharingCode;
import platform.service.CodeService;

import java.util.List;
import java.util.UUID;

@RestController
public class ApiController {

    @Autowired
    private CodeService service;

    @GetMapping("/api/code/{N}")
    public ResponseEntity<?> getApi(@PathVariable String N) {
        return new ResponseEntity<>(service.findSharingCodeById(N), HttpStatus.OK);
    }

    @PostMapping(value = "/api/code/new", consumes = "application/json")
    public ResponseEntity<String> postApiNew(@RequestBody InputCode inputCode) {
        String id = service.save(inputCode).getId();
        return new ResponseEntity<>("{\"id\":" + "\"" + id + "\"" + "}", HttpStatus.OK);
    }

    @GetMapping("/api/code/latest")
    public ResponseEntity<List<SharingCode>> getLatest() {
        return new ResponseEntity<>(service.getLatest(), HttpStatus.OK);
    }
}
