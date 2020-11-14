#Internaltionalization POC

This project shows how using Spring and Java one can adopt Internationalization 
Here the demonstation has been using Thymeleaf Template. 

##Technology used 
1. Thymeleaf 
2. Spring Boot with Gradle

##How to Use
1. download the file to your local 
2. Make Sure Gradle is present 
3. Run following command 
```
gradle bootrun
```
4. on browser run "http://localhost:8080/"
5. to update the language type "http://localhost:8080?lang=hi" for hindi , Similarly fr for French

###NOTE : Language gets updated per Session 
For the particular session the language will remain constant once you have set "?lang=<langCode>" 
even after browsing to separate page the language will remain constant unless user restarts server 
or updates the language preferance   