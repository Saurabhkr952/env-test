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
        // use the –clean option in the allure command to merge the folders and generate a report with history.
        //  sh 'allure generate --clean /allure-results/pytest1 /allure-results/pytest2 -o /allure-report' 
        // modify container name of pytest1 and pytest1
        allure includeProperties: false, jdk: '', results: [[path: 'allure-results']]
      }
    }
  }
}
