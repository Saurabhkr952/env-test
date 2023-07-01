pipeline {
    agent any
    
    environment {
        POSTGRES_DB = credentials('POSTGRES_DB')
        POSTGRES_USER = credentials('POSTGRES_USER')
        POSTGRES_PASSWORD = credentials('POSTGRES_PASSWORD')  // need to configure as secret text in jenkins credentials
    }

    stages {
        stage('Set environment variables') {
            steps {
                sh 'echo $POSTGRES_DB'
             
            }
        }

        stage('Run docker-compose') {
            steps {
                // Run the docker-compose command with environment variables
                sh 'docker-compose up -d'
            }
        }
    }
}
