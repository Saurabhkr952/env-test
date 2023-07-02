pipeline {
    agent any

    stages {
        stage('Checkout') {
            steps {
                dir('/absolute/path/to/local/files') {
                    // Perform actions within the specified directory
                    sh 'ls -la'
                    sh 'echo "Performing actions in the local files directory"'
                }
            }
        }
        
        // Other stages in your pipeline
    }
}


// Command to run Jenkins as a Docker container with Local path attached

docker run -it -p 8080:8080 -p 50000:50000 -v /var/run/docker.sock:/var/run/docker.sock -v /path/to/local/files:/path/in/container -v jenkins_home:/var/jenkins_home custom-jenkins-docker
