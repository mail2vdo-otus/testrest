package org.vorobiev.testRest;




//import com.querydsl.core.types.Predicate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.*;
//import org.springframework.data.querydsl.*;
//import org.springframework.data.querydsl.binding.QuerydslPredicate;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

import static java.lang.Thread.sleep;


@RestController
public class PorcessRequest {

    private final RepoRequest repoRequest;
    private final RepoUser repoUser;
    private final RepoClient repoClient;
    private final RepoDeal repoDeal;

    //@Value("#{systemProperties['appStatusMessage'] ?: 'no-message'}")
    @Value("${appStatusMessage:'no-message_passwd'}")
    private  String appStatusMessage;



    @Autowired
    public PorcessRequest(RepoRequest repoRequest, RepoUser repoUser, RepoDeal repoDeal, RepoClient repoClient) {
        this.repoRequest = repoRequest;
        this.repoUser = repoUser;
        this.repoClient = repoClient;
        this.repoDeal = repoDeal;
    }


    @GetMapping("/health")
    public ResponseEntity<String> checkHealth(){

        return new ResponseEntity<String>("{\""+appStatusMessage+"\": \"OK\"}", HttpStatus.OK);
    }

    @GetMapping("/version")
    public ResponseEntity<String> getVersion() throws InterruptedException {
        return new ResponseEntity<String>("{\"version\": \""+getVersionCode(1)+"''\"}", HttpStatus.OK);
    }

    @Cacheable("version")
    @GetMapping("/version/{key}")
    public String getVersionCode(@PathVariable("key") int key) throws InterruptedException {
        sleep(1000, 0);
        return "1.099";
    }
  /*  @GetMapping("/request/{id}")
    public Request dddfindRequestById(@PathVariable Map<String, String> pathVarsMap) throws InterruptedException  {

        int requestId = Integer.parseInt(pathVarsMap.get("id"));

            sleep(5000, 0);




        return repoRequest.getOne(requestId );
    }
*/
  @Cacheable("request")
  @GetMapping("/request/{id}")
  public Request findRequestById(@PathVariable("id") Request request) throws InterruptedException {

      return request;
  }
    @Cacheable("request")
    @GetMapping("/request_s/{id}")
    public Request findRequestById_s(@PathVariable("id") Request request) throws InterruptedException {
        sleep(1000, 0);
        return request;
    }



    @Cacheable("user")
    @GetMapping("/user/{id}")
    public User  findUserById(@PathVariable("id") User user) {

        return user;
    }

   /*@GetMapping("/requests")
    public Page<Request> findAllUsers(Pageable pageable) {
        return repoRequest.findAll(pageable);
    }*/
   @Cacheable("requests")
    @GetMapping(value = "/requests", params = { "sleepMs"})
    public Page<Request> findAllRequests(@RequestParam("sleepMs") String sleepMs,Pageable pageable) throws InterruptedException {
        int _sleep = 0;

            _sleep = (sleepMs==null||sleepMs.equals("")?0:Integer.parseInt(sleepMs));
            if (_sleep > 0) {
                sleep(_sleep, 0);
            }


        return repoRequest.findAll(pageable);
    }

    @Cacheable("requests")
    @GetMapping("/requests")
    public Page<Request> findAllRequests(Pageable pageable) throws InterruptedException {


        return repoRequest.findAll(pageable);
    }

    @Cacheable("users")
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

    @Cacheable("users")
    @RequestMapping(path = "/filteredUser",
            method = RequestMethod.GET,
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)

    public List<User> filteredUser(@RequestBody  User user) throws InterruptedException {
        sleep(1000, 0);
        ExampleMatcher customExampleMatcher = ExampleMatcher.matching()
                .withMatcher("firstName", ExampleMatcher.GenericPropertyMatchers.contains().ignoreCase())
                .withMatcher("surName", ExampleMatcher.GenericPropertyMatchers.contains().ignoreCase())
                .withIgnorePaths("id", "birthDate");;
        Example<User> example = Example.of(user, customExampleMatcher);
        return repoUser.findAll(example);



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
/*
   @GetMapping("/filteredusers")
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

    @GetMapping("/profile")
    public ResponseEntity<String> getProfile(@RequestHeader Map<String, String> headers)
    {
       String json ="";
        for (String key : headers.keySet()) {
           json  += (key.toLowerCase().startsWith("x-profile-")?(json.equals("")?"":",")+"\""+key.substring(10)+"\":\""+headers.get(key)+"\"":"");

        }

        json = "{"+json+"}";
        return new ResponseEntity<String>(json, HttpStatus.OK);


    }




    @Cacheable("clients")
    @GetMapping("/clients")
    public Page<Client> findAllClients(Pageable pageable) {
        return repoClient.findAll(pageable);
    }


    @RequestMapping(path = "/client",
            method = RequestMethod.POST,
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)

    public ResponseEntity<String> postClent(@RequestBody  Client client) {

        try {
            client.setClientid(0);
            client = repoClient.save(client);
            return new ResponseEntity<String>("{\"id\": \"" + client.getClientid() + "\"}", HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<String>("{\"fault\": \"" + e.getMessage() + "\"}", HttpStatus.INTERNAL_SERVER_ERROR);

        }
    }




    @GetMapping("/deals")
    public Page<Deal> findAllDeals(Pageable pageable) {
        return repoDeal.findAll(pageable);
    }


    @RequestMapping(path = "/deal/{clientid}",
            method = RequestMethod.POST,
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)

    public ResponseEntity<String> postDeal(@PathVariable int clientid,  @RequestBody  Deal deal) {

        try {

            deal.setClient(repoClient.findById(clientid).get());
            deal.setDealid(0);
            repoDeal.save(deal);
            return  new ResponseEntity<String>("{\"id\": \"" + deal.getDealid() + "\"}", HttpStatus.OK);



        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<String>("{\"fault\": \"" + e.getMessage() + "\"}", HttpStatus.INTERNAL_SERVER_ERROR);

        }
    }





    @RequestMapping(path = "/request/{dealid}",
            method = RequestMethod.POST,
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)

    public ResponseEntity<String> postRequest(@PathVariable int dealid , @RequestBody  Request request) {

        try {

            request.setDeal(repoDeal.findById(dealid).get());
            request.setId(0);
            repoRequest.save(request);
            return  new ResponseEntity<String>("{\"id\": \"" + request.getId() + "\"}", HttpStatus.OK);



        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<String>("{\"fault\": \"" + e.getMessage() + "\"}", HttpStatus.INTERNAL_SERVER_ERROR);

        }
    }



    @RequestMapping(path = "/request",
            method = RequestMethod.POST,
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)

    public ResponseEntity<String> postRequest(@RequestBody  Request request) {

        try {


            request.setId(0);
            repoRequest.save(request);
            return  new ResponseEntity<String>("{\"id\": \"" + request.getId() + "\"}", HttpStatus.OK);



        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<String>("{\"fault\": \"" + e.getMessage() + "\"}", HttpStatus.INTERNAL_SERVER_ERROR);

        }
    }

}


