pipeline {
    agent {
        // Set up your desired agent
    }
    
    stages {
        stage('Build') {
            steps {
                // Build and start the containers using docker-compose
                sh 'docker-compose up --exit-code-from container1 --exit-code-from container2'
            }
        }
        
        stage('Generate Allure Report') {
            steps {
                // Run the tests and generate the Allure results
                sh 'docker-compose run -v ./allure-results:/app/allure-results container1 pytest <path> -s --alluredir=/app/allure-results'
                sh 'docker-compose run -v ./allure-results:/app/allure-results container2 pytest <path> -s --alluredir=/app/allure-results'
                
                // Generate the HTML report using the Allure command-line tool inside a container
                sh 'docker-compose run -v ./allure-results:/app/allure-results allure generate /app/allure-results -o /app/allure-report'
            }
        }
        
        stage('Publish Allure Report') {
            steps {
                // Copy the Allure report from the containers to the Jenkins workspace
                sh 'docker cp container1:/app/allure-report ./allure-report-container1'
                sh 'docker cp container2:/app/allure-report ./allure-report-container2'
                
                // Archive the Allure reports as artifacts
                archiveArtifacts artifacts: 'allure-report-container1/**/*,allure-report-container2/**/*', fingerprint: true
            }
        }
    }
}

// volume and container name should be modified
