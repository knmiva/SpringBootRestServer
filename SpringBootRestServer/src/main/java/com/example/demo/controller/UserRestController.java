package com.example.demo.controller;

/*@RestController
@CrossOrigin
@RequestMapping(value = "/rest/user/")
public class UserRestController {

    private final UserService userService;

    public UserRestController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping(value = "{id}")
    public ResponseEntity<DtoForClient> getUserById(@PathVariable(name = "id") Long id) {
        User user = userService.getUserById(id);
        DtoForClient dtoForClient = new DtoForClient();
        if (user == null) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(DtoForClient.getDtoForClient(user), HttpStatus.OK);
    }
}*/
