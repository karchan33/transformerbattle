# Battle of Transformers

This is an application built to find the winning transformers team given a list of transformers.

It is a Spring boot application served from port "8080" and "transformersbattle" as context.

Instructions to deploy:
1. Clone the project from GitHub.
2. Import the project to IDE of choice.
3. To run all the unit tests
    a) Directly from command line (Maven must be configured in the machine): Use "mvn clean test" from the project root folder i.e., transformerbattle
    b) From Intellij IDEa: Use the Maven panel and double click on "test" from Lifecycle list
    c) From Eclipse: Right click on the imported project > Run as > Maven Test
   
4. To run the application from IDE, 
    a) Eclipse: Right click on "TransformerbattleApplication" in the package org.java.transformerbattle and use Run as application
    b) Intellij: Right click on "TransformerbattleApplication" in the package org.java.transformerbattle and use Run "TransformerbattleApplication".
   
5. Once deployed, the application will be accessible through http://localhost:8080/transformersbattle
6. Please refer http://localhost:8080/transformersbattle/swagger-ui/index.html for all information related to the created APIs
7. Could be tested through swagger or use the information from swagger to test from other apps like Postman or Insomnia or SOAP UI

Note: Application uses in-memory database, so the data will be lost once the server is stopped. DB console could be accessed at http://localhost:8080/transformersbattle/h2db

#Sample Input for creating/updating a Transformer
                {
                    "name": "Soundwave",
                    "type": "D",
                    "strength": 8,
                    "intelligence": 9,
                    "speed": 5,
                    "endurance": 2,
                    "rank": 6,  
                    "courage": 5,
                    "firePower": 2,
                    "skill": 9
                }

#Sample input for sending the IDs to battle
                {
                    "transformerIds": [1,2]
                }

Input to delete a transformer is the ID of the transformer as a path variable.