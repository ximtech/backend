# Project description

Microservice for working with currencies in ISO 4217 standart.
Information in wikipedia: https://en.wikipedia.org/wiki/ISO_4217#Active_codes

# User guide

1. Clone "frontend" project and add root pom.xml as a Maven project in your IDE.
2. Run Maven and generate project jar.
3. Repeate 1 and 2 with "backend" project.
4. Create output -> frontend and backend folders.
5. Copy rest-1.0.jar to backend folder and frontend-1.0.jar to frontend folder
6. Start H2 base. Go to \.m2\repository\com\h2database\h2\1.3.175 and start h2-1.3.175.jar
7. Then DB login page shoud appear in the browser


![alt text](http://img.brothersoft.com/screenshots/softimage/h/h2_database_engine-328487-1263364219.jpeg)

8. Start backend: output -> backend -> open cmd in current folder -> enter: java –jar rest-1.0.jar (Tomcat will start on localhost:8090)
9. Start frontend: output -> frontend -> open cmd in current folder -> enter: java –jar frontend-1.0.jar (Tomcat will start on localhost:8080)
10. Open browser and go to localhost:8080
