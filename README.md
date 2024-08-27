# notetakingapp
This is the backend for the project.  There are three parts: 1) backend written in java using spring, 2) frontend written in node + vite, and 3) A mysql/mariadb database.

1. A Jenkins docker container is created to build the container with docker added to it.  See the Dockerfile-jenkins for the code.
2. Run the docker container:
   * sudo docker build -t jenkins-withdocker .
   * sudo docker run -p 8089:8080 -v /var/run/docker.sock:/var/run/docker.sock --name jenkins jenkins-withdocker
   
# Jenkins Docker agent setup
Here are the steps to ensure your Jenkins is correctly set up to use Docker agents:

1. Ensure Docker Plugin is Installed
    1.	Go to Manage Jenkins > Manage Plugins.
    2.	In the Installed tab, check if the Docker and Docker Pipeline plugins are installed.
    3.	If not installed, go to the Available tab, search for Docker and Docker Pipeline, and install them.

2. Configure Docker in Jenkins
    1.	Go to Manage Jenkins > Configure System.
    2.	Scroll down to the Cloud section and click Add a new cloud.
    3.	Select Docker.
    4.	Configure the Docker Cloud:
       
*	Docker Host URI: unix:///var/run/docker.sock
*	Enabled: Check the box.
*	Name: Docker (or any name you prefer)

3. Create a Docker Agent Template
    1.	In the Docker Cloud configuration, click Add Docker Template.
    2.	Configure the Docker Agent Template:

*	Labels: docker-agent
*	Docker Image: maven:3.8.4-openjdk-17 (or any other image you need)
*	Remote File System Root: /home/jenkins/agent
*	Instance Capacity: 1
*	Usage: Only build jobs with label expressions matching this node
*	Enabled: Check the box.

4. Use the Docker Agent in Your Pipeline
Update your Jenkinsfile to use the label specified in the Docker Agent Template. (Use Jenkinsfile-build)


