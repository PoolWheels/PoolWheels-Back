package com.PoolWheels.controller;

import com.PoolWheels.dto.*;
import com.PoolWheels.entities.*;
import com.PoolWheels.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.NoSuchElementException;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping( "/api/v1/user" )
public class UserController {

    @Autowired
    private final UserService userService;

    ModelMapper modelMapper = new ModelMapper();

    /**
     * It is the User Controller class constructor
     *
     * @param userService The Users service
     */
    public UserController (UserService userService) {
        this.userService = userService;
    }

    /**
     * It gets all the driver users from the database and returns them as a list of UserDriverDTO objects
     *
     * @return A list of UserDriverDTO objects.
     */
    @GetMapping("/driverusers")
    public ResponseEntity<List<UserDriverDTO>> getAllDriverUsers () {
        try {
            List<UserDriver> userDriverList = userService.getAllDriver();
            ArrayList<UserDriverDTO> data = new ArrayList<UserDriverDTO>();
            if (!userDriverList.isEmpty()) {
                for (UserDriver ud : userDriverList) {
                    data.add(modelMapper.map(ud, UserDriverDTO.class));
                }
            }
            return new ResponseEntity<List<UserDriverDTO>> (data, HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<List<UserDriverDTO>> (HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /**
     * This function is used to get a driver user by id
     *
     * @param id The id of the driver user you want to get.
     * @return A UserDriverDTO object
     */
    @GetMapping("/driverusers/{id}")
    public ResponseEntity<UserDriverDTO> getDriverUserById(@PathVariable String id) {
        try {
            UserDriver userDriverTemp = userService.findByIdDriver(id);
            if (userDriverTemp != null) {
                return new ResponseEntity<UserDriverDTO>(modelMapper.map(userDriverTemp, UserDriverDTO.class), HttpStatus.OK);
            } else {
                return new ResponseEntity<UserDriverDTO>(HttpStatus.NOT_FOUND);
            }
        } catch (NoSuchElementException nsee) {
            System.out.println("\nDoesn´t exist a driver user with the specified id");
            return new ResponseEntity<UserDriverDTO>(HttpStatus.NOT_FOUND);
        }catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<UserDriverDTO>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /**
     * The function creates a new driver user and returns the newly created user driver object
     *
     * @param newUserDriverDTO This is the object that will be passed in the request body.
     * @return A ResponseEntity object is being returned.
     */
    @PostMapping("/driverusers")
    public ResponseEntity<UserDriverDTO> createNewDriverUser (@RequestBody UserDriverDTO newUserDriverDTO) {
        try {
            UserDriver userDriverTemp = userService.createUserDriver(modelMapper.map(newUserDriverDTO, UserDriver.class));
            if (userDriverTemp != null) {
                return new ResponseEntity<UserDriverDTO>(modelMapper.map(userDriverTemp, UserDriverDTO.class), HttpStatus.CREATED);
            } else {
                return new ResponseEntity<UserDriverDTO>(HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<UserDriverDTO>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    /**
     * This function updates a driver user in the database
     *
     * @param UserDriverDTO The object that will be sent to the server.
     * @param id The id of the user to be updated.
     * @return A response entity with a user driver DTO and a status code of OK.
     */
    @PutMapping("/driverusers/{id}")
    public ResponseEntity<UserDriverDTO> updateDriverUser (@RequestBody UserDriverDTO UserDriverDTO, @PathVariable String id) {
        try {
            UserDriver userDriverTemp = userService.updateDriver(modelMapper.map(UserDriverDTO, UserDriver.class), id);
            if (userDriverTemp != null) {
                return new ResponseEntity<UserDriverDTO>(modelMapper.map(userDriverTemp, UserDriverDTO.class), HttpStatus.OK);
            } else {
                return new ResponseEntity<UserDriverDTO>(HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<UserDriverDTO>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /**
     * It deletes a driver user from the database.
     *
     * @param id The id of the driver user to be deleted.
     * @return A boolean value is being returned.
     */
    @DeleteMapping("/driverusers/{id}")
    public ResponseEntity<Boolean> deleteDriverUser (@PathVariable String id) {
        try {
            boolean deleted = userService.deleteDriver(id);
            if (deleted) {
                return new ResponseEntity<Boolean>(true, HttpStatus.OK);
            } else {
                return new ResponseEntity<Boolean>(false, HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<Boolean>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    /* Traveler users services */

    /**
     * This function is used to get all the traveler users from the database
     *
     * @return A list of all the traveler users in the database.
     */
    @GetMapping("/travelerusers")
    public ResponseEntity<List<UserTravelerDTO>> getAllTravelerUsers () {
        try {
            List<UserTraveler> userTravelerList = userService.getAllTraveler();
            ArrayList<UserTravelerDTO> data = new ArrayList<UserTravelerDTO>();
            if (!userTravelerList.isEmpty()) {
                for (UserTraveler ud : userTravelerList) {
                    data.add(modelMapper.map(ud, UserTravelerDTO.class));
                }
            }
            return new ResponseEntity<List<UserTravelerDTO>> (data, HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<List<UserTravelerDTO>> (HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /**
     * This function is used to get a traveler user by id
     *
     * @param id The id of the user to be retrieved.
     * @return A UserTravelerDTO object is being returned.
     */
    @GetMapping("/travelerusers/{id}")
    public ResponseEntity<UserTravelerDTO> getTravelerUserById(@PathVariable String id) {
        try {
            UserTraveler userTravelerTemp = userService.findByIdTraveler(id);
            if (userTravelerTemp != null) {
                return new ResponseEntity<UserTravelerDTO>(modelMapper.map(userTravelerTemp, UserTravelerDTO.class), HttpStatus.OK);
            } else {
                return new ResponseEntity<UserTravelerDTO>(HttpStatus.NOT_FOUND);
            }
        } catch (NoSuchElementException nsee) {
            System.out.println("\nDoesn´t exist traveler users with the specified id");
            return new ResponseEntity<UserTravelerDTO>(HttpStatus.NOT_FOUND);
        }catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<UserTravelerDTO>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /**
     * This function creates a new traveler user and returns the newly created user's information in the response body
     *
     * @param newUserTravelerDTO This is the object that will be passed in the request body.
     * @return A new user traveler is being returned.
     */
    @PostMapping("/travelerusers")
    public ResponseEntity<UserTravelerDTO> createNewTravelerUser (@RequestBody UserTravelerDTO newUserTravelerDTO) {
        try {
            UserTraveler userTravelerTemp = userService.createUserTraveler(modelMapper.map(newUserTravelerDTO, UserTraveler.class));
            if (userTravelerTemp != null) {
                return new ResponseEntity<UserTravelerDTO>(modelMapper.map(userTravelerTemp, UserTravelerDTO.class), HttpStatus.CREATED);
            } else {
                return new ResponseEntity<UserTravelerDTO>(HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<UserTravelerDTO>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    /**
     * This function updates a traveler user in the database
     *
     * @param UserTravelerDTO The object that will be updated.
     * @param id The id of the user to be updated.
     * @return The method returns a ResponseEntity<UserTravelerDTO> object.
     */
    @PutMapping("/travelerusers/{id}")
    public ResponseEntity<UserTravelerDTO> updateTravelerUser (@RequestBody UserTravelerDTO UserTravelerDTO, @PathVariable String id) {
        try {
            UserTraveler userTravelerTemp = userService.updateTraveler(modelMapper.map(UserTravelerDTO, UserTraveler.class), id);
            if (userTravelerTemp != null) {
                return new ResponseEntity<UserTravelerDTO>(modelMapper.map(userTravelerTemp, UserTravelerDTO.class), HttpStatus.OK);
            } else {
                return new ResponseEntity<UserTravelerDTO>(HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<UserTravelerDTO>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /**
     * This function deletes a traveler user from the database
     *
     * @param id The id of the traveler user to be deleted.
     * @return A boolean value is being returned.
     */
    @DeleteMapping("/travelerusers/{id}")
    public ResponseEntity<Boolean> deleteTravelerUser (@PathVariable String id) {
        try {
            boolean deleted = userService.deleteTraveler(id);
            if (deleted) {
                return new ResponseEntity<Boolean>(true, HttpStatus.OK);
            } else {
                return new ResponseEntity<Boolean>(false, HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<Boolean>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/getUserByEmail/{userEmail}")
    public ResponseEntity<HashMap<String, String>> getUsersByEmail(@PathVariable String userEmail) {
        try {
            User user = userService.findByEmail(userEmail);
            if (user != null) {
                String userRol = user.getRol();
                HashMap<String, String> hashMap = new HashMap<>();
                hashMap.put("id", user.getId());
                hashMap.put("rol", user.getRol());
                hashMap.put("name", user.getName());
                return new ResponseEntity<>(hashMap, HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
