# kafkaclient
implement kafkaclient

Prerequites :
  gradle build tool
  java 1.8 +
  
1.Create a client.property with kafaka details
2.Clone the code from github
 https://github.com/sivankumar86/kafkaclient
3.create a uber jar and run below command inside root folder

gradle clean build uberJar
cd build/libs/
java -jar kafka-all-in-one-jar.jar -clientconf /tmp/client.properties 

