package com.v2v.dao;

@Repository
public class daoimplementation implements parcelcheckdaos {
    @Autowired
    JdbcTemplate jdbcTemplate;

    //LOGIN VALIDATION
    public boolean  loginValidation(String username,String password) {
        try {
            trackingstatus username2 = (trackingstatus) jdbcTemplate.queryForObject("select * from login where username=?",
                    new Object[]{username}, new BeanPropertyRowMapper<trackingstatus>(trackingstatus.class));
            trackingstatus encrypass= (trackingstatus)jdbcTemplate.queryForObject("select password from login where username=?",
                    new Object[]{username},new BeanPropertyRowMapper<trackingstatus>(trackingstatus.class));
            String str = "1p/RCrsiRPZxFSDgg2aT6GPjX9SbHEEoIkbz4Zhv4bA=";
            boolean passwordMatch = verifyUserPassword(password,encrypass.getPassword(), str);

            if (passwordMatch) {
                return true;
            }
        }
        catch (EmptyResultDataAccessException e) {
            return false;
        }
        return false;
    }

    //USER REGISTRATION
    public int createProfile(trackingstatus parceltrackcheckmodel) {
        KeyHolder keyHolder=new GeneratedKeyHolder();
        String user= trackingstatus.getUsername();
        String pass= usercheck(trackingstatus.getPassword());
        //System.out.println(pass);
        jdbcTemplate.update((Connection connection)->{

            PreparedStatement preparedStatement;
            preparedStatement = connection.prepareStatement("insert into login(username,password)values(?,?)", Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1,trackingstatus.getUsername());
            preparedStatement.setString(2,pass);

            return preparedStatement;
        },keyHolder);
        return keyHolder.getKey().intValue();
    }

    //PASSWORD ENCRYPTION
    public String usercheck(String pass)
    {

        String str = "1p/RCrsiRPZxFSDgg2aT6GPjX9SbHEEoIkbz4Zhv4bA=";
        String mySecurePassword = PasswordUtils.generateSecurePassword(pass, str);
        return mySecurePassword;
    }



    //REGISTRATION FOR NEW PARCEL
    public Object createNewParcel(ParcelRegistermodel parcelRegistermodel) {

        //TRACK ID GENERATION
        int m = (int)Math.pow(10,10-1);
        int b= m  + new Random().nextInt(9 * m);
        KeyHolder keyHolder=new GeneratedKeyHolder();
        jdbcTemplate.update((Connection connection)->{

            PreparedStatement preparedStatement;
            preparedStatement = connection.prepareStatement("insert into register(track_id,name,source_address,source_city,destination_address,destination_city,phone_number,kilometer,weight,amount,veh)values(?,?,?,?,?,?,?,?,?,?,?)", Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(2, parcelRegistermodel.getName());
            preparedStatement.setString(3, parcelRegistermodel.getSource_address());
            preparedStatement.setString(4, parcelRegistermodel.getSource_city());
            preparedStatement.setString(5, parcelRegistermodel.getDestination_address());


            //APPENDING DESTINATION CITY
            String str=parcelRegistermodel.getDestination_city();
            String str1 = parcelRegistermodel.getSource_city();
            preparedStatement.setString(6, str);
            String substr=str.substring(0,4);
            String a=substr+String.valueOf(b);
            preparedStatement.setString(1, a);
            preparedStatement.setString(7, parcelRegistermodel.getPhone_number());
           
           
            preparedStatement.setInt(8, parcelRegistermodel.getKilometer());
            preparedStatement.setInt(9, parcelRegistermodel.getWeight());
           
           preparedStatement.setInt(10, parcelRegistermodel.getAmount());
           String truckNo = jdbcTemplate.queryForObject(
                   "select vehicleNo from vehicle where source_city=? and destination_city=?",                   
                   String.class, str1,str);
           
           preparedStatement.setString(11,truckNo );
          
            
            return preparedStatement;
        },keyHolder);
        return keyHolder.getKey()==null?null:keyHolder.getKey().intValue();
    }


  //Track id + amount display method
    public List<ParcelRegisterSucessfulmodel> getProfiles(){
        List<ParcelRegisterSucessfulmodel> profileList = new ArrayList<>();
        Collection<Map<String,Object>> rows=null;
        rows=jdbcTemplate.queryForList("SELECT * FROM register  ORDER BY id;  ");
        rows.stream().map((row)-> {
            ParcelRegisterSucessfulmodel parcelRegisterSucessfulmodel = new ParcelRegisterSucessfulmodel();
            parcelRegisterSucessfulmodel.setId((int) row.get("id"));
            parcelRegisterSucessfulmodel.setTrackid((String) row.get("track_id"));
            parcelRegisterSucessfulmodel.setName((String) row.get("name"));
            parcelRegisterSucessfulmodel.setSource_city((String) row.get("source_city"));
            parcelRegisterSucessfulmodel.setDestination_city((String) row.get("destination_city"));
            parcelRegisterSucessfulmodel.setAmount((double) row.get("amount"));

            return parcelRegisterSucessfulmodel;
        }).forEach((ss)->{
            
            profileList.add(ss);
        });
        return profileList;
    }

    public ParcelTrackDetails getTrackIdDetails(String track_id) {
        List<ParcelTrackDetails> profileList = new ArrayList<>();
        ParcelTrackDetails parcelTrackStatus = new ParcelTrackDetails();
        
        try {
            String source = jdbcTemplate.queryForObject(
                    "select source_city from register where track_id=?",
                    String.class, track_id);
            String destination = jdbcTemplate.queryForObject(
                    "select destination_city from register where track_id=?",
                    String.class, track_id);
            String name = jdbcTemplate.queryForObject(
                    "select name from register where track_id=?",
                    String.class, track_id);
            String track = jdbcTemplate.queryForObject(
                    "select track_id from register where track_id=?",
                    String.class, track_id);
            int id = jdbcTemplate.queryForObject(
                    "select id from register where track_id=?",
                    int.class, track_id);
            double amount = jdbcTemplate.queryForObject(
                    "select amount from register where track_id=?",
                    double.class, track_id);
            
            parcelTrackStatus.setSource_city(source);
            parcelTrackStatus.setDestination_city(destination);
            parcelTrackStatus.setName(name);
            parcelTrackStatus.setId(id);
            parcelTrackStatus.setAmount(amount);
            parcelTrackStatus.setTrackid(track);
            
        }
        catch (EmptyResultDataAccessException e) {
            parcelTrackStatus.setErrormessages("Invalid Trackid");
        }
        return parcelTrackStatus;

    }


}
