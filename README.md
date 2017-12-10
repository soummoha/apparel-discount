# apparel-discount
The Apparel Discount Spring Boot Application

## Pre-requisite
1. jdk 1.8 or higher
2. maven 3.x or higher
3. IDE - as per preference i.e Spring sts, eclipse, IntelliJ

## Usage

### Command Prompt

java -jar -Dspring.profiles.active=prod apparel-discount-0.0.1-SNAPSHOT.jar

### IDE

1. Set run configuration profile to "prod".
2. Run as spring boot application
  
After starting the application when the message appear  
"Please enter a valid inventory input or ctrl+z to exit".  

Enter Input:-  
5  
1,Arrow,Shirts,800  
2,Vero Moda,Dresses,1400  
3,Provogue,Footwear,1800  
4,Wrangler,Jeans,2200  
5,UCB,Shirts,1500  

You will receive a message:-  
"Products added to inventory"  
"Please enter a valid input to generate bills of products or ctrl+z to exit"   

Enter Input:-  
2  
1,2,3,4  
1,5  

Output will be printed:-  
3860  
2140  
