# Endava Tech-Flow CI/CD
On this workshop we are going to create a CI pipeline from scratch to compile the code, run unit tests, build a docker image with the application for the frontend and backend. After that we are going to upload the docker image (artifact) to our artifacts repository and versioning the docker images to finally deploy on our environments. 

FIXME
Put the architecture here

## Set-Up environment
To setup the initial state of the environment please run the vagrant up; This command creates and configures guest machines according to your Vagrantfile on this repository.

**Please run this command on your home before go to the TechFlow event.** this process can takes ~30 minutes and above depending of your internet connection.

```
$vagrant up
```
If something goes wrong with the vagrant up command, run again the provision:
```
$vagrant provision
```
To continue with the workshop pleae go to [Step-by-step file](Manual/README.md)

---------------
#### Nexus
To send dockerimages to nexus repository you have to run:

Login
```
$docker login -u admin -p admin123 localhost:5000
```


Tag Docker Image:
```
$docker tag imageID localhost:5000/nexus:tagVersion
´´´

Push Docker Image
````
$docker push localhost:5000/nexus:tagVersion
´´´