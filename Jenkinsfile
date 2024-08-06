pipeline {
  agent {
    dockerfile {
      // Define the Dockerfile to use for the agent
      filename 'Dockerfile'
      // Define the custom network to use
      args '-v $HOME/.m2:/root/.m2 --network=mynetwork'
      reuseNode true
    }
  }
  environment {
        DOCKER_IMAGE_NAME = "notetakingapp"
        DOCKER_IMAGE_TAG = "latest"
        MYSQL_ROOT_PASSWORD = ${MYSQL_ROOT_PASSWORD}
        MYSQL_DATABASE = 'note_taking_app'
        MYSQL_USER = ${MYSQL_USER}
        MYSQL_PASSWORD = ${MYSQL_PASSWORD}
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
