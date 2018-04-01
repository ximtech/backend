# Project description

Microservice for working with currencies in ISO 4217 standart.
Information in wikipedia: https://en.wikipedia.org/wiki/ISO_4217#Active_codes

# User guide

1. Clone "backend" project and add root pom.xml as a Maven project in your IDE.
2. Start H2 base. Go to \.m2\repository\com\h2database\h2\1.3.175 and start h2-1.3.175.jar
3. Then DB login page shoud appear in the browser


![alt text](http://img.brothersoft.com/screenshots/softimage/h/h2_database_engine-328487-1263364219.jpeg)

4. Run Maven and generate project jar.
5. Repeate 1 and 4 with "frontend" project.
6. Create folder output -> frontend and backend folders.
7. Copy backend-1.0.jar to backend folder and frontend-1.0.jar to frontend folder
8. Start backend: output -> backend -> open cmd in current folder -> enter: java –jar backend-1.0.jar (Tomcat will start on localhost:8090)
9. Start frontend: output -> frontend -> open cmd in current folder -> enter: java –jar frontend-1.0.jar (Tomcat will start on localhost:8080)
10. Open browser and go to localhost:8080
