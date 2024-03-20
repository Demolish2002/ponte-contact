<h1>Database</h1>

<p>After cloning the project, we have to create a postgreSQl database using Docker.

To do this we first have to open a shell as administrator and then run the following command :
<br>
docker pull postgres<br>
Then we can create the database itself with the following command: <br> docker run -d --name postgresCont -p 5432:5432 -e POSTGRES_PASSWORD=pass123 postgres
<p>
<h1>Backend</h1>
<p>Once this is done we have to connect it to our backend via our IDE and the database connection is then established.
Make sure that the docker container is running when starting the backend.
</p>
<h1>Frontend</h1>
<p>

To launch the frontend we have to open a terminal in contact-frontend/contacts/src/app
and run npm i to download the necessary packages from the web. And then we can follow this up with an ng s
to serve our frontend. After this is done we can open a browser and go to http://localhost:4200 which is the url of the fronted.
</p>
<h1>Creating an admin</h1>
<p>

To get started we first have to create an admin user to do this we have to go http://localhost:4200/register
where we can register our first user. Please note that while registering only letters from the english alphabet will allow you to correctly login after the registration. Following this we have to go into the database and change the role of our user.
If you see a 1 under the role in the database change it zo zero if you see USER then change it to ADMIN and then save the state of the table.
Now that we have an admin we can change the accessibility of the user creation endpoint so that only the admin can create new users.
We have to find the SecurityConfigFile and remove the <br><br> .authorizeHttpRequests(auth -> auth
                        .requestMatchers(HttpMethod.POST, "/api/users")
                        .permitAll()
                )<br>
                
then we can uncomment the code below.
We have to do similar things in the UserController file as well remove or comment out<br><br>
@PreAuthorize("permitAll")
<br>
<br>
and uncomment the code below it. With that being done we can interact with program from the frontend. 
</p>