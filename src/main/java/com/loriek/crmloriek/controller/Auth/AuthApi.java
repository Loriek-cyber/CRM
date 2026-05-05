package com.loriek.crmloriek.controller.Auth;

import com.loriek.crmloriek.model.user.UniqueIndetifierService;
import com.loriek.crmloriek.model.user.User;
import com.loriek.crmloriek.model.user.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/api/auth")
public class AuthApi {

    private final UserService userService;
    private final UniqueIndetifierService uniqueIndetifierService;

    public AuthApi(UserService userService, UniqueIndetifierService uniqueIndetifierService) {
        this.userService = userService;
        this.uniqueIndetifierService = uniqueIndetifierService;
    }

    @PostMapping("/login")
    public ResponseEntity<?> access(@RequestBody Map<String, String> userDetails) {
        String username = userDetails.get("username");
        String password = userDetails.get("password");

        if(userService.validateCredentials(username, password)){
            User user = userService.findByUsername(username).get(); // The User exists becouse of previous validation
            return ResponseEntity.ok(uniqueIndetifierService.create(user));
        }
        else {
            return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE)
                    .body(Map.of("status", "e   rror", "message", "Invalid username or password"));
        }
    }


    @PostMapping("/verify-uuid")
    public boolean verifyUuid(@RequestBody Map<String, String> storege) {
        String uuid = storege.get("uuid");
        return uniqueIndetifierService.verify(uuid);
    }



    @PostMapping("/verify")
    public ResponseEntity<?> verify(@RequestBody Map<String, String> credentials) {
        String username = credentials.get("username");
        String password = credentials.get("password");

        if (userService.validateCredentials(username, password)) {
            return ResponseEntity.ok(Map.of("status", "success", "message", "Credentials are valid"));
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body(Map.of("status", "error", "message", "Invalid username or password"));
        }
    }
}
