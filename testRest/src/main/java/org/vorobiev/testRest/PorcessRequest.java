package org.vorobiev.testRest;




//import com.querydsl.core.types.Predicate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
//import org.springframework.data.querydsl.*;
//import org.springframework.data.querydsl.binding.QuerydslPredicate;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static java.lang.Thread.sleep;


@RestController
public class PorcessRequest {

    private final RepoRequest repoRequest;
    private final RepoUser repoUser;

    //@Value("#{systemProperties['appStatusMessage'] ?: 'no-message'}")
    @Value("${appStatusMessage:'no-message_passwd'}")
    private  String appStatusMessage;



    @Autowired
    public PorcessRequest(RepoRequest repoRequest, RepoUser repoUser) {
        this.repoRequest = repoRequest;
        this.repoUser = repoUser;
    }


    @GetMapping("/health")
    public ResponseEntity<String> checkHealth(){

        return new ResponseEntity<String>("{\""+appStatusMessage+"\": \"OK\"}", HttpStatus.OK);
    }

    @GetMapping("/version")
    public ResponseEntity<String> getVersion(){
        return new ResponseEntity<String>("{\"version\": \"1.098\"}", HttpStatus.OK);
    }


    @GetMapping("/request/{id}")
    public Request findRequestById(@PathVariable("id") Request request) {
        return request;
    }

    @GetMapping("/user/{id}")
    public User  findUserById(@PathVariable("id") User user) {
        return user;
    }

   /*@GetMapping("/requests")
    public Page<Request> findAllUsers(Pageable pageable) {
        return repoRequest.findAll(pageable);
    }*/

    @GetMapping(value = "/requests", params = { "sleepMs"})
    public Page<Request> findAllRequests(@RequestParam("sleepMs") String sleepMs,Pageable pageable) throws InterruptedException {
        int _sleep = 0;

            _sleep = (sleepMs==null||sleepMs.equals("")?0:Integer.parseInt(sleepMs));
            if (_sleep > 0) {
                sleep(_sleep, 0);
            }


        return repoRequest.findAll(pageable);
    }

    @GetMapping("/requests")
    public Page<Request> findAllRequests(Pageable pageable) throws InterruptedException {


        return repoRequest.findAll(pageable);
    }


    @GetMapping("/users")
    public Page<User> findAllUsers(Pageable pageable) {
        return repoUser.findAll(pageable);
    }


    @RequestMapping(path = "/user",
            method = RequestMethod.POST,
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)

    public ResponseEntity<String> postUser(@RequestBody  User user) {

        try {
            user.setId(0);
            user = repoUser.save(user);
            return new ResponseEntity<String>("{\"id\": \"" + user.getId() + "\"}", HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<String>("{\"fault\": \"" + e.getMessage() + "\"}", HttpStatus.INTERNAL_SERVER_ERROR);

        }
    }
    @RequestMapping(path = "/user",
            method = RequestMethod.PUT,
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)

    public ResponseEntity<String> putUser(@RequestBody  User user){

        try {
            user = repoUser.save(user);
            return new ResponseEntity<String>("{\"status\": \"Ok\"}", HttpStatus.OK);
        }
        catch (Exception e){
            e.printStackTrace();
            return new ResponseEntity<String>("{\"fault\": \""+e.getMessage()+"\"}", HttpStatus.INTERNAL_SERVER_ERROR);

        }



    }

    @DeleteMapping("/user/{id}")
    public ResponseEntity delUser(@PathVariable("id") int id){

        try{
            repoUser.deleteById(id);
            return new ResponseEntity<String>("{\"status\": \"Ok\"}", HttpStatus.OK);
        } catch (Exception e){
            e.printStackTrace();
            return new ResponseEntity<String>("{\"fault\": \""+e.getMessage()+"\"}", HttpStatus.INTERNAL_SERVER_ERROR);

        }

    }

    /*
    @GetMapping("/sortedrequests")
    public Page<Request> findAllUsersSortedByName() {
        Pageable pageable = PageRequest.of(0, 5, Sort.by("id"));
        return repoRequest.findAll(pageable);
    }
*/

   /* @GetMapping("/filteredusers")
    public Iterable<Request> getUsersByQuerydslPredicate(@QuerydslPredicate(root = Request.class) Predicate predicate)
    {
        return repoRequest.findAll(predicate);
    }
*/

    @RequestMapping(value= "/request/{message} ",method = RequestMethod.POST)
    public long  postRequest(String message)
    {
        Request req = new Request(message);
        repoRequest.save(req);
        return req.getId();
    }

    @RequestMapping(value= "/request/{id} ",method = RequestMethod.DELETE)
    public void  deleteRequest(int id)
    {

      //  repoRequest.deleteById(id);
        repoRequest.deleteById(id);

    }


}


