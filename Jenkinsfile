pipeline {
  agent {
    dockerfile {
      // Define the Dockerfile to use for the agent
      filename 'Dockerfile'
      // Define the custom network to use
      args '--network=mynetwork'
    }
  }
  environment {
        DOCKER_IMAGE_NAME = "notetakingapp"
        DOCKER_IMAGE_TAG = "latest"
        MYSQL_ROOT_PASSWORD = 'rootpassword'
        MYSQL_DATABASE = 'note_taking_app'
        MYSQL_USER = 'noteuser'
        MYSQL_PASSWORD = 'password'
        MYSQL_HOST = 'mariadb'
    }
  stages {
    stage('Test') {
      steps {
        sh 'ls -lta /app'
      }
    }
  }
}
