pipeline {
  agent {
    dockerfile {
      // Define the Dockerfile to use for the agent
      filename 'Dockerfile'
      // Define the custom network to use
      args '--network=mynetwork'
    }
  }
  stages {
    stage('Test') {
      steps {
        sh 'ls -lta /app'
      }
    }
  }
}
