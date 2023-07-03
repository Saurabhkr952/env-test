pipeline {
  agent any
// modification needs to be done in container name pytest1 & pytest2
  stages {
    stage('Build and run docker-compose') {
      steps {
        sh 'docker-compose build'
        sh 'docker-compose up --exit-code-from pytest1'
        sh 'docker-compose up --exit-code-from pytest2'
        sh 'docker-compose down'
      }
    }

    stage('Publish allure report') {
      steps {
        allure includeProperties: false, jdk: '', results: [[path: 'allure-results']]
      }
    }
  }
}
